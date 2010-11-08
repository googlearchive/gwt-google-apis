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
package com.google.gwt.maps.client.base;

import com.google.gwt.maps.client.HasJso;

/**
 * The Interface HasInfoWindowOptions.
 * 
 */
public interface HasInfoWindowOptions extends HasJso {

  public String getContent();

  /**
   * Content to display in the InfoWindow. This can be a
   * plain-text string, or a string containing HTML. The InfoWindow will be
   * sized according to the content.
   */
  public void setContent(String html);
  
  public boolean isDisableAutoPan();

  /**
   * Disable auto-pan on open. By default, the info window will pan the map so
   * that it is fully visible when it opens.
   */
  public void setDisableAutoPan(boolean disableAutoPan);
  
  public int getMaxWidth();
  
  /**
   * Maximum width of the infowindow, regardless of content's width. This value
   * is only considered if it is set before a call to open. To change the
   * maximum width when changing content, call close, setOptions, and then open.
   */
  public void setMaxWidth(int maxWidth);
  
  public HasSize getPixelOffset();
  
  /**
   * The offset, in pixels, of the tip of the info window from the point on the
   * map at whose geographical coordinates the info window is anchored. If an
   * InfoWindow is opened with an anchor, the pixelOffset will be calculated from
   * the top-center of the anchor's bounds.
   */
  public void setPixelOffset(HasSize size);
  
  public LatLng getPosition();
  
  /**
   * The LatLng at which to display this InfoWindow. If the InfoWindow is opened
   * with an anchor, the anchor's position will be used instead.
   */
  public void setPosition(LatLng position);
  
  public int getZIndex();
  
  /**
   * All InfoWindows are displayed on the map in order of their zIndex, with
   * higher values displaying in front of InfoWindows with lower values. By
   * default, InfoWinodws are displayed according to their latitude, with
   * InfoWindows of lower latitudes appearing in front of InfoWindows at higher
   * latitudes. InfoWindows are always displayed in front of markers.
   */
  public void setZIndex(int zIndex);
  
}
