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
package com.google.gwt.maps.client;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * A base class to support testing JavaScript Bean classes. Provides a set of
 * handy methods to minimize pain of testing.
 */
public class JsBeanTestCase extends MapsTestCase {

  public final native JavaScriptObject getJso(Object o, String property) /*-{
    return o[property];
  }-*/;

  public final native String getString(Object o, String property) /*-{
    return o[property];
  }-*/;

  public final native double getDouble(Object o, String property) /*-{
    return o[property];
  }-*/;

  public final native boolean getBoolean(Object o, String property) /*-{
    return o[property];
  }-*/;

  public final native void setProperty(Object o, String property,
      JavaScriptObject value) /*-{
    o[property] = value;
  }-*/;

  public final native void setProperty(Object o, String property, int value) /*-{
    o[property] = value;
  }-*/;

  public final native void setProperty(Object o, String property,
      String value) /*-{
    o[property] = value;
  }-*/;

  public final native void setProperty(Object o, String property,
      double value) /*-{
    o[property] = value;
  }-*/;
}
