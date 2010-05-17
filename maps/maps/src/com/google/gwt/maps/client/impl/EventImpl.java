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
import com.google.gwt.maps.client.Copyright;
import com.google.gwt.maps.client.MapType;
import com.google.gwt.maps.client.geom.Bounds;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.overlay.Overlay;
import com.google.gwt.maps.client.streetview.StreetviewData;
import com.google.gwt.maps.client.streetview.StreetviewLocation;
import com.google.gwt.maps.jsio.client.Exported;
import com.google.gwt.maps.jsio.client.Global;
import com.google.gwt.maps.jsio.client.JSFunction;
import com.google.gwt.maps.jsio.client.JSWrapper;
import com.google.gwt.user.client.Element;

/**
 * Wrapper for the GEvent class from the Maps API using JSIO. In order to give
 * type appropriate Java interfaces, this class contains many aliases for the
 * addListener() JavaScript method and associated callback interfaces.
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
    public void callbackWrapper(final boolean value) {
      invokeAndMaybeReportUncaughtExceptions(new Callback() {
        public void execute() {
          callback(value);
        }
      });
    }
  }

  /**
   * Provides a callback interface with a {@link Bounds} followed by an int
   * argument.
   */
  public abstract static class BoundsIntCallback extends JSFunction {
    public abstract void callback(Bounds bounds, int value);

    @Exported
    public void callbackWrapper(final Bounds bounds, final int value) {
      invokeAndMaybeReportUncaughtExceptions(new Callback() {
        public void execute() {
          callback(bounds, value);
        }
      });
    }
  }

  /**
   * TODO: JSIO should wrap any java method that returns to JavaScript with code
   * to deal with uncaught exceptions. Since it does not do that now, we created
   * this class and create little helper anonymous classes to deal with uncaught
   * exceptions.
   */
  public interface Callback {
    void execute();
  }

  /**
   * Provides a callback interface with two int arguments.
   */
  public abstract static class CopyrightCallback extends JSFunction {
    public abstract void callback(Copyright value);

    @Exported
    public void callbackWrapper(final Copyright value) {
      invokeAndMaybeReportUncaughtExceptions(new Callback() {
        public void execute() {
          callback(value);
        }
      });
    }
  }

  /**
   * Provides a callback interface with one double argument.
   */
  public abstract static class DoubleCallback extends JSFunction {
    public abstract void callback(double d);

    @Exported
    public void callbackWrapper(final double d) {
      invokeAndMaybeReportUncaughtExceptions(new Callback() {
        public void execute() {
          callback(d);
        }
      });
    }
  }

  /**
   * Provides a callback interface with one integer argument.
   */
  public abstract static class IntCallback extends JSFunction {
    public abstract void callback(int value);

    @Exported
    public void callbackWrapper(final int value) {
      invokeAndMaybeReportUncaughtExceptions(new Callback() {
        public void execute() {
          callback(value);
        }
      });
    }
  }

  /**
   * Provides a callback interface with two integer arguments.
   */
  public abstract static class IntIntCallback extends JSFunction {
    public abstract void callback(int value1, int value2);

    @Exported
    public void callbackWrapper(final int value1, final int value2) {
      invokeAndMaybeReportUncaughtExceptions(new Callback() {
        public void execute() {
          callback(value1, value2);
        }
      });
    }
  }

  /**
   * Provides a callback interface with a {@link LatLng} and integer argument.
   */
  public abstract static class LatLngBoundsIntCallback extends JSFunction {
    public abstract void callback(LatLngBounds bounds, int value);

    @Exported
    public void callbackWrapper(final LatLngBounds bounds, final int value) {
      invokeAndMaybeReportUncaughtExceptions(new Callback() {
        public void execute() {
          callback(bounds, value);
        }
      });
    }
  }

  /**
   * Provides a callback interface with a {@link LatLng} argument.
   */
  public abstract static class LatLngCallback extends JSFunction {
    public abstract void callback(LatLng latlng);

    @Exported
    public void callbackWrapper(final LatLng latlng) {
      invokeAndMaybeReportUncaughtExceptions(new Callback() {
        public void execute() {
          callback(latlng);
        }
      });
    }
  }

  /**
   * Provides a callback interface with a {@link MapType} argument.
   */
  public abstract static class MapTypeCallback extends JSFunction {
    public abstract void callback(MapType mapType);

    @Exported
    public void callbackWrapper(final MapType mapType) {
      invokeAndMaybeReportUncaughtExceptions(new Callback() {
        public void execute() {
          callback(mapType);
        }
      });
    }
  }

  /**
   * Provides a callback interface with an {@link Overlay} argument.
   */
  public abstract static class OverlayCallback extends JSFunction {
    public abstract void callback(Overlay overlay);

    @Exported
    public void callbackWrapper(final Overlay overlay) {
      invokeAndMaybeReportUncaughtExceptions(new Callback() {
        public void execute() {
          callback(overlay);
        }
      });
    }
  }

  /**
   * Provides a callback interface with an {@link Overlay} and two
   * {@link LatLng} arguments.
   */
  public abstract static class OverlayLatLngCallback extends JSFunction {
    public abstract void callback(Overlay overlay, LatLng latlng,
        LatLng overlaylatlng);

    @Exported
    public void callbackWrapper(final Overlay overlay, final LatLng latlng,
        final LatLng overlaylatlng) {
      invokeAndMaybeReportUncaughtExceptions(new Callback() {
        public void execute() {
          callback(overlay, latlng, overlaylatlng);
        }
      });
    }
  }

  /**
   * Provides a callback interface with {@link Point}, {@link Element}, and
   * {@link Overlay} arguments.
   */
  public abstract static class PointElementOverlayCallback extends JSFunction {
    public abstract void callback(Point point, Element element, Overlay overlay);

    @Exported
    public void callbackWrapper(final Point point, final Element element,
        final Overlay overlay) {
      invokeAndMaybeReportUncaughtExceptions(new Callback() {
        public void execute() {
          callback(point, element, overlay);
        }
      });
    }
  }

  /**
   * Provides a callback interface with {@link StreetviewData} argument.
   */
  public abstract static class StreetviewDataCallback extends JSFunction {
    public abstract void callback(StreetviewData data);

    @Exported
    public void callbackWrapper(final StreetviewData data) {
      invokeAndMaybeReportUncaughtExceptions(new Callback() {
        public void execute() {
          callback(data);
        }
      });
    }
  }

  /**
   * Provides a callback interface with {@link StreetviewLocation} argument.
   */
  public abstract static class StreetviewLocationCallback extends JSFunction {
    public abstract void callback(StreetviewLocation location);

    @Exported
    public void callbackWrapper(final StreetviewLocation location) {
      invokeAndMaybeReportUncaughtExceptions(new Callback() {
        public void execute() {
          callback(location);
        }
      });
    }
  }

  /**
   * Provides a way to specify a JavaScript function() with no arguments and
   * handles uncaught exceptions.
   */
  public abstract static class VoidCallback extends JSFunction implements
      Callback {
    public abstract void callback();

    @Exported
    public void callbackWrapper() {
      invokeAndMaybeReportUncaughtExceptions(this);
    }

    public final void execute() {
      callback();
    }
  }

  public static final EventImpl impl = GWT.create(EventImpl.class);

  /**
   * Invoke the requested {@link Callback} and report exceptions via the
   * {@link UncaughtExceptionHandler} if one is currently set.
   */
  private static void invokeAndMaybeReportUncaughtExceptions(Callback callback) {
    assert (callback != null);

    UncaughtExceptionHandler ucHandler = GWT.getUncaughtExceptionHandler();
    if (ucHandler != null) {
      invokeAndReportUncaughtExceptions(callback, ucHandler);
    } else {
      invokeImpl(callback);
    }
  }

  /**
   * Invoke the requested {@link Callback} and report any uncaught exceptions.
   */
  private static void invokeAndReportUncaughtExceptions(Callback callback,
      UncaughtExceptionHandler ucHandler) {
    assert (callback != null);
    assert (ucHandler != null);
    try {
      callback.execute();
    } catch (Throwable e) {
      ucHandler.onUncaughtException(e);
    }
  }

  /**
   * Invoke the {@link Callback} and allow any uncaught exceptions to escape.
   */
  private static void invokeImpl(Callback callback) {
    callback.execute();
  }

  public JavaScriptObject addListener(JavaScriptObject source, MapEvent event,
      BooleanCallback handler) {
    return addListener(source, event.value(), handler);
  }

  public JavaScriptObject addListener(JavaScriptObject source, MapEvent event,
      IntIntCallback handler) {
    return addListener(source, event.value(), handler);
  }

  public JavaScriptObject addListener(JavaScriptObject source, MapEvent event,
      LatLngBoundsIntCallback handler) {
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
      DoubleCallback handler);

  abstract JavaScriptObject addListener(JavaScriptObject source, String event,
      IntCallback handler);

  abstract JavaScriptObject addListener(JavaScriptObject source, String event,
      IntIntCallback handler);

  abstract JavaScriptObject addListener(JavaScriptObject source, String event,
      LatLngBoundsIntCallback handler);

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
      StreetviewLocationCallback handler);

  abstract JavaScriptObject addListener(JavaScriptObject source, String event,
      VoidCallback handler);

  abstract void trigger(JavaScriptObject source, String mapEventString);

  abstract void trigger(JavaScriptObject source, String mapEventString,
      boolean value);

  abstract void trigger(JavaScriptObject source, String mapEventString,
      Copyright value);

  abstract void trigger(JavaScriptObject source, String mapEventString,
      double value);

  abstract void trigger(JavaScriptObject source, String mapEventString, int arg);

  abstract void trigger(JavaScriptObject source, String mapEventString,
      int arg1, int arg2);

  abstract void trigger(JavaScriptObject source, String mapEventString,
      LatLng latlng);

  abstract void trigger(JavaScriptObject source, String mapEventString,
      LatLngBounds bounds, int value);

  abstract void trigger(JavaScriptObject source, String mapEventString,
      MapType type);

  abstract void trigger(JavaScriptObject source, String mapEventString,
      Overlay overlay);

  abstract void trigger(JavaScriptObject source, String mapEventString,
      Overlay overlay, LatLng latlng);

  abstract void trigger(JavaScriptObject source, String mapEventString,
      Overlay overlay, LatLng latlng1, LatLng latlng2);

  abstract void trigger(JavaScriptObject source, String mapEventString,
      Point point, Element elem, Overlay overlay);

  abstract void trigger(JavaScriptObject source, String mapEventString,
      StreetviewLocation value);

  // We don't use this method with the advent of the HandlerCollection.
  // protected abstract void clearListeners(JavaScriptObject source, String
  // event);

  // We don't use this method with the advent of the HandlerCollection.
  // protected abstract void clearInstanceListeners(JavaScriptObject source);
}
