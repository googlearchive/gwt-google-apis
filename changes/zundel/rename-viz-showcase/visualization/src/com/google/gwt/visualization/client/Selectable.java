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
package com.google.gwt.visualization.client;

/**
 * A marker interface that marks visualizations as supporting selection and the
 * select event. It might have been nice to have some characteristic methods in
 * the interface instead of just having the marker, but Visualization extends
 * JavaScriptObject, and subclasses of JavaScriptObjects may not implement
 * interfaces with methods.
 */
public interface Selectable {
  /**
   * The SelectionMethods interface can be implemented to provide the selection
   * methods in Java classes which do not extend JavaScriptObject.
   */
  public interface SelectionMethods extends Selectable {
    Selection getSelection();

    void setSelection(Selection selection);
  }
}
