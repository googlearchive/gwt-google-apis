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
package com.google.gwt.maps.sample.maps.client;

import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.MapTypeControl;
import com.google.gwt.maps.client.control.SmallMapControl;
import com.google.gwt.maps.client.event.MarkerClickListener;
import com.google.gwt.maps.client.event.MarkerDragListener;
import com.google.gwt.maps.client.event.MarkerMouseListener;
import com.google.gwt.maps.client.event.RemoveListener;
import com.google.gwt.maps.client.event.VisibilityListener;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.maps.client.overlay.Overlay;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * In this example, the different types of events that can be attached to a
 * marker are demonstrated.
 */
public class MarkerEventDemo extends MapsDemo {

  private enum ListenerActions {

    DRAG_LISTENER("DragListener"), //
    MARKER_CLICK_LISTENER("MarkerClickListener"), //
    MARKER_MOUSE_LISTENER("MarkerMouseListener"), //
    REMOVE_LISTENER("RemoveListener"), //
    VISIBILITY_LISTENER("VisibilityListener");//

    private final String value;

    private ListenerActions(String s) {
      value = s;
    }

    public String valueOf() {
      return value;
    }
  }

  private static final LatLng ATLANTA = new LatLng(33.7814790, -84.3880580);

  private static HTML descHTML = null;

  private static final String descString = ""
      + "<p>Creates a 500 x  300 pixel map viewport centered on "
      + "Atlanta, GA USA.</p>\n"
      + "<p>Displays a draggable marker and various controls to exercise "
      + "event handling.</p>\n"
      + "<ul>\n <li><b>Hide/Show Marker</b> button alternately hides and "
      + "shows the marker which triggers the VisibilityListener.</li>\n"
      + " <li><b>Remove/Add Marker</b> button alternately removes and adds "
      + "the marker to the map which triggers the removeListener.</li>\n"
      + " <li><b>Enable/Disable Dragging</b> turns dragging on and off "
      + "on the marker.  Dragging must be enabled to trigger the DragListener.</li>\n"
      + " <li><b>Clear Table</b> Remove all entries from the table below.</li>\n"
      + " <li><b>Add Listener</b> Adds a listener of the type "
      + "specified in the listbox.  This will create a new entry in the table "
      + "to monitor and control the listener.</li>\n"
      + " <li><b>Clear Listeners of this Type</b> Clears all listeners of the"
      + "  type specified in the listbox.</li>\n"
      + "</ul>"
      + "<p>Below the action buttons there is a table of all listeners that "
      + "  have been added.  Each entry contains the following items:</p>\n"
      + "<ul>\n"
      + "<li><b>ID</b> A unique ID for each listener.</li>\n"
      + "<li><b>Type</b> The type of listener.</li>\n"
      + "<li><b>Events</b> A textbox that shows events as they happen.</li>"
      + "<li><b>Clear Text</b> A button that clears the <b>Events</b> textbox.</li>"
      + "<li><b>Remove Listener</b> Remove this specific listener instance."
      + "</ul>\n";

  // A unique number assigned to each new listener added.
  private static int listenerId = 0;

  public static MapsDemoInfo init() {
    return new MapsDemoInfo() {
      @Override
      public MapsDemo createInstance() {
        return new MarkerEventDemo();
      }

      @Override
      public HTML getDescriptionHTML() {
        if (descHTML == null) {
          descHTML = new HTML(descString);
        }
        return descHTML;
      }

      @Override
      public String getName() {
        return "Marker Event Listeners";
      }
    };
  }

  private FlexTable listenerTable;

  private MapWidget map;
  private Marker marker;
  // Saves the state of whether the maker is currently removed from the map
  private boolean removed = false;

  public MarkerEventDemo() {
    VerticalPanel vp = new VerticalPanel();

    // Center the new map on Midtown Atlanta
    map = new MapWidget(ATLANTA, 13);
    map.setSize("500px", "300px");
    map.addControl(new SmallMapControl());
    map.addControl(new MapTypeControl());

    MarkerOptions opt = new MarkerOptions();
    opt.setDraggable(true);
    marker = new Marker(ATLANTA, opt);

    HorizontalPanel hp1 = createActionButtons();
    HorizontalPanel hp2 = createListenerListBox();

    // Make a spacer
    HorizontalPanel hp3 = new HorizontalPanel();
    hp3.add(new Label(" "));
    hp3.setSize("1em", "1em");

    listenerTable = new FlexTable();
    clearListenerTable();

    vp.add(map);
    vp.add(hp1);
    vp.add(hp2);
    vp.add(hp3);
    vp.add(listenerTable);

    initWidget(vp);
  }

  private void clearListenerTable() {
    int numRows = listenerTable.getRowCount();
    for (int i = numRows - 1; i >= 0; i--) {
      listenerTable.removeRow(i);
    }

    listenerTable.setHTML(0, 0, "<b>ID</b>");
    listenerTable.setHTML(0, 1, "<b>Type</b>");
    listenerTable.setHTML(0, 2, "<b>Events</b>");
    listenerTable.setHTML(0, 3, "<b>Clear Text</b>");
    listenerTable.setHTML(0, 4, "<b>Remove Listener</b>");
  }

  @Override
  public void onShow() {
    map.addOverlay(marker);
  }

  private void addListenerToMarker(ListenerActions a) {
    // Create a unique ID for this new listener.
    final int nextListenerId = ++listenerId;

    // Create a new row to add to the UI.
    final int rowNumber = listenerTable.getRowCount();
    listenerTable.setWidget(rowNumber, 0, new Label("" + nextListenerId));
    listenerTable.setWidget(rowNumber, 1, new Label(a.valueOf()));

    final TextBox textBox = new TextBox();
    textBox.setReadOnly(true);
    textBox.setSize("30em", "1em");
    listenerTable.setWidget(rowNumber, 3, textBox);

    Button clearButton = new Button("Clear Text");
    clearButton.addClickListener(new ClickListener() {

      public void onClick(Widget sender) {
        textBox.setText("");
      }

    });
    listenerTable.setWidget(rowNumber, 4, clearButton);

    Button removeListenerButton = new Button("Remove Listener");
    listenerTable.setWidget(rowNumber, 5, removeListenerButton);

    // Add event specific listeners
    switch (a) {
      case MARKER_CLICK_LISTENER: {
        final MarkerClickListener l = new MarkerClickListener() {

          public void onClick(Marker sender) {
            textBox.setText(textBox.getText() + "onClick() ");
          }

          public void onDoubleClick(Marker sender) {
            textBox.setText(textBox.getText() + "onDoubleClick() ");
          }

        };
        marker.addMarkerClickListener(l);
        removeListenerButton.addClickListener(new ClickListener() {

          public void onClick(Widget sender) {
            marker.removeMarkerClickListener(l);
            // removeRowFromTable(nextListenerId);
          }

        });
      }
        break;

      case DRAG_LISTENER: {
        final MarkerDragListener l = new MarkerDragListener() {

          public void onDrag(Marker sender) {
            textBox.setText(textBox.getText() + "onDrag() ");
          }

          public void onDragEnd(Marker sender) {
            textBox.setText(textBox.getText() + "onDragEnd() ");
          }

          public void onDragStart(Marker sender) {
            textBox.setText(textBox.getText() + "onDragStart() ");
          }
        };

        marker.addMarkerDragListener(l);
        removeListenerButton.addClickListener(new ClickListener() {

          public void onClick(Widget sender) {
            marker.removeMarkerDragListener(l);
            // removeRowFromTable(nextListenerId);
          }
        });
      }
        break;
      case MARKER_MOUSE_LISTENER: {
        final MarkerMouseListener l = new MarkerMouseListener() {

          public void onMouseDown(Marker sender) {
            textBox.setText(textBox.getText() + "onMouseDown() ");
          }

          public void onMouseOut(Marker sender) {
            textBox.setText(textBox.getText() + "onMouseOut() ");
          }

          public void onMouseOver(Marker sender) {
            textBox.setText(textBox.getText() + "onMouseOver() ");
          }

          public void onMouseUp(Marker sender) {
            textBox.setText(textBox.getText() + "onMouseUp() ");
          }
        };

        marker.addMarkerMouseListener(l);
        removeListenerButton.addClickListener(new ClickListener() {

          public void onClick(Widget sender) {
            marker.removeMarkerMouseListener(l);
            // removeRowFromTable(nextListenerId);
          }
        });
      }
        break;

      case REMOVE_LISTENER: {
        final RemoveListener l = new RemoveListener() {
          public void onRemove(Overlay sender) {
            textBox.setText(textBox.getText() + "onRemove() ");
          }

        };
        marker.addRemoveListener(l);
        removeListenerButton.addClickListener(new ClickListener() {

          public void onClick(Widget sender) {
            marker.removeRemoveListener(l);
            // removeRowFromTable(nextListenerId);
          }
        });
      }
        break;
      case VISIBILITY_LISTENER: {
        final VisibilityListener l = new VisibilityListener() {

          public void onVisibilityChanged(Marker sender, boolean isVisible) {
            textBox.setText(textBox.getText() + "onVisible(" + isVisible + ") ");
          }

        };
        marker.addVisibilityListener(l);
        removeListenerButton.addClickListener(new ClickListener() {

          public void onClick(Widget sender) {
            marker.removeVisibilityListener(l);
            // removeRowFromTable(nextListenerId);
          }
        });
      }
        break;
      default:
        Window.alert("Unhandled ListenerActions case : " + a.valueOf());
    }
  }

  /**
   * Create a panel of buttons to use to perform various actions on the marker.
   */
  private HorizontalPanel createActionButtons() {
    HorizontalPanel hp = new HorizontalPanel();

    // Create a button to hide/show the marker
    final Button hideButton = new Button("Hide Marker");
    hideButton.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        boolean state = !marker.isVisible();
        marker.setVisible(state);
        if (state) {
          hideButton.setText("Hide Marker");
        } else {
          hideButton.setText("Show Marker");
        }
      }
    });
    hp.add(hideButton);

    // Create a button to remove the marker from the map.
    final Button removeButton = new Button("Remove Marker");
    removeButton.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        if (!removed) {
          map.removeOverlay(marker);
          removeButton.setText("Add Marker");
        } else {
          map.addOverlay(marker);
          removeButton.setText("Remove Marker");
        }
        hideButton.setEnabled(removed);
        removed = !removed;
      }
    });
    hp.add(removeButton);

    // Create a button to Set the marker as draggable
    final Button draggableButton = new Button("Disable Dragging");
    draggableButton.addClickListener(new ClickListener() {

      public void onClick(Widget sender) {
        boolean state = !marker.isDraggingEnabled();
        marker.setDraggingEnabled(state);
        if (state) {
          draggableButton.setText("Disable Dragging");
        } else {
          draggableButton.setText("Enable Dragging");
        }
      }

    });
    hp.add(draggableButton);

    // Create a button to clear out the table.
    final Button clearTableButton = new Button("Clear Table");
    clearTableButton.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        clearListenerTable();
      }
    });
    hp.add(clearTableButton);

    hp.setSpacing(10);
    return hp;
  }

  /**
   * Create a drop down list that shows all the different types of listeners
   * that can be added.
   * 
   * @return a ListBox populated with the ListenerActions values
   */
  private HorizontalPanel createListenerListBox() {
    HorizontalPanel h = new HorizontalPanel();

    final ListBox listenerBox = new ListBox();
    for (ListenerActions a : ListenerActions.values()) {
      listenerBox.addItem(a.valueOf());
    }
    h.add(listenerBox);

    Button b = new Button("Add Listener");
    b.addClickListener(new ClickListener() {

      public void onClick(Widget sender) {
        int selectedIndex = listenerBox.getSelectedIndex();
        ListenerActions a = ListenerActions.values()[selectedIndex];
        addListenerToMarker(a);
      }

    });
    h.add(b);
    
    return h;
  }
}