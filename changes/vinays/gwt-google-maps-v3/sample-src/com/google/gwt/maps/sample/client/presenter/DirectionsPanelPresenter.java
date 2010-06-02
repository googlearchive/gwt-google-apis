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
package com.google.gwt.maps.sample.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.maps.client.HasMap;
import com.google.gwt.maps.client.directions.DirectionsCallback;
import com.google.gwt.maps.client.directions.DirectionsTravelMode;
import com.google.gwt.maps.client.directions.HasDirectionsRenderer;
import com.google.gwt.maps.client.directions.HasDirectionsRendererOptions;
import com.google.gwt.maps.client.directions.HasDirectionsRequest;
import com.google.gwt.maps.client.directions.HasDirectionsResult;
import com.google.gwt.maps.client.directions.HasDirectionsService;
import com.google.gwt.maps.sample.client.Constant;
import com.google.gwt.maps.sample.client.view.SampleView;

/**
 * Simple DirectionsSerive usage.
 * 
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public class DirectionsPanelPresenter implements
    Presenter<DirectionsPanelPresenter.Display> {

  public static interface Display extends SampleView {
    
    /**
     * Get map object.
     */
    HasMap getMap();

    /**
     * Get DirectionsRequest object.
     */
    HasDirectionsRequest createDirectionsRequest();

    /**
     * Get DirectionsService object.
     */
    HasDirectionsService createDirectionsService();

    /**
     * Get DirectionsRendererOptions object.
     */
    HasDirectionsRendererOptions createDirectionsRendererOptions();

    /**
     * Get DirectionsRenderer object initialized with options.
     */
    HasDirectionsRenderer createDirectionsRenderer(
        HasDirectionsRendererOptions opts);

    /**
     * Get DirectionsRenderer object.
     */
    HasDirectionsRenderer createDirectionsRenderer();

    /**
     * Get origin address.
     */
    String getOrigin();

    /**
     * Get destination address.
     */
    String getDestination();

    /**
     * Get directions button onclick handlers.
     */
    HasClickHandlers getDirections();
  }

  private Display display;
  private String presenterLink;
  private String viewLink;

  /**
   * @param display
   */
  public DirectionsPanelPresenter(Display display) {
    super();
    this.display = display;
    presenterLink = Constant.SOURCE_URL_PREFIX
        + this.getClass().getName().replace('.', '/') + ".java";
    viewLink = Constant.SOURCE_URL_PREFIX
        + display.getClass().getName().replace('.', '/') + ".java";
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.google.gwt.maps.sample.client.presenter.Presenter#bind()
   */
  @Override
  public void bind() {
    display.getDirections().addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        getDirections();
      }
    });
    display.setPresenterLink(presenterLink);
    display.setViewLink(viewLink);
  }

  /**
   * Get route between origin and destination using DirectionsService.
   * 
   * Takes origin and destination address from text boxes and call the
   * DirectionsService to fetch route between the addresses. Sends the async
   * response to render on map.
   */
  void getDirections() {
    final HasDirectionsRequest req = display.createDirectionsRequest();
    req.setOriginString(display.getOrigin());
    req.setDestinationString(display.getDestination());
    req.setTravelMode(new DirectionsTravelMode().Driving());

    final HasDirectionsService service = display.createDirectionsService();
    service.route(req, new DirectionsCallback() {

      @Override
      public void callback(HasDirectionsResult response, String status) {
        if (status.equals("OK")) {
          renderResult(response);
        }
      }
    });
  }

  /**
   * Render route result on map using DirectionsRenderer.
   */
  void renderResult(HasDirectionsResult result) {
    final HasDirectionsRendererOptions options = display
        .createDirectionsRendererOptions();
    options.setDirections(result);
    options.setMap(display.getMap());
    display.createDirectionsRenderer(options);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.google.gwt.maps.sample.client.presenter.Presenter#getDisplay()
   */
  @Override
  public Display getDisplay() {
    return display;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.google.gwt.maps.sample.client.presenter.Presenter#getEventBus()
   */
  @Override
  public HandlerManager getEventBus() {
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.google.gwt.maps.sample.client.presenter.Presenter#release()
   */
  @Override
  public void release() {
    // No-op.
  }
}
