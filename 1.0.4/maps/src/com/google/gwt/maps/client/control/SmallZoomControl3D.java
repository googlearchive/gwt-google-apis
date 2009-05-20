/*
 * Copyright 2009 Google Inc.
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
 * Creates a control with buttons to pan in four directions, and zoom in and
 * zoom out in a 3D style.
 */
public final class SmallZoomControl3D extends Control {

  /**
   * Creates a control with buttons to pan in four directions, and zoom in and
   * zoom out in a 3D style.
   */
  public SmallZoomControl3D() {
    super(ControlImpl.impl.createSmallZoomControl3D());
  }

}
