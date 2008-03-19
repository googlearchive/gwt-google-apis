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
import com.google.gwt.maps.client.impl.DistanceImpl;

/**
 * The object contains two fields: a number called indicating the numeric value
 * of the distance (in meters), and a string containing a localized string
 * representation of the distance in the units that are predominant in the
 * starting country of this set of directions.
 */
public final class Distance {

  static Distance createPeer(JavaScriptObject jsoPeer) {
    return new Distance(jsoPeer);
  }

  private final JavaScriptObject jsoPeer;

  private Distance(JavaScriptObject jsoPeer) {
    this.jsoPeer = jsoPeer;
  }

  /**
   * @return the distance in the units that are predominant in the starting
   *         country of this set of directions.
   */
  public String inLocalizedUnits() {
    return DistanceImpl.impl.getHtml(jsoPeer);
  }

  /**
   * @return the distance measured in meters.
   */
  public int inMeters() {
    return DistanceImpl.impl.getMeters(jsoPeer);
  }
}
