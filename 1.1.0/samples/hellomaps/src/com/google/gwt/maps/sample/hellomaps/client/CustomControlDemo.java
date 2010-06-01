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

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.maps.client.MapType;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.Control;
import com.google.gwt.maps.client.control.ControlAnchor;
import com.google.gwt.maps.client.control.ControlPosition;
import com.google.gwt.maps.client.control.HierarchicalMapTypeControl;
import com.google.gwt.maps.client.control.Control.CustomControl;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Version 2 of the Maps API introduces the ability to create custom map
 * controls, like the built-in pan and zoom controls, by subclassing the
 * built-in GControl class. In this example, we create a simple zoom control
 * that has textual links rather than the graphical icons used in the standard
 * Google Maps zoom control.
 *
 * The GTextualZoomControl class defines the two required methods of the
 * GControl interface: initialize(), in which we create the DOM elements
 * representing our control; and getDefaultPosition(), in which we return the
 * GControlPosition used if another position is not given when this control is
 * added to a map. See the class reference for GControl for more information
 * about the methods you can override when you create your custom controls.
 *
 * All map controls should be added to the map container which can be accessed
 * with the getContainer() method on Map
 */
public class CustomControlDemo extends MapsDemo {

  /**
   * Images used in the custom map control for zooming.
   */
  public interface ControlImageBundle extends ClientBundle {
    ImageResource minus();

    @Source("mozicon_earth.png")
    ImageResource moziconEarth();

    ImageResource plus();

    ImageResource traffic();
  }

  private enum ControlDemos {
    TEST_HIERARCHICAL_CONTROL("Hierarchical Control"), //
    TEST_IMAGE_CUSTOM_ZOOM_CONTROL("Use a custom Image Zoom Control"), //
    TEST_TEXT_CUSTOM_ZOOM_CONTROL("Use a custom Text Zoom Control");

    final String value;

    ControlDemos(String s) {
      value = s;
    }

    String valueOf() {
      return value;
    }
  }

  private static class ImageZoomControl extends CustomControl {
    public ImageZoomControl() {
      super(new ControlPosition(ControlAnchor.TOP_LEFT, 7, 7));
    }

    @Override
    protected Widget initialize(final MapWidget map) {
      ControlImageBundle imgBundle = GWT.create(ControlImageBundle.class);
      Image trafficImage = new Image(imgBundle.traffic());
      Image satelliteImage = new Image(imgBundle.moziconEarth());
      Image zoomInImage = new Image(imgBundle.plus());
      Image zoomOutImage = new Image(imgBundle.minus());

      trafficImage.addClickHandler(new ClickHandler() {
        public void onClick(ClickEvent clickEvent) {
          map.setCurrentMapType(MapType.getNormalMap());
        }
      });
      satelliteImage.addClickHandler(new ClickHandler() {
        public void onClick(ClickEvent clickEvent) {
          map.setCurrentMapType(MapType.getSatelliteMap());
        }
      });
      zoomInImage.addClickHandler(new ClickHandler() {
        public void onClick(ClickEvent clickEvent) {
          map.zoomIn();
        }
      });
      zoomOutImage.addClickHandler(new ClickHandler() {
        public void onClick(ClickEvent clickEvent) {
          map.zoomOut();
        }
      });

      Grid container = new Grid(2, 2);
      container.setWidget(0, 0, zoomInImage);
      container.setWidget(1, 0, zoomOutImage);
      container.setWidget(0, 1, trafficImage);
      container.setWidget(1, 1, satelliteImage);

      return container;
    }

    @Override
    public boolean isSelectable() {
      return false;
    }
  }

  private static class TextualZoomControl extends CustomControl {
    public TextualZoomControl() {
      super(new ControlPosition(ControlAnchor.TOP_LEFT, 7, 7));
    }

    @Override
    public boolean isSelectable() {
      return false;
    }

    @Override
    protected Widget initialize(final MapWidget map) {
      Panel container = new FlowPanel();
      Button zoomInButton = new Button("Zoom In");
      zoomInButton.setStyleName("textualZoomControl");
      zoomInButton.addClickHandler(new ClickHandler() {
        public void onClick(ClickEvent clickEvent) {
          map.zoomIn();
        }
      });
      Button zoomOutButton = new Button("Zoom Out");
      zoomOutButton.setStyleName("textualZoomControl");
      zoomOutButton.addClickHandler(new ClickHandler() {
        public void onClick(ClickEvent clickEvent) {
          map.zoomOut();
        }
      });

      container.add(zoomInButton);
      container.add(zoomOutButton);
      return container;
    }
  }

  private static HTML descHTML = null;

  private static final String descString = ""
      + "<p>Creates a 500 x 300 pixel map viewport centered on Palo Alto, CA USA. </p>"
      + "<ul>\n"
      + "<li><b>Hierarchical Control</b> A control provided by the API that allows the "
      + "user to change the map type using a drop down menu of checkboxes."
      + "</li>\n"
      + "<li><b>Image Zoom Control</b> The standard controls have been replaced with clickable "
      + "images for zooming in and our or changing the map type from satellite to road map."
      + "</li>"
      + "<li><b>Text Zoom Control</b> The standard zoom in and zoom out controls have been replaced with a "
      + "custom control in the form of two boxes with the text <i>Zoom In</i> and <i>Zoom Out</i> "
      + "at the upper left of the map.</li>\n"
      + "</ul>\n"
      + "<p>See also the Maps JavaScript API Example: "
      + "<a href=\"http://code.google.com/apis/maps/documentation/examples/control-custom.html\">"
      + "http://code.google.com/apis/maps/documentation/examples/control-custom.html</a></p>\n";

  private static HierarchicalMapTypeControl hControl;

  public static MapsDemoInfo init() {
    return new MapsDemoInfo() {
      @Override
      public MapsDemo createInstance() {
        return new CustomControlDemo();
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
        return "Custom Map Controls";
      }
    };
  }

  /**
   * Provide access to the HierarchicalMapTypeControl singleton.
   *
   * @return an instance of the control is created if necessary.
   */
  private static HierarchicalMapTypeControl getHierarchicalMapTypeControl() {
    if (hControl != null) {
      return hControl;
    }
    hControl = new HierarchicalMapTypeControl();
    hControl.addRelationship(MapType.getNormalMap(),
        MapType.getMarsVisibleMap(), "Mars visible");
    hControl.addRelationship(MapType.getNormalMap(),
        MapType.getMarsInfraredMap(), "Mars infrared");
    hControl.addRelationship(MapType.getNormalMap(),
        MapType.getMarsElevationMap(), "Mars elevation", true);
    return hControl;
  }

  private final ListBox actionListBox;

  private Control currentControl = null;

  private final MapWidget map;

  public CustomControlDemo() {

    VerticalPanel vertPanel = new VerticalPanel();
    vertPanel.setStyleName("hm-panel");

    actionListBox = new ListBox();
    for (ControlDemos cd : ControlDemos.values()) {
      actionListBox.addItem(cd.valueOf());
    }

    actionListBox.addChangeHandler(new ChangeHandler() {
      public void onChange(ChangeEvent event) {
        displayCustomControl();
      }
    });

    HorizontalPanel horizPanel = new HorizontalPanel();
    horizPanel.add(new Label("Choose Action:"));
    horizPanel.add(actionListBox);
    horizPanel.setSpacing(10);
    vertPanel.add(horizPanel);

    map = new MapWidget(LatLng.newInstance(37.441944, -122.141944), 13);
    map.setSize("500px", "300px");
    map.addMapType(MapType.getNormalMap());
    map.addMapType(MapType.getSatelliteMap());
    map.addMapType(MapType.getMarsVisibleMap());
    map.addMapType(MapType.getMarsElevationMap());
    map.addMapType(MapType.getMarsInfraredMap());
    vertPanel.add(map);

    new Timer() {
      @Override
      public void run() {
        displayCustomControl();
      }
    }.schedule(250);

    initWidget(vertPanel);
  }

  /**
   * Display the appropriate custom control depending on what the user has
   * selected in the action box.
   */
  private void displayCustomControl() {

    if (currentControl != null) {
      map.removeControl(currentControl);
      currentControl = null;
    }

    ControlDemos selected = ControlDemos.values()[actionListBox.getSelectedIndex()];

    switch (selected) {
      case TEST_IMAGE_CUSTOM_ZOOM_CONTROL:
        currentControl = new ImageZoomControl();
        break;
      case TEST_TEXT_CUSTOM_ZOOM_CONTROL:
        currentControl = new TextualZoomControl();
        break;
      case TEST_HIERARCHICAL_CONTROL:
        currentControl = getHierarchicalMapTypeControl();
        break;
      default:
        Window.alert("Unknown selection: " + selected.valueOf());
        return;
    }

    map.addControl(currentControl);
  }
}
