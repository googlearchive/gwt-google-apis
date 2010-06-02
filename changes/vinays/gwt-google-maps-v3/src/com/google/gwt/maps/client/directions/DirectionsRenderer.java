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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.HasMap;
import com.google.gwt.maps.client.Map;
import com.google.gwt.maps.client.base.ElementProvider;
import com.google.gwt.maps.client.base.HasElementProvider;
import com.google.gwt.maps.client.directions.impl.DirectionsRendererImpl;
import com.google.gwt.user.client.Element;

/**
 * 
 * 
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public class DirectionsRenderer implements HasDirectionsRenderer {

  final private JavaScriptObject jso;

  public DirectionsRenderer(JavaScriptObject jso) {
    super();
    this.jso = jso;
  }

  /**
   * Creates the renderer with the given options. Directions can be rendered on
   * a map (as visual overlays) or additionally on a <div> panel (as textual
   * instructions).
   */
  public DirectionsRenderer() {
    this(DirectionsRendererImpl.impl.construct());
  }

  /**
   * Creates the renderer with the given options. Directions can be rendered on
   * a map (as visual overlays) or additionally on a <div> panel (as textual
   * instructions).
   */
  public DirectionsRenderer(HasDirectionsRendererOptions options) {
    this(DirectionsRendererImpl.impl.construct(options.getJso()));
  }

  /*
   * (non-Javadoc)
   * 
   * @seecom.google.gwt.maps.client.directions.HasDirectionsRendererOptions#
   * getDirections()
   */
  @Override
  public HasDirectionsResult getDirections() {
    return new DirectionsResult(DirectionsRendererImpl.impl.getDirections(jso));
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.google.gwt.maps.client.directions.HasDirectionsRendererOptions#getMap()
   */
  @Override
  public HasMap getMap() {
    return new Map(DirectionsRendererImpl.impl.getMap(jso));
  }

  @Override
  public HasElementProvider getPanel() {
    return new ElementProvider((Element) DirectionsRendererImpl.impl
        .getPanel(jso));
  }

  /*
   * (non-Javadoc)
   * 
   * @seecom.google.gwt.maps.client.directions.HasDirectionsRendererOptions#
   * getRouteIndex()
   */
  @Override
  public int getRouteIndex() {
    return DirectionsRendererImpl.impl.getRouteIndex(jso);
  }

  /*
   * (non-Javadoc)
   * 
   * @seecom.google.gwt.maps.client.directions.HasDirectionsRendererOptions#
   * setDirections(com.google.gwt.maps.client.directions.HasDirectionsResult)
   */
  @Override
  public void setDirections(HasDirectionsResult directions) {
    DirectionsRendererImpl.impl.setDirections(jso, directions.getJso());
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.google.gwt.maps.client.directions.HasDirectionsRendererOptions#setMap
   * (com.google.gwt.maps.client.HasMap)
   */
  @Override
  public void setMap(HasMap map) {
    DirectionsRendererImpl.impl.setMap(jso, map.getJso());
  }

  @Override
  public void setPanel(HasElementProvider panel) {
    DirectionsRendererImpl.impl.setPanel(jso, panel.get());
  }

  /*
   * (non-Javadoc)
   * 
   * @seecom.google.gwt.maps.client.directions.HasDirectionsRendererOptions#
   * setRouteIndex(int)
   */
  @Override
  public void setRouteIndex(int routeIndex) {
    DirectionsRendererImpl.impl.setRouteIndex(jso, routeIndex);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.google.gwt.maps.client.HasJso#getJso()
   */
  @Override
  public JavaScriptObject getJso() {
    return jso;
  }

}
