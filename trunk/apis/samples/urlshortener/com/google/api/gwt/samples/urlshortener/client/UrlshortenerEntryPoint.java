/*
 * Copyright (c) 2011 Google Inc.
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

package com.google.api.gwt.samples.urlshortener.client;

import com.google.api.gwt.client.impl.ClientGoogleApiRequestTransport;
import com.google.api.gwt.client.impl.ClientOAuth2Login;
import com.google.api.gwt.samples.urlshortener.shared.Url;
import com.google.api.gwt.samples.urlshortener.shared.Urlshortener;
import com.google.api.gwt.samples.urlshortener.shared.Urlshortener.UrlContext;
import com.google.api.gwt.samples.urlshortener.shared.UrlshortenerAuthScope;
import com.google.api.gwt.shared.GoogleApiRequestTransport;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

/**
 * Simple sample app that retrieves information about a short URL and shortens a
 * long URL.
 *
 * @author jasonhall@google.com (Jason Hall)
 */
public class UrlshortenerEntryPoint implements EntryPoint {

  private static final String CLIENT_ID = "692753340433.apps.googleusercontent.com";
  private static final String API_KEY = "AIzaSyA5bNyuRQFaTQle_YC5BUH7tQzRmAPiqsM";
  private static final String APPLICATION_NAME = "UrlshortenerSample/1.0";

  private final Urlshortener urlShortener = GWT.create(Urlshortener.class);

  @Override
  public void onModuleLoad() {
    login();
  }

  /** Demonstrates authentication using OAuth 2.0. */
  private void login() {
    final Button button = new Button("Log in to get started");
    button.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        new ClientOAuth2Login(CLIENT_ID)
            .withScopes(UrlshortenerAuthScope.URLSHORTENER)
            .login(new Receiver<String>() {
              @Override
              public void onSuccess(String accessToken) {
                initialize(accessToken);
              }
            });
        button.setVisible(false);
      }
    });
    RootPanel.get().add(button);
  }

  /** Demonstrates initialization of the RequestTransport. */
  private void initialize(String accessToken) {
    new ClientGoogleApiRequestTransport()
        .setApiAccessKey(API_KEY)
        .setApplicationName(APPLICATION_NAME)
        .setAccessToken(accessToken)
        .create(new Receiver<GoogleApiRequestTransport>() {
          @Override
          public void onSuccess(GoogleApiRequestTransport transport) {
            urlShortener.initialize(new SimpleEventBus(), transport);

            expand();
          }

          @Override
          public void onFailure(ServerFailure error) {
            Window.alert("Failed to initialize Transport");
          }
        });
  }

  /** Demonstrates expanding a short URL by requesting its information. */
  private void expand() {
    String shortUrl = Window.prompt("Enter a short URL", "http://goo.gl/xwrT5");

    // Get a new RequestContext which we will execute.
    UrlContext urlContext = urlShortener.url();

    // Fire a get() request with the short URL we want to expand.
    urlContext.get(shortUrl).fire(new Receiver<Url>() {
      @Override
      public void onSuccess(Url response) {
        Window.alert("Long URL: " + response.getLongUrl() + "\n" //
            + "Short URL: " + response.getId() + "\n" //
            + "Status: " + response.getStatus());

        shorten();
      }

      @Override
      public void onFailure(ServerFailure error) {
        Window.alert("Error expanding a URL\n" + error.getMessage());
      }
    });
  }

  /** Demonstrates shortening a URL by inserting a new {@link Url} object. */
  private void shorten() {
    String longUrl = Window.prompt("Enter a long URL",
        "http://gwt-google-apis.googlecode.com/svn/trunk/apis/samples/urlshortener/demo/"
            + "urlshortener.html");

    // Get a new RequestContext which we will execute.
    UrlContext urlContext = urlShortener.url();

    // Create a new Url instance with the longUrl we want to insert.
    Url url = urlContext.create(Url.class).setLongUrl(longUrl);

    // Fire an insert() request with the Url to insert.
    urlContext.insert(url).fire(new Receiver<Url>() {
      @Override
      public void onSuccess(Url response) {
        Window.alert("Long URL: " + response.getLongUrl() + "\n" //
            + "Short URL: " + response.getId() + "\n" //
            + "Status: " + response.getStatus());
      }

      @Override
      public void onFailure(ServerFailure error) {
        Window.alert("Error shortening a URL\n" + error.getMessage());
      }
    });
  }
}
