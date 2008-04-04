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
import com.google.gwt.jsio.client.Binding;
import com.google.gwt.jsio.client.Constructor;
import com.google.gwt.jsio.client.JSFlyweightWrapper;
import com.google.gwt.maps.client.geocode.GeocodeCache;
import com.google.gwt.maps.client.geocode.GeocodeCache.ConcreteGeocodeCache;

/**
 * Wraps the GGeocodeCache and GFactualGeocodeCache objects from the Maps API
 * using JSIO.
 */
public abstract class GeocodeCacheImpl implements JSFlyweightWrapper {

  public static GeocodeCacheImpl impl = GWT.create(GeocodeCacheImpl.class);

  /**
   * This bind function is manual because using \@Binding causes an exception to
   * be thrown with a MultipleWrapper exception.
   */
  public native void bindConcreteGeocodeCache(JavaScriptObject jsoPeer,
      ConcreteGeocodeCache geocodeCache) /*-{
    jsoPeer.__gwtPeer = geocodeCache;
  }-*/;
  
  @Binding
  public abstract void bindGeocodeCache(JavaScriptObject jsoPeer, GeocodeCache cache);

  @Constructor("$wnd.GFactualGeocodeCache")
  public abstract JavaScriptObject constructFactualGeocodeCache();

  @Constructor("$wnd.GGeocodeCache")
  public abstract JavaScriptObject constructGeocodeCache();

  public abstract JavaScriptObject get(JavaScriptObject cache, String address);

  public abstract boolean isCachable(JavaScriptObject cache, JavaScriptObject reply);

  public abstract void put(JavaScriptObject cache, String address, JavaScriptObject reply);

  public abstract void reset(JavaScriptObject cache);

  public abstract String toCanonical(JavaScriptObject cache, String address);
}
