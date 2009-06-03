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
import com.google.gwt.maps.client.impl.GeocodeCacheImpl;

/**
 * This class refines the basic GeocodeCache class by placing stricter
 * conditions on cached replies. It only caches replies which are very unlikely
 * to change within a short period of time.
 */
public final class FactualGeocodeCache extends GeocodeCache {

  /**
   * Constructs a new cache that only caches replies which are very unlikely to
   * change within a short period of time.
   */
  public FactualGeocodeCache() {
    this(GeocodeCacheImpl.impl.constructFactualGeocodeCache());
    // No bind() is needed - you can't extend this object.
  }

  private FactualGeocodeCache(JavaScriptObject jsoPeer) {
    super(jsoPeer);
  }

  /*
   * Design note: These methods are not exported because that would cause an
   * infinite loop. These methods are just for developer access to the functions
   * in the cache, not as a way to extend GeocoderCache functionality. See
   * {@link AbstractGeocoderCache}.
   */
  @Override
  public JavaScriptObject get(String address) {
    return GeocodeCacheImpl.impl.get(jsoPeer, address);
  }

  @Override
  public boolean isCacheable(JavaScriptObject reply) {
    return GeocodeCacheImpl.impl.isCachable(jsoPeer, reply);
  }

  @Override
  public void put(String address, JavaScriptObject reply) {
    GeocodeCacheImpl.impl.put(jsoPeer, address, reply);
  }

  @Override
  public void reset() {
    GeocodeCacheImpl.impl.reset(jsoPeer);
  }

  @Override
  public String toCanonical(String address) {
    return GeocodeCacheImpl.impl.toCanonical(jsoPeer, address);
  }
}
