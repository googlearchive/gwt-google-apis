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
package com.google.gwt.maps.client.impl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.jsio.client.JSWrapper;
import com.google.gwt.maps.client.geocode.GeocodeCache;

/**
 * @gwt.global $wnd.GGeocodeCache.prototype
 */
public interface GeocodeCachePrototype extends JSWrapper {

  public static GeocodeCachePrototype impl = (GeocodeCachePrototype) GWT.create(GeocodeCachePrototype.class);

  /**
   * @gwt.imported get.call
   */
  public JavaScriptObject get(GeocodeCache instance, String address);

  /**
   * @gwt.imported isCachable.call
   */
  public boolean isCachable(GeocodeCache instance, JavaScriptObject reply);

  /**
   * @gwt.imported put.call
   */
  public void put(GeocodeCache instance, String address, JavaScriptObject reply);

  /**
   * @gwt.imported reset.call
   */
  public void reset(GeocodeCache instance);

  /**
   * @gwt.imported toCanonical.call
   */
  public String toCanonical(GeocodeCache instance, String address);
}
