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
import com.google.gwt.maps.client.InfoWindowContent.InfoWindowTab;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.Size;
import com.google.gwt.maps.jsio.client.Constructor;
import com.google.gwt.maps.jsio.client.JSFlyweightWrapper;
import com.google.gwt.maps.jsio.client.JSList;
import com.google.gwt.user.client.Element;

/**
 * Provides an interface to the GInfoWindowTab object from the Maps API using
 * JSIO.
 */
public interface InfoWindowImpl extends JSFlyweightWrapper {

  InfoWindowImpl impl = GWT.create(InfoWindowImpl.class);

  @Constructor("$wnd.GInfoWindowTab")
  JavaScriptObject createInfoWindowTab(String label, Element content);

  @Constructor("$wnd.GInfoWindowTab")
  JavaScriptObject createInfoWindowTab(String label, String content);

  void disableMaximize(JavaScriptObject jsoPeer);

  void enableMaximize(JavaScriptObject jsoPeer);

  JSList<Element> getContentContainers(JavaScriptObject jsoPeer);

  Size getPixelOffset(JavaScriptObject jsoPeer);

  LatLng getPoint(JavaScriptObject jsoPeer);

  int getSelectedTab(JavaScriptObject jsoPeer);

  void hide(JavaScriptObject jsoPeer);

  boolean isHidden(JavaScriptObject jsoPeer);

  void maximize(JavaScriptObject jsoPeer);

  void reset(JavaScriptObject jsoPeer, LatLng point,
      JSList<InfoWindowTab> tabs, Size size);

  void reset(JavaScriptObject jsoPeer, LatLng point,
      JSList<InfoWindowTab> tabs, Size size, Size offset);

  void reset(JavaScriptObject jsoPeer, LatLng point,
      JSList<InfoWindowTab> tabs, Size size, Size offset, int selectedTab);

  void restore(JavaScriptObject jsoPeer);

  void selectTab(JavaScriptObject jsoPeer, int index);

  void show(JavaScriptObject jsoPeer);
}
