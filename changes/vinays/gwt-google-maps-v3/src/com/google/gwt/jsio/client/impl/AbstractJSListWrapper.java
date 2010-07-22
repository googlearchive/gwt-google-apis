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

import java.util.AbstractList;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.jsio.client.JSONWrapperException;

/**
 * Common implementation details for {@link JSListWrapper} and
 * {@link JSStringListWrapper}.
 * 
 * @param <T> the type of element to store in the list.
 */
public abstract class AbstractJSListWrapper<T> extends AbstractList<T> {

  /**
   * Used by JSNI code to throw an IndexOutOfBoundsException.
   */
  static void throwIndexOutOfBoundsException() {
    throw new IndexOutOfBoundsException();
  }

  protected static native int sizeNative(JavaScriptObject array) /*-{
    return array.length;
  }-*/;

  protected static native Object splice(JavaScriptObject array, int from,
      int length) /*-{
    return array.splice(from, length);
  }-*/;

  protected static native <T> Object splice(JavaScriptObject array, int index,
      int length, T value) /*-{
  return array.splice(index, length, value);
  }-*/;

  protected JavaScriptObject arr;

  @Override
  public void clear() {
    arr = JavaScriptObject.createArray();
  }

  /**
   * Return the JSONObject that is backing the wrapper. Modifications to the
   * returned JSONObject are not required to be correctly reflected in the
   * source wrapper.
   */
  public JavaScriptObject getJavaScriptObject() {
    return arr;
  }

  /**
   * Unimplemented.
   */
  public void setJSONData(String data) throws JSONWrapperException {
    throw new JSONWrapperException("Unimplemented");
  }

  @Override
  public int size() {
    return sizeNative(arr);
  }

  @Override
  protected void removeRange(int fromIndex, int toIndex) {
    splice(arr, fromIndex, toIndex - fromIndex);
  }

}
