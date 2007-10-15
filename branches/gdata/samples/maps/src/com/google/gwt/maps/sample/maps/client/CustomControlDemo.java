/*
 * Copyright 2007 Google Inc.
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
import com.google.gwt.maps.client.control.ControlPosition;
import com.google.gwt.maps.client.control.Control.CustomControl;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Panel;
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

  private MapWidget map;

  public static MapsDemoInfo init() {
    return new MapsDemoInfo() {
      public MapsDemo createInstance() {
        return new CustomControlDemo();
      }

      public String getName() {
        return "Custom Map Controls";
      }
    };
  }

  private static class TextualZoomControl extends CustomControl {
    public TextualZoomControl() {
      super(new ControlPosition(ControlPosition.TOP_LEFT, 7, 7));
    }

    protected Widget initialize(final MapWidget map) {
      System.out.println("initializing!");
      Panel container = new FlowPanel();
      container.add(new TextualZoomWidget("Zoom In", new ClickListener() {
        public void onClick(Widget sender) {
          map.zoomIn();
        }
      }));
      container.add(new TextualZoomWidget("Zoom Out", new ClickListener() {
        public void onClick(Widget sender) {
          map.zoomOut();
        }
      }));
      return container;
    }
  }

  private static class TextualZoomWidget extends Composite {
    public TextualZoomWidget(String text, ClickListener listener) {
      FocusPanel panel = new FocusPanel();
      DOM.setInnerText(panel.getElement(), text);
      panel.setTitle(text);
      panel.addClickListener(listener);
      initWidget(panel);
      setStyleName("textualZoomControl");
    }
  }

  public CustomControlDemo() {
    map = new MapWidget(new LatLng(37.441944, -122.141944), 13);
    map.setSize("600px", "400px");
    map.addControl(new TextualZoomControl());
    initWidget(map);
  }
}
