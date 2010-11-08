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
package com.google.gwt.maps.sample.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.maps.client.HasMap;
import com.google.gwt.maps.client.MapOptions;
import com.google.gwt.maps.client.MapTypeId;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.directions.DirectionsRenderer;
import com.google.gwt.maps.client.directions.DirectionsRendererOptions;
import com.google.gwt.maps.client.directions.DirectionsRequest;
import com.google.gwt.maps.client.directions.DirectionsService;
import com.google.gwt.maps.client.directions.HasDirectionsRenderer;
import com.google.gwt.maps.client.directions.HasDirectionsRendererOptions;
import com.google.gwt.maps.client.directions.HasDirectionsRequest;
import com.google.gwt.maps.client.directions.HasDirectionsService;
import com.google.gwt.maps.sample.client.presenter.DirectionsPanelPresenter.Display;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * 
 * 
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public class DirectionsPanelView extends Composite implements Display {

  private static DirectionsPanelViewUiBinder uiBinder = GWT
      .create(DirectionsPanelViewUiBinder.class);

  interface DirectionsPanelViewUiBinder extends
      UiBinder<Widget, DirectionsPanelView> {
  }
  
  final private static int ZOOM = 8;
  final private static LatLng CENTER = LatLng.construct(41.850033, -87.6500523);
  final private static String MAP_TYPE = new MapTypeId().getRoadmap();

  @UiField
  Anchor presenterLink;
  @UiField
  Anchor viewLink;
  @UiField
  SimplePanel mapWrapper;
  @UiField
  TextBox origin;
  @UiField
  TextBox destination;
  @UiField
  Button directions;

  private MapWidget mapWidget;

  public DirectionsPanelView() {
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
    
    origin.setValue("Joplin, MO");
    destination.setValue("Chicago");
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.google.gwt.maps.sample.client.presenter.DirectionsPanelPresenter.Display
   * #createDirectionsRenderer(com.google.gwt.maps.client.directions.
   * HasDirectionsRendererOptions)
   */
  @Override
  public HasDirectionsRenderer createDirectionsRenderer(
      HasDirectionsRendererOptions opts) {
    return new DirectionsRenderer(opts);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.google.gwt.maps.sample.client.presenter.DirectionsPanelPresenter.Display
   * #createDirectionsRendererOptions()
   */
  @Override
  public HasDirectionsRendererOptions createDirectionsRendererOptions() {
    return new DirectionsRendererOptions();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.google.gwt.maps.sample.client.presenter.DirectionsPanelPresenter.Display
   * #createDirectionsRequest()
   */
  @Override
  public HasDirectionsRequest createDirectionsRequest() {
    return new DirectionsRequest();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.google.gwt.maps.sample.client.presenter.DirectionsPanelPresenter.Display
   * #createDirectionsService()
   */
  @Override
  public HasDirectionsService createDirectionsService() {
    return new DirectionsService();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.google.gwt.maps.sample.client.presenter.DirectionsPanelPresenter.Display
   * #getDestination()
   */
  @Override
  public String getDestination() {
    return destination.getValue();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.google.gwt.maps.sample.client.presenter.DirectionsPanelPresenter.Display
   * #getDirections()
   */
  @Override
  public HasClickHandlers getDirections() {
    return directions;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.google.gwt.maps.sample.client.presenter.DirectionsPanelPresenter.Display
   * #getMap()
   */
  @Override
  public HasMap getMap() {
    return mapWidget.getMap();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.google.gwt.maps.sample.client.presenter.DirectionsPanelPresenter.Display
   * #getOrigin()
   */
  @Override
  public String getOrigin() {
    return origin.getValue();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.google.gwt.maps.sample.client.view.SampleView#setPresenterLink(java
   * .lang.String)
   */
  @Override
  public void setPresenterLink(String html) {
    presenterLink.setHref(html);
    presenterLink.setText(html);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.google.gwt.maps.sample.client.view.SampleView#setViewLink(java.lang
   * .String)
   */
  @Override
  public void setViewLink(String html) {
    viewLink.setHref(html);
    viewLink.setText(html);

  }

  /*
   * (non-Javadoc)
   * 
   * @see com.google.gwt.maps.sample.client.view.View#asWidget()
   */
  @Override
  public Widget asWidget() {
    return this;
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.sample.client.presenter.DirectionsPanelPresenter.Display#createDirectionsRenderer()
   */
  @Override
  public HasDirectionsRenderer createDirectionsRenderer() {
    return new DirectionsRenderer();
  }

}
