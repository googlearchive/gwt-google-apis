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

package com.google.api.gwt.samples.calendar.client;

import com.google.api.gwt.client.impl.ClientGoogleApiRequestTransport;
import com.google.api.gwt.client.impl.ClientOAuth2Login;
import com.google.api.gwt.services.calendar.shared.Calendar;
import com.google.api.gwt.services.calendar.shared.Calendar.CalendarAuthScope;
import com.google.api.gwt.services.calendar.shared.model.Event;
import com.google.api.gwt.services.calendar.shared.model.Events;
import com.google.api.gwt.shared.GoogleApiRequestTransport;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

import java.util.Date;

/**
 * Simple sample app that retrieves upcoming events from Google Calendar.
 *
 * @author jasonhall@google.com (Jason Hall)
 */
public class CalendarEntryPoint implements EntryPoint {

  private static final String CLIENT_ID = "692753340433.apps.googleusercontent.com";
  private static final String API_KEY = "AIzaSyA5bNyuRQFaTQle_YC5BUH7tQzRmAPiqsM";
  private static final String APPLICATION_NAME = "CalendarSample/1.0";

  private final Calendar calendar = GWT.create(Calendar.class);

  @Override
  public void onModuleLoad() {
    login();
  }

  public void login() {
    final Button button = new Button("Log in to get started");
    button.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        new ClientOAuth2Login(CLIENT_ID)
            .withScopes(CalendarAuthScope.CALENDAR)
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

  public void initialize(final String accessToken) {
    new ClientGoogleApiRequestTransport()
        .setApiAccessKey(API_KEY)
        .setApplicationName(APPLICATION_NAME)
        .setAccessToken(accessToken)
        .create(new Receiver<GoogleApiRequestTransport>() {
          @Override
          public void onSuccess(GoogleApiRequestTransport transport) {
            SimpleEventBus eventBus = new SimpleEventBus();
            calendar.initialize(eventBus, transport);

            String calendarId = Window.prompt("Provide a Calendar ID (try your email address)", "");
            listEvents(calendarId);
          }

          @Override
          public void onFailure(ServerFailure failure) {
            Window.alert("Failed to initialize transport");
          }
        });
  }

  private String today() {
    String today = DateTimeFormat.getFormat("yyyy-MM-dd'T'00:00:00-00:00").format(new Date());
    println("Today is " + today);
    return today;
  }

  public void listEvents(String calendarId) {
    calendar.events().list(calendarId)
        .setTimeMin(today())
        .to(new Receiver<Events>() {
      @Override
      public void onSuccess(Events events) {
        println("=== UPCOMING EVENTS ===");
        for (Event event : events.getItems()) {
          printLink(event.getSummary(), event.getHtmlLink());
        }
      }
    }).fire();
  }

  private void println(String msg) {
    RootPanel.get().add(new Label(msg));
  }

  private void printLink(String text, String url) {
    RootPanel.get().add(new Anchor(text, url));
  }
}
