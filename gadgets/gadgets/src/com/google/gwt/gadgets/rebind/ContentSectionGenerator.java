/*
 * Copyright 2009 Google Inc.
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

import com.google.gwt.core.ext.BadPropertyValueException;
import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.PropertyOracle;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.gadgets.client.Gadget;
import com.google.gwt.gadgets.rebind.GadgetUtils.GadgetViewType;

/**
 * Determines, which sub-class of
 * {@link com.google.gwt.gadgets.client.ContentSection} to instantiate, based on
 * the current gadget view.
 */
public class ContentSectionGenerator extends Generator {
  /**
   * The name of the property that is set to the name of the gadget view.
   */
  private static final String GADGET_VIEW_PROPERTY = "gadget_view";

  /**
   * The name of the property that the developer can set to specify the main
   * {@link Gadget} sub-type for this compilation process.
   */
  private static final String MAINCLASS_PROPERTY = "gadget_mainclass";

  /**
   * Returns the {@link Gadget} sub-type that is the main gadget in this
   * compilation. This type can be given using a property. If this property is
   * not set, this method will return the first {@link Gadget} sub-type it can
   * find, which is a reasonable fall-back for most cases.
   * 
   * @return A class type that extends {@link Gadget} or null, if it couldn't be
   *         found.
   */
  private static JClassType findGadgetClassType(TreeLogger logger,
      TypeOracle typeOracle, PropertyOracle propertyOracle) {
    JClassType[] gadgetSubTypes = typeOracle.findType(Gadget.class.getName()).getSubtypes();

    // If there is no Gadget sub-type, then something is wrong.
    if (gadgetSubTypes.length == 0) {
      return null;
    }

    try {
      // In order to determine the Gadget class that this ContentSection belongs
      // to, we try to see if the developer specified it as a property.
      String gadgetMainClass = propertyOracle.getPropertyValue(logger,
          MAINCLASS_PROPERTY);
      if (gadgetMainClass.length() > 0) {
        // Make sure that the user-provided type is actually a Gadget sub-type.
        JClassType type = typeOracle.findType(gadgetMainClass);
        if (type != null) {
          for (JClassType gadgetSubType : gadgetSubTypes) {
            if (type.equals(gadgetSubType)) {
              return type;
            }
          }
        }
        logger.log(TreeLogger.WARN, "'" + MAINCLASS_PROPERTY
            + "' property found, but '" + gadgetMainClass
            + "' is not a Gadget sub-class.");
      }
    } catch (BadPropertyValueException e) {
      // Just fall-through.
    }
    logger.log(
        TreeLogger.WARN,
        "'"
            + MAINCLASS_PROPERTY
            + "'"
            + " property not set or not valid. Trying to find Gadget sub-type automatically.");

    // As a fallback, check whether there is exactly one Gadget sub-class in
    // this compilation. If so, we choose it.
    if (gadgetSubTypes.length == 1) {
      return gadgetSubTypes[0];
    } else {
      logger.log(TreeLogger.WARN,
          "More than one Gadget subclass has been found. Choosing: "
              + gadgetSubTypes[0].getName());
      return gadgetSubTypes[0];
    }
  }

  @Override
  public String generate(TreeLogger logger, GeneratorContext context,
      String typeName) throws UnableToCompleteException {
    // The TypeOracle knows about all types in the type system
    TypeOracle typeOracle = context.getTypeOracle();
    PropertyOracle propertyOracle = context.getPropertyOracle();
    String[] views = new String[0];
    String viewsProperty;
    try {
      viewsProperty = propertyOracle.getPropertyValue(logger,
          GADGET_VIEW_PROPERTY);
      views = viewsProperty.split(",");
    } catch (BadPropertyValueException e) {
      throw new UnableToCompleteException();
    }
    JClassType gadgetClassType = findGadgetClassType(logger, typeOracle,
        propertyOracle);
    // These are the view types the user specified with the Content annotation
    GadgetViewType[] gadgetViewTypes = GadgetUtils.getViewTypes(logger,
        gadgetClassType, typeOracle);
    // Try to match a view type with the current view.
    for (GadgetViewType gadgetViewType : gadgetViewTypes) {
      String[] gadgetViewNames = gadgetViewType.viewNames;
      for (String view : views) {
        for (String gadgetViewName : gadgetViewNames) {
          if (gadgetViewName.equals(view)) {
            return gadgetViewType.viewType.getQualifiedSourceName();
          }
        }
      }
    }
    return null;
  }
}
