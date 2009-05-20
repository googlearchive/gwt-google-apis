/*
 * Copyright 2009 Google Inc.
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
package com.google.gwt.maps.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.geom.Size;

/**
 * Wrapper for GMapUIOptions.
 * 
 */
public class MapUIOptions extends JavaScriptObject {

  /**
   * Returns a new instance of GMapUIOptions with values set to defaults
   * suitable for a map of the given size. A "small" map is one which is smaller
   * than 400 pixels wide or 300 pixels high. A "large" map is any map 400x300
   * or larger.
   * 
   * @return a new instance of GMapUIOptions.
   */
  public static final native MapUIOptions newInstance(Size optSize) /*-{
    var obj;
    obj = new $wnd.GMapUIOptions(optSize);
    return obj;
  }-*/;

  protected MapUIOptions() {
    // Protected constructor required for JSO Overlays
  }

  /**
   * Returns whether zooming with double-clicks should be enabled.
   * 
   * @return whether zooming with double-clicks should be enabled.
   */
  public final native boolean getDoubleClick() /*-{
    return this.zoom.doubleclick ? true : false;
  }-*/;

  /**
   * Returns whether the satellite MapType (G_HYBRID_MAP) should be added to the
   * map.
   * 
   * @return <code>true</code> if the hybrid MapType should be added to the map.
   */
  public final native boolean getHybridMapType() /*-{
    return this.maptypes.hybrid ? true : false;
  }-*/;

  /**
   * Returns whether the KeyboardHandler should be enabled for the Map.
   * 
   * @return whether the KeyboardHandler should be enabled for the Map.
   */
  public final native boolean getKeyboard() /*-{
    return this.keyboard ? true : false;
  }-*/;

  /**
   * Returns whether the LargeMapControl3D should be added to the Map.
   * 
   * @return whether the LargeMapControl3D should be added to the Map.
   */
  public final native boolean getLargeMapControl3d() /*-{
    return this.controls.largemapcontrol3d ? true : false;
  }-*/;

  /**
   * Returns whether a MapTypeControl should be added to the Map.
   * 
   * @return whether a MapTypeControl should be added to the Map.
   */
  public final native boolean getMapTypeControl() /*-{
    return this.controls.maptypecontrol ? true : false;
  }-*/;

  /**
   * Returns whether a MenuMapTypeControl should be added to the Map.
   * 
   * @return whether a MenuMapTypeControl should be added to the Map.
   */
  public final native boolean getMenuMapTypeControl() /*-{
    return this.controls.menumaptypecontrol ? true : false;
  }-*/;

  /**
   * Returns whether the normal MapType (G_NORMAL_MAP) should be added to the
   * map.
   * 
   * @return <code>true</code> if the Normal MapType should be added to the map.
   */
  public final native boolean getNormalMapType() /*-{
    return this.maptypes.normal ? true : false;
  }-*/;

  /**
   * Returns whether the physical MapType (G_PHYSICAL_MAP) should be added to
   * the map.
   * 
   * @return <code>true</code> if the physical MapType should be added to the
   *         map.
   */
  public final native boolean getPhysicalMapType() /*-{
    return this.maptypes.physical ? true : false;
  }-*/;

  /**
   * Returns whether the satellite MapType (G_SATELLITE_MAP) should be added to
   * the map.
   * 
   * @return <code>true</code> if the satellite MapType should be added to the
   *         map.
   */
  public final native boolean getSatelliteMapType() /*-{
    return this.maptypes.satellite ? true : false;
  }-*/;

  /**
   * Returns whether a ScaleControl should be added to the Map.
   * 
   * @return whether a ScaleControl should be added to the Map.
   */
  public final native boolean getScaleControl() /*-{
    return this.controls.scalecontrol ? true : false;
  }-*/;

  /**
   * Returns whether zooming with the scrollwheel should be enabled.
   * 
   * @return whether zooming with the scrollwheel should be enabled.
   */
  public final native boolean getScrollwheel() /*-{
    return this.zoom.scrollwheel ? true : false;
  }-*/;

  /**
   * Returns whether a SmallZoomControl3D should be added to the Map.
   * 
   * @return whether a SmallZoomControl3D should be added to the Map.
   */
  public final native boolean getSmallZoomControl3d() /*-{
    return this.controls.smallzoomcontrol3d ? true : false;
  }-*/;

  /**
   * When set to <code>true</code>, this method specifies that zooming with
   * double-clicks should be enabled. True by default.
   * 
   * @param value <code>true</code> to specify that zooming with double-clicks
   *          should be enabled.
   */
  public final native MapUIOptions setDoubleClick(boolean value) /*-{
    this.zoom.doubleclick = value;
    return this;
  }-*/;

  /**
   * When set to <code>true</code>, this method specifies that the hybrid
   * MapType (G_HYBRID_MAP) should be added to the Map. True by default.
   * 
   * @param value <code>true</code> to specify the Hybrid MapType.
   */
  public final native MapUIOptions setHybridMapType(boolean value) /*-{
    this.maptypes.hybrid = value;
    return this;
  }-*/;

  /**
   * When set to <code>true</code>, this method specifies that KeyboardHandler
   * should be enabled for the Map. True by default.
   * 
   * @param value <code>true</true> to specify that KeyboardHandler
   *          should be enabled for the Map.
   */
  public final native MapUIOptions setKeyboard(boolean value) /*-{
    this.keyboard = value;
    return this;
  }-*/;

  /**
   * When set to <code>true</code>, this property specifies that a
   * LargeMapControl3D should be added to the Map. True by default for "large"
   * maps.
   * 
   * @param value <code>true</code> to specify that a LargeMapControl3D should
   *          be added to the Map.
   */
  public final native MapUIOptions setLargeMapControl3d(boolean value) /*-{
    this.controls.largemapcontrol3d  = value;
    return this;
  }-*/;

  /**
   * When set to <code>true</code>, this method specifies that a MapTypeControl
   * should be added to the Map. True by default for "large" maps.
   * 
   * @param value <code>true</code> to specify that a MapTypeControl should be
   *          added to the Map.
   */
  public final native MapUIOptions setMapTypeControl(boolean value) /*-{
    this.controls.maptypecontrol = value;
    return this;
  }-*/;

  /**
   * When set to <code>true</code>, this method specifies that a
   * MenuMapTypeControl should be added to the Map. True by default for "small"
   * maps.
   * 
   * @param value <code>true</code> to specify that a MenuMapTypeControl should
   *          be added to the Map.
   */
  public final native MapUIOptions setMenuMapTypeControl(boolean value) /*-{
    this.controls.menumaptypecontrol = value;
    return this;
  }-*/;

  /**
   * When set to <code>true</code>, this method specifies that the normal
   * MapType (G_NORMAL_MAP) should be added to the Map. True by default.
   * 
   * @param value <code>true</code> to specify the normal MapType.
   */
  public final native MapUIOptions setNormalMapType(boolean value) /*-{
    this.maptypes.normal = value;
    return this;
  }-*/;

  /**
   * When set to <code>true</code>, this method specifies that the physical
   * MapType (G_PHYSICAL_MAP) should be added to the Map. True by default.
   * 
   * @param value <code>true</code> to specify the Physical MapType.
   */
  public final native MapUIOptions setPhysicalMapType(boolean value) /*-{
    this.maptypes.physical = value;
    return this;
  }-*/;

  /**
   * When set to <code>true</code>, this method specifies that the satellite
   * MapType (G_SATELLITE_MAP) should be added to the Map. True by default.
   * 
   * @param value <code>true</code> to specify the Satellite MapType.
   */
  public final native MapUIOptions setSatelliteMapType(boolean value) /*-{
    this.maptypes.satellite = value;
    return this;
  }-*/;

  /**
   * When set to <code>true</code>, this method specifies that a ScaleControl
   * should be added to the Map. True by default for "large" maps.
   * 
   * @param value <code>true</code> to specify that a ScaleControl should be
   *          added to the Map.
   */
  public final native MapUIOptions setScaleControl(boolean value) /*-{
    this.controls.scalecontrol = value;
    return this;
  }-*/;

  /**
   * When set to <code>true</code>, this method specifies that zooming with the
   * scrollwheel should be enabled. True by default.
   * 
   * @param value Pass <code>true</code> to enable the scrollwheel zoom.
   */
  public final native MapUIOptions setScrollwheel(boolean value) /*-{
    this.zoom.scrollwheel = value;
    return this;
  }-*/;

  /**
   * When set to <code>true</code>, this method specifies that a
   * SmallZoomControl3D should be added to the Map. True by default for "small"
   * maps.
   * 
   * @param value <code>true</code> to specify that a SmallZoomControl3D should
   *          be added to the Map.
   */
  public final native MapUIOptions setSmallZoomControl3d(boolean value) /*-{
    this.controls.smallzoomcontrol3d  = value;
    return this;
  }-*/;
}