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
package com.google.gwt.maps.client.directions.impl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.jsio.client.BeanProperties;
import com.google.gwt.jsio.client.Constructor;
import com.google.gwt.jsio.client.FieldName;
import com.google.gwt.jsio.client.JSFlyweightWrapper;

/**
 * 
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public interface DirectionsWaypointImpl extends JSFlyweightWrapper {
  
  public DirectionsWaypointImpl impl = GWT.create(DirectionsWaypointImpl.class);
  
  @Constructor("Object")
  public JavaScriptObject construct();

  @BeanProperties
  @FieldName("location")
  public String getLocationString(JavaScriptObject jso);

  @BeanProperties
  @FieldName("location")
  public JavaScriptObject getLocationLatLng(JavaScriptObject jso);

  @BeanProperties
  @FieldName("location")
  public void setLocationString(JavaScriptObject jso, String location);

  @BeanProperties
  @FieldName("location")
  public void setLocationLatLng(JavaScriptObject jso, JavaScriptObject point);

  @BeanProperties
  @FieldName("stopover")
  public boolean isStopover(JavaScriptObject jso);

  @BeanProperties
  public void setStopover(JavaScriptObject jso, boolean isStopover);

}
