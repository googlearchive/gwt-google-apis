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

import com.google.gwt.ajaxloader.client.Properties;
import com.google.gwt.core.client.JavaScriptObject;

/**
 * Color specification for DrawOptions. Color-related draw options often accept
 * either the name of the color as a string, or the detailed color specification
 * as implemented by the following class.
 */
public class Color extends Properties {
  public static Color create() {
    return JavaScriptObject.createObject().cast();
  }

  public static Color create(String fill, String stroke, int strokeSize) {
    Color result = create();
    result.setFill(fill);
    result.setStroke(stroke);
    result.setStrokeSize(strokeSize);
    return result;
  }

  protected Color() {
  }

  public final native void setFill(String fill) /*-{
    this.fill = fill;
  }-*/;

  public final native void setStroke(String stroke) /*-{
    this.stroke = stroke;
  }-*/;

  public final native void setStrokeSize(int strokeSize) /*-{
    this.strokeSize = strokeSize;
  }-*/;
}