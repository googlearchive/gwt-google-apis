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

import com.google.gwt.maps.client.HasJso;

/**
 * Defines an image to be used as the icon or shadow for a Marker. 'origin' and
 * 'size' are used to select a segment of a sprite image and 'anchor' overrides
 * the position of the anchor point from its default bottom middle position. The
 * anchor of an image is the pixel to which the system refers in tracking the
 * image's position. By default, the anchor is set to the bottom middle of the
 * image (coordinates width/2, height). So when a marker is placed at a given
 * LatLng, the pixel defined as the anchor is positioned at the specified LatLng.
 * The MarkerImage cannot be changed once constructed.
 * 
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public interface HasMarkerImage extends HasJso {

}
