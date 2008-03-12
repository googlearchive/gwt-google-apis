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
package com.google.gwt.maps.client.overlay;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.event.RemoveListener;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.impl.EventImpl;
import com.google.gwt.maps.client.impl.ListenerCollection;
import com.google.gwt.maps.client.impl.MapEvent;
import com.google.gwt.maps.client.impl.PolylineFactoryImpl;
import com.google.gwt.maps.client.impl.PolylineImpl;
import com.google.gwt.maps.client.impl.PolylineOptionsImpl;
import com.google.gwt.maps.client.impl.EventImpl.VoidCallback;
import com.google.gwt.maps.client.overlay.Overlay.ConcreteOverlay;
import com.google.gwt.maps.client.util.JsUtil;

/**
 * 
 */
public final class Polyline extends ConcreteOverlay {

  public static Polyline fromEncoded(String color, int weight, double opacity,
      String encodedPoints, int zoomFactor, String encodedLevels, int numLevels) {
    JavaScriptObject optionsJso = PolylineOptionsImpl.impl.construct();
    PolylineOptionsImpl.impl.setColor(optionsJso, color);
    PolylineOptionsImpl.impl.setWeight(optionsJso, weight);
    PolylineOptionsImpl.impl.setOpacity(optionsJso, opacity);
    PolylineOptionsImpl.impl.setPoints(optionsJso, encodedPoints);
    PolylineOptionsImpl.impl.setZoomFactor(optionsJso, zoomFactor);
    PolylineOptionsImpl.impl.setLevels(optionsJso, encodedLevels);
    PolylineOptionsImpl.impl.setNumLevels(optionsJso, numLevels);
    return new Polyline(PolylineFactoryImpl.impl.fromEncoded(optionsJso));
  }

  public static Polyline fromEncoded(String encodedPoints, int zoomFactor,
      String encodedLevels, int numLevels) {
    JavaScriptObject optionsJso = PolylineOptionsImpl.impl.construct();
    PolylineOptionsImpl.impl.setPoints(optionsJso, encodedPoints);
    PolylineOptionsImpl.impl.setZoomFactor(optionsJso, zoomFactor);
    PolylineOptionsImpl.impl.setLevels(optionsJso, encodedLevels);
    PolylineOptionsImpl.impl.setNumLevels(optionsJso, numLevels);
    return new Polyline(PolylineFactoryImpl.impl.fromEncoded(optionsJso));
  }

  private static Polyline createPeer(JavaScriptObject jsoPeer) {
    return new Polyline(jsoPeer);
  }

  private ListenerCollection<RemoveListener> removeListeners;

  public Polyline(LatLng[] points) {
    super(PolylineImpl.impl.construct(JsUtil.toJsList(points)));
  }

  public Polyline(LatLng[] points, String color) {
    super(PolylineImpl.impl.construct(JsUtil.toJsList(points), color));
  }

  public Polyline(LatLng[] points, String color, int weight) {
    super(PolylineImpl.impl.construct(JsUtil.toJsList(points), color, weight));
  }

  public Polyline(LatLng[] points, String color, int weight, double opacity) {
    super(PolylineImpl.impl.construct(JsUtil.toJsList(points), color, weight,
        opacity));
  }

  private Polyline(JavaScriptObject jsoPeer) {
    super(jsoPeer);
  }

  public void addRemoveListener(final RemoveListener listener) {
    if (removeListeners == null) {
      removeListeners = new ListenerCollection<RemoveListener>();
    }

    JavaScriptObject removeEventHandles[] = {EventImpl.impl.addListenerVoid(
        super.jsoPeer, MapEvent.REMOVE, new VoidCallback() {
          @Override
          public void callback() {
            listener.onRemove(Polyline.this);
          }
        })};
    removeListeners.addListener(listener, removeEventHandles);
  }

  public void clearRemoveListeners() {
    if (removeListeners != null) {
      removeListeners.clearListeners();
    }
  }

  public LatLng getVertex(int index) {
    return PolylineImpl.impl.getVertex(super.jsoPeer, index);
  }

  public int getVertexCount() {
    return PolylineImpl.impl.getVertexCount(super.jsoPeer);
  }

  public void removeRemoveListener(RemoveListener listener) {
    if (removeListeners != null) {
      removeListeners.removeListener(listener);
    }
  }
}
