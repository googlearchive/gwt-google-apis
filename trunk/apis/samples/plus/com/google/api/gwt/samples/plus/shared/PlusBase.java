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
package com.google.api.gwt.samples.plus.shared;

import com.google.api.gwt.services.plus.shared.Plus;
import com.google.api.gwt.services.plus.shared.Plus.ActivitiesContext.ListRequest.Collection;
import com.google.api.gwt.services.plus.shared.Plus.PlusAuthScope;
import com.google.api.gwt.services.plus.shared.model.ActivityFeed;
import com.google.api.gwt.services.plus.shared.model.Activity;
import com.google.api.gwt.services.plus.shared.model.Person;
import com.google.api.gwt.shared.GoogleApiRequestTransport;
import com.google.api.gwt.shared.OAuth2Login;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;

/**
 * Demo code for reading a user's Plus circles. The concrete subclass
 * {@link PlusModule} provides the client-specific usage. This class is suitable
 * for tests or to be used on the server-side.
 */
public abstract class PlusBase {
  protected static final String CLIENT_ID = "692753340433.apps.googleusercontent.com";
  private static final String API_KEY = "AIzaSyA5bNyuRQFaTQle_YC5BUH7tQzRmAPiqsM";
  private static final String APPLICATION_NAME = "PlusSample/1.0";

  public void login(OAuth2Login loginImpl, final GoogleApiRequestTransport transportImpl) {
    loginImpl.withScopes(PlusAuthScope.PLUS_ME).login(new Receiver<String>() {
      @Override
      public void onSuccess(String accessToken) {
        initializeTransport(transportImpl, accessToken);
      }
    });
  }

  protected void initializeTransport(GoogleApiRequestTransport impl, String authorization) {
    impl.setAccessToken(authorization)
        .setApplicationName(APPLICATION_NAME)
        .setApiAccessKey(API_KEY)
        .create(new Receiver<GoogleApiRequestTransport>() {
          @Override
          public void onSuccess(GoogleApiRequestTransport transport) {
            Plus plus = createPlus();
            plus.initialize(new SimpleEventBus(), transport);
            getMe(plus);
          }
        });
  }

  public void getMe(final Plus plus) {
    plus.people().get("me").to(new Receiver<Person>() {
      @Override
      public void onSuccess(Person person) {
        println("Hello " + person.getDisplayName());

        getMyActivities(plus);
      }
    }).fire();
  }

  public void getMyActivities(Plus plus) {
    plus.activities().list("me", Collection.PUBLIC).to(new Receiver<ActivityFeed>() {
      @Override
      public void onSuccess(ActivityFeed feed) {
        println("===== PUBLIC ACTIVITIES =====");
        if (feed.getItems() == null || feed.getItems().isEmpty()) {
          println("You have no public activities");
        } else {
          for (Activity a : feed.getItems()) {
            println(a.getTitle());
          }
        }
      }
    }).fire();
  }

  protected abstract Plus createPlus();

  protected abstract void println(String value);
}
