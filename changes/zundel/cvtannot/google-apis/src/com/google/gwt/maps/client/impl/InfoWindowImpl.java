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
import com.google.gwt.jsio.client.JSList;
import com.google.gwt.maps.client.InfoWindowContent.InfoWindowTab;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.Size;
import com.google.gwt.user.client.Element;

/**
 * 
 */
public interface InfoWindowImpl extends JSFlyweightWrapper {

  public static final InfoWindowImpl impl = (InfoWindowImpl) GWT.create(InfoWindowImpl.class);

  /**
   * @gwt.constructor $wnd.GInfoWindowTab
   */
  public JavaScriptObject createInfoWindowTab(String label, String content);

  /**
   * @gwt.constructor $wnd.GInfoWindowTab
   */
  public JavaScriptObject createInfoWindowTab(String label, Element content);

  public JSList<Element> getContentContainers(JavaScriptObject jsoPeer);

  public Size getPixelOffset(JavaScriptObject jsoPeer);

  public LatLng getPoint(JavaScriptObject jsoPeer);

  public int getSelectedTab(JavaScriptObject jsoPeer);

  public void hide(JavaScriptObject jsoPeer);

  public boolean isHidden(JavaScriptObject jsoPeer);

  public void reset(JavaScriptObject jsoPeer, LatLng point,
      JSList<InfoWindowTab> tabs, Size size);

  public void reset(JavaScriptObject jsoPeer, LatLng point,
      JSList<InfoWindowTab> tabs, Size size, Size offset);

  public void reset(JavaScriptObject jsoPeer, LatLng point,
      JSList<InfoWindowTab> tabs, Size size, Size offset, int selectedTab);

  public void selectTab(JavaScriptObject jsoPeer, int index);

  public void show(JavaScriptObject jsoPeer);

}
