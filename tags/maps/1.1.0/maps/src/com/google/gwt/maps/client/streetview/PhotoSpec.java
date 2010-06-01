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
 * A class uniquely identifying a user contributed photo in Street View.
 *
 * @see "http://code.google.com/apis/maps/documentation/reference.html#GPhotoSpec"
 */
public class PhotoSpec extends JavaScriptObject {

  /**
   * Returns a new instance of {@link PhotoSpec} class.
   */
  public static PhotoSpec newInstance() {
    return JavaScriptObject.createObject().cast();
  }

  /**
   * Protected constructor required for JS overlay.
   */
  protected PhotoSpec() {
  }

  /**
   * Sets a string that uniquely identifies the photo given its repository.
   *
   * For Panoramio, this is the string representation of the panoramio
   * 'photo_id'. See the <a
   * href="http://www.panoramio.com/api/widget/api.html">panoramio API</a> for
   * information about how to obtain photo IDs given a lat/long bounding box.
   *
   * @param id a string that uniquely identifies the photo given its repository.
   * @return this {@link PhotoSpec} object, for convenience when using the
   *         Builder pattern.
   */
  public final native PhotoSpec setId(String id) /*-{
    this.id = id;
    return this;
  }-*/;

  /**
   * Sets the repository storing the photo. Currently, only the value
   * 'panoramio' is supported.
   *
   * @param repository string identifying the repository storing the photo.
   * @return this {@link PhotoSpec} object, for convenience when using the
   *         Builder pattern.
   */
  public final native PhotoSpec setRepository(String repository) /*-{
    this.repository = repository;
    return this;
  }-*/;
}
