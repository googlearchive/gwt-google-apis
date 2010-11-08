/* Copyright (c) 2010 Vinay Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.gwt.maps.client.overlay;

import com.google.gwt.maps.client.HasMap;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.mvc.HasMVCObject;

/**
 * 
 * This class extends MVCObject.
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public interface HasMarker extends HasMVCObject {
  
  public static enum Event {
    
    CLICK("click"),
    DRAG("drag"),
    DRAG_START("dragstart"),
    DRAG_ENG("dragend"),
    ZINDEX_CHANGED("zindex_changed");
    
    final private String value;
    
    private Event(String value) {
      this.value = value;
    }
    
    public String getValue() {
      return this.value;
    }
  }

  boolean isClickable();

  void setClickable(boolean clickable);

  String getCursor();

  void setCursor(String cursor);

  boolean isDraggable();

  void setDraggable(boolean draggable);

  boolean isFlat();

  void setFlat(boolean flat);
  
  HasMarkerImage getIcon();
  
  void setIcon(HasMarkerImage image);
  
  HasMap getMap();

  /**
   * Renders the marker on the specified map. If map is set to null, the marker
   * will be removed.
   */
  void setMap(HasMap map);

  LatLng getPosition();

  void setPosition(LatLng position);

  String getTitle();

  void setTitle(String title);

  boolean isVisible();

  void setVisible(boolean visible);

  int getZIndex();

  void setZIndex(int zIndex);

}
