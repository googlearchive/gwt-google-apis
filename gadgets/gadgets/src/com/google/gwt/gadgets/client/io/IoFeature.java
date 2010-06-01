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
import com.google.gwt.dom.client.ObjectElement;
import com.google.gwt.gadgets.client.GadgetFeature;
import com.google.gwt.gadgets.client.io.ResponseReceivedHandler.ResponseReceivedEvent;

/**
 * Provides access to the gadgets.io APIs provided by the container.
 */
public class IoFeature implements GadgetFeature {

  private static interface MakeRequestCallback<T> {
    void onDone(Response<T> response);
  }

  private IoFeature() {
  }

  /**
   * Returns a proxy URL that can be used to access a given URL.
   */
  public native String getProxyUrl(String url) /*-{
    return $wnd.gadgets.io.getProxyUrl(url);
  }-*/;

  /**
   * Returns a proxy URL that can be used to access a given URL with a specified
   * refresh interval specified in seconds.
   */
  public native String getProxyUrl(String url, int refreshIntervalSeconds) /*-{
    return $wnd.gadgets.io.getProxyUrl(url, refreshInterval);
  }-*/;

  /**
   * Makes the HTTP request and invokes the
   * {@link ResponseReceivedHandler#onResponseReceived(ResponseReceivedEvent)}
   * method with the received response. The fetched content is cached on the
   * Gadget Container.
   *
   * @param url the URL for the request
   * @param handler the {@link ResponseReceivedHandler} instance to handle the
   *          response
   */
  public void makeRequest(final String url,
      final ResponseReceivedHandler<Object> handler) {
    makeRequest(url, handler, null);
  }

  /**
   * Makes the HTTP request and invokes the
   * {@link ResponseReceivedHandler#onResponseReceived(ResponseReceivedEvent)}
   * method with the received response. The fetched content is cached on the
   * Gadget Container.
   *
   * @param url the URL for the request
   * @param handler the {@link ResponseReceivedHandler} instance to handle the
   *          response
   * @param options options for this request
   */
  public void makeRequest(final String url,
      final ResponseReceivedHandler<Object> handler, RequestOptions options) {
    makeRequestImpl(url, handler, options);
  }

  /**
   * Makes the HTTP request and invokes the
   * {@link ResponseReceivedHandler#onResponseReceived(ResponseReceivedEvent)}
   * method with the received response. This method can be used for fetching XML
   * data, which is parsed into a DOM document. The fetched content is cached on
   * the Gadget Container.
   *
   * @param url the URL for the request
   * @param handler the {@link ResponseReceivedHandler} instance to handle the
   *          response
   */
  public void makeRequestAsDom(final String url,
      final ResponseReceivedHandler<ObjectElement> handler) {
    makeRequestAsDom(url, handler, null);
  }

  /**
   * Makes the HTTP request and invokes the
   * {@link ResponseReceivedHandler#onResponseReceived(ResponseReceivedEvent)}
   * method with the received response. This method can be used for fetching XML
   * data, which is parsed into a DOM document. The fetched content is cached on
   * the Gadget Container.
   *
   * @param url the URL for the request
   * @param handler the {@link ResponseReceivedHandler} instance to handle the
   *          response
   * @param options options for this request
   */
  public void makeRequestAsDom(final String url,
      final ResponseReceivedHandler<ObjectElement> handler,
      RequestOptions options) {
    if (options == null) {
      options = RequestOptions.newInstance();
    }
    options.setContentType(ContentType.DOM);
    makeRequestImpl(url, handler, options);
  }

  /**
   * Makes the HTTP request and invokes the
   * {@link ResponseReceivedHandler#onResponseReceived(ResponseReceivedEvent)}
   * method with the received response. This method can be used for fetching any
   * text data. The fetched content is cached on the Gadget Container.
   *
   * @param url the URL for the request
   * @param handler the {@link ResponseReceivedHandler} instance to handle the
   *          response
   */
  public void makeRequestAsText(final String url,
      final ResponseReceivedHandler<String> handler) {
    makeRequestAsText(url, handler, null);
  }

  /**
   * Makes the HTTP request and invokes the
   * {@link ResponseReceivedHandler#onResponseReceived(ResponseReceivedEvent)}
   * method with the received response. This method can be used for fetching any
   * text data. The fetched content is cached on the Gadget Container.
   *
   * @param url the URL for the request
   * @param handler the {@link ResponseReceivedHandler} instance to handle the
   *          response
   * @param options options for this request
   */
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
    return $wnd.gadgets.io.makeRequest(url, function(obj) {
      callback.@com.google.gwt.gadgets.client.io.IoFeature$MakeRequestCallback::onDone(Lcom/google/gwt/gadgets/client/io/Response;)(obj);
    }, (options === null) ? undefined : options);
  }-*/;
}
