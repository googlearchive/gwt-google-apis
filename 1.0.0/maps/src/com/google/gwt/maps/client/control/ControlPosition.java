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
package com.google.gwt.maps.client.control;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.geom.Size;
import com.google.gwt.maps.client.impl.ControlPositionImpl;

/**
 * This class described the position of a control in the map view. It consists
 * of a corner relative to where the control is positioned, and an offset that
 * determines this position. It can be passed as optional argument position to
 * the method {@link com.google.gwt.maps.client.MapWidget#addControl(Control)},
 * and it is returned from method
 * {@link com.google.gwt.maps.client.control.Control.CustomControl#getDefaultPosition()}.
 */
public final class ControlPosition {

  static ControlPosition createPeer(JavaScriptObject jsoPeer) {
    return new ControlPosition(jsoPeer);
  }

  @SuppressWarnings("unused")
  private final JavaScriptObject jsoPeer;

  /**
   * Create a new ControlPosition.
   * 
   * @param anchor specifies which corner of the screen the X and Y offsets are
   *          computed from.
   * @param offsetX number of horizontal pixels offset from the corner.
   * @param offsetY number of vertical pixels offset from the corner.
   */
  public ControlPosition(ControlAnchor anchor, int offsetX, int offsetY) {
    jsoPeer = ControlPositionImpl.impl.construct(anchor.getValue(),
        Size.newInstance(offsetX, offsetY));
  }

  /**
   * Wrap an existing GControlPosition JavaScriptObject to make a new
   * ControlPosition instance.
   * 
   * @param jsoPeer
   */
  private ControlPosition(JavaScriptObject jsoPeer) {
    this.jsoPeer = jsoPeer;
  }

}
