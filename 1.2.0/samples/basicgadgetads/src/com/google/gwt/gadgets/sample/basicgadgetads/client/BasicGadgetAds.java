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
package com.google.gwt.gadgets.sample.basicgadgetads.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.gadgets.client.AdsFeature;
import com.google.gwt.gadgets.client.AdsUserPreferences;
import com.google.gwt.gadgets.client.GadgetAds;
import com.google.gwt.gadgets.client.Gadget.AllowHtmlQuirksMode;
import com.google.gwt.gadgets.client.Gadget.InjectContent;
import com.google.gwt.gadgets.client.Gadget.ModulePrefs;
import com.google.gwt.gadgets.client.Gadget.UseLongManifestName;
import com.google.gwt.gadgets.client.ui.GadgetImage;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Basic Gadget Ads.
 */
@ModulePrefs(//
title = "Basic Gadget Ads using GWT!", //
author = "Nicolas Garnier", //
author_email = "nivco+gadgetads@google.com", //
author_affiliation = "Google Inc.", //
// The Gadget Ad size must be defined and must match a supported Google
// AdSense Ad Format.
height = GadgetAds.SupportedGoogleAdSenseAdFormats.SKYSCRAPER_HEIGHT, //
width = GadgetAds.SupportedGoogleAdSenseAdFormats.SKYSCRAPER_WIDTH)
// Adding a dummy call to an Ad interaction JavaScript function in the Gadget
// Spec because the Gadget Ads Validator doesn't inspect the external
// JavaScript files.
@InjectContent(files = {"dummyAdsInteraction.xml"})
// Create a short manifest name (instead of prepending the package prefix)
@UseLongManifestName(false)
@AllowHtmlQuirksMode(false)
public class BasicGadgetAds extends GadgetAds<AdsUserPreferences> {

  // The AdsFeature is required in Gadget Ads
  private AdsFeature adsFeature;

  public void initializeFeature(AdsFeature adsFeature) {
    this.adsFeature = adsFeature;
  }

  @Override
  protected void init(AdsUserPreferences preferences) {

    // Creating the Panel
    VerticalPanel vp = new VerticalPanel();
    RootPanel.get().add(vp);

    // Setting style and alignment
    vp.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
    vp.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

    // Using GadgetImage instead of Image will cache your images on the
    // Gadget's Container server. It is a Gadget Ads requirement that all
    // embedded assets must be cached.
    vp.add(new GadgetImage(
        "http://code.google.com/webtoolkit/images/gwt-logo.png"));

    // Adding a waiting message to the RootPanel
    Label disclaimer = new Label("Use GWT for your Gadget Ads!");
    vp.add(disclaimer);

    // Another Logo
    vp.add(new GadgetImage(
        "http://code.google.com/webtoolkit/images/gwt-logo.png"));

    // Adding the button to go to the GWT website
    Button button = new Button("Visit the GWT Website NOW!!");
    vp.add(button);

    // When clicking the button, we are redirected to the GWT website.
    // We use the AdsFeature.clickDestinationUrl() which will redirect to the
    // URL and report the clickthrough hit.
    // It is a GadgetAds requirement to use some AdsFeature.reportInteraction()
    // or AdsFeature.clickDestinationUrl()
    button.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        adsFeature.clickDestinationUrl("http://code.google.com/webtoolkit/");
      }
    });

    // Another Logo
    vp.add(new GadgetImage(
        "http://code.google.com/webtoolkit/images/gwt-logo.png"));

    // Some styling
    vp.setHeight("100%");
    vp.setWidth("100%");
    button.setWidth("80%");
    button.getElement().getStyle().setProperty("cursor", "pointer");
    button.getElement().getStyle().setPropertyPx("fontSize", 15);
    button.getElement().getStyle().setProperty("fontWeight", "bold");
    button.getElement().getStyle().setPropertyPx("padding", 5);
  }
}
