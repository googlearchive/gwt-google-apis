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
package com.google.gwt.maps.client.overlay;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayInteger;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.MapsTestCase;
import com.google.gwt.maps.client.TestUtilities;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.geom.Size;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Tests the Marker class.
 */
public class IconTest extends MapsTestCase {
  @Override
  public String getModuleName() {
    return "com.google.gwt.maps.GoogleMapsTest";
  }

  /**
   * Runs before each test method.
   */
  @Override
  public void gwtSetUp() {
    TestUtilities.cleanDom();
  }

  /**
   * Test Icon getters and setters.
   */
  public void testIconAccessors() {
    loadApi(new Runnable() {
      public void run() {
        Icon ic = Icon.newInstance();

        Point pointA = Point.newInstance(10, 20);
        ic.setDragCrossAnchor(pointA);
        assertEquals("DragCrossAnchor", pointA, ic.getDragCrossAnchor());

        String dragCrossImage = "DragCrossImage";
        ic.setDragCrossImageURL(dragCrossImage);
        assertEquals("DragCrossImageURL", dragCrossImage,
            ic.getDragCrossImageUrl());

        Size size1 = Size.newInstance(20, 20);
        ic.setDragCrossSize(size1);
        assertEquals("DragCrossSize", size1, ic.getDragCrossSize());

        Point pointB = Point.newInstance(20, 30);
        ic.setIconAnchor(pointB);
        assertEquals("IconAnchor", pointB, ic.getIconAnchor());

        Size size2 = Size.newInstance(2, 2);
        ic.setIconSize(size2);
        assertEquals("IconSize", size2, ic.getIconSize());

        int[] map1 = {1, 2, 3, 4};
        ic.setImageMap(map1);
        assertEquals("ImageMap length", map1.length, ic.getImageMap().length());
        for (int i = 0; i < map1.length; i++) {
          assertEquals("ImageMap", map1[i], ic.getImageMapArray()[i]);
        }

        JsArrayInteger map2 = JavaScriptObject.createArray().cast();
        map2.set(0, 4);
        map2.set(1, 5);
        map2.set(2, 6);
        map2.set(3, 7);
        ic.setImageMap(map2);
        for (int i = 0; i < map2.length(); i++) {
          assertEquals("ImageMap", map2.get(i), ic.getImageMap().get(i));
        }

        String dummyImage = "DummyImage";
        ic.setImageURL(dummyImage);
        assertEquals("ImageURL", dummyImage, ic.getImageURL());

        Point pointC = Point.newInstance(100, 20);
        ic.setInfoWindowAnchor(pointC);
        assertEquals("InfoWindowAnchor", pointC, ic.getInfoWindowAnchor());

        ic.setMaxHeight(10);
        assertEquals("MaxHeight", 10, ic.getMaxHeight());

        dummyImage = "DummyMozPrintImage";
        ic.setMozPrintImageURL(dummyImage);
        assertEquals("MozPrintImageURL", dummyImage, ic.getMozPrintImageURL());

        dummyImage = "PrintImage";
        ic.setPrintImageURL(dummyImage);
        assertEquals("PrintImageURL", dummyImage, ic.getPrintImageURL());

        Size size3 = Size.newInstance(12, 34);
        ic.setShadowSize(size3);
        assertEquals("ShadowSize", size3, ic.getShadowSize());

        String dummyShadowURL = "DummyShadowURL";
        ic.setShadowURL(dummyShadowURL);
        assertEquals("Shadow URL", dummyShadowURL, ic.getShadowURL());

        String transparentUrl = "TransparentURL";
        ic.setTransparentImageURL(transparentUrl);
        assertEquals("TransarentURL", transparentUrl,
            ic.getTransparentImageURL());
      }
    });
  }

  /**
   * Test the default Icon() constructor.
   */
  public void testIconDefaultConstructor() {
    loadApi(new Runnable() {
      public void run() {
        LatLng atlanta = LatLng.newInstance(33.7814790, -84.3880580);
        final MapWidget map = new MapWidget(atlanta, 13);
        map.setSize("300px", "300px");

        Icon ic = Icon.newInstance();
        ic.setImageURL("house.png");
        MarkerOptions mo = MarkerOptions.newInstance();
        mo.setIcon(ic);
        Marker m = new Marker(LatLng.newInstance(33.7814790, -84.3880580), mo);
        map.addOverlay(m);
        RootPanel.get().add(map);
      }
    });
  }

  /**
   * Test the Icon(Icon) constructor.
   */
  public void testIconFromDefault() {
    loadApi(new Runnable() {
      public void run() {

        LatLng atlanta = LatLng.newInstance(33.7814790, -84.3880580);
        final MapWidget map = new MapWidget(atlanta, 13);
        map.setSize("300px", "300px");

        Icon ic = Icon.newInstance(Icon.getDefaultIcon());
        ic.setIconSize(Size.newInstance(30, 30));
        MarkerOptions mo = MarkerOptions.newInstance();
        mo.setIcon(ic);
        Marker m = new Marker(LatLng.newInstance(33.7814790, -84.3880580), mo);
        map.addOverlay(m);
        RootPanel.get().add(map);
      }
    });
  }

  /**
   * Test the Icon(String) constructor.
   */
  public void testIconFromURL() {
    loadApi(new Runnable() {
      public void run() {
        LatLng atlanta = LatLng.newInstance(33.7814790, -84.3880580);
        final MapWidget map = new MapWidget(atlanta, 13);
        map.setSize("300px", "300px");

        Icon ic = Icon.newInstance("house.png");
        ic.setIconSize(Size.newInstance(30, 30));
        MarkerOptions mo = MarkerOptions.newInstance();
        mo.setIcon(ic);
        Marker m = new Marker(LatLng.newInstance(33.7814790, -84.3880580), mo);
        map.addOverlay(m);
        RootPanel.get().add(map);
      }
    });
  }

}
