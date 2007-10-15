/*
 * Copyright 2007 Google Inc.
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
 * 
 */
public final class ControlPosition {

  /**
   * 
   */
  public static class ControlAnchor {
    private final int value;

    private ControlAnchor(int value) {
      this.value = value;
    }
  }

  public static final ControlAnchor BOTTOM_LEFT = new ControlAnchor(2);

  public static final ControlAnchor BOTTOM_RIGHT = new ControlAnchor(3);

  public static final ControlAnchor TOP_LEFT = new ControlAnchor(0);

  public static final ControlAnchor TOP_RIGHT = new ControlAnchor(1);

  static ControlPosition createPeer(JavaScriptObject jsoPeer) {
    return new ControlPosition(jsoPeer);
  }

  private final JavaScriptObject jsoPeer;

  // TODO: size or ints
  public ControlPosition(ControlAnchor anchor, int offsetX, int offsetY) {
    jsoPeer = ControlPositionImpl.impl.construct(anchor.value, new Size(offsetX, offsetY));
  }

  private ControlPosition(JavaScriptObject jsoPeer) {
    this.jsoPeer = jsoPeer;
  }

}
