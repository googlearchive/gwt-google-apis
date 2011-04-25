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
import com.google.api.gwt.shared.AuthScope;
import com.google.api.gwt.shared.OAuth2Login;
import com.google.gwt.core.client.Callback;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

/**
 * Implementation of {@link OAuth2Login} that delegates to the gwt-oauth2
 * implementation to provide an access token.
 */
public class ClientOAuth2Login extends OAuth2Login {

  public ClientOAuth2Login(String clientId) {
    super(clientId);
  }

  private static final String GOOGLE_AUTH_URL = "https://accounts.google.com/o/oauth2/auth";

  @Override
  public void login(final Receiver<String> callback) {
    AuthRequest req = new AuthRequest(GOOGLE_AUTH_URL, clientId).withScopes(scopesToString(scopes));
    Auth.get().login(req, new Callback<String, Throwable>() {
      @Override
      public void onSuccess(String result) {
        callback.onSuccess(result);
      }

      @Override
      public void onFailure(Throwable reason) {
        callback.onFailure(new ServerFailure(reason.getMessage()));
      }
    });
  }

  private static String[] scopesToString(AuthScope... scopes) {
    String[] strings = new String[scopes.length];
    for (int i = 0; i < scopes.length; i++) {
      strings[i] = scopes[i].getScope();
    }
    return strings;
  }
}
