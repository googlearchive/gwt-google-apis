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
import com.google.gwt.maps.jsio.client.BeanProperties;
import com.google.gwt.maps.jsio.client.Constructor;
import com.google.gwt.maps.jsio.client.FieldName;
import com.google.gwt.maps.jsio.client.JSFlyweightWrapper;

/**
 * Wraps the GDirectionQueryOptions object in the Maps API using JSIO. It has no
 * constructor, but is instantiated as an object literal.
 */
@BeanProperties
public interface DirectionQueryOptionsImpl extends JSFlyweightWrapper {

  DirectionQueryOptionsImpl impl = GWT.create(DirectionQueryOptionsImpl.class);

  @Constructor("Object")
  JavaScriptObject construct();

  @FieldName("avoidHighways")
  void setAvoidHighways(JavaScriptObject jsoPeer, boolean avoid);

  void setLocale(JavaScriptObject jsoPeer, String locale);

  void setPreserveViewport(JavaScriptObject jsoPeer, boolean preserveViewport);

  @FieldName("getPolyline")
  void setRetrievePolyline(JavaScriptObject jsoPeer, boolean retrievePolyline);

  @FieldName("getSteps")
  void setRetrieveSteps(JavaScriptObject jsoPeer, boolean retrieveSteps);

  @FieldName("travelMode")
  void setTravelMode(JavaScriptObject jsoPeer, int travelMode);
}
