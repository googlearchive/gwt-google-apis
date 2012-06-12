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
package com.google.api.gwt.client.impl;

import com.google.api.gwt.client.GoogleApiRequestTransport;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

/**
 * Provides access to Google API endpoints for RequestFactory.
 *
 * @deprecated Use {@link GoogleApiRequestTransport}.
 */
@Deprecated
public class ClientGoogleApiRequestTransport implements
    com.google.api.gwt.shared.GoogleApiRequestTransport<ClientGoogleApiRequestTransport> {

  private String baseUrl = "https://www.googleapis.com";
  private String apiKey;
  private String applicationName;
  private GoogleApiRequestTransport transport;

  @Override
  public void create(@SuppressWarnings("rawtypes") Receiver<
      com.google.api.gwt.shared.GoogleApiRequestTransport> receiver) {
    if (this.transport != null) {
      // receiver.onSuccess(this);
      receiver.onFailure(new ServerFailure("Transport is already created."));
    }
    this.transport =
        new GoogleApiRequestTransport(applicationName, apiKey, baseUrl);
    receiver.onSuccess(this);
  }

  @Override
  public void send(String payload, TransportReceiver receiver) {
    if (this.transport == null) {
      receiver.onTransportFailure(new ServerFailure("Must call create() before making requests."));
    }
    transport.send(payload, receiver);
  }

  @Override
  public ClientGoogleApiRequestTransport setBaseUrl(String baseUrl) {
    if (this.transport != null) {
      throw new IllegalStateException("Transport was already created.");
    }
    this.baseUrl = baseUrl;
    return this;
  }

  @Override
  public ClientGoogleApiRequestTransport setAccessToken(String accessToken) {
    if (this.transport != null) {
      throw new IllegalStateException("Transport was already created.");
    }
    return this;
  }

  @Override
  public ClientGoogleApiRequestTransport setApiAccessKey(String apiAccessKey) {
    if (this.transport != null) {
      throw new IllegalStateException("Transport was already created.");
    }
    this.apiKey = apiAccessKey;
    return this;
  }

  @Override
  public ClientGoogleApiRequestTransport setApplicationName(String applicationName) {
    if (this.transport != null) {
      throw new IllegalStateException("Transport was already created.");
    }
    this.applicationName = applicationName;
    return this;
  }
}
