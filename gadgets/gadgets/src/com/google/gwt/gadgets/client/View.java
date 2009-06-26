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
 * This class is the base for all view objects.
 */
public class View extends JavaScriptObject {
  protected View() {
  }

  /**
   * Returns the name of the view.
   *
   * TODO(haeberling): The spec says, that getName could also return ViewType.
   */
  public final native String getName() /*-{
    return this.getName();
  }-*/;

  /**
   * Returns a string URI template conforming to the IETF spec draft with
   * variables for substitution.
   */
  public final native String getUrlTemplate() /*-{
    return this.getUrlTemplate();
  }-*/;

  /**
   * Returns true if the gadget is the only visible gadget in this view.
   */
  public final native boolean isOnlyVisibleGadget() /*-{
    return this.isOnlyVisibleGadget();
  }-*/;
}
