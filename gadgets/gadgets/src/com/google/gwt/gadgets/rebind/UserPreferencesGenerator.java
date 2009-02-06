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
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.gadgets.client.UserPreferences.Preference;
import com.google.gwt.gadgets.client.impl.PreferencesUtil;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;

import java.io.PrintWriter;

/**
 * Provides a binding from a UserPreferences subtype to the Gadget container's
 * preferences API.
 */
public class UserPreferencesGenerator extends Generator {

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

    // Make sure the UserPreferences type is correctly defined
    validateType(logger, sourceType);

    // Pick a name for the generated class to not conflict.
    String generatedSimpleSourceName = sourceType.getSimpleSourceName()
        + "UserPreferencesImpl";

    // Begin writing the generated source.
    ClassSourceFileComposerFactory f = new ClassSourceFileComposerFactory(
        sourceType.getPackage().getName(), generatedSimpleSourceName);
    f.addImport(GWT.class.getName());
    f.addImport(PreferencesUtil.class.getName());
    f.addImplementedInterface(typeName);

    // All source gets written through this Writer
    PrintWriter out = context.tryCreate(logger,
        sourceType.getPackage().getName(), generatedSimpleSourceName);

    // If an implementation already exists, we don't need to do any work
    if (out != null) {
      JClassType preferenceType = typeOracle.findType(
          Preference.class.getName().replace('$', '.'));
      assert preferenceType != null;

      // We really use a SourceWriter since it's convenient
      SourceWriter sw = f.createSourceWriter(context, out);

      for (JMethod m : sourceType.getOverridableMethods()) {
        JClassType extendsPreferenceType = m.getReturnType().isClassOrInterface();
        if (extendsPreferenceType == null) {
          logger.log(TreeLogger.ERROR, "Return type of " + m.getName()
              + " is not a class or interface (must be assignable to "
              + preferenceType.getName() + ").", null);
          throw new UnableToCompleteException();
        }

        if (!preferenceType.isAssignableFrom(extendsPreferenceType)) {
          logger.log(TreeLogger.ERROR, "Cannot assign "
              + extendsPreferenceType.getQualifiedSourceName() + " to "
              + preferenceType.getQualifiedSourceName(), null);
          throw new UnableToCompleteException();
        }

        // private final FooProperty __propName = new FooProperty() {...}
        sw.println("private final "
            + extendsPreferenceType.getParameterizedQualifiedSourceName()
            + " __" + m.getName() + " = ");
        sw.indent();
        writeInstantiation(logger, sw, extendsPreferenceType, m);
        sw.println(";");
        sw.outdent();

        // public FooProperty property() { return __property; }
        sw.print("public ");
        sw.print(m.getReadableDeclaration(true, true, true, true, true));
        sw.println("{");
        sw.indent();
        sw.println("return __" + m.getName() + ";");
        sw.outdent();
        sw.println("}");
      }

      sw.commit(logger);
    }

    return f.getCreatedClassName();
  }

  protected void validateType(TreeLogger logger, JClassType sourceType)
      throws UnableToCompleteException {
    if (sourceType.isInterface() == null) {
      logger.log(TreeLogger.ERROR, "UserPreferences type must be interfaces",
          null);
      throw new UnableToCompleteException();
    }
  }

  /**
   * Write an instantiation expression for a given Preference subtype.
   */
  protected void writeInstantiation(TreeLogger logger, SourceWriter sw,
      JClassType extendsPreferenceType, JMethod prefMethod)
      throws UnableToCompleteException {

    PreferenceGenerator prefGenerator = GadgetUtils.getPreferenceGenerator(
        logger, extendsPreferenceType);
    prefGenerator.writeInstantiation(logger, sw, extendsPreferenceType,
        prefMethod);
  }
}
