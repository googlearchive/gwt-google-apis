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
package com.google.gwt.maps.client.overlay.impl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.jsio.client.BeanProperties;
import com.google.gwt.jsio.client.Constructor;
import com.google.gwt.jsio.client.JSFlyweightWrapper;
import com.google.gwt.maps.client.HasMap;

/**
 * 
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
@BeanProperties
public interface PolygonOptionsImpl extends JSFlyweightWrapper {
  
  PolygonOptionsImpl impl = GWT.create(PolygonOptionsImpl.class);
  
  @Constructor("Object")
  public JavaScriptObject construct();

  public String getFillColor(JavaScriptObject jso);

  public double getFillOpacity(JavaScriptObject jso);

  public HasMap getMap(JavaScriptObject jso);

  public JsArray<JsArray<JavaScriptObject>> getPaths(JavaScriptObject jso);

  public String getStrokeColor(JavaScriptObject jso);

  public double getStrokeOpacity(JavaScriptObject jso);

  public int getStrokeWeight(JavaScriptObject jso);

  public int getZIndex(JavaScriptObject jso);

  public void setFillColor(JavaScriptObject jso, String fillColor);

  public void setFillOpacity(JavaScriptObject jso, double fillOpacity);

  public void setMap(JavaScriptObject jso, HasMap map);

  public void setPaths(JavaScriptObject jso, JsArray<JsArray<JavaScriptObject>> paths);

  public void setStrokeColor(JavaScriptObject jso, String strokeColor);

  public void setStrokeOpacity(JavaScriptObject jso, double strokeOpacity);

  public void setStrokeWeight(JavaScriptObject jso, int strokeWeight);

  public void setZIndex(JavaScriptObject jso, int zIndex);

}
