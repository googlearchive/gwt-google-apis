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
package com.google.gwt.visualization.client.events;

import com.google.gwt.ajaxloader.client.Properties;

/**
 * This class handles zoom out events for visualizations such as GeoMap.
 */
public abstract class ZoomOutHandler extends Handler {
  /**
   * The zoom out event is fired when the user clicks the zoom out button. The
   * ZoomOutEvent class is a placeholder.
   */
  public static class ZoomOutEvent {
  }

  public abstract void onZoomOut(ZoomOutEvent event);

  @Override
  protected void onEvent(Properties properties) {
    onZoomOut(new ZoomOutEvent());
  }
}
