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

import com.google.api.gwt.samples.urlshortener.shared.Url;
import com.google.api.gwt.samples.urlshortener.shared.Urlshortener;
import com.google.api.gwt.samples.urlshortener.shared.Urlshortener.UrlContext;
import com.google.api.gwt.shared.GoogleApiRequestTransport;
import com.google.api.gwt.shared.GoogleApiRequestTransport.RequestTransportReceiver;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

/**
 * Simple sample app that retrieves information about a short URL and shortens a
 * long URL.
 *
 * @author jasonhall@google.com (Jason Hall)
 */
public class UrlshortenerEntryPoint implements EntryPoint {

  // Instantiate the service class.
  private final Urlshortener urlShortener = GWT.create(Urlshortener.class);

  @Override
  public void onModuleLoad() {
    initialize();
  }

  /** Demonstrates initialization of the RequestTransport. */
  private void initialize() {
    new GoogleApiRequestTransport.Builder() //
        .build(new RequestTransportReceiver() {
          @Override
          public void onSuccess(GoogleApiRequestTransport transport) {
            urlShortener.initialize(new SimpleEventBus(), transport);

            expand();
          }

          @Override
          public void onFailure(Throwable cause) {
            Window.alert("Failed to initialize Transport");
          }
        });
  }

  /** Demonstrates expanding a short URL by requesting its information. */
  private void expand() {
    String shortUrl = Window.prompt("Enter a short URL", "");

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
    String longUrl = Window.prompt("Enter a long URL", "");

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
