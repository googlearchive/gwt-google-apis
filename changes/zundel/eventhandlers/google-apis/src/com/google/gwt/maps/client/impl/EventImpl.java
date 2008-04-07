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
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.jsio.client.Exported;
import com.google.gwt.jsio.client.Global;
import com.google.gwt.jsio.client.JSFunction;
import com.google.gwt.jsio.client.JSWrapper;
import com.google.gwt.maps.client.Copyright;
import com.google.gwt.maps.client.MapType;
import com.google.gwt.maps.client.geom.Bounds;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.overlay.Overlay;
import com.google.gwt.user.client.Element;

/**
 * 
 */
@Global("$wnd.GEvent")
public abstract class EventImpl implements JSWrapper<EventImpl> {

  /**
   * Provides a way to specify a JavaScript function() with a single boolean
   * argument and handles uncaught exceptions.
   */
  public abstract static class BooleanCallback extends JSFunction {

    public abstract void callback(boolean value);

    @Exported
    public void callbackWrapper(boolean value) {
      UncaughtExceptionHandler handler = GWT.getUncaughtExceptionHandler();
      try {
        callback(value);
      } catch (Throwable throwable) {
        if (handler != null) {
          handler.onUncaughtException(throwable);
        }
      }
    }
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
  public abstract static class CopyrightCallback extends JSFunction {
    public abstract void callback(Copyright value);
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
   * Provides a way to specify a JavaScript function() with no
   * arguments and handles uncaught exceptions.
   */
  public abstract static class VoidCallback extends JSFunction {
   
    public abstract void callback();
    
    @Exported
    public void callbackWrapper() {
      UncaughtExceptionHandler handler = GWT.getUncaughtExceptionHandler();
      try {
        callback();
      } catch (Throwable throwable) {
        if (handler != null) {
          handler.onUncaughtException(throwable);
        }
      }
    }
  }

  public static final EventImpl impl = GWT.create(EventImpl.class);

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
    return addListener(source, event.value(), handler);
  }

  public abstract void removeListener(JavaScriptObject mapEventHandle);

  abstract JavaScriptObject addListener(JavaScriptObject source, String event,
      BooleanCallback handler);

  abstract JavaScriptObject addListener(JavaScriptObject source, String event,
      CopyrightCallback handler);

  abstract JavaScriptObject addListener(JavaScriptObject source, String event,
      IntIntCallback handler);

  abstract JavaScriptObject addListener(JavaScriptObject source, String event,
      LatLngCallback handler);

  abstract JavaScriptObject addListener(JavaScriptObject source, String event,
      MapTypeCallback handler);

  abstract JavaScriptObject addListener(JavaScriptObject source, String event,
      OverlayCallback handler);

  abstract JavaScriptObject addListener(JavaScriptObject source, String event,
      OverlayLatLngCallback handler);

  abstract JavaScriptObject addListener(JavaScriptObject source, String event,
      PointElementOverlayCallback handler);

  abstract JavaScriptObject addListener(JavaScriptObject source, String event,
      VoidCallback handler);

  abstract void trigger(JavaScriptObject source, String mapEventString);

  abstract void trigger(JavaScriptObject source, String mapEventString,
      boolean value);

  abstract void trigger(JavaScriptObject source, String mapEventString,
      Copyright value);

  abstract void trigger(JavaScriptObject source, String mapEventString,
      int arg1, int arg2);

  abstract void trigger(JavaScriptObject source, String mapEventString,
      LatLng latlng);

  abstract void trigger(JavaScriptObject source, String mapEventString,
      MapType type);

  abstract void trigger(JavaScriptObject source, String mapEventString,
      Overlay overlay);

  abstract void trigger(JavaScriptObject source, String mapEventString,
      Overlay overlay, LatLng latlng);

  abstract void trigger(JavaScriptObject source, String mapEventString,
      Point point, Element elem, Overlay overlay);

  // We don't use this method with the advent of the ListenerCollection.
  // protected abstract void clearListeners(JavaScriptObject source, String
  // event);
}
