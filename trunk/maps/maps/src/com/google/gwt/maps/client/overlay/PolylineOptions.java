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
 * Options to pass to the {@link Polyline} constructor.
 */
public class PolylineOptions extends JavaScriptObject {

  /**
   * Construct a new PolylineOptions instance.
   * 
   * @param clickable pass <code>false</code> to make this polyline not
   *          clickable. 
   * @return a new PolylineOptions instance. 
   */
  public static native PolylineOptions newInstance(boolean clickable) /*-{
    return {"clickable":clickable};
  }-*/;

  /**
   * Construct a new PolylineOptions instance.
   * 
   * @param clickable pass <code>false</code> to make this polyline not
   *          clickable. 
   * @param geodesic <code>true</code> to set this polyline to be geodesic.
   * @return a new PolylineOptions instance. 
   */
  public static native PolylineOptions newInstance(boolean clickable, boolean geodesic) /*-{
    return {"clickable":clickable,"geodesic":geodesic};
  }-*/;

  public static PolylineOptions newInstance() {
    return (PolylineOptions) createObject();
  }

  protected PolylineOptions() {
    // Protected constructor required for JS overlays.
  }

  /**
   * Toggles whether or not the polyline is clickable. The default value for this
   * option is true, i.e. if the option is not specified, the polyline will be
   * clickable.
   * 
   * @param clickable pass <code>false</code> to make this polyline not
   *          clickable.
   */
  public final native void setClickable(boolean clickable) /*-{
    this.clickable = clickable;
  }-*/;

  /**
   * Render each edge of the polyline as a geodesic (a segment of a
   * "great circle"). A geodesic is the shortest path between two points along
   * the surface of the Earth.
   * 
   * @param geodesic <code>true</code> to set this polyline to be geodesic.
   */
  public final native void setGeodesic(boolean geodesic) /*-{
    this.geodesic = geodesic;
  }-*/;
}
