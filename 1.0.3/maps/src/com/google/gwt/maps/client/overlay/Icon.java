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
import com.google.gwt.core.client.JsArrayInteger;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.geom.Size;

/**
 * Wrapper for the Maps API GIcon class, which is like a marker, but allows you
 * to change the shape and other properties.
 */
public class Icon extends JavaScriptObject {

  public static final Icon DEFAULT_ICON = getDefaultIcon();

  public static native Icon getDefaultIcon() /*-{
    return $wnd.G_DEFAULT_ICON;
  }-*/;
  
  /**
   * Construct a new Icon object.
   * 
   */  
  public static native Icon newInstance() /*-{
    var icon = new $wnd.GIcon();
    // Workaround for problem in the Maps API - issue 124
    icon.iconAnchor = new $wnd.GPoint(0,0);
    return icon;
  }-*/;
  
  /**
   * Construct an icon from an existing Icon object.
   * 
   * @param icon the object to copy.
   */  
  public static native Icon newInstance(Icon icon) /*-{
    return new $wnd.GIcon(icon); 
  }-*/;

  /**
   * Construct an icon from an image loaded from a URL.
   * 
   * @param imageUrl a URL to the image to use for the icon.
   */
  public static native Icon newInstance(String imageUrl) /*-{
    var icon = new $wnd.GIcon(null, imageUrl);
    // Workaround for problem in the Maps API - issue 124
    icon.iconAnchor = new $wnd.GPoint(0,0);
    return icon;
  }-*/;
  
  protected Icon() {
    // Protected constructor required from JS overlays
  }

  /**
   * Returns the pixel coordinate offsets (relative to the the value
   * {@link Icon#getIconAnchor()}) of the cross image when an icon is dragged.
   * 
   * @return the pixel coordinate offsets relative to the the value
   *         {@link Icon#getIconAnchor()}) of the cross image when an icon is
   *         dragged.
   */
  public final native Point getDragCrossAnchor() /*-{
    return this.dragCrossAnchor;
  }-*/;

  /**
   * Returns the cross image URL when an icon is dragged.
   * 
   * @return the cross image URL when an icon is dragged.
   */
  public final native String getDragCrossImageUrl() /*-{
    return this.dragCrossImage;
  }-*/;
  
  /**
   * Returns the pixel size of the cross image when an icon is dragged.
   * 
   * @return the pixel size of the cross image when an icon is dragged.
   */
  public final native Size getDragCrossSize() /*-{
    return this.dragCrossSize;
  }-*/;

  /**
   * Returns the pixel coordinate relative to the top left corner of the icon
   * image at which this icon is anchored to the map.
   * 
   * @return the pixel coordinate relative to the top left corner of the icon
   *         image at which this icon is anchored to the map.
   */
  public final native Point getIconAnchor() /*-{
    return this.iconAnchor;
  }-*/;

  /**
   * Returns the pixel size of the foreground image of the icon.
   * 
   * @return the pixel size of the foreground image of the icon.
   */
  public final native Size getIconSize() /*-{
    return this.iconSize;
  }-*/;

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
  public final native JsArrayInteger getImageMap() /*-{
    return this.imageMap;
  }-*/;
 
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
  public final int[] getImageMapArray() {
    JsArrayInteger imageMap = getImageMap();
    int[] returnValue = new int[imageMap.length()];
    for (int i = 0; i < imageMap.length(); i++) {
      returnValue[i] = imageMap.get(i);
    }
    return returnValue;
  }

  /**
   * Returns the foreground image URL of the icon.
   * 
   * @return the foreground image URL of the icon.
   */
  public final native String getImageURL() /*-{
    return this.image;
  }-*/;

  /**
   * Returns the pixel coordinate relative to the top left corner of the icon
   * image at which the info window is anchored to this icon.
   * 
   * @return the pixel coordinate relative to the top left corner of the icon
   *         image at which the info window is anchored to this icon.
   */
  public final native Point getInfoWindowAnchor() /*-{
    return this.infoWindowAnchor;
  }-*/;

  /**
   * Returns the distance in pixels in which a marker will visually "rise"
   * vertically when dragged.
   * 
   * @return the distance in pixels in which a marker will visually "rise"
   *         vertically when dragged.
   */
  public final native int getMaxHeight() /*-{
    return this.maxHeight;
  }-*/;

  /**
   * Returns the URL of the foreground icon image used for printed maps in
   * Firefox/Mozilla. It must be the same size as the main icon image given by
   * {@link Icon#setImageURL(String)}. This method is needed because of browser
   * differences in Mozilla/Firefox.
   * 
   * @return the URL used for printing this icon in Mozilla.
   */
  public final native String getMozPrintImageURL() /*-{
    return this.mozPrintImage;
  }-*/;

  /**
   * Returns the URL of the foreground icon image used for printed maps. It must
   * be the same size as the main icon image given by image.
   * 
   * @return the URL of the foreground icon image used for printed maps. It must
   *         be the same size as the main icon image given by image.
   */
  public final native String getPrintImageURL() /*-{
    return this.printImage;
  }-*/;

  /**
   * Returns the pixel size of the shadow image.
   * 
   * @return the pixel size of the shadow image.
   */
  public final native Size getShadowSize() /*-{
    return this.shadowSize;
  }-*/;

  /**
   * Returns the shadow image URL of the icon.
   * 
   * @return the shadow image URL of the icon.
   */
  public final native String getShadowURL() /*-{
    return this.shadow;
  }-*/;

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
  public final native String getTransparentImageURL() /*-{
    return this.transparent;
  }-*/;

  /**
   * Sets the pixel coordinate offsets relative to the the value
   * {@link Icon#getIconAnchor()}) of the cross image when an icon is dragged.
   * 
   * @param anchor the pixel coordinate offsets relative to the the value
   *          {@link Icon#getIconAnchor()}) of the cross image when an icon is
   *          dragged.
   */
  public final native void setDragCrossAnchor(Point anchor) /*-{
    this.dragCrossAnchor = anchor;
  }-*/;

  /**
   * Sets the cross image URL when an icon is dragged.
   * 
   * @param url the cross image URL when an icon is dragged.
   */
  public final native void setDragCrossImageURL(String url) /*-{
    this.dragCrossImage = url;
  }-*/;

  /**
   * Sets the pixel size of the cross image when an icon is dragged.
   * 
   * @param size the pixel size of the cross image when an icon is dragged.
   */
  public final native void setDragCrossSize(Size size) /*-{
    this.dragCrossSize = size;
  }-*/;

  /**
   * Sets the pixel coordinate relative to the top left corner of the icon image
   * at which this icon is anchored to the map.
   * 
   * @param anchor the pixel coordinate relative to the top left corner of the
   *          icon image at which this icon is anchored to the map.
   */
  public final native void setIconAnchor(Point anchor) /*-{
    this.iconAnchor = anchor;
  }-*/;

  /**
   * Sets the pixel size of the foreground image of the icon.
   * 
   * @param size The pixel size of the foreground image of the icon.
   */
  public final native void setIconSize(Size size) /*-{
    this.iconSize = size;
  }-*/;

  /**
   * Sets the clickable part of the icon image in browsers other than Internet
   * Explorer. See {@link Icon#setTransparentImageURL(String)} for Internet
   * Explorer.
   * 
   * @param imageMap an array of integers representing the x/y coordinates of
   *          the image map we should use to specify the clickable part of the
   *          icon image in browsers other than Internet Explorer.
   */
  public final void setImageMap(int[] imageMap) {
    JsArrayInteger array = (JsArrayInteger) createArray();
    for (int i = 0; i < imageMap.length; i++) {
      array.set(i, imageMap[i]);
    }
    setImageMap(array);
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
  public final native void setImageMap(JsArrayInteger imageMap) /*-{
    this.imageMap = imageMap;
  }-*/;

  /**
   * Sets the foreground image URL of the icon.
   * 
   * @param url the foreground image URL of the icon.
   */
  public final native void setImageURL(String url) /*-{
    this.image = url;
  }-*/;

  /**
   * Sets the pixel coordinate relative to the top left corner of the icon image
   * at which the info window is anchored to this icon.
   * 
   * @param anchor the pixel coordinate relative to the top left corner of the
   *          icon image at which the info window is anchored to this icon.
   */
  public final native void setInfoWindowAnchor(Point anchor) /*-{
    this.infoWindowAnchor = anchor;
  }-*/;

  /**
   * Sets the distance in pixels in which a marker will visually "rise"
   * vertically when dragged.
   * 
   * @param height the distance in pixels in which a marker will visually "rise"
   *          vertically when dragged.
   */
  public final native void setMaxHeight(int height) /*-{
    this.maxHeight = height;
  }-*/;

  /**
   * Sets the URL of the foreground icon image used for printed maps in
   * Firefox/Mozilla. It must be the same size as the main icon image given by
   * {@link Icon#setImageURL(String)}. This method is needed because of browser
   * differences in Mozilla/Firefox.
   * 
   * @param url the URL of the foreground icon image used for printed maps in
   *          Firefox/Mozilla.
   */
  public final native void setMozPrintImageURL(String url) /*-{
    this.mozPrintImage = url;
  }-*/;

  /**
   * Sets the URL of the foreground icon image used for printed maps. It must be
   * the same size as the main icon image given by image.
   * 
   * @param url the URL of the foreground icon image used for printed maps.
   */
  public final native void setPrintImageURL(String url) /*-{
    this.printImage = url;
  }-*/;

  /**
   * Sets the pixel size of the shadow image.
   * 
   * @param size the pixel size of the shadow image.
   */
  public final native void setShadowSize(Size size) /*-{
    this.shadowSize = size;
  }-*/;

  /**
   * Sets the shadow image URL of the icon.
   * 
   * @param url the shadow image URL of the icon.
   */
  public final native void setShadowURL(String url) /*-{
    this.shadow = url;
  }-*/;

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
  public final native void setTransparentImageURL(String url) /*-{
    this.transparent = url;
  }-*/;
}
