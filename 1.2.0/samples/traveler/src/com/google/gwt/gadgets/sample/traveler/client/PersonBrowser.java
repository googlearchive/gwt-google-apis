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

import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.gadgets.client.osapi.Callback;
import com.google.gwt.gadgets.client.osapi.OsapiCollection;
import com.google.gwt.gadgets.client.osapi.OsapiError;
import com.google.gwt.gadgets.client.osapi.people.PeopleService;
import com.google.gwt.gadgets.client.osapi.people.Person;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Resizeable widget displaying the list of it's owner's friends.
 */
public class PersonBrowser extends ResizeComposite {

  /**
   * Interface to implement in order to interact with {@link PersonBrowser}.
   */
  public interface PersonClickedHandler {
    void handle(String id);
  }

  private static final int BUTTON_WIDTH = 30;

  private static String noPhotoUrl;
  private PeopleService people;
  private PersonClickedHandler clickHandler;
  private Grid friendsGrid;
  private int cellSize;

  // Current start index of the browser
  private int start = 0;
  // Number of items that can be displayed in the browser due to it's size
  private int pageSize = 1;
  // Total number of items according to last request
  private int total = 1;
  // Result of last request, or null if request returned error
  private OsapiCollection<Person> friends;

  /**
   * Constructs widget using given {@link PeopleService}.
   *
   * @param people {@link PeopleService} instance being a source of data.
   * @param height height of the gadget.
   * @param noPhotoUrl url to the thumbnail to be used if thumbnail for a
   *          displayed friend is unavailable.
   */
  public PersonBrowser(PeopleService people, int height, String noPhotoUrl) {
    this.people = people;
    this.cellSize = height;
    this.noPhotoUrl = noPhotoUrl;
    DockLayoutPanel panel = new DockLayoutPanel(Unit.PX);

    friendsGrid = new Grid(1, pageSize);
    friendsGrid.setWidth("100%");
    friendsGrid.setCellPadding(0);
    friendsGrid.setCellSpacing(0);

    Button prevButton = new Button("&lt;");
    Button nextButton = new Button("&gt;");

    prevButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        start = Math.max(start - 1, 0);
        showItems();
      }
    });

    nextButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        start = Math.min(start + 1, Math.max(total - pageSize, 0));
        showItems();
      }
    });

    panel.addWest(prevButton, BUTTON_WIDTH);
    panel.addEast(nextButton, BUTTON_WIDTH);
    panel.add(friendsGrid);

    showItems();

    initWidget(panel);
  }

  private void showItems() {
    if (friends != null
        && friends.getStartIndex() <= start
        && friends.getStartIndex() + friends.getItemsPerPage() >= start
            + pageSize) {
      // No request needed - this is implemented mainly for smooth resizing
      showImpl();
    } else {
      people.newGetOwnersFriendsRequestBuilder().setCount(pageSize).setStartIndex(
          start).setFields(Person.THUMBNAIL_URL, Person.ID, Person.DISPLAY_NAME).build().execute(
          new Callback<OsapiCollection<Person>>() {

            public void onFail(OsapiError error) {
              friends = null;
              friendsGrid.clear(true);
              friendsGrid.resize(1, 1);
              friendsGrid.setText(0, 0, error.getMessage());
            }

            public void onSuccess(OsapiCollection<Person> collection) {
              friends = collection;
              total = friends.getTotalResults();
              showImpl();
            }
          });
    }
  }

  private void showImpl() {
    JsArray<Person> list = friends.getList();
    int length = Math.min(list.length(), pageSize);
    friendsGrid.clear(true);
    if (friendsGrid.getColumnCount() != length) {
      friendsGrid.resize(1, length);
    }
    for (int i = 0; i < length; i++) {
      friendsGrid.setWidget(0, i, createPersonPanel(list.get(i)));
    }
  }

  private Panel createPersonPanel(final Person p) {
    VerticalPanel panel = new VerticalPanel();
    panel.setPixelSize(cellSize, cellSize);
    panel.setStylePrimaryName("friend-cell");
    panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

    String imgUrl = p.getThumbnailUrl();
    panel.add(new Image(imgUrl == null ? noPhotoUrl : imgUrl));
    panel.add(new Label(p.getDisplayName()));

    FocusPanel fPanel = new FocusPanel(panel);
    if (clickHandler != null) {
      fPanel.addClickHandler(new ClickHandler() {
        public void onClick(ClickEvent event) {
          clickHandler.handle(p.getId());
        }
      });
    }
    return fPanel;
  }

  @Override
  public void onResize() {
    int newPageSize = (getOffsetWidth() - 2 * BUTTON_WIDTH) / cellSize;
    if (newPageSize > 0 && newPageSize != pageSize) {
      pageSize = newPageSize;
      showItems();
    }
  }

  public void setPersonClickedHandler(PersonClickedHandler handler) {
    clickHandler = handler;
  }
}