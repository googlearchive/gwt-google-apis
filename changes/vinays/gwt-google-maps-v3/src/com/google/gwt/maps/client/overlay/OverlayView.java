/* Copyright (c) 2010 Vinay Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.gwt.maps.client.overlay;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.jsio.client.Exported;
import com.google.gwt.maps.client.HasMapCanvasProjection;
import com.google.gwt.maps.client.HasMapPanes;
import com.google.gwt.maps.client.Map;
import com.google.gwt.maps.client.MapCanvasProjection;
import com.google.gwt.maps.client.MapPanes;
import com.google.gwt.maps.client.mvc.MVCObject;
import com.google.gwt.maps.client.overlay.impl.OverlayViewImpl;

/**
 * You should inherit from this class by setting your overlay's prototype to new
 * OverlayView.prototype. You must implement three methods: onAdd(), draw(), and
 * onRemove(). In the add() method, you should create DOM objects and append
 * them as children of the panes. In the draw() method, you should position
 * these elements. In the onRemove() method, you should remove the objects from
 * the DOM. You must call setMap() with a valid Map object to trigger the call
 * to the onAdd() method and setMap(null) in order to trigger the onRemove()
 * method. The setMap() method can be called at the time of construction or at
 * any point afterward when the overlay should be re-shown after removing. The
 * draw() method will then be called whenever a map property changes that could
 * change the position of the element, such as zoom, center, or map type.
 * 
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public abstract class OverlayView extends MVCObject implements HasOverlayView {
  
  private final JavaScriptObject jso;
  
  public OverlayView(JavaScriptObject jso) {
    this.jso = jso;
    OverlayViewImpl.impl.bind(jso, this);
  }
  
  public OverlayView() {
    this(OverlayViewImpl.impl.construct());
  }
  
  @Override
  public Map getMap() {
    return OverlayViewImpl.impl.getMap(jso).cast();
  }
  
  @Override
  public HasMapPanes getPanes() {
    return new MapPanes(OverlayViewImpl.impl.getPanes(jso));
  }
  
  @Override
  public HasMapCanvasProjection getProjection() {
    return new MapCanvasProjection(OverlayViewImpl.impl.getProjection(jso));
  }
  
  @Override
  public void setMap(Map map) {
    OverlayViewImpl.impl.setMap(jso, map);
  }
  
  @Exported
  @Override
  public abstract void draw();
  
  @Exported
  @Override
  public abstract void onAdd();
  
  @Exported
  @Override
  public abstract void onRemove();
  
  @Override
  public JavaScriptObject getJso() {
    return jso;
  }

}
