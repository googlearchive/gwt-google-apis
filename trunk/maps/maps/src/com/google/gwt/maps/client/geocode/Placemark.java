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

/**
 * This class represents a JSON result returned from the Google Geocoding
 * service.
 */
public final class Placemark extends JavaScriptObject {

  /**
   *  A protected constructor is required for JS overlays.
   */
  protected Placemark() {
  }
  
  /**
   * An attribute indicating how accurately the Google servers were able to
   * geocode the given address.
   * 
   * @return one of the integer values defined in GeoAddressAccuracy
   */
  public native int getAccuracy() /*-{
     return this.AddressDetails.Accuracy;
   }-*/;

  /**
   * Returns the entire address for this result.
   * 
   * @return a nicely formatted and properly capitalized version of the address
   *         including city, state, postal code and country.
   */
  public native String getAddress() /*-{
     return this.address;
   }-*/;

  /**
   * The xAL field for "AdministrativeArea", often referred to as 'state'.
   * 
   * @return the name of the administrative area.
   */
  public native String getAdministrativeArea() /*-{
     var AdministrativeArea = this.AddressDetails.Country.AdministrativeArea;
     return AdministrativeArea && AdministrativeArea.AdministrativeAreaName || null;
   }-*/;

  /**
   * xAL field for "LocalityName" returned by the query.
   * 
   * @return the name of the city for the address.
   */
  public String getCity() {
    return getLocality();
  }

  /**
   * The xAL field "CountryNameCode" for the country code.
   * 
   * @return a two letter country code for the address.
   */
  public native String getCountry() /*-{
    return this.AddressDetails.Country.CountryNameCode;
  }-*/;

  /**
   * The xAL field for "SubAdministrativeAreaName".
   * 
   * @return the name of the county
   */
  public String getCounty() {
    return getSubAdministrativeArea();
  }

  public native String getLocality() /*-{
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
  public LatLng getPoint() {
    return new LatLng(nativeGetLat(), nativeGetLng());
  }

  /**
   * The xAL field for "PostalCode".
   * 
   * @return the postal code to use with the address.
   */
  public native String getPostalCode() /*-{
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
  public String getState() {
    return getAdministrativeArea();
  }

  /**
   * The xAL field for "ThoroughfareName".
   * 
   * @return the name of the street.
   */
  public native String getStreet() /*-{
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
  public native String getSubAdministrativeArea() /*-{
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
