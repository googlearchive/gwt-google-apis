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

import com.google.api.gwt.oauth2.client.Auth;
import com.google.api.gwt.oauth2.client.AuthRequest;
import com.google.api.gwt.shared.OAuth2Login;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;

/**
 * Intended to be constructed via {@link OAuth2Login.Builder}.
 */
public class ClientOAuth2Login extends OAuth2Login {

  @Override
  protected void configure(CodeProvider codeProvider, final OAuth2LoginReceiver receiver) {
    AuthRequest req = new AuthRequest(authUrl, CLIENT_ID).withScopes(scope);

    Auth.get().login(req, new Callback<String, Throwable>() {
      @Override
      public void onSuccess(String token) {
        accessToken = token;
        receiver.onSuccess(ClientOAuth2Login.this);
      }

      @Override
      public void onFailure(Throwable caught) {
        receiver.onFailure(caught);
      }
    });
  }

  @Override
  protected String getRedirectUri() {
    return GWT.getModuleBaseURL() + "OAuth2Helper.html";
  }

  @Override
  protected String getResponseType() {
    return TOKEN;
  }

  @Override
  protected void postToTokenUrl(String payload, ServerResponseReceiver receiver) {
    throw new UnsupportedOperationException();
  }
}
