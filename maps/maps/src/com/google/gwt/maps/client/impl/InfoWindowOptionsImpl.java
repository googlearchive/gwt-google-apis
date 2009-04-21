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
import com.google.gwt.maps.client.MapType;
import com.google.gwt.maps.client.impl.EventImpl.VoidCallback;
import com.google.gwt.maps.jsio.client.BeanProperties;
import com.google.gwt.maps.jsio.client.Constructor;
import com.google.gwt.maps.jsio.client.FieldName;
import com.google.gwt.maps.jsio.client.JSFlyweightWrapper;

/**
 * Provides an interface to the GInfoWindowsOptions object from the Maps API
 * using JSIO. Note that there is no provided constructor for this object.
 * Instead it is build from an Object literal.
 */
@BeanProperties
public interface InfoWindowOptionsImpl extends JSFlyweightWrapper {

  InfoWindowOptionsImpl impl = GWT.create(InfoWindowOptionsImpl.class);

  @Constructor("Object")
  JavaScriptObject construct();

  void setMapType(JavaScriptObject jsoPeer, MapType mapType);

  /**
   * JSIO will not create a JS binding to an overloaded method. Use the
   * fieldName annotation to map 2 differently named functions to the same JS
   * property.
   * 
   */
  @FieldName("maxContent")
  void setMaxContentElement(JavaScriptObject jsoPeer,
      JavaScriptObject maximizedContentWidget);

  @FieldName("maxContent")
  void setMaxContentString(JavaScriptObject jsoPeer,
      String windowMaximizedContent);

  @FieldName("maxTitle")
  void setMaxTitleElement(JavaScriptObject jsoPeer,
      JavaScriptObject maximizedTitleWidget);

  @FieldName("maxTitle")
  void setMaxTitleString(JavaScriptObject jsoPeer, String windowMaximizedTitle);

  void setMaxWidth(JavaScriptObject jsoPeer, int maxWidth);

  void setNoCloseOnClick(JavaScriptObject jsoPeer, boolean noCloseFlag);

  /**
   * @deprecated use a GEvent handler instead.
   */
  @Deprecated
  void setOnCloseFn(JavaScriptObject jsoPeer, VoidCallback closeFunction);

  /**
   * @deprecated use a GEvent handler instead.
   */
  @Deprecated
  void setOnOpenFn(JavaScriptObject jsoPeer, VoidCallback openFunction);

  void setSelectedTab(JavaScriptObject jsoPeer, int tab);

  void setZoomLevel(JavaScriptObject jsoPeer, int zoomLevel);

}