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
import com.google.gwt.jsio.client.Binding;
import com.google.gwt.jsio.client.JSFlyweightWrapper;
import com.google.gwt.maps.client.overlay.OverlayView;

/**
 * 
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public interface ProjectionImpl extends JSFlyweightWrapper {
  
  ProjectionImpl impl = GWT.create(ProjectionImpl.class);

  JavaScriptObject fromLatLngToPoint(JavaScriptObject jso, JavaScriptObject latLng);

  JavaScriptObject fromPointToLatLng(JavaScriptObject jso, JavaScriptObject point);
}
