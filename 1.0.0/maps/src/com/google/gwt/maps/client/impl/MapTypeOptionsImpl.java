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
 * This class corresponds to the GMapTypeOptions class in the Maps JavaScript
 * API.
 */
@BeanProperties
public interface MapTypeOptionsImpl extends JSFlyweightWrapper {

  MapTypeOptionsImpl impl = GWT.create(MapTypeOptionsImpl.class);

  @Constructor("Object")
  JavaScriptObject construct();

  void setAlt(JavaScriptObject jsoPeer, String alt);

  void setErrorMessage(JavaScriptObject jsoPeer, String errorMessage);

  void setLinkColor(JavaScriptObject jsoPeer, String linkColor);

  void setMaxResolution(JavaScriptObject jsoPeer, int maxResolution);

  void setMinResolution(JavaScriptObject jsoPeer, int minResolution);

  void setRadius(JavaScriptObject jsoPeer, int radius);

  void setShortName(JavaScriptObject jsoPeer, String shortName);

  void setTextColor(JavaScriptObject jsoPeer, String tileColor);

  void setTileSize(JavaScriptObject jsoPeer, int tileSize);

  void setUrlArg(JavaScriptObject jsoPeer, String urlArg);
}
