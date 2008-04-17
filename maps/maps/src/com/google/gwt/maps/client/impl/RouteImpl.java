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
import com.google.gwt.maps.client.geocode.Distance;
import com.google.gwt.maps.client.geocode.Duration;
import com.google.gwt.maps.client.geocode.Placemark;
import com.google.gwt.maps.client.geocode.Step;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.jsio.client.JSFlyweightWrapper;

/**
 * Wraps the GRoute object from the Maps API using JSIO.
 */
public interface RouteImpl extends JSFlyweightWrapper {

  RouteImpl impl = GWT.create(RouteImpl.class);

  Distance getDistance(JavaScriptObject jsoPeer);

  Duration getDuration(JavaScriptObject jsoPeer);

  Placemark getEndGeocode(JavaScriptObject jsoPeer);

  LatLng getEndLatLng(JavaScriptObject jsoPeer);

  int getNumSteps(JavaScriptObject jsoPeer);

  Placemark getStartGeocode(JavaScriptObject jsoPeer);

  Step getStep(JavaScriptObject jsoPeer, int index);
  
  String getSummaryHtml(JavaScriptObject jsoPeer);
  
}
