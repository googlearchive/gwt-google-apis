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
import com.google.gwt.core.ext.typeinfo.JEnumConstant;
import com.google.gwt.core.ext.typeinfo.JEnumType;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.gadgets.annotations.EnumDisplayValue;
import com.google.gwt.user.rebind.SourceWriter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Used to generate instantiations of the EnumPreferences type.
 */
public class EnumPreferenceGenerator implements PreferenceGenerator {

  /**
   * Given a preference, determine if its <code>getValue()</code> method
   * returns an enumeration and if so, return the specific enumeration type.
   * 
   * @param extendsEnumPref a subtype of EnumPreference
   * @return the type of enumeration returned by the preference
   */
  static JEnumType getEnumType(JClassType extendsEnumPref) {
    while (extendsEnumPref != null) {
      JMethod m = extendsEnumPref.findMethod("getValue", new JType[0]);
      if (m == null) {
        extendsEnumPref = extendsEnumPref.getSuperclass();
        continue;
      }
      return m.getReturnType().isEnum();
    }
    return null;
  }

  /**
   * Add the enumvalue elements to the userpref element.
   */
  public void configurePreferenceElement(TreeLogger logger, Document d,
      Element userPref, JClassType preferenceType, JMethod m)
      throws UnableToCompleteException {
    logger = logger.branch(TreeLogger.DEBUG, "Generating enumvalue elements",
        null);

    JEnumType enumType = getEnumType(preferenceType);
    assert enumType != null;

    for (JEnumConstant constant : enumType.getEnumConstants()) {
      Element enumValue = (Element) userPref.appendChild(d.createElement("enumvalue"));
      enumValue.setAttribute("value", constant.getName());

      EnumDisplayValue displayValue = constant.getAnnotation(EnumDisplayValue.class);
      if (displayValue != null) {
        enumValue.setAttribute("display_value", displayValue.value());
      }
    }
  }

  public void writeInstantiation(TreeLogger logger, SourceWriter sw,
      JClassType extendsPreferenceType, JMethod prefMethod) {
    sw.println("new "
        + extendsPreferenceType.getParameterizedQualifiedSourceName() + "() {");
    sw.indent();

    // public MyEnum getValue() { return MyEnum.valueOf(...); }
    JEnumType enumType = getEnumType(extendsPreferenceType);
    assert enumType != null;
    sw.println("public " + enumType.getQualifiedSourceName() + " getValue() {");
    sw.indent();
    sw.println("return " + enumType.getQualifiedSourceName()
        + ".valueOf(PreferencesUtil.getString(getName()));");
    sw.outdent();
    sw.println("}");

    sw.println("public String getName() {return \"" + prefMethod.getName()
        + "\";}");
    sw.outdent();
    sw.println("}");
  }
}
