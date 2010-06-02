/* Copyright (c) 2010 Vinay Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.gwt.maps.client.base.impl;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.jsio.client.Constructor;
import com.google.gwt.jsio.client.JSFlyweightWrapper;

/**
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public interface LatLngBoundsImpl extends JSFlyweightWrapper {

  @Constructor("$wnd.google.maps.LatLngBounds")
  public JavaScriptObject construct();
  
  @Constructor("$wnd.google.maps.LatLngBounds")
  public JavaScriptObject construct(JavaScriptObject sw, JavaScriptObject ne);
  
  public boolean contains(JavaScriptObject jso, JavaScriptObject point);
  
  public boolean equals(JavaScriptObject jso, JavaScriptObject other);
  
  public JavaScriptObject extend(JavaScriptObject jso, JavaScriptObject point);
  
  public JavaScriptObject getCenter(JavaScriptObject jso);
  
  public JavaScriptObject getNorthEast(JavaScriptObject jso);
  
  public JavaScriptObject getSouthWest(JavaScriptObject jso);
  
  public boolean intersects(JavaScriptObject jso, JavaScriptObject other);
  
  public boolean isEmpty(JavaScriptObject jso);
  
  public JavaScriptObject toSpan(JavaScriptObject jso);
  
  public String toString(JavaScriptObject jso);
  
  public String toUrlValue(JavaScriptObject jso);
  
  public String toUrlValue(JavaScriptObject jso, int precision);
  
  public JavaScriptObject union(JavaScriptObject jso, JavaScriptObject other);
  
}
