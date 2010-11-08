/* Copyright (c) 2010 Vinay Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.gwt.maps.client.overlay;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.maps.client.HasMap;
import com.google.gwt.maps.client.Map;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.mvc.MVCObject;
import com.google.gwt.maps.client.overlay.impl.PolylineImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements {@link HasPolyline}.
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public class Polyline extends MVCObject implements HasPolyline {

  private JavaScriptObject jso;

  public Polyline(JavaScriptObject jso) {
    this.jso = jso;
  }

  public Polyline() {
    this(PolylineImpl.impl.construct());
  }

  public Polyline(HasPolylineOptions options) {
    this(PolylineImpl.impl.construct(options.getJso()));
  }

  @Override
  public HasMap getMap() {
    return new Map(PolylineImpl.impl.getMap(jso));
  }

  @Override
  public List<LatLng> getPath() {
    List<LatLng> path = new ArrayList<LatLng>();
    JsArray<JavaScriptObject> pathJsArr = PolylineImpl.impl.getPath(jso);
    for (int i = 0; i < pathJsArr.length(); ++i) {
      path.add((LatLng) pathJsArr.get(i));
    }
    return path;
  }

  @Override
  public void setMap(HasMap map) {
    PolylineImpl.impl.setMap(jso, map.getJso());
  }

  @Override
  public void setOptions(HasPolylineOptions options) {
    PolylineImpl.impl.setOptions(jso, options.getJso());
  }

  @SuppressWarnings("unchecked")
  @Override
  public void setPath(List<LatLng> path) {
    JsArray<JavaScriptObject> pathJsArr = (JsArray<JavaScriptObject>) JavaScriptObject
        .createArray();
    for (LatLng latLng : path) {
      pathJsArr.push(latLng);
    }
    PolylineImpl.impl.setPath(jso, pathJsArr);
  }

}
