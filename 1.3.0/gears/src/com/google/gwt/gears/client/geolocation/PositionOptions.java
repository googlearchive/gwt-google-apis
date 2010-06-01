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
package com.google.gwt.gears.client.geolocation;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.gears.client.impl.Utils;

/**
 * Encapsulates some optional arguments to make a new Geolocation service call.
 */
public final class PositionOptions extends JavaScriptObject {

  public static PositionOptions create() {
    return JavaScriptObject.createObject().cast();
  }

  protected PositionOptions() {
    // required for overlay types
  }

  /**
   * Gets the language in which the address (if requested) should be returned.
   * 
   * @return the language in which the address (if requested) should be returned
   */
  public native PositionOptions getGearsAddressLanguage()/*-{
    return this.gearsAddressLanguage;
  }-*/;

  /**
   * Gets the list of URLs to contact to convert geolocation signals into
   * positions.
   * 
   * @return the list of URLs to contact to convert geolocation signals into
   *         positions
   */
  public String[] getGearsLocationProviderUrls() {
    return Utils.toJavaArray(nativeGetGearsLocationProviderUrls());
  }

  /**
   * Gets whether reverse geocoded address information is returned as part of
   * the position data.
   * 
   * @return <code>true</code> if reverse geocoded address information is returned as part of
   *         the position data.
   */
  public native String getGearsRequestAddress()/*-{
    return this.gearsRequestAddress;
  }-*/;

  /**
   * Gets whether high accuracy is requested.
   * 
   * @return true if high accuracy is requested.
   */
  public native boolean isHighAccuracy()/*-{
    return this.enableHighAccuracy;
  }-*/;

  /**
   * Optional, specifies the language in which the address (if requested) should
   * be returned. Specify the language in accordance with RFC 3066, en-GB for
   * British English for example.
   * 
   * If this is not specified, the address is provided in the default language
   * of the location provider used to perform the reverse geocoding.
   * 
   * @param addressLanguage the language in which the address (if requested)
   *          should be returned
   * @return this instance
   */
  public native PositionOptions setGearsAddressLanguage(String addressLanguage)/*-{
    this.gearsAddressLanguage = addressLanguage;
  }-*/;

  /**
   * Optional, specifies one or more URLs to contact to convert geolocation
   * signals into positions. Note that these must be complete URLs which include
   * the scheme, e.g. http://gears.mylocationprovider.com. If unset, defaults to
   * a single Google-implemented service. The array can also be cleared, or set
   * to null, so that no location providers are used.
   * 
   * @param urls one or more URLs to contact to convert geolocation signals into
   *          positions
   * @return this instance
   */
  public native PositionOptions setGearsLocationProviderUrls(JsArrayString urls)/*-{
    this.gearsLocationProviderUrls = urls;
    return this;
  }-*/;

  /**
   * Optional, specifies one or more URLs to contact to convert geolocation
   * signals into positions. Note that these must be complete URLs which include
   * the scheme, e.g. http://gears.mylocationprovider.com. If unset, defaults to
   * a single Google-implemented service. The array can also be cleared, or set
   * to null, so that no location providers are used.
   * 
   * @param urls one or more URLs to contact to convert geolocation signals into
   *          positions
   * @return this instance
   */
  public PositionOptions setGearsLocationProviderUrls(String[] urls) {
    setGearsLocationProviderUrls(Utils.toJavaScriptArray(urls));
    return this;
  }

  /**
   * Optional, requests reverse geocoded address information as part of the
   * position data. Reverse geocoding is not performed if this flag is not
   * specified or if it is set to false.
   * 
   * @param requestAddress If true, requests reverse geocoded address
   *          information as part of the position data
   * @return this instance
   */
  public native PositionOptions setGearsRequestAddress(boolean requestAddress)/*-{
    this.gearsRequestAddress = requestAddress;
    return this;
  }-*/;

  /**
   * Optional, requests the most accurate possible results. This may result in
   * slower response times or increased battery consumption. Also, there is no
   * guarantee that the device will be able to provide more accurate results
   * than if this flag is not specified. The default value is false.
   * 
   * @param enabled If true, request the most accurate possible results
   * @return this instance
   */
  public native PositionOptions setHighAccuracy(boolean enabled)/*-{
    this.enableHighAccuracy = enabled;
    return this;
  }-*/;

  private native JsArrayString nativeGetGearsLocationProviderUrls()/*-{
    return this.gearsLocationProviderUrls;
  }-*/;
}
