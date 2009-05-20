/*
 * Copyright 2009 Google Inc.
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
package com.google.gwt.maps.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;

/**
 * Wrapper for GGoogleBarOptions, passed as an argument to construct
 * {@link MapOptions}.
 */
public class GoogleBarOptions extends JavaScriptObject {

  /**
   * Returns a new instance of GoogleBarOptions.
   * 
   * @return a new instance of GoogleBarOptions.
   */
  public static native GoogleBarOptions newInstance() /*-{
    // A complex constructor works around inlining bug. See GWT issue 3568
    // http://code.google.com/p/google-web-toolkit/issues/detail?id=3568
    var obj;
    obj = new $wnd.Object();
    return obj;
  }-*/;

  protected GoogleBarOptions() {
    // Protected constructor required for JSO Overlays
  }

  /**
   * Indicates the parameters to use for the display of advertising when using
   * the GoogleBar. Advertising is enabled in the GoogleBar by default.
   * Configure these options to add information on how this advertising should
   * be displayed and to what AdSense accounts it should be linked (to gain
   * revenue).
   * 
   * @param adsOptions options for how advertising should be displayed.
   */
  public final native GoogleBarOptions setAdsOptions(
      GoogleBarAdsOptions adsOptions) /*-{
    this.adsOptions = adsOptions
    return this;
  }-*/;

  /**
   * Allows you to specify the target for links embedded within the search
   * results of the GoogleBar. Blank is the default value, which specifies that
   * links will open within a new window.
   * 
   * This method will override other settings of the link target.
   */
  public final native GoogleBarOptions setLinkTargetBlank() /*-{
    this.linkTarget = $wnd.G_GOOGLEBAR_LINK_TARGET_BLANK;
    return this;
  }-*/;

  /**
   * Allows you to specify the target for links embedded within the search
   * results of the GoogleBar. Blank is the default value, which specifies that
   * links will open within a new window.
   * 
   * This method will override other settings of the link target.
   */
  public final native GoogleBarOptions setLinkTargetParent() /*-{
    this.linkTarget = $wnd.G_GOOGLEBAR_LINK_TARGET_PARENT;
    return this;
  }-*/;

  /**
   * Allows you to specify the target for links embedded within the search
   * results of the GoogleBar.
   * 
   * This method will override other settings of the link target.
   */
  public final native GoogleBarOptions setLinkTargetSelf() /*-{
    this.linkTarget = $wnd.G_GOOGLEBAR_LINK_TARGET_SELF;
    return this;
  }-*/;

  /**
   * Allows you to specify the target for links embedded within the search
   * results of the GoogleBar.
   * 
   * This method will override other settings of the link target.
   */
  public final native GoogleBarOptions setLinkTargetTop() /*-{
    this.linkTarget = $wnd.G_GOOGLEBAR_LINK_TARGET_TOP;
    return this;
  }-*/;

  /**
   * Indicates that searches from the map's GGoogleBar should return all types
   * of results (KML, businesses, geocodes, etc).
   * 
   * This will override any other setting of the listing type.
   */
  public final native GoogleBarOptions setListingTypeBlended() /*-{
    this.listingType = $wnd.G_GOOGLEBAR_TYPE_BLENDED_RESULTS;
    return this;
  }-*/;

  /**
   * Indicates that searches from the map's GGoogleBar should return only
   * results from indexed KML/KMZ/GeoRSS files.
   * 
   * This will override any other setting of the listing type.
   */
  public final native GoogleBarOptions setListingTypeKmlOnly() /*-{
    this.listingType = $wnd.G_GOOGLEBAR_TYPE_KMLONLY_RESULTS;
    return this;
  }-*/;

  /**
   * Indicates that searches from the map's GGoogleBar should return only
   * business and geocode results.
   * 
   * This will override any other setting of the listing type.
   */
  public final native GoogleBarOptions setListingTypeLocalOnly() /*-{
    this.listingType = $wnd.G_GOOGLEBAR_TYPE_LOCALONLY_RESULTS;
    return this;
  }-*/;

  /**
   * Lets you specify the style of the search result list for the GoogleBar,
   * passing a block-level DOM Element which places the list within a container
   * of your choice (typically a <div> element). This will override any other
   * setting of the result list.
   * 
   * @param resultList a DOM element of your choice to display the results.
   */
  public final native GoogleBarOptions setResultListElement(//
      Element resultList) /*-{
    this.resultList = resultList;
    return this;
  }-*/;

  /**
   * Lets you specify the style of the search result list for the GoogleBar as
   * inline, meaning the result list is displayed in tabular form on the map.
   * This will override any other setting of the result list.
   */
  public final native GoogleBarOptions setResultListInline() /*-{
    this.resultList = $wnd.G_GOOGLEBAR_RESULT_LIST_INLINE;
    return this;
  }-*/;

  /**
   * Lets you specify the style of the search result list for the GoogleBar as
   * the default style, where the result list is hidden and replaced with
   * next/previous buttons..
   */
  public final native GoogleBarOptions setResultListSuppress() /*-{
    this.resultList = $wnd.G_GOOGLEBAR_RESULT_LIST_SUPPRESS;
    return this;
  }-*/;

  /**
   * When set to <code>true</code>, displays the GoogleBar text search box
   * (provided the control is enabled and the map is loaded). By default, the
   * text search box within the control is hidden and only expanded upon
   * clicking the control's magnifying glass. Note: when setting the style
   * property to "new", the GoogleBar text search box is always shown and this
   * setting is ignored.
   * 
   * @param showOnLoad pass <code>true</code> to display the GoogleBar text
   *          search box.
   */
  public final native GoogleBarOptions setShowOnLoad(boolean showOnLoad) /*-{
    this.showOnLoad = showOnLoad;
    return this;
  }-*/;

  /**
   * Indicates the style in use by the GGoogleBar. Setting style to "new"
   * enables the GoogleBar's revamped look and feel. (This style will become the
   * default in the near future.)
   * 
   * @param style set to "new" to enable GoogleBar's revamped look and feel.
   */
  public final native GoogleBarOptions setStyle(String style) /*-{
    this.style = style;
    return this;
  }-*/;

  /**
   * Suppresses displaying the first result within its own info window upon
   * completion of a search in the GoogleBar (which is the default behavior).
   * 
   * @param value pass <code>true</code> to suppress display of the first result
   *          immediately upon return of the search results.
   */
  public final native GoogleBarOptions setSuppressInitialResultSelection(
      boolean value) /*-{
    this.suppressInitialResultSelection = value;
    return this;
  }-*/;

  /**
   * This property suppresses automatic panning and zooming to the set of
   * results upon completion of a search in the GoogleBar. (This setting
   * suppresses the default behavior.)
   * 
   * @param value pass <code>true</code> to suppress automatic panning and
   *          zooming to the first set of results.
   */
  public final native GoogleBarOptions setSuppressZoomToBounds(//
      boolean value) /*-{
    this.suppressZoomToBounds = value;
    return this;
  }-*/;

  /**
   * TODO(zundel): not implemented
   * 
   * onIdleCallback Function This property specifies a callback function to be
   * invoked when the GoogleBar finishes searching and the search results are
   * dismissed.
   */

  /**
   * TODO(zundel): not implemented
   * 
   * onSearchCompleteCallback Function This property specifies a callback
   * function to be invoked when the GoogleBar finishes searching and the search
   * completes. It is passed the GlocalSearch object associated with the search
   * control. This callback function is called before results are placed on the
   * map or into the results list.
   */

  /**
   * TODO(zundel): not implemented
   * 
   * onGenerateMarkerHtmlCallback Function This property lets you specify a
   * callback function to be invoked when the info window for a search result
   * marker is opened. The function should be passed a GMarker, generated HTML
   * string, and GlocalSearchResult (in that order) and must return the modified
   * HTML string to be displayed in the info window.
   */

  /**
   * TODO(zundel): not implemented
   * 
   * onMarkersSetCallback Function This property lets you specify a callback
   * function to be invoked when the GGoogleBar completes creation of its
   * markers and places them on the map. This function must be passed an array
   * of objects of the form {result: GlocalSearch, marker: GMarker}.
   */
}
