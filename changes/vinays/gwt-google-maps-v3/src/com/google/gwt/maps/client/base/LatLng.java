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
 * This class implements {@link HasLatLng}.
 *
 */
public class LatLng extends JavaScriptObject {
    
  public static native LatLng newInstance(double lat, double lng) /*-{
    return new $wnd.google.maps.LatLng(lat, lng);
  }-*/;
  
  public static native LatLng newInstance(double lat, double lng, boolean noWrap) /*-{
    return new $wnd.google.maps.LatLng(lat, lng, noWrap);
  }-*/;
  
  protected LatLng() {}
  
  public final native double getLatitude() /*-{
    return this.lat();
  }-*/;
  
  public final native double getLongitude() /*-{
    return this.lng();
  }-*/;
  
  public final native String getString() /*-{
    return this.toString();
  }-*/;
  
  public final native String toUrlValue() /*-{
    return this.toUrlValue();
  }-*/;
  
  public final native String toUrlValue(int precision) /*-{
    return this.toUrlValue(precision);
  }-*/;
  
  public final native boolean equalsTo(LatLng other) /*-{
    this.equals(other);
  }-*/;

}
