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
 * This an implementation of List that operates directly on JS arrays of objects
 * that can be made into JavaScriptObjects.
 * 
 * @param <T> the type of object stored in the list
 */
public final class JSListWrapper<T> extends AbstractJSListWrapper<T> implements
    JSList<T>, JSWrapper<JSListWrapper<T>> {

  /**
   * This is used with nested JSLists.
   */
  private static class WrappingExtractor<T> implements
      Extractor<JSListWrapper<T>> {
    // package protected to avoid style warning, only read from JSNI
    @SuppressWarnings("unused")
    private final Extractor<T> subExtractor;

    public WrappingExtractor(Extractor<T> subExtractor) {
      this.subExtractor = subExtractor;
    }

    public native JSListWrapper<T> fromJS(JavaScriptObject obj) /*-{
      var toReturn = @com.google.gwt.jsio.client.impl.JSListWrapper::create(Lcom/google/gwt/jsio/client/impl/Extractor;)(
      this.@com.google.gwt.jsio.client.impl.JSListWrapper.WrappingExtractor::subExtractor);
      toReturn.@com.google.gwt.jsio.client.JSWrapper::setJavaScriptObject(Lcom/google/gwt/core/client/JavaScriptObject;)(obj);
      return toReturn;
    }-*/;

    public native JavaScriptObject toJS(JSListWrapper<T> o) /*-{
      return this.@com.google.gwt.jsio.client.JSWrapper::getJavaScriptObject()();
    }-*/;
  }

  /**
   * Used by JSNI code to construct new JSListWrappers.
   */
  public static <T> JSListWrapper<T> create(Extractor<T> e) {
    return new JSListWrapper<T>(e);
  }

  public static <T> WrappingExtractor<T> createExtractor(final Extractor<T> e) {
    return new WrappingExtractor<T>(e);
  }

  private static native Object getNative(JavaScriptObject array, int index) /*-{
    return Object(array[index]);
  }-*/;

  private final Extractor<T> extractor;

  public JSListWrapper(Extractor<T> extractor) {
    this.extractor = extractor;
    arr = JavaScriptObject.createArray();
  }

  @Override
  public void add(int index, T object) {
    if (index < 0 || index > size()) {
      throw new IndexOutOfBoundsException();
    }
    splice(arr, index, 0, extractor.toJS(object));
  }

  @Override
  public T get(int index) {
    if (index < 0 || index > size()) {
      throw new IndexOutOfBoundsException();
    }

    Object toReturn = getNative(arr, index);
    if (toReturn instanceof JavaScriptObject) {
      return extractor.fromJS((JavaScriptObject) toReturn);
    } else {
      return (T) toReturn;
    }
  }

  public WrappingExtractor<T> getExtractor() {
    return new WrappingExtractor<T>(extractor);
  }

  @Override
  public T remove(int index) {
    if (index < 0 || index > size()) {
      throw new IndexOutOfBoundsException();
    }

    Object toReturn = splice(arr, index, 1);
    if (toReturn instanceof JavaScriptObject) {
      return extractor.fromJS((JavaScriptObject) toReturn);
    } else {
      return (T) toReturn;
    }
  }

  @Override
  public T set(int index, T o) {
    if (index < 0 || index > size()) {
      throw new IndexOutOfBoundsException();
    }

    Object toReturn = splice(arr, index, 1, o);
    if (toReturn instanceof JavaScriptObject) {
      return extractor.fromJS((JavaScriptObject) toReturn);
    } else {
      return (T) toReturn;
    }
  }

  /**
   * Convenience setter for generated subclasses to be able to initialize
   * newly-created instances without another parsing cycle. This is
   * intentionally not exposed via JSWrapper to prevent the backing JSONObject
   * from changing out from under the wrapper.
   */
  public JSListWrapper<T> setJavaScriptObject(JavaScriptObject obj) {
    arr = obj;
    return this;
  }

}
