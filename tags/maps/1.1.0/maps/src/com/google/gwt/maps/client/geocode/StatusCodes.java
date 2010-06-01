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
package com.google.gwt.maps.client.geocode;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Result status codes for the Google Geocoding service.
 */
public class StatusCodes extends JavaScriptObject {

  /*
   * Design note: This was not implemented as an enum because we feel that the
   * Maps API might return new status codes in the future.
   */

  /**
   * The given key is either invalid or does not match the domain for which it
   * was given.
   */
  public static final int BAD_KEY = getBadKeyCode();

  /**
   * A directions request could not be successfully parsed.
   */
  public static final int BAD_REQUEST = getBadRequestCode();

  /**
   * Synonym for {@link StatusCodes#MISSING_QUERY}.
   */
  public static final int MISSING_ADDRESS = getMissingAddressCode();

  /**
   * The HTTP q parameter was either missing or had no value. For geocoding
   * requests, this means that an empty address was specified as input. For
   * directions requests, this means that no query was specified in the input
   */
  public static final int MISSING_QUERY = getMissingQueryCode();

  /**
   * A geocoding or directions request could not be successfully processed, yet
   * the exact reason for the failure is not known.
   */
  public static final int SERVER_ERROR = getServerErrorCode();

  /**
   * No errors occurred; the address was successfully parsed and its geocode has
   * been returned.
   */
  public static final int SUCCESS = getSuccessCode();

  /**
   * The given key has gone over the requests limit in the 24 hour period.
   */
  public static final int TOO_MANY_QUERIES = getTooManyQueriesCode();
  
  /**
   * The geocode for the given address or the route for the given directions
   * query cannot be returned due to legal or contractual reasons.
   */
  public static final int UNAVAILABLE_ADDRESS = getUnavailableAddressCode();

  /**
   * No corresponding geographic location could be found for the specified
   * address. This may be due to the fact that the address is relatively new, or
   * it may be incorrect.
   */
  public static final int UNKNOWN_ADDRESS = getUnknownAddressCode();

  /**
   * The {@link Directions} object could not compute directions between the
   * points mentioned in the query. This is usually because there is no route
   * available between the two points, or because we do not have data for
   * routing in that region.
   */
  public static final int UNKNOWN_DIRECTIONS = getUnknownDirectionsCode();

  /**
   * Something went wrong inside the API itself.
   */
  public static final int API_ERROR = 9999;
 
  public static final String getName(int statusCode) {
    /* 
     * It would be nice to make this a switch statement, but two of the values
     * currently map to the same numeric constant.
     *
     * MISSING_QUERY and MISSING_ADDRESS both map to 601.
     */
    if (statusCode == BAD_KEY) {
      return "BAD_KEY";
    } else if (statusCode == BAD_REQUEST) {
      return "BAD_REQUEST";
    } else if (statusCode == MISSING_ADDRESS) {
      return "MISSING_ADDRESS";
    } else if (statusCode == MISSING_QUERY) {
      return "MISSING_QUERY";
    } else if (statusCode == SERVER_ERROR) {
      return "SERVER_ERROR";
    } else if (statusCode == SUCCESS) {
      return "SUCCESS";
    } else if (statusCode == UNAVAILABLE_ADDRESS) {
      return "UNAVAILABLE_ADDRESS";
    } else if (statusCode == UNKNOWN_ADDRESS) {
      return "UNKNOWN_ADDRESS";
    } else if (statusCode == UNKNOWN_DIRECTIONS) {
      return "UNKNOWN_DIRECTIONS";
    } else if (statusCode == TOO_MANY_QUERIES) {
      return "TOO_MANY_QUERIES";
    } else if (statusCode == API_ERROR) {
      return "API_ERROR";      
    } else {
      return "UNKNOWN_STATUS: " + statusCode;
    }
  }

  private static native int getBadKeyCode() /*-{
    return $wnd.G_GEO_BAD_KEY;
  }-*/;

  private static native int getBadRequestCode() /*-{
    return $wnd.G_GEO_BAD_REQUEST;
  }-*/;

  private static native int getMissingAddressCode() /*-{
    return $wnd.G_GEO_MISSING_ADDRESS;
  }-*/;

  private static native int getMissingQueryCode() /*-{
    return $wnd.G_GEO_MISSING_QUERY;
  }-*/;

  private static native int getServerErrorCode() /*-{
    return $wnd.G_GEO_SERVER_ERROR;
  }-*/;

  private static native int getSuccessCode() /*-{
    return $wnd.G_GEO_SUCCESS;
  }-*/;

  private static native int getTooManyQueriesCode() /*-{
    return $wnd.G_GEO_TOO_MANY_QUERIES;
  }-*/;

  private static native int getUnavailableAddressCode() /*-{
    return $wnd.G_GEO_UNAVAILABLE_ADDRESS;
  }-*/;

  private static native int getUnknownAddressCode() /*-{
    return $wnd.G_GEO_UNKNOWN_ADDRESS;
  }-*/;

  private static native int getUnknownDirectionsCode() /*-{
    return $wnd.G_GEO_UNKNOWN_DIRECTIONS;
  }-*/;

  protected StatusCodes() {
    // Required for JS overlays
  }
}
