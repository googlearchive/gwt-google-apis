/*
 * Copyright 2011 Google Inc.
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
package com.google.api.gwt.client.impl;

import com.google.api.gwt.shared.GoogleApiRequestTransport;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.http.client.Response;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

import java.util.Map;

/**
 * Implementation of {@link GoogleApiRequestTransport} that should be used in
 * the browser. It defers requests to a the JS library which provides
 * cross-domain RPC.
 */
public class ClientGoogleApiRequestTransport extends GoogleApiRequestTransport {

  private static final String JS_CLIENT_NAME = "ae_f85e3bd0744c7080861c3ae42085d071.js";
  private static final String JS_CLIENT_URL = "https://ssl.gstatic.com/gb/js/" + JS_CLIENT_NAME;

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

  /** Called from JSNI callback in {@link #makeCall}. */
  protected static void handleResult(JavaScriptObject result, TransportReceiver receiver) {
    ApiResponseJso response = result.cast();
    if (response.getStatus() != Response.SC_OK) {
      receiver.onTransportFailure(
          new ServerFailure(response.getStatus() + " " + response.getStatusText()));
    } else {
      receiver.onTransportSuccess(response.getBody());
    }
  }

  protected static native boolean isLoaded() /*-{
    return !!$wnd.googleapis && !!$wnd.googleapis.newHttpRequest;
  }-*/;

  private static native void makeCall(JavaScriptObject data, TransportReceiver receiver) /*-{
    var callback = $entry(
    function(result) {
      @com.google.api.gwt.client.impl.ClientGoogleApiRequestTransport::handleResult(*)
        (result, receiver);
    });
    $wnd.googleapis.newHttpRequest(data).execute(callback);
  }-*/;

  private static native JavaScriptObject makeData(
      String url, JavaScriptObject headers, String payload) /*-{
    return {
        'url' : url,
        'headers' : headers,
        'body' : payload,
        'httpMethod' : 'POST'
    };
  }-*/;

  private static native void set(JavaScriptObject jso, String key, String value) /*-{
    jso[key] = value;
  }-*/;

  protected final JavaScriptObject headers = JavaScriptObject.createObject();

  @Override
  protected void configure(final Receiver<GoogleApiRequestTransport> receiver) {
    // Only load the script once, if the necessary API isn't available
    if (!isLoaded()) {
      ScriptInjector.fromUrl(JS_CLIENT_URL)
          .setWindow(ScriptInjector.TOP_WINDOW)
          .setCallback(new Callback<Void, Exception>() {
            public void onSuccess(Void result) {
              baseConfigure(baseUrl);
              setHeaders();
              receiver.onSuccess(ClientGoogleApiRequestTransport.this);
            }

            public void onFailure(Exception e) {
              receiver.onFailure(new ServerFailure(e.getMessage()));
            }
          }).inject();
    } else {
      setHeaders();
      receiver.onSuccess(this);
    }
  }

  private void setHeaders() {
    for (Map.Entry<String, String> entry : getHeaders().entrySet()) {
      set(headers, entry.getKey(), entry.getValue());
    }

  }

  /** Configures the base JS client to use the alternate JS library. */
  private static native void baseConfigure(String baseUrl) /*-{
    $wnd['__GOOGLEAPIS'] = {
      'googleapis.config' : {
        'proxy': baseUrl + "/static/proxy.html",
        'gcv' : @com.google.api.gwt.client.impl.ClientGoogleApiRequestTransport::JS_CLIENT_NAME
      }
    };
  }-*/;

  @Override
  public void send(String payload, TransportReceiver receiver) {
    JavaScriptObject data = makeData(getRpcUrl(), headers, payload);
    makeCall(data, receiver);
  }
}
