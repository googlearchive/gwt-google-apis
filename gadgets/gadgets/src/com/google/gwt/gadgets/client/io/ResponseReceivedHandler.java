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
package com.google.gwt.gadgets.client.io;

import com.google.gwt.user.client.ui.RootPanel;

import java.util.EventObject;

/**
 * The interface a caller must implement to receive a response to a
 * {@link com.google.gwt.gadgets.client.io.GadgetsIo#makeRequest}.
 *
 * @param <T> Type of parsed data of the response.
 */
public interface ResponseReceivedHandler<T> {

  /**
   * Encapsulates the response for the request.
   *
   * @param <T> Type of parsed data of the response.
   */
  @SuppressWarnings("serial")
  public static class ResponseReceivedEvent<T> extends EventObject {

    private final String url;
    private final Response<T> response;

    public ResponseReceivedEvent(Response<T> response, String url) {
      super(RootPanel.get());
      this.url = url;
      this.response = response;
    }

    /**
     * Returns the response.
     *
     * @return the response.
     */
    public Response<T> getResponse() {
      return this.response;
    }

    /**
     * Returns the URL related to received response.
     *
     * @return the URL related to received response.
     */
    public String getUrl() {
      return this.url;
    }
  }

  /**
   * Method to be invoked when a response is received.
   *
   * @param event event encapsulating the response.
   */
  void onResponseReceived(ResponseReceivedEvent<T> event);
}
