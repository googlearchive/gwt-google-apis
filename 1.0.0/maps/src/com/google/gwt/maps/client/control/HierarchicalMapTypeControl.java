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
package com.google.gwt.maps.client.control;

import com.google.gwt.maps.client.MapType;
import com.google.gwt.maps.client.impl.HierarchicalMapTypeControlImpl;

/**
 * Creates a control with buttons to pan in four directions, and zoom in and
 * zoom out, and a zoom slider.
 */
public final class HierarchicalMapTypeControl extends Control {

  /**
   * Constructs the control. By default, the G_HYBRID_MAP map type is made a
   * child of the {@link MapType#getSatelliteMap()} map type. If this is not
   * desired, the relationship can be removed by calling the
   * {@link HierarchicalMapTypeControl#clearRelationships()} method.
   */
  public HierarchicalMapTypeControl() {
    super(HierarchicalMapTypeControlImpl.impl.construct());
  }

  /**
   * Registers a parent/child relationship between map types with the control.
   * Note that all relationships must be set up before the control is added.
   * (Adding relationships after the control is added will have no effect.)
   * 
   * @param parentType the parent MapType in the menu
   * @param childType the child MapType to add to the menu.
   */
  public void addRelationship(MapType parentType, MapType childType) {
    HierarchicalMapTypeControlImpl.impl.addRelationship(jsoPeer, parentType,
        childType);
  }

  /**
   * 
   * Registers a parent/child relationship between map types with the control.
   * Note that all relationships must be set up before the control is added.
   * (Adding relationships after the control is added will have no effect.)
   * 
   * @param parentType the parent MapType in the menu
   * @param childType the child MapType to add to the menu.
   * @param childText will be displayed next to the checkbox for the child map
   *          type instead of its name
   */
  public void addRelationship(MapType parentType, MapType childType,
      String childText) {
    HierarchicalMapTypeControlImpl.impl.addRelationship(jsoPeer, parentType,
        childType, childText);
  }

  /**
   * Registers a parent/child relationship between map types with the control.
   * Note that all relationships must be set up before the control is added.
   * (Adding relationships after the control is added will have no effect.)
   * 
   * @param parentType the parent MapType in the menu
   * @param childType the child MapType to add to the menu.
   * @param childText the text to display in the menu (instead of the default)
   * @param isDefault Set to <code>true</code> to make this map type the
   *          default selection when the map is loaded.
   */
  public void addRelationship(MapType parentType, MapType childType,
      String childText, boolean isDefault) {
    HierarchicalMapTypeControlImpl.impl.addRelationship(jsoPeer, parentType,
        childType, childText, isDefault);
  }

  /**
   * Removes all relationships from the control.
   */
  public void clearRelationships() {
    HierarchicalMapTypeControlImpl.impl.clearRelationships(jsoPeer);
  }

  /**
   * Removes all relationships involving a map type from the control.
   */
  public void removeRelationship(MapType mapType) {
    HierarchicalMapTypeControlImpl.impl.removeRelationship(jsoPeer, mapType);
  }
}
