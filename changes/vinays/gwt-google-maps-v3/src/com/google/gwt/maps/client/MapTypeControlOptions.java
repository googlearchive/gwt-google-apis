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
package com.google.gwt.maps.client;

import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.maps.client.impl.ControlPositionImpl;
import com.google.gwt.maps.client.impl.MapTypeControlOptionsImpl;
import com.google.gwt.maps.client.impl.MapTypeControlStyleImpl;

/**
 * This class implements {@link HasMapTypeControlOptions}.
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public class MapTypeControlOptions implements HasMapTypeControlOptions {

  final private JavaScriptObject jso;
  final private ControlPositionImpl controlPositionImpl;
  final private MapTypeControlStyleImpl mapTypeControlStyleImpl;
  
  public MapTypeControlOptions(JavaScriptObject jso,
      ControlPositionImpl controlPositionImpl,
      MapTypeControlStyleImpl mapTypeControlStyleImpl) {
    this.jso = jso;
    this.controlPositionImpl = controlPositionImpl;
    this.mapTypeControlStyleImpl = mapTypeControlStyleImpl;
  }

  public MapTypeControlOptions(ControlPositionImpl controlPositionImpl,
      MapTypeControlStyleImpl mapTypeControlStyleImpl) {
    this(MapTypeControlOptionsImpl.impl.construct(), controlPositionImpl, mapTypeControlStyleImpl);
  }
  
  @Override
  public void setMapTypeIds(List<String> mapTypeIds) {
    JsArrayString ids = (JsArrayString) JavaScriptObject.createArray();
    MapTypeControlOptionsImpl.impl.setMapTypeIds(ids, ids);
  }

  @Override
  public void setPosition(ControlPosition position) {
    MapTypeControlOptionsImpl.impl.setPosition(jso,
        controlPositionImpl.getValue(position));
  }

  @Override
  public void setStyle(MapTypeControlStyle style) {
    MapTypeControlOptionsImpl.impl.setStyle(jso,
        mapTypeControlStyleImpl.getValue(style));
  }

  @Override
  public JavaScriptObject getJso() {
    return jso;
  }

}
