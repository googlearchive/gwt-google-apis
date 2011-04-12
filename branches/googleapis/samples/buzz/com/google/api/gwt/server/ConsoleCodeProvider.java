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

import com.google.api.gwt.shared.OAuth2Login.CodeProvider;
import com.google.api.gwt.shared.OAuth2Login.CodeReceiver;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * A very simple IO provider that uses standard in to read the authorization
 * code.
 *
 */
public class ConsoleCodeProvider implements CodeProvider {

  @Override
  public void getCode(String url, CodeReceiver callback) {
    try {
      if (Desktop.isDesktopSupported()) {
        Desktop.getDesktop().browse(new URI(url));
      } else {
        System.out.println("Open a browser at " + url);
      }
      System.out.println("Input temporary authorization code");
      callback.onSuccess(new BufferedReader(new InputStreamReader(System.in)).readLine());
    } catch (IOException e) {
      callback.onFailure(e);
    } catch (URISyntaxException e) {
      callback.onFailure(e);
    }
  }
}
