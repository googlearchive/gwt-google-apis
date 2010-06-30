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

import com.google.gwt.gadgets.client.AdsFeature;

/**
 * Provides access to the Ads APIs provided by the container.
 */
public class AdsFeatureImpl implements AdsFeature {

  private AdsFeatureImpl() {
  }

  public native void clickDestinationUrl(String destUrl) /*-{
    $wnd._ADS_ClickDestinationUrl(destUrl);
  }-*/;

  public native void reportInteraction() /*-{
    $wnd._ADS_ReportInteraction();
  }-*/;

  public native void reportInteraction(String name) /*-{
    $wnd._ADS_ReportInteraction(name);
  }-*/;

  public native void reportInteraction(String name, String value) /*-{
    $wnd._ADS_ReportInteraction(name, value);
  }-*/;

  public native void reportInteractionClick(String destUrl, String name,
      String value) /*-{
    $wnd._ADS_ReportInteractionClick(destUrl, name, value);
  }-*/;
}
