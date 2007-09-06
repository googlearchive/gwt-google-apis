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
package com.google.gwt.maps.client.overlay;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.impl.MarkerManagerOptionsImpl;

public final class MarkerManagerOptions {

  private final JavaScriptObject jsoPeer;

  public MarkerManagerOptions() {
    jsoPeer = MarkerManagerOptionsImpl.impl.construct();
  }

  public void setBorderPadding(int borderPadding) {
    MarkerManagerOptionsImpl.impl.setBorderPadding(jsoPeer, borderPadding);
  }

  public void setMaxZoom(int maxZoom) {
    MarkerManagerOptionsImpl.impl.setMaxZoom(jsoPeer, maxZoom);
  }

  public void setTrackMarkers(boolean trackMarkers) {
    MarkerManagerOptionsImpl.impl.setTrackMarkers(jsoPeer, trackMarkers);
  }
}
