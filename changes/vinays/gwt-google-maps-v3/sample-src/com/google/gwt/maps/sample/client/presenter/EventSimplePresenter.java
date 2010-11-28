/* Copyright (c) 2010 Google Inc.
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
package com.google.gwt.maps.sample.client.presenter;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.maps.client.Map;
import com.google.gwt.maps.client.event.EventCallback;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.sample.client.Constant;
import com.google.gwt.maps.sample.client.view.SampleView;

/**
 * Add click event to a marker.
 * 
 * Zooms in to level '4' on click on the marker.
 */
public class EventSimplePresenter implements Presenter<EventSimplePresenter.Display>{

  final private String presenterLink;
  final private String viewLink;

  public static interface Display extends SampleView {
    
    void addListener(Marker instance, String eventName, EventCallback callback);
    
    void clearInstanceListeners(Marker instance);
    
    Map getMap();
    
    Marker getMarker();
  }
  
  final private Display display;
  final private HandlerManager eventBus;

  /**
   * @param display
   * @param eventBus
   */
  public EventSimplePresenter(Display display, HandlerManager eventBus) {
    super();
    this.display = display;
    this.eventBus = eventBus;
    presenterLink = Constant.SOURCE_URL_PREFIX + this.getClass().getName().replace('.', '/')
        + ".java";
    viewLink = Constant.SOURCE_URL_PREFIX + display.getClass().getName().replace('.', '/')
        + ".java";
  }

  @Override
  public void bind() {
    display.addListener(display.getMarker(), "click", new EventCallback() {
      
      @Override
      public void callback() {
        display.getMap().setZoom(6);
      }
    });
    display.setPresenterLink(presenterLink);
    display.setViewLink(viewLink);
  }

  @Override
  public EventSimplePresenter.Display getDisplay() {
    return display;
  }

  @Override
  public HandlerManager getEventBus() {
    return eventBus;
  }

  @Override
  public void release() {
    display.clearInstanceListeners(display.getMarker());
  }
}
