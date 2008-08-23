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
package com.google.gwt.gears.sample.database.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.gears.client.Factory;
import com.google.gwt.gears.client.GearsException;
import com.google.gwt.gears.client.database.Database;
import com.google.gwt.gears.client.database.DatabaseException;
import com.google.gwt.gears.client.database.ResultSet;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * Sample application demonstrating how to use the {@link Database} class.
 */
public class DatabaseDemo implements EntryPoint {

  private final Button button = new Button("Add");
  private Database db;
  private final TextBox input = new TextBox();

  private final Label[] labels = new Label[] {
      new Label(), new Label(), new Label()};

  public void onModuleLoad() {
    RootPanel rootPanel = RootPanel.get();
    rootPanel.add(input);
    rootPanel.add(button);
    for (int i = 0; i < labels.length; ++i) {
      rootPanel.add(labels[i]);
    }

    try {
      db = Factory.getInstance().createDatabase();
      db.open("database-demo");
      db.execute("create table if not exists Demo (Phrase varchar(255), Timestamp int)");
    } catch (GearsException e) {
      // TODO(mmendez): GearsException should be DatabaseException
      Window.alert(e.toString());
    }

    // TODO(mmendez): should we add a keyboard listener to trap the enter key?
    button.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        try {
          String phrase = input.getText();
          if (phrase.length() > 0) {
            db.execute("insert into Demo values (?, ?)", new String[] {
                phrase, Integer.toString((int) System.currentTimeMillis())});
            displayRecentPhrases();
          }
        } catch (DatabaseException e) {
          Window.alert(e.toString());
        }
      }
    });

    displayRecentPhrases();
  }

  void displayRecentPhrases() {
    try {
      ResultSet rs = db.execute("select * from Demo order by Timestamp desc");
      for (int i = 0; rs.isValidRow(); ++i, rs.next()) {
        if (i < labels.length) {
          labels[i].setText(rs.getFieldAsString(0));
        } else {
          db.execute("delete from Demo where Timestamp=?",
              new String[] {rs.getFieldAsString(1)});
        }
      }
      rs.close();
    } catch (DatabaseException e) {
      Window.alert(e.toString());
    }
  }
}
