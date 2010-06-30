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
 * Provides access to the Google Analytics feature.
 * 
 * @deprecated {@link GoogleAnalyticsFeature}
 */
@Deprecated
public class AnalyticsFeature implements GadgetFeature {
  private AnalyticsFeature() {
  }

  /**
   * Record a page view.
   * 
   * @param id the Google Analytics account ID
   * @param path the virtual page view path that should be recorded
   * 
   * @deprecated {@link GoogleAnalyticsFeature.Tracker#reportPageview(String)}
   */
  @Deprecated
  public native void recordPageView(String id, String path) /*-{
    $wnd._IG_Analytics(id, path);
  }-*/;
}