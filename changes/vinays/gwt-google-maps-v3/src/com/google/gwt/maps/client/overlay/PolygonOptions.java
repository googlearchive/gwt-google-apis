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
import com.google.gwt.maps.client.Map;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.overlay.impl.PolygonOptionsImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements {@link HasPolygonOptions}.
 *
 */
public class PolygonOptions implements HasPolygonOptions {
  
  final private JavaScriptObject jso;
  
  public PolygonOptions() {
    super();
    jso = PolygonOptionsImpl.impl.construct();
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.overlay.HasPolygonOptions#getFillColor()
   */
  @Override
  public String getFillColor() {
    return PolygonOptionsImpl.impl.getFillColor(jso);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.overlay.HasPolygonOptions#getFillOpacity()
   */
  @Override
  public double getFillOpacity() {
    return PolygonOptionsImpl.impl.getFillOpacity(jso);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.overlay.HasPolygonOptions#getMap()
   */
  @Override
  public Map getMap() {
    return PolygonOptionsImpl.impl.getMap(jso);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.overlay.HasPolygonOptions#getPaths()
   */
  @Override
  public List<List<LatLng>> getPaths() {
    final JsArray<JsArray<JavaScriptObject>> jsPaths = PolygonOptionsImpl.impl.getPaths(jso);
    final ArrayList<List<LatLng>> paths = new ArrayList<List<LatLng>>();
    for (int i = 0; i < jsPaths.length(); ++i) {
      JsArray<JavaScriptObject> jsPath = jsPaths.get(i);
      ArrayList<LatLng> path = new ArrayList<LatLng>();
      for (int j = 0; j < jsPath.length(); ++j) {
        path.add((LatLng) jsPath.get(i));
      }
      paths.add(path);
    }
    return paths;
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.overlay.HasPolygonOptions#getStrokeColor()
   */
  @Override
  public String getStrokeColor() {
    return PolygonOptionsImpl.impl.getStrokeColor(jso);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.overlay.HasPolygonOptions#getStrokeOpacity()
   */
  @Override
  public double getStrokeOpacity() {
    return PolygonOptionsImpl.impl.getStrokeOpacity(jso);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.overlay.HasPolygonOptions#getStrokeWeight()
   */
  @Override
  public int getStrokeWeight() {
    return PolygonOptionsImpl.impl.getStrokeWeight(jso);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.overlay.HasPolygonOptions#getzIndex()
   */
  @Override
  public int getZIndex() {
    return PolygonOptionsImpl.impl.getZIndex(jso);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.overlay.HasPolygonOptions#setFillColor(java.lang.String)
   */
  @Override
  public void setFillColor(String fillColor) {
    PolygonOptionsImpl.impl.setFillColor(jso, fillColor);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.overlay.HasPolygonOptions#setFillOpacity(double)
   */
  @Override
  public void setFillOpacity(double fillOpacity) {
    PolygonOptionsImpl.impl.setFillOpacity(jso, fillOpacity);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.overlay.HasPolygonOptions#setMap(com.google.gwt.maps.client.HasMap)
   */
  @Override
  public void setMap(Map map) {
    PolygonOptionsImpl.impl.setMap(jso, map);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.overlay.HasPolygonOptions#setPaths(java.util.List)
   */
  @SuppressWarnings("unchecked")
  @Override
  public void setPaths(List<List<LatLng>> paths) {
    final JsArray<JsArray<JavaScriptObject>> jsPaths
        = (JsArray<JsArray<JavaScriptObject>>) JavaScriptObject.createArray();
    for (List<LatLng> path : paths) {
      final JsArray<JavaScriptObject> jsPath
          = (JsArray<JavaScriptObject>) JavaScriptObject.createArray();
      for (LatLng point : path) {
        jsPath.push(point);
      }
      jsPaths.push(jsPath);
    }
    PolygonOptionsImpl.impl.setPaths(jso, jsPaths);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.overlay.HasPolygonOptions#setStrokeColor(java.lang.String)
   */
  @Override
  public void setStrokeColor(String strokeColor) {
    PolygonOptionsImpl.impl.setStrokeColor(jso, strokeColor);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.overlay.HasPolygonOptions#setStrokeOpacity(double)
   */
  @Override
  public void setStrokeOpacity(double strokeOpacity) {
    PolygonOptionsImpl.impl.setStrokeOpacity(jso, strokeOpacity);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.overlay.HasPolygonOptions#setStrokeWeight(int)
   */
  @Override
  public void setStrokeWeight(int strokeWeight) {
    PolygonOptionsImpl.impl.setStrokeWeight(jso, strokeWeight);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.overlay.HasPolygonOptions#setzIndex(int)
   */
  @Override
  public void setZIndex(int zIndex) {
    PolygonOptionsImpl.impl.setZIndex(jso, zIndex);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.HasJso#getJso()
   */
  @Override
  public JavaScriptObject getJso() {
    return null;
  }

}
