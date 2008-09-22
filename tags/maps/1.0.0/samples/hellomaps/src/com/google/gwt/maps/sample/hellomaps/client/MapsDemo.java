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
package com.google.gwt.maps.sample.hellomaps.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;

/**
 * All HelloMaps demos extend this class.
 */
public abstract class MapsDemo extends Composite {

  /**
   * This inner static class creates a factory method to return an instance of
   * MapsDemo.
   */
  public abstract static class MapsDemoInfo {

    private MapsDemo instance;

    /**
     * @return a new instance of MapsDemo
     */
    public abstract MapsDemo createInstance();

    /**
     * @return an HTML description of this demo
     */
    public HTML getDescriptionHTML() {
      return new HTML("<p><i>Description not provided.</i></p>\n"
          + "<p>(Add an implementation of <code>getDescriptionHTML()</code> "
          + "for this demo)</p>");
    }

    /**
     * Factory method for MapsDemo.
     * 
     * @return an instance of this MapsDemo class
     */
    public MapsDemo getInstance() {
      if (instance == null) {
        instance = createInstance();
      }
      return instance;
    }

    public abstract String getName();
  }

  /**
   * Method that gets called by the main demo when this demo is now active on
   * the screen.
   */
  public void onShow() {
  }
}
