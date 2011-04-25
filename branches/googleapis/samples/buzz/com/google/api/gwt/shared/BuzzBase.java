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
import com.google.api.gwt.shared.GoogleApiRequestTransport.RequestTransportReceiver;
import com.google.api.gwt.shared.OAuth2Login;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;

/**
 * Demo code for reading a user's Buzz feed. The two concrete subclasses provide
 * environment-specific bootstrapping.
 */
public abstract class BuzzTest {
  protected abstract Buzz createBuzz();

  public void doTest(final Buzz buzz) {
    buzz.people().get("@me").to(new Receiver<Person>() {
      @Override
      public void onSuccess(Person response) {
        println("Hello " + response.getDisplayName());
        getMyPosts(buzz);
      }
    }).fire();
  }

  public void getMyPosts(Buzz buzz) {
    buzz.activities().list("@me", Scope.SELF) //
        .setMaxResults(5).setMaxComments(5).setMaxLiked(5) //
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
        }) //
        .fire();
  }

  protected void initializeTransport(String authorization) {
    new GoogleApiRequestTransport.Builder() //
        .setAccessToken(authorization) //
        .setApiAccessKey("AIzaSyCJLFXyjqx604qverIinL9UAevgvPyjJjE") //
        .setApplicationName("BuzzTest/1.0") //
        .build(new RequestTransportReceiver() {
          @Override
          public void onFailure(Throwable cause) {
            cause.printStackTrace();
          }

          @Override
          public void onSuccess(GoogleApiRequestTransport transport) {
            Buzz buzz = createBuzz();
            buzz.initialize(new SimpleEventBus(), transport);
            doTest(buzz);
          }
        });
  }

  protected OAuth2Login.Builder loginBuilder() {
    return new OAuth2Login.Builder().addScope("https://www.googleapis.com/auth/buzz");
  }

  protected abstract void println(String value);
}
