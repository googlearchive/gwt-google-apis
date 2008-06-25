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
 * See {@link Polygon#fromEncoded(EncodedPolyline[])}
 */
public final class EncodedPolyline extends JavaScriptObject {

  public static EncodedPolyline createEncodedPolyline(String points,
      int zoomFactor, String levels, int numLevels) {
    EncodedPolyline enc = (EncodedPolyline) JavaScriptObject.createObject();
    enc.setPoints(points);
    enc.setZoomFactor(zoomFactor);
    enc.setLevels(levels);
    enc.setNumLevels(numLevels);
    return enc;
  }
  
  public static EncodedPolyline createEncodedPolyline(String points,
      int zoomFactor, String levels, int numLevels, String color, int weight,
      double opacity) {
    EncodedPolyline enc = createEncodedPolyline(points, zoomFactor, levels,
        numLevels);
    enc.setColor(color);
    enc.setWeight(weight);
    enc.setOpacity(opacity);
    return enc;
  }

  protected EncodedPolyline() {
    // Should this be empty? It won't compile w/o it.
  }

  public native void setColor(String colorIn) /*-{
    this.color = colorIn;
  }-*/;

  public native void setLevels(String levelsIn) /*-{
    this.levels = levelsIn;
  }-*/;

  public native void setNumLevels(int numLevelsIn) /*-{
    this.numLevels = numLevelsIn;
  }-*/;

  public native void setOpacity(double opacityIn) /*-{
    this.opacity = opacityIn;
  }-*/;

  public native void setPoints(String pointsIn) /*-{
    this.points = pointsIn;
  }-*/;

  public native void setWeight(int weightIn) /*-{
    this.weight = weightIn;
  }-*/;

  public native void setZoomFactor(int zoomFactorIn) /*-{
    this.zoomFactor = zoomFactorIn;
  }-*/;

}
