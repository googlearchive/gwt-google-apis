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
package com.google.gwt.gears.sample.resourcestore.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.gears.core.client.GearsException;
import com.google.gwt.gears.localserver.client.LocalServer;
import com.google.gwt.gears.localserver.client.ResourceStore;
import com.google.gwt.gears.localserver.client.URLCaptureCallback;
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
      getLocationPathName(), "ResourceStoreDemo.css"};

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

    try {
      localServer = new LocalServer();
    } catch (GearsException e) {
      statusLabel.setText(e.getMessage());
    }
  }

  private void captureUrls() {
    statusLabel.setText("Capturing...");
    try {
      int pendingCaptureId = resourceStore.captureURLs(FILES_TO_CAPTURE,
          new URLCaptureCallback() {
            public void onCaptureFailure(String url, int captureId) {
              statusLabel.setText("Failed to capture URL: " + url);
            }

            public void onCaptureSuccess(String url, int captureId) {
              statusLabel.setText("Captured URL: " + url);
            }
          });
    } catch (GearsException e) {
      statusLabel.setText(e.getMessage());
    }
  }

  private void createResourceStore() {
    try {
      resourceStore = localServer.createResourceStore(RESOURCE_STORE_NAME);
      statusLabel.setText("Created a ResourceStore");
    } catch (GearsException e) {
      statusLabel.setText("Failed to create the resource store");
    }
  }

  private void removeStore() {
    try {
      if (localServer.openResourceStore(RESOURCE_STORE_NAME) != null) {
        localServer.removeResourceStore(RESOURCE_STORE_NAME);
        statusLabel.setText("Removed store '" + RESOURCE_STORE_NAME + "'");
        resourceStore = null;
      } else {
        statusLabel.setText("The store '" + RESOURCE_STORE_NAME
            + "' does not exist.");
      }
    } catch (GearsException e) {
      statusLabel.setText(e.getMessage());
    }
  }

  private void removeURLs() {
    try {
      for (int i = 0; i < FILES_TO_CAPTURE.length; ++i) {
        String url = FILES_TO_CAPTURE[i];
        resourceStore.removeCapturedURL(url);
      }
      statusLabel.setText("Removed URLs from the store");
    } catch (GearsException e) {
      statusLabel.setText(e.getMessage());
    }
  }
}
