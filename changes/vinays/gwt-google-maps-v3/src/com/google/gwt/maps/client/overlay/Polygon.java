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

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.maps.client.HasMap;
import com.google.gwt.maps.client.Map;
import com.google.gwt.maps.client.base.HasLatLng;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.mvc.MVCObject;
import com.google.gwt.maps.client.overlay.impl.PolygonImpl;

/**
 * This class implements {@link HasPolygon}.
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public class Polygon extends MVCObject implements HasPolygon {
  
  final private JavaScriptObject jso;
  
  public Polygon() {
    this(PolygonImpl.impl.construct());
  }
  
  public Polygon(final HasPolygonOptions options) {
    this(PolygonImpl.impl.construct(options.getJso()));
  }
  
  public Polygon(final JavaScriptObject jso) {
    super();
    this.jso = jso;
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.overlay.HasPolygon#getMap()
   */
  @Override
  public HasMap getMap() {
    return new Map(PolygonImpl.impl.getMap(jso));
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.overlay.HasPolygon#getPath()
   */
  @Override
  public List<HasLatLng> getPath() {
    final ArrayList<HasLatLng> path = new ArrayList<HasLatLng>();
    final JsArray<JavaScriptObject> jsPath = PolygonImpl.impl.getPath(jso);
    for (int i = 0; i < jsPath.length(); ++i) {
      path.add(new LatLng(jsPath.get(i)));
    }
    return path;
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.overlay.HasPolygon#getPaths()
   */
  @Override
  public List<List<HasLatLng>> getPaths() {
    final JsArray<JsArray<JavaScriptObject>> jsPaths = PolygonImpl.impl.getPaths(jso);
    final ArrayList<List<HasLatLng>> paths = new ArrayList<List<HasLatLng>>();
    for (int i = 0; i < jsPaths.length(); ++i) {
      JsArray<JavaScriptObject> jsPath = jsPaths.get(i);
      ArrayList<HasLatLng> path = new ArrayList<HasLatLng>();
      for (int j = 0; j < jsPath.length(); ++j) {
        path.add(new LatLng(jsPaths.get(i)));
      }
      paths.add(path);
    }
    return paths;
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.overlay.HasPolygon#setMap(com.google.gwt.maps.client.HasMap)
   */
  @Override
  public void setMap(HasMap map) {
    PolygonImpl.impl.setMap(jso, map.getJso());
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.overlay.HasPolygon#setOptions(com.google.gwt.maps.client.overlay.HasPolygonOptions)
   */
  @Override
  public void setOptions(HasPolygonOptions options) {
    PolygonImpl.impl.setOptions(jso, options.getJso());
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.overlay.HasPolygon#setPath(java.util.List)
   */
  @SuppressWarnings("unchecked")
  @Override
  public void setPath(List<HasLatLng> path) {
    JsArray<JavaScriptObject> jsPath = (JsArray<JavaScriptObject>) JavaScriptObject.createArray();
    for (HasLatLng point : path) {
      jsPath.push(point.getJso());
    }
    PolygonImpl.impl.setPath(jso, jsPath);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.overlay.HasPolygon#setPaths(java.util.List)
   */
  @SuppressWarnings("unchecked")
  @Override
  public void setPaths(List<List<HasLatLng>> paths) {
    final JsArray<JsArray<JavaScriptObject>> jsPaths
        = (JsArray<JsArray<JavaScriptObject>>) JavaScriptObject.createArray();
    for (List<HasLatLng> path : paths) {
      final JsArray<JavaScriptObject> jsPath
          = (JsArray<JavaScriptObject>) JavaScriptObject.createArray();
      for (HasLatLng point : path) {
        jsPath.push(point.getJso());
      }
      jsPaths.push(jsPath);
    }
    PolygonImpl.impl.setPaths(jso, jsPaths);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.HasJso#getJso()
   */
  @Override
  public JavaScriptObject getJso() {
    return jso;
  }

}
