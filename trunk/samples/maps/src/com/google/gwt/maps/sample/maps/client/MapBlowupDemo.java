package com.google.gwt.maps.sample.maps.client;

import com.google.gwt.maps.client.InfoWindow;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.geom.LatLng;

public class MapBlowupDemo extends MapsDemo {

  public static MapsDemoInfo init() {
    return new MapsDemoInfo() {
      public MapsDemo createInstance() {
        return new MapBlowupDemo();
      }

      public String getName() {
        return "Opening an Map Blowup Window";
      }
    };
  }

  private MapWidget map;

  public MapBlowupDemo() {
    map = new MapWidget(new LatLng(37.4419, -122.1419), 13);
    map.setSize("500px", "400px");
    initWidget(map);
  }

  public void onShow() {
    InfoWindow info = map.getInfoWindow();
    info.open(map.getCenter(), new InfoWindowContent.MapBlowupContent());
  }

}
