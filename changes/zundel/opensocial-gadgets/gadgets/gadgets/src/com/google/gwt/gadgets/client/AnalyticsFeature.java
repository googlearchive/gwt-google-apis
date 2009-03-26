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

import com.google.gwt.core.client.GWT;

/**
 * Provides access to the Google Analytics feature.
 *   spec should have: <Require feature="com.google.gadgets.analytics" /> 
 * TODO(zundel): see http://code.google.com/apis/analytics/docs/gadgetTracking.html
 */
public class AnalyticsFeature implements GadgetFeature {
  private AnalyticsFeature() {
  }

  /**
   * Record a page view.
   * 
   * @param id the Google Analytics account ID
   * @param path the virtual page view path that should be recorded
   */
  public void recordPageView(String id, String path) {
    // $wnd._IG_Analytics(id, path);
    GWT.log("Analytics not yet supported.", null);
  }
}