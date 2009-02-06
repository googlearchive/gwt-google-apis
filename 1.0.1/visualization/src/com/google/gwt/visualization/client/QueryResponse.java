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

/**
 * Represents a response of a query execution as received from the data source.
 * 
 * @see <a
 *      href="http://code.google.com/apis/visualization/documentation/reference.html#QueryResponse">
 *      QueryResponse API Reference</a>
 */
public class QueryResponse extends JavaScriptObject {
  protected QueryResponse() {
  }

  public final native DataTable getDataTable() /*-{
    return this.getDataTable();
  }-*/;

  public final native String getDetailedMessage() /*-{
    return this.getDetailedMessage();
  }-*/;

  public final native String getMessage() /*-{
    return this.getMessage();
  }-*/;

  public final native boolean hasWarning() /*-{
    return this.hasWarning();
  }-*/;

  public final native boolean isError() /*-{
    return this.isError();
  }-*/;

  // TODO : add getReasons()
}