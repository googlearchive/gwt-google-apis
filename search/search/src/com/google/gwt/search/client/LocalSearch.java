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
package com.google.gwt.search.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.search.client.impl.GlocalSearch;

/**
 * A Google local search.
 */
public class LocalSearch extends Search {
  private static final GlocalSearch IMPL = GlocalSearch.IMPL;

  public LocalSearch() {
    super(IMPL);
  }

  /**
   * The default behavior of this searcher is to co-mingle address lookup
   * results (e.g., NY, NY) with local search results. There are situations
   * where this co-mingled approach is not the desired behavior. For instance,
   * suppose the searcher is centered in Santa Barbara, CA and the user is
   * searching for "Cava". With co-mingled results, the first search result is
   * actually an address match against "Cava Close, Aberdeen City, AB15 UK". The
   * second result is "Cava Restaurant &amp; Bar". Using this method,
   * applications are able to disable and enable address lookup producing either
   * strictly search results, or address lookup results co-mingled with search
   * results. In this case, if address lookup is disabled, the first result
   * would be "Cava Restaurant &amp; Bar".
   * 
   * @param mode Supplies the desired address lookup mode.
   */
  public void setAddressLookupMode(AddressLookupMode mode) {
    IMPL.setAddressLookupMode(this, mode.getValue());
  }

  /**
   * This method establishes a center point that is used to scope search
   * results. You can pass a GLatLng or GMap2 object created by the Google Maps API.
   * If you are using the gwt-maps project, pass an instance of the LatLng class.
   * 
   * @param point Supply either a GLatLng object or a GMap2 object from the
   *          Google Maps API.  
   */
  public void setCenterPoint(JavaScriptObject point) {
    IMPL.setCenterPoint(this, point);
  }

  /**
   * This method establishes a center point that is used to scope search
   * results.
   * 
   * @param point a string which specifies an address by name. When this
   *          argument is presented, searches are scoped as if the user
   *          performed a Google Local Search specifying near location as part
   *          of their search expression. Note:, the string location is resolved
   *          into a Google Maps LatLng asynchronously so if this method is
   *          used, searches are made relative to this location once the
   *          geocoding operation completes with success.
   */
  public void setCenterPoint(String point) {
    IMPL.setCenterPoint(this, point);
  }
}
