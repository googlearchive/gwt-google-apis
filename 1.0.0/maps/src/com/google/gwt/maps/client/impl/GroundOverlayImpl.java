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
import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.maps.jsio.client.Constructor;
import com.google.gwt.maps.jsio.client.JSFlyweightWrapper;

/**
 * Creates a binding to the GGroundOverlay class in the Maps API.
 */
public interface  GroundOverlayImpl extends JSFlyweightWrapper {

  GroundOverlayImpl impl = GWT.create(GroundOverlayImpl.class);

  @Constructor("$wnd.GGroundOverlay")
  JavaScriptObject construct(String imageUrl, LatLngBounds bounds);

  void hide(JavaScriptObject jsoPeer);
  
  boolean isHidden(JavaScriptObject jsoPeer);
  
  void show(JavaScriptObject jsoPeer);
  
}
