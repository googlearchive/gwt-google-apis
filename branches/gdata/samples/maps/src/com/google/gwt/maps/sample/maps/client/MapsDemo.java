package com.google.gwt.maps.sample.maps.client;

import com.google.gwt.user.client.ui.Composite;

public abstract class MapsDemo extends Composite {

  public abstract static class MapsDemoInfo {

    private MapsDemo instance;

    public abstract MapsDemo createInstance();

    public abstract String getName();

    public MapsDemo getInstance() {
      if (instance == null) {
        instance = createInstance();
      }
      return instance;
    }
  }

  public void onShow() {    
  }
}