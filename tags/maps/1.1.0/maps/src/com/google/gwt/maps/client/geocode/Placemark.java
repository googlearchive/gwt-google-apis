/*
 * Copyright 2007 Google Inc.
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
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.LatLngBounds;

/**
 * This class represents a JSON result returned from the Google Geocoding
 * service. This object contains an address encoded in eXtensible Address
 * Language(xAL) from <a href="http://www.oasis-open.org">OASIS</a>.
 */
public class Placemark extends JavaScriptObject {

  /**
   * This class represents a JSON ExtendedData object being part of result
   * returned from the Google Geocoding service.
   */
  public static class ExtendedData extends JavaScriptObject {

    /**
     * A protected constructor is required for JS overlays.
     */
    protected ExtendedData() {
    }

    /**
     * The rectangle area representing bounds of the placemark.
     * 
     * @return bounds of the placemark.
     */
    public final LatLngBounds getBounds() {
      return LatLngBounds.newInstance(LatLng.newInstance(
          nativeGetLatLngBoxSouth(), nativeGetLatLngBoxWest()),
          LatLng.newInstance(nativeGetLatLngBoxNorth(),
              nativeGetLatLngBoxEast()));
    }

    private native double nativeGetLatLngBoxEast() /*-{
      return this.LatLonBox.east;
    }-*/;

    private native double nativeGetLatLngBoxNorth() /*-{
      return this.LatLonBox.north;
    }-*/;

    private native double nativeGetLatLngBoxSouth() /*-{
      return this.LatLonBox.south;
    }-*/;

    private native double nativeGetLatLngBoxWest() /*-{
      return this.LatLonBox.west;
    }-*/;

  }

  /**
   * A protected constructor is required for JS overlays.
   */
  protected Placemark() {
  }

  /**
   * An attribute indicating how accurately the Google servers were able to
   * geocode the given address.
   * 
   * @return one of the integer values defined in GeoAddressAccuracy
   */
  public final native int getAccuracy() /*-{
    return this.AddressDetails.Accuracy;
  }-*/;

  /**
   * Returns the entire address for this result.
   * 
   * @return a nicely formatted and properly capitalized version of the address
   *         including city, state, postal code and country.
   */
  public final native String getAddress() /*-{
    return this.address;
  }-*/;

  /**
   * The xAL field for "AdministrativeArea", often referred to as 'state'.
   * 
   * @return the name of the administrative area.
   */
  public final native String getAdministrativeArea() /*-{
    var AdministrativeArea = this.AddressDetails.Country.AdministrativeArea;
    return AdministrativeArea && AdministrativeArea.AdministrativeAreaName || null;
  }-*/;

  /**
   * xAL field for "LocalityName" returned by the query.
   * 
   * @return the name of the city for the address.
   */
  public final String getCity() {
    return getLocality();
  }

  /**
   * The xAL field "CountryNameCode" for the country code.
   * 
   * @return a two letter country code for the address.
   */
  public final native String getCountry() /*-{
    return this.AddressDetails.Country.CountryNameCode;
  }-*/;

  /**
   * The xAL field for "SubAdministrativeAreaName".
   * 
   * @return the name of the county
   */
  public final String getCounty() {
    return getSubAdministrativeArea();
  }

  /**
   * Returns class grouping additional information regarding geocoding.
   * 
   * @return class providing additional information.
   */
  public final native ExtendedData getExtendedData() /*-{
    return this.ExtendedData;
  }-*/;

  public final native String getLocality() /*-{
    var AdministrativeArea = this.AddressDetails.Country.AdministrativeArea;
    if (AdministrativeArea) {
      var Locality = (AdministrativeArea.SubAdministrativeArea || AdministrativeArea).Locality;
      return Locality && Locality.LocalityName || null;
    }
    return null;
  }-*/;

  /**
   * Returns the point corresponding to the decoded address.
   * 
   * @return the point corresponding to the decoded address.
   */
  public final LatLng getPoint() {
    return LatLng.newInstance(nativeGetLat(), nativeGetLng());
  }

  /**
   * The xAL field for "PostalCode".
   * 
   * @return the postal code to use with the address.
   */
  public final native String getPostalCode() /*-{
    var AdministrativeArea = this.AddressDetails.Country.AdministrativeArea;
    if (AdministrativeArea) {
      var Area = AdministrativeArea.SubAdministrativeArea || AdministrativeArea;
      var Locality = Area.Locality;
      var DependentLocality = (Locality && Locality.DependentLocality) || null;
      var PostalCode = (DependentLocality && DependentLocality.PostalCode) 
      || (Locality && Locality.PostalCode) || (Area.PostalCode);
      return PostalCode && PostalCode.PostalCodeNumber || null;
    }
    return null;
  }-*/;

  /**
   * The xAL field for "AdministrativeArea".
   * 
   * @return the name of the state.
   */
  public final String getState() {
    return getAdministrativeArea();
  }

  /**
   * The xAL field for "ThoroughfareName".
   * 
   * @return the name of the street.
   */
  public final native String getStreet() /*-{
    var AdministrativeArea = this.AddressDetails.Country.AdministrativeArea;
    if (AdministrativeArea) {
      var Area = AdministrativeArea.SubAdministrativeArea || AdministrativeArea;
      var Locality = Area.Locality;
      var DependentLocality = (Locality && Locality.DependentLocality) || null;
      var Thoroughfare = (DependentLocality && DependentLocality.Thoroughfare)  
        || (Locality && Locality.Thoroughfare) || (Area.Thoroughfare);
      return Thoroughfare && Thoroughfare.ThoroughfareName || null;
    }
    return null;
  }-*/;

  /**
   * The xAL field for "SubAdministrativeAreaName", often called "county".
   * 
   * @return the name of the sub-administrative area
   */
  public final native String getSubAdministrativeArea() /*-{
    var AdministrativeArea = this.AddressDetails.Country.AdministrativeArea;
    if (AdministrativeArea) {
      var SubAdministrativeArea = AdministrativeArea.SubAdministrativeArea;
      return SubAdministrativeArea && SubAdministrativeArea.SubAdministrativeAreaName || null;
    }
    return null;
  }-*/;

  private native double nativeGetLat() /*-{
    return this.Point.coordinates[1];
  }-*/;

  private native double nativeGetLng() /*-{
    return this.Point.coordinates[0];
  }-*/;
}
