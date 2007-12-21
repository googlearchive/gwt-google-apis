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
package com.google.gwt.maps.client.impl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.jsio.client.JSFunction;
import com.google.gwt.jsio.client.JSWrapper;
import com.google.gwt.maps.client.MapType;
import com.google.gwt.maps.client.geom.Bounds;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Overlay;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @gwt.global $wnd.GEvent
 */
public abstract class EventImpl implements JSWrapper {

  /**
   * 
   */
  public abstract static class BooleanCallback extends JSFunction {
    public abstract void callback(boolean value);
  }

  /**
   * 
   */
  public abstract static class BoundsIntCallback extends JSFunction {
    public abstract void callback(Bounds bounds, int value);
  }

  /**
   * 
   */
  public abstract static class IntIntCallback extends JSFunction {
    public abstract void callback(int value1, int value2);
  }

  /**
   * 
   */
  public abstract static class LatLngCallback extends JSFunction {
    public abstract void callback(LatLng latlng);
  }

  /**
   * 
   */
  public abstract static class MapTypeCallback extends JSFunction {
    public abstract void callback(MapType mapType);
  }

  /**
   * 
   */
  public abstract static class OverlayCallback extends JSFunction {
    public abstract void callback(Overlay overlay);
  }

  /**
   * 
   */
  public abstract static class OverlayLatLngCallback extends JSFunction {
    public abstract void callback(Overlay overlay, LatLng latlng);
  }

  /**
   * 
   */
  public abstract static class VoidCallback extends JSFunction {
    public abstract void callback();
  }

  public static final EventImpl impl = (EventImpl) GWT.create(EventImpl.class);

  /**
   * Map of Java listener instances to JavaScript listener functions. This is
   * used in cases where a listener interface needs to sync several JavaScript
   * events in order to fulfill its contract.
   */
  private final Map<Object, Collection<JavaScriptObject>> javaListenerToJavaScriptListeners = new HashMap<Object, Collection<JavaScriptObject>>();

  public abstract JavaScriptObject addListener(JavaScriptObject source,
      String event, BooleanCallback handler);

  public abstract JavaScriptObject addListener(JavaScriptObject source,
      String event, BoundsIntCallback handler);

  public abstract JavaScriptObject addListener(JavaScriptObject source,
      String event, IntIntCallback handler);

  public abstract JavaScriptObject addListener(JavaScriptObject source,
      String event, LatLngCallback handler);

  public abstract JavaScriptObject addListener(JavaScriptObject source,
      String event, MapTypeCallback handler);

  public abstract JavaScriptObject addListener(JavaScriptObject source,
      String event, OverlayCallback handler);

  public abstract JavaScriptObject addListener(JavaScriptObject source,
      String event, OverlayLatLngCallback handler);

  /**
   * @gwt.fieldName addListener
   */
  public abstract JavaScriptObject addListenerVoid(JavaScriptObject source,
      String event, VoidCallback handler);

  public void associate(Object listener, JavaScriptObject jsListener) {
    Collection<JavaScriptObject> associated = javaListenerToJavaScriptListeners.get(listener);
    if (associated == null) {
      associated = new ArrayList<JavaScriptObject>();
      javaListenerToJavaScriptListeners.put(listener, associated);
    }
    associated.add(jsListener);
  }

  public void associate(Object listener, JavaScriptObject[] jsListeners) {
    for (JavaScriptObject jsListener : jsListeners) {
      associate(listener, jsListener);
    }
  }

  public abstract void clearListeners(JavaScriptObject source, String event);

  public void removeListeners(Object listener) {
    Collection<JavaScriptObject> jsListeners = javaListenerToJavaScriptListeners.get(listener);
    if (jsListeners != null) {
      for (JavaScriptObject jsListener : jsListeners) {
        removeListener(jsListener);
      }
    }
  }

  protected abstract void removeListener(JavaScriptObject jsListener);
}
