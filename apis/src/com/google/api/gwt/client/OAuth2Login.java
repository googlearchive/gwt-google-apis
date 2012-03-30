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

package com.google.api.gwt.client;

import com.google.api.gwt.shared.AuthScope;
import com.google.gwt.core.client.Callback;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Class to manage OAuth 2.0 authentication with Google APIs.
 *
 * @author jasonhall@google.com (Jason Hall)
 */
public abstract class OAuth2Login {

  private static Impl impl;

  public static final OAuth2Login get() {
    if (impl == null) {
      impl = new Impl();
    }
    return impl;
  }

  public void authorize(String clientId, AuthScope scope, Callback<Void, Exception> callback) {
    authorize(clientId, Arrays.asList(scope), callback);
  }

  public abstract void authorize(
      final String clientId, Iterable<AuthScope> scopes, Callback<Void, Exception> callback);

  private static final class Impl extends OAuth2Login {
    @Override
    public final void authorize(final String clientId, Iterable<AuthScope> scopes,
        final Callback<Void, Exception> callback) {
      if (!scopes.iterator().hasNext()) {
        throw new IllegalArgumentException("Must specify some auth scopes");
      }

      final String scopesString = scopesToString(scopes);
      // Ensure that the API library is loaded. If it is, this callback will be executed
      // immediately.
      GoogleApiLoader.get().load(new Callback<Void, Exception>() {
        @Override
        public void onSuccess(Void v) {
          nativeAuthorize(clientId, scopesString, callback);
        }

        @Override
        public void onFailure(Exception e) {
          callback.onFailure(e);
        }
      });
    }

    private static final native void nativeAuthorize(String clientId, String scope,
        Callback<Void, Exception> callback) /*-{
      $wnd.gapi.auth.authorize({
        client_id: clientId,
        scope: scope
      }, $entry(function(authResult) {
        if (authResult) {
          callback.@com.google.gwt.core.client.Callback::onSuccess(*)(null);
        } else {
          callback.@com.google.gwt.core.client.Callback::onFailure(*)(null);
        }
      }));
    }-*/;

    private static String scopesToString(Iterable<AuthScope> scopes) {
      StringBuilder sb = new StringBuilder();
      Iterator<AuthScope> it = scopes.iterator();
      while (it.hasNext()) {
        sb.append(it.next().getScope());
        if (it.hasNext()) {
          sb.append(' ');
        }
      }
      return sb.toString();
    }
  }
}
