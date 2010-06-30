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
import com.google.gwt.maps.client.InfoWindow;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapUIOptions;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.event.MapDoubleClickHandler;
import com.google.gwt.maps.client.event.MarkerClickHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.Size;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import java.util.Date;

/**
 * Widget displaying a google maps and a set of {@link Location}s. Allows to
 * pass callbacks to react to creation and deletion of a {@link Location}.
 */
public class TravelMap extends ResizeComposite {

  /**
   * Interface to implement in order to interact with {@link TravelMap}.
   */
  public interface LocationHandler {
    void handle(Location location);
  }

  private final MapWidget map;
  private final LatLng zero = LatLng.newInstance(0, 0);
  private LocationHandler deleteHandler;

  private TravelMap(boolean doubleClickToZoom) {
    map = new MapWidget();
    map.setSize("100%", "100%");
    map.setCenter(zero, 0);
    MapUIOptions options = MapUIOptions.newInstance(Size.newInstance(200, 200));
    options.setDoubleClick(doubleClickToZoom);
    map.setUI(options);
    DockLayoutPanel panel = new DockLayoutPanel(Unit.PCT);
    panel.add(map);
    initWidget(panel);
  }

  public TravelMap() {
    this(true);
  }

  public TravelMap(final LocationHandler createHandler,
      final LocationHandler deleteHandler) {
    this(false);
    this.deleteHandler = deleteHandler;
    map.addMapDoubleClickHandler(new MapDoubleClickHandler() {
      public void onDoubleClick(MapDoubleClickEvent event) {
        InfoWindow info = map.getInfoWindow();
        info.open(event.getLatLng(), newLocationCreateForm(event.getLatLng(),
            createHandler, info));
      }
    });
  }

  public void clear() {
    map.clearOverlays();
  }

  public void zoomOut() {
    map.setCenter(zero, 0);
  }

  public void showLocation(JsArray<Location> locations) {
    clear();
    for (int i = 0; i < locations.length(); i++) {
      map.addOverlay(createMarker(locations.get(i)));
    }
  }

  private Marker createMarker(final Location loc) {
    LatLng latlng = LatLng.newInstance(loc.getLatitude(), loc.getLongitude());
    final Marker marker = new Marker(latlng);
    marker.addMarkerClickHandler(new MarkerClickHandler() {
      public void onClick(MarkerClickEvent event) {
        final InfoWindow info = map.getInfoWindow();
        info.open(marker, newLocationDescription(loc, info, marker));
      }
    });
    return marker;
  }

  private InfoWindowContent newLocationCreateForm(final LatLng point,
      final LocationHandler handler, final InfoWindow info) {
    final TextBox titleBox = new TextBox();
    final TextArea descriptionBox = new TextArea();
    Button saveButton = new Button("Save");
    saveButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        titleBox.setReadOnly(true);
        descriptionBox.setReadOnly(true);
        Location loc = Location.newInstance();
        loc.setTitle(titleBox.getText());
        loc.setDescription(descriptionBox.getText());
        loc.setLatitude(point.getLatitude());
        loc.setLongitude(point.getLongitude());
        loc.setMilis(Long.toString(new Date().getTime()));
        handler.handle(loc);
        info.close();
        map.addOverlay(createMarker(loc));
      }
    });

    VerticalPanel panel = new VerticalPanel();
    panel.add(new HTML("title:"));
    panel.add(titleBox);
    panel.add(new HTML("description"));
    panel.add(descriptionBox);
    panel.add(saveButton);
    panel.setCellHorizontalAlignment(saveButton,
        HasHorizontalAlignment.ALIGN_RIGHT);
    return new InfoWindowContent(panel);
  }

  private InfoWindowContent newLocationDescription(final Location location,
      final InfoWindow info, final Marker marker) {
    VerticalPanel panel = new VerticalPanel();
    panel.setSpacing(3);
    panel.setWidth("100%");
    Label title = new Label(location.getTitle());
    title.setStylePrimaryName("location-title");
    Label desc = new Label(location.getDescription());
    desc.setStylePrimaryName("location-desc");
    Label date = new Label("Added " + location.getDate());
    date.setStylePrimaryName("location-date");
    panel.add(title);
    panel.add(desc);
    panel.add(date);
    Button deleteButton;
    if (deleteHandler != null) {
      deleteButton = new Button("Delete");
      deleteButton.addClickHandler(new ClickHandler() {
        public void onClick(ClickEvent event) {
          map.removeOverlay(marker);
          deleteHandler.handle(location);
          info.close();
        }
      });
      panel.add(deleteButton);
      panel.setCellHorizontalAlignment(deleteButton,
          HasHorizontalAlignment.ALIGN_RIGHT);
    }
    return new InfoWindowContent(panel);
  }
}
