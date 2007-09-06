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
package com.google.gwt.maps.client.impl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.jsio.client.JSFlyweightWrapper;

/**
 * @gwt.beanProperties
 */
public interface MapTypeOptionsImpl extends JSFlyweightWrapper {

  public static final MapTypeOptionsImpl impl = (MapTypeOptionsImpl) GWT.create(MapTypeOptionsImpl.class);

  /**
   * @gwt.constructor Object
   */
  public JavaScriptObject construct();

  public void setAlt(JavaScriptObject jsoPeer, String alt);

  public void setErrorMessage(JavaScriptObject jsoPeer, String errorMessage);

  public void setLinkColor(JavaScriptObject jsoPeer, String linkColor);

  public void setMaxResolution(JavaScriptObject jsoPeer, int maxResolution);

  public void setMinResolution(JavaScriptObject jsoPeer, int minResolution);

  public void setShortName(JavaScriptObject jsoPeer, String shortName);

  public void setTileColor(JavaScriptObject jsoPeer, String tileColor);

  public void setTileSize(JavaScriptObject jsoPeer, int tileSize);

  public void setUrlArg(JavaScriptObject jsoPeer, String urlArg);
}
