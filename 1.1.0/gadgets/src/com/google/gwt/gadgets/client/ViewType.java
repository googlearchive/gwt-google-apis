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
 * Contains all the view type objects.
 */
public class ViewType {
  /**
   * A view where the gadget is displayed in a very large mode.
   */
  public static final JavaScriptObject CANVAS = getCanvas();
  /**
   * A view where the gadget is displayed in a small area usually on a page with
   * other gadgets.
   */
  public static final JavaScriptObject HOME = getHome();
  /**
   * A demo view of the gadget.
   */
  public static final JavaScriptObject PREVIEW = getPreview();
  /**
   * A view where the gadget is displayed in a small area usually on a page with
   * other gadgets.
   */
  public static final JavaScriptObject PROFILE = getProfile();

  private static final native JavaScriptObject getCanvas() /*-{
    return $wnd.gadget.views.ViewType.CANVAS;
   }-*/;

  private static final native JavaScriptObject getHome() /*-{
    return $wnd.gadget.views.ViewType.HOME;
   }-*/;

  private static final native JavaScriptObject getPreview() /*-{
    return $wnd.gadget.views.ViewType.PREVIEW;
   }-*/;

  private static final native JavaScriptObject getProfile() /*-{
    return $wnd.gadget.views.ViewType.PROFILE;
   }-*/;
}
