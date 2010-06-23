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

import com.google.gwt.ajaxloader.client.ExceptionHelper;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.ObjectElement;
import com.google.gwt.gadgets.client.io.ResponseReceivedHandler.ResponseReceivedEvent;

/**
 * Provides access to the gadgets.io APIs provided by the container.
 */
class GadgetsIoImpl implements GadgetsIo {

  private static interface MakeRequestCallback<T> {
    void onDone(Response<T> response);
  }

  GadgetsIoImpl() {
  }

  public native String encodeValues(JavaScriptObject jso) /*-{
    return $wnd.gadgets.io.encodeValues(jso);
  }-*/;


  public native String getProxyUrl(String url) /*-{
    return $wnd.gadgets.io.getProxyUrl(url);
  }-*/;


  public native String getProxyUrl(String url, int refreshIntervalSeconds) /*-{
    return $wnd.gadgets.io.getProxyUrl(url, refreshInterval);
  }-*/;


  public void makeRequest(final String url,
      final ResponseReceivedHandler<Object> handler) {
    makeRequest(url, handler, null);
  }

  public void makeRequest(final String url,
      final ResponseReceivedHandler<Object> handler, RequestOptions options) {
    makeRequestImpl(url, handler, options);
  }

  public void makeRequestAsDom(final String url,
      final ResponseReceivedHandler<ObjectElement> handler) {
    makeRequestAsDom(url, handler, null);
  }

  public void makeRequestAsDom(final String url,
      final ResponseReceivedHandler<ObjectElement> handler,
      RequestOptions options) {
    if (options == null) {
      options = RequestOptions.newInstance();
    }
    options.setContentType(ContentType.DOM);
    makeRequestImpl(url, handler, options);
  }

  public void makeRequestAsJso(final String url,
      final ResponseReceivedHandler<? extends JavaScriptObject> handler) {
    makeRequestAsJso(url, handler, null);
  }

  public void makeRequestAsJso(final String url,
      final ResponseReceivedHandler<? extends JavaScriptObject> handler,
      RequestOptions options) {
    if (options == null) {
      options = RequestOptions.newInstance();
    }
    options.setContentType(ContentType.JSON);
    makeRequestImpl(url, handler, options);
  }

  public void makeRequestAsText(final String url,
      final ResponseReceivedHandler<String> handler) {
    makeRequestAsText(url, handler, null);
  }

  public void makeRequestAsText(final String url,
      final ResponseReceivedHandler<String> handler, RequestOptions options) {
    if (options == null) {
      options = RequestOptions.newInstance();
    }
    options.setContentType(ContentType.TEXT);
    makeRequestImpl(url, handler, options);
  }

  private <T> void makeRequestImpl(final String url,
      final ResponseReceivedHandler<T> handler, final RequestOptions options) {
    makeRequestNative(url, new MakeRequestCallback<T>() {
      public void onDone(final Response<T> response) {
        ExceptionHelper.runProtected(new Runnable() {
          public void run() {
            handler.onResponseReceived(new ResponseReceivedEvent<T>(response,
                url));
          }
        });
      }
    }, options);
  }

  private native <T> void makeRequestNative(String url,
      MakeRequestCallback<T> callback, RequestOptions options) /*-{
    $wnd.gadgets.io.makeRequest(url, function(obj) {
      callback.@com.google.gwt.gadgets.client.io.GadgetsIoImpl$MakeRequestCallback::onDone(Lcom/google/gwt/gadgets/client/io/Response;)(obj);
    }, (options === null) ? undefined : options);
  }-*/;
}
