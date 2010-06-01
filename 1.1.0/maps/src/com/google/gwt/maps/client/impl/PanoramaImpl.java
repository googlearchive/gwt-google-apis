/*
 * Copyright 2010 Google Inc.
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
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.streetview.StreetviewPanoramaOptions;
import com.google.gwt.maps.client.streetview.PhotoSpec;
import com.google.gwt.maps.client.streetview.Pov;
import com.google.gwt.maps.client.streetview.StreetviewPanoramaWidget;
import com.google.gwt.maps.jsio.client.Binding;
import com.google.gwt.maps.jsio.client.Constructor;
import com.google.gwt.maps.jsio.client.JSFlyweightWrapper;
import com.google.gwt.user.client.Element;

/**
 * Wrapper for the GStreetviewPanorama object from the Maps API using JSIO.
 */
public interface PanoramaImpl extends JSFlyweightWrapper {

  PanoramaImpl impl = GWT.create(PanoramaImpl.class);

  @Binding
  void bind(JavaScriptObject jsoPeer, StreetviewPanoramaWidget panorama);

  void checkResize(JavaScriptObject jsoPeer);

  @Constructor("$wnd.GStreetviewPanorama")
  JavaScriptObject construct(Element container);

  @Constructor("$wnd.GStreetviewPanorama")
  JavaScriptObject construct(Element container, StreetviewPanoramaOptions options);

  void followLink(JavaScriptObject jsoPeer, double yaw);

  LatLng getLatLng(JavaScriptObject jsoPeer);

  String getPanoId(JavaScriptObject jsoPeer);

  Pov getPOV(JavaScriptObject jsoPeer);

  void hide(JavaScriptObject jsoPeer);

  boolean isHidden(JavaScriptObject jsoPeer);

  void panTo(JavaScriptObject jsoPeer, Pov pov, boolean longRoute);

  void remove(JavaScriptObject jsoPeer);

  void setLocationAndPOV(JavaScriptObject jsoPeer, LatLng latLng, Pov pov);

  void setPOV(JavaScriptObject jsoPeer, Pov pov);

  void setUserPhoto(JavaScriptObject jsoPeer, PhotoSpec photoSpec);

  void show(JavaScriptObject jsoPeer);
}
