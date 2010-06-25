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

import com.google.gwt.user.client.ui.RootPanel;

/**
 * Provides access to the dynamic height feature.
 */
public interface DynamicHeightFeature {

  /**
   * Causes the Gadget to be resized.  Make sure you have added your
   * content to the RootPanel returned by {@link #getContentDiv()}.
   */
  void adjustHeight();

  /**
   * Use this method to retrieve the RootPanel to attach all gadget content to
   * when using DynamicHeight.
   *
   * @return a pointer to a panel that will allow your gadget to be resizable
   *         when you add content here.
   */
  RootPanel getContentDiv();
}