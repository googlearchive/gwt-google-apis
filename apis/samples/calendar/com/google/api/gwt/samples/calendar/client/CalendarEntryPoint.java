/*
 * Copyright 2012 Google Inc.
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

package com.google.api.gwt.samples.calendar.client;

import com.google.api.gwt.client.GoogleApiRequestTransport;
import com.google.api.gwt.client.OAuth2Login;
import com.google.api.gwt.services.calendar.shared.Calendar;
import com.google.api.gwt.services.calendar.shared.Calendar.CalendarAuthScope;
import com.google.api.gwt.services.calendar.shared.Calendar.CalendarListContext.ListRequest.MinAccessRole;
import com.google.api.gwt.services.calendar.shared.Calendar.EventsContext;
import com.google.api.gwt.services.calendar.shared.model.CalendarList;
import com.google.api.gwt.services.calendar.shared.model.Event;
import com.google.api.gwt.services.calendar.shared.model.EventDateTime;
import com.google.api.gwt.shared.EmptyResponse;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.web.bindery.requestfactory.shared.Receiver;

import java.util.Date;

/**
 * A simple GWT module that performs some common Calendar API operations:
 *
 * <ul>
 *   <li>List calendars that the authenticated user has write access to</li>
 *   <li>Insert a new event</li>
 *   <li>Retrieve the newly-inserted event</li>
 *   <li>Update the event</li>
 *   <li>Delete the event</li>
 * </ul>
 */
public class CalendarEntryPoint implements EntryPoint {

  private static final String CLIENT_ID = "692753340433.apps.googleusercontent.com";
  private static final String API_KEY = "AIzaSyA5bNyuRQFaTQle_YC5BUH7tQzRmAPiqsM";
  private static final String APPLICATION_NAME = "CalendarSample/1.0";
  private static final Calendar calendar = GWT.create(Calendar.class);

  @Override
  public void onModuleLoad() {
    calendar.initialize(new SimpleEventBus(),
        new GoogleApiRequestTransport(APPLICATION_NAME, API_KEY));

    final Button b = new Button("Authenticate to insert, update and delete an event");
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
    OAuth2Login.get().authorize(CLIENT_ID, CalendarAuthScope.CALENDAR,
        new Callback<Void, Exception>() {
          @Override
          public void onSuccess(Void v) {
            getCalendarId();
          }

          @Override
          public void onFailure(Exception e) {
            GWT.log("Auth failed:", e);
          }
        });
  }

  /** Gets the calendar ID of some calendar that the user can write to. */
  private void getCalendarId() {
    // We need to find an ID of a calendar that we have permission to write events to. We'll just
    // pick the first one that gets returned, and we will delete the event when we're done.
    calendar.calendarList().list().setMinAccessRole(MinAccessRole.OWNER)
        .fire(new Receiver<CalendarList>() {
          @Override
          public void onSuccess(CalendarList list) {
            String calendarId = list.getItems().get(0).getId();

            insertEvent(calendarId);
          }
        });
  }

  /** Insert a new event for the given calendar ID. */
  private void insertEvent(final String calendarId) {
    String today = DateTimeFormat.getFormat("yyyy-MM-dd").format(new Date());
    EventsContext ctx = calendar.events();
    Event event = ctx.create(Event.class)
        .setSummary("Learn about the Google API GWT client library")
        .setStart(ctx.create(EventDateTime.class).setDateTime(today))
        .setEnd(ctx.create(EventDateTime.class).setDateTime(today));

    // Note that the EventsContext used to insert the Event has to be the same one used to create
    // it.
    ctx.insert(calendarId, event).fire(new Receiver<Event>() {
      @Override
      public void onSuccess(Event inserted) {
        // The event has been inserted.

        // Now we'll demonstrate retrieving it and updating it.
        String eventId = inserted.getId();
        getEventForUpdate(calendarId, eventId);
      }
    });
  }

  /** Get an event for the purposes of updating it. */
  private void getEventForUpdate(final String calendarId, final String eventId) {
    final EventsContext ctx = calendar.events();
    ctx.get(calendarId, eventId).fire(new Receiver<Event>() {
      @Override
      public void onSuccess(Event event) {
        // Note that the EventsContext used to update the event has to be the same one that was
        // used to retrieve it.
        updateEvent(ctx, event, calendarId, eventId);
      }
    });
  }

  /** Update an event that was previously retrieved. */
  private void updateEvent(EventsContext ctx, Event event, final String calendarId,
      final String eventId) {
    String newSummary = "";
    while (newSummary.isEmpty()) {
      newSummary = Window.prompt("Provide a new name for the event", "");
    }
    Event editableEvent = ctx.edit(event); // Don't forget to call edit()
    editableEvent.setSummary(newSummary);
    ctx.update(calendarId, eventId, editableEvent).fire(new Receiver<Event> (){
      @Override
      public void onSuccess(Event updated) {
        // The event has been updated. Now we'll delete it.

        deleteEvent(calendarId, eventId);
      }
    });
  }

  /** Delete an event by its ID. */
  private void deleteEvent(String calendarId, String eventId) {
    calendar.events().delete(calendarId, eventId).fire(new Receiver<EmptyResponse>() {
      @Override
      public void onSuccess(EmptyResponse r) {
        // The event has been deleted. And we're done!
        Window.alert("Event deleted! Demo complete!");
      }
    });
  }
}
