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
package com.google.gwt.maps.client.impl;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.jsio.client.JSFlyweightWrapper;
import com.google.gwt.maps.client.CopyrightCollection;
import com.google.gwt.maps.client.TileLayer;
import com.google.gwt.maps.client.geom.Point;

/**
 * 
 */
public interface TileLayerImpl extends JSFlyweightWrapper {

  /**
   * @gwt.binding
   */
  public void bind(JavaScriptObject jsoPeer, TileLayer tileLayer);

  /**
   * @gwt.constructor $wnd.GTileLayer
   */
  public JavaScriptObject construct();

  /**
   * @gwt.constructor $wnd.GTileLayer
   */
  public JavaScriptObject construct(CopyrightCollection copyrights,
      int minResolution, int maxResolution);

  public int getMaxResolution(TileLayer jsoPeer);

  public int getMinResolution(TileLayer jsoPeer);

  public double getOpacity(TileLayer jsoPeer);

  public String getTileUrl(TileLayer jsoPeer, Point tile, int zoomLevel);

  public boolean isPng(TileLayer jsoPeer);

}
