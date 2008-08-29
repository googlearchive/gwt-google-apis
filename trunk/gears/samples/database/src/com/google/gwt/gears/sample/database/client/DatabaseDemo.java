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
import com.google.gwt.gears.client.database.Database;
import com.google.gwt.gears.client.database.DatabaseException;
import com.google.gwt.gears.client.database.ResultSet;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.KeyboardListenerAdapter;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Sample application demonstrating how to use the {@link Database} class.
 */
public class DatabaseDemo implements EntryPoint {

  private final Button addButton = new Button("Add");
  private final Button clearButton = new Button("Clear Database");
  private Database db;
  private final TextBox input = new TextBox();

  private final Label[] labels = new Label[] {
      new Label(), new Label(), new Label()};

  public void onModuleLoad() {
    VerticalPanel outerPanel = new VerticalPanel();
    outerPanel.getElement().getStyle().setPropertyPx("margin", 15);    
    
    HorizontalPanel textAndButtonsPanel = new HorizontalPanel();
    textAndButtonsPanel.add(input);
    textAndButtonsPanel.add(addButton);
    textAndButtonsPanel.add(clearButton);
    outerPanel.add(textAndButtonsPanel);
    
    for (int i = 0; i < labels.length; ++i) {
      outerPanel.add(labels[i]);
    }

    // Create the database if it doesn't exist.
    try {
      db = Factory.getInstance().createDatabase();
      db.open("database-demo");
      db.execute("create table if not exists Demo (Phrase varchar(255), Timestamp int)");
    } catch (DatabaseException e) {
      Window.alert(e.toString());
    }

    input.addKeyboardListener(new KeyboardListenerAdapter() {
      @Override
      public void onKeyDown(Widget sender, char keyCode, int modifiers) {
        if (keyCode == KeyboardListener.KEY_ENTER) {
          insertPhrase();
        }
      }
    });

    addButton.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        insertPhrase();
      }
    });

    clearButton.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        clearPhrases();
        displayRecentPhrases();
      }
    });
    
    RootPanel.get("demo").add(outerPanel);
    displayRecentPhrases();
  }
  
  /**
   * Remove all phrases from the database.
   */
  private void clearPhrases() {
    try {
      db.execute("delete from Demo");
    } catch (DatabaseException e) {
      Window.alert(e.toString());
    }
  }
  
  /**
   * Fill the labels with the phrases from the database.
   */
  private void displayRecentPhrases() {
    try {
      ResultSet rs = db.execute("select * from Demo order by Timestamp desc");
      int i;
     
      for (i = 0; rs.isValidRow(); ++i, rs.next()) {
        if (i < labels.length) {
          labels[i].setText(rs.getFieldAsString(0));
        } else {
          db.execute("delete from Demo where Timestamp=?",
              new String[] {rs.getFieldAsString(1)});
        }
      }
      // If a phrase has been removed, clear the label.
      for (; i < labels.length; i++) {
        labels[i].setText("");
      }
      rs.close();
    } catch (DatabaseException e) {
      Window.alert(e.toString());
    }
  }

  /**
   * Add a new phrase to the database.
   */
  private void insertPhrase() {
    try {
      String phrase = input.getText();
      if (phrase.length() > 0) {
        db.execute("insert into Demo values (?, ?)", new String[] {
            phrase, Long.toString(System.currentTimeMillis())});
        displayRecentPhrases();
        input.setText("");
      }
    } catch (DatabaseException e) {
      Window.alert(e.toString());
    }
  }
}
