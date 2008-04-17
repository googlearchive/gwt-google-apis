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
package com.google.gwt.maps.client.impl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.jsio.client.BeanProperties;
import com.google.gwt.maps.jsio.client.Constructor;
import com.google.gwt.maps.jsio.client.JSFlyweightWrapper;

/**
 * Wrapper for the GMapOptions object from the Maps API using JSIO. Note that
 * there is no JavaScript constructor for this object. Instead, it is built from
 * an Object literal.
 */
@BeanProperties
public interface MapOptionsImpl extends JSFlyweightWrapper {

  MapOptionsImpl impl = GWT.create(MapOptionsImpl.class);

  @Constructor("Object")
  JavaScriptObject construct();

  void setDraggableCursor(JavaScriptObject jsoPeer, String cursor);

  void setDraggingCursor(JavaScriptObject jsoPeer, String cursor);
}
