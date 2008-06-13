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
import com.google.gwt.maps.client.impl.PolyEditingOptionsImpl;

/**
 * Options to pass to the {@link Polyline} editing routines.
 */
public class PolyEditingOptions {

  private final JavaScriptObject jsoPeer;

  public PolyEditingOptions() {
    jsoPeer = PolyEditingOptionsImpl.impl.construct();
  }

  public PolyEditingOptions(boolean fromStart) {
    this();
    setFromStart(fromStart);
  }

  public PolyEditingOptions(int maxVerticies) {
    this();
    setMaxVertices(maxVerticies);
  }

  public PolyEditingOptions(int maxVerticies, boolean fromStart) {
    this();
    setMaxVertices(maxVerticies);
    setFromStart(fromStart);
  }

  /**
   * This property specifies whether enableDrawing should add points from the
   * start rather than from the end, which is the default. 
   * 
   * @param fromStart
   */
  public void setFromStart(boolean fromStart) {
    PolyEditingOptionsImpl.impl.setFromStart(jsoPeer, fromStart);
  }

  /**
   * This property specifies the maximum number of vertices permitted for this
   * polyline. Once this number is reached, no more may be added. 
   * 
   * @param maxVertices the maximum number of vertices permitted.
   */
  public void setMaxVertices(int maxVertices) {
    PolyEditingOptionsImpl.impl.setMaxVertices(jsoPeer, maxVertices);
  }
}
