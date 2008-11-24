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

/**
 * An object representing an address returned from the Geolocation service.
 *
 */
public final class Address extends JavaScriptObject {

  protected Address() {
    // required for overlay types
  }

  /**
   * Gets the city name.
   * 
   * @return the city name
   */
  public native String getCity() /*-{
    return this.city;
  }-*/;

  /**
   * Gets the country.
   * 
   * @return the country
   */
  public native String getCountry() /*-{
    return this.country;
  }-*/;

  /**
   * Gets the country code (ISO 3166-1).
   * 
   * @return the country code (ISO 3166-1).
   */
  public native String getCountryCode() /*-{
    return this.countryCode;
  }-*/;

  /**
   * Gets the county name.
   * 
   * @return the county name.
   */
  public native String getCounty() /*-{
    return this.county;
  }-*/;

  /**
   * Gets the Postal code. This is the zip code in the US and postcode in the
   * UK.
   * 
   * @return the Postal code. This is the zip code in the US and postcode in the
   *         UK.
   */
  public native String getPostalCode() /*-{
    return this.postalCode;
  }-*/;

  /**
   * Gets the premises, e.g. building name.
   * 
   * @return the premises, e.g. building name.
   */
  public native String getPremises() /*-{
    return this.premises;
  }-*/;

  /**
   * Gets the region, e.g. a state in the US.
   * 
   * @return the region, e.g. a state in the US.
   */
  public native String getRegion() /*-{
    return this.region;
  }-*/;

  /**
   * Gets the street name.
   * 
   * @return the street name
   */
  public native String getStreet() /*-{
    return this.street;
  }-*/;

  /**
   * Gets the building's street number.
   * 
   * @return the building's street number.
   */
  public native String getStreetNumber() /*-{
    return this.streetNumber;
  }-*/;
}
