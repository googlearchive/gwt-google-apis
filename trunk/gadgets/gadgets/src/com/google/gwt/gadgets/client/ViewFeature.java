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
package com.google.gwt.gadgets.client;

/**
 * Provides operations for dealing with views.
 */
public interface ViewFeature {

  /**
   * This is used to return view parameters.
   */
  static interface Params {

    String getParam(String name);
  }

  /**
   * This is used to return views.
   */
  static interface Views {

    /**
     * Returns a view given the name as a string.
     *
     * @param name the name of the view
     * @return the view with the given name or null
     */
    View getView(String name);
  }

  /**
   * Returns the current view.
   */
  View getCurrentView();

  Params getParams();

  /**
   * Returns all views that are supported by the container.
   */
  Views getSupportedViews();

  /**
   * Moves the current view, to the new one
   *
   * @param view The new view.
   */
  void requestNavigateTo(View view);
}