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

/**
 * SelectionHelper implements selection related functions.
 *
 */
public class SelectionHelper {
  public static void addSelectListener(Visualization<?> viz,  
                                       SelectListener listener) {
    Listener.addListener(viz, "select", listener);
  }
  
  public static final native Selection getSelection(Selectable visualization) /*-{
    return visualization.getSelection();
  }-*/;

  public static final native void setSelection(Selectable visualization, Selection sel) /*-{
    visualization.setSelection(sel);
  }-*/;
  
  protected SelectionHelper() {
  }
}
