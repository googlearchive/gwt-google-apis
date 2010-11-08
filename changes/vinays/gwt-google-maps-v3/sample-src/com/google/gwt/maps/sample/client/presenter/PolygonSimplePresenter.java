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

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.maps.client.HasMap;
import com.google.gwt.maps.client.base.HasLatLngBounds;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.overlay.HasPolygon;
import com.google.gwt.maps.client.overlay.Polygon;
import com.google.gwt.maps.sample.client.Constant;
import com.google.gwt.maps.sample.client.view.SampleView;

import java.util.ArrayList;

/**
 * Sample polygon simple presenter.
 * 
 * Add a rectangle overlay on the map with given corner LatLngs.
 *
 */
public class PolygonSimplePresenter implements Presenter<PolygonSimplePresenter.Display> {

  /**
   * Interface for presenter's UI.
   */
  public static interface Display extends SampleView {

    /**
     * Create LatLng.
     * 
     * new LatLng(lat, lng);
     */
    LatLng createLatLng(double lat, double lng);
    
    /**
     * Create LatLngBounds.
     * 
     * new LatLngBounds(southWest, northEast);
     */
    HasLatLngBounds createBounds(LatLng southWest, LatLng northEast);

    /**
     * Create Polygon.
     * 
     * new Polygon();
     */
    HasPolygon createPolygon();
    
    /**
     * Get embedded map's reference.
     */
    HasMap getMap();

    /**
     * Fit embedded map to the bounds.
     */
    void fitBounds(HasLatLngBounds bounds);
  }
  
  private Display display;
  // Corners of a polygon.
  private double[][] points = new double[][] {
      {-25.363882, 131.044922},
      {-25.363882, 125.244141},
      {-31.203405, 125.244141},
      {-31.203405, 131.044922}
  };
  private String presenterLink;
  private String viewLink;
  
  public PolygonSimplePresenter(Display display) {
    super();
    this.display = display;
    presenterLink = Constant.SOURCE_URL_PREFIX
        + this.getClass().getName().replace('.', '/') + ".java";
    viewLink = Constant.SOURCE_URL_PREFIX
        + display.getClass().getName().replace('.', '/') + ".java";
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.sample.client.presenter.Presenter#bind()
   */
  @Override
  public void bind() {
    display.setPresenterLink(presenterLink);
    display.setViewLink(viewLink);
    
    // Create bounds and fit map to it.
    final LatLng southWest = display.createLatLng(points[2][0], points[2][1]);
    final LatLng northEast = display.createLatLng(points[0][0], points[0][1]);
    final HasLatLngBounds bounds = display.createBounds(southWest, northEast);
    display.fitBounds(bounds);
    
    // Create polygon with given LatLngs(points).
    final Polygon polygon = new Polygon();
    final ArrayList<LatLng> path = new ArrayList<LatLng>();
    for (int i = 0; i < points.length; ++i) {
      path.add(display.createLatLng(points[i][0], points[i][1]));
    }
    polygon.setPath(path);
    // Overlay polygon on map.
    polygon.setMap(display.getMap());
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.sample.client.presenter.Presenter#getDisplay()
   */
  @Override
  public Display getDisplay() {
    return display;
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.sample.client.presenter.Presenter#getEventBus()
   */
  @Override
  public HandlerManager getEventBus() {
    return null;
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.sample.client.presenter.Presenter#release()
   */
  @Override
  public void release() {
    // No-op.
  }
}
