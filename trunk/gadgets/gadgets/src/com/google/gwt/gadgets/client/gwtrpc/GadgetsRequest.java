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
package com.google.gwt.gadgets.client.gwtrpc;

import com.google.gwt.gadgets.client.io.ResponseReceivedHandler.ResponseReceivedEvent;
import com.google.gwt.http.client.Header;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestTimeoutException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Timer;

/**
 * A subclass of {@link Request} used by RPC requests using Gadgets container as
 * a proxy. This class is not meant to be used in any other use case.
 */
class GadgetsRequest extends Request {

  /**
   * Creates a {@link Response} instance based on passed
   * {@link ResponseReceivedEvent} object.
   * 
   * @param event {@link ResponseReceivedEvent} instance being source of data
   *          for new {@link Response}.
   * @return Created {@link Response} instance.
   */
  private static Response createResponse(
      final ResponseReceivedEvent<Object> event) {
    return new Response() {
      @Override
      public String getHeader(String header) {
        return null;
      }

      @Override
      public Header[] getHeaders() {
        return null;
      }

      @Override
      public String getHeadersAsString() {
        return null;
      }

      @Override
      public int getStatusCode() {
        return event.getResponse().getStatusCode();
      }

      @Override
      public String getStatusText() {
        return null;
      }

      @Override
      public String getText() {
        return event.getResponse().getText();
      }
    };
  }

  /**
   * The number of milliseconds to wait for this HTTP request to complete.
   */
  private final int timeoutMillis;

  /**
   * Timer used to force HTTPRequest timeouts. If the user has not requested a
   * timeout then this field is null.
   */
  private final Timer timer;

  /**
   * Indicates whether request is pending.
   */
  private boolean isPending = false;
  private boolean canceled = false;

  /**
   * Constructs an instance of the {@link GadgetsRequest} object.
   * 
   * @param timeoutMillis number of milliseconds to wait for a response
   * @param callback callback interface to use for notification
   * 
   * @throws IllegalArgumentException if timeoutMillis &lt; 0
   * @throws NullPointerException if xmlHttpRequest, or callback are null
   */
  GadgetsRequest(int timeoutMillis, final RequestCallback callback) {
    if (callback == null) {
      throw new NullPointerException("No callback specified");
    }

    if (timeoutMillis < 0) {
      throw new IllegalArgumentException();
    }

    this.timeoutMillis = timeoutMillis;

    if (timeoutMillis > 0) {
      // create and start a Timer
      timer = new Timer() {
        @Override
        public void run() {
          fireOnTimeout(callback);
        }
      };

      timer.schedule(timeoutMillis);
    } else {
      // no Timer required
      timer = null;
    }
  }

  /**
   * Cancels a pending request. If the request has already been canceled or if
   * it has timed out no action is taken.
   */
  @Override
  public void cancel() {
    cancelTimer();
    canceled = true;
    isPending = false;
  }

  /**
   * Returns true if this request is waiting for a response.
   * 
   * @return true if this request is waiting for a response
   */
  @Override
  public boolean isPending() {
    return isPending;
  }

  /**
   * Method called when this request receives response.
   */
  void fireOnResponseReceived(final ResponseReceivedEvent<Object> event,
      RequestCallback callback) {
    cancelTimer();
    if (!canceled) {
      isPending = false;
      final Response response = createResponse(event);

      if (response.getStatusCode() == Response.SC_OK) {
        callback.onResponseReceived(this, response);
      } else {
        String errorMessage = event.getResponse().getText() + " Errors:"
            + event.getResponse().getErrors();
        callback.onError(this, new RuntimeException(errorMessage));
      }
    }
  }

  /**
   * Stops the current HTTPRequest timer if there is one.
   */
  private void cancelTimer() {
    if (timer != null) {
      timer.cancel();
    }
  }

  /**
   * Method called when this request times out.
   */
  private void fireOnTimeout(RequestCallback callback) {
    cancel();
    callback.onError(this, new RequestTimeoutException(this, timeoutMillis));
  }

  /**
   * Method to be called to indicate that this request is waiting for the
   * response.
   */
  void setPending(boolean pending) {
    isPending = pending;
  }
}
