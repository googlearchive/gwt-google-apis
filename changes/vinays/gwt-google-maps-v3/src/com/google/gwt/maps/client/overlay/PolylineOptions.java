/* Copyright (c) 2010 Google Inc.
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
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.overlay.impl.PolylineOptionsImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements {@link HasPolylineOptions}.
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public class PolylineOptions implements HasPolylineOptions {

  final private JavaScriptObject jso;
  
  public PolylineOptions(JavaScriptObject jso) {
    this.jso = jso;
  }
  
  public PolylineOptions() {
    this(PolylineOptionsImpl.impl.construct());
  }
  
  @Override
  public List<LatLng> getPath() {
    List<LatLng> path = new ArrayList<LatLng>();
    JsArray<JavaScriptObject> pathJsArr = PolylineOptionsImpl.impl.getPath(jso);
    for (int i = 0; i < pathJsArr.length(); ++i) {
      path.add((LatLng) pathJsArr.get(i));
    }
    return path;
  }

  @Override
  public String getStrokeColor() {
    return PolylineOptionsImpl.impl.getStrokeColor(jso);
  }

  @Override
  public double getStrokeOpacity() {
    return PolylineOptionsImpl.impl.getStrokeOpacity(jso);
  }

  @Override
  public int getStrokeWeight() {
    return PolylineOptionsImpl.impl.getStrokeWeight(jso);
  }

  @SuppressWarnings("unchecked")
  @Override
  public void setPath(List<LatLng> path) {
    JsArray<JavaScriptObject> pathJsArr
        = (JsArray<JavaScriptObject>) JavaScriptObject.createArray();
    for (LatLng latLng : path) {
      pathJsArr.push(latLng);
    }
    PolylineOptionsImpl.impl.setPath(jso, pathJsArr);
  }

  @Override
  public void setStrokeColor(String strokeColor) {
    PolylineOptionsImpl.impl.setStrokeColor(jso, strokeColor);
  }

  @Override
  public void setStrokeOpacity(double strokeOpacity) {
    PolylineOptionsImpl.impl.setStrokeOpacity(jso, strokeOpacity);
  }

  @Override
  public void setStrokeWeight(int strokeWeight) {
    PolylineOptionsImpl.impl.setStrokeWeight(jso, strokeWeight);
  }

  @Override
  public JavaScriptObject getJso() {
    return jso;
  }

}
