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
package com.google.gwt.gadgets.client.osapi.albums;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.gadgets.client.osapi.CollectionRequestBuilder;
import com.google.gwt.gadgets.client.osapi.OsapiCollection;
import com.google.gwt.gadgets.client.osapi.OsapiRequest;

/**
 * Builder for requests returning {@link OsapiCollection} of {@link Album}.
 */
public class GetAlbumsRequestBuilder extends
    CollectionRequestBuilder<GetAlbumsRequestBuilder> {

  /**
   * Returns new instance of {@link GetAlbumsRequestBuilder}.
   *
   * @return New instance of {@link GetAlbumsRequestBuilder}.
   */
  static GetAlbumsRequestBuilder newInstance() {
    return JavaScriptObject.createObject().cast();
  }

  /**
   * Required by {@link JavaScriptObject} policy.
   */
  protected GetAlbumsRequestBuilder() {
  }

  /**
   * Builds the request.
   *
   * @return {@link OsapiRequest} instance.
   */
  public final native OsapiRequest<OsapiCollection<Album>> build() /*-{
    return $wnd.osapi.albums.get(this);
  }-*/;

  /**
   * Sets a list of IDs specifying the {@link Album}s to retrieve.
   *
   * @param ids A list of IDs.
   */
  public final GetAlbumsRequestBuilder setIds(String... ids) {
    JsArrayString array = JavaScriptObject.createArray().cast();
    for (String id : ids) {
      array.push(id);
    }
    return nativeSet("id", array);
  }
}
