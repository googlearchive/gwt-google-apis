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
package com.google.gwt.maps.client.event;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.MapWidget;

import java.util.EventObject;

/**
 * Provides an interface to the callback method for
 * {@link MapWidget#getEarthInstance(EarthInstanceHandler)}.
 */
public interface EarthInstanceHandler {

  /**
   * Encapsulates the arguments for the callback to
   * {@link MapWidget#getEarthInstance(EarthInstanceHandler)}.
   */
  @SuppressWarnings("serial")
  class EarthInstanceEvent extends EventObject {
    JavaScriptObject earthPlugin = null;

    public EarthInstanceEvent(MapWidget source, JavaScriptObject earthPlugin) {
      super(source);
      this.earthPlugin = earthPlugin;
    }

    /**
     * Returns the instance of the earth that generated this event. If the Earth
     * plugin fails to initialize, the value returned will be <code>null</code>.
     * 
     * @return the instance of the earth that generated this event.
     */
    public JavaScriptObject getEarthInstance() {
      return earthPlugin;
    }
  }

  void onEarthInstance(EarthInstanceEvent event);
}
