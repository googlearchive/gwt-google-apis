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
package com.google.gwt.jsio.client.impl;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.jsio.client.JSOpaque;
import com.google.gwt.jsio.client.MultipleWrapperException;

/**
 * Internal utility functions to encapsulate often-used idioms.
 */
public class JSONWrapperUtil {
  public static final Extractor<Boolean> BOOLEAN_EXTRACTOR = new Extractor<Boolean>() {
    public native Boolean fromJS(JavaScriptObject obj) /*-{
     return @com.google.gwt.jsio.client.impl.JSONWrapperUtil::createWrapper(Z)(Boolean(obj));
     }-*/;

    public native JavaScriptObject toJS(Boolean o) /*-{
     return new Boolean(o.@java.lang.Boolean::booleanValue()());
     }-*/;
  };

  public static final Extractor<Byte> BYTE_EXTRACTOR = new Extractor<Byte>() {
    public native Byte fromJS(JavaScriptObject obj) /*-{
     return @com.google.gwt.jsio.client.impl.JSONWrapperUtil::createWrapper(B)(Number(obj));
     }-*/;

    public native JavaScriptObject toJS(Byte o) /*-{
     return new Number(o.@java.lang.Byte::byteValue()());
     }-*/;
  };

  public static final Extractor<Character> CHARACTER_EXTRACTOR = new Extractor<Character>() {
    public native Character fromJS(JavaScriptObject obj) /*-{
     return @com.google.gwt.jsio.client.impl.JSONWrapperUtil::createWrapper(C)(Number(obj));
     }-*/;

    public native JavaScriptObject toJS(Character o) /*-{
     return new Number(o.@java.lang.Character::charValue()());
     }-*/;
  };

  public static final Extractor<Double> DOUBLE_EXTRACTOR = new Extractor<Double>() {
    public native Double fromJS(JavaScriptObject obj) /*-{
     return @com.google.gwt.jsio.client.impl.JSONWrapperUtil::createWrapper(D)(Number(obj));
     }-*/;

    public native JavaScriptObject toJS(Double o) /*-{
     return new Number(o.@java.lang.Double::doubleValue()());
     }-*/;
  };

  public static final Extractor<Float> FLOAT_EXTRACTOR = new Extractor<Float>() {
    public native Float fromJS(JavaScriptObject obj) /*-{
     return @com.google.gwt.jsio.client.impl.JSONWrapperUtil::createWrapper(F)(Number(obj));
     }-*/;

    public native JavaScriptObject toJS(Float o) /*-{
     return new Number(o.@java.lang.Float::floatValue()());
     }-*/;
  };

  public static final Extractor<Integer> INTEGER_EXTRACTOR = new Extractor<Integer>() {
    public native Integer fromJS(JavaScriptObject obj) /*-{
     return @com.google.gwt.jsio.client.impl.JSONWrapperUtil::createWrapper(I)(Number(obj));
     }-*/;

    public native JavaScriptObject toJS(Integer o) /*-{
     return new Number(o.@java.lang.Integer::intValue()());
     }-*/;
  };

  /**
   * Essentially a no-op since JavaScriptObjects are transparent to the Java
   * side of the code.
   */
  public static final Extractor<JavaScriptObject> JSO_EXTRACTOR = new Extractor<JavaScriptObject>() {
    public native JavaScriptObject fromJS(JavaScriptObject obj) /*-{
     return obj;
     }-*/;

    public native JavaScriptObject toJS(JavaScriptObject o) /*-{
     return o;
     }-*/;
  };

  public static final Extractor<JSOpaque> JSOPAQUE_EXTRACTOR = new Extractor<JSOpaque>() {
    // XXX Does this make sense?
    public native JSOpaque fromJS(JavaScriptObject obj) /*-{
     return undefined;
     }-*/;

    public native JavaScriptObject toJS(JSOpaque o) /*-{
     return eval(o.@com.google.gwt.jsio.client.JSOpaque::reference);
     }-*/;
  };

  public static final Extractor<Short> SHORT_EXTRACTOR = new Extractor<Short>() {
    public native Short fromJS(JavaScriptObject obj) /*-{
     return @com.google.gwt.jsio.client.impl.JSONWrapperUtil::createWrapper(S)(Number(obj));
     }-*/;

    public native JavaScriptObject toJS(Short o) /*-{
     return new Number(o.@java.lang.Short::shortValue()());
     }-*/;
  };

  public static Boolean createWrapper(boolean b) {
    return Boolean.valueOf(b);
  }

  public static Byte createWrapper(byte b) {
    return new Byte(b);
  }

  public static Character createWrapper(char c) {
    return new Character(c);
  }

  public static Double createWrapper(double c) {
    return new Double(c);
  }

  public static Float createWrapper(float c) {
    return new Float(c);
  }

  public static Integer createWrapper(int c) {
    return new Integer(c);
  }

  public static Short createWrapper(short c) {
    return new Short(c);
  }

  /*
   * This method converts the json string into a JavaScriptObject inside of JSNI
   * method by simply evaluating the string in JavaScript.
   */
  public static native JavaScriptObject evaluate(String jsonString) /*-{
   var x = eval('(' + jsonString + ')');
   if (typeof x == 'number' || typeof x == 'string' || typeof x == 'array' || typeof x == 'boolean') {
   x = (Object(x));
   }
   return x;
   }-*/;

  public static native boolean hasField(JavaScriptObject jso, String fieldName) /*-{
    return fieldName in jso;
  }-*/;

  /**
   * Utility method for JSWrapper to throw an exception.
   */
  public static void throwMultipleWrapperException() {
    throw new MultipleWrapperException();
  }

  private JSONWrapperUtil() {
  }
}
