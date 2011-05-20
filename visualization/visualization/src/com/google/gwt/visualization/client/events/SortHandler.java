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
 * This class handles sort events for visualizations such as Table.
 */
public abstract class SortHandler extends Handler {
  /**
   * This event is triggered when the user clicks on a column heading.
   */
  public class SortEvent {
    private int column;
    private boolean ascending;

    public SortEvent(boolean ascending, int column) {
      this.ascending = ascending;
      this.column = column;
    }

    public int getColumn() {
      return column;
    }

    public boolean isAscending() {
     return ascending;
    }
  }

  public abstract void onSort(SortEvent event);

  @Override
  protected void onEvent(Properties event) throws TypeException {
    boolean ascending = event.getBoolean("ascending");
    Double boxedColumn = event.getNumber("column");
    int column = boxedColumn == null ? -1 : boxedColumn.intValue();
    onSort(new SortEvent(ascending, column));
  }
}