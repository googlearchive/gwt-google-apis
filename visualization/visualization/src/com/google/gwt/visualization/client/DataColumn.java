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

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Represents a list view of a data column in an {@link AbstractDataTable}.
 * For example: <pre class="code">   {@code
 *   int columnIndex = ...;
 *   DataColumn<Integer> intColumn =
 *       DataColumn.integers(data, columnIndex);
 *   for (int value : intColumn) {
 *     ...
 *   }
 *   DataColumn<String> stringColumn =
 *       DataColumn.strings(data, stringColumnIndex);
 *   ...
 * }</pre>
 * Or if the column type is unknown: <pre class="code">   {@code
 *   DataColumn<?> column = DataColumn.of(data, columnIndex);
 * }</pre>
 * This is useful for looping through a single column with the Java enhanced for
 * loop. It also allows utilities provided in Java collection framework to be
 * used. For example, {@link java.util.Collections#min(java.util.Collection)},
 * {@link java.util.Collections#max(java.util.Collection)}, {@link
 * java.util.Collections#binarySearch(List, Object)} or Guava utilities such
 * as {@link com.google.common.collect.Iterable.filter} etc.
 * Passing around {@code DataColumn} object is also easier than passing around a
 * data table along with the column index integer.
 *
 * <p>No mutation method such as {@link List#add(Object)} is supported,
 * except {@link List#set(int, Object)}, which writes through to the underlying
 * data if the data is an instance of {@link DataTable}.
 *
 * <p>In order to add a row to the underlying table, use {@link
 * DataTable#addRow()}. And then {@link #setValue} or {@link #set} can be used
 * to set cell values.
 */
public abstract class DataColumn<T> extends AbstractList<T> {

  final AbstractDataTable data;
  final int columnIndex;
  final ColumnType columnType;

  DataColumn(AbstractDataTable table, int columnIndex, ColumnType... compatibleColumnTypes) {
    if (columnIndex < 0 || columnIndex >= table.getNumberOfColumns()) {
      throw new IndexOutOfBoundsException(Integer.toString(columnIndex));
    }
    this.columnType = table.getColumnType(columnIndex);
    if (!Arrays.asList(compatibleColumnTypes).contains(columnType)) {
      throw new IllegalArgumentException(columnType.name());
    }
    this.data = table;
    this.columnIndex = columnIndex;
  }

  /** Returns the column id. @see AbstractDataTable#getColumnId(int) */
  public final String getColumnId() {
    return data.getColumnId(columnIndex);
  }

  /** Returns the column type. @see AbstractDataTable#getColumnType(int) */
  public final ColumnType getColumnType() {
   return data.getColumnType(columnIndex);
  }

  /** Returns the column label. @see AbstractDataTable#getColumnLabel(int) */
  public final String getColumnLabel() {
    return data.getColumnLabel(columnIndex);
  }

  /** Returns the column index. */
  public final int getColumnIndex() {
    return columnIndex;
  }

  /** Returns the underlying table that this object reads from or writes to. */
  public final AbstractDataTable getDataTable() {
    return data;
  }

  /** Returns the cell value at {@code row}. */
  @Override
  public abstract T get(int row);

  /**
   * Sets the cell value at {@code row} to {@code value} and returns the
   * previous cell value. Unsupported unless the underlying table is an instance
   * of {@link DataTable}.
   *
   * <p>When the previous value isn't need, use {@link #setValue(int, Object)}
   * for better performance.
   */
  @Override
  public final T set(int row, T value) {
    T oldValue = get(row);
    setValue(row, value);
    return oldValue;
  }

  /**
   * Sets the cell value at {@code row} to {@code value}. Unsupported unless the
   * underlying table is an instance of {@link DataTable}.
   */
  public final void setValue(int row, T value) {
    DataTable dataTable = (DataTable) data;
    if (value == null) {
      dataTable.setCellNull(row, columnIndex, null, null);
    } else {
      setNonNullValue(row, value);
    }
  }

  /** Returns the number of rows in the underlying table. */
  @Override
  public final int size() {
    return data.getNumberOfRows();
  }

  /**
   * Returns the column identified by {@code columnId} in {@code table}.
   *
   * @throws IllegalArgumentException if {@code columnId} is not found
   */
  public static DataColumn<?> of(AbstractDataTable table, String columnId) {
    return of(table, getColumnIndex(table, columnId));
  }

  /** Returns the column identified by {@code columnIndex} in {@code table}. */
  public static DataColumn<?> of(AbstractDataTable table, int columnIndex) {
    ColumnType columnType = table.getColumnType(columnIndex);
    switch (columnType) {
      case BOOLEAN:
        return booleans(table, columnIndex);
      case NUMBER:
        return numbers(table, columnIndex);
      case STRING:
        return strings(table, columnIndex);
      case DATE:
        return dates(table, columnIndex);
      case DATETIME:
        return datetimes(table, columnIndex);
      case TIMEOFDAY:
        return timeOfDays(table, columnIndex);
      default:
        throw new AssertionError(columnType.name());
    }
  }

  /**
   * Returns the column identified by {@code columnId} in {@code table}.
   *
   * @throws IllegalArgumentException if {@code columnId} is not found
   */
  public static DataColumn<Boolean> booleans(AbstractDataTable table, String columnId) {
    return booleans(table, getColumnIndex(table, columnId));
  }

  /** Returns the column identified by {@code columnIndex} in {@code table}. */
  public static DataColumn<Boolean> booleans(AbstractDataTable table, int columnIndex) {
    return new PrimitiveDataColumn<Boolean>(table, columnIndex, ColumnType.BOOLEAN) {
      @Override
      Boolean getNonNullValue(int row) {
        return data.getValueBoolean(row, columnIndex);
      }
      @Override
      void setNonNullValue(int row, Boolean value) {
        ((DataTable) data).setValue(row, columnIndex, value);
      }
    };
  }

  /**
   * Returns the column identified by {@code columnId} in {@code table}.
   *
   * @throws IllegalArgumentException if {@code columnId} is not found
   */
  public static DataColumn<Integer> integers(AbstractDataTable table, String columnId) {
    return integers(table, getColumnIndex(table, columnId));
  }

  /** Returns the column identified by {@code columnIndex} in {@code table}. */
  public static DataColumn<Integer> integers(AbstractDataTable table, int columnIndex) {
    return new PrimitiveDataColumn<Integer>(table, columnIndex, ColumnType.NUMBER) {
      @Override
      Integer getNonNullValue(int row) {
        return data.getValueInt(row, columnIndex);
      }
      @Override
      void setNonNullValue(int row, Integer value) {
        ((DataTable) data).setValue(row, columnIndex, value);
      }
    };
  }

  /** Returns the column identified by {@code columnIndex} in {@code table}. */
  static DataColumn<Number> numbers(AbstractDataTable table, int columnIndex) {
    return new PrimitiveDataColumn<Number>(table, columnIndex, ColumnType.NUMBER) {
      @Override
      Number getNonNullValue(int row) {
        return data.getValueDouble(row, columnIndex);
      }
      @Override
      void setNonNullValue(int row, Number value) {
        ((DataTable) data).setValue(row, columnIndex, value.doubleValue());
      }
    };
  }

  /**
   * Returns the column identified by {@code columnId} in {@code table}.
   *
   * @throws IllegalArgumentException if {@code columnId} is not found
   */
  public static DataColumn<Double> doubles(AbstractDataTable table, String columnId) {
    return doubles(table, getColumnIndex(table, columnId));
  }

  /** Returns the column identified by {@code columnIndex} in {@code table}. */
  public static DataColumn<Double> doubles(AbstractDataTable table, int columnIndex) {
    return new PrimitiveDataColumn<Double>(table, columnIndex, ColumnType.NUMBER) {
      @Override
      Double getNonNullValue(int row) {
        return data.getValueDouble(row, columnIndex);
      }
      @Override
      void setNonNullValue(int row, Double value) {
        ((DataTable) data).setValue(row, columnIndex, value.doubleValue());
      }
    };
  }

  /**
   * Returns the column identified by {@code columnId} in {@code table}.
   *
   * @throws IllegalArgumentException if {@code columnId} is not found
   */
  public static DataColumn<String> strings(AbstractDataTable table, String columnId) {
    return strings(table, getColumnIndex(table, columnId));
  }

  /** Returns the column identified by {@code columnIndex} in {@code table}. */
  public static DataColumn<String> strings(AbstractDataTable table, int columnIndex) {
    return new DataColumn<String>(table, columnIndex, ColumnType.STRING) {
      @Override
      public String get(int row) {
        return data.getValueString(row, columnIndex);
      }
      @Override
      void setNonNullValue(int row, String value) {
        ((DataTable) data).setValue(row, columnIndex, value);
      }
    };
  }

  /**
   * Returns the column identified by {@code columnId} in {@code table}.
   *
   * @throws IllegalArgumentException if {@code columnId} is not found
   */
  public static DataColumn<Date> dates(AbstractDataTable table, String columnId) {
    return dates(table, getColumnIndex(table, columnId));
  }

  /** Returns the column identified by {@code columnIndex} in {@code table}. */
  public static DataColumn<Date> dates(AbstractDataTable table, int columnIndex) {
    return datetimes(table, columnIndex);
  }

  /**
   * Returns the column identified by {@code columnId} in {@code table}.
   *
   * @throws IllegalArgumentException if {@code columnId} is not found
   */
  public static DataColumn<Date> datetimes(AbstractDataTable table, String columnId) {
    return datetimes(table, getColumnIndex(table, columnId));
  }

  /** Returns the column identified by {@code columnIndex} in {@code table}. */
  public static DataColumn<Date> datetimes(AbstractDataTable table, int columnIndex) {
    return new DataColumn<Date>(table, columnIndex, ColumnType.DATE, ColumnType.DATETIME) {
      @Override
      public Date get(int row) {
        return data.getValueDate(row, columnIndex);
      }
      @Override
      void setNonNullValue(int row, Date value) {
        ((DataTable) data).setValue(row, columnIndex, value);
      }
    };
  }

  /**
   * Returns the column identified by {@code columnId} in {@code table}.
   *
   * @throws IllegalArgumentException if {@code columnId} is not found
   */
  public static DataColumn<TimeOfDay> timeOfDays(AbstractDataTable table, String columnId) {
    return timeOfDays(table, getColumnIndex(table, columnId));
  }

  /** Returns the column identified by {@code columnIndex} in {@code table}. */
  public static DataColumn<TimeOfDay> timeOfDays(AbstractDataTable table, int columnIndex) {
    return new DataColumn<TimeOfDay>(table, columnIndex, ColumnType.TIMEOFDAY) {
      @Override
      public TimeOfDay get(int row) {
        return data.getValueTimeOfDay(row, columnIndex);
      }
      @Override
      void setNonNullValue(int row, TimeOfDay value) {
        ((DataTable) data).setValue(row, columnIndex, value);
      }
    };
  }

  /**
   * Returns a list of all the columns in the specified table, in the order
   * they appear in the table.
   */
  public static List<DataColumn<?>> getAllColumns(AbstractDataTable dataTable) {
    List<DataColumn<?>> columns = new ArrayList<DataColumn<?>>();
    for (int i = 0; i < dataTable.getNumberOfColumns(); i++) {
      columns.add(DataColumn.of(dataTable, i));
    }
    return columns;
  }

  /** Sets the value of {@code row}. {@code value} is not null. */
  abstract void setNonNullValue(int row, T value);

  /**
   * Returns the column index for {@code columnId} in {@code table}.
   *
   * @throws IllegalArgumentException if that column ID is not found
   */
  private static int getColumnIndex(AbstractDataTable table, String columnId) {
    int index = table.getColumnIndex(columnId);
    if (index < 0) {
      throw new IllegalArgumentException(columnId);
    }
    return index;
  }

  private static abstract class PrimitiveDataColumn<T> extends DataColumn<T> {

    PrimitiveDataColumn(
        AbstractDataTable table, int columnIndex, ColumnType columnType) {
      super(table, columnIndex, columnType);
    }

    @Override
    public final T get(int rowIndex) {
      if (data.isValueNull(rowIndex, columnIndex)) {
        return null;
      } else {
        return getNonNullValue(rowIndex);
      }
    }

    abstract T getNonNullValue(int row);
  }
}
