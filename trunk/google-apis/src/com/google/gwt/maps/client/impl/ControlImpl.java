/*
 * Copyright 2007 Google Inc.
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
import com.google.gwt.jsio.client.JSFlyweightWrapper;
import com.google.gwt.maps.client.control.Control.CustomControl;

/**
 * 
 */
public interface ControlImpl extends JSFlyweightWrapper {
  
  public static ControlImpl impl = (ControlImpl) GWT.create(ControlImpl.class);

  /**
   * @gwt.binding
   */
  public void bind(JavaScriptObject jsoPeer, CustomControl control);

  /**
   * @gwt.constructor $wnd.GControl
   */
  public JavaScriptObject createControl(boolean printable, boolean selectable);

  /**
   * @gwt.constructor $wnd.GLargeMapControl
   */
  public JavaScriptObject createLargeMapControl();

  /**
   * @gwt.constructor $wnd.GMapTypeControl
   */
  public JavaScriptObject createMapTypeControl();

  /**
   * @gwt.constructor $wnd.GScaleControl
   */
  public JavaScriptObject createScaleControl();
  
  /**
   * @gwt.constructor $wnd.GSmallMapControl
   */
  public JavaScriptObject createSmallMapControl();
  
  /**
   * @gwt.constructor $wnd.GSmallZoomControl
   */
  public JavaScriptObject createSmallZoomControl();
}
