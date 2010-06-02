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

/**
 * This is an interface for use with JSWrapper to export a Java function into a
 * JavaScript context. If the JSFunction contains multiple functions, the method
 * to export must by specified with an {@link Exported} annotation on the
 * method. Anonymous classes are supported.
 * <p>
 * The underlying JavaScript function object will have an identity corresponding
 * to that of the JSFunction object, which makes it suitable for use with a
 * register/unregister style of JavaScript API.
 * </p>
 */
public abstract class JSFunction {
  /**
   * This stores a reference to the created JavaScript function so that the
   * lifetime of the function is at least equal to that of the enclosing object.
   * This object is constructed the first time the JSFunction is used.
   */
  @SuppressWarnings("unused")
  private JavaScriptObject exportedFunction;
}
