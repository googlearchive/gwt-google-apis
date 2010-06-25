/*
 * Copyright 2010 Google Inc.
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
package com.google.gwt.gadgets.sample.traveler.client;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Location class used by client-side part of the application. Represent a
 * single geographical location with it's description.
 */
public class Location extends JavaScriptObject {
  public static Location newInstance() {
    return JavaScriptObject.createObject().cast();
  }

  protected Location() {
  }

  public final native String getDescription() /*-{
    return (this.description == null) ? null : this.description;
  }-*/;

  public final native String getKey() /*-{
    return (this.key == null) ? null : this.key;
  }-*/;

  public final native String getDate() /*-{
    return (this.date == null) ? null : this.date;
  }-*/;

  public final native double getLatitude() /*-{
    return (this.latitude == null) ? 0 : this.latitude;
  }-*/;

  public final native double getLongitude() /*-{
    return (this.longitude == null) ? 0 : this.longitude;
  }-*/;

  public final native String getTitle() /*-{
    return (this.title == null) ? null : this.title;
  }-*/;

  public final native Location setDescription(String description) /*-{
    this.description = description;
    return this;
  }-*/;

  public final native Location setLatitude(double latitude) /*-{
    this.latitude = latitude;
    return this;
  }-*/;

  public final native Location setMilis(String milis) /*-{
    this.milis = milis;
    return this;
  }-*/;

  public final native Location setLongitude(double longitude) /*-{
    this.longitude = longitude;
    return this;
  }-*/;

  public final native Location setTitle(String title) /*-{
    this.title = title;
    return this;
  }-*/;
}
