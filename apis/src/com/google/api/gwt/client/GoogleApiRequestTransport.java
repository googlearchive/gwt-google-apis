/*
 * Copyright 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.api.gwt.client;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.JsonUtils;
import com.google.web.bindery.requestfactory.shared.RequestTransport;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link RequestTransport} designed to communicate with Google APIs.
 *
 * @author jasonhall@google.com (Jason Hall)
 */
public class GoogleApiRequestTransport implements RequestTransport {

  private final String apiKey;
  private final String userAgent;
  private final Map<String, String> headers = new HashMap<String, String>();
  private final String baseUrl;

  /**
   * @param applicationName the application name to be sent in the User-Agent header of requests.
   * @param apiKey an optional API key. See https://code.google.com/apis/console, or {@code null} to
   *     send no API key.
   */
  public GoogleApiRequestTransport(String applicationName, String apiKey) {
    this(applicationName, apiKey, "https://content.googleapis.com");
  }

  /**
   * @param applicationName the application name to be sent in the User-Agent header of requests.
   * @param apiKey an optional API key. See https://code.google.com/apis/console, or {@code null} to
   *     send no API key.
   * @param baseUrl the base URL of the Discovery service to use
   */
  public GoogleApiRequestTransport(String applicationName, String apiKey, String baseUrl) {
    this.apiKey = apiKey;
    this.userAgent =
        (applicationName == null ? "" : (applicationName + " ")) + "google-api-gwt-client/0.1";
    this.baseUrl = baseUrl;
  }

  private native void nativeSend(String payload, TransportReceiver receiver) /*-{
    var callback =  $entry(function(result) {
      if ('error' in result) {
        var code = result['error']['code'];
        var message = result['error']['message'];
        @com.google.api.gwt.client.GoogleApiRequestTransport::handleError(*)
            (code, message, receiver);
      } else {
        var resultStr = JSON.stringify(result);
        receiver.
            @com.google.web.bindery.requestfactory.shared.RequestTransport.TransportReceiver::onTransportSuccess(*)
            (resultStr);
      }
    });

    // TODO(jasonhall): Find some way to avoid this.
    eval('var payloadObject = ' + payload);

    var params = payloadObject['params'] || {};
    params['key'] = this.@com.google.api.gwt.client.GoogleApiRequestTransport::apiKey;
    params['root'] = this.@com.google.api.gwt.client.GoogleApiRequestTransport::baseUrl;

    // TODO(jasonhall): Set the X-JavaScript-User-Agent header

    $wnd.gapi.client.rpcRequest(payloadObject.method,
        payloadObject.apiVersion, params).execute(callback);
  }-*/;

  private static void handleError(int code, String message, TransportReceiver receiver) {
    receiver.onTransportFailure(new ServerFailure(code + " " + message));
  }

  @Override
  public void send(final String payload, final TransportReceiver receiver) {
    // Ensure that the API library is loaded. If it is, this callback will be executed immediately.
    GoogleApiLoader.get().load(new Callback<Void, Exception>() {
      @Override
      public void onSuccess(Void v) {
        makeRequest(payload, receiver);
      }

      @Override
      public void onFailure(Exception e) {
        receiver.onTransportFailure(new ServerFailure(e.getMessage()));
      }
    });
  }

  private void makeRequest(String payload, TransportReceiver receiver) {
    if (JsonUtils.safeToEval(payload)) {
      nativeSend(payload, receiver);
    } else {
      receiver.onTransportFailure(new ServerFailure("Request payload is invalid."));
    }
  }
}
