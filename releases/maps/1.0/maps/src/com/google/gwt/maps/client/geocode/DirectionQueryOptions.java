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
package com.google.gwt.maps.client.geocode;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.impl.DirectionQueryOptionsImpl;
import com.google.gwt.user.client.ui.Widget;

/**
 * A class that stores parameters used to create a new instance of the
 * Directions object.
 */
public final class DirectionQueryOptions {

  final MapWidget map;

  final Widget panel;

  private final JavaScriptObject jsoPeer;

  /**
   * Constant values used for setting the mode of travel for retrieving
   * directions.
   */
  public static enum TravelMode {
    WALKING(), DRIVING();

    private final int value;

    private TravelMode() {
      value = getConstant(this.name());
    }

    private native int getConstant(String name) /*-{
      return $wnd['G_TRAVEL_MODE_' + name];
    }-*/;

    public int value() {
      return value;
    }
  }

  /**
   * Create an options object with default parameters.
   */
  public DirectionQueryOptions() {
    this(null, null);
  }

  /**
   * Create an options object with default parameters associated with a
   * particular map.
   * 
   * @param map the map to draw directions on.
   */
  public DirectionQueryOptions(MapWidget map) {
    this(map, null);
  }

  /**
   * Create an options object with default parameters associated with a
   * particular map and a panel to put the textual directions in.
   * 
   * @param map
   * @param panel
   */
  public DirectionQueryOptions(MapWidget map, DirectionsPanel panel) {
    this.map = map;
    this.panel = panel;
    this.jsoPeer = DirectionQueryOptionsImpl.impl.construct();
  }

  /**
   * If <code>true</code> directions will attempt to exclude highways when
   * computing directions. Note that directions may still include highways if
   * there are no viable alternatives.
   * 
   */
  public void setAvoidHighways(boolean avoid) {
    DirectionQueryOptionsImpl.impl.setAvoidHighways(jsoPeer, avoid);
  }

  /**
   * The locale to use for the directions result. For example, "en_US", "fr",
   * "fr_CA", etc.
   * 
   * @param locale the locale to use for directions results.
   */
  public void setLocale(String locale) {
    DirectionQueryOptionsImpl.impl.setLocale(jsoPeer, locale);
  }

  /**
   * By default, when a Directions object has a map, the map is centered and
   * zoomed to the bounding box of the the directions result. When this option
   * is set to <code>true</code>, the viewport is left alone for this request.
   * 
   * @param preserveViewport pass <code>true</code> to leave alone the viewport
   *          (do not center).
   */
  public void setPreserveViewport(boolean preserveViewport) {
    DirectionQueryOptionsImpl.impl.setPreserveViewport(jsoPeer,
        preserveViewport);
  }

  /**
   * By default, the Directions.load*() methods fetch polyline data only if a
   * map is attached to the Directions object. This field can be used to
   * override this behavior and retrieve polyline data even when a map is not
   * attached to the Directions object.
   * 
   * @param retrievePolyline pass <code>true</code> to retrieve polyline data
   *          even when a map is not attached to the Directions object.
   */
  public void setRetrievePolyline(boolean retrievePolyline) {
    DirectionQueryOptionsImpl.impl.setRetrievePolyline(jsoPeer,
        retrievePolyline);
  }

  /**
   * By default, the Directions.load*() methods fetch steps data only if a panel
   * is attached to the Directions object. This field can be used to override
   * this behavior and retrieve steps data even when a panel is not attached to
   * the Directions object.
   * 
   * @param retrieveSteps pass <code>true</code> to retrieve steps data even
   *          when a panel is not attached to the Directions object.
   */
  public void setRetrieveSteps(boolean retrieveSteps) {
    DirectionQueryOptionsImpl.impl.setRetrieveSteps(jsoPeer, retrieveSteps);
  }

  /**
   * The mode of travel, such as driving (default) or walking. Note that if you
   * specify walking directions, you will need to specify a &lt;div&gt; panel to
   * hold a warning notice to users.
   * 
   * @param mode the mode of travel.
   */
  public void setTravelMode(TravelMode mode) {
    DirectionQueryOptionsImpl.impl.setTravelMode(jsoPeer, mode.value());
  }
}