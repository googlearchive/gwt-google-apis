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
package com.google.gwt.maps.client.impl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.jsio.client.Constructor;
import com.google.gwt.jsio.client.JSFlyweightWrapper;
import com.google.gwt.user.client.Element;

/**
 * JS interfacing with Map class.
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public interface MapImpl extends JSFlyweightWrapper {

  MapImpl impl = GWT.create(MapImpl.class);
  
  @Constructor("$wnd.google.maps.Map")
  JavaScriptObject construct(Element mapDiv);
  
  @Constructor("$wnd.google.maps.Map")
  JavaScriptObject construct(Element mapDiv, JavaScriptObject mapOptions);

  public void fitBounds(JavaScriptObject jso, JavaScriptObject bounds);
  
  public JavaScriptObject getBounds(JavaScriptObject jso);
  
  public JavaScriptObject getCenter(JavaScriptObject jso);
  
  public Element getDiv(JavaScriptObject jso);
  
  public String getMapTypeId(JavaScriptObject jso);
  
  public JavaScriptObject getProjection(JavaScriptObject jso);
  
  int getZoom(JavaScriptObject jso);
  
  void panBy(JavaScriptObject jso, int x, int y);
  
  void panTo(JavaScriptObject jso, JavaScriptObject latLng);
  
  void panToBounds(JavaScriptObject jso, JavaScriptObject bounds);
  
  void setCenter(JavaScriptObject jso, JavaScriptObject latLng);
  
  void setMapTypeId(JavaScriptObject jso, String mapTypeId);
  
  void setOptions(JavaScriptObject jso, JavaScriptObject options);
  
  void setZoom(JavaScriptObject jso, int zoom);
  
}
