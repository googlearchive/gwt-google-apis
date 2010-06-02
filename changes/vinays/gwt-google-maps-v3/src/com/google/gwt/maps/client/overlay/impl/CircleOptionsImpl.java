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
import com.google.gwt.jsio.client.BeanProperties;
import com.google.gwt.jsio.client.Constructor;
import com.google.gwt.jsio.client.JSFlyweightWrapper;

/**
 * 
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public interface CircleOptionsImpl extends JSFlyweightWrapper {

  public CircleOptionsImpl impl = GWT.create(CircleOptionsImpl.class);
  
  @Constructor("Object")
  public JavaScriptObject construct();

  @BeanProperties
  public JavaScriptObject getCenter(JavaScriptObject jso);

  @BeanProperties
  public void setCenter(JavaScriptObject jso, JavaScriptObject center);

  @BeanProperties
  public String getFillColor(JavaScriptObject jso);

  @BeanProperties
  public void setFillColor(JavaScriptObject jso, String fillColor);

  @BeanProperties
  public double getFillOpacity(JavaScriptObject jso);

  @BeanProperties
  public void setFillOpacity(JavaScriptObject jso, double fillOpacity);

  @BeanProperties
  public double getRadius(JavaScriptObject jso);

  @BeanProperties
  public void setRadius(JavaScriptObject jso, double radius);

  @BeanProperties
  public String getStrokeColor(JavaScriptObject jso);

  @BeanProperties
  public void setStrokeColor(JavaScriptObject jso, String strokeColor);

  @BeanProperties
  public double getStrokeOpacity(JavaScriptObject jso);

  @BeanProperties
  public void setStrokeOpacity(JavaScriptObject jso, double strokeOpacity);

  @BeanProperties
  public int getStrokeWeight(JavaScriptObject jso);

  @BeanProperties
  public void setStrokeWeight(JavaScriptObject jso, int strokeWeight);
}
