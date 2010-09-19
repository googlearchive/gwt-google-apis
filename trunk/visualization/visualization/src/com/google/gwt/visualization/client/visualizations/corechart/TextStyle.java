/*
 * Copyright 2010 Google Inc.
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

package com.google.gwt.visualization.client.visualizations.corechart;

import com.google.gwt.ajaxloader.client.Properties;
import com.google.gwt.core.client.JavaScriptObject;

/**
 * Text style specification for CoreChart Options. Color-related draw options
 * often accept either the name of the color as a string, or the detailed color
 * specification as implemented by the following class.
 *
 * @see <a href=
 *      "http://code.google.com/apis/visualization/documentation/gallery/areachart.html#Configuration_Options"
 *      > Configuration Options Reference</a>
 */
public class TextStyle extends Properties {
  public static TextStyle create() {
    return JavaScriptObject.createObject().cast();
  }

  protected TextStyle() {
  }

  public final native void setColor(String color) /*-{
    this.color = color;
  }-*/;

  public final native void setFontName(String fontName) /*-{
    this.fontName = fontName;
  }-*/;

  public final native void setFontSize(int fontSize) /*-{
    this.fontSize = fontSize;
  }-*/;
}
