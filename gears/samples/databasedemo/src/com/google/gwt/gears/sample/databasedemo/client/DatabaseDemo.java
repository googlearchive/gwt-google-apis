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
package com.google.gwt.gears.sample.databasedemo.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.gears.client.Factory;
import com.google.gwt.gears.client.database.Database;
import com.google.gwt.gears.client.database.DatabaseException;
import com.google.gwt.gears.client.database.ResultSet;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import java.util.Date;

/**
 * Sample application demonstrating how to use the {@link Database} class.
 */
public class DatabaseDemo implements EntryPoint {
  private static final int NUM_SAVED_ROWS = 3;
  private static final int NUM_DATA_TABLE_COLUMNS = 3;

  private final Button addButton = new Button("Add");
  private final Button clearButton = new Button("Clear Database");
  private Database db;
  private final TextBox input = new TextBox();
  private final FlexTable dataTable = new FlexTable();

  public void onModuleLoad() {
    VerticalPanel outerPanel = new VerticalPanel();
    outerPanel.setSpacing(10);
    outerPanel.getElement().getStyle().setPropertyPx("margin", 15);

    HorizontalPanel textAndButtonsPanel = new HorizontalPanel();
    textAndButtonsPanel.add(new Label("Enter a Phrase: "));
    textAndButtonsPanel.add(input);
    textAndButtonsPanel.add(addButton);
    textAndButtonsPanel.add(clearButton);
    outerPanel.add(textAndButtonsPanel);
    outerPanel.add(new Label("Last 3 Entries:"));
    outerPanel.add(dataTable);

    for (int i = 0; i <= NUM_SAVED_ROWS; ++i) {
      dataTable.insertRow(i);
      for (int j = 0; j < NUM_DATA_TABLE_COLUMNS; j++) {
        dataTable.addCell(i);
      }
    }
    dataTable.setWidget(0, 0, new HTML("<b>Id</b>"));
    dataTable.setWidget(0, 1, new HTML("<b>Phrase</b>"));
    dataTable.setWidget(0, 2, new HTML("<b>Timestamp</b>"));

    // Create the database if it doesn't exist.
    try {
      db = Factory.getInstance().createDatabase();
      db.open("database-demo");
      db.execute("CREATE TABLE IF NOT EXISTS Phrases (Id INTEGER PRIMARY KEY AUTOINCREMENT, Phrase VARCHAR(255), Timestamp INTEGER)");
    } catch (DatabaseException e) {
      RootPanel.get("demo").add(
          new HTML("Error opening or creating database: <font color=\"red\">"
              + e.toString() + "</font>"));
      // Fatal error. Do not build the interface.
      return;
    }

    input.addKeyDownHandler(new KeyDownHandler() {
      public void onKeyDown(KeyDownEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
          insertPhrase();
        }
      }
    });

    addButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        insertPhrase();
      }
    });

    clearButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
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
      db.execute("DELETE FROM Phrases");
    } catch (DatabaseException e) {
      Window.alert(e.toString());
    }
  }

  /**
   * Fill the labels with the phrases from the database.
   */
  private void displayRecentPhrases() {
    try {
      ResultSet rs = db.execute("SELECT * FROM Phrases ORDER BY Id DESC");
      int i;

      for (i = 1; rs.isValidRow(); ++i, rs.next()) {
        if (i <= NUM_SAVED_ROWS) {
          dataTable.setText(i, 0, rs.getFieldAsString(0));
          dataTable.setText(i, 1, rs.getFieldAsString(1));
          dataTable.setText(i, 2, new Date(rs.getFieldAsLong(2)).toString());
        } else {
          db.execute("DELETE FROM Phrases WHERE Id = ?",
              new String[]{rs.getFieldAsString(0)});
        }
      }
      // If a phrase has been removed, clear the label.
      for (; i <= NUM_SAVED_ROWS; i++) {
        for (int j = 0; j < NUM_DATA_TABLE_COLUMNS; j++) {
          dataTable.clearCell(i, j);
        }
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
        db.execute("INSERT INTO Phrases (Phrase, Timestamp) VALUES (?, ?)",
            new String[]{phrase, Long.toString(System.currentTimeMillis())});
        displayRecentPhrases();
        input.setText("");
      }
    } catch (DatabaseException e) {
      Window.alert(e.toString());
    }
  }
}
