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
import com.google.gwt.maps.client.control.Control.CustomControl;
import com.google.gwt.maps.jsio.client.Binding;
import com.google.gwt.maps.jsio.client.Constructor;
import com.google.gwt.maps.jsio.client.JSFlyweightWrapper;

/**
 * Wraps the GControl and its subclasses in the Maps API using JSIO.
 */
public interface ControlImpl extends JSFlyweightWrapper {

  ControlImpl impl = GWT.create(ControlImpl.class);

  @Binding
  void bind(JavaScriptObject jsoPeer, CustomControl control);

  @Constructor("$wnd.GControl")
  JavaScriptObject createControl(boolean printable, boolean selectable);

  @Constructor("$wnd.GLargeMapControl")
  JavaScriptObject createLargeMapControl();

  @Constructor("$wnd.GLargeMapControl3D")
  JavaScriptObject createLargeMapControl3D();
  
  @Constructor("$wnd.GMapTypeControl")
  JavaScriptObject createMapTypeControl();

  @Constructor("$wnd.GMapTypeControl")
  JavaScriptObject createMapTypeControl(boolean useShortNames);

  @Constructor("$wnd.GMenuMapTypeControl")
  JavaScriptObject createMenuMapTypeControl();

  @Constructor("$wnd.GMenuMapTypeControl")
  JavaScriptObject createMenuMapTypeControl(boolean useShortNames);

  @Constructor("$wnd.GNavLabelControl")
  JavaScriptObject createNavLabelControl();

  @Constructor("$wnd.GOverviewMapControl")
  JavaScriptObject createOverviewMapControl();

  @Constructor("$wnd.GScaleControl")
  JavaScriptObject createScaleControl();

  @Constructor("$wnd.GSmallMapControl")
  JavaScriptObject createSmallMapControl();

  @Constructor("$wnd.GSmallZoomControl")
  JavaScriptObject createSmallZoomControl();
  
  @Constructor("$wnd.GSmallZoomControl3D")
  JavaScriptObject createSmallZoomControl3D();
}
