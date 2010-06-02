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
package com.google.gwt.jsio.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.jsio.client.impl.Extractor;

/**
 * Automatically generates Java/JavaScript interface linkages.
 * 
 * @param <T> A self-referential generic type
 * @see <a href="package-summary.html#package_description">JSIO reference</a>
 */
public interface JSWrapper<T extends JSWrapper<T>> {

  /**
   * Used by JSList.
   */
  Extractor<T> getExtractor();

  /**
   * Return the JavaScriptObject that is backing the wrapper.
   */
  JavaScriptObject getJavaScriptObject();

  /**
   * Set the JavaScriptObject to be wrapped by the generated class.
   * 
   * @return the instance of the JSWrapper.
   * @throws MultipleWrapperException if <code>obj</code> is already the
   *           target of another JSWrapper.
   */
  // The JSWrapper instance is returned because it allows wrapper creation
  // in the generated classes to be a one-liner. See JSWrapperFragmentGenerator.
  T setJavaScriptObject(JavaScriptObject obj) throws MultipleWrapperException;

  /**
   * Convenience setter for wrapping JSON data. The data will be parsed and
   * wrapped by the instance of the JSWrapper
   */
  void setJSONData(String data) throws JSONWrapperException;
}
