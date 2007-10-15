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
package com.google.gwt.maps.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.impl.MapTypeOptionsImpl;

/**
 * 
 */
public final class MapTypeOptions {

  private final JavaScriptObject jsoPeer;

  public MapTypeOptions() {
    jsoPeer = MapTypeOptionsImpl.impl.construct();
  }

  public void setAlt(String alt) {
    MapTypeOptionsImpl.impl.setAlt(jsoPeer, alt);
  }

  public void setErrorMessage(String errorMessage) {
    MapTypeOptionsImpl.impl.setErrorMessage(jsoPeer, errorMessage);
  }

  public void setLinkColor(String linkColor) {
    MapTypeOptionsImpl.impl.setLinkColor(jsoPeer, linkColor);
  }

  public void setMaxResolution(int maxResolution) {
    MapTypeOptionsImpl.impl.setMaxResolution(jsoPeer, maxResolution);
  }

  public void setMinResolution(int minResolution) {
    MapTypeOptionsImpl.impl.setMinResolution(jsoPeer, minResolution);
  }

  public void setShortName(String shortName) {
    MapTypeOptionsImpl.impl.setShortName(jsoPeer, shortName);
  }

  public void setTileColor(String tileColor) {
    MapTypeOptionsImpl.impl.setTileColor(jsoPeer, tileColor);
  }

  public void setTileSize(int tileSize) {
    MapTypeOptionsImpl.impl.setTileSize(jsoPeer, tileSize);
  }

  public void setUrlArg(String urlArg) {
    MapTypeOptionsImpl.impl.setUrlArg(jsoPeer, urlArg);
  }

}
