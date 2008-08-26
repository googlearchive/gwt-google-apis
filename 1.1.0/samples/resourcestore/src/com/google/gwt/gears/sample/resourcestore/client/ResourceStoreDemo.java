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
package com.google.gwt.gears.sample.resourcestore.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.gears.client.Factory;
import com.google.gwt.gears.client.localserver.LocalServer;
import com.google.gwt.gears.client.localserver.ResourceStore;
import com.google.gwt.gears.client.localserver.ResourceStoreUrlCaptureHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Sample application which demonstrates how to use the {@link ResourceStore}
 * class.
 */
public class ResourceStoreDemo implements EntryPoint {
  private static final String RESOURCE_STORE_NAME = "helloworld-store";
  private static final String[] FILES_TO_CAPTURE = {
      getLocationPathName(), "resource.txt"};

  private static native String getLocationPathName() /*-{
    return $doc.location.href;
  }-*/;

  private final HorizontalPanel buttonPanel = new HorizontalPanel();
  private final Button captureButton = new Button("Capture");
  private final Button createStoreButton = new Button("Create Store");
  private LocalServer localServer;
  private final Button removeStoreButton = new Button("Remove Store");

  private ResourceStore resourceStore;
  private final Label statusLabel = new Label();

  private final Button uncaptureButton = new Button("Uncapture");

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    captureButton.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        captureUrls();
      }
    });

    createStoreButton.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        createResourceStore();
      }
    });

    removeStoreButton.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        removeStore();
      }
    });

    uncaptureButton.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        removeURLs();
      }
    });

    buttonPanel.add(createStoreButton);
    buttonPanel.add(captureButton);
    buttonPanel.add(uncaptureButton);
    buttonPanel.add(removeStoreButton);

    RootPanel.get().add(buttonPanel);
    RootPanel.get().add(statusLabel);

    localServer = Factory.getInstance().createLocalServer();
  }

  private void captureUrls() {
    statusLabel.setText("Capturing...");
    resourceStore.capture(new ResourceStoreUrlCaptureHandler() {
      public void onCapture(ResourceStoreUrlCaptureEvent event) {
        if (event.isSucceess()) {
          statusLabel.setText("Captured URL: " + event.getUrl());
        } else {
          statusLabel.setText("Failed to capture URL: " + event.getUrl());
        }
      }
    }, FILES_TO_CAPTURE);
  }

  private void createResourceStore() {
    resourceStore = localServer.createStore(RESOURCE_STORE_NAME);
    statusLabel.setText("Created a ResourceStore");
  }

  private void removeStore() {
    if (localServer.openStore(RESOURCE_STORE_NAME) != null) {
      localServer.removeStore(RESOURCE_STORE_NAME);
      statusLabel.setText("Removed store '" + RESOURCE_STORE_NAME + "'");
      resourceStore = null;
    } else {
      statusLabel.setText("The store '" + RESOURCE_STORE_NAME
          + "' does not exist.");
    }
  }

  private void removeURLs() {
    for (int i = 0; i < FILES_TO_CAPTURE.length; ++i) {
      String url = FILES_TO_CAPTURE[i];
      resourceStore.remove(url);
    }
    statusLabel.setText("Removed URLs from the store");
  }
}
