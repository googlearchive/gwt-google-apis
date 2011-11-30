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
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.jsonp.client.JsonpRequestBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

/**
 * Implementation of {@link OAuth2Login} that delegates to the gwt-oauth2
 * implementation to provide an access token.
 */
public class ClientOAuth2Login extends OAuth2Login {

  private static final String GOOGLE_AUTH_URL = "https://accounts.google.com/o/oauth2/auth";
  private static final String GOOGLE_VALIDATE_URL =
      "https://www.googleapis.com/oauth2/v1/tokeninfo?access_token=";

  public ClientOAuth2Login(String clientId) {
    super(clientId);
  }

  @Override
  public void authorize(final Receiver<String> callback) {
    AuthRequest req = new AuthRequest(GOOGLE_AUTH_URL, clientId)
        .withScopes(scopesToStrings(scopes));
    Auth.get().login(req, new Callback<String, Throwable>() {
      @Override
      public void onSuccess(final String accessToken) {
        callback.onSuccess(accessToken);
      }

      @Override
      public void onFailure(Throwable reason) {
        callback.onFailure(new ServerFailure(reason.getMessage()));
      }
    });
  }

  private static String[] scopesToStrings(AuthScope... scopes) {
    String[] strings = new String[scopes.length];
    for (int i = 0; i < scopes.length; i++) {
      strings[i] = scopes[i].getScope();
    }
    return strings;
  }

  private static final class ValidationResponse extends JavaScriptObject {
    protected ValidationResponse() {
    }

    private final native String getError() /*-{
      return this.error;
    }-*/;

    private final native String getAudience() /*-{
      return this.audience;
    }-*/;
  }

  @Override
  protected void validate(String accessToken, final Receiver<Void> callback) {
    String url = GOOGLE_VALIDATE_URL + accessToken;
    new JsonpRequestBuilder().requestObject(url, new AsyncCallback<ValidationResponse>() {
      @Override
      public void onSuccess(ValidationResponse result) {
        if (result.getError() != null) {
          callback.onFailure(new ServerFailure(result.getError()));
        } else if (!result.getAudience().equals(ClientOAuth2Login.this.clientId)) {
          callback.onFailure(
              new ServerFailure("Token validation failed: Client ID mismatch"));
        } else {
          callback.onSuccess(null);
        }
      }

      @Override
      public void onFailure(Throwable reason) {
        callback.onFailure(new ServerFailure(reason.getMessage()));
      }
    });
  }
}
