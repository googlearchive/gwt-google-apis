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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.ObjectElement;
import com.google.gwt.gadgets.client.io.ResponseReceivedHandler.ResponseReceivedEvent;

/**
 * Provides access to the gadgets.io APIs provided by the container.
 *
 * For {@link GadgetsIo} implementation, use {@link IoProvider#get()}.
 */
public interface GadgetsIo {

  /**
   * Converts an input object into a URL-encoded data string (key=value&...).
   * This method is particularly useful when used with
   * {@link RequestOptions#setPostData(String)} for creating POST requests.
   *
   * @param jso JavaScript object to convert
   * @return result of conversion
   */
  public String encodeValues(JavaScriptObject jso);

  /**
   * Returns a proxy URL that can be used to access a given URL.
   */
  public String getProxyUrl(String url);

  /**
   * Returns a proxy URL that can be used to access a given URL with a specified
   * refresh interval specified in seconds.
   */
  public String getProxyUrl(String url, int refreshIntervalSeconds);

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
      final ResponseReceivedHandler<Object> handler);

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
      final ResponseReceivedHandler<Object> handler, RequestOptions options);

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
      final ResponseReceivedHandler<ObjectElement> handler);

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
      RequestOptions options);

  /**
   * Makes the HTTP request and invokes the
   * {@link ResponseReceivedHandler#onResponseReceived(ResponseReceivedEvent)}
   * method with the received response. This method can be used for fetching
   * JSON data, which is parsed into a {@link JavaScriptObject} instance or it's
   * subclass. The fetched content is cached on the Gadget Container.
   *
   * @param url the URL for the request
   * @param handler the {@link ResponseReceivedHandler} instance to handle the
   *          response
   */
  public void makeRequestAsJso(final String url,
      final ResponseReceivedHandler<? extends JavaScriptObject> handler);

  /**
   * Makes the HTTP request and invokes the
   * {@link ResponseReceivedHandler#onResponseReceived(ResponseReceivedEvent)}
   * method with the received response. This method can be used for fetching
   * JSON data, which is parsed into a {@link JavaScriptObject} instance or it's
   * subclass. The fetched content is cached on the Gadget Container.
   *
   * @param url the URL for the request
   * @param handler the {@link ResponseReceivedHandler} instance to handle the
   *          response
   * @param options options for this request
   */
  public void makeRequestAsJso(final String url,
      final ResponseReceivedHandler<? extends JavaScriptObject> handler,
      RequestOptions options);

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
      final ResponseReceivedHandler<String> handler);

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
      final ResponseReceivedHandler<String> handler, RequestOptions options);

}