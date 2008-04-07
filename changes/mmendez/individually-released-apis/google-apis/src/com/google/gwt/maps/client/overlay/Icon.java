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
import com.google.gwt.jsio.client.JSList;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.geom.Size;
import com.google.gwt.maps.client.impl.IconImpl;
import com.google.gwt.maps.client.impl.JsUtil;

/**
 * Wrapper for the Maps API GIcon class
 */
public final class Icon {

  public static final Icon DEFAULT_ICON = createPeer(IconImpl.impl.getDefaultIcon());

  static Icon createPeer(JavaScriptObject jsoPeer) {
    Icon icon = new Icon(jsoPeer);
    IconImpl.impl.bind(jsoPeer, icon);
    return icon;
  }

  private final JavaScriptObject jsoPeer;

  public Icon() {
    jsoPeer = IconImpl.impl.construct();
  }

  public Icon(Icon icon) {
    jsoPeer = IconImpl.impl.construct(icon);
  }

  public Icon(String imageUrl) {
    jsoPeer = IconImpl.impl.construct(null, imageUrl);
  }

  private Icon(JavaScriptObject jsoPeer) {
    this.jsoPeer = jsoPeer;
  }

  public String getDragCossImageUrl() {
    return IconImpl.impl.getDragCrossImage(jsoPeer);
  }

  public Point getDragCrossAnchor() {
    return IconImpl.impl.getDragCrossAnchor(jsoPeer);
  }

  public Size getDragCrossSize() {
    return IconImpl.impl.getDragCrossSize(jsoPeer);
  }

  public Point getIconAnchor() {
    return IconImpl.impl.getIconAnchor(jsoPeer);
  }

  public Size getIconSize() {
    return IconImpl.impl.getIconSize(jsoPeer);
  }

  public int[] getImageMap() {
    JSList<Integer> imageMap = IconImpl.impl.getImageMap(jsoPeer);
    int[] returnValue = new int[imageMap.size()];
    JsUtil.toArray(imageMap, returnValue);
    return returnValue;
  }

  public String getImageURL() {
    return IconImpl.impl.getImage(jsoPeer);
  }

  public Point getInfoWindowAnchor() {
    return IconImpl.impl.getInfoWindowAnchor(jsoPeer);
  }

  public int getMaxHeight() {
    return IconImpl.impl.getMaxHeight(jsoPeer);
  }

  // This different than getPrintImageUrl because of browser differences
  // in Mozilla/Firefox. (problems with transparent images on IE?)
  public String getMozPrintImageURL() {
    return IconImpl.impl.getMozPrintImage(jsoPeer);
  }

  public String getPrintImageURL() {
    return IconImpl.impl.getPrintImage(jsoPeer);
  }

  public Size getShadowSize() {
    return IconImpl.impl.getShadowSize(jsoPeer);
  }

  public String getShadowURL() {
    return IconImpl.impl.getShadow(jsoPeer);
  }

  public String getTransparentImageUrl() {
    return IconImpl.impl.getTransparent(jsoPeer);
  }

  public void setDragCrossAnchor(Point anchor) {
    IconImpl.impl.setDragCrossAnchor(jsoPeer, anchor);
  }

  public void setDragCrossImageURL(String url) {
    IconImpl.impl.setDragCrossImage(jsoPeer, url);
  }

  public void setDragCrossSize(Size size) {
    IconImpl.impl.setDragCrossSize(jsoPeer, size);
  }

  public void setIconAnchor(Point anchor) {
    IconImpl.impl.setIconAnchor(jsoPeer, anchor);
  }

  public void setIconSize(Size size) {
    IconImpl.impl.setIconSize(jsoPeer, size);
  }

  public void setImageMap(int[] imageMap) {
    IconImpl.impl.setImageMap(jsoPeer, JsUtil.toJsList(imageMap));
  }

  public void setImageURL(String url) {
    IconImpl.impl.setImage(jsoPeer, url);
  }

  public void setInfoWindowAnchor(Point anchor) {
    IconImpl.impl.setInfoWindowAnchor(jsoPeer, anchor);
  }

  public void setMaxHeight(int height) {
    IconImpl.impl.setMaxHeight(jsoPeer, height);
  }

  // This different than getPrintImageUrl because of browser differences
  // in Mozilla/Firefox (problems with transparent images on IE?)
  public void setMozPrintImageURL(String url) {
    IconImpl.impl.setMozPrintImage(jsoPeer, url);
  }

  public void setPrintImageURL(String url) {
    IconImpl.impl.setPrintImage(jsoPeer, url);
  }

  public void setShadowSize(Size size) {
    IconImpl.impl.setShadowSize(jsoPeer, size);
  }

  public void setShadowURL(String url) {
    IconImpl.impl.setShadow(jsoPeer, url);
  }

  public void setTransparentImageUrl(String url) {
    IconImpl.impl.setTransparent(jsoPeer, url);
  }
}
