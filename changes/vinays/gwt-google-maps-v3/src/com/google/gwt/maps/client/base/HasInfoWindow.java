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
package com.google.gwt.maps.client.base;

import com.google.gwt.maps.client.HasJso;
import com.google.gwt.maps.client.HasMap;
import com.google.gwt.maps.client.mvc.HasMVCObject;
import com.google.gwt.maps.client.mvc.MVCObject;

/**
 * An overlay that looks like a bubble and is often connected to a marker. This
 * class extends {@link MVCObject}.
 * 
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public interface HasInfoWindow extends HasJso {
  
  public static enum Event {
    
    /**
     * This event is fired when the close button was clicked.
     */
    CLOSE_CLICK("closeclick"),
    
    /**
     * This event is fired when the content property changes.
     */
    CONTENT_CHANGED("content_changed"),
    
    /**
     * This event is fired when the <div> containing the InfoWindow's content is
     * attached to the DOM. You may wish to monitor this event if you are building
     * out your info window content dynamically.
     */
    DOM_READY("domready"),
    
    /**
     * This event is fired when the position property changes.
     */
    POISITION_CHANGED("position_changed"),
    
    /**
     * This event is fired when the InfoWindow's zIndex changes.
     */
    ZINDEX_CHANGED("zindex_changed");
    
    final private String value;
    
    /**
     * Instantiates a new event.
     *
     * @param value the value
     */
    private Event(String value) {
      this.value = value;
    }
    
    /**
     * Gets the value.
     *
     * @return the value
     */
    public String getValue() {
      return this.value;
    }
  }

  /**
   * Opens this InfoWindow on the given map. Optionally, an InfoWindow can be
   * associated with an anchor. In the core API, the only anchor is the Marker
   * class. However, an anchor can be any MVCObject that exposes the position
   * property and optionally pixelBounds for calculating the pixelOffset (see
   * InfoWindowOptions).
   */
  void open(HasMap map, HasMVCObject anchor);

  /**
   * Closes this InfoWindow by removing it from the DOM structure.
   */
  public void close();
  
  public String getContent();
  
  public LatLng getPosition();
  
  public int getZIndex();
  
  public void setContent(String html);
  
  public void setOptions(HasInfoWindowOptions options);
  
  public void setPosition(LatLng position);
  
  public void setZIndex(int zIndex);
  
}
