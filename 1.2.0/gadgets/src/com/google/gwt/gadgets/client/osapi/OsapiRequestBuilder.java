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
package com.google.gwt.gadgets.client.osapi;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.i18n.client.DateTimeFormat;

import java.util.Date;

/**
 * A superclass for all request builders.
 *
 * @param <T> Specific request builder implementation.
 */
public abstract class OsapiRequestBuilder<T extends OsapiRequestBuilder<?>>
    extends JavaScriptObject {

  private static final DateTimeFormat dtf = DateTimeFormat.getFormat("yyyy-MM-dd'T'hh:mm:ss.SSSS");

  /**
   * Required by {@link JavaScriptObject} policy.
   */
  protected OsapiRequestBuilder() {
  }

  /**
   * Sets the <code>groupId</code> parameter on the request. This parameter
   * specifies context for the built request.
   *
   * @param groupId group id to set.
   * @return this request builder.
   */
  public final native T setGroupId(String groupId) /*-{
    this.groupId = groupId;
    return this;
  }-*/;

  /**
   * When specified the container should only return items whose updated date
   * and time is equal to or more recent then the specified value.
   */
  public final T setUpdatedSince(Date date) {
    return nativeSet("updatedSince", dtf.format(date));
  }

  /**
   * Sets the <code>userId</code> parameter on the request. This parameter
   * specifies context for the built request.
   *
   * @param userId user id to set.
   * @return this request builder.
   */
  public final native T setUserId(String userId) /*-{
    this.userId = userId;
    return this;
  }-*/;

  /**
   * Helper method for setting key-value pair on the underlying JavaScript
   * object.
   */
  protected final native T nativeSet(String key, JsArrayString value) /*-{
    this[key] = value;
    return this;
  }-*/;

  /**
   * Helper method for setting key-value pair on the underlying JavaScript
   * object.
   */
  protected final native T nativeSet(String key, String value) /*-{
    this[key] = value;
    return this;
  }-*/;
}
