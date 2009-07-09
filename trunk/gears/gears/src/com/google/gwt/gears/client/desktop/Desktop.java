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
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.gears.client.blob.Blob;
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
  private static void fireFilesOpened(OpenFilesHandler handler,
      JsArray<File> fileArray) {
    if (handler == null) {
      return;
    }
    File[] files = new File[fileArray.length()];
    for (int i = 0; i < files.length; i++) {
      files[i] = fileArray.get(i);
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

  /**
   * Creates a desktop shortcut for launching a web application.
   * <p>
   * The icon sizes were chosen to meet the needs of a variety of platforms. If
   * an application wants its icons to look as good as possible on all
   * platforms, it should provide all valid sizes.
   * <p>
   * The shortcut will launch the same web browser that created the shortcut.
   * <p>
   * The <code>url</code> and <code>icons</code> values can be relative or
   * absolute URLs. Relative URLs are resolved using the caller's location.
   * <p>
   * Calling this API will trigger a confirmation dialog. The dialog will use
   * the 32x32 icon if it exists, or else the closest size available.
   * <p>
   * Users can choose to never allow a particular named shortcut for a given
   * origin, in which case the dialog will not be displayed.
   * <p>
   * Shortcuts created through this API cannot overwrite an existing shortcut
   * with the same name, and such attempts fail silently. This restriction may
   * be relaxed in a future version of Gears.
   * <p>
   * Any query in the shortcut URL must not contain literal percent symbols,
   * encoded (%25) or otherwise. Any such characters will be stripped from the
   * URL.
   * 
   * @param name The user-visible name of the shortcut. It cannot contain any of
   *          these characters: <code>"\/:*?&lt;&gt;|</code>
   * @param url The address to launch when the user opens the shortcut. The URL
   *          must be in the same origin as the calling page. Note that for
   *          technical reasons, on Windows Mobile, any query parameters are
   *          stripped from the URL.
   * @param icons An object containing one or more of these named properties:
   *          128x128, 48x48, 32x32, 16x16. The value of each property must be
   *          the URL of a PNG-formatted image with dimensions matching the
   *          property name. A <a
   *          href="http://en.wikipedia.org/wiki/Data:_URI_scheme">data URL</a>
   *          containing base64-encoded PNG data can also be used.
   */
  public native void createShortcut(String name, String url, DesktopIcons icons) /*-{
    this.createShortcut(name, url, icons);
  }-*/;

  /**
   * Creates a desktop shortcut for launching a web application.
   * <p>
   * The icon sizes were chosen to meet the needs of a variety of platforms. If
   * an application wants its icons to look as good as possible on all
   * platforms, it should provide all valid sizes.
   * <p>
   * The shortcut will launch the same web browser that created the shortcut.
   * <p>
   * The <code>url</code> and <code>icons</code> values can be relative or
   * absolute URLs. Relative URLs are resolved using the caller's location.
   * <p>
   * Calling this API will trigger a confirmation dialog. The dialog will use
   * the 32x32 icon if it exists, or else the closest size available.
   * <p>
   * Users can choose to never allow a particular named shortcut for a given
   * origin, in which case the dialog will not be displayed.
   * <p>
   * Shortcuts created through this API cannot overwrite an existing shortcut
   * with the same name, and such attempts fail silently. This restriction may
   * be relaxed in a future version of Gears.
   * <p>
   * Any query in the shortcut URL must not contain literal percent symbols,
   * encoded (%25) or otherwise. Any such characters will be stripped from the
   * URL.
   * 
   * @param name The user-visible name of the shortcut. It cannot contain any of
   *          these characters: <code>"\/:*?&lt;&gt;|</code>
   * @param url The address to launch when the user opens the shortcut. The URL
   *          must be in the same origin as the calling page. Note that for
   *          technical reasons, on Windows Mobile, any query parameters are
   *          stripped from the URL.
   * @param icons An object containing one or more of these named properties:
   *          128x128, 48x48, 32x32, 16x16. The value of each property must be
   *          the URL of a PNG-formatted image with dimensions matching the
   *          property name. A <a
   *          href="http://en.wikipedia.org/wiki/Data:_URI_scheme">data URL</a>
   *          containing base64-encoded PNG data can also be used.
   * @param description Additional text to display in the confirmation dialog.
   */
  public native void createShortcut(String name, String url,
      DesktopIcons icons, String description) /*-{
    this.createShortcut(name, url, icons, description);
  }-*/;

  /**
   * Calculates metadata (such as MIME type) from a Blob's contents. For
   * example, if the Blob contains image data in a well-known encoding (such as
   * PNG or JPEG), this will return the image's width and height.
   * 
   * @param blob The Blob to examine.
   * @return A {@link BlobMetaData} instance.
   */
  public native BlobMetaData extractMetaData(Blob blob) /*-{
    return this.extractMetaData(blob);
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
      @com.google.gwt.gears.client.desktop.Desktop::fireFilesOpened(Lcom/google/gwt/gears/client/desktop/OpenFilesHandler;Lcom/google/gwt/core/client/JsArray;)(handler, files);
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
      @com.google.gwt.gears.client.desktop.Desktop::fireFilesOpened(Lcom/google/gwt/gears/client/desktop/OpenFilesHandler;Lcom/google/gwt/core/client/JsArray;)(handler, files);
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
