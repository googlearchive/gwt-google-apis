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
import com.google.gwt.jsio.client.JSFlyweightWrapper;
import com.google.gwt.maps.client.MapType;
import com.google.gwt.jsio.client.BeanProperties;
import com.google.gwt.jsio.client.Constructor;
import com.google.gwt.jsio.client.FieldName;
import com.google.gwt.maps.client.impl.EventImpl.VoidCallback;

/**
 * 
 */
@BeanProperties
public interface InfoWindowOptionsImpl extends JSFlyweightWrapper {

  public static final InfoWindowOptionsImpl impl = (InfoWindowOptionsImpl) GWT.create(InfoWindowOptionsImpl.class);

  @Constructor("Object")
  public JavaScriptObject construct();

  public void setMapType(JavaScriptObject jsoPeer, MapType mapType);

  /**
   * JSIO will not create a JS binding to an overloaded method. Use the
   * fieldName annotation to map 2 differently named functions to the same JS
   * property.
   * 
   */
  @FieldName("maxContent")
  public void setMaxContentElement(JavaScriptObject jsoPeer,
      JavaScriptObject maximizedContentWidget);

  @FieldName("maxContent")
  public void setMaxContentString(JavaScriptObject jsoPeer,
      String windowMaximizedContent);

  @FieldName("maxTitle")
  public void setMaxTitleElement(JavaScriptObject jsoPeer,
      JavaScriptObject maximizedTitleWidget);

  @FieldName("maxTitle")
  public void setMaxTitleString(JavaScriptObject jsoPeer,
      String windowMaximizedTitle);

  public void setMaxWidth(JavaScriptObject jsoPeer, int maxWidth);

  public void setNoCloseOnClick(JavaScriptObject jsoPeer, boolean noCloseFlag);

  public void setOnCloseFn(JavaScriptObject jsoPeer, VoidCallback closeFunction);

  public void setOnOpenFn(JavaScriptObject jsoPeer, VoidCallback openFunction);

  public void setSelectedTab(JavaScriptObject jsoPeer, int tab);

  public void setZoomLevel(JavaScriptObject jsoPeer, int zoomLevel);

}