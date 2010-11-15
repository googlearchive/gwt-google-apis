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
import com.google.gwt.maps.client.HasMap;
import com.google.gwt.maps.client.base.HasInfoWindow;
import com.google.gwt.maps.client.base.HasLatLngBounds;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.event.EventCallback;
import com.google.gwt.maps.client.mvc.MVCObject;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.sample.client.Constant;
import com.google.gwt.maps.sample.client.view.SampleView;

import java.util.ArrayList;

/**
 * Adds five random markers on map with random infoWindows attached.
 * 
 * It attaches five info windows each containing a letter of a sentence to five
 * markers randomly positioned on the map.
 */
public class EventClosurePresenter implements Presenter<EventClosurePresenter.Display> {

  final private String presenterLink;
  final private String viewLink;

  public static interface Display extends SampleView {
    HasMap getMap();
    void fitBounds(HasLatLngBounds bounds);
    LatLng createLatLng(double lat, double lng);
    HasLatLngBounds createBounds(LatLng southWest, LatLng northEast);
    Marker createMarkerAt(LatLng position);
    HasInfoWindow createInfoWindow(String content);
    void addListener(Marker instance, String eventName, EventCallback callback);
    void clearInstanceListeners(Marker instance);
  }
  
  final private Display display;
  final private HandlerManager eventBus;
  final private ArrayList<Marker> markers;
  final private String[] message = new String[] { "This", "is", "the", "secret", "message" };

  public EventClosurePresenter(Display display, HandlerManager eventBus) {
    super();
    this.display = display;
    this.eventBus = eventBus;
    markers = new ArrayList<Marker>();
    presenterLink = Constant.SOURCE_URL_PREFIX + this.getClass().getName().replace('.', '/')
        + ".java";
    viewLink = Constant.SOURCE_URL_PREFIX + display.getClass().getName().replace('.', '/')
        + ".java";
  }

  @Override
  public void bind() {
    display.setPresenterLink(presenterLink);
    display.setViewLink(viewLink);
    final LatLng southWest = display.createLatLng(-31.203405, 125.244141);
    final LatLng northEast = display.createLatLng(-25.363882, 131.044922);
    display.fitBounds(display.createBounds(southWest, northEast));
    
    final double lngSpan = northEast.getLongitude() - southWest.getLongitude();
    final double latSpan = northEast.getLatitude() - southWest.getLatitude();
    
    for (int i = 0; i < 5; ++i) {
      final LatLng location = display.createLatLng(southWest.getLongitude() + (latSpan * Math.random()),
          southWest.getLongitude() + (lngSpan * Math.random()));
      final Marker marker = display.createMarkerAt(location);
      markers.add(marker);
      int j = i + 1;
      marker.setTitle("" + j);
      attachSecretMessage(marker, i);
    }
  }

  @Override
  public Display getDisplay() {
    return display;
  }

  @Override
  public HandlerManager getEventBus() {
    return eventBus;
  }

  @Override
  public void release() {
    for (final Marker marker : markers) {
      display.clearInstanceListeners(marker);
    }
  }

  private void attachSecretMessage(final Marker marker, final int number) {
    final HasInfoWindow infoWindow = display.createInfoWindow(message[number]);
    display.addListener(marker, "click", new EventCallback() {
      
      @Override
      public void callback() {
        infoWindow.open(display.getMap(), new MVCObject(marker));
      }
    });
  }
}
