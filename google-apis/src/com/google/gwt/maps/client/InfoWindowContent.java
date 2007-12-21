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
import com.google.gwt.jsio.client.JSWrapper;
import com.google.gwt.jsio.client.impl.Extractor;
import com.google.gwt.maps.client.event.InfoWindowListener;
import com.google.gwt.maps.client.impl.InfoWindowImpl;
import com.google.gwt.maps.client.impl.InfoWindowOptionsImpl;
import com.google.gwt.maps.client.util.JsUtil;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class InfoWindowContent {

  /**
   * 
   */
  public static final class InfoWindowTab {
    // TODO: DELETE ME! (needs to function w/o)
    private static final Extractor __extractor = new Extractor() {
      public Object fromJS(JavaScriptObject jso) {
        throw new UnsupportedOperationException();
      }

      public JavaScriptObject toJS(Object o) {
        return ((InfoWindowTab) o).jsoPeer;
      }
    };

    private final JavaScriptObject jsoPeer;

    private final Widget widget;

    public InfoWindowTab(String label, String content) {
      widget = null;
      jsoPeer = InfoWindowImpl.impl.createInfoWindowTab(label, content);
    }

    public InfoWindowTab(String label, Widget content) {
      widget = content;
      jsoPeer =
          InfoWindowImpl.impl.createInfoWindowTab(label, content.getElement());
    }

    protected Widget getWidget() {
      return widget;
    }
  }

  /**
   * 
   */
  public static final class MapBlowupContent extends InfoWindowContent {
    public MapBlowupContent() {
      super(TYPE_MAP_BLOWUP);
    }

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

  private final JavaScriptObject options =
      InfoWindowOptionsImpl.impl.construct();

  private final int type;

  private final List<Widget> widgets = new ArrayList<Widget>();

  private List<InfoWindowListener> infoWindowListeners = null;

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

  public InfoWindowContent(InfoWindowTab[] tabs, int selectedTab) {
    this(tabs);
    InfoWindowOptionsImpl.impl.setSelectedTab(options, selectedTab);
  }
  
  public InfoWindowContent(String content) {
    Element e = DOM.createDiv();
    DOM.setInnerHTML(e, content);
    this.content = e;
    this.type = TYPE_ELEMENT;
  }

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
   * Add an InfoWindowListener that will get called back when the info window
   * pops open or closed. Multiple listeners can be added.
   * 
   * @param l listener to add.
   */
  public void addInfoWindowListener(InfoWindowListener l) {
    if (infoWindowListeners == null) {
      infoWindowListeners = new ArrayList<InfoWindowListener>();
    }
    infoWindowListeners.add(l);
  }

  public void removeInfoWindowListeners() {
    infoWindowListeners = null;
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
   * map that was not on a marker. If set to true, the info window will not
   * close when the map is clicked. The default value is false
   * 
   * @param noCloseFlag Pass true to leave the window open when the map is
   *        clicked.
   */
  public void setNoCloseOnClick(boolean noCloseFlag) {
    InfoWindowOptionsImpl.impl.setNoCloseOnClick(options, noCloseFlag);
  }

  protected JavaScriptObject getContent() {
    return content;
  }

  protected List<InfoWindowListener> getInfoWindowListeners() {
    return infoWindowListeners;
  }

  protected JavaScriptObject getOptions() {
    return options;
  }

  protected int getType() {
    return type;
  }

  protected List<Widget> getWidgets() {
    return widgets;
  }

}
