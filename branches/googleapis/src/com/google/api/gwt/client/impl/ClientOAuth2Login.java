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

import com.google.api.gwt.shared.OAuth2Login;
import com.google.gwt.core.client.GWT;

/**
 * Intended to be constructed via {@link OAuth2Login.Builder}.
 */
public class ClientOAuth2Login extends OAuth2Login {
  @Override
  protected void configure(CodeProvider codeProvider, final OAuth2LoginReceiver receiver) {
    if (codeProvider == null) {
      codeProvider = new WindowCodeProvider();
    }
    if (accessToken == null) {
      String url = authUrl + "?" + //
          CLIENT_ID + clientId + "&" + //
          REDIRECT_URI + getRedirectUri() + "&" + //
          SCOPE + scope + "&" + //
          RESPONSE_TYPE + getResponseType();
      codeProvider.getCode(url, new CodeReceiver() {

        @Override
        public void onSuccess(String code) {
          accessToken = code;
          receiver.onSuccess(ClientOAuth2Login.this);
        }

        @Override
        public void onFailure(Throwable t) {
          receiver.onFailure(t);
        }
      });
    }
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
