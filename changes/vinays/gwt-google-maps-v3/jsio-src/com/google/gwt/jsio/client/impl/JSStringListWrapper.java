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
import com.google.gwt.jsio.client.JSList;
import com.google.gwt.jsio.client.JSWrapper;

/**
 * This an implementation of List that operates directly on JS arrays of
 * Strings.
 */
public final class JSStringListWrapper extends AbstractJSListWrapper<String>
    implements JSList<String>, JSWrapper<JSStringListWrapper> {

  private static class StringExtractor {
    static String fromJS(Object o) {
      return (String) o;
    }
  }

  /**
   * Used by JSNI code to construct new JSStringListWrappers.
   */
  public static JSStringListWrapper create() {
    return new JSStringListWrapper();
  }

  private static native String getNative(JavaScriptObject array, int index) /*-{
    return String(array[index]);
  }-*/;

  public JSStringListWrapper() {
    // Extractor is ignored.
    arr = JavaScriptObject.createArray();
  }

  @Override
  public void add(int index, String value) {
    if (index < 0 || index > size()) {
      throw new IndexOutOfBoundsException();
    }
    splice(arr, index, 0, value);
  }

  @Override
  public String get(int index) {
    if (index < 0 || index > size()) {
      throw new IndexOutOfBoundsException();
    }
    return getNative(arr, index);
  }

  @SuppressWarnings("unchecked")
  public Extractor getExtractor() {
    // Unused method, the Extractor class isn't appropriate for Strings.
      return null;
  }

  @Override
  public String remove(int index) {
    if (index < 0 || index > size()) {
      throw new IndexOutOfBoundsException();
    }
    Object toReturn = splice(arr, index, 1);
    if (toReturn instanceof JavaScriptObject) {
      return StringExtractor.fromJS(toReturn);
    } else if (toReturn instanceof String) {
      return (String) toReturn;
    } else {
      // Don't know what to do with any other type.
     assert (false);
    }
    return null;
  }

  @Override
  public String set(int index, String o) {
    if (index < 0 || index > size()) {
      throw new IndexOutOfBoundsException();
    }
    Object toReturn = splice(arr, index, 1, o);
    if (toReturn instanceof JavaScriptObject) {
      return StringExtractor.fromJS(toReturn);
    } else if (toReturn instanceof String) {
      return (String) toReturn;
    } else {
      // Don't know what to do with any other type.
      assert (false);
    }
    return o;
  }

  /**
   * Convenience setter for generated subclasses to be able to initialize
   * newly-created instances without another parsing cycle. This is
   * intentionally not exposed via JSWrapper to prevent the backing JSONObject
   * from changing out from under the wrapper.
   */
  public JSStringListWrapper setJavaScriptObject(JavaScriptObject obj) {
    arr = obj;
    return this;
  }
}
