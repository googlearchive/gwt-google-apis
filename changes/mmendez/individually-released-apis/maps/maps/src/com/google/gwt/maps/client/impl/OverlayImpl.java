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
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.overlay.Overlay;
import com.google.gwt.maps.client.overlay.Overlay.ConcreteOverlay;
import com.google.gwt.maps.jsio.client.Binding;
import com.google.gwt.maps.jsio.client.Constructor;
import com.google.gwt.maps.jsio.client.JSFlyweightWrapper;

/**
 * Wrapper for the GOverlay and GGroundOverlay objects from the Maps API using
 * JSIO.
 */
public abstract class OverlayImpl implements JSFlyweightWrapper {

  public static final OverlayImpl impl = GWT.create(OverlayImpl.class);

  /**
   * This bind function is manual because using \@Binding causes an exception to
   * be thrown with a MultipleWrapper exception.
   */
  public native void bindConcreteOverlay(JavaScriptObject jsoPeer,
      ConcreteOverlay overlay) /*-{
    jsoPeer.__gwtPeer = overlay;
  }-*/;

  @Binding
  public abstract void bindOverlay(JavaScriptObject jsoPeer, Overlay overlay);

  @Constructor("$wnd.GOverlay")
  public abstract JavaScriptObject constructOverlay();

  public abstract Overlay copy(JavaScriptObject jsoPeer);

  public abstract void initialize(JavaScriptObject jsoPeer, MapWidget map);

  public abstract void redraw(JavaScriptObject jsoPeer, boolean force);

  public abstract void remove(JavaScriptObject jsoPeer);
}
