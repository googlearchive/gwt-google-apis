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
package com.google.gwt.maps.client.control;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.impl.ControlImpl;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Control is the abstract base class for all
 */
public class Control {

  public static abstract class CustomControl extends Control {

    private final ControlPosition defaultPosition;

    protected CustomControl(ControlPosition defaultPosition) {
      this(defaultPosition, false, false);
    }

    protected CustomControl(ControlPosition defaultPosition, boolean printable,
        boolean selectable) {
      super(ControlImpl.impl.createControl(printable, selectable));
      ControlImpl.impl.bind(super.jsoPeer, this);
      this.defaultPosition = defaultPosition;
    }

    /**
     * Gets the default position of this control.
     * 
     * The default position is used with the one argument
     * {@link MapWidget#addControl(Control)} method.
     * 
     * @return the default position of this control
     * @gwt.fieldName getDefaultPosition
     * @gwt.exported
     */
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
     * @gwt.fieldName initialize
     * @gwt.exported
     */
    Element initializeControl(MapWidget map) {
      Widget w = initialize(map);
      // Wrap the widget in a flow panel to prevent click events from being
      // supressed
      FlowPanel panel = new FlowPanel();
      panel.add(w);
      map.addControlWidget(panel);
      return panel.getElement();
    }
  }

  static Control createPeer(JavaScriptObject jsoPeer) {
    return new Control(jsoPeer);
  }

  private final JavaScriptObject jsoPeer;

  protected Control(JavaScriptObject jsoPeer) {
    this.jsoPeer = jsoPeer;
  }
}