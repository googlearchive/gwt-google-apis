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
import com.google.gwt.maps.client.overlay.TrafficOverlay;
import com.google.gwt.maps.client.overlay.TrafficOverlayOptions;
import com.google.gwt.maps.jsio.client.Constructor;
import com.google.gwt.maps.jsio.client.JSFlyweightWrapper;

/**
 * Wrapper for the GTrafficOverlay object from the Maps API using JSIO.
 */
public interface TrafficOverlayImpl extends JSFlyweightWrapper {

  TrafficOverlayImpl impl = GWT.create(TrafficOverlayImpl.class);

  @Constructor("$wnd.GTrafficOverlay")
  JavaScriptObject construct();

  @Constructor("$wnd.GTrafficOverlay")
  JavaScriptObject construct(TrafficOverlayOptions options);
  
  void setVisible(TrafficOverlay overlay, boolean visible);
}
