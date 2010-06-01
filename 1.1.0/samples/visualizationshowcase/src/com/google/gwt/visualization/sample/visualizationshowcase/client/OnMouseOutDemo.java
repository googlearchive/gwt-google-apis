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
package com.google.gwt.visualization.sample.visualizationshowcase.client;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.visualization.client.events.OnMouseOutHandler;

/**
 * Demo for OnMouseOutHandler that can be applied to any visualization.
 */
public class OnMouseOutDemo extends OnMouseOutHandler {

  private final Label label;
  
  OnMouseOutDemo(Label label) {
    this.label = label;
  }

  @Override
  public void onMouseOutEvent(OnMouseOutEvent event) { 
    StringBuffer b = new StringBuffer();
    b.append(" row: ");
    b.append(event.getRow());
    b.append(", column: ");
    b.append(event.getColumn());
    label.setText("Mouse out of " + b.toString()); 
  }
}
