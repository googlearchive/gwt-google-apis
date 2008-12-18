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

import com.google.gwt.core.client.GWT;
import com.google.gwt.libideas.resources.client.ImmutableResourceBundle;
import com.google.gwt.libideas.resources.client.TextResource;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * A window to display the source code of this app.
 */
public class SourceCode extends Composite {
  TabPanel tabs = new TabPanel();

  public interface sourceCodeResources extends ImmutableResourceBundle {
    @Resource("CapitalSearch.java")
    TextResource getCapitalSearchSource();

    @Resource("RetreatPresentation.java")
    TextResource getRetreatPresentationSource();
  }

  private sourceCodeResources resources = GWT.create(sourceCodeResources.class);

  public SourceCode() {
    
    tabs.add(makePanel(resources.getCapitalSearchSource()),
        "CapitalSearch.java");
    tabs.add(makePanel(resources.getRetreatPresentationSource()),
        "RetreatPresentation.java");
    tabs.selectTab(0);
    initWidget(tabs);
  }
  
  private Panel makePanel(TextResource textResource) {
    VerticalPanel outerPanel = new VerticalPanel();
    ScrollPanel panel = new ScrollPanel();
    panel.setSize("530px", "360px");
    HTML html = new HTML(procSource(textResource.getText()));
    html.getElement().getStyle().setProperty("fontSize", "small");
    panel.add(html);
    panel.setAlwaysShowScrollBars(true);
    panel.scrollToTop();
    outerPanel.add(panel);
    return outerPanel;
  }
  
  /**
   * Formats the input for HTML display of source code.
   * 
   * @param source
   * @return source code in HTML.
   */
  private String procSource(String source) {
    StringBuilder output = new StringBuilder();
    output.append("<pre>");
    output.append(source.replace("<", "&lt;"));
    output.append("</pre>");
    return output.toString();
    
  }
}
