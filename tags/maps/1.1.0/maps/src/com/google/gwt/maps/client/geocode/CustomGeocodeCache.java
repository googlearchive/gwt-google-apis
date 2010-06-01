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
package com.google.gwt.maps.client.geocode;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.impl.ExportedGeocodeCache;
import com.google.gwt.maps.client.impl.GeocodeCacheImpl;

/**
 * Provides a way to customize the caching behavior of geocoded queries. This
 * class is both functional and a base class so the user can extend it.
 */
public class CustomGeocodeCache extends GeocodeCache {

  /**
   * Used by JSIO library to instantiate this class from an existing
   * JavaScript object.
   */
  @SuppressWarnings("unused")
  private static CustomGeocodeCache createPeer(JavaScriptObject jsoPeer) {
    return new CustomGeocodeCache(jsoPeer);
  }

  private static native JavaScriptObject nativeGet(JavaScriptObject jsoPeer,
      String address) /*-{
    return jsoPeer.constructor.prototype.get.call(jsoPeer, address);
  }-*/;

  private static native boolean nativeIsCacheable(JavaScriptObject jsoPeer,
      JavaScriptObject reply) /*-{
    return jsoPeer.constructor.prototype.isCacheable.call(jsoPeer, reply);
  }-*/;

  private static native void nativePut(JavaScriptObject jsoPeer,
      String address, JavaScriptObject reply) /*-{
    return jsoPeer.constructor.prototype.put.call(jsoPeer, address, reply);
  }-*/;

  private static native void nativeReset(JavaScriptObject jsoPeer) /*-{
    jsoPeer.constructor.prototype.reset.call(jsoPeer);
  }-*/;

  private static native String nativeToCanonical(JavaScriptObject jsoPeer,
      String address) /*-{
    return jsoPeer.constructor.prototype.toCanonical.call(jsoPeer, address);
  }-*/;

  private ExportedGeocodeCache exportedGeocodeCache;

  public CustomGeocodeCache() {
    super(GeocodeCacheImpl.impl.constructGeocodeCache());

    /*
     * The exported GeocodeCache object is bound to the GGeocodeCache object
     * returned from JavaScript and then delegates all methods back to
     * AbstractGeocodeCache. This is to work around problems when trying to use
     * @Exported in the same class hierarchy with methods you want to import in
     * JSIO.
     */
    exportedGeocodeCache = new ExportedGeocodeCache(this);
    GeocodeCacheImpl.impl.bind(jsoPeer, exportedGeocodeCache);
  }

  CustomGeocodeCache(JavaScriptObject jsoPeer) {
    super(jsoPeer);
  }

  @Override
  public JavaScriptObject get(String address) {
    return nativeGet(jsoPeer, address);
  }

  @Override
  public boolean isCacheable(JavaScriptObject reply) {
    return nativeIsCacheable(jsoPeer, reply);
  }

  @Override
  public void put(String address, JavaScriptObject reply) {
    nativePut(jsoPeer, address, reply);
  }

  @Override
  public void reset() {
    nativeReset(jsoPeer);
  }

  @Override
  public String toCanonical(String address) {
    return nativeToCanonical(jsoPeer, address);
  }
}
