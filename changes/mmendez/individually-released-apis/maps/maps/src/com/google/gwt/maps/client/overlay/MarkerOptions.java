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
package com.google.gwt.maps.client.overlay;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.impl.MarkerOptionsImpl;

/**
 * 
 */
public final class MarkerOptions {

  private final JavaScriptObject jsoPeer;

  public MarkerOptions() {
    jsoPeer = MarkerOptionsImpl.impl.construct();
  }

  public MarkerOptions(Icon icon) {
    this();
    setIcon(icon);
  }

  public void setBounceGravity(double bounceGravity) {
    MarkerOptionsImpl.impl.setBounceGravity(jsoPeer, bounceGravity);
  }

  public void setBouncy(boolean bouncy) {
    MarkerOptionsImpl.impl.setBouncy(jsoPeer, bouncy);
  }

  public void setClickable(boolean clickable) {
    MarkerOptionsImpl.impl.setClickable(jsoPeer, clickable);
  }

  public void setDragCrossMove(boolean dragCrossMove) {
    MarkerOptionsImpl.impl.setDragCrossMove(jsoPeer, dragCrossMove);
  }

  public void setDraggable(boolean draggable) {
    MarkerOptionsImpl.impl.setDraggable(jsoPeer, draggable);
  }

  public void setIcon(Icon icon) {
    MarkerOptionsImpl.impl.setIcon(jsoPeer, icon);
  }

  public void setTitle(String title) {
    MarkerOptionsImpl.impl.setTitle(jsoPeer, title);
  }
}
