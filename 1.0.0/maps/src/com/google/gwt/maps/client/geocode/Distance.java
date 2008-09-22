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
package com.google.gwt.maps.client.geocode;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * The object contains two fields: a number called indicating the numeric value
 * of the distance (in meters), and a string containing a localized string
 * representation of the distance in the units that are predominant in the
 * starting country of this set of directions.
 */
public class Distance extends JavaScriptObject {

  protected Distance() {
    // Protected constructor required for JS overlay.
  }

  /**
   * Returns the distance in the units that are predominant in the starting
   * country of this set of directions.
   * 
   * @return the distance in the units that are predominant in the starting
   *         country of this set of directions.
   */
  public final native String inLocalizedUnits() /*-{
    return this.html;
  }-*/;

  /**
   * Returns the distance measured in meters.
   * 
   * @return the distance measured in meters.
   */
  public final native double inMeters() /*-{
    return this.meters;
  }-*/;
}
