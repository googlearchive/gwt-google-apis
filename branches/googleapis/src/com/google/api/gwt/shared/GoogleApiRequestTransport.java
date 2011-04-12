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

import com.google.web.bindery.requestfactory.shared.RequestTransport;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Provides access to Google API endpoints for RequestFactory.
 *
 */
public abstract class GoogleApiRequestTransport implements RequestTransport {

  /**
   * Constructs instances of {@link GoogleApiRequestTransport}.
   */
  public static class Builder {
    private String applicationName;
    private final Map<String, String> headers = new HashMap<String, String>();
    private GoogleApiRequestTransport transport = TransportHelper.createTransport();
    private final Map<String, String> queryParams = new HashMap<String, String>();

    public Builder() {
      setRpcUrl(DEFAULT_RPC_URL);
    }

    /**
     * Initialize the RequestTransport and pass it to the {@code receiver}
     * parameter.
     */
    public void build(RequestTransportReceiver receiver) {
      if (transport == null) {
        throw new IllegalStateException("build() already called");
      }

      if (!queryParams.isEmpty()) {
        StringBuilder currentUrl = new StringBuilder(transport.rpcUrl);
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
        transport.rpcUrl = currentUrl.toString();
      }

      // Mandatory headers
      String userAgent =
          (applicationName == null ? "" : (applicationName + " ")) + USER_AGENT_STRING;
      setRequestHeader(CONTENT_TYPE_HEADER, CONTENT_TYPE);
      setRequestHeader(USER_AGENT_HEADER, userAgent);
      transport.headers = Collections.unmodifiableMap(headers);

      transport.configure(receiver);
      transport = null;
    }

    /**
     * Set the OAuth access token to pass with the request in order to access
     * protected information.
     */
    public Builder setAccessToken(String authorization) {
      setRequestHeader(AUTHORIZATION_HEADER, OAUTH + authorization);
      return this;
    }

    /**
     * Set the API access key to support quota management.
     */
    public Builder setApiAccessKey(String apiAccessKey) {
      setQueryParameter("key", apiAccessKey);
      return this;
    }

    /**
     * Set the application name for use in quota management.
     *
     * @param applicationName A user-agent fragment, such as {@code MyApp/1.0}
     */
    public Builder setApplicationName(String applicationName) {
      this.applicationName = applicationName;
      return this;
    }

    /**
     * Append a query parameter to the URL used to make the request.
     */
    public Builder setQueryParameter(String name, String value) {
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
    public Builder setRequestHeader(String name, String value) {
      if (value == null) {
        headers.remove(name);
      } else {
        headers.put(name, value);
      }
      return this;
    }

    /**
     * Set the base URL that requests will be sent to.
     */
    public Builder setRpcUrl(String rpcUrl) {
      transport.rpcUrl = rpcUrl;
      return this;
    }
  }

  /**
   * Provided by user code to receive an initialized instance of the transport.
   */
  public interface RequestTransportReceiver {
    void onFailure(Throwable cause);

    void onSuccess(GoogleApiRequestTransport transport);
  }

  protected static final String AUTHORIZATION_HEADER = "Authorization";
  protected static final String CONTENT_TYPE = "application/json";
  protected static final String CONTENT_TYPE_HEADER = "Content-Type";
  protected static final String PROTOCOL = "https://";
  protected static final String OAUTH = "OAuth ";
  protected static final String ORIGIN = PROTOCOL + "www.googleapis.com/";
  protected static final String DEFAULT_RPC_URL = ORIGIN + "rpc";
  protected static final String USER_AGENT_HEADER = "X-JavaScript-User-Agent";
  protected static final String USER_AGENT_STRING = "google-api-gwtrf-client/0.1";

  private Map<String, String> headers;
  private String rpcUrl;

  protected GoogleApiRequestTransport() {
  }

  protected abstract void configure(RequestTransportReceiver receiver);

  protected Map<String, String> getHeaders() {
    return headers;
  }

  protected String getRpcUrl() {
    return rpcUrl;
  }
}
