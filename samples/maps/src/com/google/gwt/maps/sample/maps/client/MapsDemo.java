package com.google.gwt.maps.sample.maps.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;

public abstract class MapsDemo extends Composite {

  public abstract static class MapsDemoInfo {

    private MapsDemo instance;

    public abstract MapsDemo createInstance();

    public HTML getDescriptionHTML() {
      return new HTML("<p><i>Description not provided.</i></p>\n"
          + "<p>(Add an implementation of <code>getDescriptionHTML()</code> "
          + "for this demo)</p>");
    }

    public MapsDemo getInstance() {
      if (instance == null) {
        instance =
            createInstance();
      }
      return instance;
    }

    public abstract String getName();
  }

  public void onShow() {
  }
}
