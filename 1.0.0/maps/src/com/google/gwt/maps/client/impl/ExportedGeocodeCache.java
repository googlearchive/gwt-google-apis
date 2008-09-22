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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.geocode.CustomGeocodeCache;
import com.google.gwt.maps.jsio.client.Exported;

/**
 * Helper class for AbstractGeocodeCache. This class defines methods that are
 * actually exported to the GeocodeCache JavaScriptObject.
 */
public class ExportedGeocodeCache {
  private final CustomGeocodeCache gc;

  public ExportedGeocodeCache(CustomGeocodeCache gc) {
    this.gc = gc;
  }

  @Exported
  public JavaScriptObject get(String address) {
    return gc.get(address);
  }

  @Exported
  public boolean isCacheable(JavaScriptObject reply) {
    return gc.isCacheable(reply);
  }

  @Exported
  public void put(String address, JavaScriptObject reply) {
    gc.put(address, reply);
  }

  @Exported
  public void reset() {
    gc.reset();
  }

  @Exported
  public String toCanonical(String address) {
    return gc.toCanonical(address);
  }
}
