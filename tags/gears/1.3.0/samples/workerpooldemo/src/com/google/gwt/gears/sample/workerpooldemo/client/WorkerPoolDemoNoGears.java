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
package com.google.gwt.gears.sample.workerpooldemo.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * This {@link EntryPoint} will receive control if Gears is not available on the
 * browser that will run the application.
 */
public class WorkerPoolDemoNoGears implements EntryPoint {
  public void onModuleLoad() {
    RootPanel rootPanel = RootPanel.get("demo");
    rootPanel.add(new HTML(
        "<font color=\"red\">ERROR: This browser does not support Google Gears."
        + "  Please install Gears and reload the application."
        + "  Note that GWT Gears applications can only be debugged in hosted mode on Windows.</font>"));
  }
}
