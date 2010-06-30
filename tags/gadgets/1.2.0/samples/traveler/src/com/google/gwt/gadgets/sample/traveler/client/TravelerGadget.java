/*
 * Copyright 2010 Google Inc.
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
package com.google.gwt.gadgets.sample.traveler.client;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.gadgets.client.Gadget;
import com.google.gwt.gadgets.client.GadgetsUtil;
import com.google.gwt.gadgets.client.Gadget.AllowHtmlQuirksMode;
import com.google.gwt.gadgets.client.Gadget.InjectContent;
import com.google.gwt.gadgets.client.Gadget.ModulePrefs;
import com.google.gwt.gadgets.client.Gadget.UseLongManifestName;
import com.google.gwt.gadgets.client.io.Response;
import com.google.gwt.gadgets.client.io.ResponseReceivedHandler;
import com.google.gwt.gadgets.client.osapi.NeedsOsapi;
import com.google.gwt.gadgets.client.osapi.OsapiFeature;
import com.google.gwt.gadgets.client.osapi.people.PeopleService;
import com.google.gwt.gadgets.sample.traveler.client.PersonBrowser.PersonClickedHandler;
import com.google.gwt.gadgets.sample.traveler.client.TravelMap.LocationHandler;
import com.google.gwt.maps.client.Maps;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.RequiresResize;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Traveler Gadget.
 */
// Added comments at the end of each line to break up Eclipse's auto formatting.
@ModulePrefs(//
title = "GWT Traveler!", //
directory_title = "TravelerGadget - Google APIs for GWT", //
author = "Piotr Swigon", //
author_email = "piotrs@google.com", //
author_affiliation = "Google Inc.", //
height = 400, //
thumbnail = "traveler-thumbnail.png", //
screenshot = "traveler-screenshot.png")
// File to inject, which contains CSS to inline into gadget spec.
@InjectContent(files = "inject.html")
@UseLongManifestName(false)
@AllowHtmlQuirksMode(false)
public class TravelerGadget extends Gadget<TravelerPreferences> implements
    NeedsOsapi {

  private static final int FRIENDS_DOCK_HEIGHT = 120;
  private static final String NO_PHOTO_NAME = "traveler/no_avatar.jpg";
  private PeopleService people;
  private TravelerServletClient travelerServletClient;
  private TravelMap personalMap;
  private ErrorNotifier errorNotifier;

  @Override
  protected void init(final TravelerPreferences prefs) {
    Maps.loadMapsApi("", "2", false, new Runnable() {
      public void run() {
        buildUi(prefs);
      }
    });
  }

  private void buildUi(TravelerPreferences prefs) {
    String serverUrl = prefs.serverUrl().getValue();
    if (serverUrl == null || "".equals(serverUrl)) {
      String specUrl = GadgetsUtil.getUrlParameters("url");
      String staticContentUrl = specUrl.substring(0, specUrl.lastIndexOf('/'));
      serverUrl = staticContentUrl.substring(0,
          staticContentUrl.lastIndexOf('/'));
    }
    if (!serverUrl.endsWith("/")) {
      serverUrl += "/";
    }

    travelerServletClient = new TravelerServletClient(serverUrl);
    errorNotifier = ErrorNotifier.getErrorNotifier();

    // Building personal tab

    final ResponseReceivedHandler<Object> refreshWhenDone = new ResponseReceivedHandler<Object>() {
      public void onResponseReceived(ResponseReceivedEvent<Object> event) {
        Response<Object> response = event.getResponse();
        if (response.getStatusCode() == 200) {
          refreshPersonalMap();
        } else {
          errorNotifier.showError(response.getStatusCode(), response.getText());
        }
      }
    };

    LocationHandler createHandler = new LocationHandler() {
      public void handle(Location location) {
        travelerServletClient.saveLocation(location, refreshWhenDone);
      }
    };

    LocationHandler deleteHandler = new LocationHandler() {
      public void handle(Location location) {
        travelerServletClient.deleteLocation(location, refreshWhenDone);
      }
    };

    personalMap = new TravelMap(createHandler, deleteHandler);
    refreshPersonalMap();

    // Building friends tab

    String noPhotoUrl = serverUrl + NO_PHOTO_NAME;
    final PersonBrowser browser = new PersonBrowser(people,
        FRIENDS_DOCK_HEIGHT, noPhotoUrl);
    final TravelMap friendsMap = new TravelMap();

    browser.setPersonClickedHandler(new PersonClickedHandler() {
      public void handle(String id) {
        friendsMap.clear();
        travelerServletClient.getUsersLocations(id,
            new ResponseReceivedHandler<JsArray<Location>>() {
              public void onResponseReceived(
                  ResponseReceivedEvent<JsArray<Location>> event) {
                Response<JsArray<Location>> response = event.getResponse();
                if (response.getStatusCode() == 200) {
                  friendsMap.zoomOut();
                  friendsMap.showLocation(response.getData());
                } else {
                  errorNotifier.showError(response.getStatusCode(),
                      response.getText());
                }
              }
            });
      }
    });

    DockLayoutPanel friendsTab = new DockLayoutPanel(Unit.PX);
    friendsTab.addNorth(browser, FRIENDS_DOCK_HEIGHT);
    friendsTab.add(friendsMap);

    // Building top level panel

    final TabLayoutPanel tabs = new TabLayoutPanel(1.5, Unit.EM);
    tabs.add(personalMap, "My Map");
    tabs.add(friendsTab, "Friends");
    tabs.setSize("100%", "100%");
    tabs.addSelectionHandler(new SelectionHandler<Integer>() {
      public void onSelection(SelectionEvent<Integer> event) {
        Widget widget = tabs.getWidget(event.getSelectedItem());
        if (widget instanceof RequiresResize) {
          ((RequiresResize) widget).onResize();
        }
      }
    });
    RootLayoutPanel.get().add(tabs);
  }

  public void initializeFeature(OsapiFeature feature) {
    people = feature.getPeopleService();
  }

  private void refreshPersonalMap() {
    travelerServletClient.getLocations(new ResponseReceivedHandler<JsArray<Location>>() {
      public void onResponseReceived(
          ResponseReceivedEvent<JsArray<Location>> event) {
        Response<JsArray<Location>> response = event.getResponse();
        if (response.getStatusCode() == 200) {
          personalMap.showLocation(response.getData());
        } else {
          errorNotifier.showError(response.getStatusCode(), response.getText());
        }
      }
    });
  }
}
