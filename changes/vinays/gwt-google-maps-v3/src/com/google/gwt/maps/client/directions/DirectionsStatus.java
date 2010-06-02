/* Copyright (c) 2010 Vinay Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.gwt.maps.client.directions;


/**
 * This class implements {@link HasDirectionsStatus}.
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public class DirectionsStatus implements HasDirectionsStatus {
  
  @Override
  public native String InvalidRequest() /*-{
    return $wnd.google.maps.DirectionsStatus.INVALID_REQUEST;
  }-*/;

  @Override
  public native String MaxWaypointsExceeded() /*-{
    return $wnd.google.maps.DirectionsStatus.MAX_WAYPOINTS_EXCEEDED;
  }-*/;

  @Override
  public native String NotFound() /*-{
    return $wnd.google.maps.DirectionsStatus.NOT_FOUND;
  }-*/;

  @Override
  public native String Ok() /*-{
    return $wnd.google.maps.DirectionsStatus.OK;
  }-*/;

  @Override
  public native String OverQueryLimit() /*-{
    return $wnd.google.maps.DirectionsStatus.OVER_QUERY_LIMIT;
  }-*/;

  @Override
  public native String RequestDenied() /*-{
    return $wnd.google.maps.DirectionsStatus.REQUEST_DENIED;
  }-*/;

  @Override
  public native String UnknownError() /*-{
    return $wnd.google.maps.DirectionsStatus.UNKNOWN_ERROR;
  }-*/;

  @Override
  public native String ZeroResults() /*-{
    return $wnd.google.maps.DirectionsStatus.ZERO_RESULTS;
  }-*/;

}
