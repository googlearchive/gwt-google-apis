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

/**
 * Wrapper for GGoogleBarAdsOptions, passed as an argument to constructing the
 * map in order to show ads from the GoogleBar.
 */
public class GoogleBarAdsOptions extends JavaScriptObject {

  /**
   * Constants to be used to set the AdSafe option.
   */
  public enum AdSafeOption {
    /**
     * Safety is set to 'high' (default) indicating that no adult ads are
     * served.
     */
    ADSAFE_HIGH("high"),

    /**
     * Safety is set to 'low' indicating adult-themed and pornographic ads may
     * be served when the user specifically requests them. 'off' indicates any
     * ads may be served. Any other values are ignored.
     */
    ADSAFE_LOW("low"),

    /**
     * Safety is set to 'medium' indicating that adult-themed ads may be served
     * when the user specifically requests them (for example, ads for alcohol),
     * but ads for pornographic content will not be served.
     */
    ADSAFE_MEDIUM("medium"),

    /**
     * Safety is set to 'off' indicating any ads may be served.
     */
    ADSAFE_OFF("off");

    private final String value;

    private AdSafeOption(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }

  /**
   * Returns a new instance of GoogleBarAdsOptions.
   * 
   * @return a new instance of GoogleBarAdsOptions.
   */
  public static native GoogleBarAdsOptions newInstance() /*-{
     // A complex constructor works around inlining bug. See GWT issue 3568
     // http://code.google.com/p/google-web-toolkit/issues/detail?id=3568
     var obj;
     obj = new $wnd.Object();
     return obj;
   }-*/;

  protected GoogleBarAdsOptions() {
    // Protected constructor required for JSO Overlays
  }

  /**
   * Specifies the ad "safety" level to use for advertising results on your
   * GGoogleBar. Ad Safety levels indicate the level of adult content filtering
   * applied to search results.
   * 
   * @param adsafe one of the MapUIOptions.AdSafeOptions constant values. Any
   *          other values are ignored.
   */
  public final GoogleBarAdsOptions setAdSafe(AdSafeOption adsafe) {
    return setAdSafe(adsafe.getValue());
  }

  /**
   * Specifies the ad "safety" level to use for advertising results on your
   * GGoogleBar. Ad Safety levels indicate the level of adult content filtering
   * applied to search results.
   * 
   * @param adsafe one of the AdSafe constant values ("none", "low", "medium",
   *          "high".) Any other values are ignored.
   */
  public final native GoogleBarAdsOptions setAdSafe(String adsafe) /*-{
     this.adsafe = adsafe;
     return this;
   }-*/;

  /**
   * Specifies the channel number of your Google AdSense for Search account, if
   * you've previously set up such a channel. AdSense channels allow you to
   * track advertising campaigns and determine which sources provide revenue.
   * Note that channels are tied to client IDs, so you must also pass a client
   * parameter if you wish to specify a channel.
   * 
   * @param channel the adsense channel name.
   */
  public final native GoogleBarAdsOptions setChannel(String channel) /*-{
     this.channel = channel;
     return this;
   }-*/;

  /**
   * Specifies the Client ID of your Google AdSense for Search account.
   * Specifying this parameter allows you to gain revenue from the ads displayed
   * in GoogleBar search results.
   * 
   * @param client the client id for your AdSense account.
   */
  public final native GoogleBarAdsOptions setClient(String client) /*-{
     this.client = client;
     return this;
   }-*/;

  /**
   * Specifies the language in which to serve advertising results. Languages are
   * specified in BCP 47 language codes (for example 'en' for English or 'pt-BR'
   * for Brazilian Portugese).
   * 
   * @param language BCP 47 lanugage code specifying the language for which to
   *          serve advertising results.
   */
  public final native GoogleBarAdsOptions setLanguage(String language) /*-{
     this.language = language;
     return this;
   }-*/;
}
