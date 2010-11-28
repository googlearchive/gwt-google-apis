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
package com.google.gwt.maps.client.overlay;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.Map;
import com.google.gwt.maps.client.base.LatLng;

/**
 * Creates marker on attached map.
 */
public class Marker extends JavaScriptObject {

  /**
   * Creates a marker with the no options specified.
   */
  public final static native Marker newInstance() /*-{
    return new $wnd.google.maps.Marker();
  }-*/;

  public final static Marker newInstance(HasMarkerOptions opts) {
    return newInstance(opts.getJso());
  }
  
  /**
   * Creates a marker with the options specified. If a map is specified, the
   * marker is added to the map upon construction. Note that the position must
   * be set for the marker to display.
   */
  private final static native Marker newInstance(JavaScriptObject opts) /*-{
    return new $wnd.google.maps.Marker(opts);
  }-*/;
  
  protected Marker() {}
  
  public final native void bindTo(String key, JavaScriptObject target) /*-{
    this.bindTo(key, target);
  }-*/;
  
  public final native void changed(String key) /*-{
    this.changed(key);
  }-*/;
  
  public final native JavaScriptObject get(String key) /*-{
    return this.get(key);
  }-*/;
  
  public final native boolean getAsBoolean(String key) /*-{
    return this.get(key);
  }-*/;
  
  public final native double getAsDouble(String key) /*-{
    return this.get(key);
  }-*/;
  
  public final native int getAsInt(String key) /*-{
    return this.get(key);
  }-*/;
  
  public final native String getAsString(String key) /*-{
    return this.get(key);
  }-*/;
  
  public final native String getCursor() /*-{
    return this.getCursor();
  }-*/;
  
  public final HasMarkerImage getIcon() {
    return new MarkerImage(getIconImpl());
  }
  
  private final native JavaScriptObject getIconImpl() /*-{
    return this.getIcon();
  }-*/;
  
  public final native Map getMap() /*-{
    return this.getMap();
  }-*/;
  
  public final native LatLng getPosition() /*-{
    return this.getPosition();
  }-*/;
  
  public final native String getTitle() /*-{
    return this.getTitle();
  }-*/;
  
  public final native int getZIndex() /*-{
    return this.getZIndex();
  }-*/;

  public final native boolean isClickable() /*-{
    return this.isClickable();
  }-*/;
  
  public final native boolean isDraggable() /*-{
    return this.isDraggable();
  }-*/;
  
  public final native boolean isFlat() /*-{
    return this.isFlat();
  }-*/;
  
  public final native boolean isVisible() /*-{
    return this.isVisible();
  }-*/;
  
  public final native void notify(String key) /*-{
    this.notify(key);
  }-*/;
  
  public final native void set(String key, JavaScriptObject value) /*-{
    this.set(key, value);
  }-*/;
  
  public final native void set(String key, String value) /*-{
    this.set(key, value);
  }-*/;
  
  public final native void set(String key, int value) /*-{
    this.set(key, value);
  }-*/;
  
  public final native void set(String key, double value) /*-{
    this.set(key, value);
  }-*/;
  
  public final native void set(String key, boolean value) /*-{
    this.set(key, value);
  }-*/;
  
  public final native void setClickable(boolean clickable) /*-{
    this.setClickable(clickable)
  }-*/;
  
  public final native void setCursor(String cursor) /*-{
    this.setCursor(cursor);
  }-*/;
  
  public final native void setDraggable(boolean draggable) /*-{
    this.setDraggable(draggable);
  }-*/;
  
  public final native void setFlat(boolean flat) /*-{
    this.setFlat(flat);
  }-*/;
  
  public final void setIcon(HasMarkerImage image) {
    setIcon(image.getJso());
  }
  
  private final native void setIcon(JavaScriptObject image) /*-{
    this.setIcon(image);
  }-*/;

  public final native void setMap(Map map) /*-{
    this.setMap(map);
  }-*/;
  
  public final native void setPosition(LatLng position) /*-{
    this.setPosition(position);
  }-*/;
  
  public final native void setTitle(String title) /*-{
    this.setTitle(title);
  }-*/;
  
  public final native void setValues(JavaScriptObject values) /*-{
    this.setValues(values);
  }-*/;
  
  public final native void setVisible(boolean visible) /*-{
    this.setVisible(visible);
  }-*/;
  
  public final native void setZIndex(int zIndex) /*-{
    this.setZIndex(zIndex);
  }-*/;
  
  public final native void unbind(String key) /*-{
    this.unbind(key);
  }-*/;
  
  public final native void unbindAll() /*-{
    this.unbindAll();
  }-*/;

}
