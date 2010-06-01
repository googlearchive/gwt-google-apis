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
package com.google.gwt.visualization.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayInteger;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.visualizations.PieChart;

public class DataViewTest extends VisualizationTest {

  private static void assertArrayEquals(JsArrayInteger actual, int... expected) {
    assertEquals(expected.length, actual.length());
    for (int i = 0; i < expected.length; i++) {
      assertEquals(expected[i], actual.get(i));
    }
  }

  private static native JavaScriptObject nativeData() /*-{
    return {
       cols: [{id: 'task', label: 'Task', type: 'string'},
                {id: 'hours', label: 'Hours per Day', type: 'number'}],
       rows: [{c:[{v: 'Work'}, {v: 11}]},
              {c:[{v: 'Eat'}, {v: 2}]},
              {c:[{v: 'Commute'}, {v: 2}]},
              {c:[{v: 'Watch TV'}, {v:2}]},
              {c:[{v: 'Sleep'}, {v:7, f:'7.000'}]}
             ]
     };
  }-*/;
  DataTable table;

  DataView view;

  public void testColumnIndicesMapping() {
    loadApi(new Runnable() {
      public void run() {
        view.setColumns(new int[]{1, 0});
        assertEquals(1, view.getTableColumnIndex(0));
        assertEquals(0, view.getTableColumnIndex(1));
        assertEquals(1, view.getViewColumnIndex(0));
        assertEquals(0, view.getViewColumnIndex(1));
        assertArrayEquals(view.getViewColumns(), 1, 0);
      }
    });
  }

  public void testColumnIndicesMapping2() {
    loadApi(new Runnable() {
      public void run() {
        view.setColumns(new int[]{1});
        assertEquals(1, view.getTableColumnIndex(0));
        assertEquals(-1, view.getViewColumnIndex(0));
        assertEquals(0, view.getViewColumnIndex(1));
        assertArrayEquals(view.getViewColumns(), 1);
      }
    });
  }

  public void testHideColumns() {
    loadApi(new Runnable() {
      public void run() {
        view.hideColumns(new int[]{0});
        assertEquals(7, view.getValueInt(4, 0));
        assertEquals(11, view.getValueInt(0, 0));
      }
    });
  }

  public void testHideRowsIndices() {
    loadApi(new Runnable() {
      public void run() {
        view.hideRows(new int[]{0, 2, 4});
        assertEquals(2, view.getNumberOfRows());
        assertEquals("Eat", view.getValueString(0, 0));
        assertEquals("Watch TV", view.getValueString(1, 0));
        assertEquals(2, view.getValueInt(0, 1));
        assertEquals(2, view.getValueInt(1, 1));
      }
    });
  }

  public void testHideRowsRange() {
    loadApi(new Runnable() {
      public void run() {
        view.hideRows(1, 2);
        assertEquals(3, view.getNumberOfRows());
        assertEquals("Work", view.getValueString(0, 0));
        assertEquals("Watch TV", view.getValueString(1, 0));
        assertEquals("Sleep", view.getValueString(2, 0));
        assertEquals(11, view.getValueInt(0, 1));
        assertEquals(2, view.getValueInt(1, 1));
        assertEquals(7, view.getValueInt(2, 1));
      }
    });
  }

  public void testRowIndicesMapping() {
    loadApi(new Runnable() {
      public void run() {
        view.setRows(new int[]{4, 0, 1});
        assertEquals(1, view.getViewRowIndex(0));
        assertEquals(2, view.getViewRowIndex(1));
        assertEquals(-1, view.getViewRowIndex(2));
        assertEquals(-1, view.getViewRowIndex(3));
        assertEquals(0, view.getViewRowIndex(4));
        assertEquals(4, view.getTableRowIndex(0));
        assertEquals(0, view.getTableRowIndex(1));
        assertEquals(1, view.getTableRowIndex(2));
        assertArrayEquals(view.getViewRows(), 4, 0, 1);
      }
    });
  }

  public void testSetColumns() {
    loadApi(new Runnable() {
      public void run() {
        view.setColumns(new int[]{1, 0});
        assertEquals("Sleep", view.getValueString(4, 1));
        assertEquals("Work", view.getValueString(0, 1));
        assertEquals(7, view.getValueInt(4, 0));
        assertEquals(11, view.getValueInt(0, 0));
      }
    });
  }

  public void testSetRowsIndices() {
    loadApi(new Runnable() {
      public void run() {
        view.setRows(new int[]{4, 0, 1});
        assertEquals(3, view.getNumberOfRows());
        assertEquals("Sleep", view.getValueString(0, 0));
        assertEquals("Work", view.getValueString(1, 0));
        assertEquals("Eat", view.getValueString(2, 0));
        assertEquals(7, view.getValueInt(0, 1));
        assertEquals(11, view.getValueInt(1, 1));
        assertEquals(2, view.getValueInt(2, 1));
      }
    });
  }

  public void testSetRowsRange() {
    loadApi(new Runnable() {
      public void run() {
        view.setRows(1, 2);
        assertEquals(2, view.getNumberOfRows());
        assertEquals("Eat", view.getValueString(0, 0));
        assertEquals("Commute", view.getValueString(1, 0));
        assertEquals(2, view.getValueInt(0, 1));
        assertEquals(2, view.getValueInt(1, 1));
      }
    });
  }

  public void testUnderlyingDataTable() {
    loadApi(new Runnable() {
      public void run() {
        assertEquals("task", view.getColumnId(0));
        assertEquals("hours", view.getColumnId(1));
        assertEquals(ColumnType.STRING, view.getColumnType(0));
        assertEquals(ColumnType.NUMBER, view.getColumnType(1));
        assertEquals(2, view.getNumberOfColumns());
        assertEquals(5, view.getNumberOfRows());
        assertEquals(11, view.getValueInt(0, 1));
        assertEquals("Watch TV", view.getValueString(3, 0));
      }
    });
  }

  @Override
  protected String getVisualizationPackage() {
    return PieChart.PACKAGE;
  }

  @Override
  protected void gwtSetUp() throws Exception {
    table = DataTable.create(nativeData());
    view = DataView.create(table);
  }
}
