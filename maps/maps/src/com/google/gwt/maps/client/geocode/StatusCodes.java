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

import com.google.gwt.maps.client.impl.StatusCodesImpl;

/**
 * Result status codes for the Google Geocoding service.
 */
public final class StatusCodes {

  /*
   * Design note: This was not implemented as an enum because we feel that the
   * Maps API might return new status codes in the future.
   */
  
  /**
   * The given key is either invalid or does not match the domain for which it
   * was given.
   */
  public static final int BAD_KEY = StatusCodesImpl.impl.getBadKeyCode(StatusCodesImpl.obj);

  /**
   * A directions request could not be successfully parsed.
   */
  public static final int BAD_REQUEST = StatusCodesImpl.impl.getBadRequestCode(StatusCodesImpl.obj);

  /**
   * Synonym for {@link StatusCodes#MISSING_QUERY}.
   */
  public static final int MISSING_ADDRESS = StatusCodesImpl.impl.getMissingAddressCode(StatusCodesImpl.obj);

  /**
   * The HTTP q parameter was either missing or had no value. For geocoding
   * requests, this means that an empty address was specified as input. For
   * directions requests, this means that no query was specified in the input
   */
  public static final int MISSING_QUERY = StatusCodesImpl.impl.getMissingQueryCode(StatusCodesImpl.obj);

  /**
   * A geocoding or directions request could not be successfully processed, yet
   * the exact reason for the failure is not known.
   */
  public static final int SERVER_ERROR = StatusCodesImpl.impl.getServerErrorCode(StatusCodesImpl.obj);

  /**
   * No errors occurred; the address was successfully parsed and its geocode has
   * been returned.
   */
  public static final int SUCCESS = StatusCodesImpl.impl.getSuccessCode(StatusCodesImpl.obj);

  /**
   * The geocode for the given address or the route for the given directions
   * query cannot be returned due to legal or contractual reasons.
   */
  public static final int UNAVAILABLE_ADDRESS = StatusCodesImpl.impl.getUnavailableAddressCode(StatusCodesImpl.obj);

  /**
   * No corresponding geographic location could be found for the specified
   * address. This may be due to the fact that the address is relatively new, or
   * it may be incorrect.
   */
  public static final int UNKNOWN_ADDRESS = StatusCodesImpl.impl.getUnknownAddressCode(StatusCodesImpl.obj);

  /**
   * The {@link Directions} object could not compute directions between the
   * points mentioned in the query. This is usually because there is no route
   * available between the two points, or because we do not have data for
   * routing in that region.
   */
  public static final int UNKNOWN_DIRECTIONS = StatusCodesImpl.impl.getUnknownDirectionsCode(StatusCodesImpl.obj);

  public static String getName(int statusCode) {
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
    } else {
      return "UNKNOWN_STATUS: " + statusCode;
    }
  }

  // This class is not meant to be instantiated by end users.
  private StatusCodes() {
  }
}
