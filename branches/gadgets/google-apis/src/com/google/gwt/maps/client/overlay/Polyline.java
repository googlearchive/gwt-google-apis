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
import com.google.gwt.maps.client.event.RemoveListener;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.impl.EventImpl;
import com.google.gwt.maps.client.impl.PolylineImpl;
import com.google.gwt.maps.client.impl.EventImpl.VoidCallback;
import com.google.gwt.maps.client.overlay.Overlay.ConcreteOverlay;
import com.google.gwt.maps.client.util.JsUtil;

/**
 * 
 */
public final class Polyline extends ConcreteOverlay {
  
  private static Polyline createPeer(JavaScriptObject jsoPeer) {
    return new Polyline(jsoPeer);
  }

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

  // TODO: "Factory method"?

  public void addRemoveListener(final RemoveListener listener) {
    EventImpl.impl.associate(listener, EventImpl.impl.addListenerVoid(
        super.jsoPeer, "remove", new VoidCallback() {
          public void callback() {
            listener.onRemove(Polyline.this);
          }
        }));
  }

  public void clearRemoveListeners() {
  }

  public LatLng getVertex(int index) {
    return PolylineImpl.impl.getVertex(this, index);
  }

  public int getVertexCount() {
    return PolylineImpl.impl.getVertexCount(this);
  }

  public void removeRemoveListener(RemoveListener listener) {
    EventImpl.impl.removeListeners(listener);
  }
}
