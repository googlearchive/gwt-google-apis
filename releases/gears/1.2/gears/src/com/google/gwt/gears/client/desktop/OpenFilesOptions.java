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
package com.google.gwt.gears.client.desktop;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.gears.client.impl.Utils;

/**
 * A class that encapsulates the options to the
 * {@link Desktop#openFiles(OpenFilesHandler, OpenFilesOptions)} method.
 * 
 */
public final class OpenFilesOptions extends JavaScriptObject {

  public static OpenFilesOptions create() {
    return JavaScriptObject.createObject().cast();
  }

  protected OpenFilesOptions() {
    // required for overlay types
  }

  /**
   * By default, all files on the local disk are selectable. If a filter is
   * provided, only files matching the filter are selectable. The user can turn
   * the filter off, so be aware that selected files may not match the filter.
   * The filter is an array of Internet content types and/or file extensions.
   * Example: ['text/html', '.txt', 'image/*']
   * 
   * @param filter An array of content types or file extensions to filter by
   * @return this instance
   */
  public OpenFilesOptions setFilter(String[] filter) {
    this.setFilter(Utils.toJavaScriptArray(filter));
    return this;
  }

  /**
   * By default, the user may select multiple files. If true, the user is
   * limited to selecting only one file.
   * 
   * @param singleFile If true, the user may select only a single file
   * @return this instance
   */
  public native OpenFilesOptions setSingleFile(boolean singleFile)/*-{
    this.singleFile = singleFile;
    return this;
  }-*/;

  private native void setFilter(JavaScriptObject filter)/*-{
    this.filter = filter;
  }-*/;
}
