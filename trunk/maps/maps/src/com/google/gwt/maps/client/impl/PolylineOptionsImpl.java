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
import com.google.gwt.maps.jsio.client.JSFlyweightWrapper;

/**
 * Wraps the GPolylineOptions class from the Maps API using JSIO. The Maps API
 * does not provide a constructor for this object. Instead, the object is
 * constructed from a JavaScript Object literal.
 */
@BeanProperties
public abstract class PolylineOptionsImpl implements JSFlyweightWrapper {
  public static final PolylineOptionsImpl impl = GWT.create(PolylineOptionsImpl.class);

  /**
   * The Maps API does not provide a GPolylineOptions constructor. It advises to
   * use Object instead.
   */
  @Constructor("Object")
  public abstract JavaScriptObject construct();

  public abstract void setColor(JavaScriptObject jsoPeer, String color);

  public abstract void setLevels(JavaScriptObject jsoPeer, String encodedLevels);

  public abstract void setNumLevels(JavaScriptObject jsoPeer, int numLevels);

  public abstract void setOpacity(JavaScriptObject jsoPeer, double opacity);

  public abstract void setPoints(JavaScriptObject jsoPeer, String encodedPoints);

  public abstract void setWeight(JavaScriptObject jsoPeer, int weight);

  public abstract void setZoomFactor(JavaScriptObject jsoPeer, int zoomFactor);
}