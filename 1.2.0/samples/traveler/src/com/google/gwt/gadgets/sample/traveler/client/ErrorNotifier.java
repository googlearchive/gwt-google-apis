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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Component responsible for displaying errors.
 */
public class ErrorNotifier {

  private static ErrorNotifier errorNotifier;

  public static ErrorNotifier getErrorNotifier() {
    if (errorNotifier == null) {
      errorNotifier = new ErrorNotifier();
    }
    return errorNotifier;
  }

  private DialogBox dialogBox;
  private Label codeLabel;
  private HTML detailsHTML;
  private Button detailsButton;

  private ErrorNotifier() {
    dialogBox = new DialogBox();
    dialogBox.setText("Error occurred");
    dialogBox.setAnimationEnabled(true);

    codeLabel = new Label();
    detailsHTML = new HTML();
    detailsHTML.setVisible(false);
    detailsButton = new Button("Show details");
    detailsButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        toggleDetails();
      }
    });
    final Button closeButton = new Button("Close");
    closeButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        dialogBox.hide();
      }
    });

    VerticalPanel dialogVPanel = new VerticalPanel();
    dialogVPanel.setStylePrimaryName("error-panel");
    dialogVPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    dialogVPanel.add(codeLabel);

    dialogVPanel.add(detailsHTML);
    FlowPanel buttons = new FlowPanel();
    buttons.add(detailsButton);
    buttons.add(closeButton);
    dialogVPanel.add(buttons);
    dialogBox.setWidget(dialogVPanel);
  }

  public void showError(int code, String details) {
    codeLabel.setText("Error code: " + code);
    detailsHTML.setHTML(details);
    if (detailsHTML.isVisible()) {
      toggleDetails();
    }
    dialogBox.center();
  }

  private void toggleDetails() {
    detailsHTML.setVisible(!detailsHTML.isVisible());
    if (detailsHTML.isVisible()) {
      detailsButton.setText("Hide details");
    } else {
      detailsButton.setText("Show details");
    }
    dialogBox.center();
  }
}
