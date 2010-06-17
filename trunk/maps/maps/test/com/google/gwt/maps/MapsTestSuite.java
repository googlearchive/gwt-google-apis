/*
 * Copyright 2008 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.gwt.maps;

import com.google.gwt.junit.tools.GWTTestSuite;
import com.google.gwt.maps.client.AdsManagerTest;
import com.google.gwt.maps.client.CopyrightEventTest;
import com.google.gwt.maps.client.DraggableObjectTest;
import com.google.gwt.maps.client.GoogleBarOptionsTest;
import com.google.gwt.maps.client.InfoWindowEventsTest;
import com.google.gwt.maps.client.MapOptionsTest;
import com.google.gwt.maps.client.MapPaneTypeTest;
import com.google.gwt.maps.client.MapUIOptionsTest;
import com.google.gwt.maps.client.MapWidgetEventsTest;
import com.google.gwt.maps.client.MapWidgetTest;
import com.google.gwt.maps.client.MapsNotInstalledTest;
import com.google.gwt.maps.client.control.ControlTest;
import com.google.gwt.maps.client.geocode.DirectionsTest;
import com.google.gwt.maps.client.geocode.GeocodeTest;
import com.google.gwt.maps.client.geocode.StatusCodesTest;
import com.google.gwt.maps.client.geom.BoundsTest;
import com.google.gwt.maps.client.geom.LatLngBoundsTest;
import com.google.gwt.maps.client.geom.LatLngTest;
import com.google.gwt.maps.client.geom.ProjectionTest;
import com.google.gwt.maps.client.impl.MinimumMapVersionTest;
import com.google.gwt.maps.client.overlay.GeoXmlOverlayTest;
import com.google.gwt.maps.client.overlay.IconTest;
import com.google.gwt.maps.client.overlay.MarkerEventsTest;
import com.google.gwt.maps.client.overlay.MarkerTest;
import com.google.gwt.maps.client.overlay.OverlayTest;
import com.google.gwt.maps.client.overlay.PolygonEventsTest;
import com.google.gwt.maps.client.overlay.PolygonTest;
import com.google.gwt.maps.client.overlay.PolylineEventsTest;
import com.google.gwt.maps.client.overlay.PolylineTest;
import com.google.gwt.maps.client.overlay.TrafficOverlayTest;
import com.google.gwt.maps.client.streetview.PhotoSpecTest;
import com.google.gwt.maps.client.streetview.PovTest;
import com.google.gwt.maps.client.streetview.StreetviewClientTest;
import com.google.gwt.maps.client.streetview.StreetviewDataTest;
import com.google.gwt.maps.client.streetview.StreetviewEventRemoversTest;
import com.google.gwt.maps.client.streetview.StreetviewFeaturesTest;
import com.google.gwt.maps.client.streetview.StreetviewLinkTest;
import com.google.gwt.maps.client.streetview.StreetviewLocationTest;
import com.google.gwt.maps.client.streetview.StreetviewPanoramaOptionsTest;
import com.google.gwt.maps.client.streetview.StreetviewPanoramaWidgetTest;
import com.google.gwt.maps.client.streetview.StreetviewPanoramaWidgetTest2;
import com.google.gwt.maps.client.streetview.StreetviewUserPhotoOptionsTest;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * TestSuite for the Maps API.
 */
public class MapsTestSuite extends GWTTestSuite {
  public static Test suite() {
    TestSuite suite = new TestSuite("Test for the Maps API");

    // com.google.gwt.maps.client tests
    suite.addTestSuite(AdsManagerTest.class);
    suite.addTestSuite(CopyrightEventTest.class);
    suite.addTestSuite(DraggableObjectTest.class);
    suite.addTestSuite(GoogleBarOptionsTest.class);
    suite.addTestSuite(InfoWindowEventsTest.class);
    suite.addTestSuite(MapOptionsTest.class);
    suite.addTestSuite(MapPaneTypeTest.class);
    // Intentionally omitting MapsAjaxLoaderTest - run manually
    suite.addTestSuite(MapsNotInstalledTest.class);
    suite.addTestSuite(MapUIOptionsTest.class);
    suite.addTestSuite(MapWidgetEventsTest.class);
    suite.addTestSuite(MapWidgetTest.class);

    // com.google.gwt.maps.client.control tests
    suite.addTestSuite(ControlTest.class);

    // com.google.gwt.maps.client.geocode tests
    suite.addTestSuite(DirectionsTest.class);
    suite.addTestSuite(GeocodeTest.class);
    suite.addTestSuite(StatusCodesTest.class);

    // com.google.gwt.maps.client.geom tests
    suite.addTestSuite(BoundsTest.class);
    suite.addTestSuite(LatLngBoundsTest.class);
    suite.addTestSuite(LatLngTest.class);
    suite.addTestSuite(ProjectionTest.class);

    // com.google.gwt.maps.client.impl
    suite.addTestSuite(MinimumMapVersionTest.class);

    // com.google.gwt.maps.client.overlay
    suite.addTestSuite(GeoXmlOverlayTest.class);
    suite.addTestSuite(IconTest.class);
    suite.addTestSuite(MarkerEventsTest.class);
    suite.addTestSuite(MarkerTest.class);
    suite.addTestSuite(OverlayTest.class);
    suite.addTestSuite(PolygonEventsTest.class);
    suite.addTestSuite(PolygonTest.class);
    suite.addTestSuite(PolylineEventsTest.class);
    suite.addTestSuite(PolylineTest.class);
    suite.addTestSuite(TrafficOverlayTest.class);

    // com.google.gwt.maps.client.streetview
    suite.addTestSuite(PhotoSpecTest.class);
    suite.addTestSuite(PovTest.class);
    suite.addTestSuite(StreetviewClientTest.class);
    suite.addTestSuite(StreetviewDataTest.class);
    suite.addTestSuite(StreetviewFeaturesTest.class);
    suite.addTestSuite(StreetviewLinkTest.class);
    suite.addTestSuite(StreetviewLocationTest.class);
    suite.addTestSuite(StreetviewPanoramaOptionsTest.class);
    suite.addTestSuite(StreetviewUserPhotoOptionsTest.class);

    // These tests are running as different modules.
    // They are last to minimize the module-switching time.
    suite.addTestSuite(StreetviewPanoramaWidgetTest.class);
    suite.addTestSuite(StreetviewPanoramaWidgetTest2.class);
    suite.addTestSuite(StreetviewEventRemoversTest.class);

    return suite;
  }
}
