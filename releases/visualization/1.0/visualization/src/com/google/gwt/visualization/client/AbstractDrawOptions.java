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

import com.google.gwt.core.client.JavaScriptObject;

/**
 * DrawOptions stores the options for drawing the visualization.
 * 
 */
public abstract class AbstractDrawOptions extends JavaScriptObject {
  protected AbstractDrawOptions() {
  }

  public final native void setOption(String option, boolean value) /*-{
    this[option] = value;
  }-*/;

  public final native void setOption(String option, double value) /*-{
    this[option] = value;
  }-*/;
  
  public final native void setOption(String option, int value) /*-{
    this[option] = value;
  }-*/;
  
  public final native void setOption(String option, String value) /*-{
    this[option] = value;
  }-*/;
}