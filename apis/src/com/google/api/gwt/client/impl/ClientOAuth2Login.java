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

import com.google.api.gwt.client.OAuth2Login;
import com.google.api.gwt.shared.AuthScope;
import com.google.gwt.core.client.Callback;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

import java.util.Arrays;

/**
 * Handles the OAuth2 login process for GWT client code.
 *
 * @deprecated Use {@link OAuth2Login}
 */
@Deprecated
public class ClientOAuth2Login implements com.google.api.gwt.shared.OAuth2Login<ClientOAuth2Login> {
  private final String clientId;
  private Iterable<AuthScope> scopes;

  public ClientOAuth2Login(String clientId) {
    this.clientId = clientId;
  }

  @Override
  public ClientOAuth2Login withScopes(AuthScope... scopes) {
    this.scopes = Arrays.asList(scopes);
    return this;
  }

  @Override
  public void login(final Receiver<String> receiver) {
    OAuth2Login.get().authorize(clientId, scopes, new Callback<Void, Exception>() {
      @Override
      public void onSuccess(Void v) {
        receiver.onSuccess("");
      }

      @Override
      public void onFailure(Exception e) {
        receiver.onFailure(new ServerFailure(e.getMessage()));
      }
    });
  }
}
