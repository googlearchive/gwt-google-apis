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

/**
 * These strings represent the event names used in the Google Maps API.
 */
public enum MapEvent {

  ADDMAPTYPE("addmaptype"),
  ADDOVERLAY("addoverlay"),
  CHANGED("changed"),
  CANCELLINE("cancelline"),
  CLEARLISTENERS("clearlisteners"),
  CLEAROVERLAYS("clearoverlays"),
  CLICK("click"),
  CLOSECLICK("closeclick"),
  DBLCLICK("dblclick"),
  DRAG("drag"),
  DRAGEND("dragend"),
  DRAGSTART("dragstart"),
  ENDLINE("endline"),
  ERROR("error"),
  INFOWINDOWBEFORECLOSE("infowindowbeforeclose"),
  INFOWINDOWCLOSE("infowindowclose"),
  INFOWINDOWOPEN("infowindowopen"),
  LINEUPDATED("lineupdated"),
  LOAD("load"),
  MAPTYPECHANGED("maptypechanged"),
  MAXIMIZECLICK("maximizeclick"),
  MAXIMIZEEND("maximizeend"),
  MOUSEDOWN("mousedown"),
  MOUSEMOVE("mousemove"),
  MOUSEOUT("mouseout"),
  MOUSEOVER("mouseover"),
  MOUSEUP("mouseup"),
  MOVE("move"),
  MOVEEND("moveend"),
  MOVESTART("movestart"),
  NEWCOPYRIGHT("newcopyright"),
  NONE("none"),
  REMOVE("remove"),
  REMOVEMAPTYPE("removemaptype"),
  REMOVEOVERLAY("removeoverlay"),
  RESTORECLICK("restoreclick"),
  RESTOREEND("restoreend"),
  SINGLERIGHTCLICK("singlerightclick"),
  VISIBILITYCHANGED("visibilitychanged"),
  ZOOMEND("zoomend"),
  // Streetview events:
  INITIALIZED("initialized"),
  PITCHCHANGED("pitchchanged"),
  YAWCHANGED("yawchanged"),
  ZOOMCHANGED("zoomchanged");

  public String value;

  MapEvent(String value) {
    this.value = value;
  }

  public String value() {
    return value;
  }
}
