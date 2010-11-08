/* Copyright (c) 2010 Google Inc.
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
package com.google.gwt.maps.client;

import com.google.gwt.dom.client.Document;
import com.google.gwt.maps.client.base.HasLatLngBounds;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.event.Event;
import com.google.gwt.user.client.ui.Widget;

/**
 * A widget that presents a viewable Google Map to a user.
 * 
 * Note: Resize event will be trigger on load(i.e when it is attached to window's document).
 *
 */
public class MapWidget extends Widget {

  final private static String EXCEPTION_NOT_ATTACHED
      = "Map widget must be attached to window's document.";
  private HasMap map;
  
  public MapWidget(HasMapOptions options) {
    setElement(Document.get().createDivElement());
    map = new Map(getElement(), options);
  }
  
  public HasMap getMap() {
    return map;
  }

  /**
   * Sets the maps to fit to the given bounds.
   * 
   * Note: Call this *after* you add it to a LayoutPanel. It will throw an exception if not done so.
   */
  public void fitBounds(HasLatLngBounds bounds) {
    if (!this.isAttached()) {
      throw new IllegalStateException(EXCEPTION_NOT_ATTACHED);
    }
    map.fitBounds(bounds);
  }

  @Override
  protected void onLoad() {
    super.onLoad();
    // Resize the map and retain the center.
    LatLng center = map.getCenter();
    Event.trigger(map, "resize");
    map.setCenter(center);
  }
  
}
