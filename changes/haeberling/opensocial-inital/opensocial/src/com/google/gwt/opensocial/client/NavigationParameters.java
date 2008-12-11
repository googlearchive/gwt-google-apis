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
 * Parameters used by RequestShareApp to instruct the container on where to go
 * after the request is made.
 *
 * @see "http://code.google.com/apis/opensocial/docs/0.8/reference/#opensocial.NavigationParameters"
 */
public class NavigationParameters extends JavaScriptObject {
  protected NavigationParameters() {
  }

  /**
   * All of the fields that NavigationParameters can have.
   */
  public enum Field {
    /**
     * A string representing the owner id.
     */
    OWNER("OWNER"),
    /**
     * An optional list of parameters passed to the gadget once the new view,
     * with the new owner, has been loaded.
     */
    PARAMETERS("PARAMETERS"),
    /**
     * The <code>gadgets.views.View</code> to navigate to.
     */
    VIEW("VIEW");

    private String value = null;

    private Field(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return this.value;
    }
  }

  /**
   * The destinations available for navigation in
   * {@link com.google.gwt.opensocial.client.OpenSocialFeature#requestShareApp(com.google.gwt.opensocial.client.DataRequest.Id, String, com.google.gwt.opensocial.client.util.OneArgumentFunction)}
   * and
   * {@link com.google.gwt.opensocial.client.OpenSocialFeature#requestSendMessage(com.google.gwt.opensocial.client.DataRequest.Id, String, com.google.gwt.opensocial.client.util.OneArgumentFunction)}.
   */
  public enum DestinationType {
    RECIPIENT_DESTINATION("RECIPIENT_DESTINATION"),
    VIEWER_DESTINATION("VIEWER_DESTINATION");

    private String value = null;

    private DestinationType(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return this.value;
    }
  }

  /**
   * Gets the NavigationParameters' data that's associated with the specified
   * key.
   */
  public String getField(Field key) {
    return nativeGetField(key.toString());
  }
  
  /**
   * Sets data for this NavigationParameters associated with the given key.
   */
  public final void setField(Field key, String data) {
    nativeSetField(key.toString(), data);
  }
  
  private native String nativeGetField(String value) /*-{
    return this.getField(value);
  }-*/;
  
  public final native void nativeSetField(String key, String data) /*-{
  	this.setField(key, data);
  }-*/;
}
