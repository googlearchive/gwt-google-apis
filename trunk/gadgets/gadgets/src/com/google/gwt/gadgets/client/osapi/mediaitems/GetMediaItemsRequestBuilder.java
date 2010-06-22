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
package com.google.gwt.gadgets.client.osapi.mediaitems;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.gadgets.client.osapi.CollectionRequestBuilder;
import com.google.gwt.gadgets.client.osapi.OsapiCollection;
import com.google.gwt.gadgets.client.osapi.OsapiRequest;

/**
 * Builder for requests returning {@link OsapiCollection} of {@link MediaItem}.
 */
public class GetMediaItemsRequestBuilder extends
    CollectionRequestBuilder<GetMediaItemsRequestBuilder> {

  /**
   * Returns new instance of {@link GetMediaItemsRequestBuilder}.
   *
   * @return New instance of {@link GetMediaItemsRequestBuilder}.
   */
  static GetMediaItemsRequestBuilder newInstance() {
    return JavaScriptObject.createObject().cast();
  }

  /**
   * Required by {@link JavaScriptObject} policy.
   */
  protected GetMediaItemsRequestBuilder() {
  }

  /**
   * Builds the request.
   *
   * @return {@link OsapiRequest} instance.
   */
  public final native OsapiRequest<OsapiCollection<MediaItem>> build() /*-{
    return $wnd.osapi.mediaitems.get(this);
  }-*/;

  /**
   * Sets the ID of the album whose MediaItems are to be returned. If no albumId
   * is provided, then the container should return media items from all albums.
   *
   * @param albumId The ID of the album whose MediaItems are to be returned.
   */
  public final native GetMediaItemsRequestBuilder setAlbumId(String albumId) /*-{
    this.albumId = albumId;
  }-*/;

  /**
   * Sets a list of MediaItem IDs specifying the MediaItems to retrieve.
   *
   * @param ids IDs specifying the MediaItems to retrieve.
   */
  public final GetMediaItemsRequestBuilder setIds(String... ids) {
    JsArrayString array = JavaScriptObject.createArray().cast();
    for (String id : ids) {
      array.push(id);
    }
    return nativeSet("id", array);
  }
}
