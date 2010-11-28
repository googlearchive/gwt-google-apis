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

import com.google.gwt.core.client.JavaScriptObject;

/**
 * An overlay that looks like a bubble and is often connected to a marker.
 */
public class InfoWindow extends JavaScriptObject {
  
  public static final native InfoWindow newInstance() /*-{
    return new $wnd.google.maps.InfoWindow();
  }-*/;
  
  public static final native InfoWindow newInstance(InfoWindowOptions opts) /*-{
    return new $wnd.google.maps.InfoWindow(opts);
  }-*/;
  
  protected InfoWindow() {}
  
  // TODO: replace JSO with concrete type {@link Map}, {@link MVCObject}.
  public final native void open(JavaScriptObject map, JavaScriptObject anchor) /*-{
    this.open(map, anchor);
  }-*/;

  public final native void close() /*-{
    this.close();
  }-*/;
  
  public final native String getContent() /*-{
    return this.getContent();
  }-*/;
  
  public final native LatLng getPosition() /*-{
    return this.getPosition();
  }-*/;
  
  public final native int getZIndex() /*-{
    return this.getZIndex();
  }-*/;
  
  public final native void setContent(String html) /*-{
    this.setContent(html);
  }-*/;
  
  public final native void setOptions(InfoWindowOptions options) /*-{
    this.setOptions(options);
  }-*/;
  
  public final native void setPosition(LatLng position) /*-{
    this.setPosition(position);
  }-*/;
  
  public final native void setZIndex(int zIndex) /*-{
    this.setZIndex(zIndex);
  }-*/;

}
