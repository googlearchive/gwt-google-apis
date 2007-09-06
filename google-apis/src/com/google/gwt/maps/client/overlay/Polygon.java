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

import com.google.gwt.maps.client.event.RemoveListener;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.impl.EventImpl;
import com.google.gwt.maps.client.impl.PolygonImpl;
import com.google.gwt.maps.client.impl.EventImpl.VoidCallback;
import com.google.gwt.maps.client.overlay.Overlay.ConcreteOverlay;
import com.google.gwt.maps.client.util.JsUtil;

public final class Polygon extends ConcreteOverlay {

  public Polygon(LatLng[] points, String strokeColor, int strokeWeight,
      double strokeOpacity, String fillColor, double fillOpacity) {
    super(PolygonImpl.impl.construct(JsUtil.toJsList(points), strokeColor,
        strokeWeight, strokeOpacity, fillColor, fillOpacity));
  }

  public Polygon(LatLng[] points) {
    super(PolygonImpl.impl.construct(JsUtil.toJsList(points)));
  }

  public int getVertextCount() {
    return PolygonImpl.impl.getVertextCount(this);
  }

  public LatLng getVertex(int index) {
    return PolygonImpl.impl.getVertex(this, index);
  }

  public void addRemoveListener(final RemoveListener listener) {
    EventImpl.impl.associate(listener, EventImpl.impl.addListenerVoid(
        super.jsoPeer, "remove", new VoidCallback() {
          public void callback() {
            listener.onRemove(Polygon.this);
          }
        }));
  }

  public void removeRemoveListener(RemoveListener listener) {
    EventImpl.impl.removeListeners(listener);
  }

  public void clearRemoveListeners() {
  }
}
