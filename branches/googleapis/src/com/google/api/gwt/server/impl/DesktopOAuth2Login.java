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
package com.google.api.gwt.server.impl;

import com.google.api.gwt.shared.OAuth2Login;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.google.web.bindery.autobean.vm.AutoBeanFactorySource;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Intended to be constructed via {@link OAuth2Login.Builder}.
 */
public class DesktopOAuth2Login extends OAuth2Login {
  interface Factory extends AutoBeanFactory {
    AutoBean<ServerResponse> response();
  }

  private static final Factory FACTORY = AutoBeanFactorySource.create(Factory.class);

  @Override
  protected String getRedirectUri() {
    return "oob";
  }

  @Override
  protected String getResponseType() {
    return "code";
  }

  @Override
  protected void postToTokenUrl(String payload, ServerResponseReceiver receiver)
      throws NoTokenException {
    try {
      URL url = new URL(tokenUrl);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("POST");
      conn.setUseCaches(false);
      conn.setDoInput(true);
      conn.setDoOutput(true);

      conn.connect();

      PrintStream out = new PrintStream(conn.getOutputStream());
      out.print(payload);
      out.flush();
      out.close();

      String contents = SimpleRequestTransport.readResponse(conn);

      ServerResponse response = AutoBeanCodex.decode(FACTORY, ServerResponse.class, contents).as();
      receiver.onSuccess(response);
    } catch (IOException e) {
      throw new NoTokenException(e);
    }
  }
}
