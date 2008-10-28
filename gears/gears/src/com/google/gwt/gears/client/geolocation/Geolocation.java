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
package com.google.gwt.gears.client.geolocation;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;

/**
 * The Geolocation API enables a web application to obtain a user's geographical
 * position.
 * <p>
 * The Geolocation API enables a web application to:
 * <ul>
 * <li>Obtain the user's current position, using the
 * {@link #getCurrentPosition(PositionCallback) getCurrentPosition} method
 * <li>Watch the user's position as it changes over time, using the
 * {@link #watchPosition(PositionCallback) watchPosition} method
 * <li>Quickly and cheaply obtain the user's last known position, using the
 * {@link #lastPosition} property
 * </ul>
 * <p>
 * The Geolocation API provides the best estimate of the user's position using a
 * number of sources (called location providers). These providers may be onboard
 * (GPS for example) or server-based (a network location provider). The
 * {@link #getCurrentPosition(PositionCallback, PositionOptions)
 * getCurrentPosition} and
 * {@link #watchPosition(PositionCallback, PositionOptions) watchPosition}
 * methods accept a parameter of type {@link PositionOptions} which lets you
 * specify which location providers to use.
 * <p>
 * To find out how to implement your own network location provider for use with
 * the Geolocation API, see the <a
 * href="http://code.google.com/p/gears/wiki/GeolocationAPI">Geolocation API
 * design document</a>.
 * <p>
 * <b>Permission</b>
 * <p>
 * A site's permission to use location information is separate from the
 * permission required by other Gears APIs. Permission is granted by the user in
 * the same way as for other Gears APIs, through a dialog. If you would like to
 * customize the default dialog, you can explicitly call the
 * {@link #getPermission(String, String, String) getPermission} method.
 */
public final class Geolocation extends JavaScriptObject {

  protected Geolocation() {
    // required for overlay types
  }

  /**
   * Gets the last known position, or null if there is no last known position.
   * This does not actively trigger obtaining a new position fix, so it is quick
   * and does not consume power or computational resources.
   * 
   * @return The last known position, or null if there is no last known position
   */
  public native Position getLastPosition()/*-{
    return this.lastPosition;
  }-*/;

  /**
   * Obtains a new position. Obtains a new position. If successful, the success
   * callback function is called exactly once with a good position. If none of
   * the location providers find a good fix, the error callback is called
   * exactly once with an error message unless you have passed null for
   * errorCallback.
   * 
   * @param callback
   *          a {@link PositionCallback callback} which will receive the
   *          {@link Position} if available, or a {@link PositionError} if none
   *          of the location providers find a good fix
   */
  public native void getCurrentPosition(PositionCallback callback)/*-{
    this.getCurrentPosition(
      function(position) {
        @com.google.gwt.gears.client.geolocation.Geolocation::firePositionSuccess(Lcom/google/gwt/gears/client/geolocation/PositionCallback;Lcom/google/gwt/gears/client/geolocation/Position;)(callback, position);
      }, 
      function(error) {
        @com.google.gwt.gears.client.geolocation.Geolocation::firePositionError(Lcom/google/gwt/gears/client/geolocation/PositionCallback;Lcom/google/gwt/gears/client/geolocation/PositionError;)(callback, error);
      }
    );
  }-*/;

  /**
   * Obtains a new position. Obtains a new position. If successful, the success
   * callback function is called exactly once with a good position. If none of
   * the location providers find a good fix, the error callback is called
   * exactly once with an error message.
   * 
   * @param callback
   *          a {@link PositionCallback callback} which will receive the
   *          {@link Position} if available, or a {@link PositionError} if none
   *          of the location providers find a good fix
   * @param options
   *          specifies the {@link PositionOptions options} to use for this
   *          request
   */
  public native void getCurrentPosition(PositionCallback callback,
      PositionOptions options)/*-{
    this.getCurrentPosition(
      function(position) {
        @com.google.gwt.gears.client.geolocation.Geolocation::firePositionSuccess(Lcom/google/gwt/gears/client/geolocation/PositionCallback;Lcom/google/gwt/gears/client/geolocation/Position;)(callback, position);
      }, 
      function(error) {
        @com.google.gwt.gears.client.geolocation.Geolocation::firePositionError(Lcom/google/gwt/gears/client/geolocation/PositionCallback;Lcom/google/gwt/gears/client/geolocation/PositionError;)(callback, error);
      },
      options
    );
  }-*/;

  /**
   * Repeatedly obtains a new position. The success callback function is called
   * as soon as a good position is available, or whenever the position changes
   * significantly, subject to a maximum callback frequency. The error callback
   * is called if a fatal error occurs which will prevent a position fix from
   * ever being obtained by this watch.
   * 
   * @param callback
   *          a {@link PositionCallback callback} which will receive the
   *          {@link Position} when available and whenever the position changes
   *          significantly, or a {@link PositionError} if errors prevent a
   *          position fix from ever being obtained by this watch.
   * @return A unique watch identifier
   */
  public native int watchPosition(PositionCallback callback)/*-{
    return this.watchPosition(
      function(position) {
        @com.google.gwt.gears.client.geolocation.Geolocation::firePositionSuccess(Lcom/google/gwt/gears/client/geolocation/PositionCallback;Lcom/google/gwt/gears/client/geolocation/Position;)(callback, position);
      }, 
      function(error) {
        @com.google.gwt.gears.client.geolocation.Geolocation::firePositionError(Lcom/google/gwt/gears/client/geolocation/PositionCallback;Lcom/google/gwt/gears/client/geolocation/PositionError;)(callback, error);
      }
    );
  }-*/;

  /**
   * Repeatedly obtains a new position. The success callback function is called
   * as soon as a good position is available, or whenever the position changes
   * significantly, subject to a maximum callback frequency. The error callback
   * is called if a fatal error occurs which will prevent a position fix from
   * ever being obtained by this watch.
   * 
   * @param successCallback
   *          a {@link SuccessCallback callback} which will receive the Position
   *          when available and whenever the position changes significantly
   * @param errorCallback
   *          an {@link ErrorCallback callback} which will be invoked if errors
   *          prevent a position fix from ever being obtained by this watch.
   * @param options
   *          specifies the {@link PositionOptions options} to use for this
   *          request
   * @return A unique watch identifier
   */
  public native int watchPosition(PositionCallback callback,
      PositionOptions options)/*-{
    return this.watchPosition(
      function(position) {
        @com.google.gwt.gears.client.geolocation.Geolocation::firePositionSuccess(Lcom/google/gwt/gears/client/geolocation/PositionCallback;Lcom/google/gwt/gears/client/geolocation/Position;)(callback, position);
      }, 
      function(error) {
        @com.google.gwt.gears.client.geolocation.Geolocation::firePositionError(Lcom/google/gwt/gears/client/geolocation/PositionCallback;Lcom/google/gwt/gears/client/geolocation/PositionError;)(callback, error);
      },
      options
    );
  }-*/;

  /**
   * Stop watching the current position
   * 
   * @param watchId
   *          the identifier of the watch to stop
   */
  public native void clearWatch(int watchId)/*-{
    this.clearWatch(watchId);
  }-*/;

  /**
   * Lets a site manually trigger the Gears location security dialog.
   * <p>
   * It is always safe to call this function; it will return immediately if the
   * site already has permission to access the user's location through Gears.
   * 
   * @return true if the site has permission to access the user's location
   *         through Gears
   */
  public native boolean getPermission()/*-{
    return this.getPermission();
  }-*/;

  /**
   * Lets a site manually trigger the Gears location security dialog.
   * <p>
   * It is always safe to call this function; it will return immediately if the
   * site already has permission to access the user's location through Gears.
   * 
   * @param siteName
   *          friendly name of the site requesting permission
   * @return true if the site has permission to access the user's location
   *         through Gears
   */
  public native boolean getPermission(String siteName)/*-{
    return this.getPermission(siteName);
  }-*/;

  /**
   * Lets a site manually trigger the Gears location security dialog.
   * <p>
   * It is always safe to call this function; it will return immediately if the
   * site already has permission to access the user's location through Gears.
   * 
   * @param siteName
   *          friendly name of the site requesting permission
   * @param imageUrl
   *          URL of a .png file to display in the dialog
   * @return true if the site has permission to access the user's location
   *         through Gears
   */
  public native boolean getPermission(String siteName, String imageUrl)/*-{
    return this.getPermission(siteName, imageUrl);
  }-*/;

  /**
   * Lets a site manually trigger the Gears location security dialog.
   * <p>
   * It is always safe to call this function; it will return immediately if the
   * site already has permission to access the user's location through Gears.
   * 
   * @param siteName
   *          friendly name of the site requesting permission
   * @param imageUrl
   *          URL of a .png file to display in the dialog
   * @param extraMessage
   *          a site-specific text to display to users in the security dialog
   * @return true if the site has permission to access the user's location
   *         through Gears
   */
  public native boolean getPermission(String siteName, String imageUrl,
      String extraMessage)/*-{
    return this.getPermission(siteName, imageUrl, extraMessage);
  }-*/;

  // Called from JSNI
  @SuppressWarnings("unused")
  private static void firePositionSuccess(PositionCallback callback,
      Position position) {
    if (callback == null) {
      return;
    }

    UncaughtExceptionHandler ueh = GWT.getUncaughtExceptionHandler();
    if (ueh != null) {
      try {
        callback.onSuccess(position);
      } catch (Throwable e) {
        ueh.onUncaughtException(e);
      }
    } else {
      callback.onSuccess(position);
    }
  }

  // Called from JSNI
  @SuppressWarnings("unused")
  private static void firePositionError(PositionCallback callback,
      PositionError error) {
    if (callback == null) {
      return;
    }

    UncaughtExceptionHandler ueh = GWT.getUncaughtExceptionHandler();
    if (ueh != null) {
      try {
        callback.onError(error);
      } catch (Throwable e) {
        ueh.onUncaughtException(e);
      }
    } else {
      callback.onError(error);
    }
  }
}
