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

package com.google.api.gwt.samples.plus.client;

import com.google.api.gwt.client.GoogleApiRequestTransport;
import com.google.api.gwt.client.OAuth2Login;
import com.google.api.gwt.services.plus.shared.Plus;
import com.google.api.gwt.services.plus.shared.Plus.ActivitiesContext.ListRequest.Collection;
import com.google.api.gwt.services.plus.shared.Plus.PlusAuthScope;
import com.google.api.gwt.services.plus.shared.model.Activity;
import com.google.api.gwt.services.plus.shared.model.ActivityFeed;
import com.google.api.gwt.services.plus.shared.model.Person;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.web.bindery.requestfactory.shared.Receiver;

/**
 * A simple GWT module to display a user's activities.
 */
public class PlusEntryPoint implements EntryPoint {

  private static final Plus plus = GWT.create(Plus.class);
  private static final String CLIENT_ID = "692753340433.apps.googleusercontent.com";
  private static final String API_KEY = "AIzaSyA5bNyuRQFaTQle_YC5BUH7tQzRmAPiqsM";
  private static final String APPLICATION_NAME = "PlusSample/1.0";

  @Override
  public void onModuleLoad() {
    plus.initialize(new SimpleEventBus(), new GoogleApiRequestTransport(APPLICATION_NAME, API_KEY));

    final Button b = new Button("Authenticate to get public activities");
    b.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent e) {
        login();
        b.setVisible(false);
      }
    });
    RootPanel.get().add(b);
  }

  private void login() {
    OAuth2Login.get().authorize(CLIENT_ID, PlusAuthScope.PLUS_ME, new Callback<Void, Exception>() {
      @Override
      public void onSuccess(Void v) {
        getMe();
      }

      @Override
      public void onFailure(Exception e) {
        println(e.getMessage());
      }
    });
  }

  private void getMe() {
    plus.people().get("me").to(new Receiver<Person>() {
      @Override
      public void onSuccess(Person person) {
        println("Hello " + person.getDisplayName());

        getMyActivities();
      }
    }).fire();
  }

  private void getMyActivities() {
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

  private void println(String msg) {
    RootPanel.get().add(new Label(msg));
  }
}
