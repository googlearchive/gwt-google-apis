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
import com.google.gwt.jsio.client.BeanProperties;
import com.google.gwt.jsio.client.FieldName;
import com.google.gwt.jsio.client.Global;
import com.google.gwt.jsio.client.JSFlyweightWrapper;
import com.google.gwt.jsio.client.ReadOnly;

/**
 * Provides access to the GGeoStatusCode constants.
 */
@ReadOnly
@BeanProperties
public interface StatusCodesImpl extends JSFlyweightWrapper {
  StatusCodesImpl impl = GWT.create(StatusCodesImpl.class);
  JavaScriptObject obj = impl.construct();
  
  @Global("$wnd")
  JavaScriptObject construct();
  
  @FieldName("G_GEO_BAD_KEY")
  int getBadKeyCode(JavaScriptObject o);
  
  @FieldName("G_GEO_BAD_REQUEST")
  int getBadRequestCode(JavaScriptObject o);
  
  @FieldName("G_GEO_MISSING_ADDRESS")
  int getMissingAddressCode(JavaScriptObject o);
  
  @FieldName("G_GEO_MISSING_QUERY")
  int getMissingQueryCode(JavaScriptObject o);
  
  @FieldName("G_GEO_SERVER_ERROR")
  int getServerErrorCode(JavaScriptObject o);
  
  @FieldName("G_GEO_SUCCESS")
  int getSuccessCode(JavaScriptObject o);
  
  @FieldName("G_GEO_TOO_MANY_QUERIES")
  int getTooManyQueriesCode(JavaScriptObject o);
  
  @FieldName("G_GEO_UNAVAILABLE_ADDRESS")
  int getUnavailableAddressCode(JavaScriptObject o);
  
  @FieldName("G_GEO_UNKNOWN_ADDRESS")
  int getUnknownAddressCode(JavaScriptObject o);
  
  @FieldName("G_GEO_UNKNOWN_DIRECTIONS")
  int getUnknownDirectionsCode(JavaScriptObject o);
}
