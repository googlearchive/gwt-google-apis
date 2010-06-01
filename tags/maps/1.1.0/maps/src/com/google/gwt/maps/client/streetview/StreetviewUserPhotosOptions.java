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

import com.google.gwt.ajaxloader.client.ArrayHelper;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;

/**
 * This class represents the options available in the userPhotos viewing mode.
 *
 * @see "http://code.google.com/apis/maps/documentation/reference.html#GStreetviewUserPhotosOptions"
 */
public class StreetviewUserPhotosOptions extends JavaScriptObject {

  /**
   * Returns a new instance of StreetviewUserPhotosOptions class.
   */
  public static StreetviewUserPhotosOptions newInstance() {
    return JavaScriptObject.createObject().cast();
  }

  /**
   * Protected constructor required for JS overlay.
   */
  protected StreetviewUserPhotosOptions() {
  }

  /**
   * Specifies photo repositories to enable. By default all repositories are
   * enabled. Each string specifies one photo repository. Valid string values
   * are: 'panoramio', 'picasa' and 'flickr'.
   *
   * @param photoRepositories strings specifying photo repositories to enable.
   * @return this {@link StreetviewUserPhotosOptions} object, for convenience
   *         when using the Builder pattern.
   */
  public final StreetviewUserPhotosOptions setPhotoRepositories(
      String... photoRepositories) {
    return setPhotoRepositories(ArrayHelper.toJsArrayString(photoRepositories));
  }

  /**
   * Sets an array of strings specifying photo repositories to enable. By
   * default all repositories are enabled. Valid string values are: 'panoramio',
   * 'picasa' and 'flickr'.
   *
   * @param photoRepositories an array of strings specifying photo repositories
   *          to enable.
   * @return this {@link StreetviewUserPhotosOptions} object, for convenience
   *         when using the Builder pattern.
   */
  public final native StreetviewUserPhotosOptions setPhotoRepositories(
      JsArrayString photoRepositories) /*-{
    this.photoRepositories = photoRepositories;
    return this;
  }-*/;

}
