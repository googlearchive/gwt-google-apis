/*
 * Copyright 2008 Google Inc.
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
package com.google.gwt.gears.sample.managedresourcestoredemo.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.gears.client.Factory;
import com.google.gwt.gears.client.GearsException;
import com.google.gwt.gears.client.localserver.LocalServer;
import com.google.gwt.gears.client.localserver.ManagedResourceStore;
import com.google.gwt.gears.offline.client.Offline;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Sample application demonstrating how to use the {@link ManagedResourceStore}
 * class provided by the {@link Offline} support.
 */
public class ManagedResourceStoreDemo implements EntryPoint {

  private final Button createManagedResourceStoreButton = new Button(
      "Go Offline");

  private final Button removeManagedResourceStoreButton = new Button(
      "Remove Store");

  private final Label statusLabel = new Label();

  public void onModuleLoad() {
    HorizontalPanel hpanel = new HorizontalPanel();
    hpanel.setSpacing(10);
    RootPanel.get("demo").add(hpanel);

    hpanel.add(createManagedResourceStoreButton);

    // See if we're already running from a ManagedResourceStore
    try {
      LocalServer server = Factory.getInstance().createLocalServer();

      // This check to see if the host page can be served locally
      if (server.canServeLocally(Window.Location.getPath())) {
        createManagedResourceStoreButton.setText("Refresh Manifest");

        // Give the user an opportunity to delete the MRS
        hpanel.add(removeManagedResourceStoreButton);
      }
    } catch (GearsException e) {
      // Gears probably isn't available (e.g. hosted mode)
    }

    createManagedResourceStoreButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        statusLabel.setText("Starting update");
        createManagedResourceStore();
      }
    });

    removeManagedResourceStoreButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        try {
          LocalServer server = Factory.getInstance().createLocalServer();
          ManagedResourceStore store = Offline.getManagedResourceStore();
          server.removeManagedStore(store.getName());
          statusLabel.setText("Removed ManagedResourceStore.  Press Refresh to see Online version.");
          createManagedResourceStoreButton.setEnabled(false);
          removeManagedResourceStoreButton.setEnabled(false);
        } catch (GearsException e) {
          statusLabel.setText(e.getMessage());
        }
      }
    });

    hpanel.add(statusLabel);
  }

  private void createManagedResourceStore() {
    try {
      final ManagedResourceStore managedResourceStore = Offline.getManagedResourceStore();

      new Timer() {
        final String oldVersion = managedResourceStore.getCurrentVersion();

        @Override
        public void run() {
          switch (managedResourceStore.getUpdateStatus()) {
            case ManagedResourceStore.UPDATE_OK:
              if (managedResourceStore.getCurrentVersion().equals(oldVersion)) {
                statusLabel.setText("No update was available.");
              } else {
                statusLabel.setText("Update to "
                    + managedResourceStore.getCurrentVersion()
                    + " was completed.  Please refresh the page to see the changes.");
                createManagedResourceStoreButton.setEnabled(false);
              }
              break;
            case ManagedResourceStore.UPDATE_CHECKING:
            case ManagedResourceStore.UPDATE_DOWNLOADING:
              statusLabel.setText("Transferring data");
              schedule(500);
              break;
            case ManagedResourceStore.UPDATE_FAILED:
              statusLabel.setText(managedResourceStore.getLastErrorMessage());
              break;
          }
        }
      }.schedule(500);

    } catch (GearsException e) {
      statusLabel.setText("");
      Window.alert(e.getMessage());
    }
  }
}
