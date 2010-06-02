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
package com.google.gwt.maps.client.directions;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.directions.impl.DirectionsDistanceImpl;

/**
 * A representation of distance as a numeric value and a display string.
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public class DirectionsDistance implements HasTextAndValue {

  final private DirectionsDistanceImpl impl;
  final private JavaScriptObject jso;
  
  public DirectionsDistance(final DirectionsDistanceImpl impl, final JavaScriptObject jso) {
    this.impl = impl;
    this.jso = jso;
  }
  
  public DirectionsDistance(final JavaScriptObject jso) {
    this.impl = GWT.create(DirectionsDistanceImpl.class);
    this.jso = jso;
  }

  @Override
  public JavaScriptObject getJso() {
    return jso;
  }

  @Override
  public String getText() {
    return impl.getText(jso);
  }

  @Override
  public double getValue() {
    return impl.getValue(jso);
  }


}
