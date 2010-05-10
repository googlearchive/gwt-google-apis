/*
 * Copyright 2010 Google Inc.
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
import com.google.gwt.ajaxloader.client.Properties.TypeException;

/**
 * This class handles statechange events for visualizations such as Table.
 */
public abstract class StateChangeHandler extends Handler {
  /**
   * This event is triggered when the user interacts with the chart in some way.
   * Call getState() to learn the current state of the chart.
   */
  public static class StateChangeEvent {
  }

  public abstract void onStateChange(StateChangeEvent event);

  @Override
  protected void onEvent(Properties event) throws TypeException {
    onStateChange(new StateChangeEvent());
  }
}