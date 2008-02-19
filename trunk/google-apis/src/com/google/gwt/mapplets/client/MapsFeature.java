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
package com.google.gwt.mapplets.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.gadgets.client.GadgetFeature;

/**
 * 
 */
public class MapsFeature implements GadgetFeature {
  @SuppressWarnings("unused")
  private final JavaScriptObject map;

  private MapsFeature() {
    map = createMap();
  }

  private native JavaScriptObject createMap() /*-{
    return new $wnd.GMap2();
  }-*/;

  public native void zoomIn() /*-{
    this.@com.google.gwt.mapplets.client.MapsFeature::map.zoomIn();
  }-*/;

  public native void zoomOut() /*-{
    this.@com.google.gwt.mapplets.client.MapsFeature::map.zoomOut();
  }-*/;
}