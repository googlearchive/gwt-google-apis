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
package com.google.gwt.maps.client.geocode;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.impl.DirectionQueryOptionsImpl;
import com.google.gwt.user.client.ui.Widget;

public final class DirectionQueryOptions {

  private final JavaScriptObject jsoPeer;

  final MapWidget map;

  final Widget panel;

  public DirectionQueryOptions() {
    this(null, null);
  }

  public DirectionQueryOptions(MapWidget map) {
    this(map, null);
  }

  public DirectionQueryOptions(MapWidget map, DirectionsPanel panel) {
    this.map = map;
    this.panel = panel;
    this.jsoPeer = DirectionQueryOptionsImpl.impl.construct();
  }

  public void setLocale(String locale) {
    DirectionQueryOptionsImpl.impl.setLocale(jsoPeer, locale);
  }

  public void setPreserveViewport(boolean preserveViewport) {
    DirectionQueryOptionsImpl.impl.setPreserveViewport(jsoPeer,
        preserveViewport);
  }

  public void setRetrievePolyline(boolean retrievePolyline) {
    DirectionQueryOptionsImpl.impl.setRetrievePolyline(jsoPeer,
        retrievePolyline);
  }

  public void setRetrieveSteps(boolean retrieveSteps) {
    DirectionQueryOptionsImpl.impl.setRetrieveSteps(jsoPeer, retrieveSteps);
  }
}