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
package com.google.gwt.maps.client.overlay;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.impl.PolygonOptionsImpl;

/**
 * Options to pass to the {@link Polygon} constructor.
 */
public class PolygonOptions {

  private final JavaScriptObject jsoPeer;
  
  public PolygonOptions() {
    jsoPeer = PolygonOptionsImpl.impl.construct();
  }
  
  /**
   * Toggles whether or not the polygon is clickable. The default value for this
   * option is true, i.e. if the option is not specified, the polygon will be
   * clickable.
   * 
   * @param clickable pass <code>false</code> to make this polygon not clickable.
   */
  public void setClickable(boolean clickable) {
    PolygonOptionsImpl.impl.setClickable(jsoPeer, clickable);
  }
}
