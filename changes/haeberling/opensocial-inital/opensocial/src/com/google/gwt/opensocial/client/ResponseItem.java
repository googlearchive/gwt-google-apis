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
package com.google.gwt.opensocial.client;

import com.google.gwt.core.client.JavaScriptObject;


/**
 * @see "http://code.google.com/apis/opensocial/docs/0.8/reference/#opensocial.ResponseItem"
 */
public class ResponseItem extends JavaScriptObject {
  /**
   * Error codes that a response item can return.
   */
  public enum Error {
    /**
     * This container does not support the request that was made.
     */
    NOT_IMPLEMENTED("notImplemented"),

    /**
     * The gadget does not have access to the requested data. To get access, use
     * {@link OpenSocialFeature#requestPermission(java.util.List, String, com.google.gwt.opensocial.client.util.OneArgumentFunction)}.
     */
    UNAUTHORIZED("unauthorized"),

    /**
     * The gadget can never have access to the requested data.
     */
    FORBIDDEN("forbidden"),

    /**
     * The request was invalid. Example: 'max' was -1.
     */
    BAD_REQUEST("badRequest"),

    /**
     * The request encountered an unexpected condition that prevented it from
     * fulfilling the request.
     */
    INTERNAL_ERROR("internalError"),

    /**
     * The gadget exceeded a quota on the request. Example quotas include a max
     * number of calls per day, calls per user per day, calls within a certain
     * time period and so forth.
     */
    LIMIT_EXCEEDED("limitExceeded");
    private String value = null;

    Error(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return this.value;
    }
  }

  protected ResponseItem() {
  }

  public final native Collection<? extends Object> getData() /*-{
    return this.getData();
  }-*/;

  public final native Collection<Activity> getDataAsActivities() /*-{
    return this.getData();
  }-*/;

  public final native Collection<Person> getDataAsPersons() /*-{
    return this.getData();
  }-*/;

  public final native String getErrorCode() /*-{
    return this.getErrorCode();
  }-*/;

  public final native String getErrorMessage() /*-{
    return this.getErrorMessage();
  }-*/;

  public final native DataRequest getOriginalDataRequest() /*-{
    return this.getOriginalDataRequest();
  }-*/;

  public final native boolean hadError() /*-{
    return this.hadError();
  }-*/;
}
