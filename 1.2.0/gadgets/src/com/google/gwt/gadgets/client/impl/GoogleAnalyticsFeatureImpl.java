/*
 * Copyright 2010 Google Inc.
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
package com.google.gwt.gadgets.client.impl;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.gadgets.client.GoogleAnalyticsFeature;

/**
 * Provides access to the Google Analytics feature.
 */
public class GoogleAnalyticsFeatureImpl implements GoogleAnalyticsFeature {

  /**
   * Provides a way of reporting event and page views.
   */
  public static class TrackerImpl extends JavaScriptObject implements Tracker {

    /**
     * Required protected constructor for JavaScriptObject subclass.
     */
    protected TrackerImpl() {
    }

    public final native void reportEvent(String eventName, String action) /*-{
      this.reportEvent(eventName, action);
    }-*/;

    public final native void reportEvent(String eventName, String action,
        String label) /*-{
      this.reportEvent(eventName, action, label);
    }-*/;

    public final native void reportEvent(String eventName, String action,
        String label, int value) /*-{
      this.reportEvent(eventName, action, label, value);
    }-*/;

    public final native void reportPageview(String path) /*-{
      this.reportPageview(path);
    }-*/;
  }

  private GoogleAnalyticsFeatureImpl() {
  }

  public native TrackerImpl createTracker(String domainId) /*-{
    return new $wnd._IG_GA(domainId);
  }-*/;
}