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
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ScriptElement;
import com.google.gwt.http.client.Response;
import com.google.web.bindery.autobean.gwt.client.impl.JsoSplittable;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

import java.util.Map;

public class ClientGoogleApiRequestTransport extends GoogleApiRequestTransport {

  /**
   * Used by implementation to interpret the response.
   */
  interface ApiResponse {
    String getBody();

    Map<String, String> getHeaders();

    int getStatus();

    String getStatusText();
  }

  interface Factory extends AutoBeanFactory {
    AutoBean<ApiResponse> api();
  }

  static class InitializationState {
    private final Receiver<GoogleApiRequestTransport> receiver;
    private boolean scriptLoaded;
    private GoogleApiRequestTransport transport;

    public InitializationState(GoogleApiRequestTransport transport,
        Receiver<GoogleApiRequestTransport> receiver) {
      this.receiver = receiver;
      this.transport = transport;
    }

    void maybeStart() {
      if (scriptLoaded) {
        receiver.onSuccess(transport);
      }
    }

    void setScriptLoaded() {
      scriptLoaded = true;
      maybeStart();
    }
  }

  private static final Factory FACTORY = GWT.create(Factory.class);
  private static final String SUPPORT_URL =
      "https://ajax.googleapis.com/ajax/libs/googleapis/0.0.3/googleapis.min.js";

  /**
   * Prevents the js client shell script from being loaded multiple times.
   */
  private static ScriptElement script;

  /**
   * Called from JSNI callback in {@link #makeCall}.
   */
  protected static void handleResult(JavaScriptObject result, TransportReceiver receiver) {
    JsoSplittable split = result.cast();
    AutoBean<ApiResponse> bean = FACTORY.api();
    AutoBeanCodex.decodeInto(split, bean);
    ApiResponse response = bean.as();
    if (response.getStatus() != Response.SC_OK) {
      receiver.onTransportFailure(new ServerFailure(response.getStatus() + " "
          + response.getStatusText()));
      return;
    }
    receiver.onTransportSuccess(response.getBody());
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

  private static native JavaScriptObject makeData(String url, JavaScriptObject headers,
      String payload) /*-{
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
  protected void configure(Receiver<GoogleApiRequestTransport> receiver) {
    // Only load the script once, if the necessary API isn't available
    if (script == null && !isLoaded()) {
      script = Document.get().createScriptElement();
      script.setSrc(SUPPORT_URL);
      Document.get().getElementsByTagName("head").getItem(0).appendChild(script);
    }

    for (Map.Entry<String, String> entry : getHeaders().entrySet()) {
      set(headers, entry.getKey(), entry.getValue());
    }

    /*
     * If a second instance of this class is created, the callback will still
     * occur on a subsequent tick of the event loop.
     */
    final InitializationState state = new InitializationState(this, receiver);
    Scheduler.get().scheduleFixedDelay(new RepeatingCommand() {
      @Override
      public boolean execute() {
        boolean loaded = isLoaded();
        if (loaded) {
          state.setScriptLoaded();
        }
        return !loaded;
      }
    }, 100);
  }

  @Override
  public void send(String payload, TransportReceiver receiver) {
    JavaScriptObject data = makeData(getRpcUrl(), headers, payload);
    makeCall(data, receiver);
  }
}
