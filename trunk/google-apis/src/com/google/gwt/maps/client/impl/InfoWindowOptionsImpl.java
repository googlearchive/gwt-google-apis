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
import com.google.gwt.maps.client.MapType;
import com.google.gwt.maps.client.impl.EventImpl.VoidCallback;

/**
 * @gwt.beanProperties
 */
public interface InfoWindowOptionsImpl extends JSFlyweightWrapper {

  public static final InfoWindowOptionsImpl impl = (InfoWindowOptionsImpl) GWT.create(InfoWindowOptionsImpl.class);

  /**
   * @gwt.constructor Object
   */
  public JavaScriptObject construct();

  public void setMapType(JavaScriptObject jsoPeer, MapType mapType);

  public void setMaxWidth(JavaScriptObject jsoPeer, int maxWidth);

  public void setOnCloseFn(JavaScriptObject jsoPeer, VoidCallback closeFunction);

  public void setOnOpenFn(JavaScriptObject jsoPeer, VoidCallback openFunction);

  public void setSelectedTab(JavaScriptObject jsoPeer, int tab);

  public void setZoomLevel(JavaScriptObject jsoPeer, int zoomLevel);

}