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
package com.google.api.gwt.samples.buzz.server;

import com.google.api.gwt.buzz.v1.shared.Buzz;
import com.google.api.gwt.samples.buzz.shared.BuzzTest;
import com.google.api.gwt.shared.OAuth2Login;
import com.google.api.gwt.shared.OAuth2Login.OAuth2LoginReceiver;
import com.google.web.bindery.requestfactory.vm.RequestFactorySource;

/**
 * A simple console app to display a user's Buzz posts. If you've already
 * authenticated against OAuth2, put the refresh token as the first argument to
 * avoid the need to re-authorize the app.
 */
public class BuzzMain extends BuzzTest {
  private static final String CLIENT_SECRET = "ccGEHcxNqCseZAM9e8oqaMUm";
  private static final String CLIENT_ID = "795143362888.apps.googleusercontent.com";

  public static void main(String[] args) {
    final String refreshToken = args.length == 1 ? args[0] : null;
    new BuzzMain().execImpl(refreshToken);
  }

  @Override
  protected void println(String value) {
    System.out.println(value);
  }

  void execImpl(String refreshToken) {
    loginBuilder()//
        .setClientId(CLIENT_ID)//
        .setClientSecret(CLIENT_SECRET)//
        .setCodeProvider(new ConsoleCodeProvider())//
        .setRefreshToken(refreshToken)//
        .build(new OAuth2LoginReceiver() {
          @Override
          public void onSuccess(OAuth2Login login) {
            System.out.println("Refresh code: " + login.getRefreshToken());
            initializeTransport(login.getAuthorizationCode());
          }

          @Override
          public void onFailure(Throwable t) {
            t.printStackTrace();
          }
        });
  }

  @Override
  protected Buzz createBuzz() {
    return RequestFactorySource.create(Buzz.class);
  }
}
