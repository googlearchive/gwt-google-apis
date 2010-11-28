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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.maps.client.Map;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.geocoder.GeocoderCallback;
import com.google.gwt.maps.client.geocoder.HasGeocoder;
import com.google.gwt.maps.client.geocoder.HasGeocoderRequest;
import com.google.gwt.maps.client.geocoder.HasGeocoderResult;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.sample.client.Constant;
import com.google.gwt.maps.sample.client.view.SampleView;
import com.google.gwt.user.client.Window;

import java.util.List;

/**
 * Sample geocoder simple presenter.
 * 
 * Geocode address entered in the text box.
 */
public class GeocoderSimplePresenter implements Presenter<GeocoderSimplePresenter.Display> {

  public static interface Display extends SampleView {
    
    /**
     * Get address to geocode from user.
     */
    String getAddress();
    
    /**
     * Get click handler to attach geocode event.
     */
    HasClickHandlers getGeocodeButton();
    
    /**
     * Create new Geocoder.
     */
    HasGeocoder createGeocoder();
    
    /**
     * Create new GeocoderRequest.
     */
    HasGeocoderRequest createGeocoderRequest();
    
    /**
     * Create new Marker.
     */
    Marker createMarker();
    
    /**
     * Get embedded map.
     */
    Map getMap();
    
  }
  
  final private Display display;
  final private String presenterLink;
  final private String viewLink;
  
  public GeocoderSimplePresenter(final Display display) {
    super();
    this.display = display;
    presenterLink = Constant.SOURCE_URL_PREFIX
        + this.getClass().getName().replace('.', '/') + ".java";
    viewLink = Constant.SOURCE_URL_PREFIX
        + display.getClass().getName().replace('.', '/') + ".java";
  }

  @Override
  public void bind() {
    display.setPresenterLink(presenterLink);
    display.setViewLink(viewLink);
    
    final HasClickHandlers geocodeButton = display.getGeocodeButton();
    // Add click handler to geocode button
    geocodeButton.addClickHandler(new ClickHandler() {
      
      @Override
      public void onClick(ClickEvent event) {
        // Geocode address entered by user in the text box.
        final String address = display.getAddress();
        final HasGeocoderRequest gRequest = display.createGeocoderRequest();
        gRequest.setAddress(address);
        
        final HasGeocoder geocoder = display.createGeocoder();
        geocoder.geocode(gRequest, new GeocoderCallback() {
          
          @Override
          public void callback(List<HasGeocoderResult> responses, String status) {
            if (status.equals("OK")) {
              final HasGeocoderResult gResult = responses.get(0);
              final LatLng gLatLng = gResult.getGeometry().getLocation();
              // Add marker at the geocoder location.
              final Marker marker = display.createMarker();
              marker.setPosition(gLatLng);
              marker.setMap(display.getMap());
              // Pan to the geocoded location.
              display.getMap().panTo(gLatLng);
            } else {
              Window.alert("Geocoder failed with response : " + status);
            }
          }
        });
      }
    });
    
  }

  @Override
  public Display getDisplay() {
    return display;
  }

  @Override
  public HandlerManager getEventBus() {
    return null;
  }

  @Override
  public void release() {
    // TODO Auto-generated method stub
    
  }
}
