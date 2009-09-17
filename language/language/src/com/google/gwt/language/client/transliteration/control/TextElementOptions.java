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
package com.google.gwt.language.client.transliteration.control;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * The optional argument supplies the options that are applied for this set of
 * textfields. This can contain the following fields:<br>
 * <ul>
 * <li>adjustTextareaStyle - Optional boolean field. If true, the text field
 * size and font are adjusted optimally to suit Indic and Arabic characters.
 * Default value for this field is true.
 * <li>adjustTextareaDirection - Optional boolean field. If true, the text field
 * direction is adjusted optimally according to the direction of the
 * destinationLanguage and the contents of the text field. Default value for
 * this field is true.
 * </ul>
 */
public class TextElementOptions extends JavaScriptObject {

  /**
   * Creates new instance with given options.
   * 
   * @param adjustTextareaStyle adjusts text field optimally to suit Indic and
   *          Arabic characters.
   * @return TextAreaOptions object
   */
  public static native TextElementOptions newInstance(
      boolean adjustTextareaStyle) /*-{
    var options = new Object();
    options.adjustTextareaStyle = adjustTextareaStyle;
    return options;
  }-*/;

  /**
   * Creates new instance with given options.
   * 
   * @param adjustTextareaStyle adjusts text field optimally to suit Indic and
   *          Arabic characters.
   * @param adjustTextareaDirection adjusts direction optionally for destination
   *          language.
   * @return TextAreaOptions object
   */
  public static native TextElementOptions newInstance(
      boolean adjustTextareaStyle, boolean adjustTextareaDirection) /*-{
    var options = new Object();
    options.adjustTextareaStyle = adjustTextareaStyle;
    options.adjustTextareaDirection = adjustTextareaDirection;
    return options;
  }-*/;

  protected TextElementOptions() {
  }

  /**
   * Sets given option.
   * 
   * @param adjustTextareaDirection adjusts direction optionally for destination
   *          language.
   * @return TextAreaOptions object
   */
  public final native TextElementOptions setTextAreaDirection(
      boolean adjustTextareaDirection) /*-{
    this.adjustTextareaDirection = adjustTextareaDirection;
    return this;
  }-*/;
  
  /**
   * Sets given option.
   * 
   * @param adjustTextareaStyle adjusts text field optimally to suit Indic and
   *          Arabic characters.
   * @return TextAreaOptions object
   */
  public final native TextElementOptions setTextAreaStyle(
      boolean adjustTextareaStyle) /*-{
    this.adjustTextareaStyle = adjustTextareaStyle;
    return this;
  }-*/;
}
