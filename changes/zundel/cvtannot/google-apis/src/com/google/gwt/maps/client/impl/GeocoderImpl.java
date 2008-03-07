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
   * @gwt.beanProperties
   */
  public static interface Response extends JSWrapper {
    public String getName();

    /**
     * @gwt.fieldName Placemark
     */
    public JSList<Placemark> getPlacemarks();

    /**
     * @gwt.fieldName Status
     */
    public ResponseStatus getStatus();
  }
  
  /**
   * @gwt.beanProperties
   */
  public static interface ResponseStatus extends JSWrapper {
    public int getCode();
  }
  
  public static GeocoderImpl impl = (GeocoderImpl) GWT.create(GeocoderImpl.class);

  /**
   * @gwt.constructor $wnd.GClientGeocoder
   */
  public JavaScriptObject construct();

  /**
   * @gwt.constructor $wnd.GClientGeocoder
   */
  public JavaScriptObject construct(GeocodeCache cache);

  public String getBaseCountryCode(JavaScriptObject jsoPeer);

  public GeocodeCache getCache(JavaScriptObject jsoPeer);

  public void getLatLng(JavaScriptObject jsoPeer, String address,
      LatLngCallback callback);

  public void getLocations(JavaScriptObject jsoPeer, String address,
      LocationsCallback callback);

  public LatLngBounds getViewport(JavaScriptObject jsoPeer);

  public void reset(JavaScriptObject jsoPeer);

  public void setBaseCountryCode(JavaScriptObject jsoPeer, String countryCode);

  public void setCache(JavaScriptObject jsoPeer, GeocodeCache cache);

  public void setViewport(JavaScriptObject jsoPeer, LatLngBounds bounds);
}
