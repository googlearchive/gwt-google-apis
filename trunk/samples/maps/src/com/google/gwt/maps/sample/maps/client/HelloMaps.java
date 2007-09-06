package com.google.gwt.maps.sample.maps.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.maps.sample.maps.client.MapsDemo.MapsDemoInfo;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.HistoryListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class HelloMaps implements EntryPoint, HistoryListener {

  protected DemoList list = new DemoList();
  private MapsDemoInfo curInfo;
  private MapsDemo curMapsDemo;
  private HTML description = new HTML();
  private VerticalPanel panel = new VerticalPanel();

  public void onHistoryChanged(String token) {
    // Find the MapsDemoInfo associated with the history context. If one is
    // found, show it (It may not be found, for example, when the user mis-
    // types a URL, or on startup, when the first context will be "").
    MapsDemoInfo info = list.find(token);
    if (info == null) {
      showInfo();
      return;
    }
    show(info, false);
  }

  public void onModuleLoad() {
    // Load all the MapsDemos.
    loadMapsDemos();

    panel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
    panel.add(list);
    panel.add(description);
    panel.setWidth("100%");

    description.setStyleName("ks-Info");

    History.addHistoryListener(this);
    RootPanel.get().add(panel);

    // Show the initial screen.
    String initToken = History.getToken();
    if (initToken.length() > 0) {
      onHistoryChanged(initToken);
    } else {
      showInfo();
    }
  }

  public void show(MapsDemoInfo info, boolean affectHistory) {
    // Don't bother re-displaying the existing MapsDemo. This can be an issue
    // in practice, because when the history context is set, our
    // onHistoryChanged() handler will attempt to show the currently-visible
    // MapsDemo.
    if (info == curInfo) {
      return;
    }
    curInfo = info;

    // Remove the old MapsDemo from the display area.
    if (curMapsDemo != null) {
      panel.remove(curMapsDemo);
    }

    // Get the new MapsDemo instance, and display its description in the
    // MapsDemo list.
    curMapsDemo = info.getInstance();
    list.setMapsDemoSelection(info.getName());
    // description.setHTML(info.getDescription());

    // If affectHistory is set, create a new item on the history stack. This
    // will ultimately result in onHistoryChanged() being called. It will call
    // show() again, but nothing will happen because it will request the exact
    // same MapsDemo we're already showing.
    if (affectHistory) {
      History.newItem(info.getName());
    }

    // Change the description background color.
    // DOM.setStyleAttribute(description.getElement(), "backgroundColor",
    // info.getColor());

    // Display the new MapsDemo.
    panel.add(curMapsDemo);
    curMapsDemo.onShow();
  }

  /**
   * Adds all MapsDemos to the list. Note that this does not create actual
   * instances of all MapsDemos yet (they are created on-demand). This can make
   * a significant difference in startup time.
   */
  protected void loadMapsDemos() {
    list.addMapsDemo(SimpleDemo.init());
    list.addMapsDemo(AnimateDemo.init());
    list.addMapsDemo(ControlsDemo.init());
    list.addMapsDemo(EventDemo.init());
    list.addMapsDemo(InfoWindowDemo.init());
    list.addMapsDemo(OverlayDemo.init());
    list.addMapsDemo(ClickDemo.init());
    list.addMapsDemo(MarkerInfoWindowDemo.init());
    list.addMapsDemo(IconDemo.init());
    list.addMapsDemo(IconClassDemo.init());
    list.addMapsDemo(DragMarkerDemo.init());
    list.addMapsDemo(CustomControlDemo.init());
    list.addMapsDemo(CustomOverlayDemo.init());
    list.addMapsDemo(GeoRssOverlayDemo.init());
    list.addMapsDemo(KmlOverlayDemo.init());
    list.addMapsDemo(TrafficOverlayDemo.init());
    list.addMapsDemo(SimpleDirectionsDemo.init());
    // advanced directions
    list.addMapsDemo(GeocoderDemo.init());
    list.addMapsDemo(Geocoder2Demo.init());
    list.addMapsDemo(WeatherMapDemo.init());
    list.addMapsDemo(TabbedInfoWindowDemo.init());
  }

  private void showInfo() {
    show(list.find("The Basics"), false);
  }
}
