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
package com.google.gwt.maps.client.impl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.jsio.client.JSFlyweightWrapper;
import com.google.gwt.jsio.client.JSFunction;
import com.google.gwt.jsio.client.JSList;
import com.google.gwt.jsio.client.JSWrapper;
import com.google.gwt.maps.client.geocode.GeocodeCache;
import com.google.gwt.maps.client.geocode.Placemark;
import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.jsio.client.BeanProperties;
import com.google.gwt.jsio.client.Constructor;
import com.google.gwt.jsio.client.FieldName;
import com.google.gwt.maps.client.impl.EventImpl.LatLngCallback;

/**
 * 
 */
public interface GeocoderImpl extends JSFlyweightWrapper {

  // TODO: this is only used in GeocoderImpl. Where should it be placed?
  /**
   * 
   */
  public abstract static class LocationsCallback extends JSFunction {
    public abstract void callback(Response response);
  }

  /**
   * 
   */
  @BeanProperties
  public static interface Response extends JSWrapper<Response> {
    String getName();

    @FieldName("Placemark")
    JSList<Placemark> getPlacemarks();

    @FieldName("Status")
    ResponseStatus getStatus();
  }

  /**
   * 
   */
  @BeanProperties
  public static interface ResponseStatus extends JSWrapper<ResponseStatus> {
    int getCode();
  }
  
  GeocoderImpl impl = GWT.create(GeocoderImpl.class);

  @Constructor("$wnd.GClientGeocoder")
  JavaScriptObject construct();

  @Constructor("$wnd.GClientGeocoder")
  JavaScriptObject construct(GeocodeCache cache);

  String getBaseCountryCode(JavaScriptObject jsoPeer);

  GeocodeCache getCache(JavaScriptObject jsoPeer);

  void getLatLng(JavaScriptObject jsoPeer, String address,
      LatLngCallback callback);

  void getLocations(JavaScriptObject jsoPeer, String address,
      LocationsCallback callback);

  LatLngBounds getViewport(JavaScriptObject jsoPeer);

  void reset(JavaScriptObject jsoPeer);

  void setBaseCountryCode(JavaScriptObject jsoPeer, String countryCode);

  void setCache(JavaScriptObject jsoPeer, GeocodeCache cache);

  void setViewport(JavaScriptObject jsoPeer, LatLngBounds bounds);
}
