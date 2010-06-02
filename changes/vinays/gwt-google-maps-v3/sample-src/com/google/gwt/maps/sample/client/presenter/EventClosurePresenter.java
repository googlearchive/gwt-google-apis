package com.google.gwt.maps.sample.client.presenter;

import java.util.ArrayList;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.maps.client.HasJso;
import com.google.gwt.maps.client.HasMap;
import com.google.gwt.maps.client.base.HasInfoWindow;
import com.google.gwt.maps.client.base.HasLatLng;
import com.google.gwt.maps.client.base.HasLatLngBounds;
import com.google.gwt.maps.client.event.EventCallback;
import com.google.gwt.maps.client.overlay.HasMarker;
import com.google.gwt.maps.sample.client.Constant;
import com.google.gwt.maps.sample.client.view.SampleView;

/**
 * Adds five random markers on map with random infoWindows attached.
 * 
 * It attaches five info windows each containing a letter of a sentence to five
 * markers randomly positioned on the map.
 * 
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public class EventClosurePresenter implements Presenter<EventClosurePresenter.Display> {

  final private String presenterLink;
  final private String viewLink;

  public static interface Display extends SampleView {
    HasMap getMap();
    void fitBounds(HasLatLngBounds bounds);
    HasLatLng createLatLng(double lat, double lng);
    HasLatLngBounds createBounds(HasLatLng southWest, HasLatLng northEast);
    HasMarker createMarkerAt(HasLatLng position);
    HasInfoWindow createInfoWindow(String content);
    void addListener(HasJso instance, String eventName, EventCallback callback);
    void clearInstanceListeners(HasJso instance);
  }
  
  final private Display display;
  final private HandlerManager eventBus;
  final private ArrayList<HasMarker> markers;
  final private String[] message = new String[] { "This", "is", "the", "secret", "message" };

  /**
   * @param display
   * @param eventBus
   */
  public EventClosurePresenter(Display display, HandlerManager eventBus) {
    super();
    this.display = display;
    this.eventBus = eventBus;
    markers = new ArrayList<HasMarker>();
    presenterLink = Constant.SOURCE_URL_PREFIX + this.getClass().getName().replace('.', '/')
        + ".java";
    viewLink = Constant.SOURCE_URL_PREFIX + display.getClass().getName().replace('.', '/')
        + ".java";
  }

  @Override
  public void bind() {
    display.setPresenterLink(presenterLink);
    display.setViewLink(viewLink);
    final HasLatLng southWest = display.createLatLng(-31.203405, 125.244141);
    final HasLatLng northEast = display.createLatLng(-25.363882, 131.044922);
    display.fitBounds(display.createBounds(southWest, northEast));
    
    final double lngSpan = northEast.getLongitude() - southWest.getLongitude();
    final double latSpan = northEast.getLatitude() - southWest.getLatitude();
    
    for (int i = 0; i < 5; ++i) {
      final HasLatLng location = display.createLatLng(southWest.getLatitude() + (latSpan * Math.random()),
          southWest.getLongitude() + (lngSpan * Math.random()));
      final HasMarker marker = display.createMarkerAt(location);
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
    for (final HasMarker marker : markers) {
      display.clearInstanceListeners(marker);
    }
  }

  private void attachSecretMessage(final HasMarker marker, final int number) {
    final HasInfoWindow infoWindow = display.createInfoWindow(message[number]);
    display.addListener(marker, "click", new EventCallback() {
      
      @Override
      public void callback() {
        infoWindow.open(display.getMap(), marker);
      }
    });
  }
}
