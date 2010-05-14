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
package com.google.gwt.maps.sample.hellomaps.client;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.maps.client.MapUIOptions;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.event.PolygonCancelLineHandler;
import com.google.gwt.maps.client.event.PolygonEndLineHandler;
import com.google.gwt.maps.client.event.PolygonLineUpdatedHandler;
import com.google.gwt.maps.client.event.PolylineCancelLineHandler;
import com.google.gwt.maps.client.event.PolylineEndLineHandler;
import com.google.gwt.maps.client.event.PolylineLineUpdatedHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.PolyEditingOptions;
import com.google.gwt.maps.client.overlay.PolyStyleOptions;
import com.google.gwt.maps.client.overlay.Polygon;
import com.google.gwt.maps.client.overlay.Polyline;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Demonstrates editable/drawable polylines/polygons.
 */
public class DrawingOverlayDemo extends MapsDemo {
  private static HTML descHTML = null;
  private static final String descString = "<p>Creates a map"
      + " centered on Paris, France.</p>"
      + "<p>You can create lines or polygons on the map by clicking the <i>Draw New Object</i> button. "
      + "The resulting dialog box allows you to control the properties of the object to draw."
      + "You can edit the last line you created by clicking the <i>Edit Polyline</i> button, or "
      + "the last polygon you created by clicking the <i>Edit Polygon</i> button.</p>";

  public static MapsDemoInfo init() {
    return new MapsDemoInfo() {
      @Override
      public MapsDemo createInstance() {
        return new DrawingOverlayDemo();
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
        return "Drawing Demo";
      }
    };
  }

  private String color = "#FF0000";
  private boolean makePolygon = false;
  private MapWidget map;
  private Label message1;
  private Label message2;
  private double opacity = 1.0;
  private int weight = 1;
  private boolean fillFlag = false;
  private Polyline lastPolyline = null;
  private Polygon lastPolygon = null;
  private DialogBox addPolyDialog = null;
  private Button editPolylineButton = new Button("Edit Last Polyline");
  private Button editPolygonButton = new Button("Edit Last Polygon");

  public DrawingOverlayDemo() {

    // Go to Paris
    map = new MapWidget(LatLng.newInstance(48.859068, 2.344894), 12);
    map.setSize("500px", "400px");

    // Change the default UI controls a bit to help with drawing.
    MapUIOptions options = map.getDefaultUI();
    options.setScrollwheel(false);
    options.setDoubleClick(false);
    options.setLargeMapControl3d(true);
    map.setUI(options);
    map.setDoubleClickZoom(false);
    map.setDraggable(false);

    VerticalPanel panel = new VerticalPanel();
    panel.setSpacing(10);
    panel.add(makeToolbar());
    panel.add(map);
    message1 = new Label();
    panel.add(message1);
    message2 = new Label();
    panel.add(message2);
    initWidget(panel);
  }

  private void createPolyline() {
    PolyStyleOptions style = PolyStyleOptions.newInstance(color, weight,
        opacity);

    final Polyline poly = new Polyline(new LatLng[0]);
    lastPolyline = poly;
    map.addOverlay(poly);
    poly.setDrawingEnabled();
    poly.setStrokeStyle(style);
    message2.setText("");
    poly.addPolylineLineUpdatedHandler(new PolylineLineUpdatedHandler() {

      public void onUpdate(PolylineLineUpdatedEvent event) {
        message2.setText(message2.getText() + " : Line Updated");
      }
    });

    poly.addPolylineCancelLineHandler(new PolylineCancelLineHandler() {

      public void onCancel(PolylineCancelLineEvent event) {
        message2.setText(message2.getText() + " : Line Canceled");
      }
    });

    poly.addPolylineEndLineHandler(new PolylineEndLineHandler() {

      public void onEnd(PolylineEndLineEvent event) {
        message2.setText(message2.getText() + " : Line End at "
            + event.getLatLng() + ".  Bounds="
            + poly.getBounds().getNorthEast() + ","
            + poly.getBounds().getSouthWest() + " length=" + poly.getLength()
            + "m");
      }
    });
  }

  private void createPolygon() {
    PolyStyleOptions style = PolyStyleOptions.newInstance(color, weight,
        opacity);

    final Polygon poly = new Polygon(new LatLng[0], color, weight, opacity,
        color, fillFlag ? .7 : 0.0);
    lastPolygon = poly;
    map.addOverlay(poly);
    poly.setDrawingEnabled();
    poly.setStrokeStyle(style);
    message2.setText("");
    poly.addPolygonLineUpdatedHandler(new PolygonLineUpdatedHandler() {

      public void onUpdate(PolygonLineUpdatedEvent event) {
        message2.setText(message2.getText() + " : Polygon Updated");
      }
    });

    poly.addPolygonCancelLineHandler(new PolygonCancelLineHandler() {

      public void onCancel(PolygonCancelLineEvent event) {
        message2.setText(message2.getText() + " : Polygon Cancelled");
      }
    });

    poly.addPolygonEndLineHandler(new PolygonEndLineHandler() {

      public void onEnd(PolygonEndLineEvent event) {
        message2.setText(message2.getText() + " : Polygon End at "
            + event.getLatLng() + ".  Bounds="
            + poly.getBounds().getNorthEast() + ","
            + poly.getBounds().getSouthWest() + " area=" + poly.getArea() + "m");
      }
    });
  }

  private void editPolygon() {
    if (lastPolygon == null) {
      return;
    }
    // allow up to 10 vertices to exist in the polygon.
    lastPolygon.setEditingEnabled(PolyEditingOptions.newInstance(10));
  }

  private void editPolyline() {
    if (lastPolyline == null) {
      return;
    }
    // allow up to 10 vertices to exist in the line.
    lastPolyline.setEditingEnabled(PolyEditingOptions.newInstance(10));
  }

  /**
   * Create the toolbar above the map. Note that the map must be initialized
   * before this method is called.
   */
  private Widget makeToolbar() {
    DockPanel p = new DockPanel();
    p.setWidth("100%");

    HorizontalPanel buttonPanel = new HorizontalPanel();

    Button addButton = new Button("Draw new object");
    addButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        if (addPolyDialog == null) {
          addPolyDialog = makeAddPolyDialog();
        }
        addPolyDialog.center();
        addPolyDialog.show();
        if (lastPolygon != null) {
          lastPolygon.setEditingEnabled(false);
        }
        if (lastPolyline != null) {
          lastPolyline.setEditingEnabled(false);
        }
      }
    });
    buttonPanel.add(addButton);

    editPolylineButton.setEnabled(false);
    editPolylineButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        editPolyline();
      }
    });
    buttonPanel.add(editPolylineButton);

    editPolygonButton.setEnabled(false);
    editPolygonButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        editPolygon();
      }
    });
    buttonPanel.add(editPolygonButton);

    p.add(buttonPanel, DockPanel.EAST);

    return p;
  }

  private DialogBox makeAddPolyDialog() {

    DialogBox dialog = new DialogBox();
    dialog.setTitle("Add Polyline");

    Grid grid = new Grid(2, 4);

    VerticalPanel vp = new VerticalPanel();
    grid.setHTML(0, 0, "<b>Opacity</b>");
    // The drop down lists for setting the style
    final ListBox opacityBox = new ListBox();
    for (int i = 100; i > 0; i -= 10) {
      opacityBox.addItem(i + "%");
    }
    opacityBox.addChangeHandler(new ChangeHandler() {
      public void onChange(ChangeEvent event) {
        String val = opacityBox.getItemText(opacityBox.getSelectedIndex());
        opacity = Double.parseDouble(val.replace("%", "")) / 100.0;
      }
    });
    grid.setWidget(1, 0, opacityBox);

    grid.setHTML(0, 1, "<b>Weight</b>");
    final ListBox weightBox = new ListBox();
    weightBox.addItem("1 pixel");
    weightBox.addItem("2 pixels");
    weightBox.addItem("3 pixels");
    weightBox.addItem("5 pixels");
    weightBox.addItem("10 pixels");
    weightBox.addChangeHandler(new ChangeHandler() {
      public void onChange(ChangeEvent event) {
        String val = weightBox.getItemText(weightBox.getSelectedIndex());
        val = val.replace(" pixel", "");
        val = val.replace("s", "");
        weight = Integer.parseInt(val);
      }
    });
    grid.setWidget(1, 1, weightBox);

    grid.setHTML(0, 2, "<b>Color</b>");
    final ListBox colorBox = new ListBox();
    colorBox.addItem("#FF0000 red");
    colorBox.addItem("#FFFF00 yellow");
    colorBox.addItem("#00FF00 green");
    colorBox.addItem("#00FFFF cyan");
    colorBox.addItem("#0000FF blue");
    colorBox.addItem("#FF00FF violet");
    colorBox.addChangeHandler(new ChangeHandler() {
      public void onChange(ChangeEvent event) {
        color = colorBox.getItemText(colorBox.getSelectedIndex()).substring(0,
            7);
      }
    });
    grid.setWidget(1, 2, colorBox);

    grid.setHTML(0, 3, "<b>Fill Polygon</b>");
    final CheckBox fillCheckBox = new CheckBox("");
    fillCheckBox.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        fillFlag = fillCheckBox.getValue();
      }
    });
    grid.setWidget(1, 3, fillCheckBox);

    Button addPolylineButton = new Button("Make Polyline");
    addPolylineButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        message1.setText("opacity=" + opacity + " color=" + color + " weight="
            + weight + " polygon=" + makePolygon + " center=" + map.getCenter()
            + " zoom=" + map.getZoomLevel());
        addPolyDialog.hide();
        createPolyline();
        editPolylineButton.setEnabled(true);
      }
    });

    Button addPolygonButton = new Button("Make Polygon");
    addPolygonButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        message1.setText("Opacity=" + opacity + " color=" + color + "weight="
            + weight + "polygon = " + makePolygon + "Center=" + map.getCenter()
            + " zoom=" + map.getZoomLevel() + "fill=" + fillFlag);
        addPolyDialog.hide();
        createPolygon();
        editPolygonButton.setEnabled(true);
      }
    });

    Button cancelButton = new Button("Cancel");
    cancelButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        addPolyDialog.hide();
      }
    });

    HorizontalPanel buttonPanel = new HorizontalPanel();
    buttonPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
    buttonPanel.add(addPolylineButton);
    buttonPanel.add(addPolygonButton);
    buttonPanel.add(cancelButton);
    vp.add(grid);
    vp.add(buttonPanel);
    dialog.add(vp);

    return dialog;
  }
}
