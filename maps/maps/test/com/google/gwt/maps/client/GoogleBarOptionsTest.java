/*
 * Copyright 2009 Google Inc.
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

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.maps.client.GoogleBarAdsOptions.AdSafeOption;

/**
 * Tests for the MapOptions object.
 * 
 */
public class GoogleBarOptionsTest extends MapsTestCase {

  private static native boolean nativeTestAdsAdSafe(
      GoogleBarAdsOptions adsOptions, String value) /*-{
    return adsOptions.adsafe == value;
  }-*/;

  private static native boolean nativeTestAdsChannel(
      GoogleBarAdsOptions adsOptions, String value) /*-{
    return adsOptions.channel == value;
  }-*/;

  private static native boolean nativeTestAdsClient(
      GoogleBarAdsOptions adsOptions, String value) /*-{
    return adsOptions.client == value;
  }-*/;

  private static native boolean nativeTestAdsLanguage(
      GoogleBarAdsOptions adsOptions, String value) /*-{
    return adsOptions.language == value;
  }-*/;

  private static native boolean nativeTestAdsOptions(GoogleBarOptions options,
      GoogleBarAdsOptions adsOptions) /*-{
    return options.adsOptions === adsOptions;
  }-*/;

  private static native boolean nativeTestLinkTarget(GoogleBarOptions options,
      String target) /*-{
    return options.linkTarget == target;
  }-*/;

  private static native boolean nativeTestListingType(GoogleBarOptions options,
      String type) /*-{
    eval("var typeObj = $wnd." + type + ";");
    return options.listingType == typeObj;
  }-*/;

  private static native boolean nativeTestResultList(GoogleBarOptions options,
      String value) /*-{
    eval("var typeObj = $wnd." + value + ";");
    return options.resultList == typeObj;
  }-*/;

  private static native boolean nativeTestResultListElement(
      GoogleBarOptions options, Element element) /*-{
    return options.resultList === element;
  }-*/;

  private static native boolean nativeTestShowOnLoad(GoogleBarOptions options,
      boolean value) /*-{
    return options.showOnLoad == value;
  }-*/;

  private static native boolean nativeTestStyle(GoogleBarOptions options,
      String value) /*-{
    return options.style == value;
  }-*/;

  private static native boolean nativeTestSuppressInitialResultSelection(
      GoogleBarOptions options, boolean value) /*-{
    return options.suppressInitialResultSelection == value;
  }-*/;

  private static native boolean nativeTestSuppressZoomToBounds(
      GoogleBarOptions options, boolean value) /*-{
    return options.suppressZoomToBounds == value;
  }-*/;

  @Override
  public String getModuleName() {
    return "com.google.gwt.maps.GoogleMapsTest";
  }

  public void testGoogleBarAdsOptions() {
    loadApi(new Runnable() {
      public void run() {
        GoogleBarAdsOptions adsOptions = GoogleBarAdsOptions.newInstance();
        adsOptions.setAdSafe(AdSafeOption.ADSAFE_HIGH);
        assertTrue("ads adsafe", nativeTestAdsAdSafe(adsOptions, "high"));
        adsOptions.setAdSafe(AdSafeOption.ADSAFE_MEDIUM);
        assertTrue("ads adsafe", nativeTestAdsAdSafe(adsOptions, "medium"));
        adsOptions.setAdSafe(AdSafeOption.ADSAFE_LOW);
        assertTrue("ads adsafe", nativeTestAdsAdSafe(adsOptions, "low"));
        adsOptions.setAdSafe(AdSafeOption.ADSAFE_OFF);
        assertTrue("ads adsafe", nativeTestAdsAdSafe(adsOptions, "off"));
        adsOptions.setChannel("channel-1");
        assertTrue("ads channel", nativeTestAdsChannel(adsOptions, "channel-1"));
        adsOptions.setClient("testClient");
        assertTrue("ads channel", nativeTestAdsClient(adsOptions, "testClient"));
        adsOptions.setLanguage("de-DE");
        assertTrue("ads language", nativeTestAdsLanguage(adsOptions, "de-DE"));
      }
    });
  }

  public void testGoogleBarOptions() {
    loadApi(new Runnable() {
      public void run() {

        GoogleBarOptions options = GoogleBarOptions.newInstance();
        GoogleBarAdsOptions adsOptions = GoogleBarAdsOptions.newInstance();
        options.setAdsOptions(adsOptions);
        assertTrue("adsOptions", nativeTestAdsOptions(options, adsOptions));

        options.setLinkTargetBlank();
        assertTrue("linkTarget blank", nativeTestLinkTarget(options, "_blank"));
        options.setLinkTargetParent();
        assertTrue("linkTarget parent",
            nativeTestLinkTarget(options, "_parent"));
        options.setLinkTargetSelf();
        assertTrue("linkTarget self", nativeTestLinkTarget(options, "_self"));
        options.setLinkTargetTop();
        assertTrue("linkTarget top", nativeTestLinkTarget(options, "_top"));

        options.setListingTypeBlended();
        assertTrue("listingType blended", nativeTestListingType(options,
            "G_GOOGLEBAR_TYPE_BLENDED_RESULTS "));
        options.setListingTypeKmlOnly();
        assertTrue("listingType blended", nativeTestListingType(options,
            "G_GOOGLEBAR_TYPE_KMLONLY_RESULTS "));
        options.setListingTypeLocalOnly();
        assertTrue("listingType blended", nativeTestListingType(options,
            "G_GOOGLEBAR_TYPE_LOCALONLY_RESULTS "));

        Element elem = Document.get().createDivElement();
        elem.setId("testDivElement");
        options.setResultListElement(elem);
        assertTrue("resultList element", nativeTestResultListElement(options,
            elem));
        options.setResultListInline();
        assertTrue("resultList inline", nativeTestResultList(options,
            "G_GOOGLEBAR_RESULT_LIST_INLINE"));
        options.setResultListSuppress();
        assertTrue("resultList suppress", nativeTestResultList(options,
            "G_GOOGLEBAR_RESULT_LIST_SUPPRESS"));

        options.setShowOnLoad(true);
        assertTrue("showOnLoad", nativeTestShowOnLoad(options, true));
        options.setStyle("testStyle");
        assertTrue("style", nativeTestStyle(options, "testStyle"));
        options.setSuppressInitialResultSelection(true);
        assertTrue("suppressInitialResultSelection",
            nativeTestSuppressInitialResultSelection(options, true));
        options.setSuppressZoomToBounds(true);
        assertTrue("suppressZoomToBounds", nativeTestSuppressZoomToBounds(
            options, true));
      }
    });
  }
}
