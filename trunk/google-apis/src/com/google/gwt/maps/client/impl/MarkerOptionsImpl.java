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
import com.google.gwt.jsio.client.BeanProperties;
import com.google.gwt.jsio.client.Constructor;
import com.google.gwt.maps.client.overlay.Icon;

/**
 * 
 */
@BeanProperties
public interface MarkerOptionsImpl extends JSFlyweightWrapper {

  public static final MarkerOptionsImpl impl = (MarkerOptionsImpl) GWT.create(MarkerOptionsImpl.class);

  @Constructor("Object")
  public JavaScriptObject construct();

  public void setBounceGravity(JavaScriptObject jsoPeer, double bounceGravity);

  public void setBouncy(JavaScriptObject jsoPeer, boolean bouncy);

  public void setClickable(JavaScriptObject jsoPeer, boolean clickable);

  public void setDragCrossMove(JavaScriptObject jsoPeer, boolean dragCrossMove);

  public void setDraggable(JavaScriptObject jsoPeer, boolean draggable);

  public void setIcon(JavaScriptObject jsoPeer, Icon icon);

  public void setTitle(JavaScriptObject jsoPeer, String title);

}
