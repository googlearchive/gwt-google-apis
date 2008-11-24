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

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.gears.client.desktop.OpenFilesHandler.OpenFilesEvent;

/**
 * The Desktop module provides an interface for accessing desktop related
 * functionality, such as creating shortcuts or selecting files.
 * <p>
 * <b>Permission</b>
 * <p>
 * Does not require user permission.
 */
public final class Desktop extends JavaScriptObject {

  // Called from JSNI
  @SuppressWarnings("unused")
  private static void fireFilesOpened(OpenFilesHandler handler, File[] files) {
    if (handler == null) {
      return;
    }
    OpenFilesEvent event = new OpenFilesEvent(files);
    UncaughtExceptionHandler ueh = GWT.getUncaughtExceptionHandler();
    if (ueh != null) {
      try {
        handler.onOpenFiles(event);
      } catch (Throwable e) {
        ueh.onUncaughtException(e);
      }
    } else {
      handler.onOpenFiles(event);
    }
  }

  protected Desktop() {
    // required for overlay types
  }

  public native void createShortcut(String name, String url, DesktopIcons icons) /*-{
    this.createShortcut(name, url, icons);
  }-*/;

  public native void createShortcut(String name, String url,
      DesktopIcons icons, String description) /*-{
    this.createShortcut(name, url, icons, description);
  }-*/;

  /**
   * Provides user-driven access to files on the client machine. Presents the
   * user with a file selection dialog for choosing files to make available to
   * the application.
   * <p>
   * Default options are used: all files on the local disk are selectable, and
   * the user may select multiple files
   * 
   * @param handler The callback to invoke after the user has selected files
   */
  public native void openFiles(OpenFilesHandler handler) /*-{
    this.openFiles(function(files) {
      @com.google.gwt.gears.client.desktop.Desktop::fireFilesOpened(Lcom/google/gwt/gears/client/desktop/OpenFilesHandler;[Lcom/google/gwt/gears/client/desktop/File;)(handler, files);
    });
  }-*/;

  /**
   * Provides user-driven access to files on the client machine. Presents the
   * user with a file selection dialog for choosing files to make available to
   * the application.
   * <p>
   * Default options are used: all files on the local disk are selectable, and
   * the user may select multiple files
   * 
   * @param handler The handler to receive an event after the user has selected
   *          files
   * @param singleFile If true, the user may select only a single file
   */
  public void openFiles(OpenFilesHandler handler, boolean singleFile) {
    OpenFilesOptions options = OpenFilesOptions.create();
    options.setSingleFile(singleFile);
    openFiles(handler, options);
  }

  /**
   * Provides user-driven access to files on the client machine. Presents the
   * user with a file selection dialog for choosing files to make available to
   * the application.
   * <p>
   * Default options are used: all files on the local disk are selectable, and
   * the user may select multiple files
   * 
   * @param handler The handler to receive an event after the user has selected
   *          files
   * @param singleFile If true, the user may select only a single file
   * @param filter An array of content types or file extensions to filter by
   */
  public void openFiles(OpenFilesHandler handler, boolean singleFile,
      String[] filter) {
    OpenFilesOptions options = OpenFilesOptions.create();
    options.setSingleFile(singleFile);
    options.setFilter(filter);
    openFiles(handler, options);
  }

  /**
   * Provides user-driven access to files on the client machine. Presents the
   * user with a file selection dialog for choosing files to make available to
   * the application.
   * <p>
   * Only files matching the filter are selectable. The user can turn the filter
   * off, so be aware that selected files may not match the filter.
   * 
   * @param handler The handler to receive an event after the user has selected
   *          files
   * @param options An instance of {@link OpenFilesOptions} to customize the
   *          behavior of the operation.
   */
  public native void openFiles(OpenFilesHandler handler,
      OpenFilesOptions options) /*-{
    this.openFiles(function(files) {
      @com.google.gwt.gears.client.desktop.Desktop::fireFilesOpened(Lcom/google/gwt/gears/client/desktop/OpenFilesHandler;[Lcom/google/gwt/gears/client/desktop/File;)(handler, files);
    }, options);
  }-*/;

  /**
   * Provides user-driven access to files on the client machine. Presents the
   * user with a file selection dialog for choosing files to make available to
   * the application.
   * <p>
   * Default options are used: all files on the local disk are selectable, and
   * the user may select multiple files
   * 
   * @param handler The handler to receive an event after the user has selected
   *          files
   * @param filter An array of content types or file extensions to filter by
   */
  public void openFiles(OpenFilesHandler handler, String[] filter) {
    OpenFilesOptions options = OpenFilesOptions.create();
    options.setFilter(filter);
    openFiles(handler, options);
  }
}
