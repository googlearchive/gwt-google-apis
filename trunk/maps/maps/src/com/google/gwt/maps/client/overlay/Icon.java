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
package com.google.gwt.maps.client.overlay;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.geom.Size;
import com.google.gwt.maps.client.impl.IconImpl;
import com.google.gwt.maps.client.impl.JsUtil;
import com.google.gwt.maps.jsio.client.JSList;

/**
 * Wrapper for the Maps API GIcon class, which is like a marker, but allows you
 * to change the shape and other properties.
 */
public final class Icon {

  public static final Icon DEFAULT_ICON = createPeer(IconImpl.impl.getDefaultIcon());

  /**
   * Method used by JSNI to wrap return values from JavaScript method calls.
   * 
   * @param jsoPeer JSO to wrap in an Icon object.
   * @return a newly created Icon object that wraps jsoPeer.
   */
  static Icon createPeer(JavaScriptObject jsoPeer) {
    Icon icon = new Icon(jsoPeer);
    return icon;
  }

  private final JavaScriptObject jsoPeer;

  /**
   * Construct a new, empty icon.
   */
  public Icon() {
    jsoPeer = IconImpl.impl.construct();
    // Workaround for problem in the Maps API - issue 124
    setIconAnchor(new Point(0,0));
  }

  /**
   * Construct an icon from an existing Icon object.
   * 
   * @param icon the object to copy.
   */
  public Icon(Icon icon) {
    jsoPeer = IconImpl.impl.construct(icon);
  }

  /**
   * Construct an icon from an image loaded from a URL.
   * 
   * @param imageUrl a URL to the image to use for the icon.
   */
  public Icon(String imageUrl) {
    jsoPeer = IconImpl.impl.construct(null, imageUrl);
    // Workaround for problem in the Maps API - issue 124
    setIconAnchor(new Point(0,0));
  }

  /**
   * Internal constructor - build an Icon from an existing JSO.
   * 
   * @param jsoPeer
   */
  private Icon(JavaScriptObject jsoPeer) {
    this.jsoPeer = jsoPeer;
  }

  /**
   * Returns the pixel coordinate offsets (relative to the the value
   * {@link Icon#getIconAnchor()}) of the cross image when an icon is dragged.
   * 
   * @return the pixel coordinate offsets relative to the the value
   *         {@link Icon#getIconAnchor()}) of the cross image when an icon is
   *         dragged.
   */
  public Point getDragCrossAnchor() {
    return IconImpl.impl.getDragCrossAnchor(jsoPeer);
  }

  /**
   * Returns the cross image URL when an icon is dragged.
   * 
   * @return the cross image URL when an icon is dragged.
   */
  public String getDragCrossImageUrl() {
    return IconImpl.impl.getDragCrossImage(jsoPeer);
  }
  
  /**
   * Returns the pixel size of the cross image when an icon is dragged.
   * 
   * @return the pixel size of the cross image when an icon is dragged.
   */
  public Size getDragCrossSize() {
    return IconImpl.impl.getDragCrossSize(jsoPeer);
  }

  /**
   * Returns the pixel coordinate relative to the top left corner of the icon
   * image at which this icon is anchored to the map.
   * 
   * @return the pixel coordinate relative to the top left corner of the icon
   *         image at which this icon is anchored to the map.
   */
  public Point getIconAnchor() {
    return IconImpl.impl.getIconAnchor(jsoPeer);
  }

  /**
   * Returns the pixel size of the foreground image of the icon.
   * 
   * @return the pixel size of the foreground image of the icon.
   */
  public Size getIconSize() {
    return IconImpl.impl.getIconSize(jsoPeer);
  }

  /**
   * Returns an array of integers representing the x/y coordinates of the image
   * map we should use to specify the clickable part of the icon image in
   * browsers other than Internet Explorer. See
   * {@link Icon#getTransparentImageURL()} for Internet Explorer.
   * 
   * @return an array of integers representing the x/y coordinates of the image
   *         map we should use to specify the clickable part of the icon image
   *         in browsers other than Internet Explorer.
   */
  public int[] getImageMap() {
    JSList<Integer> imageMap = IconImpl.impl.getImageMap(jsoPeer);
    int[] returnValue = new int[imageMap.size()];
    JsUtil.toArray(imageMap, returnValue);
    return returnValue;
  }

  /**
   * Returns the foreground image URL of the icon.
   * 
   * @return the foreground image URL of the icon.
   */
  public String getImageURL() {
    return IconImpl.impl.getImage(jsoPeer);
  }

  /**
   * Returns the pixel coordinate relative to the top left corner of the icon
   * image at which the info window is anchored to this icon.
   * 
   * @return the pixel coordinate relative to the top left corner of the icon
   *         image at which the info window is anchored to this icon.
   */
  public Point getInfoWindowAnchor() {
    return IconImpl.impl.getInfoWindowAnchor(jsoPeer);
  }

  /**
   * Returns the distance in pixels in which a marker will visually "rise"
   * vertically when dragged.
   * 
   * @return the distance in pixels in which a marker will visually "rise"
   *         vertically when dragged.
   */
  public int getMaxHeight() {
    return IconImpl.impl.getMaxHeight(jsoPeer);
  }

  /**
   * Returns the URL of the foreground icon image used for printed maps in
   * Firefox/Mozilla. It must be the same size as the main icon image given by
   * {@link Icon#setImageURL(String)}. This method is needed because of browser
   * differences in Mozilla/Firefox.
   * 
   * @return the URL used for printing this icon in Mozilla.
   */
  public String getMozPrintImageURL() {
    return IconImpl.impl.getMozPrintImage(jsoPeer);
  }

  /**
   * Returns the URL of the foreground icon image used for printed maps. It must
   * be the same size as the main icon image given by image.
   * 
   * @return the URL of the foreground icon image used for printed maps. It must
   *         be the same size as the main icon image given by image.
   */
  public String getPrintImageURL() {
    return IconImpl.impl.getPrintImage(jsoPeer);
  }

  /**
   * Returns the pixel size of the shadow image.
   * 
   * @return the pixel size of the shadow image.
   */
  public Size getShadowSize() {
    return IconImpl.impl.getShadowSize(jsoPeer);
  }

  /**
   * Returns the shadow image URL of the icon.
   * 
   * @return the shadow image URL of the icon.
   */
  public String getShadowURL() {
    return IconImpl.impl.getShadow(jsoPeer);
  }

  /**
   * Returns the URL of a virtually transparent version of the foreground icon
   * image used to capture click events in Internet Explorer. This image should
   * be a 24-bit PNG version of the main icon image with 1% opacity, but the
   * same shape and size as the main icon.
   * 
   * @return the URL of a virtually transparent version of the foreground icon
   *         image used to capture click events in Internet Explorer. This image
   *         should be a 24-bit PNG version of the main icon image with 1%
   *         opacity, but the same shape and size as the main icon.
   */
  public String getTransparentImageURL() {
    return IconImpl.impl.getTransparent(jsoPeer);
  }

  /**
   * Sets the pixel coordinate offsets relative to the the value
   * {@link Icon#getIconAnchor()}) of the cross image when an icon is dragged.
   * 
   * @param anchor the pixel coordinate offsets relative to the the value
   *          {@link Icon#getIconAnchor()}) of the cross image when an icon is
   *          dragged.
   */
  public void setDragCrossAnchor(Point anchor) {
    IconImpl.impl.setDragCrossAnchor(jsoPeer, anchor);
  }

  /**
   * Sets the cross image URL when an icon is dragged.
   * 
   * @param url the cross image URL when an icon is dragged.
   */
  public void setDragCrossImageURL(String url) {
    IconImpl.impl.setDragCrossImage(jsoPeer, url);
  }

  /**
   * Sets the pixel size of the cross image when an icon is dragged.
   * 
   * @param size the pixel size of the cross image when an icon is dragged.
   */
  public void setDragCrossSize(Size size) {
    IconImpl.impl.setDragCrossSize(jsoPeer, size);
  }

  /**
   * Sets the pixel coordinate relative to the top left corner of the icon image
   * at which this icon is anchored to the map.
   * 
   * @param anchor the pixel coordinate relative to the top left corner of the
   *          icon image at which this icon is anchored to the map.
   */
  public void setIconAnchor(Point anchor) {
    IconImpl.impl.setIconAnchor(jsoPeer, anchor);
  }

  /**
   * Sets the pixel size of the foreground image of the icon.
   * 
   * @param size The pixel size of the foreground image of the icon.
   */
  public void setIconSize(Size size) {
    IconImpl.impl.setIconSize(jsoPeer, size);
  }

  /**
   * Sets the clickable part of the icon image in browsers other than Internet
   * Explorer. See {@link Icon#setTransparentImageURL(String)} for Internet
   * Explorer.
   * 
   * @param imageMap an array of integers representing the x/y coordinates of
   *          the image map we should use to specify the clickable part of the
   *          icon image in browsers other than Internet Explorer.
   */
  public void setImageMap(int[] imageMap) {
    IconImpl.impl.setImageMap(jsoPeer, JsUtil.toJsList(imageMap));
  }

  /**
   * Sets the foreground image URL of the icon.
   * 
   * @param url the foreground image URL of the icon.
   */
  public void setImageURL(String url) {
    IconImpl.impl.setImage(jsoPeer, url);
  }

  /**
   * Sets the pixel coordinate relative to the top left corner of the icon image
   * at which the info window is anchored to this icon.
   * 
   * @param anchor the pixel coordinate relative to the top left corner of the
   *          icon image at which the info window is anchored to this icon.
   */
  public void setInfoWindowAnchor(Point anchor) {
    IconImpl.impl.setInfoWindowAnchor(jsoPeer, anchor);
  }

  /**
   * Sets the distance in pixels in which a marker will visually "rise"
   * vertically when dragged.
   * 
   * @param height the distance in pixels in which a marker will visually "rise"
   *          vertically when dragged.
   */
  public void setMaxHeight(int height) {
    IconImpl.impl.setMaxHeight(jsoPeer, height);
  }

  /**
   * Sets the URL of the foreground icon image used for printed maps in
   * Firefox/Mozilla. It must be the same size as the main icon image given by
   * {@link Icon#setImageURL(String)}. This method is needed because of browser
   * differences in Mozilla/Firefox.
   * 
   * @param url the URL of the foreground icon image used for printed maps in
   *          Firefox/Mozilla.
   */
  public void setMozPrintImageURL(String url) {
    IconImpl.impl.setMozPrintImage(jsoPeer, url);
  }

  /**
   * Sets the URL of the foreground icon image used for printed maps. It must be
   * the same size as the main icon image given by image.
   * 
   * @param url the URL of the foreground icon image used for printed maps.
   */
  public void setPrintImageURL(String url) {
    IconImpl.impl.setPrintImage(jsoPeer, url);
  }

  /**
   * Sets the pixel size of the shadow image.
   * 
   * @param size the pixel size of the shadow image.
   */
  public void setShadowSize(Size size) {
    IconImpl.impl.setShadowSize(jsoPeer, size);
  }

  /**
   * Sets the shadow image URL of the icon.
   * 
   * @param url the shadow image URL of the icon.
   */
  public void setShadowURL(String url) {
    IconImpl.impl.setShadow(jsoPeer, url);
  }

  /**
   * The URL of a virtually transparent version of the foreground icon image
   * used to capture click events in Internet Explorer. This image should be a
   * 24-bit PNG version of the main icon image with 1% opacity, but the same
   * shape and size as the main icon.
   * 
   * Note: If you set this value, you must also supply values for
   * {@link Icon#setImageMap(int[])}.
   * 
   * @param url URL of a virtually transparent version of the foreground icon
   *          image used to capture click events in Internet Explorer.
   */
  public void setTransparentImageURL(String url) {
    IconImpl.impl.setTransparent(jsoPeer, url);
  }

  // Temporary method until this class is converted to a JS overlay
  JavaScriptObject getJavaScriptObject() {
    return jsoPeer;
  }
}
