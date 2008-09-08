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
import com.google.gwt.core.client.JsArray;
import com.google.gwt.maps.client.geom.Bounds;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.jsio.client.BeanProperties;
import com.google.gwt.maps.jsio.client.Constructor;
import com.google.gwt.maps.jsio.client.JSFlyweightWrapper;

/**
 * Wraps the GBounds object in the Maps API using JSIO.
 */
@BeanProperties
public interface BoundsImpl extends JSFlyweightWrapper {

  BoundsImpl impl = GWT.create(BoundsImpl.class);

  @Constructor("$wnd.GBounds")
  JavaScriptObject construct(JsArray<Point> points);

  boolean containsBounds(JavaScriptObject jsoPeer, Bounds other);

  boolean containsPoint(JavaScriptObject jsoPeer, Point point);

  void extend(JavaScriptObject jsoPeer, Point point);

  int getMaxX(JavaScriptObject jsoPeer);

  int getMaxY(JavaScriptObject jsoPeer);

  int getMinX(JavaScriptObject jsoPeer);

  int getMinY(JavaScriptObject jsoPeer);

  Point max(JavaScriptObject jsoPeer);

  Point mid(JavaScriptObject jsoPeer);

  Point min(JavaScriptObject jsoPeer);

  String toString(JavaScriptObject jsoPeer);
}
