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
import com.google.gwt.jsio.client.Global;
import com.google.gwt.jsio.client.JSWrapper;

/**
 * Wraps the prototype object associated with GGeocodeCache from the Maps API
 * using JSIO. This is done to facilitate subclassing GGeocodeCache in Java.
 */
@Global("$wnd.GGeocodeCache.constructor.prototype")
public interface GeocodeCachePrototype extends JSWrapper<GeocodeCachePrototype> {

  GeocodeCachePrototype impl = GWT.create(GeocodeCachePrototype.class);
  
  JavaScriptObject get(String address);
  
  boolean isCachable(JavaScriptObject reply);

  void put(String address, JavaScriptObject reply);
  
  void reset();
  
  String toCanonical(String address);
}
