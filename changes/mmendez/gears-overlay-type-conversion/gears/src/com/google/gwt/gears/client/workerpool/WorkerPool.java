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

import com.google.gwt.core.client.JavaScriptException;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.gears.core.client.Gears;
import com.google.gwt.gears.core.client.GearsException;
import com.google.gwt.gears.core.client.impl.GearsImpl;

/**
 * Used to create and manage JavaScript threads using the Gears WorkerPool
 * system. A WorkerPool represents a group of JavaScript threads. Each thread
 * runs in a separate JavaScript VM, and has an isolated namespace. That is,
 * threads do not have access to any other threads' variables, including their
 * parents'.
 * 
 * WorkerPools can be used to group threads for convenience.
 * 
 * Currently this class can only create worker threads out of raw JavaScript
 * code. That is, user code cannot currently create worker bodies from Java
 * code.
 */
public class WorkerPool {
  /*
   * This method is called from JSNI.
   */
  @SuppressWarnings("unused")
  private static void fireOnMessageReceived(WorkerPool pool, String msg,
      int srcWorkerId) {
    if (pool.callback != null) {
      pool.callback.onMessageReceived(msg, srcWorkerId);
    }
  }

  /**
   * Native method that calls <code>createWorker</code> on the underlying
   * <code>pool</code> object. Note that this method can throw an unchecked
   * JavaScriptException.
   * 
   * @param javaScript the JavaScript code to pass into the worker thread
   * @return the ID of the newly-created thread
   */
  private static native int nativeCreateWorkerByString(JavaScriptObject pool,
      String javaScript) /*-{
    return pool.createWorker(javaScript);
  }-*/;

  private static native void nativeSendMessage(JavaScriptObject pool,
      String message, int destWorker) /*-{
    pool.sendMessage(message, destWorker);
  }-*/;

  /**
   * Sets the raw JavaScript onmessage handler to a function which dynamically
   * dispatches to the current callback object, if one is registered.
   * 
   */
  private static native void setOnMessage(WorkerPool workerPool,
      JavaScriptObject jsPool) /*-{
    var jPool = workerPool;
    jsPool.onmessage = function(msg, srcId) {
      @com.google.gwt.gears.client.workerpool.WorkerPool::fireOnMessageReceived(Lcom/google/gwt/gears/client/workerpool/WorkerPool;Ljava/lang/String;I)(jPool, msg, srcId);
    }
  }-*/;

  /**
   * The callback function registered to this worker thread.
   */
  private final MessageHandler callback;

  /**
   * Handle to the native WorkerPool object wrapped by this class.
   */
  private final JavaScriptObject pool;

  /**
   * Constructs a WorkerPool object.
   * 
   * @param callback if non-<code>null</code>, the instance which will
   *          receive messages from threads
   * 
   * @throws GearsException if the WorkerPool could not be constructed
   */
  public WorkerPool(MessageHandler callback) throws GearsException {
    this(callback, Gears.WORKERPOOL, Gears.GEARS_VERSION);
  }

  /**
   * Constructs a WorkerPool object backed by the provided Gears pool object.
   * 
   * @throws GearsException if the WorkerPool could not be constructed
   */
  protected WorkerPool(MessageHandler callback, String className,
      String classVersion) throws GearsException {
    this.callback = callback;
    pool = GearsImpl.create(className, classVersion);
    setOnMessage(this, pool);
  }

  /**
   * Creates a JavaScript thread using the JavaScript code stored in
   * <code>javaScript</code>. The code in <code>javaScript</code> is
   * guaranteed to complete before this method returns. The code must set an
   * <code>onmessage</code> handler (though it may be a no-op handler.)
   * 
   * @param javaScript the JavaScript code to run in the thread
   * @return the ID of the newly-created thread
   * @throws GearsException if the worker could not be created
   * @throws NullPointerException if the java script string is <code>null</code>
   */
  public int createWorkerFromString(String javaScript) throws GearsException {
    if (javaScript == null) {
      throw new NullPointerException();
    }

    try {
      return nativeCreateWorkerByString(pool, javaScript);
    } catch (JavaScriptException ex) {
      throw new GearsException(ex.getMessage(), ex);
    }
  }

  /**
   * Sends a message with the indicated data to the indicated thread. Messages
   * can only be sent between members of the same <code>WorkerPool</code>.
   * 
   * @param message the data to send
   * @param destWorker the thread to send the data to
   * @throws NullPointerException of <code>message</code> is <code>null</code>
   * @throws GearsException if the <code>message</code> cannot be sent to the
   *           specified <code>destWorker</code>
   */
  public void sendMessage(String message, int destWorker) throws GearsException {
    if (message == null) {
      throw new NullPointerException();
    }

    try {
      nativeSendMessage(pool, message, destWorker);
    } catch (JavaScriptException ex) {
      throw new GearsException(ex.getMessage(), ex);
    }
  }

  /**
   * Returns the JavaScript <code>WorkerPool</code> object wrapped by this
   * instance.
   * 
   * @return the JavaScript <code>WorkerPool</code> object wrapped by this
   *         instance
   */
  protected final JavaScriptObject getJavaScriptObject() {
    return pool;
  }
}
