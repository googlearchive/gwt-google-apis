/*
 * Copyright 2007 Google Inc.
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
import com.google.gwt.maps.client.impl.InfoWindowImpl;
import com.google.gwt.maps.client.impl.InfoWindowOptionsImpl;
import com.google.gwt.maps.client.impl.JsUtil;
import com.google.gwt.maps.jsio.client.JSWrapper;
import com.google.gwt.maps.jsio.client.impl.Extractor;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;
import java.util.List;

/**
 * InfoWindowContent describes the type of content that can be displayed in an
 * InfoWindow.
 */
public class InfoWindowContent {

  /*
   * Design Note: This class that wraps the GInfoWindowOptions object and
   * enables us to unify the three Maps API classes for plain info windows
   * (GInfoWindow), Info Windows with tabs (GInfoWindowTab) and blowup info
   * windows into a unified InfoWindow class for the gwt-google-apis version of
   * the Maps API.
   */

  /**
   * Class that allows access to one tab of a tabbed info window.
   */
  public static final class InfoWindowTab {

    // TODO: DELETE ME! (needs to function w/o)
    @SuppressWarnings("unused")
    private static final Extractor<InfoWindowTab> __extractor = new Extractor<InfoWindowTab>() {
      public InfoWindowTab fromJS(JavaScriptObject jso) {
        throw new UnsupportedOperationException();
      }

      public JavaScriptObject toJS(InfoWindowTab o) {
        return o.jsoPeer;
      }
    };

    private final JavaScriptObject jsoPeer;

    private final Widget widget;

    /**
     * Create a new tab on an InfoWindow with the specified HTML string as the
     * content.
     * 
     * @param label The label to display on the tab.
     * @param content The HTML data to display when the tab is in focus.
     */
    public InfoWindowTab(String label, String content) {
      widget = null;
      jsoPeer = InfoWindowImpl.impl.createInfoWindowTab(label, content);
    }

    /**
     * Create a new tab on an InfoWindow with the specified GWT widget as the
     * content.
     * 
     * @param label The label to display on the tab.
     * @param content The widget to display when the tab is in focus.
     */
    public InfoWindowTab(String label, Widget content) {
      widget = content;
      jsoPeer = InfoWindowImpl.impl.createInfoWindowTab(label,
          content.getElement());
    }

    protected Widget getWidget() {
      return widget;
    }
  }

  /**
   * Class that represents the content of a blowup map.
   */
  public static final class MapBlowupContent extends InfoWindowContent {

    /**
     * Create a new blowup map with default parameters for map type and zoom
     * level.
     */
    public MapBlowupContent() {
      super(TYPE_MAP_BLOWUP);
    }

    /**
     * Create a new blowup map.
     * 
     * @param mapType The type of map to display in the blowup.
     * @param zoomLevel The level of zoom to display in the blowup.
     */
    public MapBlowupContent(MapType mapType, int zoomLevel) {
      super(TYPE_MAP_BLOWUP);
      InfoWindowOptionsImpl.impl.setMapType(super.options, mapType);
      InfoWindowOptionsImpl.impl.setZoomLevel(super.options, zoomLevel);
    }

    /**
     * Type of blowup map to show in the info window. Sets the mapType property
     * in a GInfoWindowOptions object. See also the MapBlowupContent class
     * constructor.
     * 
     * @param mapType A valid GMapType value.
     */
    public void setMapType(MapType mapType) {
      InfoWindowOptionsImpl.impl.setMapType(super.options, mapType);
    }

    /**
     * Zoom level of the blowup map in the info window. See also the
     * MapBlowupContent class constructor. Sets the zoomLevel property in a
     * GInfoWindowOptions object.
     * 
     * @param zoomLevel Zoom level of the blowup map in the info window.
     */
    public void setZoomLevel(int zoomLevel) {
      InfoWindowOptionsImpl.impl.setZoomLevel(super.options, zoomLevel);
    }
  }

  protected static final int TYPE_ELEMENT = 0;

  protected static final int TYPE_MAP_BLOWUP = 1;

  protected static final int TYPE_TABBED = 2;

  private final JavaScriptObject content;

  private final JavaScriptObject options = InfoWindowOptionsImpl.impl.construct();

  private final int type;

  private final List<Widget> widgets = new ArrayList<Widget>();

  private Widget windowMaximizedContent;

  /**
   * Create a new InfoWindowContent object given an array of tabs.
   * 
   * @param tabs A populated array of tabs to display in an info window.
   */
  @SuppressWarnings("unchecked")
  public InfoWindowContent(InfoWindowTab[] tabs) {
    this.content = ((JSWrapper) JsUtil.toJsList(tabs)).getJavaScriptObject();
    this.type = TYPE_TABBED;
    for (int i = 0; i < tabs.length; i++) {
      Widget w = tabs[i].getWidget();
      if (w != null) {
        widgets.add(w);
      }
    }
  }

  /**
   * Create a new InfoWindowContent object given an array of tabs, setting the
   * focus to a particular tab.
   * 
   * @param tabs A populated array of tabs to display in an info window.
   * @param selectedTab The tab to select when the info window is displayed.
   */
  public InfoWindowContent(InfoWindowTab[] tabs, int selectedTab) {
    this(tabs);
    InfoWindowOptionsImpl.impl.setSelectedTab(options, selectedTab);
  }

  /**
   * Create a new InfoWindowContent object containing an HTML string to display
   * in the window.
   * 
   * @param content An HTML string to display in the window.
   */
  public InfoWindowContent(String content) {
    Element e = DOM.createDiv();
    DOM.setInnerHTML(e, content);
    this.content = e;
    this.type = TYPE_ELEMENT;
  }

  /**
   * Create the new InfoWindowContent object containing a GWT Widget.
   * 
   * @param content A widget to display in the window.
   */
  public InfoWindowContent(Widget content) {
    this.content = content.getElement();
    this.type = TYPE_ELEMENT;
    widgets.add(content);
  }

  private InfoWindowContent(int type) {
    this.content = null;
    this.type = type;
  }

  /**
   * Returns the underlying GInfoWindowOptions object constructed from the
   * building of this InfoWindowContent object.
   * 
   * @return the underlying GInfoWindowOptions object constructed from the
   *         building of this InfoWindowContent object.
   */
  public JavaScriptObject getOptions() {
    return options;
  }

  /**
   * Specifies content to be shown when the InfoWindow is maximized.
   * 
   * @param windowMaximizedContent content to be shown
   */
  public void setMaxContent(String windowMaximizedContent) {
    InfoWindowOptionsImpl.impl.setMaxContentString(options,
        windowMaximizedContent);
  }

  /**
   * Specifies content to be shown when the InfoWindow is maximized.
   * 
   * @param windowMaximizedContent content to be shown
   */
  public void setMaxContent(Widget windowMaximizedContent) {
    this.windowMaximizedContent = windowMaximizedContent;
    InfoWindowOptionsImpl.impl.setMaxContentElement(options,
        windowMaximizedContent.getElement());
  }

  /**
   * Specifies title to be shown when the InfoWindow is maximized.
   * 
   * @param windowMaximizedTitle
   */
  public void setMaxTitle(String windowMaximizedTitle) {
    InfoWindowOptionsImpl.impl.setMaxTitleString(options, windowMaximizedTitle);
  }

  /**
   * Specifies title to be shown when the InfoWindow is maximized.
   * 
   * @param windowMaximizedTitle Title to be shown
   */
  public void setMaxTitle(Widget windowMaximizedTitle) {
    InfoWindowOptionsImpl.impl.setMaxTitleElement(options,
        windowMaximizedTitle.getElement());
  }

  /**
   * Maximum width of the info window content, in pixels.
   * 
   * @param maxWidth Maximum width of the info window content in pixels
   */
  public void setMaxWidth(int maxWidth) {
    InfoWindowOptionsImpl.impl.setMaxWidth(options, maxWidth);
  }

  /**
   * Indicates whether or not the info window should close for a click on the
   * map that was not on a marker. If set to <code>true</code>, the info window
   * will not close when the map is clicked. The default value is
   * <code>false</code>
   * 
   * @param noCloseFlag Pass <code>true</code> to leave the window open when the
   *          map is clicked.
   */
  public void setNoCloseOnClick(boolean noCloseFlag) {
    InfoWindowOptionsImpl.impl.setNoCloseOnClick(options, noCloseFlag);
  }

  protected JavaScriptObject getContent() {
    return content;
  }

  protected int getType() {
    return type;
  }

  protected List<Widget> getWidgets() {
    return widgets;
  }

  /**
   * Returns the widget passed to
   * {@link InfoWindowContent#setMaxContent(String)}.
   * 
   * @return the widget passed to
   *         {@link InfoWindowContent#setMaxContent(String)}.
   */
  Widget getMaxContent() {
    return windowMaximizedContent;
  }
}
