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
import com.google.gwt.dom.client.Node;

/**
 * The class to specify InfoWindow specifications as options.
 */
public class InfoWindowOptions extends JavaScriptObject {

  public static final InfoWindowOptions newInstance() {
    return (InfoWindowOptions) JavaScriptObject.createObject();
  };
  
  protected InfoWindowOptions() {}
  
  /**
   * Make sure content is set as string(or html) using {@link #setContent(String)}.
   * If not correctly used it will cause unpredictable behavior in production mode.
   */
  public native final String getContentString() /*-{
    return this.content;
  }-*/;
  
  /**
   * Make sure content is set as Node using {@link #setContent(Node)}.
   * If not correctly used it will cause unpredictable behavior in production mode.
   */
  public native final Node getContentNode() /*-{
    return this.content;
  }-*/;

  public native final int getMaxWidth() /*-{
    return this.maxWidth;
  }-*/;

  // TODO: replace javascriptobject with concrete type {@link Size}.
  public native final JavaScriptObject getPixelOffset() /*-{
    return this.pixelOffset;
  }-*/;

  public native final LatLng getPosition() /*-{
    return this.position;
  }-*/;

  public native final int getZIndex() /*-{
    return this.zIndex;
  }-*/;

  public native final boolean isDisableAutoPan() /*-{
    return disableAutoPan;
  }-*/;

  public native final void setContent(String html) /*-{
    this.content = html;
  }-*/;

  public native final void setContent(Node node) /*-{
    this.content = node;
  }-*/;

  public native final void setDisableAutoPan(boolean disableAutoPan) /*-{
    this.disableAutoPan = disableAutoPan;
  }-*/;

  public native final void setMaxWidth(int maxWidth) /*-{
    this.maxWidth = maxWidth;
  }-*/;

  //TODO: replace javascriptobject with concrete type {@link Size}.
  public native final void setPixelOffset(JavaScriptObject size) /*-{
    this.pixelOffset = pixelOffset;
  }-*/;

  public native final void setPosition(LatLng position) /*-{
    this.position = position;
  }-*/;

  public native final void setZIndex(int zIndex) /*-{
    this.zIndex = zIndex;
  }-*/;

}
