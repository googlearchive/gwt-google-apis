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
import com.google.gwt.maps.client.geom.LatLng;

/**
 * A class representing the options passed to the
 * {@link StreetviewPanoramaWidget} constructor.
 *
 * @see "http://code.google.com/apis/maps/documentation/reference.html#GStreetviewPanoramaOptions"
 */
public class StreetviewPanoramaOptions extends JavaScriptObject {

  /**
   * Returns a new instance of {@link StreetviewPanoramaOptions} class.
   */
  public static StreetviewPanoramaOptions newInstance() {
    return JavaScriptObject.createObject().cast();
  }

  /**
   * Protected constructor required for JS overlay.
   */
  protected StreetviewPanoramaOptions() {
  }

  /**
   * By default, the panorama viewer features a full-screen control in the upper
   * right corner. To exclude the full-screen control, set this flag to
   * <code>false</code>.
   *
   * @param enableFullScreen if <code>false</code> full-screen control will be
   *          disabled, it will be enabled otherwise.
   * @return this {@link StreetviewPanoramaOptions} object, for convenience when
   *         using the Builder pattern.
   */
  public final native StreetviewPanoramaOptions setEnableFullScreen(
      boolean enableFullScreen) /*-{
    this.enableFullScreen = enableFullScreen;
    return this;
  }-*/;

  /**
   * By default, the panorama viewer will enable all major viewing modes. This
   * flag may be used to selectively enable or disable certain modes.
   *
   * @param features the {@link StreetviewFeatures} instance to specify viewing
   *          modes to enable.
   * @return this {@link StreetviewPanoramaOptions} object, for convenience when
   *         using the Builder pattern.
   */
  public final native StreetviewPanoramaOptions setFeatures(
      StreetviewFeatures features) /*-{
    this.features = features;
    return this;
  }-*/;

  /**
   * Sets the {@link LatLng} at which to open the flash viewer.
   *
   * @param latLng the {@link LatLng} at which to open the flash viewer.
   * @return this {@link StreetviewPanoramaOptions} object, for convenience when
   *         using the Builder pattern.
   */
  public final native StreetviewPanoramaOptions setLatLng(LatLng latLng) /*-{
    this.latlng = latLng;
    return this;
  }-*/;

  /**
   * The camera orientation with which to open the flash viewer.
   *
   * @param pov the camera orientation with which to open the flash viewer.
   * @return this {@link StreetviewPanoramaOptions} object, for convenience when
   *         using the Builder pattern.
   */
  public final native StreetviewPanoramaOptions setPov(Pov pov) /*-{
    this.pov = pov;
    return this;
  }-*/;

  /**
   * Specifies options for displayed user photos. This optional property is
   * ignored unless the userPhotos feature is enabled using
   * {@link StreetviewPanoramaOptions#setFeatures(StreetviewFeatures)} method.
   *
   * @param userPhotosOptions the {@link StreetviewUserPhotosOptions} instance
   *          to specify user photos options.
   * @return this {@link StreetviewPanoramaOptions} object, for convenience when
   *         using the Builder pattern.
   */
  public final native StreetviewPanoramaOptions setUserPhotosOptions(
      StreetviewUserPhotosOptions userPhotosOptions) /*-{
    this.userPhotosOptions = userPhotosOptions;
    return this;
  }-*/;
}
