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
 * Identifiers used to specify the placement of controls on the map. Controls
 * are positioned relative to other controls in the same layout position.
 * Controls that are added first are positioned closer to the edge of the map. <br />
 * 
 * <pre>
 * +-----------+ 
 * + TL  T  TR + 
 * +           + 
 * + L       R + 
 * +           +
 * + BL  B  BR + 
 * +-----------+
 * </pre>
 * 
 * Elements in the top or bottom row flow towards the middle. Elements at the
 * left or right sides flow downwards.<br />
 * 
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public enum ControlPosition {

  /**
   * Elements are positioned in the center of the bottom row.
   */
  BOTTOM,
  /**
   * Elements are positioned in the bottom left and flow towards the middle.
   * Elements are positioned to the right of the Google logo.
   */
  BOTTOM_LEFT,
  /**
   * Elements are positioned in the bottom right and flow towards the middle.
   * Elements are positioned to the left of the copyrights.
   */
  BOTTOM_RIGHT,
  /**
   * Elements are positioned on the left, below top-left elements, and flow
   * downwards.
   */
  LEFT,
  /**
   * Elements are positioned on the right, below top-right elements, and flow
   * downwards.
   */
  RIGHT,
  /**
   * Elements are positioned in the center of the top row.
   */
  TOP,
  /**
   * Elements are positioned in the top left and flow towards the middle.
   */
  TOP_LEFT,
  /**
   * Elements are positioned in the top right and flow towards the middle.
   */
  TOP_RIGHT;

}
