/*
 * Copyright 2009 Google Inc.
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

package com.google.gwt.gadgets.client;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Provides operations for dealing with views.
 */
public class ViewFeature implements GadgetFeature {
  protected ViewFeature() {
  }

  /**
   * Returns the current view.
   */
  public final native View getCurrentView() /*-{
    return $wnd.gadgets.views.getCurrentView();
  }-*/;

  public final native Params getParams() /*-{
    return this.getParams();
  }-*/;

  /**
   * Returns all views that are supported by the container.
   */
  public final native Views getSupportedViews() /*-{
    return $wnd.gadgets.views.getSupportedViews();
  }-*/;

  /**
   * Moves the current view, to the new one
   * 
   * @param view The new view.
   */
  public final native void requestNavigateTo(View view) /*-{
    $wnd.gadgets.views.requestNavigateTo(view);
  }-*/;

  /**
   * This is used to return view parameters.
   */
  public static class Params extends JavaScriptObject {
    protected Params() {
    }

    public final native String getParam(String name) /*-{
      return this[name];
    }-*/;
  }

  /**
   * This is used to return views.
   */
  public static class Views extends JavaScriptObject {
    protected Views() {
    }

    /**
     * Returns a view given the name as a string.
     * 
     * @param name the name of the view
     * @return the view with the given name or null
     */
    public final native View getView(String name) /*-{
      return this[name];
    }-*/;
  }
}
