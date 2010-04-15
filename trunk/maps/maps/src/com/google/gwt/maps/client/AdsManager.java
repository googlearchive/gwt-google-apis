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
package com.google.gwt.maps.client;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Wrapper for the GAdsManager class which allows advertisements to be displayed
 * in your map.
 */
public class AdsManager extends JavaScriptObject {

  /**
   * Optional parameters to use when creating an AdsManager.
   */
  public static class AdsManagerOptions extends JavaScriptObject {
    /**
     * Specifies {@link AdsManager} should display content ads in a frame on the
     * map.
     */
    public static final String STYLE_ADUNIT = "adunit";

    /**
     * Create a new {@link AdsManagerOptions} instance.
     */
    public static native AdsManagerOptions newInstance() /*-{
      var result = new $wnd.Object(); 
      return result;
    }-*/;

    protected AdsManagerOptions() {
      // Protected or private constructor required for JavaScriptObject
      // overlays. Use newInstance() to instantiate a new AdsManager object.
    }

    /**
     * The AdSense channel number used for fetching ads. Channels are an
     * optional feature that AdSense publishers can use to track ad revenue from
     * multiple sources.
     * 
     * @param channelIn The AdSense channel number.
     * @return this AdsManagerOption object, for convenience when using the
     *         Builder pattern.
     */
    public final native AdsManagerOptions setChannel(double channelIn) /*-{
      this.channel = channelIn;
      return this;
    }-*/;

    /**
     * The maximum number of ads to show on the map at any time. The default
     * value is 3.
     * 
     * @param maxAdsOnMap The maximum number of ads to show on the map.
     * @return this AdsManagerOption object, for convenience when using the
     *         Builder pattern.
     */
    public final native AdsManagerOptions setMaxAdsOnMap(int maxAdsOnMap) /*-{
      this.maxAdsOnMap = maxAdsOnMap;
      return this;
    }-*/;

    /**
     * The minimum zoom level at which to show ads. The default value is 6.
     * 
     * @param minZoomLevel The minimum zoom level at which to show ads.
     * @return this AdsManagerOption object, for convenience when using the
     *         Builder pattern.
     */
    public final native AdsManagerOptions setMinZoomLevel(int minZoomLevel) /*-{
      this.minZoomLevel = minZoomLevel;
      return this;
    }-*/;

    /**
     * The AdSense for Maps style to exhibit for the placement of maps.
     * 
     * @param style The AdSense for Maps style to exhibit for the placement of
     *          maps.
     * @return AdsManagerOption object, for convenience when using the Builder
     *         pattern.
     */
    public final native AdsManagerOptions setStyle(String style) /*-{
      this.style = style;
      return this;
    }-*/;

    /**
     * Accessor intended for unit testing only.
     * 
     * @return the channel number set in {@link #setChannel(double)}
     */
    final native double getChannel() /*-{
      return this.channel;
    }-*/;

    /**
     * Accessor intended for unit testing only.
     * 
     * @return the maxAdsOnMap value set in {@link #setMaxAdsOnMap(int)}.
     */
    final native int getMaxAdsOnMap() /*-{
      return this.maxAdsOnMap;
    }-*/;

    /**
     * Accessor intended for unit testing only.
     * 
     * @return the minZoomLevel set in {@link #setMinZoomLevel(int)}.
     */
    final native int getMinZoomLevel() /*-{
      return this.minZoomLevel;
    }-*/;

    /**
     * Accessor intended for unit testing only.
     * 
     * @return the style set in {@link #setStyle(String)}
     */
    final native String getStyle() /*-{
      return this.style;
    }-*/;
  }

  /**
   * Create a new AdsManager instance.
   * 
   * @param map The map to associate the AdsManager with.
   * @param publisherId An AdSense publisher Id (e.g. "pub-1234567890123456".)
   * 
   * @return A new AdsManager object.
   */
  public static native AdsManager newInstance(MapWidget map, String publisherId) /*-{
    return new $wnd.GAdsManager(
        map.@com.google.gwt.maps.client.MapWidget::getPeer()(), 
        publisherId);
  }-*/;

  /**
   * Create a new AdsManager instance.
   * 
   * @param map The map to associate the AdsManager with.
   * @param publisherId An AdSense publisher Id (e.g. "pub-1234567890123456".)
   * @param options Optional arguments to initialize the AdsManager.
   * @return A new AdsManager object.
   */
  public static native AdsManager newInstance(MapWidget map,
      String publisherId, AdsManagerOptions options) /*-{
    return new $wnd.GAdsManager(
        map.@com.google.gwt.maps.client.MapWidget::getPeer()(), 
        publisherId, options);
  }-*/;

  protected AdsManager() {
    // Protected or private constructor required for JavaScriptObject overlays.
    // Use newInstance() to instantiate a new AdsManager object.
  }

  /**
   * Enable or disable fetching of ads. Ads are not enabled by default.
   * 
   * @param enabledValue Pass <code>true</code> to enable fetching of ads.
   */
  public final native void setEnabled(boolean enabledValue) /*-{
    if (enabledValue) { 
      this.enable(); 
    } else { 
      this.disable(); 
    };
  }-*/;
}
