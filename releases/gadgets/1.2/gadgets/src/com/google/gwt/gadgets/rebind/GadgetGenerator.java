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
import com.google.gwt.gadgets.client.Gadget.InjectContent;
import com.google.gwt.gadgets.client.Gadget.InjectModulePrefs;
import com.google.gwt.gadgets.client.Gadget.ModulePrefs;
import com.google.gwt.gadgets.client.GadgetFeature.FeatureName;
import com.google.gwt.gadgets.client.UserPreferences.DataType;
import com.google.gwt.gadgets.client.UserPreferences.Preference;
import com.google.gwt.gadgets.client.UserPreferences.PreferenceAttributes;
import com.google.gwt.gadgets.rebind.GadgetUtils.GadgetViewType;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

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
      sw.println("initializeFeatures();");
      sw.println("init((" + userPrefsType.getQualifiedSourceName()
          + ")GWT.create(" + userPrefsType.getQualifiedSourceName()
          + ".class));");
      sw.outdent();
      sw.println("}");

      generateFeatureInitializers(logger, typeOracle, sw, sourceType);

      // Write out the manifest
      String manifestName = typeName;
      if (!GadgetUtils.useLongManifestName(logger, typeOracle, sourceType)) {
        int lastIndex = manifestName.lastIndexOf('.');
        if (lastIndex != -1) {
          manifestName = manifestName.substring(lastIndex + 1);
        }
      }
      OutputStream manifestOut = context.tryCreateResource(logger, manifestName
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

  /**
   * Creates a single Content section.
   *
   * @param logger for logging errors
   * @param type either the Gadget or ContentView sub-class the section is for
   * @param d the document we use to create the Content XML element for
   * @param viewName the name of the view. Can be empty or null, if not name is
   *          given
   * @return the XML Content element
   * @throws UnableToCompleteException
   */
  protected Element createContentSection(TreeLogger logger, JClassType type,
      Document d, String viewName, boolean quirksMode)
      throws UnableToCompleteException {
    StringBuilder contentToInject = new StringBuilder();
    if (!quirksMode) {
      contentToInject.append("<!DOCTYPE html>");
    }
    getInjectedContent(logger, type, contentToInject);
    // The Gadget linker will fill in the bootstrap
    // <content type="html">
    Element content = d.createElement("Content");
    content.setAttribute("type", "html");
    if (viewName != null && viewName.length() > 0) {
      content.setAttribute("view", viewName);
      /*
       * Add a piece of JS code that sets the name of this view. We use this to
       * select the right code. Thus we don't have to rely on the views feature
       * being present. We could use gadget.views.* functionality, but we
       * shouldn't enforce the "views" feature to be present, as it adds API
       * that the gadget doesn't use.
       */
      contentToInject.append("<script>window.gadgetViewName = '"
          + viewName.replace("\'", "\\\'") + "';</script>");
    }
    content.appendChild(d.createCDATASection(contentToInject + "__BOOTSTRAP__"));
    return content;
  }

  /**
   * Returns one or more (in case the content section is set for multiple
   * views).
   *
   * {@link Element}s for each content section that this gadget contains.
   */
  protected Element[] createContentSections(TreeLogger logger,
      JClassType gadgetSourceType, TypeOracle typeOracle, Document d)
      throws UnableToCompleteException {
    boolean quirksMode = GadgetUtils.allowHtmlQuirksMode(logger, typeOracle,
        gadgetSourceType);
    // TODO(haeberling): Check that a Gadget class has not Content AND
    // InjectContent
    List<Element> result = new ArrayList<Element>();
    GadgetViewType[] gadgetViewTypes = GadgetUtils.getViewTypes(logger,
        gadgetSourceType, typeOracle);

    if (gadgetViewTypes == null) {
      // If the Gadget class doesn't have a Content annotation, we treat it as a
      // single-view gadget.
      result.add(createContentSection(logger, gadgetSourceType, d, null,
          quirksMode));
    } else {
      logger.log(TreeLogger.INFO, "Using multi-view generation mode.");
      for (int i = 0; i < gadgetViewTypes.length; ++i) {
        // If a view type has been assigned multiple view, we create a separate
        // content section for each.
        // The gadget spec would support having one content
        // section and the view names concatenated with commas, but a GWT
        // property provider must have a fixed list of possible values.
        String[] viewNames = gadgetViewTypes[i].viewNames;
        for (String viewName : viewNames) {
          result.add(createContentSection(logger, gadgetViewTypes[i].viewType,
              d, viewName, quirksMode));
        }
      }
    }
    return result.toArray(new Element[0]);
  }

  protected void generateFeatureInitializers(TreeLogger logger,
      TypeOracle typeOracle, SourceWriter sw, JClassType gadgetType)
      throws UnableToCompleteException {
    JClassType gadgetFeatureType = typeOracle.findType(GadgetFeature.class.getName());
    assert gadgetFeatureType != null;
    // Maps from the feature class to the name of a method
    // initializing this feature
    Map<JClassType, String> deferredFeatures = new HashMap<JClassType, String>();
    Map<JClassType, String> concreteFeatures = new HashMap<JClassType, String>();

    // Looping through features add adding them to maps
    // Looking at the superclass in case any Features are needed there.
    for (JClassType currentClass = gadgetType; currentClass != null; currentClass = currentClass.getSuperclass()) {
      for (JClassType interfaceType : currentClass.getImplementedInterfaces()) {

        JClassType featureType = interfaceType;

        TreeLogger branchedLogger = logger.branch(TreeLogger.DEBUG,
            "Generating GadgetFeature initializer for type "
                + featureType.getQualifiedSourceName(), null);
        FeatureName name = featureType.getAnnotation(FeatureName.class);
        if (name == null) {
          continue;
        }

        JMethod[] methods = featureType.getMethods();
        if (methods.length == 0) {
          branchedLogger.log(TreeLogger.DEBUG, featureType.getName()
              + " defines no methods", null);
          continue;
        } else if (methods.length > 1) {
          branchedLogger.log(TreeLogger.ERROR,
              "A Feature interface must define no more then one method", null);
          throw new UnableToCompleteException();
        }

        JMethod m = methods[0];
        JParameter[] params = m.getParameters();

        if (params.length != 1) {
          branchedLogger.log(TreeLogger.ERROR, m.getName()
              + " must have exactly one parameter", null);
          throw new UnableToCompleteException();
        }

        JClassType featureClass = params[0].getType().isClassOrInterface();

        if (featureClass == null) {
          branchedLogger.log(TreeLogger.ERROR, "The parameter "
              + params[0].getName() + " must be a class or interface", null);
          throw new UnableToCompleteException();

        } else if (featureClass.isInterface() != null
            || featureClass.isAbstract()) {
          deferredFeatures.put(featureClass, m.getName());
        } else {
          try {
            featureClass.getConstructor(new JType[0]);
          } catch (NotFoundException e) {
            branchedLogger.log(TreeLogger.ERROR, "The "
                + featureClass.getName()
                + " type must have a zero-arg constructor", e);
            throw new UnableToCompleteException();
          }
          if (!gadgetFeatureType.isAssignableFrom(featureClass)) {
            branchedLogger.log(TreeLogger.ERROR, "The parameter "
                + params[0].getName() + " must be assignable to GadgetFeature",
                null);
            throw new UnableToCompleteException();
          }
          concreteFeatures.put(featureClass, m.getName());
        }
      }
    }
    // Writing initializing code
    // Features instantiated with deferred bindings are created in Java
    sw.println("private void initializeFeatures() {");
    sw.indent();
    for (Map.Entry<JClassType, String> feature : deferredFeatures.entrySet()) {
      sw.println(feature.getValue() + "(("
          + feature.getKey().getQualifiedSourceName() + ")GWT.create("
          + feature.getKey().getQualifiedSourceName() + ".class));");
    }
    sw.outdent();
    sw.println("initializeConcreteFeatures();");
    sw.println("}");
    // Features instantiated with a no-arg constructor are created in JS for
    // access to non-public code
    sw.println("private native void initializeConcreteFeatures() /*-{");
    sw.indent();
    for (Map.Entry<JClassType, String> feature : concreteFeatures.entrySet()) {
      sw.println("this.@" + gadgetType.getQualifiedSourceName() + "::"
          + feature.getValue() + "(" + feature.getKey().getJNISignature()
          + ")(@" + feature.getKey().getQualifiedSourceName() + "::new()());");
    }
    sw.outdent();
    sw.println("}-*/;");
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

    addInjectedModulePrefs(logger, type, modulePrefs);

    // Write out the ModulePrefs tag
    ModulePrefs prefs = type.getAnnotation(ModulePrefs.class);
    if (prefs != null) {
      GadgetUtils.writeAnnotationToElement(logger, prefs, modulePrefs,
          "requirements", "locales");
      GadgetUtils.writeRequirementsToElement(logger, d, modulePrefs,
          prefs.requirements());
      GadgetUtils.writeLocalesToElement(logger, d, modulePrefs, prefs.locales());

    }

    // Write out the UserPref tags
    JClassType preferenceType = typeOracle.findType(Preference.class.getName().replace(
        '$', '.'));
    assert preferenceType != null;

    JClassType prefsType = GadgetUtils.getUserPrefsType(logger, typeOracle,
        type);
    for (JMethod m : prefsType.getOverridableMethods()) {
      Element userPref = (Element) module.appendChild(d.createElement("UserPref"));
      configurePreferenceElement(logger, d, userPref, preferenceType, m);
    }

    // Add required features to the manifest
    // <require feature="someFeature" />
    for (JClassType currentClass = type; currentClass != null; currentClass = currentClass.getSuperclass()) {
      for (JClassType interfaceType : currentClass.getImplementedInterfaces()) {
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
    }

    // Generate and append the Content section(s).
    for (Element contentSection : createContentSections(logger, type,
        typeOracle, d)) {
      module.appendChild(contentSection);
    }
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

  /**
   * Inject additional hand-written XML into the gadget's XML file. Get the
   *
   * @InjectModulePrefs annotation, where the file for injection is specified
   *                    and add it as a child of the modlePrefs element.
   *
   * @param logger for logging errors
   * @param type the Gadget subclass we are generating code for
   * @param modulePrefs Element in the gadget speck representing
   *          &lt;ModulePrefs&gt;
   */
  private void addInjectedModulePrefs(TreeLogger logger, JClassType type,
      Element modulePrefs) throws UnableToCompleteException {

    InjectModulePrefs injectContent = type.getAnnotation(InjectModulePrefs.class);
    if (injectContent != null) {
      String[] injectionFiles = injectContent.files();
      for (String filename : injectionFiles) {
        DocumentBuilder builder;
        Document parsedDoc;
        try {
          builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
          logger.log(TreeLogger.ERROR, "Error creating XML parser", ex);
          throw new UnableToCompleteException();
        }
        if (!"".equals(filename)) {

          try {
            InputSource source = new InputSource();
            source.setCharacterStream(new StringReader(readFileToInject(logger,
                type, filename)));
            parsedDoc = builder.parse(source);
          } catch (IOException ex) {
            logger.log(TreeLogger.ERROR,
                "Error reading file with injected ModulePrefs content: "
                    + filename, ex);
            throw new UnableToCompleteException();
          } catch (SAXException ex) {
            logger.log(TreeLogger.ERROR,
                "Error parsing file with injected ModulePrefs content: "
                    + filename, ex);
            throw new UnableToCompleteException();
          }
          Element element = parsedDoc.getDocumentElement();
          Node adoptedNode = modulePrefs.getOwnerDocument().importNode(element,
              true);
          modulePrefs.appendChild(adoptedNode);
        }
      }
    }
  }

  /**
   * Inject additional hand-written JavaScript to be written into the gadget's
   * XML file in the &lt;Content&gt; section.
   *
   * @param logger for logging errors
   * @param type the Gadget subclass we are generating code for
   * @param contentToInject a StringBuilder to which all files annotated in the
   * @InjectContent annotation will be added to.
   * @throws UnableToCompleteException
   */
  private void getInjectedContent(TreeLogger logger, JClassType type,
      StringBuilder contentToInject) throws UnableToCompleteException {
    // Get additional prefs annotation, where the file for injection is
    // specified.
    InjectContent injectContent = type.getAnnotation(InjectContent.class);
    if (injectContent != null) {
      String[] injectionFiles = injectContent.files();
      for (String filename : injectionFiles) {
        if (!"".equals(filename)) {
          contentToInject.append(readFileToInject(logger, type, filename));
        }
      }
    }
  }

  /**
   * Reads the file for injection from the classpath and returns its contents.
   *
   * @param logger For logging.
   * @param gadgetClass The main gadget class that contained the annotation.
   *          This class should be in a '*.client' package.
   * @param filename The path filename of the file to inject. If the name does
   *          not start with a '/', it is assumed to be relative to the same
   *          package as the gadgetClass.
   * @return The contents of the file or an empty string, if an error occurred
   */
  private String readFileToInject(TreeLogger logger, JClassType gadgetClass,
      String filename) throws UnableToCompleteException {
    StringBuilder buffer = new StringBuilder();
    String packageName = gadgetClass.getPackage().getName();
    String pathToFile;
    // If the filename starts with a '/' it is assumed to be the name of a
    // resource on the classpath.
    if (filename.startsWith("/")) {
      pathToFile = filename.substring(1);
    } else {
      pathToFile = packageName.replace(".", "/") + "/" + filename;
    }
    // Try to read the file for injection from the same .
    try {
      ClassLoader loader = getClass().getClassLoader();
      InputStream is = loader.getResourceAsStream(pathToFile);
      if (is == null) {
        logger.branch(TreeLogger.ERROR, "Unable to read injection file from '"
            + pathToFile);
        throw new UnableToCompleteException();
      }
      BufferedReader reader = new BufferedReader(new InputStreamReader(is,
          "UTF-8"));
      String line;
      while ((line = reader.readLine()) != null) {
        buffer.append(line + "\n");
      }
    } catch (IOException e) {
      logger.branch(TreeLogger.ERROR, "Unable to read injection file from '"
          + pathToFile, e);
      throw new UnableToCompleteException();
    }
    return buffer.toString();
  }
}
