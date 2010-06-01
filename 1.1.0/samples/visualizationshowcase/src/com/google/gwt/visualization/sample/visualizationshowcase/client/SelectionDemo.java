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
package com.google.gwt.visualization.sample.visualizationshowcase.client;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.visualization.client.Selectable;
import com.google.gwt.visualization.client.Selection;
import com.google.gwt.visualization.client.events.SelectHandler;

/**
 * Demo for SelectHandler that can be applied to any Selectable visualization.
 */
class SelectionDemo extends SelectHandler {
  private final Selectable viz;
  private final Label label;

  SelectionDemo(Selectable viz, Label label) {
    this.viz = viz;
    this.label = label;
  }

  @Override
  public void onSelect(SelectEvent event) {
    StringBuffer b = new StringBuffer();
    JsArray<Selection> s = getSelections();
    for (int i = 0; i < s.length(); ++i) {
      if (s.get(i).isCell()) {
        b.append(" cell ");
        b.append(s.get(i).getRow());
        b.append(":");
        b.append(s.get(i).getColumn());
      } else if (s.get(i).isRow()) {
        b.append(" row ");
        b.append(s.get(i).getRow());
      } else {
        b.append(" column ");
        b.append(s.get(i).getColumn());
      }
    }
    label.setText("selection changed " + b.toString()); 
  }

  private JsArray<Selection> getSelections() {
    return viz.getSelections();
  }
}
