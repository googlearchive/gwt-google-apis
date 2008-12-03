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

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.visualization.client.Selectable;
import com.google.gwt.visualization.client.Selection;
import com.google.gwt.visualization.client.SelectionHelper;
import com.google.gwt.visualization.client.events.SelectHandler;


/**
 * Demo for SelectHandler that can be applied to any Selectable visualization.
 */
class SelectionDemo extends SelectHandler {
  @SuppressWarnings("unused")
  private final Selectable viz;
  private final Label label;

  SelectionDemo(Selectable viz, Label label) {
    this.viz = viz;
    this.label = label;
  }

  @Override
  public void onSelect(SelectEvent event) {
    StringBuffer b = new StringBuffer();
    Selection s = getSelection();
    for (int i = 0; i < s.getLength(); ++i) {
      if (s.isCell(i)) {
        b.append(" cell ");
        b.append(s.getRow(i));
        b.append(":");
        b.append(s.getColumn(i));
      } else if (s.isRow(i)) {
        b.append(" row ");
        b.append(s.getRow(i));
      } else {
        b.append(" column ");
        b.append(s.getColumn(i));
      }
    }
    label.setText("selection changed " + b.toString()); 
  }

  private Selection getSelection() {
    return SelectionHelper.getSelection(viz);
  }
}
