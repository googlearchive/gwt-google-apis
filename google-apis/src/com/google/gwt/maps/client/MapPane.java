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
package com.google.gwt.maps.client;

import com.google.gwt.maps.client.impl.MapImpl;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.AbsolutePanel;

public final class MapPane extends AbsolutePanel {

  public static class MapPaneType {
    private final int value;

    private MapPaneType(int value) {
      this.value = value;
    }

    protected int getValue() {
      return value;
    }
  }

  public static final MapPaneType FLOAT_PANE = new MapPaneType(7);

  public static final MapPaneType FLOAT_SHADOW_PANE = new MapPaneType(5);

  public static final MapPaneType MAP_PANE = new MapPaneType(0);

  public static final MapPaneType MARKER_MOUSE_PANE = new MapPaneType(6);

  public static final MapPaneType MARKER_PANE = new MapPaneType(4);

  public static final MapPaneType MARKER_SHADOW_PANE = new MapPaneType(2);

  private static MapPane floatPane;

  private static MapPane floatShadowPane;

  private static MapPane mapPane;

  private static MapPane markerMousePane;

  private static MapPane markerPane;

  private static MapPane markerShadowPane;

  protected static MapPane getPane(MapWidget map, MapPaneType type) {
    int typeId = type.value;
    switch (typeId) {
      case 0:
        if (mapPane == null) {
          mapPane = new MapPane(map, typeId);
        }
        return mapPane;
      case 2:
        if (markerShadowPane == null) {
          markerShadowPane = new MapPane(map, typeId);
        }
        return markerShadowPane;
      case 4:
        if (markerPane == null) {
          markerPane = new MapPane(map, typeId);
        }
        return markerPane;
      case 5:
        if (floatShadowPane == null) {
          floatShadowPane = new MapPane(map, typeId);
        }
        return floatShadowPane;
      case 6:
        if (markerMousePane == null) {
          markerMousePane = new MapPane(map, typeId);
        }
        return markerMousePane;
      case 7:
        if (floatPane == null) {
          floatPane = new MapPane(map, typeId);
        }
        return floatPane;
      default:
        return null;
    }
  }

  private MapPane(MapWidget map, int paneId) {
    Element mapPaneElement = MapImpl.impl.getPane(map, paneId);
    setElement(mapPaneElement);
    map.addVirtual(this);
  }
}
