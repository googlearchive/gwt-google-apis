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
import com.google.gwt.jsio.client.JSFlyweightWrapper;

/**
 * JS interfacing with MapCanvasProjection class.
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public interface MapCanvasProjectionImpl extends JSFlyweightWrapper {

  MapCanvasProjectionImpl impl = GWT.create(MapCanvasProjectionImpl.class);
  
  JavaScriptObject fromContainerPixelToLatLng(JavaScriptObject jso, JavaScriptObject pixel);
  
  JavaScriptObject fromDivPixelToLatLng(JavaScriptObject jso, JavaScriptObject pixel);
  
  JavaScriptObject fromLatLngToContainerPixel(JavaScriptObject jso, JavaScriptObject latLng);
  
  JavaScriptObject fromLatLngToDivPixel(JavaScriptObject jso, JavaScriptObject latLng);
  
  int getWorldWidth(JavaScriptObject jso);
  
}
