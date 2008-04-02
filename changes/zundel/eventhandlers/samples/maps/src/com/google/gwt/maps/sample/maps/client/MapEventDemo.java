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

import com.google.gwt.maps.client.InfoWindow;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapType;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.MapTypeControl;
import com.google.gwt.maps.client.control.SmallMapControl;
import com.google.gwt.maps.client.event.MapInfoWindowBeforeCloseHandler;
import com.google.gwt.maps.client.event.MapInfoWindowCloseHandler;
import com.google.gwt.maps.client.event.MapInfoWindowOpenHandler;
import com.google.gwt.maps.client.event.MapAddMapTypeHandler;
import com.google.gwt.maps.client.event.MapAddOverlayHandler;
import com.google.gwt.maps.client.event.MapClearOverlaysHandler;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.event.MapDoubleClickHandler;
import com.google.gwt.maps.client.event.MapDragEndHandler;
import com.google.gwt.maps.client.event.MapDragHandler;
import com.google.gwt.maps.client.event.MapDragStartHandler;
import com.google.gwt.maps.client.event.MapMouseMoveHandler;
import com.google.gwt.maps.client.event.MapMouseOutHandler;
import com.google.gwt.maps.client.event.MapMouseOverHandler;
import com.google.gwt.maps.client.event.MapMoveEndHandler;
import com.google.gwt.maps.client.event.MapMoveHandler;
import com.google.gwt.maps.client.event.MapMoveStartHandler;
import com.google.gwt.maps.client.event.MapRemoveMapTypeHandler;
import com.google.gwt.maps.client.event.MapRemoveOverlayHandler;
import com.google.gwt.maps.client.event.MapRightClickHandler;
import com.google.gwt.maps.client.event.MapTypeChangedHandler;
import com.google.gwt.maps.client.event.MapZoomEndHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * In this example, the different types of events that can be attached to a map
 * are demonstrated.
 */
public class MapEventDemo extends MapsDemo {

  // EOL Comments added to fix Eclipse automatic formatting
  private enum HandlerActions { 
    INFO_WINDOW_BEFORE_CLOSE_HANDLER("InfoWindowBeforeCloseHandler"), //                                                                      // 
    INFO_WINDOW_CLOSE_HANDLER("InfoWindowCloseHandler"), //
    INFO_WINDOW_OPEN_HANDLER("InfoWindowOpenHandler"), //
    MAP_CLEAR_OVERLAYS_HANDLER("MapClearOverlaysHandler"), //
    MAP_ADD_MAP_TYPE_HANDLER("MapAddMapTypeHandler"), //
    MAP_ADD_OVERLAY_HANDLER("MapAddOverlayHandler"), //
    MAP_CLICK_HANDLER("MapClickHandler"), //
    MAP_DOUBLE_CLICK_HANDLER("MapDoubleClickHandler"), // 
    MAP_DRAG_END_HANDLER("MapDragEndHandler"), //
    MAP_DRAG_HANDLER("MapDragHandler"), //
    MAP_DRAG_START_HANDLER("MapDragStartHandler"), //
    MAP_MOUSE_OVER_HANDLER("MapMouseOverHandler"), //
    MAP_MOUSE_MOVE_HANDLER("MapMouseMoveHandler"), //
    MAP_MOUSE_OUT_HANDLER("MapMouseOutHandler"), //
    MAP_MOVE_END_HANDLER("MapMoveEndHandler"), //
    MAP_MOVE_HANDLER("MapMoveHandler"), //
    MAP_MOVE_START_HANDLER("MapMoveStartHandler"), //
    MAP_REMOVE_MAP_TYPE_HANDLER("MapRemoveMapTypeHandler"), //
    MAP_REMOVE_OVERLAY_HANDLER("MapRemoveOverlayHandler"), //
    MAP_RIGHT_CLICK_HANDLER("MapRightClickHandler"), //
    MAP_TYPE_CHANGED_HANDLER("MapTypeChangedHandler"), //
    MAP_ZOOM_END_HANDLER("MapZoomEndHandler"); //

    private final String value;

    private HandlerActions(String s) {
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
      + "shows the marker which triggers the MarkerVisibilityListener.</li>\n"
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
        return new MapEventDemo();
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
        return "Map Event Handlers";
      }
    };
  }

  private FlexTable handlerTable;

  private MapWidget map;
  private Marker marker;
  // Saves the state of whether the maker is currently removed from the map
  private boolean markerRemoved = false;
  // Saves the state of whether the sky map type has been removed.
  private boolean mapTypeRemoved = false;

  public MapEventDemo() {
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

    handlerTable = new FlexTable();
    clearListenerTable();

    vp.add(map);
    vp.add(hp1);
    vp.add(hp2);
    vp.add(hp3);
    vp.add(handlerTable);

    initWidget(vp);
  }

  @Override
  public void onShow() {
    map.addOverlay(marker);
  }

  private void addListenerToMarker(HandlerActions a) {
    // Create a unique ID for this new listener.
    final int nextListenerId = ++listenerId;

    // Create a new row to add to the UI.
    final int rowNumber = handlerTable.getRowCount();
    handlerTable.setWidget(rowNumber, 0, new Label("" + nextListenerId));
    handlerTable.setWidget(rowNumber, 1, new Label(a.valueOf()));

    final TextBox textBox = new TextBox();
    textBox.setReadOnly(true);
    textBox.setSize("30em", "1em");
    handlerTable.setWidget(rowNumber, 3, textBox);

    Button clearButton = new Button("Clear Text");
    clearButton.addClickListener(new ClickListener() {

      public void onClick(Widget sender) {
        textBox.setText("");
      }

    });
    handlerTable.setWidget(rowNumber, 4, clearButton);

    Button removeHandlerButton = new Button("Remove Handler");
    handlerTable.setWidget(rowNumber, 5, removeHandlerButton);

    // Add event specific handlers
    switch (a) {
      case INFO_WINDOW_BEFORE_CLOSE_HANDLER: {
        final MapInfoWindowBeforeCloseHandler h = new MapInfoWindowBeforeCloseHandler() {

          public void onInfoWindowBeforeClose(MapInfoWindowBeforeCloseEvent event) {
            textBox.setText(textBox.getText() + "onInfoWindowBeforeClose()");
          }

        };

        map.addInfoWindowBeforeCloseHandler(h);
        removeHandlerButton.addClickListener(new ClickListener() {

          public void onClick(Widget sender) {
            map.removeInfoWindowBeforeCloseHandler(h);
            // removeRowFromTable(nextListenerId);
          }

        });
      }
        break;

      case INFO_WINDOW_CLOSE_HANDLER: {
        final MapInfoWindowCloseHandler h = new MapInfoWindowCloseHandler() {

          public void onInfoWindowClose(MapInfoWindowCloseEvent event) {
            textBox.setText(textBox.getText() + "onInfoWindowClose()");
          }

        };

        map.addInfoWindowCloseHandler(h);
        removeHandlerButton.addClickListener(new ClickListener() {

          public void onClick(Widget sender) {
            map.removeInfoWindowCloseHandler(h);
            // removeRowFromTable(nextListenerId);
          }

        });
      }
        break;

      case INFO_WINDOW_OPEN_HANDLER: {
        final MapInfoWindowOpenHandler h = new MapInfoWindowOpenHandler() {

          public void onInfoWindowOpen(MapInfoWindowOpenEvent event) {
            textBox.setText(textBox.getText() + "onInfoWindowOpen()");
          }

        };

        map.addInfoWindowOpenHandler(h);
        removeHandlerButton.addClickListener(new ClickListener() {

          public void onClick(Widget sender) {
            map.removeInfoWindowOpenHandler(h);
            // removeRowFromTable(nextListenerId);
          }

        });
      }
        break;

      case MAP_CLEAR_OVERLAYS_HANDLER: {
        final MapClearOverlaysHandler h = new MapClearOverlaysHandler() {

          public void onClearOverlays(MapClearOverlaysEvent event) {
            textBox.setText(textBox.getText() + "onClearOverlays()");
          }

        };

        map.addMapClearOverlaysHandler(h);
        removeHandlerButton.addClickListener(new ClickListener() {

          public void onClick(Widget sender) {
            map.removeMapClearOverlaysHandler(h);
            // removeRowFromTable(nextListenerId);
          }

        });
      }
        break;

      case MAP_ADD_MAP_TYPE_HANDLER: {
        final MapAddMapTypeHandler h = new MapAddMapTypeHandler() {

          public void onAddMapType(MapAddMapTypeEvent event) {
            textBox.setText(textBox.getText() + "onAddMapType("
                + event.getType().getName(true) + ")");
          }

        };

        map.addMapAddMapTypeHandler(h);
        removeHandlerButton.addClickListener(new ClickListener() {

          public void onClick(Widget sender) {
            map.removeMapAddMapTypeHandler(h);
            // removeRowFromTable(nextListenerId);
          }

        });
      }
        break;

      case MAP_ADD_OVERLAY_HANDLER: {
        final MapAddOverlayHandler h = new MapAddOverlayHandler() {

          public void onAddOverlay(MapAddOverlayEvent event) {
            textBox.setText(textBox.getText() + "onAddOverlay("
                + event.getOverlay() + ")");
          }

        };

        map.addMapAddOverlayHandler(h);
        removeHandlerButton.addClickListener(new ClickListener() {

          public void onClick(Widget sender) {
            map.removeMapAddOverlayHandler(h);
            // removeRowFromTable(nextListenerId);
          }

        });
      }
        break;

      case MAP_CLICK_HANDLER: {
        final MapClickHandler h = new MapClickHandler() {

          public void onClick(MapClickEvent e) {
            textBox.setText(textBox.getText() + "onClick(" + e.getLatLng()
                + ")");
          }

        };
        map.addMapClickHandler(h);
        removeHandlerButton.addClickListener(new ClickListener() {

          public void onClick(Widget sender) {
            map.removeMapClickHandler(h);
            // removeRowFromTable(nextListenerId);
          }

        });
      }
        break;

      case MAP_DOUBLE_CLICK_HANDLER: {
        final MapDoubleClickHandler h = new MapDoubleClickHandler() {

          public void onDoubleClick(MapDoubleClickEvent e) {
            textBox.setText(textBox.getText() + "onDoubleClick("
                + e.getLatLng() + ")");
          }
        };
        map.addMapDoubleClickHandler(h);
        removeHandlerButton.addClickListener(new ClickListener() {
          public void onClick(Widget sender) {
            map.removeMapDoubleClickHandler(h);
            // removeRowFromTable(nextListenerId);
          }
        });
      }
        break;

      case MAP_DRAG_END_HANDLER: {
        final MapDragEndHandler h = new MapDragEndHandler() {

          public void onDragEnd(MapDragEndEvent event) {
            textBox.setText(textBox.getText() + "onDragEnd()");
          }

        };

        map.addMapDragEndHandler(h);
        removeHandlerButton.addClickListener(new ClickListener() {

          public void onClick(Widget sender) {
            map.removeMapDragEndHandler(h);
            // removeRowFromTable(nextListenerId);
          }

        });
      }
        break;
      case MAP_DRAG_HANDLER: {
        final MapDragHandler h = new MapDragHandler() {

          public void onDrag(MapDragEvent event) {
            textBox.setText(textBox.getText() + "onDrag()");
          }

        };

        map.addMapDragHandler(h);
        removeHandlerButton.addClickListener(new ClickListener() {

          public void onClick(Widget sender) {
            map.removeMapDragHandler(h);
            // removeRowFromTable(nextListenerId);
          }

        });
      }
        break;

      case MAP_DRAG_START_HANDLER: {
        final MapDragStartHandler h = new MapDragStartHandler() {

          public void onDragStart(MapDragStartEvent event) {
            textBox.setText(textBox.getText() + "onDragStart()");
          }

        };

        map.addMapDragStartHandler(h);
        removeHandlerButton.addClickListener(new ClickListener() {

          public void onClick(Widget sender) {
            map.removeMapDragStartHandler(h);
            // removeRowFromTable(nextListenerId);
          }

        });
      }
        break;

      case MAP_MOUSE_OUT_HANDLER: {
        final MapMouseOutHandler h = new MapMouseOutHandler() {

          public void onMouseOut(MapMouseOutEvent event) {
            textBox.setText(textBox.getText() + "onMouseOut("
                + event.getLatLng() + ")");
          }

        };

        map.addMapMouseOutHandler(h);
        removeHandlerButton.addClickListener(new ClickListener() {

          public void onClick(Widget sender) {
            map.removeMapMouseOutHandler(h);
            // removeRowFromTable(nextListenerId);
          }

        });
      }
        break;

      case MAP_MOUSE_OVER_HANDLER: {
        final MapMouseOverHandler h = new MapMouseOverHandler() {

          public void onMouseOver(MapMouseOverEvent event) {
            textBox.setText(textBox.getText() + "onMouseOver("
                + event.getLatLng() + ")");
          }

        };

        map.addMapMouseOverHandler(h);
        removeHandlerButton.addClickListener(new ClickListener() {

          public void onClick(Widget sender) {
            map.removeMapMouseOverHandler(h);
            // removeRowFromTable(nextListenerId);
          }

        });
      }
        break;

      case MAP_MOUSE_MOVE_HANDLER: {
        final MapMouseMoveHandler h = new MapMouseMoveHandler() {

          public void onMouseMove(MapMouseMoveEvent event) {
            textBox.setText(textBox.getText() + "onMouseMove("
                + event.getLatLng() + ")");
          }
        };
        map.addMapMouseMoveHandler(h);
        removeHandlerButton.addClickListener(new ClickListener() {
          public void onClick(Widget sender) {
            map.removeMapMouseMoveHandler(h);
            // removeRowFromTable(nextListenerId);
          }
        });
      }
        break;

      case MAP_MOVE_END_HANDLER: {
        final MapMoveEndHandler h = new MapMoveEndHandler() {

          public void onMoveEnd(MapMoveEndEvent e) {
            textBox.setText(textBox.getText() + "onMoveEnd()");
          }
        };
        map.addMapMoveEndHandler(h);
        removeHandlerButton.addClickListener(new ClickListener() {
          public void onClick(Widget sender) {
            map.removeMapMoveEndHandler(h);
            // removeRowFromTable(nextListenerId);
          }
        });
      }
        break;

      case MAP_MOVE_HANDLER: {
        final MapMoveHandler h = new MapMoveHandler() {

          public void onMove(MapMoveEvent e) {
            textBox.setText(textBox.getText() + "onMove()");
          }
        };
        map.addMapMoveHandler(h);
        removeHandlerButton.addClickListener(new ClickListener() {
          public void onClick(Widget sender) {
            map.removeMapMoveHandler(h);
            // removeRowFromTable(nextListenerId);
          }
        });
      }
        break;

      case MAP_MOVE_START_HANDLER: {
        final MapMoveStartHandler h = new MapMoveStartHandler() {

          public void onMoveStart(MapMoveStartEvent e) {
            textBox.setText(textBox.getText() + "onMoveStart()");
          }
        };
        map.addMapMoveStartHandler(h);
        removeHandlerButton.addClickListener(new ClickListener() {
          public void onClick(Widget sender) {
            map.removeMapMoveStartHandler(h);
            // removeRowFromTable(nextListenerId);
          }
        });
      }
        break;

      case MAP_REMOVE_MAP_TYPE_HANDLER: {
        final MapRemoveMapTypeHandler h = new MapRemoveMapTypeHandler() {

          public void onRemoveMapType(MapRemoveMapTypeEvent event) {
            textBox.setText(textBox.getText() + "onRemoveMapType("
                + event.getType().getName(true) + ")");
          }

        };

        map.addMapRemoveMapTypeHandler(h);
        removeHandlerButton.addClickListener(new ClickListener() {

          public void onClick(Widget sender) {
            map.removeMapRemoveMapTypeHandler(h);
            // removeRowFromTable(nextListenerId);
          }

        });
      }
        break;
      case MAP_REMOVE_OVERLAY_HANDLER: {
        final MapRemoveOverlayHandler h = new MapRemoveOverlayHandler() {

          public void onRemoveOverlay(MapRemoveOverlayEvent event) {
            textBox.setText(textBox.getText() + "onRemoveOverlay("
                + event.getOverlay().toString() + ")");
          }

        };

        map.addMapRemoveOverlayHandler(h);
        removeHandlerButton.addClickListener(new ClickListener() {

          public void onClick(Widget sender) {
            map.removeMapRemoveOverlayHandler(h);
            // removeRowFromTable(nextListenerId);
          }

        });
      }
        break;
      case MAP_RIGHT_CLICK_HANDLER: {
        final MapRightClickHandler h = new MapRightClickHandler() {

          public void onRightClick(MapRightClickEvent e) {
            textBox.setText(textBox.getText() + "onRightClick(" + e.getPoint()
                + ")");
          }
        };
        map.addMapRightClickHandler(h);
        removeHandlerButton.addClickListener(new ClickListener() {
          public void onClick(Widget sender) {
            map.removeMapRightClickHandler(h);
            // removeRowFromTable(nextListenerId);
          }
        });
      }
        break;

      case MAP_TYPE_CHANGED_HANDLER: {
        final MapTypeChangedHandler h = new MapTypeChangedHandler() {

          public void onTypeChanged(MapTypeChangedEvent e) {
            textBox.setText(textBox.getText() + "onTypeChanged()");
          }
        };
        map.addMapTypeChangedHandler(h);
        removeHandlerButton.addClickListener(new ClickListener() {
          public void onClick(Widget sender) {
            map.removeMapTypeChangedHandler(h);
            // removeRowFromTable(nextListenerId);
          }
        });
      }
        break;

      case MAP_ZOOM_END_HANDLER: {
        final MapZoomEndHandler h = new MapZoomEndHandler() {

          public void onZoomEnd(MapZoomEndEvent e) {
            textBox.setText(textBox.getText() + "onZoomEnd("
                + e.getNewZoomLevel() + "," + e.getOldZoomLevel() + ")");
          }
        };
        map.addMapZoomEndHandler(h);
        removeHandlerButton.addClickListener(new ClickListener() {
          public void onClick(Widget sender) {
            map.removeMapZoomEndHandler(h);
            // removeRowFromTable(nextListenerId);
          }
        });
      }
        break;

      default:
        Window.alert("Unhandled HandlerActions case : " + a.valueOf());
    }
  }

  private void clearListenerTable() {
    int numRows = handlerTable.getRowCount();
    for (int i = numRows - 1; i >= 0; i--) {
      handlerTable.removeRow(i);
    }

    handlerTable.setHTML(0, 0, "<b>ID</b>");
    handlerTable.setHTML(0, 1, "<b>Type</b>");
    handlerTable.setHTML(0, 2, "<b>Events</b>");
    handlerTable.setHTML(0, 3, "<b>Clear Text</b>");
    handlerTable.setHTML(0, 4, "<b>Remove Listener</b>");
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
        if (!markerRemoved) {
          map.removeOverlay(marker);
          removeButton.setText("Add Marker");
        } else {
          map.addOverlay(marker);
          removeButton.setText("Remove Marker");
        }
        hideButton.setEnabled(markerRemoved);
        markerRemoved = !markerRemoved;
      }
    });
    hp.add(removeButton);

    final Button clearOverlaysButton = new Button("Clear Overlays");
    clearOverlaysButton.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        map.clearOverlays();
      }
    });
    hp.add(clearOverlaysButton);

    final Button infoWindowButton = new Button("Show InfoWindow");
    infoWindowButton.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        InfoWindow info = map.getInfoWindow();
        info.open(map.getCenter(), new InfoWindowContent("Hello Maps!"));
      }
    });
    hp.add(infoWindowButton);

    final Button mapTypeButton = new Button("Add Map Type");
    mapTypeButton.addClickListener(new ClickListener() {

      public void onClick(Widget sender) {
        if (!mapTypeRemoved) {
          map.addMapType(MapType.getSkyVisibleMap());
          mapTypeButton.setText("Remove MapType");
        } else {
          map.removeMapType(MapType.getSkyVisibleMap());
          removeButton.setText("Add MapType");
        }
        mapTypeRemoved = !mapTypeRemoved;
      }

    });
    hp.add(mapTypeButton);

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
    for (HandlerActions a : HandlerActions.values()) {
      listenerBox.addItem(a.valueOf());
    }
    h.add(listenerBox);

    Button b = new Button("Add Handler");
    b.addClickListener(new ClickListener() {

      public void onClick(Widget sender) {
        int selectedIndex = listenerBox.getSelectedIndex();
        HandlerActions a = HandlerActions.values()[selectedIndex];
        addListenerToMarker(a);
      }

    });
    h.add(b);

    return h;
  }
}