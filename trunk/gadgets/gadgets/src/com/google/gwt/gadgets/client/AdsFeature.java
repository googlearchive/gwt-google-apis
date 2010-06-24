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
package com.google.gwt.gadgets.client;

/**
 * Provides access to the Ads APIs provided by the container.
 */
public interface AdsFeature {

  /**
   * Reports a clickthrough and redirect to the destination URL.
   *
   * <p>
   * In order to enable clickthrough tracking on this link, you must use this
   * method to initiate the redirect. When this method is called, a clickthrough
   * hit is received and then redirected to the destination.
   *
   * @param destUrl Final destination URL you want to report and be redirected
   *          to
   */
  void clickDestinationUrl(String destUrl);

  /**
   * Reports a single user-interaction.
   *
   * <p>
   * Use this when your gadget contains only a single user-interaction that
   * needs to be tracked. For example, this could be a click-to-play button
   * which starts video playback or a start button to load in a Flash game. Each
   * time this function is called, a generic hit is sent to the tracking server.
   * These hits are grouped into "Primary Interactions" in the interaction
   * reports.
   */
  void reportInteraction();

  /**
   * Reports a named user-interaction.
   *
   * <p>
   * Use this function when your gadget needs to track multiple user interaction
   * types.
   *
   * @param name The name of the reported interaction. It must match one from
   *          the supported list of interactions
   */
  void reportInteraction(String name);

  /**
   * Reports a named user-interaction that you want summed-up.
   *
   * <p>
   * Use this function when you want pass in a value for a specific interaction
   * type to be summed up in the reports.
   *
   * @param name The name of the reported interaction. It must match one from
   *          the supported list of interactions
   * @param value value to sum up for the reported interaction. It must be a
   *          positive number
   */
  void reportInteraction(String name, String value);

  /**
   * Reliably reports an interaction followed by a clickthrough and redirect to
   * the destination URL.
   *
   * <p>
   * This function is a combination of two methods:
   * {@link #clickDestinationUrl(String)} and
   * {@link #reportInteraction(String, String)}. All three parameters are
   * analogous to the parameters passed into each individual function.
   *
   * @param destUrl final destination URL you want to report and be redirected
   *          to
   * @param name The name of the reported interaction. It must match one from
   *          the supported list of interactions. This parameter is optional,
   *          {@code null} is accepted
   * @param value value to sum up for the reported interaction. It must be a
   *          positive number This parameter is optional, {@code null} is
   *          accepted
   */
  void reportInteractionClick(String destUrl, String name, String value);
}