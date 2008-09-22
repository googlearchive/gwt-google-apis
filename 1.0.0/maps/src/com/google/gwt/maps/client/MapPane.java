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
package com.google.gwt.maps.client;

import com.google.gwt.maps.client.impl.MapImpl;
import com.google.gwt.maps.jsio.client.JSOpaque;
import com.google.gwt.user.client.ui.AbsolutePanel;

/**
 * Represents a DIV element for the specified layer of the map identified by the
 * G_MAP_XXX_PANE constants in the Maps API.
 */
public final class MapPane extends AbsolutePanel {

  /**
   * Retrieve the specified MapPane. Always creates a new MapPane object.
   * 
   * Note: Do not instantiate more than one MapPane instance per map and layer
   * combination.
   * 
   * @param map The map to query
   * @param layer The DIV element representing the layer of the map to return.
   * @return a new instance of a MapPane object.
   */
  static MapPane getPane(MapWidget map, MapPaneType layer) {
    return new MapPane(map, layer.getValue());
  }

  private MapPane(MapWidget map, JSOpaque paneId) {
    super(MapImpl.impl.getPane(map, paneId));
    map.addVirtual(this);
  }
}
