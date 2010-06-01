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
package com.google.gwt.gadgets.client.ui;

import com.google.gwt.user.client.ui.Image;

/**
 * Image that will be cached by the Gadget Container unless this is not
 * executed in a Gadget Container.
 *
 * <p>The image URL appearing on the browser will not be the one set here but
 * the URL of the cached image on the Gadget container's server.
 */

public class GadgetImage extends Image {

  /**
   * Returns a proxy URL that can be used to access the given image's URL. If
   * we are not in a Gadget container, we just return the entered url
   */
  private static native String getCachedImageUrl(String url) /*-{
    if($wnd.gadgets.io.getProxyUrl){
      return $wnd.gadgets.io.getProxyUrl(url);
    } else {
      return url;
    }
  }-*/;

  /**
   * Constructs a new GadgetImage. This image will be cached on the container.
   */
  public GadgetImage() {
  }

  /**
   * Constructs a new GadgetImage given the image's URL. This image will be
   * cached by the Gadget container.
   *
   * @param url The URL of the image that will be cached
   */
  public GadgetImage(String url) {
    super(getCachedImageUrl(url));
  }

  /**
   * Creates a clipped image with a specified URL and visibility rectangle. The
   * visibility rectangle is declared relative to the the rectangle which
   * encompasses the entire image, which has an upper-left vertex of (0,0). The
   * load event will be fired immediately after the object has been constructed
   * (i.e. potentially before the image has been loaded in the browser). Since
   * the width and height are specified explicitly by the user, this behavior
   * will not cause problems with retrieving the width and height of a clipped
   * image in a load event handler. This image will be cached by the
   * Gadget container server.
   *
   * @param url the URL of the image to be displayed and that will be cached
   * @param left the horizontal co-ordinate of the upper-left vertex of the
   *          visibility rectangle
   * @param top the vertical co-ordinate of the upper-left vertex of the
   *          visibility rectangle
   * @param width the width of the visibility rectangle
   * @param height the height of the visibility rectangle
   */
  public GadgetImage(String url, int left, int top, int width, int height) {
    super(getCachedImageUrl(url),left, top, width, height);
  }

  /**
   * Sets the image's URL. The image will be cached first by the Gadget
   * container.
   *
   * @param url The URL of the image
   */
  @Override
  public void setUrl(String url) {
      super.setUrl(getCachedImageUrl(url));
  }

  /**
   * Sets the url and the visibility rectangle for the image at the same time.
   * A single load event will be fired if either the incoming url or visiblity
   * rectangle co-ordinates differ from the image's current url or current
   * visibility rectangle co-ordinates. If the image is currently in the
   * unclipped state, a call to this method will cause a transition to the
   * clipped state. The image will be cached first by the Gadget container.
   *
   * @param url the image URL
   * @param left the horizontal coordinate of the upper-left vertex of the
   *          visibility rectangle
   * @param top the vertical coordinate of the upper-left vertex of the
   *          visibility rectangle
   * @param width the width of the visibility rectangle
   * @param height the height of the visibility rectangle
   */
  @Override
  public void setUrlAndVisibleRect(String url, int left, int top, int width,
      int height) {
    super.setUrlAndVisibleRect(getCachedImageUrl(url), left, top, width,
        height);
  }
}
