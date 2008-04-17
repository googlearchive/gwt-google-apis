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
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.geom.TileIndex;
import com.google.gwt.maps.jsio.client.BeanProperties;
import com.google.gwt.maps.jsio.client.Constructor;
import com.google.gwt.maps.jsio.client.JSFlyweightWrapper;

/**
 * Wraps the Maps API GPoint object from the Maps API using JSIO.
 */
@BeanProperties
public interface PointImpl extends JSFlyweightWrapper {
  PointImpl impl = GWT.create(PointImpl.class);

  @Constructor("$wnd.GPoint")
  JavaScriptObject construct(double x, double y);

  boolean equals(JavaScriptObject jso, Point other);
  
  boolean equals(JavaScriptObject jso, TileIndex other);

  int getX(JavaScriptObject jso);

  int getY(JavaScriptObject jso);
  
  void setX(JavaScriptObject o, int x);
  
  void setY(JavaScriptObject o, int y);

  String toString(JavaScriptObject jso);

}
