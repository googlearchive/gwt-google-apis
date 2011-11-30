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
import com.google.web.bindery.requestfactory.shared.ServerFailure;

/**
 * Handles the OAuth2 login process for GWT client code.
 */
public abstract class OAuth2Login {

  protected AuthScope[] scopes;
  protected final String clientId;

  /**
   * @param clientId This application's unique client ID, which can be found at
   *        the Google APIs Console
   *        {@link "http://code.google.com/apis/console"}.
   */
  public OAuth2Login(String clientId) {
    this.clientId = clientId;
  }

  /** Sets some scopes to which to request permission. */
  public OAuth2Login withScopes(AuthScope... scopes) {
    this.scopes = scopes;
    return this;
  }

  /**
   * Performs authorization to get an access token, then validates that token.
   */
  public void login(final Receiver<String> callback) {
    authorize(new Receiver<String>() {
      @Override
      public void onSuccess(final String accessToken) {
        OAuth2Login.this.validate(accessToken, new Receiver<Void>() {
          @Override
          public void onSuccess(Void result) {
            callback.onSuccess(accessToken);
          }

          @Override
          public void onFailure(ServerFailure failure) {
            callback.onFailure(failure);
          }
        });
      }

      @Override
      public void onFailure(ServerFailure failure) {
        callback.onFailure(failure);
      }
    });
  }

  protected abstract void authorize(Receiver<String> callback);

  protected abstract void validate(String accessToken, Receiver<Void> callback);
}
