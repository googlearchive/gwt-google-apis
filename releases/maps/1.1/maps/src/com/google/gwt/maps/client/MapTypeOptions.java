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
package com.google.gwt.maps.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.impl.MapTypeOptionsImpl;

/**
 * A container object for building a set of options to create a custom
 * {@link MapType}.
 */
public final class MapTypeOptions {

  private final JavaScriptObject jsoPeer;

  public MapTypeOptions() {
    jsoPeer = MapTypeOptionsImpl.impl.construct();
  }

  /**
   * Sets the alternative text to the map type returned by
   * {@link MapType#getAlt()}. Default is the empty string.
   * 
   * @param alt The alternative text to display for the MapType in a User
   *          Interface component
   */
  public void setAlt(String alt) {
    MapTypeOptionsImpl.impl.setAlt(jsoPeer, alt);
  }

  /**
   * Sets the error message returned by {@link MapType#getErrorMessage()} .
   * Default is the empty string.
   * 
   * @param errorMessage message to display
   */
  public void setErrorMessage(String errorMessage) {
    MapTypeOptionsImpl.impl.setErrorMessage(jsoPeer, errorMessage);
  }

  /**
   * Sets the text color returned by {@link MapType#getLinkColor()}. Default is
   * "#7777cc".
   * 
   * @param linkColor CSS specification of the text color to use for links
   */
  public void setLinkColor(String linkColor) {
    MapTypeOptionsImpl.impl.setLinkColor(jsoPeer, linkColor);
  }

  /**
   * Sets the maximum zoom level of this map type, returned by
   * {@link MapType#getMaximumResolution()}. Default is the maximum of all tile
   * layers.
   * 
   * @param maxResolution the maximum zoom level to use for this map type.
   */
  public void setMaxResolution(int maxResolution) {
    MapTypeOptionsImpl.impl.setMaxResolution(jsoPeer, maxResolution);
  }

  /**
   * Sets the minimum zoom level of this map type, returned by
   * {@link MapType#getMinimumResolution()}. Default is the minimum of all tile
   * layers.
   * 
   * @param minResolution the minimum zoom level to use for this map type.
   */
  public void setMinResolution(int minResolution) {
    MapTypeOptionsImpl.impl.setMinResolution(jsoPeer, minResolution);
  }

  /**
   * Sets the radius of the map type given the passed Number measured in meters.
   * The default value is 6378137, the equatorial radius of the Earth in meters.
   * (Since 2.89)
   * 
   * @param radius radius value in meters to use for this map.
   */
  public void setRadius(int radius) {
    MapTypeOptionsImpl.impl.setRadius(jsoPeer, radius);
  }

  /**
   * Sets the short name of the map type that is returned from
   * {@link MapType#getName(boolean)}. Default is the same as the name.
   * 
   * @param shortName the short name to use for this map type.
   */
  public void setShortName(String shortName) {
    MapTypeOptionsImpl.impl.setShortName(jsoPeer, shortName);
  }

  /**
   * Sets the text color returned by {@link MapType#getTextColor()}. Default is
   * "black".
   * 
   * @param textColor CSS specification of text color to use.
   */
  public void setTextColor(String textColor) {
    MapTypeOptionsImpl.impl.setTextColor(jsoPeer, textColor);
  }

  /**
   * Set the tile size returned by {@link MapType#getTileSize()}. Default is
   * 256.
   * 
   * @param tileSize the size of tiles in pixels for this map type.
   */
  public void setTileSize(int tileSize) {
    MapTypeOptionsImpl.impl.setTileSize(jsoPeer, tileSize);
  }

  /**
   * Sets the url argument of the map type that is returned from
   * {@link MapType#getUrlArg()}. Default is the empty string.
   * 
   * @param urlArg url argument to use for this map type.
   */
  public void setUrlArg(String urlArg) {
    MapTypeOptionsImpl.impl.setUrlArg(jsoPeer, urlArg);
  }
}
