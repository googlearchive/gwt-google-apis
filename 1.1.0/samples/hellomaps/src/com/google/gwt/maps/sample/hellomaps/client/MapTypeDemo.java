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
import com.google.gwt.maps.client.MapType;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.ControlAnchor;
import com.google.gwt.maps.client.control.ControlPosition;
import com.google.gwt.maps.client.control.LargeMapControl;
import com.google.gwt.maps.client.control.Control.CustomControl;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This example demonstrates the different map types available from the Maps
 * API.
 */
public class MapTypeDemo extends MapsDemo {
  private class DemoCustomMapTypeControl extends CustomControl {
    private final ListBox lb = new ListBox();
    private final HashMap<String, MapType> mapTypes = new HashMap<String, MapType>();

    DemoCustomMapTypeControl() {
      super(new ControlPosition(ControlAnchor.TOP_RIGHT, 7, 7));
    }

    @Override
    public boolean isSelectable() {
      return true;
    }

    @Override
    protected Widget initialize(final MapWidget map) {
      initListByMapType("", MapType.getDefaultMapTypes());
      List<MapType> tmpTypeList = new ArrayList<MapType>();
      tmpTypeList.add(MapType.getPhysicalMap());
      tmpTypeList.add(MapType.getEarthMap());
      tmpTypeList.add(MapType.getAerialMap());
      tmpTypeList.add(MapType.getAerialHybridMap());
      initListByMapType("", tmpTypeList);
      initListByMapType("Moon: ", MapType.getMoonMapTypes());
      initListByMapType("Mars: ", MapType.getMarsMapTypes());
      initListByMapType("Sky: ", MapType.getSkyMapTypes());
      initListByMapType("Mapmaker: ", MapType.getMapmakerMapTypes());

      lb.addChangeHandler(new ChangeHandler() {
        public void onChange(ChangeEvent event) {
          String selection = lb.getItemText(lb.getSelectedIndex());
          MapType mt = mapTypes.get(selection);
          if (mt != null) {
            map.setCurrentMapType(mt);
          }
        }
      });
      return lb;
    }

    private void initListByMapType(String prefix, List<MapType> types) {
      for (MapType mt : types) {
        String key = prefix + mt.getName(false);
        lb.addItem(key);
        mapTypes.put(key, mt);
      }
    }
  }

  private static HTML descHTML = null;

  private static final String descString = "<p>Creates a 500 x 500 pixel map viewport centered on Palo Alto, CA USA. "
      + "You should be able to scroll the viewport by clicking and dragging "
      + "with the mouse.</p>\n"
      + "<p>Choose a different item from the drop down menu to see the different map types.</p>\n";

  public static MapsDemoInfo init() {
    return new MapsDemoInfo() {
      @Override
      public MapsDemo createInstance() {
        return new MapTypeDemo();
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
        return "Map Types";
      }
    };
  }

  private MapWidget map;

  public MapTypeDemo() {

    VerticalPanel vertPanel = new VerticalPanel();
    vertPanel.setStyleName("hm-panel");

    map = new MapWidget(LatLng.newInstance(37.4419, -122.1419), 13);
    map.setSize("500px", "500px");

    // Add in all the map types we know about.
    for (MapType mt : MapType.getDefaultMapTypes()) {
      map.addMapType(mt);
    }
    map.addMapType(MapType.getPhysicalMap());
    map.addMapType(MapType.getEarthMap());

    for (MapType mt : MapType.getMoonMapTypes()) {
      map.addMapType(mt);
    }

    for (MapType mt : MapType.getMarsMapTypes()) {
      map.addMapType(mt);
    }

    for (MapType mt : MapType.getSkyMapTypes()) {
      map.addMapType(mt);
    }

    for (MapType mt : MapType.getMapmakerMapTypes()) {
      map.addMapType(mt);
    }

    map.addMapType(MapType.getAerialMap());
    map.addMapType(MapType.getAerialHybridMap());

    map.addControl(new DemoCustomMapTypeControl());
    map.addControl(new LargeMapControl());
    vertPanel.add(map);

    initWidget(vertPanel);
  }
}
