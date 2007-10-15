/*
 * Copyright 2007 Google Inc.
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
package com.google.gwt.gears.sample.managedresourcestore.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.gears.core.client.GearsException;
import com.google.gwt.gears.localserver.client.LocalServer;
import com.google.gwt.gears.localserver.client.ManagedResourceStore;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Sample application demonstrating how to use the {@link ManagedResourceStore}
 * class.
 */
public class ManagedResourceStoreDemo implements EntryPoint {
  private static final String MANAGED_STORE_NAME = "ManagedResourceStoreDemo";
  private static final String MANIFEST_URL = "manifest_v1.json";

  private final Button createManagedResourceStoreButton = new Button(
      "Map URL");

  private final Label statusLabel = new Label();

  public void onModuleLoad() {
    createManagedResourceStoreButton.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        createManagedResourceStore();
      }
    });

    HorizontalPanel hpanel = new HorizontalPanel();
    hpanel.add(createManagedResourceStoreButton);
    hpanel.add(statusLabel);
    RootPanel.get().add(hpanel);
  }

  private void createManagedResourceStore() {
    LocalServer localServer;
    try {
      localServer = new LocalServer();
      ManagedResourceStore oldManagedResourceStore = localServer.openManagedResourceStore(MANAGED_STORE_NAME); 
      if (oldManagedResourceStore != null) {
        oldManagedResourceStore.setManifestURL("");
        localServer.removeManagedResourceStore(MANAGED_STORE_NAME);
        oldManagedResourceStore = localServer.openManagedResourceStore(MANAGED_STORE_NAME); 
      }

      final ManagedResourceStore managedResourceStore = localServer.createManagedResourceStore(MANAGED_STORE_NAME);
      managedResourceStore.setManifestURL(MANIFEST_URL);
      managedResourceStore.checkForUpdate();

      new Timer() {
        public void run() {
          switch (managedResourceStore.getUpdateStatus()) {
            case ManagedResourceStore.UPDATE_OK:
              statusLabel.setText("Mapping to " + managedResourceStore.getCurrentVersion() + " was completed.  Please click on the \"Compile/Browse\" button to see the changes.");
              break;
            case ManagedResourceStore.UPDATE_CHECKING:
            case ManagedResourceStore.UPDATE_DOWNLOADING:
              schedule(500);
              break;
            case ManagedResourceStore.UPDATE_FAILED:
              statusLabel.setText(managedResourceStore.getLastErrorMessage());
              break;
          }
        }
      }.schedule(500);

    } catch (GearsException e) {
      Window.alert(e.getMessage());
    }
  }
}
