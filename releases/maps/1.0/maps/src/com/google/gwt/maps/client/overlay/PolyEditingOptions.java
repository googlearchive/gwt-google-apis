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

/**
 * Options to pass to the {@link Polyline#setDrawingEnabled()} editing routines.
 */
public class PolyEditingOptions extends JavaScriptObject {

  /**
   * Create a new {@link PolyEditingOptions} object. 
   * @return a new {@link PolyEditingOptions} object.
   */
  public static PolyEditingOptions newInstance() {
    return (PolyEditingOptions) createObject();
  }

  /**
   * Create a {@link PolyEditingOptions} object.
   * 
   * @param fromStart specifies whether {@link Polyline#setDrawingEnabled()}
   *          should add points from the start rather than from the end, which
   *          is the default.
   * @return a new {@link PolyEditingOptions} object.
   */
  public static native PolyEditingOptions newInstance(boolean fromStart) /*-{
    return {"fromStart":fromStart};
  }-*/;

  /**
   * Create a {@link PolyEditingOptions} object.
   * 
   * @param maxVertices specifies the maximum number of vertices permitted for
   *          this polyline. Once this number is reached, no more may be added
   * @return a new {@link PolyEditingOptions} object.
   */
  public static native PolyEditingOptions newInstance(int maxVertices) /*-{
    return {"maxVertices":maxVertices};
  }-*/;
  
  /**
   * Create a {@link PolyEditingOptions} object.
   * 
   * @param fromStart specifies whether {@link Polyline#setDrawingEnabled()}
   *          should add points from the start rather than from the end, which
   *          is the default.
   * @param maxVertices specifies the maximum number of vertices permitted for
   *          this polyline. Once this number is reached, no more may be added
   * @return a new {@link PolyEditingOptions} object.
   */
  public static native PolyEditingOptions newInstance(int maxVertices, boolean fromStart) /*-{
    return {"fromStart":fromStart, "maxVertices":maxVertices};
  }-*/;

  protected PolyEditingOptions() {
    // Protected constructor required for JavaScript overlays.
  }
  /**
   * This property specifies whether enableDrawing should add points from the
   * start rather than from the end, which is the default.
   * 
   * @param fromStart specifies whether {@link Polyline#setDrawingEnabled()}
   *          should add points from the start rather than from the end, which
   *          is the default.
   */
  public final native void setFromStart(boolean fromStart) /*-{
    this.fromStart = fromStart;
  }-*/;

  /**
   * This property specifies the maximum number of vertices permitted for this
   * polyline. Once this number is reached, no more may be added.
   * 
   * @param maxVertices the maximum number of vertices permitted.
   */
  public final native void setMaxVertices(int maxVertices) /*-{
    this.maxVertices = maxVertices;
  }-*/;
}
