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
import com.google.gwt.maps.jsio.client.Binding;
import com.google.gwt.maps.jsio.client.Constructor;
import com.google.gwt.maps.jsio.client.JSFlyweightWrapper;

/**
 * JSIO Wrapper for the GGeocodeCache class.
 */
public interface GeocodeCacheImpl extends JSFlyweightWrapper {

  GeocodeCacheImpl impl = GWT.create(GeocodeCacheImpl.class);

  @Binding
  void bind(JavaScriptObject jsoPeer, ExportedGeocodeCache cache);

  @Constructor("$wnd.GFactualGeocodeCache")
  JavaScriptObject constructFactualGeocodeCache();

  @Constructor("$wnd.GGeocodeCache")
  JavaScriptObject constructGeocodeCache();

  JavaScriptObject get(JavaScriptObject jsoPeer, String address);

  boolean isCachable(JavaScriptObject jsoPeer, JavaScriptObject reply);

  void put(JavaScriptObject jsoPeer, String address, JavaScriptObject reply);

  void reset(JavaScriptObject jsoPeer);

  String toCanonical(JavaScriptObject jsoPeer, String address);
}
