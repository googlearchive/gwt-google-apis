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
package com.google.gwt.maps.client.overlay;

/**
 * Callback used by the load() factory method of GeoXmlOverlay.
 */
public abstract class GeoXmlLoadCallback {
  /**
   * The call to create a new overlay failed.
   * @param url The URL that was requested
   */
  public abstract void onFailure(String url, Throwable caught);

  /**
   * The call to create the object succeeded.
   * @param url The URL that was requested
   * @param overlay a newly constructed GeoXmlOverlay object.
   */
  public abstract void onSuccess(String url, GeoXmlOverlay overlay);
}
