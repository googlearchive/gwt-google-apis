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
package com.google.gwt.visualization.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.TimeOfDay.BadTimeException;
import com.google.gwt.visualization.client.visualizations.PieChart;

import java.util.Date;

/**
 * Tests for the CommonOptions class. Uses PieChart because PieChart is the only
 * viz that uses CommonOptions rather than its subclass, CommonChartOptions.
 */
public class DataTableTest extends VisualizationTest {
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

  public void testBoolean() {
    loadApi(new Runnable() {
      public void run() {
        DataTable data = DataTable.create();
        data.addColumn(ColumnType.BOOLEAN);
        data.addRows(1);
        data.setValue(0, 0, true);
        assertTrue(data.getValueBoolean(0, 0));
      }
    });
  }

  public void testDataTableFromJso() {
    DataTable table = DataTable.create(nativeData(), 0.6);
    assertNotNull(table);
    assertEquals(2, table.getNumberOfColumns());
    assertEquals(5, table.getNumberOfRows());
    assertEquals("task", table.getColumnId(0));
    assertEquals("hours", table.getColumnId(1));
    assertEquals(11, table.getValueInt(0, 1));
  }

  public void testDate() {
    loadApi(new Runnable() {
      @SuppressWarnings("deprecation")
      public void run() {
        DataTable data = DataTable.create();
        data.addColumn(ColumnType.DATE);
        data.addRows(1);
        Date date = new Date(108, 1, 1);
        data.setValue(0, 0, date);
        assertEquals(date, data.getValueDate(0, 0));
        data.setValue(0, 0, (Date) null);
        assertNull(data.getValueDate(0, 0));
        assertTrue(data.isValueNull(0, 0));
        data.setCell(0, 0, date, null, null);
        assertEquals(date, data.getValueDate(0, 0));
      }
    });
  }

  public void testDouble() {
    loadApi(new Runnable() {
      public void run() {
        DataTable data = DataTable.create();
        data.addColumn(ColumnType.NUMBER);
        data.addRows(1);
        data.setValue(0, 0, 1.5);
        assertEquals(1.5, data.getValueDouble(0, 0));
      }
    });
  }

  public void testInteger() {
    loadApi(new Runnable() {
      public void run() {
        DataTable data = DataTable.create();
        data.addColumn(ColumnType.NUMBER);
        data.addRows(1);
        data.setValue(0, 0, -1);
        assertEquals(-1, data.getValueInt(0, 0));
      }
    });
  }

  public void testString() {
    loadApi(new Runnable() {
      public void run() {
        DataTable data = DataTable.create();
        data.addColumn(ColumnType.STRING);
        data.addRows(1);
        String hello = "world";
        data.setValue(0, 0, hello);
        assertEquals(hello, data.getValueString(0, 0));
        data.setValue(0, 0, (String) null);
        assertNull(data.getValueString(0, 0));
        assertTrue(data.isValueNull(0, 0));
      }
    });
  }

  public void testTimeOfDay() {
    loadApi(new Runnable() {
      public void run() {
        DataTable data = DataTable.create();
        data.addColumn(ColumnType.TIMEOFDAY);
        data.addRows(1);
        try {
          TimeOfDay timeof = new TimeOfDay(11, 11, 30, 500);
          data.setValue(0, 0, timeof);
          assertEquals(timeof, data.getValueTimeOfDay(0, 0));
        } catch (BadTimeException e) {
          fail(e.getMessage());
        }
        data.setValue(0, 0, (TimeOfDay) null);
        assertNull(data.getValueTimeOfDay(0, 0));
        assertTrue(data.isValueNull(0, 0));
      }
    });
  }

  @Override
  protected String getVisualizationPackage() {
    return PieChart.PACKAGE;
  }
}
