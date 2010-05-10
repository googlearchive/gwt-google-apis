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

import com.google.gwt.ajaxloader.client.Properties;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;

/**
 * Represents a query that is sent to a data source.
 * 
 * @see <a
 *      href="http://code.google.com/apis/visualization/documentation/reference.html#Query">
 *      Query API Reference</a>
 */
public class Query extends JavaScriptObject {

  public static enum SendMethod {
    XHR("xhr"), SCRIPT_INJECTION("scriptInjection"), MAKE_REQUEST("makeRequest"), AUTO(
        "auto");

    private String strValue;

    private SendMethod(String value) {
      strValue = value;
    }

    @Override
    public String toString() {
      return strValue;
    }
  }

  public static class Options extends Properties {
    public static Options create() {
      return JavaScriptObject.createObject().cast();
    }

    protected Options() {
    }

    public final void setSendMethod(SendMethod sendMethod) {
      setSendMethod(sendMethod.toString());
    }

    public final native void setMakeRequestParams(JavaScriptObject params) /*-{
      this.makeRequestParams = params;
    }-*/;

    private final native void setSendMethod(String sendMethod) /*-{
      this.sendMethod = sendMethod;
    }-*/;
  }

  /**
   * Callback for sending a query.
   * 
   */
  public interface Callback {
    void onResponse(QueryResponse response);
  }

  public static final native Query create(String dataSource) /*-{
    return new $wnd.google.visualization.Query(dataSource);
  }-*/;

  public static final native Query create(String dataSource, Options options) /*-{
    return new $wnd.google.visualization.Query(dataSource, options);
  }-*/;

  private static void fireAndCatch(UncaughtExceptionHandler handler,
      Callback callback, QueryResponse response) {
    try {
      fireImpl(callback, response);
    } catch (Throwable e) {
      handler.onUncaughtException(e);
    }
  }

  private static void fireImpl(Callback callback, QueryResponse response) {
    callback.onResponse(response);
  }

  @SuppressWarnings("unused")
  private static void onResponseCallback(Callback callback,
      QueryResponse response) {
    UncaughtExceptionHandler handler = GWT.getUncaughtExceptionHandler();
    if (handler != null) {
      fireAndCatch(handler, callback, response);
    } else {
      fireImpl(callback, response);
    }
  }

  protected Query() {
  }

  public final native void send(Callback callback) /*-{
    this.send(function(c) {
      @com.google.gwt.visualization.client.Query::onResponseCallback(Lcom/google/gwt/visualization/client/Query$Callback;Lcom/google/gwt/visualization/client/QueryResponse;)(callback, c);
    });
  }-*/;

  public final native void setQuery(String query) /*-{
    this.setQuery(query);
  }-*/;

  public final native void setRefreshInterval(int timeInSeconds) /*-{
    this.setRefreshInterval(timeInSeconds);
  }-*/;

  public final native void setTimeout(int seconds) /*-{
    this.setTimeout(seconds);
  }-*/;
}