/*
 * Copyright 2008 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.gwt.gadgets.rebind;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.JParameter;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.gadgets.client.GadgetFeature;
import com.google.gwt.gadgets.client.Gadget.ModulePrefs;
import com.google.gwt.gadgets.client.GadgetFeature.FeatureName;
import com.google.gwt.gadgets.client.UserPreferences.DataType;
import com.google.gwt.gadgets.client.UserPreferences.Preference;
import com.google.gwt.gadgets.client.UserPreferences.PreferenceAttributes;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;

/**
 * Write the top layer in the Gadget bootstrap sandwich and generate a stub
 * manifest that will be completed by the linker.
 */
public class GadgetGenerator extends Generator {

  @Override
  public String generate(TreeLogger logger, GeneratorContext context,
      String typeName) throws UnableToCompleteException {

    // The TypeOracle knows about all types in the type system
    TypeOracle typeOracle = context.getTypeOracle();

    // Get a reference to the type that the generator should implement
    JClassType sourceType = typeOracle.findType(typeName);

    // Ensure that the requested type exists
    if (sourceType == null) {
      logger.log(TreeLogger.ERROR, "Could not find requested typeName", null);
      throw new UnableToCompleteException();
    }

    // Make sure the Gadget type is correctly defined
    validateType(logger, sourceType);

    // Pick a name for the generated class to not conflict.
    String generatedSimpleSourceName = sourceType.getSimpleSourceName()
        + "GadgetImpl";

    // Begin writing the generated source.
    ClassSourceFileComposerFactory f = new ClassSourceFileComposerFactory(
        sourceType.getPackage().getName(), generatedSimpleSourceName);
    f.addImport(GWT.class.getName());
    f.setSuperclass(typeName);

    // All source gets written through this Writer
    PrintWriter out = context.tryCreate(logger,
        sourceType.getPackage().getName(), generatedSimpleSourceName);

    // If an implementation already exists, we don't need to do any work
    if (out != null) {

      JClassType userPrefsType = GadgetUtils.getUserPrefsType(logger,
          typeOracle, sourceType);

      // We really use a SourceWriter since it's convenient
      SourceWriter sw = f.createSourceWriter(context, out);
      sw.println("public " + generatedSimpleSourceName + "() {");
      sw.indent();
      sw.println("nativeInit();");
      sw.println("init((" + userPrefsType.getQualifiedSourceName()
          + ")GWT.create(" + userPrefsType.getQualifiedSourceName()
          + ".class));");
      sw.outdent();
      sw.println("}");

      sw.println("private native void nativeInit() /*-{");
      sw.indent();
      for (JClassType interfaceType : sourceType.getImplementedInterfaces()) {
        generateFeatureInitializer(logger, typeOracle, sw, sourceType,
            interfaceType);
      }
      sw.outdent();
      sw.println("}-*/;");

      // Write out the manifest
      OutputStream manifestOut = context.tryCreateResource(logger, typeName
          + ".gadget.xml");
      if (manifestOut == null) {
        logger.log(TreeLogger.ERROR, "Gadget manifest was already created",
            null);
        throw new UnableToCompleteException();
      }

      generateGadgetManifest(logger, typeOracle, sourceType, new PrintWriter(
          new OutputStreamWriter(manifestOut)));
      context.commitResource(logger, manifestOut);

      sw.commit(logger);
    }

    return f.getCreatedClassName();
  }

  protected void configurePreferenceElement(TreeLogger logger, Document d,
      Element userPref, JClassType preferenceType, JMethod m)
      throws UnableToCompleteException {
    logger = logger.branch(TreeLogger.DEBUG,
        "Generating userpref element for method " + m.getReadableDeclaration(),
        null);

    JClassType prefType = m.getReturnType().isClassOrInterface();

    if (prefType == null || !preferenceType.isAssignableFrom(prefType)) {
      logger.log(TreeLogger.ERROR, m.getReturnType().getQualifiedSourceName()
          + " is not assignable to " + preferenceType.getQualifiedSourceName(),
          null);
      throw new UnableToCompleteException();
    }

    DataType dataType = prefType.getAnnotation(DataType.class);

    if (dataType == null) {
      logger.log(TreeLogger.ERROR, prefType
          + " must define a DataType annotation", null);
      throw new UnableToCompleteException();
    }

    userPref.setAttribute("name", m.getName());
    userPref.setAttribute("datatype", dataType.value());

    PreferenceAttributes attributes = m.getAnnotation(PreferenceAttributes.class);
    if (attributes != null) {
      GadgetUtils.writeAnnotationToElement(logger, attributes, userPref);

      switch (attributes.options()) {
        case HIDDEN:
          userPref.setAttribute("datatype", "hidden");
          break;
        case NORMAL:
          break;
        case REQUIRED:
          userPref.setAttribute("required", "true");
          break;
        default:
          logger.log(TreeLogger.ERROR, "Unknown Option "
              + attributes.options().name(), null);
          throw new UnableToCompleteException();
      }
    }

    // Allow type-specific modifications to the userpref Element to be made
    PreferenceGenerator prefGenerator = GadgetUtils.getPreferenceGenerator(
        logger, prefType);
    prefGenerator.configurePreferenceElement(logger, d, userPref, prefType, m);
  }

  protected void generateFeatureInitializer(TreeLogger logger,
      TypeOracle typeOracle, SourceWriter sw, JClassType gadgetType,
      JClassType featureType) throws UnableToCompleteException {
    logger = logger.branch(TreeLogger.DEBUG,
        "Generating GadgetFeature initializer for type "
            + featureType.getQualifiedSourceName(), null);
    FeatureName name = featureType.getAnnotation(FeatureName.class);
    if (name == null) {
      return;
    }

    JMethod[] methods = featureType.getMethods();
    if (methods.length != 1) {
      logger.log(TreeLogger.ERROR,
          "A Feature interface must define exactly one method", null);
      throw new UnableToCompleteException();
    }

    JMethod m = methods[0];
    JParameter[] params = m.getParameters();

    if (params.length != 1) {
      logger.log(TreeLogger.ERROR, m.getName()
          + " must have exactly one parameter", null);
      throw new UnableToCompleteException();
    }

    JClassType paramType = params[0].getType().isClass();
    JClassType gadgetFeatureType = typeOracle.findType(GadgetFeature.class.getName());
    assert gadgetFeatureType != null;

    if (paramType == null || paramType.isAbstract()) {
      logger.log(TreeLogger.ERROR, "The parameter " + params[0].getName()
          + " must be a concrete class", null);
      throw new UnableToCompleteException();

    } else if (!gadgetFeatureType.isAssignableFrom(paramType)) {
      logger.log(TreeLogger.ERROR, "The parameter " + params[0].getName()
          + " must be assignable to GadgetFeature", null);
      throw new UnableToCompleteException();

    } else {
      try {
        paramType.getConstructor(new JType[0]);
      } catch (NotFoundException e) {
        logger.log(TreeLogger.ERROR,
            "The parameter type must have a zero-arg constructor", e);
        throw new UnableToCompleteException();
      }
    }

    sw.println("this.@" + gadgetType.getQualifiedSourceName() + "::"
        + m.getName() + "(" + paramType.getJNISignature() + ")(@"
        + paramType.getQualifiedSourceName() + "::new()());");
  }

  protected void generateGadgetManifest(TreeLogger logger,
      TypeOracle typeOracle, JClassType type, Writer out)
      throws UnableToCompleteException {
    logger = logger.branch(TreeLogger.DEBUG, "Building gadget manifest", null);

    Document d;
    LSSerializer serializer;
    LSOutput output;

    // Initialize the XML document
    try {
      DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
      DOMImplementation impl = registry.getDOMImplementation("Core 3.0");
      d = impl.createDocument(null, null, null);
      DOMImplementationLS implLS = (DOMImplementationLS) impl.getFeature("LS",
          "3.0");
      output = implLS.createLSOutput();
      output.setCharacterStream(out);
      serializer = implLS.createLSSerializer();
    } catch (ClassNotFoundException e) {
      logger.log(TreeLogger.ERROR, "Could not create document", e);
      throw new UnableToCompleteException();
    } catch (IllegalAccessException e) {
      logger.log(TreeLogger.ERROR, "Could not create document", e);
      throw new UnableToCompleteException();
    } catch (InstantiationException e) {
      logger.log(TreeLogger.ERROR, "Could not create document", e);
      throw new UnableToCompleteException();
    }

    // Root elements
    Element module = (Element) d.appendChild(d.createElement("Module"));
    Element modulePrefs = (Element) module.appendChild(d.createElement("ModulePrefs"));

    // Write out the ModulePrefs tag
    ModulePrefs prefs = type.getAnnotation(ModulePrefs.class);
    if (prefs != null) {
      GadgetUtils.writeAnnotationToElement(logger, prefs, modulePrefs,
          "requirements");
      GadgetUtils.writeRequirementsToElement(logger, d, modulePrefs,
          prefs.requirements());
    }

    // Write out the UserPref tags
    JClassType preferenceType = typeOracle.findType(Preference.class.getName().replace('$', '.'));
    assert preferenceType != null;

    JClassType prefsType = GadgetUtils.getUserPrefsType(logger, typeOracle,
        type);
    for (JMethod m : prefsType.getOverridableMethods()) {
      Element userPref = (Element) module.appendChild(d.createElement("UserPref"));
      configurePreferenceElement(logger, d, userPref, preferenceType, m);
    }

    // Add required features to the manifest
    // <require feature="someFeature" />
    for (JClassType interfaceType : type.getImplementedInterfaces()) {
      FeatureName name = interfaceType.getAnnotation(FeatureName.class);
      if (name != null) {
        for (String feature : name.value()) {
          // Skip features defined to be implicitly available in the container
          if (FeatureName.INTRINSIC.equals(feature)) {
            continue;
          }
          Element require = (Element) modulePrefs.appendChild(d.createElement("Require"));
          require.setAttribute("feature", feature);
        }
        GadgetUtils.writeRequirementsToElement(logger, d, modulePrefs,
          name.requirements());
      }
    }

    // The Gadget linker will fill in the bootstrap
    // <content type="html">
    Element content = (Element) module.appendChild(d.createElement("Content"));
    content.setAttribute("type", "html");
    content.appendChild(d.createCDATASection("__BOOTSTRAP__"));

    serializer.write(d, output);
  }

  protected void validateType(TreeLogger logger, JClassType type)
      throws UnableToCompleteException {
    if (!type.isDefaultInstantiable()) {
      logger.log(TreeLogger.ERROR, "Gadget types must be default instantiable",
          null);
      throw new UnableToCompleteException();
    }
  }
}
