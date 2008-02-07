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
package com.google.gwt.maps.client.impl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.jsio.client.JSFlyweightWrapper;
import com.google.gwt.jsio.client.JSList;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.geom.Size;
import com.google.gwt.maps.client.overlay.Icon;

/**
 * @gwt.beanProperties
 */
public interface IconImpl extends JSFlyweightWrapper {

  IconImpl impl = (IconImpl) GWT.create(IconImpl.class);

  /**
   * @gwt.binding
   */
  void bind(JavaScriptObject jsoPeer, Icon icon);

  /**
   * @gwt.constructor $wnd.GIcon
   */
  JavaScriptObject construct();

  /**
   * @gwt.constructor $wnd.GIcon
   */
  JavaScriptObject construct(Icon icon);

  /**
   * @gwt.constructor $wnd.GIcon
   */
  JavaScriptObject construct(Icon icon, String url);

  /**
   * @gwt.global $wnd.G_DEFAULT_ICON
   */
  JavaScriptObject getDefaultIcon();

  Point getDragCrossAnchor(JavaScriptObject jsoPeer);

  String getDragCrossImage(JavaScriptObject jsoPeer);

  Size getDragCrossSize(JavaScriptObject jsoPeer);

  Point getIconAnchor(JavaScriptObject jsoPeer);

  Size getIconSize(JavaScriptObject jsoPeer);

  String getImage(JavaScriptObject jsoPeer);

  /**
   * @gwt.typeArgs <java.lang.Integer>
   */
  JSList getImageMap(JavaScriptObject jsoPeer);

  Point getInfoWindowAnchor(JavaScriptObject jsoPeer);

  int getMaxHeight(JavaScriptObject jsoPeer);

  String getMozPrintImage(JavaScriptObject jsoPeer);

  String getPrintImage(JavaScriptObject jsoPeer);

  String getShadow(JavaScriptObject jsoPeer);

  Size getShadowSize(JavaScriptObject jsoPeer);

  String getTransparent(JavaScriptObject jsoPeer);

  void setDragCrossAnchor(JavaScriptObject jsoPeer, Point anchor);

  void setDragCrossImage(JavaScriptObject jsoPeer, String url);

  void setDragCrossSize(JavaScriptObject jsoPeer, Size size);

  void setIconAnchor(JavaScriptObject jsoPeer, Point anchor);

  void setIconSize(JavaScriptObject jsoPeer, Size size);

  void setImage(JavaScriptObject jsoPeer, String url);

  /**
   * @gwt.typeArgs imageMap <java.lang.Integer>
   */
  void setImageMap(JavaScriptObject jsoPeer, JSList/* int[] */imageMap);

  void setInfoWindowAnchor(JavaScriptObject jsoPeer, Point anchor);

  void setMaxHeight(JavaScriptObject jsoPeer, int height);

  // TODO: why is this different than getPrintImageUrl?
  void setMozPrintImage(JavaScriptObject jsoPeer, String url);

  void setPrintImage(JavaScriptObject jsoPeer, String url);

  void setShadow(JavaScriptObject jsoPeer, String url);

  void setShadowSize(JavaScriptObject jsoPeer, Size size);

  void setTransparent(JavaScriptObject jsoPeer, String url);
}
