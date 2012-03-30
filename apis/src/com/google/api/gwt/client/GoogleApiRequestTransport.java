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
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.http.client.Response;
import com.google.web.bindery.requestfactory.shared.RequestTransport;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

/**
 * {@link RequestTransport} designed to communicate with Google APIs.
 *
 * @author jasonhall@google.com (Jason Hall)
 */
public class GoogleApiRequestTransport implements RequestTransport {

  private final String apiKey;
  private final String userAgent;

  public GoogleApiRequestTransport(String applicationName, String apiKey) {
    this(applicationName, apiKey, "https://www.googleapis.com");
  }

  public GoogleApiRequestTransport(String applicationName, String apiKey, String baseUrl) {
    this.apiKey = apiKey;
    this.userAgent =
        (applicationName == null ? "" : (applicationName + " ")) + "google-api-gwt-client/0.1";

    baseConfigure(baseUrl);
  }

  private static native void baseConfigure(String baseUrl) /*-{
    $wnd['__GOOGLEAPIS'] = {
      'googleapis.config' : {
        'proxy': baseUrl + "/static/proxy.html",
      }
    };
  }-*/;

  private native void nativeSend(String payload, TransportReceiver receiver) /*-{
    var callback =  $entry(function(result) {
      @com.google.api.gwt.client.GoogleApiRequestTransport::handleResult(*)
      (result, receiver);
    });

    // TODO(jasonhall): Find some way to avoid this.
    eval('var payloadObject = ' + payload);

    var requestObj = {
      "url": "/rpc",
      "headers": {
        "Content-Type": "application/json",
        "X-JavaScript-User-Agent":
            this.@com.google.api.gwt.client.GoogleApiRequestTransport::userAgent
      },
      "httpMethod": "POST",
      "body": {
        "method": payloadObject.method,
        "apiVersion": payloadObject.apiVersion,
        "params": payloadObject.params
      }
    }
    requestObj['body']['params']['key'] =
        this.@com.google.api.gwt.client.GoogleApiRequestTransport::apiKey;

    $wnd.googleapis.newHttpRequest(requestObj).execute(callback);
  }-*/;

  private static final class ApiResponseJso extends JavaScriptObject {
    @SuppressWarnings("unused")
    protected ApiResponseJso() {
    }

    final native String getBody() /*-{
      return this.body;
    }-*/;

    final native int getStatus() /*-{
      return this.status;
    }-*/;

    final native String getStatusText() /*-{
      return this.statusText;
    }-*/;
  }

  private static void handleResult(JavaScriptObject result, TransportReceiver receiver) {
    ApiResponseJso response = result.cast();

    if (response.getStatus() == Response.SC_OK) {
      receiver.onTransportSuccess(response.getBody());
    } else {
      receiver.onTransportFailure(
          new ServerFailure(response.getStatus() + " " + response.getStatusText()));
    }
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
