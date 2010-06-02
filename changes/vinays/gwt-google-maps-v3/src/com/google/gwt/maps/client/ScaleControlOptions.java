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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.impl.ControlPositionImpl;
import com.google.gwt.maps.client.impl.ScaleControlOptionsImpl;
import com.google.gwt.maps.client.impl.ScaleControlStyleImpl;

/**
 * This class implements {@link HasScaleControlOptions}.
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public class ScaleControlOptions implements HasScaleControlOptions {

  final private JavaScriptObject jso;
  final private ControlPositionImpl controlPositionImpl;
  final private ScaleControlStyleImpl scaleControlStyleImpl;
  
  public ScaleControlOptions(JavaScriptObject jso, ControlPositionImpl controlPositionImpl,
      ScaleControlStyleImpl scaleControlStyleImpl) {
    this.jso = jso;
    this.controlPositionImpl = controlPositionImpl;
    this.scaleControlStyleImpl = scaleControlStyleImpl;
  }
  
  public ScaleControlOptions(ControlPositionImpl controlPositionImpl,
      ScaleControlStyleImpl scaleControlStyleImpl) {
    this(ScaleControlOptionsImpl.impl.construct(), controlPositionImpl, scaleControlStyleImpl);
  }
  
  @Override
  public void setPosition(ControlPosition position) {
    ScaleControlOptionsImpl.impl.setPosition(jso, controlPositionImpl.getValue(position));
  }

  @Override
  public void setStyle(ScaleControlStyle style) {
    ScaleControlOptionsImpl.impl.setStyle(jso, scaleControlStyleImpl.getValue(style));
  }

  @Override
  public JavaScriptObject getJso() {
    return jso;
  }

}
