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
 * Creates a standard map type control for selecting and switching between
 * supported map types via buttons.
 */
public final class MapTypeControl extends Control {

  /**
   * Create a new standard map type control.
   */
  public MapTypeControl() {
    super(ControlImpl.impl.createMapTypeControl());
  }
  
  /**
    * Creates a GMapTypeControl, using the short (alt) names for the map types if
   * <code>useShortNames</code> is set to <code>true</code>, and the long
   * names by default.
   * 
   * @param useShortNames if <code>true</code> then short (alt) names are used
   *          for the map types.
   */
  public MapTypeControl(boolean useShortNames) {
    super(ControlImpl.impl.createMapTypeControl(useShortNames));
  }
}
