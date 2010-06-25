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
package com.google.gwt.gadgets.sample.hellogadgets.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.gadgets.client.Gadget;
import com.google.gwt.gadgets.client.Gadget.AllowHtmlQuirksMode;
import com.google.gwt.gadgets.client.Gadget.ModulePrefs;
import com.google.gwt.gadgets.client.Gadget.UseLongManifestName;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * HelloWorld Gadget.
 */
// Added comments at the end of each line to break up Eclipse's auto formatting.
@ModulePrefs(//
title = "Hello GWT for gadgets!", //
directory_title = "HelloGadgets - Google APIs for GWT", //
author = "Eric Ayers", //
author_email = "zundel@google.com", //
author_affiliation = "Google Inc.", //
height = 210, //
thumbnail = "gwt-hello-gadgets-igoogle-thumb.png", //
screenshot = "gwt-hello-gadgets-igoogle.png")
// Create a short manifest name (instead of prepending the package prefix)
@UseLongManifestName(false)
@AllowHtmlQuirksMode(false)
public class HelloGadgets extends Gadget<HelloPreferences> {

  @Override
  protected void init(final HelloPreferences prefs) {
    Image img = new Image("http://code.google.com/webtoolkit/logo-185x175.png");
    Button button = new Button("Click me");

    VerticalPanel vPanel = new VerticalPanel();
    vPanel.setWidth("100%");
    vPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
    vPanel.add(img);
    vPanel.add(button);

    RootPanel.get().add(vPanel);

    // Create the dialog box
    final DialogBox dialogBox = new DialogBox();

    // The content of the dialog comes from a User specified Preference
    dialogBox.setText(prefs.promptSomethingElse().getValue());
    dialogBox.setAnimationEnabled(true);
    Button closeButton = new Button("Close");
    VerticalPanel dialogVPanel = new VerticalPanel();
    dialogVPanel.setWidth("100%");
    dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
    dialogVPanel.add(closeButton);

    closeButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        dialogBox.hide();
      }
    });

    // Set the contents of the Widget
    dialogBox.setWidget(dialogVPanel);

    button.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        dialogBox.center();
        dialogBox.show();
      }
    });
  }
}
