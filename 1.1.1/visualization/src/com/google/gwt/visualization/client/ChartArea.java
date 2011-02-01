/*
 * Copyright 2011 Google Inc.
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
 * Specification for chartArea property when setting options.
 */
public class ChartArea extends Properties {
  public static ChartArea create() {
    return JavaScriptObject.createObject().cast();
  }

  protected ChartArea() {
  }

  public final native void setHeight(String height) /*-{
    this.height = height;
  }-*/;

  public final native void setHeight(double height) /*-{
    this.height = height;
  }-*/;

  public final native void setLeft(double left) /*-{
    this.left = left;
  }-*/;

  public final native void setLeft(String left) /*-{
    this.left = left;
  }-*/;

  public final native void setTop(double top) /*-{
    this.top = top;
  }-*/;

  public final native void setTop(String top) /*-{
    this.top = top;
  }-*/;

  public final native void setWidth(double width) /*-{
    this.width = width;
  }-*/;

  public final native void setWidth(String width) /*-{
    this.width = width;
  }-*/;
}