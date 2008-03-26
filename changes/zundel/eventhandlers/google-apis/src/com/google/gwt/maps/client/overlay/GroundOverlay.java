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
package com.google.gwt.maps.client.overlay;

import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.maps.client.impl.OverlayImpl;
import com.google.gwt.maps.client.overlay.Overlay.ConcreteOverlay;

/**
 * A rectangular image overlay whose boundaries are defined by a
 * {@link LatLngBounds}.
 * 
 * Example:
 * 
 * <pre>
 *  MapWidget map; = ...;
 *  Overlay overlay = new GroundOverlay(<i>url</i>, <i>bounds</i>);
 *  map.addOverlay(overlay);
 * </pre>
 * 
 * TODO: more complete example
 * 
 * @see com.google.gwt.maps.client.MapWidget#addOverlay(Overlay)
 */
public final class GroundOverlay extends ConcreteOverlay {

  /**
   * Creates a new ground overlay from the given image with the specified size.
   * 
   * @param imageUrl the URL of the overlay image
   * @param bounds the rectangle defining the bounds of the overlay
   */
  public GroundOverlay(String imageUrl, LatLngBounds bounds) {
    super(OverlayImpl.impl.constructGroundOverlay(imageUrl, bounds));
  }

}
