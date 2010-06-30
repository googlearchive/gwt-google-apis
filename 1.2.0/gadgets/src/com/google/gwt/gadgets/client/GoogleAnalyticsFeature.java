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
 * Provides access to the Google Analytics feature.
 */
public interface GoogleAnalyticsFeature {

  /**
   * Provides a way of reporting event and page views.
   */
  interface Tracker {

    /**
     * Requires the name of the gadget and action parameter in order to
     * correctly insert tracked data into Event Tracking reports. The other
     * parameters are optional. The values passed via this method are sent in
     * the GIF request view the utm variable.
     *
     * Note: this feature is currently in closed beta test and may not be
     * available to all users.
     *
     * @param eventName A string used at the top level of the Event Tracking
     *          reports. For example, if you were tracking interaction on a
     *          number of gadget elements, you would likely use the name of the
     *          gadget itself for this parameter, so that all interaction
     *          tracking for the gadget would be aggregated in the same section
     *          of the Google Analytics reports.
     * @param action A string to further segment gadget interaction in the Event
     *          Tracking reports. For more information, see the <a href=
     *          "http://code.google.com/apis/analytics/docs/tracking/eventTrackerOverview.html"
     *          >Tracking Events</a>
     */
    void reportEvent(String eventName, String action);

    /**
     * Requires the name of the gadget and action parameter in order to
     * correctly insert tracked data into Event Tracking reports. The other
     * parameters are optional. The values passed via this method are sent in
     * the GIF request view the utm variable.
     *
     * * Note: this feature is currently in closed beta test and may not be
     * available to all users.
     *
     * @param eventName A string used at the top level of the Event Tracking
     *          reports. For example, if you were tracking interaction on a
     *          number of gadget elements, you would likely use the name of the
     *          gadget itself for this parameter, so that all interaction
     *          tracking for the gadget would be aggregated in the same section
     *          of the Google Analytics reports.
     * @param action A string to further segment gadget interaction in the Event
     *          Tracking reports. For more information, see the <a href=
     *          "http://code.google.com/apis/analytics/docs/tracking/eventTrackerOverview.html"
     *          >Tracking Events</a>
     * @param label String you can use as a secondary segment for your gadget.
     */
    void reportEvent(String eventName, String action, String label);

    /**
     * Requires the name of the gadget and action parameter in order to
     * correctly insert tracked data into Event Tracking reports. The other
     * parameters are optional. The values passed via this method are sent in
     * the GIF request view the utm variable.
     *
     * Note: this feature is currently in closed beta test and may not be
     * available to all users.
     *
     * @param eventName A string used at the top level of the Event Tracking
     *          reports. For example, if you were tracking interaction on a
     *          number of gadget elements, you would likely use the name of the
     *          gadget itself for this parameter, so that all interaction
     *          tracking for the gadget would be aggregated in the same section
     *          of the Google Analytics reports.
     * @param action A string to further segment gadget interaction in the Event
     *          Tracking reports. For more information, see the <a href=
     *          "http://code.google.com/apis/analytics/docs/tracking/eventTrackerOverview.html"
     *          >Tracking Events</a>
     * @param label String you can use as a secondary segment for your gadget.
     * @param value Number that you can supply as a value for the gadget
     *          interaction. This number is aggregated for each time the method
     *          is invoked.
     */
    void reportEvent(String eventName, String action, String label, int value);

    /**
     * Requires a string in order to correctly populate the content reports.
     * Typically this string is in the form of a path that you define for your
     * reporting purposes. The value passed into this method is sent in the GIF
     * request via the utm variable. Use this method to track gadget loads and
     * gadget interactions.
     *
     * @param path Path to provide for the virtual URL of this element.
     *
     */
    void reportPageview(String path);

  }

  /**
   * Creates a new tracker object. For further information, see <a href=
   * "http://code.google.com/apis/analytics/docs/tracking/gadgetTracking.html"
   * >Gadget Tracking in Analytics.</a>
   *
   * @param domainId The analytics ID for the domain to track.
   * @return a new tracker object.
   */
  Tracker createTracker(String domainId);
}