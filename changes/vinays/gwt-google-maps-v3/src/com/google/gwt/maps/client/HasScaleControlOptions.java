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

/**
 * Options for the rendering of the scale control.
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public interface HasScaleControlOptions extends HasJso {

  /**
   * Position id. Used to specify the position of the control on the map. The
   * default position is BOTTOM_LEFT.
   */
  void setPosition(ControlPosition position);
  
  /**
   * Style id. Used to select what style of scale control to display.
   */
  void setStyle(ScaleControlStyle style);
  
}
