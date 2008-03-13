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
import com.google.gwt.jsio.client.Exported;
import com.google.gwt.maps.client.impl.GeocodeCachePrototype;

/**
 * 
 * This class is both functional and a base class so the user can extend it.
 */
public class GeocodeCache {

  // TODO(zundel): Needs to be modeled after Overlay & ConcreteOverlay
  // (prototype pattern?)

  static class ConcreteGeocodeCache extends GeocodeCache {
    public ConcreteGeocodeCache(JavaScriptObject jsoPeer) {
      super(jsoPeer);
    }

    @Override
    public boolean isCacheable(JavaScriptObject reply) {
      return GeocodeCacheImpl.impl.isCachable(this, reply);
    }

    @Override
    public void put(String address, JavaScriptObject reply) {
      GeocodeCacheImpl.impl.put(this, address, reply);
    }

    @Override
    public void reset() {
      GeocodeCacheImpl.impl.reset(this);
    }

    @Override
    public String toCanonical(String address) {
      return GeocodeCacheImpl.impl.toCanonical(this, address);
    }
  }

  static GeocodeCache createPeer(JavaScriptObject jsoPeer) {
    return new ConcreteGeocodeCache(jsoPeer);
  }

  private final JavaScriptObject jsoPeer;

  public GeocodeCache() {
    jsoPeer = GeocodeCacheImpl.impl.constructGeocodeCache();
    GeocodeCacheImpl.impl.bind(jsoPeer, this);
  }

  private GeocodeCache(JavaScriptObject jsoPeer) {
    this.jsoPeer = jsoPeer;
  }

  // TODO: get() is too generic. Can we be more specific
  @Exported
  public JavaScriptObject get(String address) {
    return GeocodeCachePrototype.impl.get(this, address);
  }

  // TODO(samgross): cacheable or cachable? I think cacheable is the preferred
  // spelling,
  // but, Maps API uses cachable

  @Exported
  public boolean isCacheable(JavaScriptObject reply) {
    return GeocodeCachePrototype.impl.isCachable(this, reply);
  }

  @Exported
  public void put(String address, JavaScriptObject reply) {
    GeocodeCachePrototype.impl.put(this, address, reply);
  }

  @Exported
  public void reset() {
    GeocodeCachePrototype.impl.reset(this);
  }

  @Exported
  public String toCanonical(String address) {
    return GeocodeCachePrototype.impl.toCanonical(this, address);
  }

}
