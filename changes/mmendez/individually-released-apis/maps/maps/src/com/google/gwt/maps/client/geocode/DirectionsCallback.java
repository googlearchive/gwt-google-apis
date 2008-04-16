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

/**
 * An interface containing methods used to return results from a Directions
 * query.
 */
public interface DirectionsCallback {

  /**
   * Called when a Directions load() method fails.
   * 
   * @param statusCode A numeric value from the {@link StatusCodes} class.
   */
  void onFailure(int statusCode);

  /**
   * Called when a Directions.load() query returns successfully.
   * 
   * @param result represents the set of results from the last successfully
   *          loaded query. These results are valid until the next
   *          load() request is made on the associated Directions object.
   */
  void onSuccess(DirectionResults result);

}
