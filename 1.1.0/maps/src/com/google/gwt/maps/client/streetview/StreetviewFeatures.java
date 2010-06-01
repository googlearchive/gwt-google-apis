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
 * A class specifying which major viewing modes are enabled. By default all
 * major viewing modes are enabled.
 *
 * @see "http://code.google.com/apis/maps/documentation/reference.html#GStreetviewFeatures"
 */
public class StreetviewFeatures extends JavaScriptObject {

  /**
   * Returns a new instance of {@link StreetviewFeatures} class.
   */
  public static StreetviewFeatures newInstance() {
    return JavaScriptObject.createObject().cast();
  }

  /**
   * Protected constructor required for JS overlay.
   */
  protected StreetviewFeatures() {
  }

  /**
   * The streetView viewing mode shows panoramic images taken from street level.
   * This is the standard viewing mode of {@link StreetviewPanoramaWidget}
   *
   * @param streetView set to <code>false</code> to disable streetView viewing
   *          mode.
   * @return this {@link StreetviewFeatures} object, for convenience when using
   *         the Builder pattern.
   */
  public final native StreetviewFeatures setStreetView(boolean streetView) /*-{
    this.streetView = streetView;
    return this;
  }-*/;

  /**
   * The userPhotos viewing mode allows viewing of user generated photos. When
   * both this feature and the streetView feature is enabled, photos will
   * automatically appear when available as thumbnails in the top right corner
   * of the Street View panorama. Photos may also be displayed programmatically
   * using the {@link StreetviewPanoramaWidget#setUserPhoto(PhotoSpec)}
   * function.
   *
   * @param userPhotos set to <code>false</code> to disable userPhoto viewing
   *          mode.
   * @return this {@link StreetviewFeatures} object, for convenience when using
   *         the Builder pattern.
   */
  public final native StreetviewFeatures setUserPhotos(boolean userPhotos) /*-{
    this.userPhotos = userPhotos;
    return this;
  }-*/;
}
