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

import com.google.api.gwt.shared.OAuth2Login.CodeProvider;
import com.google.api.gwt.shared.OAuth2Login.CodeReceiver;
import com.google.gwt.user.client.Window;

/**
 * Pops up a new window to walk the user through the OAuth2 login process. Waits
 * for the helper window to call a function registered on the main window with
 * the hash returned from the login server.
 */
class WindowCodeProvider implements CodeProvider {


  private static native void register(WindowCodeProvider provider) /*-{
    $wnd.doLogin = $entry(function(hash) {
      $wnd.doLogin = null;
      provider.@com.google.api.gwt.client.impl.WindowCodeProvider::finish(*)
        (hash);
    });
  }-*/;

  private CodeReceiver callback;

  @Override
  public void getCode(String url, CodeReceiver callback) {
    register(this);
    this.callback = callback;
    Window.open(url, "windowCodeProviderLogin", "width=400,height=600");
  }

  void finish(String hash) {
    String[] parts = hash.substring(1).split("&");
    for (String part : parts) {
      String[] bits = part.split("=");
      if ("access_token".equals(bits[0])) {
        callback.onSuccess(bits[1]);
        return;
      }
    }
    callback.onFailure(new RuntimeException("Could not find access_token in hash " + hash));
  }
}
