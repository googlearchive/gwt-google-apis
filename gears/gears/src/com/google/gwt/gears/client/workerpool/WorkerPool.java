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
package com.google.gwt.gears.client.workerpool;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.gears.client.workerpool.WorkerPoolErrorHandler.ErrorEvent;
import com.google.gwt.gears.client.workerpool.WorkerPoolMessageHandler.MessageEvent;

/**
 * WorkerPool module allows web applications to run JavaScript code in the
 * background, without blocking the main page's script execution.
 * 
 * Currently this class can only create worker threads out of raw JavaScript
 * code. That is, user code cannot currently create worker bodies from Java
 * code.
 */
public final class WorkerPool extends JavaScriptObject {
  // Called from JSNI
  @SuppressWarnings("unused")
  private static boolean fireOnError(WorkerPoolErrorHandler handler,
      ErrorEvent event) {
    if (handler == null) {
      return false;
    }

    UncaughtExceptionHandler ueh = GWT.getUncaughtExceptionHandler();
    if (ueh != null) {
      try {
        return handler.onError(event);
      } catch (Throwable e) {
        ueh.onUncaughtException(e);
        return false;
      }
    } else {
      return handler.onError(event);
    }
  }

  // Called from JSNI
  @SuppressWarnings("unused")
  private static void fireOnMessage(WorkerPoolMessageHandler handler,
      MessageEvent event) {
    if (handler == null) {
      return;
    }

    UncaughtExceptionHandler ueh = GWT.getUncaughtExceptionHandler();
    if (ueh != null) {
      try {
        handler.onMessageReceived(event);
      } catch (Throwable e) {
        ueh.onUncaughtException(e);
      }
    } else {
      handler.onMessageReceived(event);
    }
  }

  protected WorkerPool() {
    // Required for overlay types
  }

  /**
   * A child worker must call this method if it expects to be used across
   * origins.
   */
  public native void allowCrossOrigin() /*-{
    this.allowCrossOrigin();
  }-*/;

  /**
   * Creates a worker from a string of JavaScript code.
   * 
   * Gears guarantees the code in scriptText will run once before any messages
   * are delivered. The code must set an onmessage handler during that initial
   * execution, otherwise the worker will never receive messages.
   * 
   * Two global objects are inserted into the namespace of every created worker:
   * <ul>
   * <li>google.gears.factory - Provides a Factory for the worker.</li>
   * <li>google.gears.workerPool - Gives access to the WorkerPool that created
   * the worker.</li>
   * </ul>
   * 
   * Worker IDs are guaranteed to be unique values that are never reused within
   * the same WorkerPool.
   * 
   * @param scriptText string containing the worker code
   * @return id of the created worker
   */
  public native int createWorker(String scriptText) /*-{
    return this.createWorker(scriptText);
  }-*/;

  /**
   * Creates a worker using the JavaScript code fetched from a URL
   * 
   * Note: If called from a worker, createWorkerFromUrl() always fails today,
   * due to a technical issue that will be addressed in a future release.
   * 
   * Gears guarantees the URL will be fetched and the code returned will run
   * once before any messages are delivered. The code must call
   * {@link #setMessageHandler(WorkerPoolMessageHandler)} during that initial
   * execution, otherwise the worker will never receive messages.
   * 
   * @param scriptUrl URL that contains the worker code
   * @return id of the created worker
   */
  public native int createWorkerFromUrl(String scriptUrl) /*-{
    return this.createWorkerFromUrl(scriptUrl);
  }-*/;

  /**
   * Sends message to the worker specified by destWorkerId.
   * 
   * Messages sent from worker 1 to worker 2 in a particular order will be
   * received in the same order.
   * 
   * Messages can be sent and received only between members of the same
   * WorkerPool.
   * 
   * Messages are copied between workers. Changes to a message received in one
   * worker will not be reflected in the sending worker.
   * 
   * @param message message to send to the worker.
   * @param destWorkerId id of the worker to send the message to
   */
  public native void sendMessage(boolean message, int destWorkerId) /*-{
    this.sendMessage(message, destWorkerId);
  }-*/;

  /**
   * Sends message to the worker specified by destWorkerId.
   * 
   * Messages sent from worker 1 to worker 2 in a particular order will be
   * received in the same order.
   * 
   * Messages can be sent and received only between members of the same
   * WorkerPool.
   * 
   * Messages are copied between workers. Changes to a message received in one
   * worker will not be reflected in the sending worker.
   * 
   * @param message message to send to the worker.
   * @param destWorkerId id of the worker to send the message to
   */
  public native void sendMessage(double message, int destWorkerId) /*-{
    this.sendMessage(message, destWorkerId);
  }-*/;

  /**
   * Sends message to the worker specified by destWorkerId.
   * 
   * Messages sent from worker 1 to worker 2 in a particular order will be
   * received in the same order.
   * 
   * Messages can be sent and received only between members of the same
   * WorkerPool.
   * 
   * Messages are copied between workers. Changes to a message received in one
   * worker will not be reflected in the sending worker.
   * 
   * @param messageObj message to send to the worker.
   * @param destWorkerId id of the worker to send the message to
   */
  public native void sendMessage(JavaScriptObject messageObj, int destWorkerId) /*-{
    this.sendMessage(messageObj, destWorkerId);
  }-*/;

  /**
   * Sends message to the worker specified by destWorkerId.
   * 
   * Messages sent from worker 1 to worker 2 in a particular order will be
   * received in the same order.
   * 
   * Messages can be sent and received only between members of the same
   * WorkerPool.
   * 
   * Messages are copied between workers. Changes to a message received in one
   * worker will not be reflected in the sending worker.
   * 
   * @param message message text
   * @param destWorkerId id of the worker to send the message to
   */
  public native void sendMessage(String message, int destWorkerId) /*-{
    this.sendMessage(message, destWorkerId);
  }-*/;

  /**
   * Set the {@link WorkerPoolErrorHandler}. This provides functionality in
   * workers similar to the window.onerror property. If set, it will be called
   * for any unhandled errors that occur inside a worker.
   * 
   * You can use this callback to implement "last-chance" error handling for
   * your workers. For example, you could log all unhandled errors into the
   * Database module.
   * 
   * NOTE: This callback can only be set from child workers.
   * 
   * @param handler handler to be notified of error events
   */
  public native void setErrorHandler(WorkerPoolErrorHandler handler) /*-{
    this.onerror = function(errorObject) {
      return @com.google.gwt.gears.client.workerpool.WorkerPool::fireOnError(Lcom/google/gwt/gears/client/workerpool/WorkerPoolErrorHandler;Lcom/google/gwt/gears/client/workerpool/WorkerPoolErrorHandler$ErrorEvent;)(handler, errorObject);
    };
  }-*/;

  /**
   * Set the {@link WorkerPoolMessageHandler} to call when this worker receives
   * a message.
   * 
   * @param handler handler to be notified of message events
   */
  public native void setMessageHandler(WorkerPoolMessageHandler handler) /*-{
    this.onmessage = function(messageText, senderId, messageObject) {
      @com.google.gwt.gears.client.workerpool.WorkerPool::fireOnMessage(Lcom/google/gwt/gears/client/workerpool/WorkerPoolMessageHandler;Lcom/google/gwt/gears/client/workerpool/WorkerPoolMessageHandler$MessageEvent;)(handler, messageObject);
    };
  }-*/;
}