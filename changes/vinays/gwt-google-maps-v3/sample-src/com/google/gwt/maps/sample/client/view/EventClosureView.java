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
package com.google.gwt.maps.sample.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.maps.client.HasJso;
import com.google.gwt.maps.client.HasMap;
import com.google.gwt.maps.client.MapOptions;
import com.google.gwt.maps.client.MapTypeId;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.HasInfoWindow;
import com.google.gwt.maps.client.base.HasLatLng;
import com.google.gwt.maps.client.base.HasLatLngBounds;
import com.google.gwt.maps.client.base.InfoWindow;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.base.LatLngBounds;
import com.google.gwt.maps.client.event.Event;
import com.google.gwt.maps.client.event.EventCallback;
import com.google.gwt.maps.client.overlay.HasMarker;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.sample.client.presenter.EventClosurePresenter.Display;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Event closure sample ui.
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public class EventClosureView extends Composite implements Display {

  private static EventClosureViewUiBinder uiBinder = GWT
      .create(EventClosureViewUiBinder.class);

  @UiTemplate("SimpleView.ui.xml")
  interface EventClosureViewUiBinder extends UiBinder<Widget, EventClosureView> {
  }

  final private static int ZOOM = 8;
  final private static LatLng CENTER = new LatLng(-25.363882,131.044922);
  final private static String MAP_TYPE = new MapTypeId().getRoadmap();
  
  final private MapWidget mapWidget;
  
  @UiField
  Anchor presenterLink;
  @UiField
  Anchor viewLink;
  @UiField
  SimplePanel mapWrapper;

  public EventClosureView() {
    initWidget(uiBinder.createAndBindUi(this));
    final MapOptions options = new MapOptions();
    options.setZoom(ZOOM);
    options.setCenter(CENTER);
    options.setMapTypeId(MAP_TYPE);
    options.setDraggable(true);
    options.setNavigationControl(true);
    options.setMapTypeControl(true);
    mapWidget = new MapWidget(options);
    mapWrapper.setWidget(mapWidget);
    mapWidget.setSize("800px", "600px");
  }

  @Override
  public void addListener(HasJso instance, String eventName,
      EventCallback callback) {
    Event.addListener(instance, eventName, callback);
  }

  @Override
  public void clearInstanceListeners(HasJso instance) {
    Event.clearInstanceListeners(instance);
  }

  @Override
  public HasLatLngBounds createBounds(HasLatLng southWest, HasLatLng northEast) {
    return new LatLngBounds(southWest, northEast);
  }

  @Override
  public HasInfoWindow createInfoWindow(String content) {
    final InfoWindow infoWindow = new InfoWindow();
    infoWindow.setContent(content);
    return infoWindow;
  }

  @Override
  public HasLatLng createLatLng(double lat, double lng) {
    return new LatLng(lat, lng);
  }

  @Override
  public HasMarker createMarkerAt(HasLatLng position) {
    final Marker marker = new Marker();
    marker.setMap(getMap());
    marker.setPosition(position);
    return marker;
  }

  @Override
  public HasMap getMap() {
    return mapWidget.getMap();
  }

  @Override
  public Widget asWidget() {
    return this;
  }

  @Override
  public void fitBounds(HasLatLngBounds bounds) {
    mapWidget.fitBounds(bounds);
  }

  @Override
  public void setPresenterLink(String url) {
    presenterLink.setHref(url);
    presenterLink.setText(url);
  }

  @Override
  public void setViewLink(String url) {
    viewLink.setHref(url);
    viewLink.setText(url);
  }

}
