/* Copyright (c) 2010 Vinay Inc.
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
package com.google.gwt.maps.client.overlay.impl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.jsio.client.BeanProperties;
import com.google.gwt.jsio.client.Constructor;
import com.google.gwt.jsio.client.JSFlyweightWrapper;

/**
 * 
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
@BeanProperties
public interface MarkerOptionsImpl extends JSFlyweightWrapper {

  MarkerOptionsImpl impl = GWT.create(MarkerOptionsImpl.class);
  
  @Constructor("Object")
  JavaScriptObject construct();
  
  @BeanProperties
  boolean isClickable(JavaScriptObject jso);

  @BeanProperties
  void setClickable(JavaScriptObject jso, boolean clickable);

  @BeanProperties
  String getCursor(JavaScriptObject jso);

  @BeanProperties
  void setCursor(JavaScriptObject jso, String cursor);

  @BeanProperties
  boolean isDraggable(JavaScriptObject jso);

  @BeanProperties
  void setDraggable(JavaScriptObject jso, boolean draggable);

  @BeanProperties
  boolean isFlat(JavaScriptObject jso);

  @BeanProperties
  void setFlat(JavaScriptObject jso, boolean flat);
  
  @BeanProperties
  JavaScriptObject getIcon(JavaScriptObject jso);
  
  @BeanProperties
  void setIcon(JavaScriptObject jso, JavaScriptObject image);

  @BeanProperties
  JavaScriptObject getMap(JavaScriptObject jso);

  @BeanProperties
  void setMap(JavaScriptObject jso, JavaScriptObject map);

  @BeanProperties
  JavaScriptObject getPosition(JavaScriptObject jso);

  @BeanProperties
  void setPosition(JavaScriptObject jso, JavaScriptObject position);

  @BeanProperties
  String getTitle(JavaScriptObject jso);

  @BeanProperties
  void setTitle(JavaScriptObject jso, String title);

  @BeanProperties
  boolean isVisible(JavaScriptObject jso);

  @BeanProperties
  void setVisible(JavaScriptObject jso, boolean visible);

  @BeanProperties
  int getZIndex(JavaScriptObject jso);

  @BeanProperties
  void setZIndex(JavaScriptObject jso, int zIndex);

}
