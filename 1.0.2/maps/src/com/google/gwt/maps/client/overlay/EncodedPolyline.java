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
package com.google.gwt.maps.client.overlay;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Represents a polyline entity encoded as a string used to create a polygon.
 * See {@link Polygon#fromEncoded(EncodedPolyline[])} and <a href="http://code.google.com/apis/maps/documentation/overlays.html#Polylines_Overview"
 * >Polyline Encoding documentation</a> for more details.
 */
public class EncodedPolyline extends JavaScriptObject {

  /**
   * Create a new encoded polyline. See <a href="http://code.google.com/apis/maps/documentation/overlays.html#Polylines_Overview"
   * >Encoded Polylines Documentation</a> for more details.
   * 
   */
  public static EncodedPolyline newInstance() {
    return (EncodedPolyline) JavaScriptObject.createObject();
  }

  /**
   * Create a new encoded polyline. See <a href="http://code.google.com/apis/maps/documentation/overlays.html#Polylines_Overview"
   * >Encoded Polylines Documentation</a> for more details.
   * 
   * @param points a string containing the encoded latitude and longitude
   *          coordinates.
   * @param zoomFactor the magnification between adjacent sets of zoom levels in
   *          the encoded string passed in the levels argument.
   * @param levels a string containing the encoded polyline zoom level groups.
   * @param numLevels is the number of zoom levels contained in the string
   *          passed to {@link #setLevels(String)}.
   * @return a new encoded polyline object.
   */
  public static final EncodedPolyline newInstance(String points,
      int zoomFactor, String levels, int numLevels) {
    EncodedPolyline enc = (EncodedPolyline) JavaScriptObject.createObject();
    enc.setPoints(points);
    enc.setZoomFactor(zoomFactor);
    enc.setLevels(levels);
    enc.setNumLevels(numLevels);
    return enc;
  }

  /**
   * 
   * Create a new encoded polyline. See <a href="http://code.google.com/apis/maps/documentation/overlays.html#Polylines_Overview"
   * >Encoded Polylines Documentation</a> for more details.
   * 
   * @param points a string containing the encoded latitude and longitude
   *          coordinates.
   * @param zoomFactor the magnification between adjacent sets of zoom levels in
   *          the encoded string passed in the levels argument.
   * @param levels a string containing the encoded polyline zoom level groups.
   * @param numLevels a string containing the encoded polyline zoom level
   *          groups.
   * @param color a string that contains a hexadecimal numeric HTML style, i.e.
   *          #RRGGBB
   * @param weight the width of the line in pixels. opacity is a number between
   *          0 and 1.
   * @param opacity a number between 0 and 1.0 where 1.0 is totally opaque.
   * @return a new encoded polyline object.
   */
  public static final EncodedPolyline newInstance(String points,
      int zoomFactor, String levels, int numLevels, String color, int weight,
      double opacity) {
    EncodedPolyline enc = newInstance(points, zoomFactor, levels, numLevels);
    enc.setColor(color);
    enc.setWeight(weight);
    enc.setOpacity(opacity);
    return enc;
  }

  protected EncodedPolyline() {
    // Should this be empty? It won't compile w/o it.
  }

  /**
   * Sets the color of the {@link Polyline}.
   * 
   * @param colorIn a string that contains a hexadecimal numeric HTML style,
   *          i.e. #RRGGBB
   */
  public final native void setColor(String colorIn) /*-{
    this.color = colorIn;
  }-*/;

  /**
   * Sets the zoom level groups for this polyline. See <a href="http://code.google.com/apis/maps/documentation/overlays.html#Polylines_Overview"
   * >Encoded Polylines Documentation</a> for more details.
   * 
   * @param levelsIn a string containing the encoded polyline zoom level groups.
   */
  public final native void setLevels(String levelsIn) /*-{
    this.levels = levelsIn;
  }-*/;

  /**
   * Set the number of zoom levels in the string passed to
   * {@link #setLevels(String)}.
   * 
   * @param numLevelsIn is the number of zoom levels contained in the string
   *          passed to {@link #setLevels(String)}.
   */
  public final native void setNumLevels(int numLevelsIn) /*-{
    this.numLevels = numLevelsIn;
  }-*/;

  /**
   * Sets the opacity of the polyline.
   * 
   * @param opacityIn a number between 0 and 1.0 where 1.0 is totally opaque.
   * 
   */
  public final native void setOpacity(double opacityIn) /*-{
    this.opacity = opacityIn;
  }-*/;

  /**
   * Set the list of encoded latitude and longitude coordinates. See <a href="http://code.google.com/apis/maps/documentation/overlays.html#Polylines_Overview"
   * > Encoded Polylines Documentation</a> for more details.
   * 
   * @param pointsIn a string containing the encoded latitude and longitude
   *          coordinates.
   */
  public final native void setPoints(String pointsIn) /*-{
    this.points = pointsIn;
  }-*/;

  /**
   * Sets the weight of the polyline.
   * 
   * @param weightIn the width of the line in pixels. opacity is a number
   *          between 0 and 1.
   */
  public final native void setWeight(int weightIn) /*-{
    this.weight = weightIn;
  }-*/;

  /**
   * Sets the magnification between adjacent sets of zoom levels in the encoded
   * levels string.
   * 
   * @param zoomFactorIn the magnification between adjacent sets of zoom levels
   *          in the encoded string passed to {@link #setLevels(String)}.
   */
  public final native void setZoomFactor(int zoomFactorIn) /*-{
    this.zoomFactor = zoomFactorIn;
  }-*/;
}
