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

import com.google.gwt.core.client.JavaScriptObject;

/**
 * WorkerPool module allows web applications to run JavaScript code in the
 * background, without blocking the main page's script execution.
 * 
 * Currently this class can only create worker threads out of raw JavaScript
 * code. That is, user code cannot currently create worker bodies from Java
 * code.
 */
public final class WorkerPool extends JavaScriptObject {
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
   * Two global objects are inserted into the namespace of every created worker: •
   * google.gears.factory - Provides a Factory for the worker. •
   * google.gears.workerPool - Gives access to the WorkerPool that created the
   * worker.
   * 
   * Worker IDs are guaranteed to be unique values that are never reused within
   * the same WorkerPool.
   * 
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
   * once before any messages are delivered. The code must set an onmessage
   * handler during that initial execution, otherwise the worker will never
   * receive messages.
   * 
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
   */
  public native void sendMessage(String message, int destWorkerId) /*-{
    this.sendMessage(message, destWorkerId);
  }-*/;

  /**
   * Set the {@link ErrorHandler}. This provides functionality in workers
   * similar to the window.onerror property. If set, it will be called for any
   * unhandled errors that occur inside a worker.
   * 
   * You can use this callback to implement "last-chance" error handling for
   * your workers. For example, you could log all unhandled errors into the
   * Database module.
   * 
   * NOTE: This callback can only be set from child workers.
   */
  public native void setErrorHandler(ErrorHandler handler) /*-{
    this.onerror = function(errorObject) {
      if (handler) {
        handler.@com.google.gwt.gears.client.workerpool.ErrorHandler::onError(Lcom/google/gwt/gears/client/workerpool/ErrorHandler$ErrorEvent;)(errorObject);
      }
    };
  }-*/;

  /**
   * Set the {@link MessageHandler} to call when this worker receives a message.
   */
  public native void setMessageHandler(MessageHandler handler) /*-{
    this.onmessage = function(messageText, senderId, messageObject) {
      if (handler) {
        handler.@com.google.gwt.gears.client.workerpool.MessageHandler::onMessageReceived(Lcom/google/gwt/gears/client/workerpool/MessageHandler$MessageEvent;)(messageObject);
      }
    };
  }-*/;
}