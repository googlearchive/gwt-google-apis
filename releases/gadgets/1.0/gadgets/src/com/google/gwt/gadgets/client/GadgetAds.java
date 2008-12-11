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
package com.google.gwt.gadgets.client;

/**
 * A base class for writing a Google Gadget Ads.
 * 
 * <p>
 * Adds the {@code clickurl} and {@code aiturl} UserPrefs and the {@code ads}
 * feature that are requirements for Gadget Ads.
 * 
 * @param <T> An AdsUserPreferences subclass
 */
public abstract class GadgetAds<T extends AdsUserPreferences> extends Gadget<T>
    implements NeedsAds {

  /**
   * This class contains the list of supported Google AdSense Ad Formats. See:
   * <a href="https://adwords.google.com/select/imagesamples.html">AdSense ad
   * size.</a>
   */
  public static final class SupportedGoogleAdSenseAdFormats {

    /**
     * Banner Adsense ad format height which is 60px.
     */
    public static final int BANNER_HEIGHT = 60;

    /**
     * Banner Adsense ad format width which is 468.
     */
    public static final int BANNER_WIDTH = 468;

    /**
     * Large Rectangle ad format height which is 280px.
     */
    public static final int LARGE_RECTANGLE_HEIGHT = 280;

    /**
     * Large Rectangle ad format width which is 336px.
     */
    public static final int LARGE_RECTANGLE_WIDTH = 336;

    /**
     * Leaderboard Adsense ad format height which is 90px.
     */
    public static final int LEADERBOARD_HEIGHT = 90;

    /**
     * Leaderboard Adsense ad format width which is 728px.
     */
    public static final int LEADERBOARD_WIDTH = 728;

    /**
     * Medium Rectangle ad format height which is 250px.
     */
    public static final int MEDIUM_RECTANGLE_HEIGHT = 250;

    /**
     * Medium Rectangle ad format width which is 300px.
     */
    public static final int MEDIUM_RECTANGLE_WIDTH = 300;

    /**
     * Skyscraper ad format height which is 600px.
     */
    public static final int SKYSCRAPER_HEIGHT = 600;

    /**
     * Skyscraper ad format height width is 120px.
     */
    public static final int SKYSCRAPER_WIDTH = 120;

    /**
     * Small Square Adsense ad format height which is 200px.
     */
    public static final int SMALL_SQUARE_HEIGHT = 200;

    /**
     * Small Square Adsense ad format width which is 200px.
     */
    public static final int SMALL_SQUARE_WIDTH = 200;

    /**
     * Square Adsense ad format height which is 250px.
     */
    public static final int SQUARE_HEIGHT = 250;

    /**
     * Square Adsense ad format width which is 250px.
     */
    public static final int SQUARE_WIDTH = 250;

    /**
     * Wide Skyscraper ad format height which is 600px.
     */
    public static final int WIDE_SKYSCRAPER_HEIGHT = 600;

    /**
     * Wide Skyscraper ad format width which is 160px.
     */
    public static final int WIDE_SKYSCRAPER_WIDTH = 160;
  }

}
