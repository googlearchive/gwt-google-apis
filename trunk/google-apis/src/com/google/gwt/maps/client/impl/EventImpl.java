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

import com.google.gwt.jsio.client.FieldName;
import com.google.gwt.jsio.client.Global;
import com.google.gwt.jsio.client.JSFunction;
import com.google.gwt.jsio.client.JSWrapper;

import com.google.gwt.maps.client.MapType;
import com.google.gwt.maps.client.geom.Bounds;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.overlay.Overlay;
import com.google.gwt.user.client.Element;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * 
 */
@Global("$wnd.GEvent")
public abstract class EventImpl implements JSWrapper<EventImpl> {

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
  public abstract static class PointElementOverlayCallback extends JSFunction {
    public abstract void callback(Point point, Element element, Overlay overlay);
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

  public JavaScriptObject addListener(JavaScriptObject source, MapEvent event,
      BooleanCallback handler) {
    return addListener(source, event.value(), handler);
  }

  public JavaScriptObject addListener(JavaScriptObject source, MapEvent event,
      IntIntCallback handler) {
    return addListener(source, event.value(), handler);
  }

  public JavaScriptObject addListener(JavaScriptObject source, MapEvent event,
      LatLngCallback handler) {
    return addListener(source, event.value(), handler);
  }

  public JavaScriptObject addListener(JavaScriptObject source, MapEvent event,
      MapTypeCallback handler) {
    return addListener(source, event.value(), handler);
  }

  public JavaScriptObject addListener(JavaScriptObject source, MapEvent event,
      OverlayCallback handler) {
    return addListener(source, event.value(), handler);
  }

  public JavaScriptObject addListener(JavaScriptObject source, MapEvent event,
      OverlayLatLngCallback handler) {
    return addListener(source, event.value(), handler);
  }

  public JavaScriptObject addListener(JavaScriptObject source, MapEvent event,
      PointElementOverlayCallback handler) {
    return addListener(source, event.value(), handler);
  }

  public JavaScriptObject addListenerVoid(JavaScriptObject source,
      MapEvent event, VoidCallback handler) {
    return addListenerVoid(source, event.value(), handler);
  }

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

  public void clearListeners(JavaScriptObject source, MapEvent event) {
    clearListeners(source, event.value());
  }

  public void removeListeners(Object listener) {
    Collection<JavaScriptObject> jsListeners = javaListenerToJavaScriptListeners.get(listener);
    if (jsListeners != null) {
      for (JavaScriptObject jsListener : jsListeners) {
        removeListener(jsListener);
      }
    }
  }

  protected abstract JavaScriptObject addListener(JavaScriptObject source,
      String event, BooleanCallback handler);

  protected abstract JavaScriptObject addListener(JavaScriptObject source,
      String event, IntIntCallback handler);

  protected abstract JavaScriptObject addListener(JavaScriptObject source,
      String event, LatLngCallback handler);

  protected abstract JavaScriptObject addListener(JavaScriptObject source,
      String event, MapTypeCallback handler);

  protected abstract JavaScriptObject addListener(JavaScriptObject source,
      String event, OverlayCallback handler);

  protected abstract JavaScriptObject addListener(JavaScriptObject source,
      String event, OverlayLatLngCallback handler);

  protected abstract JavaScriptObject addListener(JavaScriptObject source,
      String event, PointElementOverlayCallback handler);

  @FieldName("addListener")
  protected abstract JavaScriptObject addListenerVoid(JavaScriptObject source,
      String event, VoidCallback handler);

  protected abstract void clearListeners(JavaScriptObject source, String event);

  protected abstract void removeListener(JavaScriptObject jsListener);
}
