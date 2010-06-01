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
package com.google.gwt.maps.client.streetview;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

/**
 * A class representing the data associated to a panorama.
 *
 * This class may not be directly instantiated.
 *
 * @see "http://code.google.com/apis/maps/documentation/reference.html#GStreetviewData"
 */
public class StreetviewData extends JavaScriptObject {

  /**
   * Protected constructor required for JS overlay.
   */
  protected StreetviewData() {
  }

  /**
   * Returns a status code.
   *
   * @see "http://code.google.com/apis/maps/documentation/reference.html#GStreetviewClient.ReturnValues"
   *
   * @return a status code.
   */
  public final native int getCode() /*-{
    return this.code;
  }-*/;

  /**
   * Returns a localized copyright attribution.
   *
   * @return a localized copyright attribution.
   */
  public final native String getCopyright() /*-{
    return this.copyright;
  }-*/;

  /**
   * Returns links to neighbouring panoramas, if any are available.
   *
   * @return links to neighbouring panoramas.
   */
  public final native JsArray<StreetviewLink> getLinks() /*-{
    return this.links;
  }-*/;

  /**
   * Returns the location data.
   *
   * @return the location data.
   */
  public final native StreetviewLocation getLocation() /*-{
    return this.location;
  }-*/;
}
