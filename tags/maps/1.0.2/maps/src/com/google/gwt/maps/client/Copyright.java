/*
 * Copyright 2008 Google Inc.
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
package com.google.gwt.maps.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.maps.client.impl.CopyrightImpl;

/**
 * Contains information about which copyright message applies to a region of the
 * map given by a rectangle and a zoom level. You need this object only if you
 * implement custom map types or tile layers.
 */
public final class Copyright {

  /**
   * Used to create a new Overlay by wrapping an existing GOverlay object. This
   * method is invoked by the jsio library.
   * 
   * @param jsoPeer GOverlay object to wrap.
   * @return a new instance of Overlay.
   */
  @SuppressWarnings("unused")
  private static Copyright createPeer(JavaScriptObject jsoPeer) {
    return new Copyright(jsoPeer);
  }

  private final JavaScriptObject jsoPeer;

  /**
   * Creates a new copyright object with the given attributes.
   * 
   * @param id a unique id
   * @param bounds bounds to which this copyright applies
   * @param minZoomLevel minimum zoom at which to display
   * @param text copyright text
   */
  public Copyright(int id, LatLngBounds bounds, int minZoomLevel, String text) {
    jsoPeer = CopyrightImpl.impl.construct(id, bounds, minZoomLevel, text);
  }

  private Copyright(JavaScriptObject jsoPeer) {
    this.jsoPeer = jsoPeer;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (obj instanceof Copyright) {
      Copyright c = (Copyright) obj;
      if (c.getId() == getId()) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns the bounds to which this copyright applies.
   * 
   * @return the bounds to which this copyright applies.
   */
  public LatLngBounds getBounds() {
    return CopyrightImpl.impl.getBounds(jsoPeer);
  }

  /**
   * Returns the unique id of this copyright.
   * 
   * @return the unique id of this copyright.
   */
  public int getId() {
    return CopyrightImpl.impl.getId(jsoPeer);
  }

  /**
   * Returns the minimum zoom level at which this copyright displays.
   * 
   * @return the minimum zoom level at which this copyright displays.
   */
  public int getMinZoomLevel() {
    return CopyrightImpl.impl.getMinZoomLevel(jsoPeer);
  }

  /**
   * Returns the text of this copyright.
   * 
   * @return the text of the copyright
   */
  public String getText() {
    return CopyrightImpl.impl.getText(jsoPeer);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + getId();
    result = prime * result
        + (getBounds() == null ? 0 : getBounds().hashCode());
    result = prime * result + getMinZoomLevel();
    result = prime * result + (getText() == null ? 0 : getText().hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuffer b = new StringBuffer();
    b.append("ID=" + getId());
    b.append(" : " + (getBounds() == null ? "" : getBounds()));
    b.append(" : ZOOM=" + getMinZoomLevel());
    b.append(" : " + (getText() == null ? "" : getText()));
    return b.toString();
  }
}
