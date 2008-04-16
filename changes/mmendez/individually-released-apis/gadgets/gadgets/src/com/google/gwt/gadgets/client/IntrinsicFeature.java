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
package com.google.gwt.gadgets.client;

/**
 * Provides access to intrinsic APIs provided by the container that are not part
 * of a feature-specific API.
 */
public class IntrinsicFeature implements GadgetFeature {
  private IntrinsicFeature() {
  }

  /**
   * Returns a proxy URL that can be used to access a given URL.
   */
  public native String getCachedUrl(String url) /*-{
    return $wnd._IG_GetCachedUrl(url);
  }-*/;

  /**
   * Returns a proxy URL that can be used to access a given URL with a specified
   * refresh interval specified in seconds.
   */
  public native String getCachedUrl(String url, int refreshIntervalSeconds) /*-{
    return $wnd._IG_GetCachedUrl(url, refreshInterval);
  }-*/;
}