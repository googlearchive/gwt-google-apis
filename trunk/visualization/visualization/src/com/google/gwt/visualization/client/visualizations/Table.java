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
package com.google.gwt.visualization.client.visualizations;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.Element;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.AbstractDrawOptions;
import com.google.gwt.visualization.client.Selectable;
import com.google.gwt.visualization.client.Selection;
import com.google.gwt.visualization.client.events.Handler;
import com.google.gwt.visualization.client.events.PageHandler;
import com.google.gwt.visualization.client.events.SelectHandler;
import com.google.gwt.visualization.client.events.SortHandler;

/**
 * Table visualization.
 * 
 * @see <a href=
 *      "http://code.google.com/apis/visualization/documentation/gallery/table.html"
 *      > Table Visualization Reference</a>
 */
public class Table extends Visualization<Table.Options> implements Selectable {

  /**
   * Options for drawing the table visualization.
   * 
   */
  public static class Options extends AbstractDrawOptions {

    public static class CssClassNames extends JavaScriptObject {
      protected CssClassNames() {
      }

      public final native void setHeaderCell(String headerCell) /*-{
        this.headerCell = headerCell;
      }-*/;

      public final native void setHeaderRow(String headerRow) /*-{
        this.headerRow = headerRow;
      }-*/;

      public final native void setHoverTableRow(String hoverTableRow) /*-{
        this.hoverTableRow = hoverTableRow;
      }-*/;

      public final native void setOddTableRow(String oddTableRow) /*-{
        this.oddTableRow = oddTableRow;
      }-*/;

      public final native void setRowNumberCell(String rowNumberCell) /*-{
        this.rowNumberCell = rowNumberCell;
      }-*/;

      public final native void setSelectedTableRow(String selectedTableRow) /*-{
        this.selectedTableRow = selectedTableRow;
      }-*/;

      public final native void setTableCell(String tableCell) /*-{
        this.tableCell = tableCell;
      }-*/;

      public final native void setTableRow(String tableRow) /*-{
        this.tableRow = tableRow;
      }-*/;
    }

    /**
     * A parameter passed to several of the setters.
     */
    public static enum Policy {
      DISABLE, ENABLE, EVENT;

      @Override
      public String toString() {
        switch (this) {
          case ENABLE:
            return "enable";
          case EVENT:
            return "event";
          case DISABLE:
            return "disable";
          default:
            // unreachable
            throw new RuntimeException();
        }
      }
    }

    public static final Options create() {
      return JavaScriptObject.createObject().cast();
    }

    protected Options() {
    }

    public final native void setAllowHtml(boolean allowHtml) /*-{
      this.allowHtml = allowHtml;
    }-*/;

    public final native void setAlternatingRowStyle(boolean alternatingRowStyle) /*-{
      this.alternatingRowStyle = alternatingRowStyle;
    }-*/;

    public final native void setCssClassNames(CssClassNames cssClassNames) /*-{
      this.cssClassNames = cssClassNames;
    }-*/;

    public final native void setFirstRowNumber(int rowNumber) /*-{
      this.firstRowNumber = rowNumber;
    }-*/;

    public final native void setHeight(String height) /*-{
      this.height = height;
    }-*/;

    public final void setPage(Policy policy) {
      setPage(policy.toString());
    }

    public final native void setPageSize(int pageSize) /*-{
      this.pageSize = pageSize;
    }-*/;

    public final native void setRtlTable(boolean rtlTable) /*-{
      this.rtlTable = rtlTable;
    }-*/;

    public final native void setScrollLeftStartPosition(int pixels) /*-{
      this.scrollLeftStartPosition = pixels;
    }-*/;

    public final native void setShowRowNumber(boolean showRowNumber) /*-{
      this.showRowNumber = showRowNumber;
    }-*/;

    public final void setSort(Policy policy) {
      setSort(policy.toString());
    }

    public final native void setSortAscending(boolean sortAscending) /*-{
      this.sortAscending = sortAscending;
    }-*/;

    public final native void setSortColumn(int sortColumn) /*-{
      this.sortColumn = sortColumn;
    }-*/;

    public final native void setStartPage(int startPage) /*-{
      this.startPage = startPage;
    }-*/;

    public final native void setWidth(String width) /*-{
      this.width = width;
    }-*/;

    private native void setPage(String page) /*-{
      this.page = page;
    }-*/;

    private native void setSort(String sort) /*-{
      this.sort = sort;
    }-*/;
  }

  public static final String PACKAGE = "table";

  public Table() {
    super();
  }

  public Table(AbstractDataTable data, Options options) {
    super(data, options);
  }

  public final void addPageHandler(PageHandler handler) {
    Handler.addHandler(this, "page", handler);
  }

  public final void addSelectHandler(SelectHandler handler) {
    Selection.addSelectHandler(this, handler);
  }

  public final void addSortHandler(SortHandler handler) {
    Handler.addHandler(this, "sort", handler);
  }

  public final JsArray<Selection> getSelections() {
    return Selection.getSelections(this);
  }

  public final void setSelections(JsArray<Selection> sel) {
    Selection.setSelections(this, sel);
  }

  @Override
  protected native JavaScriptObject createJso(Element parent) /*-{
    return new $wnd.google.visualization.Table(parent);
  }-*/;
}
