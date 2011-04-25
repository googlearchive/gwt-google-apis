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
package com.google.api.gwt.samples.buzz.shared;

import com.google.api.gwt.buzz.v1.shared.Buzz;
import com.google.api.gwt.buzz.v1.shared.Buzz.ActivitiesContext.ListRequest.Scope;
import com.google.api.gwt.buzz.v1.shared.model.Activity;
import com.google.api.gwt.buzz.v1.shared.model.ActivityFeed;
import com.google.api.gwt.buzz.v1.shared.model.Comment;
import com.google.api.gwt.buzz.v1.shared.model.Person;
import com.google.api.gwt.shared.GoogleApiRequestTransport;
import com.google.api.gwt.shared.OAuth2Login;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;

/**
 * Demo code for reading a user's Buzz feed. The two concrete subclasses provide
 * environment-specific bootstrapping.
 */
public abstract class BuzzBase {
  protected static final String CLIENT_ID = "629256018955.apps.googleusercontent.com";
  private static final String API_KEY = "AIzaSyCJLFXyjqx604qverIinL9UAevgvPyjJjE";
  private static final String APPLICATION_NAME = "BuzzSample/1.0";


  public void login(OAuth2Login loginImpl, final GoogleApiRequestTransport transportImpl) {
    loginImpl.withScopes(BuzzAuthScope.BUZZ_READONLY).login(new Receiver<String>() {
      @Override
      public void onSuccess(String accessToken) {
        initializeTransport(transportImpl, accessToken);
      }
    });
  }

  protected void initializeTransport(GoogleApiRequestTransport impl, String authorization) {
    impl.setAccessToken(authorization)
        .setApiAccessKey(API_KEY)
        .setApplicationName(APPLICATION_NAME)
        .create(new Receiver<GoogleApiRequestTransport>() {
          @Override
          public void onSuccess(GoogleApiRequestTransport transport) {
            Buzz buzz = createBuzz();
            buzz.initialize(new SimpleEventBus(), transport);
            getMe(buzz);
          }
        });
  }

  public void getMe(final Buzz buzz) {
    buzz.people().get("@me").to(new Receiver<Person>() {
      @Override
      public void onSuccess(Person response) {
        println("Hello " + response.getDisplayName());
        getMyPosts(buzz);
      }
    }).fire();
  }

  public void getMyPosts(Buzz buzz) {
    buzz.activities().list("@me", Scope.SELF)
        .setMaxResults(5)
        .setMaxComments(5)
        .setMaxLiked(5)
        .to(new Receiver<ActivityFeed>() {
          @Override
          public void onSuccess(ActivityFeed response) {
            for (Activity a : response.getItems()) {
              println(a.getTitle());
              if (a.getBuzzObject().getComments() != null) {
                for (Comment comment : a.getBuzzObject().getComments()) {
                  println(">>> " + comment.getContent());
                }
              }
            }
          }
        })
        .fire();
  }

  protected abstract Buzz createBuzz();

  protected abstract void println(String value);
}
