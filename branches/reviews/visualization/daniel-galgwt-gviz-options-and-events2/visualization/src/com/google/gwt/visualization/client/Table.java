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
import com.google.gwt.dom.client.Element;

/**
 * 
 * Table visualization.
 * 
 * @see <a
 *      href="http://code.google.com/apis/visualization/documentation/gallery/table.html">
 *      Table Visualization Reference</a>
 */
public class Table extends Visualization<Table.DrawOptions> 
    implements Selectable {

  /**
   * Options for drawing the table visualization.
   * 
   */
  public static class DrawOptions extends AbstractDrawOptions {
    
    public static enum Policy {
      ENABLE, EVENT, DISABLE;
      
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
    
    public static DrawOptions create() {
      return JavaScriptObject.createObject().cast();
    }

    protected DrawOptions() {
    }
    
    public final native void setAllowHtml(boolean allowHtml) /*-{
      this.allowHtml = allowHtml;
    }-*/;

    public final native void setPageSize(int pageSize) /*-{
      this.pageSize = pageSize;
    }-*/;
    

    public final native void setShowRowNumber(boolean showRowNumber) /*-{
      this.showRowNumber = showRowNumber;
    }-*/;
    
    public final void setPage(Policy policy) {
      setPage(policy.toString());
    }

    public final void setSort(Policy policy) {
      setSort(policy.toString());
    }
    
    private final native void setPage(String page) /*-{
      this.page = page;
    }-*/;
    
    private final native void setSort(String sort) /*-{
      this.sort = sort;
    }-*/;
  }
  
  abstract public class PageListener extends Listener {
    abstract public void onPage(int page);
    
    @Override
    protected void onEvent(Visualization<?> visualization, Properties event) {
      onPage(event.getInt("page"));
    }
  }
  
  abstract public class SortListener extends Listener {
    abstract public void onSort(int column, boolean ascending);
    
    @Override
    protected void onEvent(Visualization<?> visualization, Properties event) {
      onSort(event.getInt("column"), event.getBoolean("ascending"));
    }
  }
  
  public static native Table create(Element parent) /*-{
    return new $wnd.google.visualization.Table(parent);
  }-*/;

  protected Table() {
  }
  
  public final void addPageListener(PageListener listener) {
    Listener.addListener(this, "page", listener);
  }
  
  public final void addSortListener(SortListener listener) {
    Listener.addListener(this, "sort", listener);
  }

  public final void addSelectListener(SelectListener listener) {
    SelectionHelper.addSelectListener(this, listener);
  }

  public final Selection getSelection() {
    return SelectionHelper.getSelection(this);
  }

  public final void setSelection(Selection sel) {
    SelectionHelper.setSelection(this, sel);
  }
}
