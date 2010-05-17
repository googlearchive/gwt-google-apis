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
package com.google.gwt.maps.client.streetview;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.event.StreetviewErrorHandler;
import com.google.gwt.maps.client.event.StreetviewInitializedHandler;
import com.google.gwt.maps.client.event.StreetviewPitchChangedHandler;
import com.google.gwt.maps.client.event.StreetviewYawChangedHandler;
import com.google.gwt.maps.client.event.StreetviewZoomChangedHandler;
import com.google.gwt.maps.client.event.StreetviewErrorHandler.StreetviewErrorEvent;
import com.google.gwt.maps.client.event.StreetviewInitializedHandler.StreetviewInitializedEvent;
import com.google.gwt.maps.client.event.StreetviewPitchChangedHandler.StreetviewPitchChangedEvent;
import com.google.gwt.maps.client.event.StreetviewYawChangedHandler.StreetviewYawChangedEvent;
import com.google.gwt.maps.client.event.StreetviewZoomChangedHandler.StreetviewZoomChangedEvent;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.impl.HandlerCollection;
import com.google.gwt.maps.client.impl.MapEvent;
import com.google.gwt.maps.client.impl.PanoramaImpl;
import com.google.gwt.maps.client.impl.EventImpl.DoubleCallback;
import com.google.gwt.maps.client.impl.EventImpl.IntCallback;
import com.google.gwt.maps.client.impl.EventImpl.StreetviewLocationCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;

/**
 * A class wrapping GStreetviewPanorama object that holds an instance of the
 * Flash&reg; Street View Panorama viewer.
 *
 * @see "http://code.google.com/apis/maps/documentation/reference.html#GStreetviewPanorama"
 */
public class StreetviewPanoramaWidget extends Composite {

  /**
   * An enum equivalents for error codes passed to
   * {@link StreetviewErrorHandler}.
   *
   * @see "http://code.google.com/apis/maps/documentation/reference.html#GStreetviewPanorama.ErrorValues"
   */
  public static enum ErrorValue {
    NO_NEARBY_PANO(600), NO_PHOTO(601), FLASH_UNAVAILABLE(603);

    /**
     * Returns enum value for the passed error code or <code>null</code> if
     * error code is unknown.
     *
     * @param errorCode error code.
     * @return enum value for the passed error code or <code>null</code> if
     *         error code is unknown.
     */
    public static ErrorValue getByErrorCode(int errorCode) {
      for (ErrorValue value : ErrorValue.values()) {
        if (errorCode == value.getErrorCode()) {
          return value;
        }
      }
      return null;
    }

    private int errorCode;

    ErrorValue(int errorCode) {
      this.errorCode = errorCode;
    }

    /**
     * Returns error code.
     *
     * @return error code.
     */
    public int getErrorCode() {
      return errorCode;
    }
  }

  private final FlowPanel panoramaContainer = new FlowPanel();
  private final JavaScriptObject jsoPeer;
  private HandlerCollection<StreetviewYawChangedHandler> yawChangedHandlers;
  private HandlerCollection<StreetviewPitchChangedHandler> pitchChangedHandlers;
  private HandlerCollection<StreetviewZoomChangedHandler> zoomChangedHandlers;
  private HandlerCollection<StreetviewInitializedHandler> initializedHandlers;
  private HandlerCollection<StreetviewErrorHandler> errorHandlers;

  /**
   * Creates new {@link StreetviewPanoramaWidget}.
   */
  public StreetviewPanoramaWidget() {
    initWidget(panoramaContainer);
    jsoPeer = PanoramaImpl.impl.construct(getElement());
    PanoramaImpl.impl.bind(jsoPeer, this);
    PanoramaImpl.impl.checkResize(jsoPeer);
  }

  /**
   * Creates new {@link StreetviewPanoramaWidget} with specified options.
   */
  public StreetviewPanoramaWidget(StreetviewPanoramaOptions options) {
    initWidget(panoramaContainer);
    jsoPeer = PanoramaImpl.impl.construct(getElement(), options);
    PanoramaImpl.impl.bind(jsoPeer, this);
    PanoramaImpl.impl.checkResize(jsoPeer);
  }

  /**
   * Adds a handler for error events. This event is fired when an error occurs.
   * See <a href="http://code.google.com/apis/maps/documentation/reference.html#GStreetviewPanorama.ErrorValues"
   * >GStreetviewPanorama.ErrorValues</a> for a list of error types.
   *
   * @see "http://code.google.com/apis/maps/documentation/reference.html#GStreetviewPanorama.error"
   *
   * @param handler the handler to handle error eenum value for the passed error
   *          code or <code>null</code> if error covents
   */
  public void addErrorHandler(final StreetviewErrorHandler handler) {
    maybeInitErrorHandlers();

    errorHandlers.addHandler(handler, new IntCallback() {
      @Override
      public void callback(int value) {
        StreetviewErrorEvent event = new StreetviewErrorEvent(
            StreetviewPanoramaWidget.this, value);
        handler.onError(event);
      }
    });

  }

  /**
   * Adds a handler for initialized events. This event is fired each time a
   * panorama is initialized. Note that this event is fired as soon as rendering
   * starts, but that all image data may not be loaded at this time.
   *
   * @see "http://code.google.com/apis/maps/documentation/reference.html#GStreetviewPanorama.initialized"
   *
   * @param handler the handler to handle initialized events
   */
  public void addInitializedHandler(final StreetviewInitializedHandler handler) {
    maybeInitInitializedHandlers();

    initializedHandlers.addHandler(handler, new StreetviewLocationCallback() {
      @Override
      public void callback(StreetviewLocation location) {
        StreetviewInitializedEvent event = new StreetviewInitializedEvent(
            StreetviewPanoramaWidget.this, location);
        handler.onInitialized(event);
      }
    });
  }

  /**
   * Adds a handler for pitch change events. This event is fired when the pitch
   * displayed in the flash viewer is changed.
   *
   * @see "http://code.google.com/apis/maps/documentation/reference.html#GStreetviewPanorama.pitchchanged"
   *
   * @param handler the handler to handle pitch change events
   */
  public void addPitchChangedHandler(final StreetviewPitchChangedHandler handler) {
    maybeInitPitchChangedHandlers();

    pitchChangedHandlers.addHandler(handler, new DoubleCallback() {
      @Override
      public void callback(double pitch) {
        StreetviewPitchChangedEvent event = new StreetviewPitchChangedEvent(
            StreetviewPanoramaWidget.this, pitch);
        handler.onPitchChanged(event);
      }
    });
  }

  /**
   * Adds a handler for yaw change events. This event is fired when the yaw
   * displayed in the flash viewer is changed.
   *
   * @see "http://code.google.com/apis/maps/documentation/reference.html#GStreetviewPanorama.yawchanged"
   *
   * @param handler the handler to handle yaw change events
   */
  public void addYawChangedHandler(final StreetviewYawChangedHandler handler) {
    maybeInitYawChangedHandlers();

    yawChangedHandlers.addHandler(handler, new DoubleCallback() {
      @Override
      public void callback(double yaw) {
        StreetviewYawChangedEvent event = new StreetviewYawChangedEvent(
            StreetviewPanoramaWidget.this, yaw);
        handler.onYawChanged(event);
      }
    });
  }

  /**
   * Adds a handler for zoom change events. This event is fired when the zoom
   * level of the flash viewer is changed.
   *
   * @see "http://code.google.com/apis/maps/documentation/reference.html#GStreetviewPanorama.zoomchanged"
   *
   * @param handler the handler to handle zoom change events
   */
  public void addZoomChangedHandler(final StreetviewZoomChangedHandler handler) {
    maybeInitZoomChangedHandlers();

    zoomChangedHandlers.addHandler(handler, new DoubleCallback() {
      @Override
      public void callback(double zoom) {
        StreetviewZoomChangedEvent event = new StreetviewZoomChangedEvent(
            StreetviewPanoramaWidget.this, zoom);
        handler.onZoomChanged(event);
      }
    });
  }

  /**
   * Notifies the flash viewer of a change of the size of its container.
   */
  public void checkResize() {
    PanoramaImpl.impl.checkResize(jsoPeer);
  }

  /**
   * Follows a link from the current panorama to a neighbouring panorama. Yaw
   * specifies the direction of the neighbouring panorama; if there are multiple
   * neighbouring panoramas then the nearest match will be taken.
   *
   * @param yaw the yaw specifying the direction of the neighbouring panorama;
   */
  public void followLink(double yaw) {
    PanoramaImpl.impl.followLink(jsoPeer, yaw);
  }

  /**
   * Returns the latitude and longitude of the panorama object.
   *
   * @return the latitude and longitude of the panorama object.
   */
  public LatLng getLatLng() {
    return PanoramaImpl.impl.getLatLng(jsoPeer);
  }

  /**
   * Returns the unique id of the panorama object.
   *
   * @return the unique id of the panorama object.
   */
  public String getPanoId() {
    return PanoramaImpl.impl.getPanoId(jsoPeer);
  }

  /**
   * Returns the current point of view ({@link Pov}) displayed in the flash
   * viewer.
   *
   * @return the current point of view ({@link Pov}) displayed in the flash
   *         viewer.
   */
  public Pov getPov() {
    return PanoramaImpl.impl.getPOV(jsoPeer);
  }

  /**
   * Hides the flash viewer.
   */
  public void hide() {
    PanoramaImpl.impl.hide(jsoPeer);
  }

  /**
   * Returns <code>true</code> if and only if the flash viewer associated with
   * this object is hidden.
   *
   * @return <code>true</code> if and only if the flash viewer associated with
   *         this object is hidden.
   */
  public boolean isHidden() {
    return PanoramaImpl.impl.isHidden(jsoPeer);
  }

  /**
   * Changes the current point of view ({@link Pov}) displayed in the flash
   * viewer without changing the location. Performs a smooth animation from the
   * current POV to the new POV. If longRoute is set then the animation will
   * follow a long route around the sphere, otherwise the shortest route.
   *
   * @param pov the new point of view to perform an animation to
   * @param longRoute if <code>true</code> the animation will follow a long
   *          route around the sphere, otherwise the shortest route.
   */
  public void panTo(Pov pov, boolean longRoute) {
    PanoramaImpl.impl.panTo(jsoPeer, pov, longRoute);
  }

  /**
   * Removes the instance of the flash viewer currently associated with this
   * object from the DOM. This function must be called before removing the HTML
   * container element otherwise some browsers will fail to garbage collect the
   * flash viewer.
   */
  public void remove() {
    PanoramaImpl.impl.remove(jsoPeer);
  }

  /**
   * Removes a handler for error events.
   *
   * @see StreetviewPanoramaWidget#addErrorHandler(StreetviewErrorHandler)
   * @see "http://code.google.com/apis/maps/documentation/reference.html#GStreetviewPanorama.error"
   *
   * @param handler previously added handler
   */
  public void removeErrorHandler(StreetviewErrorHandler handler) {
    if (errorHandlers != null) {
      errorHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a handler for initialized events.
   *
   * @see StreetviewPanoramaWidget#addInitializedHandler(StreetviewInitializedHandler)
   * @see "http://code.google.com/apis/maps/documentation/reference.html#GStreetviewPanorama.initialized"
   *
   * @param handler previously added handler
   */
  public void removeInitializedHandler(StreetviewInitializedHandler handler) {
    if (initializedHandlers != null) {
      initializedHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a handler for pitch changed events.
   *
   * @see StreetviewPanoramaWidget#addPitchChangedHandler(StreetviewPitchChangedHandler)
   * @see "http://code.google.com/apis/maps/documentation/reference.html#GStreetviewPanorama.pitchchanged"
   *
   * @param handler previously added handler
   */
  public void removePitchChangedHandler(StreetviewPitchChangedHandler handler) {
    if (pitchChangedHandlers != null) {
      pitchChangedHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a handler for yaw changed events.
   *
   * @see StreetviewPanoramaWidget#addYawChangedHandler(StreetviewYawChangedHandler)
   * @see "http://code.google.com/apis/maps/documentation/reference.html#GStreetviewPanorama.yawchanged"
   *
   * @param handler previously added handler
   */
  public void removeYawChangedHandler(StreetviewYawChangedHandler handler) {
    if (yawChangedHandlers != null) {
      yawChangedHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a handler for zoom changed events.
   *
   * @see StreetviewPanoramaWidget#addZoomChangedHandler(StreetviewZoomChangedHandler)
   * @see "http://code.google.com/apis/maps/documentation/reference.html#GStreetviewPanorama.zoomchanged"
   *
   * @param handler previously added handler
   */
  public void removeZoomChangedHandler(StreetviewZoomChangedHandler handler) {
    if (zoomChangedHandlers != null) {
      zoomChangedHandlers.removeHandler(handler);
    }
  }

  /**
   * Sets the location and point of view ({@link Pov}) of the flash viewer.
   * After calling this function, the viewer will display the nearest location
   * to the {@link LatLng} provided if one is available. If no data is available
   * for this location, then the flash player will remain unchanged and emit an
   * error event.
   *
   * @param latLng the new latitude and longitude
   * @param pov the new point of view
   */
  public void setLocationAndPov(LatLng latLng, Pov pov) {
    PanoramaImpl.impl.setLocationAndPOV(jsoPeer, latLng, pov);
  }

  /**
   * Changes the current point of view ({@link Pov}) displayed in the flash
   * viewer without changing the location.
   *
   * @param pov the new point of view
   */
  public void setPov(Pov pov) {
    PanoramaImpl.impl.setPOV(jsoPeer, pov);
  }

  /**
   * Shows a user-contributed photo in the flash viewer. After calling this
   * method, the viewer will display the photo uniquely identified by photoSpec.
   * If the photo cannot be displayed, then the flash player will remain
   * unchanged and emit the error code <code>601</code> - <code>NO_PHOTO</code>.
   *
   * @see "http://code.google.com/apis/maps/documentation/reference.html#GStreetviewPanorama.ErrorValues.NO_PHOTO"
   *
   * @param photoSpec specifies a photo to display.
   */
  public void setUserPhoto(PhotoSpec photoSpec) {
    PanoramaImpl.impl.setUserPhoto(jsoPeer, photoSpec);
  }

  /**
   * Shows the flash viewer.
   */
  public void show() {
    PanoramaImpl.impl.show(jsoPeer);
  }

  /**
   * Manually trigger the specified event on this object.
   *
   * Note: The trigger() methods are provided for unit testing purposes only.
   *
   * @param event an event to deliver to the handler.
   */
  void trigger(StreetviewErrorEvent event) {
    maybeInitErrorHandlers();
    errorHandlers.trigger(event.getErrorCode());
  }

  /**
   * Manually trigger the specified event on this object.
   *
   * Note: The trigger() methods are provided for unit testing purposes only.
   *
   * @param event an event to deliver to the handler.
   */
  void trigger(StreetviewInitializedEvent event) {
    maybeInitInitializedHandlers();
    initializedHandlers.trigger(event.getLocation());
  }

  /**
   * Manually trigger the specified event on this object.
   *
   * Note: The trigger() methods are provided for unit testing purposes only.
   *
   * @param event an event to deliver to the handler.
   */
  void trigger(StreetviewPitchChangedEvent event) {
    maybeInitPitchChangedHandlers();
    pitchChangedHandlers.trigger(event.getPitch());
  }

  /**
   * Manually trigger the specified event on this object.
   *
   * Note: The trigger() methods are provided for unit testing purposes only.
   *
   * @param event an event to deliver to the handler.
   */
  void trigger(StreetviewYawChangedEvent event) {
    maybeInitYawChangedHandlers();
    yawChangedHandlers.trigger(event.getYaw());
  }

  /**
   * Manually trigger the specified event on this object.
   *
   * Note: The trigger() methods are provided for unit testing purposes only.
   *
   * @param event an event to deliver to the handler.
   */
  void trigger(StreetviewZoomChangedEvent event) {
    maybeInitZoomChangedHandlers();
    zoomChangedHandlers.trigger(event.getZoom());
  }

  /**
   * Lazy init the HandlerCollection.
   */
  private void maybeInitErrorHandlers() {
    if (errorHandlers == null) {
      errorHandlers = new HandlerCollection<StreetviewErrorHandler>(jsoPeer,
          MapEvent.ERROR);
    }
  }

  /**
   * Lazy init the HandlerCollection.
   */
  private void maybeInitInitializedHandlers() {
    if (initializedHandlers == null) {
      initializedHandlers = new HandlerCollection<StreetviewInitializedHandler>(
          jsoPeer, MapEvent.INITIALIZED);
    }
  }

  /**
   * Lazy init the HandlerCollection.
   */
  private void maybeInitPitchChangedHandlers() {
    if (pitchChangedHandlers == null) {
      pitchChangedHandlers = new HandlerCollection<StreetviewPitchChangedHandler>(
          jsoPeer, MapEvent.PITCHCHANGED);
    }
  }

  /**
   * Lazy init the HandlerCollection.
   */
  private void maybeInitYawChangedHandlers() {
    if (yawChangedHandlers == null) {
      yawChangedHandlers = new HandlerCollection<StreetviewYawChangedHandler>(
          jsoPeer, MapEvent.YAWCHANGED);
    }
  }

  /**
   * Lazy init the HandlerCollection.
   */
  private void maybeInitZoomChangedHandlers() {
    if (zoomChangedHandlers == null) {
      zoomChangedHandlers = new HandlerCollection<StreetviewZoomChangedHandler>(
          jsoPeer, MapEvent.ZOOMCHANGED);
    }
  }
}
