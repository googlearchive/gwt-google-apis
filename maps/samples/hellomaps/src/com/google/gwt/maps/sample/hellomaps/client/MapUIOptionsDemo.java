/*
 * Copyright 2009 Google Inc.
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

import com.google.gwt.maps.client.MapUIOptions;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.Size;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * You can add controls to the map with the addControl method. In this case, we
 * add the built-in GSmallMapControl and GMapTypeControl controls, which let us
 * pan/zoom the map and switch between Map and Satellite modes, respectively.
 */
public class MapUIOptionsDemo extends MapsDemo {

  private static HTML descHTML = null;
  private static final String descString = ""
      + "<p>Creates 2 maps centered on Palo Alto, CA USA. "
      + "You should be able to scroll the viewport by clicking and dragging "
      + "with the mouse.</p>\n"
      + "<p>The map window should be decorated with controls for zooming "
      + "in and out and for changing the map type appropriate to the map "
      + "size.</p>"
      + "<p>Use the checkboxes and the <i>Apply</i> button to change these "
      + "settings.</p>";

  public static MapsDemoInfo init() {
    return new MapsDemoInfo() {
      @Override
      public MapsDemo createInstance() {
        return new MapUIOptionsDemo();
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
        return "Adding UI styling to the Map";
      }
    };
  }

  private class UIOptionsControl {
    final Panel panel;
    final Size size;
    final Panel controlPanel;

    MapWidget map;

    CheckBox doubleClick = new CheckBox("Double click to zoom");
    CheckBox hybridMapType = new CheckBox("Hybrid MapType");
    CheckBox keyboard = new CheckBox("KeyboardHandler");
    CheckBox largeControl3d = new CheckBox("LargeMapControl3D");
    CheckBox mapTypeControl = new CheckBox("MapType control");
    CheckBox menuMapTypeControl = new CheckBox("MenuMapType control");
    CheckBox normalMapType = new CheckBox("Normal MapType");
    CheckBox physicalMapType = new CheckBox("Physical MapType");
    CheckBox satelliteMapType = new CheckBox("Satellite MapType");
    CheckBox scaleControl = new CheckBox("Scale control");
    CheckBox scrollwheel = new CheckBox("Scrollwheel");
    CheckBox smallZoomControl3d = new CheckBox("SmallZoomControl3D");

    Button applyButton = new Button("Apply");
    Button defaultsButton = new Button("Defaults");

    public UIOptionsControl(Panel panel, Size size) {
      this.panel = panel;
      this.size = size;
      this.map = newMapWidget();

      VerticalPanel vp = new VerticalPanel();
      panel = vp;
      vp.getElement().getStyle().setPropertyPx("marginLeft", 10);
      vp.getElement().getStyle().setPropertyPx("marginBottom", 10);

      vp.add(keyboard);
      vp.add(scrollwheel);
      vp.add(doubleClick);

      vp.add(scaleControl);
      vp.add(smallZoomControl3d);
      vp.add(largeControl3d);

      vp.add(mapTypeControl);
      vp.add(menuMapTypeControl);
      vp.add(normalMapType);
      vp.add(hybridMapType);
      vp.add(satelliteMapType);
      vp.add(physicalMapType);

      HorizontalPanel buttonPanel = new HorizontalPanel();
      buttonPanel.add(applyButton);
      buttonPanel.add(defaultsButton);
      vp.add(buttonPanel);

      applyButton.addClickListener(new ClickListener() {
        public void onClick(Widget sender) {
          doApply();
        }
      });

      defaultsButton.addClickListener(new ClickListener() {
        public void onClick(Widget sender) {
          doResetDefaults();
        }
      });

      controlPanel = vp;
      doResetDefaults();
    }

    private MapWidget newMapWidget() {
      MapWidget tmpMap = new MapWidget(LatLng.newInstance(37.4419, -122.1419),
          13);
      tmpMap.setSize(size.getWidth() + "px", size.getHeight() + "px");
      return tmpMap;
    }

    private void doApply() {
      panel.remove(map);
      panel.remove(controlPanel);
      map = newMapWidget();

      MapUIOptions options = MapUIOptions.newInstance(size);
      options.setDoubleClick(doubleClick.isChecked());
      options.setHybridMapType(hybridMapType.isChecked());
      options.setKeyboard(keyboard.isChecked());
      options.setLargeMapControl3d(largeControl3d.isChecked());
      options.setMapTypeControl(mapTypeControl.isChecked());
      options.setMenuMapTypeControl(menuMapTypeControl.isChecked());
      options.setNormalMapType(normalMapType.isChecked());
      options.setPhysicalMapType(physicalMapType.isChecked());
      options.setSatelliteMapType(satelliteMapType.isChecked());
      options.setScaleControl(scaleControl.isChecked());
      options.setScrollwheel(scrollwheel.isChecked());
      options.setSmallZoomControl3d(smallZoomControl3d.isChecked());

      map.setUI(options);
      panel.add(map);
      panel.add(controlPanel);
    }

    private void doResetDefaults() {
      panel.remove(map);
      panel.remove(controlPanel);
      map = newMapWidget();

      MapUIOptions options = MapUIOptions.newInstance(size);
      doubleClick.setChecked(options.getDoubleClick());
      hybridMapType.setChecked(options.getHybridMapType());
      keyboard.setChecked(options.getKeyboard());
      largeControl3d.setChecked(options.getLargeMapControl3d());
      mapTypeControl.setChecked(options.getMapTypeControl());
      menuMapTypeControl.setChecked(options.getMenuMapTypeControl());
      normalMapType.setChecked(options.getNormalMapType());
      physicalMapType.setChecked(options.getPhysicalMapType());
      satelliteMapType.setChecked(options.getSatelliteMapType());
      scaleControl.setChecked(options.getScaleControl());
      scrollwheel.setChecked(options.getScrollwheel());
      smallZoomControl3d.setChecked(options.getSmallZoomControl3d());

      map.setUI(options);
      panel.add(map);
      panel.add(controlPanel);
    }
  }

  public MapUIOptionsDemo() {
    VerticalPanel vp = new VerticalPanel();

    Size smallSize = Size.newInstance(250, 250);
    HorizontalPanel smallMapPanel = new HorizontalPanel();
    new UIOptionsControl(smallMapPanel, smallSize);
    smallMapPanel.getElement().getStyle().setPropertyPx("marginBottom", 20);

    Size largeSize = Size.newInstance(500, 400);
    HorizontalPanel largeMapPanel = new HorizontalPanel();
    new UIOptionsControl(largeMapPanel, largeSize);

    vp.add(new Label("Small Map with default UI style"));
    vp.add(smallMapPanel);
    vp.add(new Label("Large Map with default UI style"));
    vp.add(largeMapPanel);
    initWidget(vp);
  }
}
