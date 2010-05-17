/*
 * Copyright 2010 Google Inc.
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
package com.google.gwt.maps.client.streetview;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * A class representing a link between two {@link StreetviewLocation}s.
 *
 * This class may not be directly instantiated.
 *
 * @see "http://code.google.com/apis/maps/documentation/reference.html#GStreetviewLink"
 */
public class StreetviewLink extends JavaScriptObject {

  /**
   * Protected constructor required for JS overlay.
   */
  protected StreetviewLink() {
  }

  /**
   * Returns a localized string describing the neighbouring location.
   *
   * @return a localized string describing the neighbouring location.
   */
  public final native String getDescription() /*-{
    return this.description;
  }-*/;

  /**
   * Returns a unique identifier for the neighbouring panorama. This is stable
   * within a session but unstable across sessions.
   *
   * @return a unique identifier for the neighbouring panorama.
   */
  public final native String getPanoId() /*-{
    return this.panoId;
  }-*/;

  /**
   * Returns the yaw from the current location to the neighbouring location.
   *
   * @return the yaw from the current location to the neighbouring location.
   */
  public final native double getYaw() /*-{
    return this.yaw;
  }-*/;
}
