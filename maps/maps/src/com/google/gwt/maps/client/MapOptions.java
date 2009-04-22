/*
 * Copyright 2009 Google Inc.
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
package com.google.gwt.maps.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.maps.client.geom.Size;

import java.util.List;

/**
 * Wrapper for the GMapOptions class, passed as an argument to construct
 * {@link MapWidget}.
 */
public class MapOptions extends JavaScriptObject {

  /**
   * Returns a new MapOptions instance.
   * 
   * @return a new MapOptions instance.
   */
  public static final native MapOptions newInstance() /*-{
    // A complex constructor works around inlining bug. See GWT issue 3568
    // http://code.google.com/p/google-web-toolkit/issues/detail?id=3568
    var obj;
    obj = new $wnd.Object();
    return obj;
  }-*/;

  protected MapOptions() {
    // Protected constructor required for JSO Overlays
  }

  /**
   * Specifies the color to display behind the map tiles. The color can be any
   * valid W3C standard color value.
   * 
   * @param backgroundColor CSS color value to display behind map tiles.
   */
  public final native MapOptions setBackgroundColor(String backgroundColor) /*-{
    this.backgroundColor = backgroundColor;
    return this;
  }-*/;

  /**
   * The cursor to display when the map is draggable.
   * 
   * @param draggableCursor CSS name of the cursor to display when the map is
   *          draggable
   */
  public final native MapOptions setDraggableCursor(String draggableCursor) /*-{
    this.draggableCursor = draggableCursor;
    return this;
  }-*/;

  /**
   * The cursor to display while dragging the map.
   * 
   * @param draggingCursor CSS name of the cursor to display when the map is
   *          draging
   */
  public final native MapOptions setDraggingCursor(String draggingCursor) /*-{
    this.draggingCursor = draggingCursor;
    return this;
  }-*/;

  /**
   * Specifies the options to configure the GoogleBar search control. These
   * options are passed to the GMapOptions object literal when the map is
   * constructed, and are used to construct the GoogleBar control when
   * GMap2.enableGoogleBar() is called.
   * 
   * @param googleBarOptions options to configure the GoogleBar search control.
   */
  public final native MapOptions setGoogleBarOptions(
      GoogleBarOptions googleBarOptions) /*-{
    this.googleBarOptions = googleBarOptions;
    return this;
  }-*/;

  /**
   * List of map types to be used by this map. By default,
   * {@link MapType#getDefaultMapTypes()} is used. You can use this option to
   * restrict the set of predefined map types that is displayed on the map, or
   * to pass your own map types to the map. See also
   * {@link MapWidget#addMapType(MapType)}
   * 
   * @param mapTypes List of map types to be used by this map.
   */
  public final MapOptions setMapTypes(List<MapType> mapTypes) {
    JsArray<JavaScriptObject> nativeArray = JsArray.createArray().cast();

    int index = 0;
    for (MapType mapType : mapTypes) {
      nativeArray.set(index, mapType.getPeer());
      index++;
    }
    nativeSetMapTypes(nativeArray);
    return this;
  }

  /**
   * Sets the size in pixels of the map. The container that is passed to the map
   * constructor will be resized to the given size. By default, the map will
   * assume the size of its container.
   * 
   * @param size the size in pixels of the map
   */
  public final native MapOptions setSize(Size size) /*-{
    this.size = size;
    return this;
  }-*/;

  private native MapOptions nativeSetMapTypes(//
      JsArray<JavaScriptObject> mapTypes) /*-{
    this.mapTypes = mapTypes;
    return this;
  }-*/;
}
