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
package com.google.gwt.maps.client.control;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.impl.ControlImpl;
import com.google.gwt.maps.jsio.client.Exported;
import com.google.gwt.maps.jsio.client.FieldName;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;

/**
 * Control is the abstract base class for all Map Control types.
 */
public class Control {

  /**
   * This class should be extended to create a custom control type.
   */
  public abstract static class CustomControl extends Control {

    private final ControlPosition defaultPosition;

    /**
     * Create a new CustomControl which is not printable or selectable.
     * 
     * @param defaultPosition location of the controls on the map.
     */
    protected CustomControl(ControlPosition defaultPosition) {
      this(defaultPosition, false, false);
    }

    /**
     * Create a new CustomControl which can be either printable, selectable or
     * both.
     * 
     * @param defaultPosition
     * @param printable indicates that the control should be visible in the
     *          print output of the map
     * @param selectable indicates that the control will contain text that
     *          should be selectable.
     */
    protected CustomControl(ControlPosition defaultPosition, boolean printable,
        boolean selectable) {
      super(ControlImpl.impl.createControl(printable, selectable));
      ControlImpl.impl.bind(super.jsoPeer, this);
      this.defaultPosition = defaultPosition;
    }

    /**
     * Returns to the map if the control contains selectable text.
     * 
     * @return <code>true</code> if the control contains selectable text.
     */
    public abstract boolean isSelectable();

    /**
     * Gets the default position of this control.
     * 
     * The default position is used with the one argument
     * {@link MapWidget#addControl(Control)} method.
     * 
     * @return the default position of this control
     */
    @Exported
    protected final ControlPosition getDefaultPosition() {
      return defaultPosition;
    }

    /**
     * Initializes this control with the given map.
     * 
     * This method should instantiate and return the control's widget.
     * 
     * @param map the Google map
     * @return the widget that should be attached to the map
     */
    protected abstract Widget initialize(MapWidget map);

    /**
     * Initializes the control. Called from JavaScript.
     * 
     */
    @FieldName("initialize")
    @Exported
    Element initializeControl(MapWidget map) {
      Widget w = initialize(map);
      map.addControlWidget(w);
      return w.getElement();
    }
  }

  /**
   * Factory method used by JSIO to wrap an existing JSO in a GWT Java object.
   * 
   * @param jsoPeer
   * @return a new instance of control
   */
  public static Control createPeer(JavaScriptObject jsoPeer) {
    return new Control(jsoPeer);
  }

  /**
   * The JSO being wrapped by this class.
   */
  protected final JavaScriptObject jsoPeer;

  /**
   * Constructor for creating a new instance of this class from an existing JSO.
   * 
   * @param jsoPeer
   */
  protected Control(JavaScriptObject jsoPeer) {
    this.jsoPeer = jsoPeer;
  }
}