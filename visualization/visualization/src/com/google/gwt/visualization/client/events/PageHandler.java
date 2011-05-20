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
package com.google.gwt.visualization.client.events;

import com.google.gwt.ajaxloader.client.Properties;
import com.google.gwt.ajaxloader.client.Properties.TypeException;

/**
 * This class handles page events for visualizations such as Table.
 */
public abstract class PageHandler extends Handler {
  /**
   * This event is fired when the user clicks a button to change the current
   * page on the visualization, such as "next" or "back".
   */
  public class PageEvent {
    private int page;

    public PageEvent(int page) {
      this.page = page;
    }

    public int getPage() {
      return page;
    }
  }

  public abstract void onPage(PageEvent event);

  @Override
  protected void onEvent(Properties properties) throws TypeException {
    Double boxedPage =  properties.getNumber("page");
    int page = boxedPage == null ? -1 : boxedPage.intValue();
    onPage(new PageEvent(page));
  }
}