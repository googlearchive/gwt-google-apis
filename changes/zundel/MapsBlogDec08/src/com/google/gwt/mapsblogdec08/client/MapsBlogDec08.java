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
package com.google.gwt.mapsblogdec08.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.StackPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MapsBlogDec08 implements EntryPoint {

  private DockPanel app = new DockPanel();
  private StackPanel leftPanel = new StackPanel() {
    @Override
    public void showStack(int index) {
      super.showStack(index);
      showPanel(index);
    }
  };

  AbsolutePanel mainPanel = new AbsolutePanel();
  RetreatPresentation retreat = new RetreatPresentation();
  CapitalSearch capitalSearch = new CapitalSearch();
  SourceCode sourceCode = new SourceCode();

  // GWT module entry point method.
  public void onModuleLoad() {
    app.setSize("600px", "350px");

    // Setup a disclosure panel on the left to browse the different
    // presentations
    leftPanel.setSize("150px", "325px");
    leftPanel.add(new HTML(
    "Type in the SuggestBox control to search for a world capital."),
    "Capital Finder");
    leftPanel.add(new HTML("<p>A demonstration of using "
        + "polyline, directions and infowindows in Maps, "
        + "combined with a custom control and sprite images.</p>"),
        "Retreat Ideas");
    leftPanel.add(new HTML("<p>Some of the source code for this application"),
        "Source Code.</p> <a href=\"http://gwt-google-apis.google.com/svn/changes/zundel/MapsBlogDec08/\">Full Source</a>");

    app.add(leftPanel, DockPanel.WEST);

    mainPanel.setSize("450px", "325px");
    app.add(mainPanel, DockPanel.CENTER);

    // mainPanel.add(retreat);
    RootPanel.get("mapsBlog").add(app);
  }

  private void showPanel(int index) {
    mainPanel.clear();
    switch (index) {
      case 0:
        mainPanel.add(capitalSearch);
        break;
      case 1:
        mainPanel.add(retreat);
        break;
      case 2:
        mainPanel.add(sourceCode);
        break;
      default:
        assert false;
    }
  }
}
