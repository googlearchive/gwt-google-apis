/*
 * Copyright 2007 Google Inc.
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
package com.google.gwt.maps.client.util;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.jsio.client.JSFlyweightWrapper;
import com.google.gwt.jsio.client.JSList;
import com.google.gwt.maps.client.TileLayer;
import com.google.gwt.maps.client.InfoWindowContent.InfoWindowTab;
import com.google.gwt.maps.client.geocode.Placemark;
import com.google.gwt.maps.client.geocode.Waypoint;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.overlay.Marker;

import java.util.Arrays;

/**
 * This class provides utilities to aid in GWT-JavaScript interoperability.
 * 
 * This is not part of the public API and may change or be removed from future
 * versions.
 * 
 * 
 * TODO: I'd like to see the functionality provided by this class incorporated
 * into JSIO (gwt-api-interop).
 */
public final class JsUtil {

  static interface ListGenerator extends JSFlyweightWrapper {
    /**
     * @gwt.typeArgs <com.google.gwt.maps.client.InfoWindowContent.InfoWindowTab>
     * @gwt.fieldName valueOf
     */
    public JSList asInfoWindowTabList(JavaScriptObject jso);

    /**
     * @gwt.typeArgs <java.lang.Integer>
     * @gwt.fieldName valueOf
     */
    public JSList asIntegerList(JavaScriptObject jso);

    /**
     * @gwt.typeArgs <com.google.gwt.maps.client.geom.LatLng>
     * @gwt.fieldName valueOf
     */
    public JSList asLatLngList(JavaScriptObject jso);

    /**
     * @gwt.typeArgs <com.google.gwt.maps.client.overlay.Marker>
     * @gwt.fieldName valueOf
     */
    public JSList asMarkerList(JavaScriptObject jso);

    /**
     * @gwt.typeArgs <com.google.gwt.maps.client.geom.Point>
     * @gwt.fieldName valueOf
     */
    public JSList asPointList(JavaScriptObject jso);

    /**
     * @gwt.typeArgs <com.google.gwt.maps.client.TileLayer>
     * @gwt.fieldName valueOf
     */
    public JSList asTileLayerList(JavaScriptObject jso);

    /**
     * @gwt.typeArgs <com.google.gwt.maps.client.geocode.Waypoint>
     * @gwt.fieldName valueOf
     */
    public JSList asWaypointList(JavaScriptObject jso);

    /**
     * @gwt.constructor Array
     */
    public JavaScriptObject newArray();
  }

  private static final ListGenerator lists = (ListGenerator) GWT.create(ListGenerator.class);

  public static native JavaScriptObject asJavaScriptObject(LatLng latlng) /*-{
    return latlng.@com.google.gwt.maps.client.geom.LatLng::jsoPeer;
  }-*/;

  public static native JavaScriptObject asJavaScriptObject(Placemark placemark) /*-{
    return placemark.@com.google.gwt.maps.client.geocode.Placemark::jsoPeer;
  }-*/;

  public static native JavaScriptObject asJavaScriptObject(String str) /*-{
    return new String(str);
  }-*/;

  public static void toArray(JSList list, int[] array) {
    for (int i = 0; i < array.length; i++) {
      array[i] = ((Integer) list.get(i)).intValue();
    }
  }

  public static void toArray(JSList list, Object[] array) {
    for (int i = 0; i < array.length; i++) {
      array[i] = list.get(i);
    }
  }

  public static JSList toJsList(InfoWindowTab[] array) {
    JSList list = lists.asInfoWindowTabList(lists.newArray());
    list.addAll(Arrays.asList(array));
    return list;
  }

  public static JSList toJsList(int[] array) {
    JSList list = lists.asTileLayerList(lists.newArray());
    for (int i = 0; i < array.length; i++) {
      list.add(new Integer(array[i]));
    }
    return list;
  }

  public static JSList toJsList(LatLng[] array) {
    JSList list = lists.asLatLngList(lists.newArray());
    list.addAll(Arrays.asList(array));
    return list;
  }

  public static JSList toJsList(Marker[] array) {
    JSList list = lists.asMarkerList(lists.newArray());
    list.addAll(Arrays.asList(array));
    return list;
  }

  public static JSList toJsList(Point[] array) {
    JSList list = lists.asPointList(lists.newArray());
    list.addAll(Arrays.asList(array));
    return list;
  }

  public static JSList toJsList(TileLayer[] array) {
    JSList list = lists.asTileLayerList(lists.newArray());
    list.addAll(Arrays.asList(array));
    return list;
  }

  public static JSList toJsList(Waypoint[] array) {
    JSList list = lists.asWaypointList(lists.newArray());
    list.addAll(Arrays.asList(array));
    return list;
  }

  private JsUtil() {
  }

}
