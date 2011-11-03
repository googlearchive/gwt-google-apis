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

import com.google.gwt.gadgets.client.io.IoProvider;
import com.google.gwt.gadgets.client.io.MethodType;
import com.google.gwt.gadgets.client.io.RequestOptions;
import com.google.gwt.gadgets.client.io.ResponseReceivedHandler;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;

/**
 * A subclass of {@link RequestBuilder} which builds requests using
 * {@link com.google.gwt.gadgets.client.io.GadgetsIo} for communication with the
 * server.
 */
class GadgetsRequestBuilder extends RequestBuilder {

  private final RequestOptions requestOptions;

  /**
   * Creates a builder using the parameters for configuration.
   * 
   * @param httpMethod HTTP method to use for the request
   * @param url URL that has already has already been encoded. Please see
   *          {@link com.google.gwt.http.client.URL#encode(String)} and
   *          {@link com.google.gwt.http.client.URL#encodeQueryString(String)}
   *          for how to do this.
   * @throws IllegalArgumentException if the httpMethod or URL are empty
   * @throws NullPointerException if the httpMethod or the URL are null
   */
  public GadgetsRequestBuilder(Method httpMethod, String url) {
    this(httpMethod, url, null);
  }

  /**
   * Creates a builder using the parameters for configuration.
   * 
   * @param httpMethod HTTP method to use for the request
   * @param url URL that has already has already been encoded. Please see
   *          {@link com.google.gwt.http.client.URL#encode(String)} and
   *          {@link com.google.gwt.http.client.URL#encodeQueryString(String)}
   *          for
   * @param requestOptions RequestOptions to use rather than the defaults.
   * @throws IllegalArgumentException if the httpMethod or URL are empty
   * @throws NullPointerException if the httpMethod or the URL are null
   */
  public GadgetsRequestBuilder(Method httpMethod, String url, RequestOptions requestOptions) {
    this(httpMethod.toString(), url, requestOptions);
  }

  /**
   * Creates a builder using the parameters values for configuration.
   * 
   * @param httpMethod HTTP method to use for the request
   * @param url URL that has already has already been URL encoded. Please see
   *          {@link com.google.gwt.http.client.URL#encode(String)} and
   *          {@link com.google.gwt.http.client.URL#encodeQueryString(String)}
   *          for how to do this.
   * @throws IllegalArgumentException if the httpMethod or URL are empty
   * @throws NullPointerException if the httpMethod or the URL are null
   */
  protected GadgetsRequestBuilder(String httpMethod, String url) {
    this(httpMethod, url, null);
  }

  /**
   * Creates a builder using the parameters for configuration.
   * 
   * @param httpMethod HTTP method to use for the request
   * @param url URL that has already has already been encoded. Please see
   *          {@link com.google.gwt.http.client.URL#encode(String)} and
   *          {@link com.google.gwt.http.client.URL#encodeQueryString(String)}
   *          for
   * @param requestOptions RequestOptions to use rather than the defaults.
   * @throws IllegalArgumentException if the httpMethod or URL are empty
   * @throws NullPointerException if the httpMethod or the URL are null
   */
  protected GadgetsRequestBuilder(String httpMethod, String url, RequestOptions requestOptions) {
    super(httpMethod, url);
    this.requestOptions = requestOptions;
  }

  /**
   * Sends a HTTP request based on the current builder configuration. Sent
   * request will use Gadgets container as a proxy.
   * 
   * @return a {@link GadgetsRequest} object that can be used to track the
   *         request
   */
  @Override
  public GadgetsRequest send() {
    return doSend(getRequestData(), getCallback());
  }

  /**
   * Sends an HTTP request based on the current builder configuration with the
   * specified data and callback. Sent request will use Gadgets container as a
   * proxy.
   * 
   * @param requestData the data to send as part of the request
   * @param callback the response handler to be notified when the request fails
   *          or completes
   * @return a {@link GadgetsRequest} object that can be used to track the
   *         request.
   */
  @Override
  public GadgetsRequest sendRequest(String requestData, RequestCallback callback) {
    return doSend(requestData, callback);
  }

  private GadgetsRequest doSend(String requestData, final RequestCallback callback) {
    final RequestOptions options =
        requestOptions != null ? requestOptions : RequestOptions.newInstance();
    options.setMethodType(MethodType.POST);
    options.setPostData(requestData);

    final GadgetsRequest gadgetsRequest = new GadgetsRequest(getTimeoutMillis(), callback);
    gadgetsRequest.setPending(true);

    IoProvider.get().makeRequest(getUrl(), new ResponseReceivedHandler<Object>() {
      public void onResponseReceived(ResponseReceivedEvent<Object> event) {
        gadgetsRequest.fireOnResponseReceived(event, callback);
      }
    }, options);

    return gadgetsRequest;
  }
}
