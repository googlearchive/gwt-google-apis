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
import com.google.gwt.jsio.client.impl.Extractor;
import com.google.gwt.maps.client.geom.LatLng;

/**
 * This class represents a JSON result returned from the Google Geocoding
 * service.
 */
// TODO(samgross): exposes the right information although the names might be
// off.
// TODO(zundel): This class is a candidate for GWT 1.5 native JSO support.
public final class Placemark {

  // TODO: DELETE ME! (needs to function w/o)
  @SuppressWarnings("unused")
  private static final Extractor<Placemark> __extractor = new Extractor<Placemark>() {
    public Placemark fromJS(JavaScriptObject jso) {
      return createPeer(jso);
    }

    public JavaScriptObject toJS(Placemark o) {
      return o.jsoPeer;
    }
  };

  static Placemark createPeer(JavaScriptObject jsoPeer) {
    return new Placemark(jsoPeer);
  }

  private static native int nativeGetAccuracy(JavaScriptObject placemark) /*-{
    return placemark.AddressDetails.Accuracy;
  }-*/;

  private static native String nativeGetAddress(JavaScriptObject placemark) /*-{
    return placemark.address || null;
  }-*/;

  private static native String nativeGetAdministrativeArea(
      JavaScriptObject placemark) /*-{
    var AdministrativeArea = placemark.AddressDetails.Country.AdministrativeArea;
    return AdministrativeArea && AdministrativeArea.AdministrativeAreaName || null;
  }-*/;

  private static native String nativeGetCountry(JavaScriptObject placemark) /*-{
    return placemark.AddressDetails.Country.CountryNameCode || null;
  }-*/;

  private static native double nativeGetLat(JavaScriptObject placemark) /*-{
    return placemark.Point.coordinates[1];
  }-*/;

  private static native double nativeGetLng(JavaScriptObject placemark) /*-{
    return placemark.Point.coordinates[0];
  }-*/;

  private static native String nativeGetLocality(JavaScriptObject placemark) /*-{
    var AdministrativeArea = placemark.AddressDetails.Country.AdministrativeArea;
    if (AdministrativeArea) {
      var Locality = (AdministrativeArea.SubAdministrativeArea || AdministrativeArea).Locality;
      return Locality && Locality.LocalityName || null;
    }
    return null;
  }-*/;

  private static native String nativeGetPostalCode(JavaScriptObject placemark) /*-{
    var AdministrativeArea = placemark.AddressDetails.Country.AdministrativeArea;
    if (AdministrativeArea) {
      var Area = AdministrativeArea.SubAdministrativeArea || AdministrativeArea;
      var Locality = Area.Locality;
      var PostalCode = (Locality && Locality.PostalCode) || (Area.PostalCode);
      return PostalCode && PostalCode.PostalCodeNumber || null;
    }
    return null;
  }-*/;

  private static native String nativeGetStreet(JavaScriptObject placemark) /*-{
    var AdministrativeArea = placemark.AddressDetails.Country.AdministrativeArea;
    if (AdministrativeArea) {
      var Area = AdministrativeArea.SubAdministrativeArea || AdministrativeArea;
      var Locality = Area.Locality;
      var Thoroughfare = (Locality && Locality.Thoroughfare) || (Area.Thoroughfare);
      return Thoroughfare && Thoroughfare.ThoroughfareName || null;
    }
    return null;
  }-*/;

  private static native String nativeGetSubAdministrativeArea(
      JavaScriptObject placemark) /*-{
    var AdministrativeArea = placemark.AddressDetails.Country.AdministrativeArea;
    if (AdministrativeArea) {
      var SubAdministrativeArea = AdministrativeArea.SubAdministrativeArea;
      return SubAdministrativeArea && SubAdministrativeArea.SubAdministrativeAreaName || null;
    }
    return null;
  }-*/;

  private final JavaScriptObject jsoPeer;

  private Placemark(JavaScriptObject jsoPeer) {
    this.jsoPeer = jsoPeer;
  }

  /**
   * An attribute indicating how accurately the Google servers were able to
   * geocode the given address.
   * 
   * @return one of the integer values defined in GeoAddressAccuracy
   */
  public int getAccuracy() {
    return nativeGetAccuracy(jsoPeer);
  }

  /**
   * Returns the entire address for this result.
   * 
   * @return a nicely formatted and properly capitalized version of the address
   *         including city, state, postal code and country.
   */
  public String getAddress() {
    return nativeGetAddress(jsoPeer);
  }

  /**
   * xAL field for "LocalityName" returned by the query.
   * 
   * @return the name of the city for the address.
   */
  public String getCity() {
    return nativeGetLocality(jsoPeer);
  }

  /**
   * The xAL field "CountryNameCode" for the country code.
   * 
   * @return a two letter country code for the address.
   */
  public String getCountry() {
    return nativeGetCountry(jsoPeer);
  }

  /**
   * The xAL field for "SubAdministrativeAreaName".
   * 
   * @return the name of the county
   */
  public String getCounty() {
    return nativeGetSubAdministrativeArea(jsoPeer);
  }

  /**
   * Returns the point corresponding to the decoded address.
   * 
   * @return the point corresponding to the decoded address.
   */
  public LatLng getPoint() {
    return new LatLng(nativeGetLat(jsoPeer), nativeGetLng(jsoPeer));
  }

  /**
   * The xAL field for "PostalCode".
   * 
   * @return the postal code to use with the address.
   */
  public String getPostalCode() {
    return nativeGetPostalCode(jsoPeer);
  }

  /**
   * The xAL field for "AdministrativeArea".
   * 
   * @return the name of the state.
   */
  public String getState() {
    return nativeGetAdministrativeArea(jsoPeer);
  }

  /**
   * The xAL field for "ThoroughfareName".
   * 
   * @return the name of the street.
   */
  public String getStreet() {
    return nativeGetStreet(jsoPeer);
  }
}
