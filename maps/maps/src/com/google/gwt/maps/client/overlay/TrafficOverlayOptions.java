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
package com.google.gwt.maps.client.overlay;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Used as an argument to the constructor for {@link TrafficOverlay}.
 */
public class TrafficOverlayOptions extends JavaScriptObject {

  /**
   * Construct a new TrafficOverlayOptions instance.
   * 
   * @param incidents pass <code>true</code> to make this traffic overlay show
   *          incidents.
   * @return a new TrafficOverlayOptions instance.
   */
  public static native TrafficOverlayOptions newInstance(boolean incidents) /*-{
    return {"incidents":incidents};
  }-*/;

  /**
   * Returns a new instance of the TrafficOverlayOptions object.
   * 
   * @return a new instance of the TrafficOverlayOptions object.
   */
  public static TrafficOverlayOptions newInstance() {
    return (TrafficOverlayOptions) createObject();
  }

  protected TrafficOverlayOptions() {
    // protected constructor required for JavaScriptObject overlays.
  }

  /**
   * When set to <code>true</code>, indicates that the TrafficOverlay should
   * display traffic incidents on the map, where applicable.
   * 
   * @param incidents <code>true</code> to make this traffic overlay show
   *          incidents.
   */
  public final native void setIncidents(boolean incidents) /*-{
    this.incidents = incidents;
  }-*/;
}
