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

package com.google.gwt.gears.client.impl;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayInteger;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.gears.client.blob.Blob;

/**
 * A utility class for moving arrays between Java and JavaScript.
 */
public class Utils {
  /**
   * Converts a JavaScript array of strings to a Java array of strings.
   */
  public static String[] toJavaArray(JsArrayString jsArray) {
    String[] urls = new String[jsArray.length()];
    for (int i = 0, l = jsArray.length(); i < l; i++) {
        urls[i] = jsArray.get(i);
    }
    return urls;
  }
  
  /**
   * Converts a JavaScript array of numbers to a Java array of bytes.
   */
  public static byte[] toJavaArray(JsArrayInteger jsArray) {
    byte[] urls = new byte[jsArray.length()];
    for (int i = 0, l = jsArray.length(); i < l; i++) {
        urls[i] = (byte) jsArray.get(i);
    }
    return urls;
  }
  
  /**
   * Converts a Java array of bytes to a JavaScript array of numbers.
   */
  public static JsArrayInteger toJavaScriptArray(byte[] elements) {
    JsArrayInteger array = JavaScriptObject.createArray().cast();
    for (int i = 0, l = elements.length; i < l; ++i) {
      array.set(i, elements[i]);
    }
    return array;
  }

  /**
   * Converts a Java array of Blobs to a JavaScript array of Blobs.
   */
  public static JsArray<Blob> toJavaScriptArray(Blob[] elements) {
    JsArray<Blob> array = JavaScriptObject.createArray().cast();
    for (int i = 0, l = elements.length; i < l; ++i) {
      array.set(i, elements[i]);
    }
    return array;
  }

  /**
   * Converts a Java array of strings to a JavaScript array of strings.
   */
  public static JsArrayString toJavaScriptArray(String[] elements) {
    JsArrayString array = JavaScriptObject.createArray().cast();
    for (int i = 0, l = elements.length; i < l; ++i) {
      array.set(i, elements[i]);
    }
    return array;
  }
}
