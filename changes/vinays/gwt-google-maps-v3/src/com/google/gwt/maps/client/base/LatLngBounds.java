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
package com.google.gwt.maps.client.base;

import com.google.gwt.core.client.JavaScriptObject;


/**
 * A LatLngBounds instance represents a rectangle in geographical coordinates,
 * including one that crosses the 180 degrees longitudinal meridian.
 */
public class LatLngBounds extends JavaScriptObject {
  
  public static native final LatLngBounds newInstance() /*-{
    return new $wnd.google.maps.LatLngBounds();
  }-*/;
  
  public static native final LatLngBounds newInstance(LatLng sw, LatLng ne) /*-{
    return new $wnd.google.maps.LatLngBounds(sw, ne);
  }-*/;
  
  protected LatLngBounds() {}
  
  public native final boolean contains(LatLng point) /*-{
    return this.contains(point);
  }-*/;

  public native final boolean equalsTo(LatLngBounds other) /*-{
    return this.equals(other);
  }-*/;

  public native final LatLngBounds extend(LatLng point) /*-{
    return this.extend(point);
  }-*/;

  public native final LatLng getCenter() /*-{
    return this.getCenter();
  }-*/;

  public native final LatLng getNorthEast() /*-{
    return this.getNorthEast();
  }-*/;

  public native final LatLng getSouthWest() /*-{
    return this.getSouthWest();
  }-*/;

  public native final boolean intersects(LatLngBounds other) /*-{
    return this.intersects(other);
  }-*/;

  public native final boolean isEmpty() /*-{
    return this.isEmpty();
  }-*/;

  public native final LatLng toSpan() /*-{
    return this.toSpan();
  }-*/;
  
  public native final String getString() /*-{
    return this.toString();
  }-*/;

  public native final String toUrlValue() /*-{
    return this.toUrlValue();
  }-*/;

  public native final String toUrlValue(int precision) /*-{
    return this.toUrlValue(precision);
  }-*/;

  public native final LatLngBounds union(LatLngBounds other) /*-{
    return this.union(other);
  }-*/;

}
