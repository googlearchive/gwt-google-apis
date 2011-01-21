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

import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.TimeOfDay.BadTimeException;
import com.google.gwt.visualization.client.visualizations.Table;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Test for DataColumn.
 */
public class DataColumnTest extends VisualizationTest {
  
  public void testIntegers_indexOutOfBounds() {
    loadApi(new Runnable() {
      public void run() {
        try {
          DataColumn.integers(DataTable.create(), 0);
          fail();
        } catch (RuntimeException expected) {}
      }
    });
  }
  
  public void testIntegers_invalidColumnType() {
    loadApi(new Runnable() {
      public void run() {
        DataTable table = DataTable.create();
        table.addColumn(ColumnType.STRING);
        try {
          DataColumn.integers(table, 0);
          fail();
        } catch (IllegalArgumentException expected) {}
      }
    });
  }
  
  public void testIntegers() {
    loadApi(new Runnable() {
      public void run() {
        DataTable data = DataTable.create();
        data.addColumn(ColumnType.NUMBER);
        addRow(data, 1);
        addRow(data, (Object) null);
        addRow(data, 2);
        assertContentsInOrder(DataColumn.integers(data, 0),
            1, null, 2);
      }
    });
  }

  public void testBooleans_indexOutOfBounds() {
    loadApi(new Runnable() {
      public void run() {
        try {
          DataColumn.booleans(DataTable.create(), 0);
          fail();
        } catch (RuntimeException expected) {}
      }
    });
  }
  
  public void testBooleans_invalidColumnType() {
    loadApi(new Runnable() {
      public void run() {
        DataTable data = DataTable.create();
        data.addColumn(ColumnType.STRING);
        try {
          DataColumn.booleans(data, 0);
          fail();
        } catch (IllegalArgumentException expected) {}
      }
    });
  }
  
  public void testBooleans() {
    loadApi(new Runnable() {
      public void run() {
        DataTable data = DataTable.create();
        data.addColumn(ColumnType.BOOLEAN);
        addRow(data, true);
        addRow(data, (Object) null);
        addRow(data, false);
        assertContentsInOrder(DataColumn.of(data, 0),
            true, null, false);
      }
    });
  }
  
  public void testDoubles_indexOutOfBounds() {
    loadApi(new Runnable() {
      public void run() {
        try {
          DataColumn.doubles(DataTable.create(), 0);
          fail();
        } catch (RuntimeException expected) {}
      }
    });
  }
  
  public void testDoubles_invalidColumnType() {
    loadApi(new Runnable() {
      public void run() {
        DataTable data = DataTable.create();
        data.addColumn(ColumnType.STRING);
        try {
          DataColumn.doubles(data, 0);
          fail();
        } catch (IllegalArgumentException expected) {}
      }
    });
  }
  
  public void testDoubles() {
    loadApi(new Runnable() {
      public void run() {
        DataTable data = DataTable.create();
        data.addColumn(ColumnType.NUMBER);
        addRow(data, 1);
        addRow(data, (Object) null);
        addRow(data, 2.5d);
        DataColumn<?> column = DataColumn.of(data, 0);
        assertContentsInOrder(column, 1d, null, 2.5d);
        @SuppressWarnings("unchecked")
        DataColumn<Object> unsafeColumn = (DataColumn<Object>) column;
        unsafeColumn.setValue(2, 2);
        assertContentsInOrder(column, 1d, null, 2d);
      }
    });
  }
  
  public void testNumbers_indexOutOfBounds() {
    loadApi(new Runnable() {
      public void run() {
        try {
          DataColumn.numbers(DataTable.create(), 0);
          fail();
        } catch (RuntimeException expected) {}
      }
    });
  }
  
  public void testNumbers_invalidColumnType() {
    loadApi(new Runnable() {
      public void run() {
        DataTable data = DataTable.create();
        data.addColumn(ColumnType.STRING);
        try {
          DataColumn.numbers(data, 0);
          fail();
        } catch (IllegalArgumentException expected) {}
      }
    });
  }
  
  public void testNumbers() {
    loadApi(new Runnable() {
      public void run() {
        DataTable data = DataTable.create();
        data.addColumn(ColumnType.NUMBER);
        addRow(data, 1);
        addRow(data, (Object) null);
        addRow(data, 2.5);
        DataColumn<Number> column = DataColumn.numbers(data, 0);
        assertContentsInOrder(column, 1d, null, 2.5d);
        assertEquals(2.5d, column.set(2, 2).doubleValue());
        assertContentsInOrder(column, 1d, null, 2d);
      }
    });
  }
  
  public void testStrings_indexOutOfBounds() {
    loadApi(new Runnable() {
      public void run() {
        try {
          DataColumn.strings(DataTable.create(), 0);
          fail();
        } catch (RuntimeException expected) {}
      }
    });
  }
  
  public void testStrings_invalidColumnType() {
    loadApi(new Runnable() {
      public void run() {
        DataTable data = DataTable.create();
        data.addColumn(ColumnType.NUMBER);
        try {
          DataColumn.strings(data, 0);
          fail();
        } catch (IllegalArgumentException expected) {}
      }
    });
  }
  
  public void testStrings() {
    loadApi(new Runnable() {
      public void run() {
        DataTable data = DataTable.create();
        data.addColumn(ColumnType.STRING);
        addRow(data, "1");
        addRow(data, (String) null);
        addRow(data, "2");
        assertContentsInOrder(DataColumn.of(data, 0),
            "1", null, "2");
      }
    });
  }
  
  public void testDates_indexOutOfBounds() {
    loadApi(new Runnable() {
      public void run() {
        try {
          DataColumn.dates(DataTable.create(), 0);
          fail();
        } catch (RuntimeException expected) {}
      }
    });
  }
  
  public void testDates_invalidColumnType() {
    loadApi(new Runnable() {
      public void run() {
        DataTable data = DataTable.create();
        data.addColumn(ColumnType.STRING);
        try {
          DataColumn.dates(data, 0);
          fail();
        } catch (IllegalArgumentException expected) {}
      }
    });
  }
  
  public void testDates() {
    loadApi(new Runnable() {
      public void run() {
        DataTable data = DataTable.create();
        data.addColumn(ColumnType.DATE);
        addRow(data, new Date(1));
        addRow(data, (Object) null);
        addRow(data, new Date(2));
        assertContentsInOrder(DataColumn.of(data, 0),
            new Date(1), null, new Date(2));
      }
    });
  }
  
  public void testTimeOfDays_indexOutOfBounds() {
    loadApi(new Runnable() {
      public void run() {
        try {
          DataColumn.timeOfDays(DataTable.create(), 0);
          fail();
        } catch (RuntimeException expected) {}
      }
    });
  }
  
  public void testTimeOfDays_invalidColumnType() {
    loadApi(new Runnable() {
      public void run() {
        DataTable data = DataTable.create();
        data.addColumn(ColumnType.STRING);
        try {
          DataColumn.timeOfDays(data, 0);
          fail();
        } catch (IllegalArgumentException expected) {}
      }
    });
  }
  
  public void testTimeOfDays() {
    loadApi(new Runnable() {
      public void run() {
        DataTable data = DataTable.create();
        data.addColumn(ColumnType.TIMEOFDAY);
        addRow(data, timeOfDay(1, 2, 3, 4));
        addRow(data, (Object) null);
        addRow(data, timeOfDay(2, 3, 4, 5));
        assertContentsInOrder(DataColumn.of(data, 0),
            timeOfDay(1, 2, 3, 4), null, timeOfDay(2, 3, 4, 5));
      }
    });
  }
  
  public void testOf_byColumnId() {
    loadApi(new Runnable() {
      public void run() {
        DataTable data = DataTable.create();
        data.addColumn(ColumnType.STRING, "string", "str");
        data.addColumn(ColumnType.NUMBER, "integer", "int");
        assertEquals(0, DataColumn.of(data, "str").getColumnIndex());
        assertEquals(1, DataColumn.of(data, "int").getColumnIndex());
        try {
          DataColumn.of(data, "unknown");
          fail();
        } catch (IllegalArgumentException expected) {}
      }
    });
  }
  
  private static void addRow(DataTable data, Object... values) {
    int row = data.addRow();
    Iterator<Object> iterator = Arrays.asList(values).iterator();
    for (DataColumn<?> column : DataColumn.getAllColumns(data)) {
      @SuppressWarnings("unchecked") // right value type is expected
      DataColumn<Object> c = (DataColumn<Object>) column;
      c.setValue(row, iterator.next());
    }
  }
  
  private static void assertContentsInOrder(List<?> actual, Object... expected) {
    assertEquals(Arrays.asList(expected), actual);
  }
  
  private static TimeOfDay timeOfDay(int hour, int minute, int second, int millis) {
    try {
      return new TimeOfDay(hour, minute, second, millis);
    } catch (BadTimeException e) {
      throw new RuntimeException(e);
    }
  }
  
  @Override
  protected String getVisualizationPackage() {
    return Table.PACKAGE;
  }
}
