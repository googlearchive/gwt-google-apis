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
package com.google.gwt.gadgets.client.event;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.dom.client.ObjectElement;

/**
 * Utility Class for Gadget Events. In order to give type appropriate Java
 * interfaces, this class contains many aliases for callback interfaces.
 */
public abstract class Event {

  /**
   * Provide a way to specify a JavaScript function() with no argument.
   */
  private static interface Callback {
    void execute();
  }

  /**
   * Provides a way to specify a JavaScript function() with a single String
   * argument.
   */
  public interface FetchContentCallback {
    void callback(String content);
  }

  /**
   * Provides a way to specify a JavaScript function() with a single DOM Object
   * argument and handles uncaught exceptions.
   */
  public interface FetchXmlContentCallback {
    void callback(ObjectElement content);
  }

  /**
   * Wraps a FetchXmlContentCallback to handles uncaught exceptions.
   * 
   * @param content The content to pass to the callback method
   * @param callback The callback handler object
   */
  public static void callbackWrapper(final ObjectElement content,
      final FetchXmlContentCallback callback) {
    invokeAndMaybeReportUncaughtExceptions(new Callback() {
      public void execute() {
        callback.callback(content);
      }
    });
  }

  /**
   * Wraps a FetchContentCallback to handles uncaught exceptions.
   * 
   * @param content The content to pass to the callback method
   * @param callback The callback handler object
   */
  public static void callbackWrapper(final String content,
      final FetchContentCallback callback) {
    invokeAndMaybeReportUncaughtExceptions(new Callback() {
      public void execute() {
        callback.callback(content);
      }
    });
  }

  /**
   * Invoke the requested {@link Callback} and report exceptions via the
   * {@link UncaughtExceptionHandler} if one is currently set.
   */
  private static void invokeAndMaybeReportUncaughtExceptions(Callback callback) {
    assert (callback != null);

    UncaughtExceptionHandler ucHandler = GWT.getUncaughtExceptionHandler();
    if (ucHandler != null) {
      invokeAndReportUncaughtExceptions(callback, ucHandler);
    } else {
      invokeImpl(callback);
    }
  }

  /**
   * Invoke the requested {@link Callback} and report any uncaught exceptions.
   */
  private static void invokeAndReportUncaughtExceptions(Callback callback,
      UncaughtExceptionHandler ucHandler) {
    assert (callback != null);
    assert (ucHandler != null);
    try {
      callback.execute();
    } catch (Throwable e) {
      ucHandler.onUncaughtException(e);
    }
  }

  /**
   * Invoke the {@link Callback} and allow any uncaught exceptions to escape.
   */
  private static void invokeImpl(Callback callback) {
    callback.execute();
  }
}
