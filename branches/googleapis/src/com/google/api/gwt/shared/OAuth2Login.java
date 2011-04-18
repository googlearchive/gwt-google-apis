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

import com.google.web.bindery.autobean.shared.AutoBean.PropertyName;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles the OAuth2 login process for GWT-client and server code.
 */
public abstract class OAuth2Login {
  /**
   * Constructs instances of {@link OAuth2Login}.
   */
  public static class Builder {
    private OAuth2Login login = TransportHelper.createLogin();
    {
      setAuthUrl(DEFAULT_AUTH_URL);
      setRpcUrl(DEFAULT_RPC_URL);
      setTokenUrl(DEFAULT_TOKEN_URL);
    }
    private CodeProvider codeProvider;
    private List<String> scopes = new ArrayList<String>();

    /**
     * Must be called at least once with an authorization scope.
     */
    public Builder addScope(String scope) {
      scopes.add(scope);
      return this;
    }

    public void build(OAuth2LoginReceiver receiver) {
      if (login == null) {
        throw new IllegalStateException("Already built");
      }
      StringBuilder sb = new StringBuilder();
      for (String scope : scopes) {
        sb.append("+").append(scope);
      }
      String scope = sb.substring(1);
      login.scope = scope;
      login.configure(codeProvider, receiver);
      login = null;
    }

    public Builder setAccessToken(String accessToken) {
      login.accessToken = accessToken;
      return this;
    }

    public Builder setAuthUrl(String authUrl) {
      login.authUrl = authUrl;
      return this;
    }

    public Builder setClientId(String clientId) {
      login.clientId = clientId;
      return this;
    }

    public Builder setClientSecret(String clientSecret) {
      login.clientSecret = clientSecret;
      return this;
    }

    /**
     * A CodeProvider is necessary for non-GWT-client use if a refresh token is
     * not provided. For GWT-client use, a default, popup-based CodeProvider is
     * provided.
     */
    public Builder setCodeProvider(CodeProvider callback) {
      this.codeProvider = callback;
      return this;
    }

    /**
     * Optional, but possibly avoids the need to log in again.
     */
    public Builder setRefreshToken(String refreshToken) {
      login.refreshToken = refreshToken;
      return this;
    }

    /**
     * Optional, defaults to {@value #DEFAULT_RPC_URL}.
     */
    public Builder setRpcUrl(String url) {
      login.rpcUrl = url;
      return this;
    }

    /**
     * Optional, defaults to {@value #DEFAULT_RPC_URL}.
     */
    public Builder setTokenUrl(String url) {
      login.tokenUrl = url;
      return this;
    }
  }

  /**
   * Implemented by the user to provide a UI mechanism for obtaining the
   * temporary logic code.
   */
  public interface CodeProvider {
    void getCode(String url, CodeReceiver callback);
  }

  /**
   * Provided by framework to user code.
   */
  public interface CodeReceiver {
    void onFailure(Throwable t);

    void onSuccess(String code);
  }

  /**
   * Used by framework to stop processing.
   */
  protected static class NoTokenException extends Exception {
    public NoTokenException(Throwable e) {
      super(e);
    }
  }

  /**
   * Provided by user code to receive an initialized login object.
   */
  public interface OAuth2LoginReceiver {
    void onFailure(Throwable t);

    void onSuccess(OAuth2Login login);
  }

  /**
   * Used by framework code to interpret OAuth2 server response.
   */
  protected interface ServerResponse {
    @PropertyName("access_token")
    String getAccessToken();

    @PropertyName("expires_in")
    int getExpiryTime();

    @PropertyName("refresh_token")
    String getRefreshToken();
  }

  /**
   * Used by framework code.
   */
  protected interface ServerResponseReceiver {
    void onFailure(Throwable t);

    void onSuccess(ServerResponse payload);
  }

  public static final String PROTOCOL = "https://";
  public static final String DEFAULT_AUTH_URL = PROTOCOL
      + "accounts.google.com/o/oauth2/auth";
  public static final String DEFAULT_RPC_URL = PROTOCOL + "www.googleapis.com/rpc";
  public static final String DEFAULT_TOKEN_URL = PROTOCOL
      + "accounts.google.com/o/oauth2/token";
  // Various string bits used in OAuth2 requests to avoid typos
  protected static final String CLIENT_ID = "client_id=";
  protected static final String CLIENT_SECRET = "client_secret=";
  protected static final String CODE = "code=";
  protected static final String GRANT_AUTH = "grant_type=authorization_code";
  protected static final String GRANT_REFRESH = "grant_type=refresh_token";
  protected static final String OOB = "oob";
  protected static final String REDIRECT_URI = "redirect_uri=";

  protected static final String REFRESH_TOKEN = "refresh_token=";
  protected static final String RESPONSE_TYPE = "response_type=";
  protected static final String SCOPE = "scope=";
  protected static final String TOKEN = "token";

  protected String accessToken;
  protected String authUrl;
  protected String clientId;
  protected String clientSecret;
  protected String refreshToken;
  protected String rpcUrl;
  protected String scope;
  protected long timeout;
  protected String tokenUrl;

  protected void configure(CodeProvider codeProvider, final OAuth2LoginReceiver receiver) {
    if (codeProvider == null) {
      throw new IllegalStateException("Must call Builder.setCodeProvider()");
    }
    if (refreshToken == null) {
      String codeUrl = authUrl + "?" + //
          CLIENT_ID + clientId + "&" + //
          REDIRECT_URI + getRedirectUri() + "&" + //
          SCOPE + scope + "&" + //
          RESPONSE_TYPE + getResponseType();
      codeProvider.getCode(codeUrl, new CodeReceiver() {
        @Override
        public void onFailure(Throwable t) {
          receiver.onFailure(t);
        }

        @Override
        public void onSuccess(String code) {
          try {
            loginWithCode(code);
            receiver.onSuccess(OAuth2Login.this);
          } catch (NoTokenException e) {
            receiver.onFailure(e);
          }
        }
      });
    } else {
      try {
        refresh();
        receiver.onSuccess(OAuth2Login.this);
      } catch (NoTokenException e) {
        receiver.onFailure(e);
      }
    }
  }

  private void fetchAccessToken(String payload) throws NoTokenException {
    postToTokenUrl(payload, new ServerResponseReceiver() {
      @Override
      public void onFailure(Throwable t) {
      }

      @Override
      public void onSuccess(ServerResponse payload) {
        accessToken = payload.getAccessToken();
        // The refresh token isn't repeated in refresh requests
        if (payload.getRefreshToken() != null) {
          refreshToken = payload.getRefreshToken();
        }
        timeout = System.currentTimeMillis() + payload.getExpiryTime() * 1000L;
      }
    });
  }

  /**
   * Returns the last retrieved access token.
   */
  public String getAuthorizationCode() {
    return accessToken;
  }

  protected abstract String getRedirectUri();

  /**
   * Returns the last retrieved refresh code.
   */
  public String getRefreshToken() {
    return refreshToken;
  }

  protected abstract String getResponseType();

  protected void loginWithCode(String code) throws NoTokenException {
    String payload =
        CLIENT_ID + clientId + "&" + CLIENT_SECRET + clientSecret + "&" + CODE + code + "&" + SCOPE
            + scope + "&" + REDIRECT_URI + getRedirectUri() + "&" + GRANT_AUTH;

    fetchAccessToken(payload);
  }

  protected abstract void postToTokenUrl(String payload, ServerResponseReceiver receiver)
      throws NoTokenException;

  protected void refresh() throws NoTokenException {
    String payload =
        CLIENT_ID + clientId + "&" + CLIENT_SECRET + clientSecret + "&" + REFRESH_TOKEN
            + refreshToken + "&" + GRANT_REFRESH;
    fetchAccessToken(payload);
  }
}
