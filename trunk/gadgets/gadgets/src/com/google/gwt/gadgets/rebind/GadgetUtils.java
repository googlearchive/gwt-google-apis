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

import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JGenericType;
import com.google.gwt.core.ext.typeinfo.JParameterizedType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.gadgets.client.ContentSection;
import com.google.gwt.gadgets.client.Gadget;
import com.google.gwt.gadgets.client.LanguageDirection;
import com.google.gwt.gadgets.client.Gadget.AllowHtmlQuirksMode;
import com.google.gwt.gadgets.client.Gadget.Content;
import com.google.gwt.gadgets.client.Gadget.ContentType;
import com.google.gwt.gadgets.client.Gadget.GadgetLocale;
import com.google.gwt.gadgets.client.Gadget.UseLongManifestName;
import com.google.gwt.gadgets.client.GadgetFeature.MayRequire;
import com.google.gwt.gadgets.client.impl.PreferenceGeneratorName;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Utility methods for Gadget Generators.
 */
class GadgetUtils {

  static class GadgetViewType {
    String[] viewNames;
    JClassType viewType;

    public GadgetViewType(String[] viewNames, JClassType viewType) {
      this.viewNames = viewNames;
      this.viewType = viewType;
    }
  }

  static boolean allowHtmlQuirksMode(TreeLogger logger, TypeOracle typeOracle,
      JClassType extendsGadget) throws UnableToCompleteException {
    AllowHtmlQuirksMode annotation = extendsGadget.getAnnotation(Gadget.AllowHtmlQuirksMode.class);
    if (annotation == null) {
      logger.log(TreeLogger.WARN, "Gadget class " + extendsGadget.getName()
          + " is missing @AllowHtmlQuirksMode. "
          + "Using standards mode will become the default in the future.");
      return true;
    }
    return annotation.value();
  }

  /**
   * Return an instance of a PreferenceGenerator that can be used for a subtype
   * of Preference.
   */
  static PreferenceGenerator getPreferenceGenerator(TreeLogger logger,
      JClassType extendsPreferenceType) throws UnableToCompleteException {

    PreferenceGeneratorName generator = extendsPreferenceType.getAnnotation(PreferenceGeneratorName.class);

    if (generator == null) {
      logger.log(TreeLogger.ERROR, "No PreferenceGenerator defined for type "
          + extendsPreferenceType.getQualifiedSourceName(), null);
      throw new UnableToCompleteException();
    }

    try {
      String typeName = generator.value();

      Class<? extends PreferenceGenerator> clazz = Class.forName(typeName).asSubclass(
          PreferenceGenerator.class);
      return clazz.newInstance();
    } catch (ClassCastException e) {
      logger.log(TreeLogger.ERROR, "Not a PreferenceGenerator", e);
      throw new UnableToCompleteException();
    } catch (ClassNotFoundException e) {
      logger.log(TreeLogger.ERROR, "Unable to create PreferenceGenerator", e);
      throw new UnableToCompleteException();
    } catch (InstantiationException e) {
      logger.log(TreeLogger.ERROR, "Unable to create PreferenceGenerator", e);
      throw new UnableToCompleteException();
    } catch (IllegalAccessException e) {
      logger.log(TreeLogger.ERROR, "Unable to create PreferenceGenerator", e);
      throw new UnableToCompleteException();
    }
  }

  /**
   * Returns the subtype of UserPreferencesaccepted by a Gadget.
   */
  static JClassType getUserPrefsType(TreeLogger logger, TypeOracle typeOracle,
      JClassType extendsGadget) throws UnableToCompleteException {
    JGenericType gadgetType = typeOracle.findType(Gadget.class.getName()).isGenericType();
    assert gadgetType != null;

    JParameterizedType paramType = null;

    while (true) {
      paramType = extendsGadget.isParameterized();

      if (paramType != null && gadgetType.equals(paramType.getBaseType())) {
        break;
      }

      extendsGadget = extendsGadget.getSuperclass();

      if (extendsGadget == null) {
        logger.log(TreeLogger.ERROR, "Unable to find Gadget in type hierarchy",
            null);
        throw new UnableToCompleteException();
      }
    }

    JClassType[] typeArgs = paramType.getTypeArgs();

    assert typeArgs.length == 1;
    JClassType toReturn = typeArgs[0].isClassOrInterface();
    if (toReturn == null) {
      logger.log(TreeLogger.ERROR,
          "A Gadget's UserPreferences type must be an interface", null);
      throw new UnableToCompleteException();
    }

    return toReturn;
  }

  /**
   * For the given gadget source type this function looks at it's
   * {@link Content} annotation to see if multiple views have been defined. If
   * so, it will return an array which contains the view names and their
   * associated {@link ContentSection} types.
   *
   * @return An array of views and associated types or null, if no views have
   *         been specified.
   */
  static GadgetViewType[] getViewTypes(TreeLogger logger,
      JClassType gadgetSourceType, TypeOracle typeOracle)
      throws UnableToCompleteException {
    List<GadgetViewType> result = new ArrayList<GadgetViewType>();
    Content content = gadgetSourceType.getAnnotation(Content.class);
    if (content != null) {
      for (Class<? extends ContentSection<?>> contentSectionClass : content.contents()) {
        String viewTypeName = contentSectionClass.getName().replaceAll("\\$",
            ".");
        JClassType viewType = typeOracle.findType(viewTypeName);
        if (viewType != null) {
          ContentType contentType = viewType.getAnnotation(ContentType.class);
          result.add(new GadgetViewType(contentType.views(), viewType));
        } else {
          logger.log(TreeLogger.ERROR, "Unable to find view type: "
              + viewTypeName);
          throw new UnableToCompleteException();
        }
      }
    } else {
      return null;
    }
    return result.toArray(new GadgetViewType[0]);
  }

  static boolean useLongManifestName(TreeLogger logger, TypeOracle typeOracle,
      JClassType extendsGadget) {
    UseLongManifestName annotation = extendsGadget.getAnnotation(Gadget.UseLongManifestName.class);
    if (annotation == null) {
      logger.log(TreeLogger.WARN, "Gadget class " + extendsGadget.getName()
          + " is missing @UseLongManifestName annotation.  "
          + "Using short names will become the default in the future.");
      return true;
    }
    return annotation.value();
  }

  /**
   * Add the key-value pairs of an Annotation to an Element. Enumerated
   * properties will be ignored. Specific properties can be excluded by name.
   */
  static void writeAnnotationToElement(TreeLogger logger, Annotation a,
      Element elt, String... excludeNames) throws UnableToCompleteException {
    List<String> excludeList = Arrays.asList(excludeNames);
    for (Method m : a.annotationType().getDeclaredMethods()) {
      try {
        String name = m.getName();
        Object value = m.invoke(a);
        if (value instanceof Enum) {
          continue;
        } else if (excludeList.contains(name)) {
          continue;
        } else if (!value.equals(m.getDefaultValue())) {
          elt.setAttribute(name, value.toString());
        }
      } catch (IllegalAccessException e) {
        logger.log(TreeLogger.ERROR, "Could not decode annotation", e);
        throw new UnableToCompleteException();
      } catch (InvocationTargetException e) {
        logger.log(TreeLogger.ERROR, "Could not decode annotation", e);
        throw new UnableToCompleteException();
      }
    }
  }

  static void writeLocalesToElement(TreeLogger logger, Document d,
      Element parent, GadgetLocale[] locales) throws UnableToCompleteException {
    for (GadgetLocale locale : locales) {
      Element localeElement = (Element) parent.appendChild(d.createElement("Locale"));
      String country = locale.country();
      String lang = locale.lang();
      if (country.length() == 0 && lang.length() == 0) {
        logger.log(TreeLogger.ERROR,
            "All @GadgetLocale specifictions must include either lang() or country().");
        throw new UnableToCompleteException();
      }
      writeAnnotationToElement(logger, locale, localeElement, "language_direction");
      LanguageDirection direction = locale.language_direction();
      if (direction != null && direction != LanguageDirection.UNSPECIFIED) {
        localeElement.setAttribute("language_direction", direction.getValue());
      }
    }
  }

  static void writeRequirementsToElement(TreeLogger logger, Document d,
      Element parent, MayRequire[] requirements)
      throws UnableToCompleteException {
    for (MayRequire req : requirements) {
      Element mayRequire = (Element) parent.appendChild(d.createElement("MayRequire"));
      writeAnnotationToElement(logger, req, mayRequire, "info");

      String cdata = req.info();
      if (cdata != null && cdata.length() > 0) {
        mayRequire.appendChild(d.createCDATASection(cdata));
      }
    }
  }

  /**
   * Utility class.
   */
  private GadgetUtils() {
  }
}
