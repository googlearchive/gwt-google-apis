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

  public static IconImpl impl = (IconImpl) GWT.create(IconImpl.class);

  /**
   * @gwt.binding
   */
  public void bind(JavaScriptObject jsoPeer, Icon icon);

  /**
   * @gwt.constructor $wnd.GIcon
   */
  public JavaScriptObject construct();

  /**
   * @gwt.constructor $wnd.GIcon
   */
  public JavaScriptObject construct(Icon icon);

  /**
   * @gwt.constructor $wnd.GIcon
   */
  public JavaScriptObject construct(Icon icon, String url);

  /**
   * @gwt.global $wnd.G_DEFAULT_ICON
   */
  public JavaScriptObject getDefaultIcon();

  public String getDragCrossImage(JavaScriptObject jsoPeer);

  public Point getDragCrossAnchor(JavaScriptObject jsoPeer);

  public Size getDragCrossSize(JavaScriptObject jsoPeer);

  public Point getIconAnchor(JavaScriptObject jsoPeer);

  public Size getIconSize(JavaScriptObject jsoPeer);

  public String getImage(JavaScriptObject jsoPeer);

  /**
   * @gwt.typeArgs <java.lang.Integer>
   */
  public JSList getImageMap(JavaScriptObject jsoPeer);

  public Point getInfoWindowAnchor(JavaScriptObject jsoPeer);

  public int getMaxHeight(JavaScriptObject jsoPeer);

  public String getMozPrintImage(JavaScriptObject jsoPeer);

  public String getPrintImage(JavaScriptObject jsoPeer);

  public String getShadow(JavaScriptObject jsoPeer);

  public Size getShadowSize(JavaScriptObject jsoPeer);

  public String getTransparent(JavaScriptObject jsoPeer);

  public void setDragCrossImage(JavaScriptObject jsoPeer, String url);

  public void setDragCrossAnchor(JavaScriptObject jsoPeer, Point anchor);

  public void setDragCrossSize(JavaScriptObject jsoPeer, Size size);

  public void setIconAnchor(JavaScriptObject jsoPeer, Point anchor);

  public void setIconSize(JavaScriptObject jsoPeer, Size size);

  public void setImage(JavaScriptObject jsoPeer, String url);

  /**
   * @gwt.typeArgs imageMap <java.lang.Integer>
   */
  public void setImageMap(JavaScriptObject jsoPeer, JSList/* int[] */imageMap);

  public void setInfoWindowAnchor(JavaScriptObject jsoPeer, Point anchor);

  public void setMaxHeight(JavaScriptObject jsoPeer, int height);

  // TODO: why is this different than getPrintImageUrl?
  public void setMozPrintImage(JavaScriptObject jsoPeer, String url);

  public void setPrintImage(JavaScriptObject jsoPeer, String url);

  public void setShadow(JavaScriptObject jsoPeer, String url);

  public void setShadowSize(JavaScriptObject jsoPeer, Size size);

  public void setTransparent(JavaScriptObject jsoPeer, String url);
}
