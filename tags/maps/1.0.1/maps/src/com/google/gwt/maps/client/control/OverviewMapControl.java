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

import com.google.gwt.maps.client.impl.ControlImpl;

/**
 * Creates a collapsible overview mini-map in the corner of the main map for
 * reference location and navigation (through dragging). The GOverviewMapControl
 * creates an overview map with a one-pixel black border.
 */
public final class OverviewMapControl extends Control {

  /**
   * Creates a collapsible overview mini-map in the corner of the main map for
   * reference location and navigation (through dragging). The
   * GOverviewMapControl creates an overview map with a one-pixel black border.
   */
  public OverviewMapControl() {
    super(ControlImpl.impl.createOverviewMapControl());
  }

}
