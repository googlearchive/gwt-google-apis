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
package com.google.api.gwt.shared;

import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.RequestTransport;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Provides access to Google API endpoints for RequestFactory.
 *
 */
public abstract class GoogleApiRequestTransport implements RequestTransport {

  private String applicationName;
  private Map<String, String> queryParams = new HashMap<String, String>();
  private Map<String, String> headers = new HashMap<String, String>();
  private String rpcUrl = "https://www.googleapis.com/rpc";

  private boolean finished = false;

  /**
   * Initialize the RequestTransport and pass it to the {@code receiver}
   * parameter.
   */
  public void create(Receiver<GoogleApiRequestTransport> receiver) {
    if (finished) {
      receiver.onFailure(new ServerFailure("Already called create() on GoogleApiRequestTransport"));
      return;
    }
    if (!queryParams.isEmpty()) {
      StringBuilder currentUrl = new StringBuilder(rpcUrl);
      boolean needsAmp = currentUrl.indexOf("?") != -1;
      for (Map.Entry<String, String> entry : queryParams.entrySet()) {
        if (needsAmp) {
          currentUrl.append("&");
        } else {
          currentUrl.append("?");
          needsAmp = true;
        }
        currentUrl.append(entry.getKey()).append("=").append(entry.getValue());
      }
      rpcUrl = currentUrl.toString();
    }

    // Mandatory headers
    String userAgent = (applicationName == null ? "" : (applicationName + " ")) + USER_AGENT_STRING;
    setRequestHeader(CONTENT_TYPE_HEADER, CONTENT_TYPE);
    setRequestHeader(USER_AGENT_HEADER, userAgent);
    headers = Collections.unmodifiableMap(headers);
    queryParams = Collections.unmodifiableMap(new HashMap<String, String>());

    configure(receiver);
  }

  /**
   * Set the OAuth access token to pass with the request in order to access
   * protected information.
   */
  public GoogleApiRequestTransport setAccessToken(String authorization) {
    setRequestHeader(AUTHORIZATION_HEADER, OAUTH + authorization);
    return this;
  }

  /**
   * Set the API access key to support quota management.
   */
  public GoogleApiRequestTransport setApiAccessKey(String apiAccessKey) {
    setQueryParameter("key", apiAccessKey);
    return this;
  }

  /**
   * Set the application name for use in quota management.
   *
   * @param applicationName A user-agent fragment, such as {@code MyApp/1.0}
   */
  public GoogleApiRequestTransport setApplicationName(String applicationName) {
    this.applicationName = applicationName;
    return this;
  }

  /**
   * Append a query parameter to the URL used to make the request.
   */
  public GoogleApiRequestTransport setQueryParameter(String name, String value) {
    if (value == null) {
      queryParams.remove(name);
    } else {
      queryParams.put(name, value);
    }
    return this;
  }

  /**
   * Add a header to every HTTP query used to make a request.
   */
  public GoogleApiRequestTransport setRequestHeader(String name, String value) {
    if (value == null) {
      headers.remove(name);
    } else {
      headers.put(name, value);
    }
    return this;
  }

  private static final String AUTHORIZATION_HEADER = "Authorization";
  private static final String CONTENT_TYPE = "application/json";
  private static final String CONTENT_TYPE_HEADER = "Content-Type";
  private static final String OAUTH = "OAuth ";
  private static final String USER_AGENT_HEADER = "X-JavaScript-User-Agent";
  private static final String USER_AGENT_STRING = "google-api-gwt-client/0.1";

  protected GoogleApiRequestTransport() {
  }

  protected abstract void configure(Receiver<GoogleApiRequestTransport> receiver);

  protected Map<String, String> getHeaders() {
    return headers;
  }

  protected String getRpcUrl() {
    return rpcUrl;
  }

  // begin_strip
  /**
   * Override the RPC URL being requested. By default this is
   * {@link "https://www.googleapis.com/"}. If you set this, it must include the
   * trailing slash.
   */
  public GoogleApiRequestTransport setBaseUrl(String baseUrl) {
    this.rpcUrl = baseUrl + "/rpc";
    configureBaseUrl(baseUrl);
    return this;
  }

  /**
   * Configures the base JS library to use the proxy.html from the new base URL
   * in future requests.
   */
  private native void configureBaseUrl(String baseUrl) /*-{
    $wnd['__GOOGLEAPIS'] = {
      'googleapis.config' : {
        'proxy' : baseUrl + '/static/proxy.html'
      }
    }
  }-*/;
  // end_strip
}
