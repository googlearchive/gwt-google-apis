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
import com.google.gwt.jsio.client.Constructor;
import com.google.gwt.jsio.client.JSFlyweightWrapper;

/**
 * 
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public interface DirectionsRendererImpl extends JSFlyweightWrapper {
  
  DirectionsRendererImpl impl = GWT.create(DirectionsRendererImpl.class);
  
  @Constructor("$wnd.google.maps.DirectionsRenderer")
  JavaScriptObject construct();
  
  @Constructor("$wnd.google.maps.DirectionsRenderer")
  JavaScriptObject construct(JavaScriptObject options);

  public JavaScriptObject getDirections(JavaScriptObject jso);

  public JavaScriptObject getMap(JavaScriptObject jso);

  public JavaScriptObject getPanel(JavaScriptObject jso);

  public int getRouteIndex(JavaScriptObject jso);

  public void setDirections(JavaScriptObject jso, JavaScriptObject directions);

  public void setMap(JavaScriptObject jso, JavaScriptObject map);

  public void setPanel(JavaScriptObject jso, JavaScriptObject markerOptions);

  public void setRouteIndex(JavaScriptObject jso, int routeIndex);

}
