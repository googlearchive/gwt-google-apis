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
package com.google.gwt.maps.client.event;

import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.overlay.Marker;

/**
 * DragListener defines the interface for an object that listens to drag events
 * on a {@link MapWidget} or {@link Marker}.
 * 
 * @see MapWidget#addDragListener(DragListener)
 * @see Marker#addDragListener(DragListener)
 */
public interface DragListener {

  // TODO: how do we do the 'sender' for DragListeners since Map and Marker's
  // only common ancestor is Object? Separate listener interfaces for each?
  // Object sender?

  /**
   * Fired when the user begins dragging the map.
   */
  public void onDragStart();

  /**
   * Fired when the user drags the map.
   */
  public void onDrag();

  /**
   * Fired when the user finishes dragging the map.
   */
  public void onDragEnd();

}
