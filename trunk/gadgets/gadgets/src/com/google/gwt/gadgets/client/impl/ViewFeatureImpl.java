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
package com.google.gwt.gadgets.client.impl;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.gadgets.client.View;
import com.google.gwt.gadgets.client.ViewFeature;

/**
 * Provides operations for dealing with views.
 */
public class ViewFeatureImpl implements ViewFeature {
  /**
   * This is used to return view parameters.
   */
  public static class ParamsImpl extends JavaScriptObject implements Params {
    protected ParamsImpl() {
    }

    public final native String getParam(String name) /*-{
      return this[name];
    }-*/;
  }

  /**
   * This is used to return views.
   */
  public static class ViewsImpl extends JavaScriptObject implements Views {
    protected ViewsImpl() {
    }

    public final native View getView(String name) /*-{
      return this[name];
    }-*/;
  }

  protected ViewFeatureImpl() {
  }

  public final native View getCurrentView() /*-{
    return $wnd.gadgets.views.getCurrentView();
  }-*/;

  public final native ParamsImpl getParams() /*-{
    return this.getParams();
  }-*/;

  public final native ViewsImpl getSupportedViews() /*-{
    return $wnd.gadgets.views.getSupportedViews();
  }-*/;

  public final native void requestNavigateTo(View view) /*-{
    $wnd.gadgets.views.requestNavigateTo(view);
  }-*/;
}
