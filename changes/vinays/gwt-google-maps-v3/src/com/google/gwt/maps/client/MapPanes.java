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
import com.google.gwt.maps.client.impl.MapPanesImpl;
import com.google.gwt.user.client.Element;

/**
 * 
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public class MapPanes implements HasMapPanes {
  
  private final JavaScriptObject jso;

  public MapPanes(JavaScriptObject jso) {
    super();
    this.jso = jso;
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.HasMapPanes#getFloatPane()
   */
  @Override
  public Element getFloatPane() {
    return MapPanesImpl.impl.getFloatPane(jso);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.HasMapPanes#getFloatShadow()
   */
  @Override
  public Element getFloatShadow() {
    return MapPanesImpl.impl.getFloatShadow(jso);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.HasMapPanes#getMapPane()
   */
  @Override
  public Element getMapPane() {
    return MapPanesImpl.impl.getMapPane(jso);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.HasMapPanes#getOverlayImage()
   */
  @Override
  public Element getOverlayImage() {
    return MapPanesImpl.impl.getOverlayImage(jso);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.HasMapPanes#getOverlayLayer()
   */
  @Override
  public Element getOverlayLayer() {
    return MapPanesImpl.impl.getOverlayLayer(jso);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.HasMapPanes#getOverlayMouseTarget()
   */
  @Override
  public Element getOverlayMouseTarget() {
    return MapPanesImpl.impl.getOverlayMouseTarget(jso);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.HasMapPanes#getOverlayShadow()
   */
  @Override
  public Element getOverlayShadow() {
    return MapPanesImpl.impl.getOverlayShadow(jso);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.HasJso#getJso()
   */
  @Override
  public JavaScriptObject getJso() {
    return jso;
  }

}
