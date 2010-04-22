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

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import java.util.HashMap;
import java.util.Map;

/**
 * A panel with a tree on the left and a display case on the right. Selecting a
 * tree item displays the analogous widget.
 */
public class LeftTabPanel extends Composite {
  /**
   * Allows the caller to pass in objects that will create new widgets every
   * time a link is clicked, to work around the issue that some visualizations
   * become invalid when they are removed from a panel.
   */
  public interface WidgetProvider {
    Widget getWidget();
  }

  private static void setWidget(SimplePanel simple, WidgetProvider provider) {
    simple.clear();
    simple.add(provider.getWidget());
  }

  private final Map<String, WidgetProvider> cogs = new HashMap<String, WidgetProvider>();
  private final VerticalPanel left = new VerticalPanel();
  private final Tree leftTree = new Tree();
  private final HorizontalPanel main = new HorizontalPanel();
  private final SimplePanel right = new SimplePanel();

  public LeftTabPanel() {
    initWidget(main);
    main.add(left);
    left.add(leftTree);
    DecoratorPanel decorator = new DecoratorPanel();
    Style decoratorStyle = decorator.getElement().getStyle();
    decoratorStyle.setPropertyPx("marginLeft", 15);
    Style rightStyle = right.getElement().getStyle();
    rightStyle.setPropertyPx("margin", 5);
    decorator.add(right);

    main.add(decorator);
    leftTree.addSelectionHandler(new SelectionHandler<TreeItem>() {
      public void onSelection(SelectionEvent<TreeItem> event) {
        String name = event.getSelectedItem().getText();
        setWidget(right, cogs.get(name));
      }
    });
  }

  public void add(final WidgetProvider cog, String title) {
    cogs.put(title, cog);
    TreeItem item = new TreeItem(title);
    leftTree.addItem(item);
  }

  public void setWidget(WidgetProvider provider) {
    setWidget(right, provider);
  }
}
