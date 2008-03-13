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
 * 
 */
//TODO(zundel): I am not sure why this class is not using JSFlyweightWrapper
// like the rest of the project.
// TODO(samgross): exposes the right information
// although the names might be off.
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

  private static native String nativeGetAdministrativeArea(JavaScriptObject placemark) /*-{
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

  private static native String nativeGetSubAdministrativeArea(JavaScriptObject placemark) /*-{
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
  
  public int getAccuracy() {
    return nativeGetAccuracy(jsoPeer);
  }
  
  public String getAddress() {
    return nativeGetAddress(jsoPeer);
  }

  public String getCity() {
    return nativeGetLocality(jsoPeer);
  }
  
  public String getCountry() {
    return nativeGetCountry(jsoPeer);
  }

  public String getCounty() {
    return nativeGetSubAdministrativeArea(jsoPeer);
  }
  
  public LatLng getPoint() {
    return new LatLng(nativeGetLat(jsoPeer), nativeGetLng(jsoPeer));
  }

  public String getPostalCode() {
    return nativeGetPostalCode(jsoPeer);
  }

  public String getState() {
    return nativeGetAdministrativeArea(jsoPeer);
  }

  public String getStreet() {
    return nativeGetStreet(jsoPeer);
  }

}
