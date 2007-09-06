/**
 * 
 */
package com.google.gwt.maps.client.geocode;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.maps.client.impl.DirectionsImpl;
import com.google.gwt.maps.client.overlay.Polyline;

import java.util.AbstractList;
import java.util.List;

public final class DirectionResults {

  private final JavaScriptObject jsoPeer;

  DirectionResults(JavaScriptObject jsoPeer) {
    this.jsoPeer = jsoPeer;
  }

  public LatLngBounds getBounds() {
    return DirectionsImpl.impl.getBounds(jsoPeer);
  }

  public String getCopyrightsHtml() {
    return DirectionsImpl.impl.getCopyrightsHtml(jsoPeer);
  }

  public Distance getDistance() {
    return DirectionsImpl.impl.getDistance(jsoPeer);
  }

  public Duration getDuration() {
    return DirectionsImpl.impl.getDuration(jsoPeer);
  }

  public List /* <Marker> */getMarkers() {
    return new AbstractList() {
      public Object get(int i) {
        return DirectionsImpl.impl.getMarker(jsoPeer, i);
      }

      public int size() {
        return DirectionsImpl.impl.getNumGeocodes(jsoPeer);
      }
    };
  }

  public List /* <Placemark> */getPlacemarks() {
    return new AbstractList() {
      public Object get(int i) {
        return DirectionsImpl.impl.getGeocode(jsoPeer, i);
      }

      public int size() {
        return DirectionsImpl.impl.getNumGeocodes(jsoPeer);
      }
    };
  }

  public Polyline getPolyline() {
    return DirectionsImpl.impl.getPolyline(jsoPeer);
  }

  public List /* <Route> */getRoutes() {
    return new AbstractList() {
      public Object get(int i) {
        return DirectionsImpl.impl.getRoute(jsoPeer, i);
      }

      public int size() {
        return DirectionsImpl.impl.getNumRoutes(jsoPeer);
      }
    };
  }

  public String getSummaryHtml() {
    return DirectionsImpl.impl.getSummaryHtml(jsoPeer);
  }
}