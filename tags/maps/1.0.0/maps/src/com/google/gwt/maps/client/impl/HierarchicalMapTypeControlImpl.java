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
package com.google.gwt.maps.client.impl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.jsio.client.Constructor;
import com.google.gwt.maps.jsio.client.JSFlyweightWrapper;
import com.google.gwt.maps.client.MapType;

/**
 * The HierarchicalMapTypeControl provides a "nested" map type control for
 * selecting and switching between supported map types via buttons and nested
 * checkboxes. Controls will be made available for all map types currently
 * attached to the map at the time the control is constructed.
 * 
 * Map types added to the map appear as buttons as in the normal
 * GMapTypeControl. However, map types set as sub-types of other map types (see
 * the addRelationship() method below) will appear as checkbox sub-menu items
 * below the parent button. By default, maps support the set of
 * {@link com.google.gwt.maps.client.MapType#getDefaultMapTypes()} though maps
 * may also add map types explicitly via
 * {@link com.google.gwt.maps.client.MapWidget#addMapType(com.google.gwt.maps.client.MapType)}
 * 
 * Controls will be made available for all map types currently attached to the
 * map at the time the control is constructed. Note that because sub-types
 * appear as checkboxes, you can toggle their appearance with their parents or
 * their siblings in the sub-menu.
 * 
 * 
 */
public interface HierarchicalMapTypeControlImpl extends JSFlyweightWrapper {
  HierarchicalMapTypeControlImpl impl = GWT.create(HierarchicalMapTypeControlImpl.class);
  
  void addRelationship(JavaScriptObject jsoPeer, MapType parentType, MapType childType);

  void addRelationship(JavaScriptObject jsoPeer, MapType parentType, MapType childType, String childText);
  
  void addRelationship(JavaScriptObject jsoPeer, MapType parentType, MapType childType, String childText, boolean isDefault);
  
  void clearRelationships(JavaScriptObject jsoPeer);

  @Constructor("$wnd.GHierarchicalMapTypeControl")
  JavaScriptObject construct();

  void removeRelationship(JavaScriptObject jsoPeer, MapType mapType);
  
}
