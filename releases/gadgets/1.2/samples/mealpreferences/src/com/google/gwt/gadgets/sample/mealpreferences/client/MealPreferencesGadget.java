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
package com.google.gwt.gadgets.sample.mealpreferences.client;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.gadgets.client.Gadget;
import com.google.gwt.gadgets.client.Gadget.AllowHtmlQuirksMode;
import com.google.gwt.gadgets.client.Gadget.ModulePrefs;
import com.google.gwt.gadgets.client.Gadget.UseLongManifestName;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Sample gadget from the Getting Started manual.
 */
@ModulePrefs(title = "MealPreferences Gadget", author = "yournamehere", author_email = "yournamehere@gmail.com")
@UseLongManifestName(false)
@AllowHtmlQuirksMode(false)
public class MealPreferencesGadget extends Gadget<MealPreferences> {
  private String[] meatEntrees = {
      "steak kabobs", "lamb fricassee", "chicken kiev"};
  private String[] vegEntrees = {"saag paneer", "baba ganoush", "boca burger"};
  private String[] dishes;

  @Override
  protected void init(MealPreferences preferences) {
    initDishes(preferences);

    // Create a table with a checklist of all available dishes based on the
    // user's dietary preferences.
    Panel p = new DockLayoutPanel(Unit.PX);
    p.setWidth("100%");

    FlexTable ft = new FlexTable();

    int index = 0;
    for (String dish : dishes) {
      CheckBox cb = new CheckBox();
      final String dishCopy = dish;
      cb.addClickHandler(new ClickHandler() {
        public void onClick(ClickEvent event) {
          Window.alert("Your order of " + dishCopy + " will be right up.");
        }
      });
      ft.setWidget(index, 0, cb);
      ft.setHTML(index, 1, dish);
      index++;
    }
    p.add(ft);
    RootLayoutPanel.get().add(p);
  }

  private void initDishes(MealPreferences preferences) {
    // Customize the list of dishes to the dining preferences of the user.
    if (preferences.noMeat().getValue().booleanValue()) {
      dishes = vegEntrees;
    } else {
      dishes = meatEntrees;
    }
  }
}

