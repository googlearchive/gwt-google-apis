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
import com.google.gwt.jsio.client.impl.Extractor;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.impl.JsUtil;

/**
 * 
 */
public final class Waypoint {

  // TODO: DELETE ME! (needs to function w/o)
  private static final Extractor __extractor = new Extractor() {
    public Object fromJS(JavaScriptObject jso) {
      throw new UnsupportedOperationException();
    }

    public JavaScriptObject toJS(Object o) {
      return ((Waypoint) o).jsoPeer;
    }
  };

  private final JavaScriptObject jsoPeer;

  public Waypoint(LatLng point) {
    jsoPeer = JsUtil.asJavaScriptObject(point);
  }

  public Waypoint(Placemark placemark) {
    jsoPeer = JsUtil.asJavaScriptObject(placemark);
  }

  public Waypoint(String address) {
    jsoPeer = JsUtil.asJavaScriptObject(address);
  }

}
