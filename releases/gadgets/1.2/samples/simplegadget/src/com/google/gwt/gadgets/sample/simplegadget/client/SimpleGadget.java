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

package com.google.gwt.gadgets.sample.simplegadget.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.gadgets.client.Gadget;
import com.google.gwt.gadgets.client.NeedsSetTitle;
import com.google.gwt.gadgets.client.SetTitleFeature;
import com.google.gwt.gadgets.client.UserPreferences;
import com.google.gwt.gadgets.client.Gadget.AllowHtmlQuirksMode;
import com.google.gwt.gadgets.client.Gadget.ModulePrefs;
import com.google.gwt.gadgets.client.Gadget.UseLongManifestName;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Sample gadget from the Getting Started manual.
 */
@ModulePrefs(title = "Original Title", author = "yournamehere", author_email = "yournamehere@gmail.com")
@UseLongManifestName(false)
@AllowHtmlQuirksMode(false)
public class SimpleGadget extends Gadget<UserPreferences> implements
    NeedsSetTitle {
  int titleIndex = 0;
  String[] titles = {"NeedsTitle Example", "Hello World!", "Goodbye World!"};
  SetTitleFeature titleFeature;

  @Override
  protected void init(UserPreferences preferences) {
    Button changeTitleButton = new Button("Change Title");
    changeTitleButton.addClickHandler(new ClickHandler() {

      public void onClick(ClickEvent event) {
        titleFeature.setTitle(titles[titleIndex++ % titles.length]);
      }

    });
    RootLayoutPanel.get().add(changeTitleButton);
  }

  public void initializeFeature(SetTitleFeature feature) {
    this.titleFeature = feature;
  }
}
