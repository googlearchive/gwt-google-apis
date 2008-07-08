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
package com.google.gwt.gears.workerpool.client;

import com.google.gwt.gears.core.client.GearsException;
import com.google.gwt.junit.client.GWTTestCase;

/**
 * 
 */
public class WorkerPoolTest extends GWTTestCase {
  private static final String WORKER_JS_SRC = ""
      + "function workerInit() {"
      + "  google.gears.workerPool.onmessage = function(messageString, srcWorkerId){"
      + "     google.gears.workerPool.sendMessage('done', srcWorkerId);"
      + "  };" + "}" + "workerInit();";

  public String getModuleName() {
    return "com.google.gwt.gears.Gears";
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.workerpool.client.WorkerPool#createWorkerFromString(java.lang.String)}
   * and
   * {@link com.google.gwt.gears.workerpool.client.WorkerPool#sendMessage(java.lang.String, int)}.
   * 
   * @throws GearsException
   */
  public void testCreateWorkerFromString() throws GearsException {
    final WorkerPool wp = new WorkerPool(new MessageHandler() {
      public void onMessageReceived(String message, int srcWorker) {
        finishTest();
      }
    });

    int wID = wp.createWorkerFromString(WORKER_JS_SRC);
    delayTestFinish(5000);
    wp.sendMessage("stop", wID);
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.workerpool.client.WorkerPool#createWorkerFromString(java.lang.String)}.
   * 
   * @throws GearsException
   */
  public void testCreateWorkerFromStringEmpty() throws GearsException {
    WorkerPool wp = new WorkerPool(new MessageHandler() {
      public void onMessageReceived(String message, int srcWorker) {
      }
    });

    // You can create a worker from an empty string, you just can't sent it a message.
    wp.createWorkerFromString("");
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.workerpool.client.WorkerPool#createWorkerFromString(java.lang.String)}.
   * 
   * @throws GearsException
   */
  public void testCreateWorkerFromStringNull() throws GearsException {
    WorkerPool wp = new WorkerPool(new MessageHandler() {
      public void onMessageReceived(String message, int srcWorker) {
      }
    });
    try {
      int wID = wp.createWorkerFromString(null);
      wp.sendMessage("stop", wID);
      fail("Expected a NullPointerException");
    } catch (NullPointerException e) {
      // Expected to get here
    }
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.workerpool.client.WorkerPool#sendMessage(java.lang.String, int)}.
   */
  public void testSendMessage() {
    try {
      WorkerPool wp = new WorkerPool(null);
      int workerID = wp.createWorkerFromString(WORKER_JS_SRC);
      wp.sendMessage("foo", workerID);
    } catch (GearsException e) {
      fail(e.getMessage());
    }
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.workerpool.client.WorkerPool#sendMessage(java.lang.String, int)}.
   */
  public void testSendMessageEmpty() {
    try {
      WorkerPool wp = new WorkerPool(null);
      int workerID = wp.createWorkerFromString(WORKER_JS_SRC);
      wp.sendMessage("", workerID);
    } catch (GearsException e) {
      fail(e.getMessage());
    }
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.workerpool.client.WorkerPool#sendMessage(java.lang.String, int)}.
   * 
   * @throws GearsException
   */
  public void testSendMessageNegativeWorkerID() throws GearsException {

    try {
      WorkerPool wp = new WorkerPool(null);
      int workerID = wp.createWorkerFromString(WORKER_JS_SRC);
      wp.sendMessage("", -2);
      fail("Expected a GearsException");
    } catch (GearsException e) {
      // Expected to get here
    }
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.workerpool.client.WorkerPool#sendMessage(java.lang.String, int)}.
   * 
   * @throws GearsException
   */
  public void testSendMessageNull() throws GearsException {
    WorkerPool wp = new WorkerPool(null);
    int workerID = wp.createWorkerFromString(WORKER_JS_SRC);

    try {
      wp.sendMessage(null, workerID);
    } catch (NullPointerException e) {
      // Expected to get here
    } catch (GearsException e) {
      fail(e.getMessage());
    }

    try {
      wp.sendMessage("", -2);
    } catch (GearsException e) {
      // Expected to get here
    }
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.workerpool.client.WorkerPool#WorkerPool(MessageHandler))}.
   */
  public void testWorkerPoolMessageHandler() {
    WorkerPool wp;
    try {
      wp = new WorkerPool(new MessageHandler() {
        public void onMessageReceived(String message, int srcWorker) {
          // purposely empty
        }
      });
    } catch (GearsException e) {
      fail(e.getMessage());
    }

    try {
      wp = new WorkerPool(null);
    } catch (GearsException e) {
      fail(e.getMessage());
    }
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.workerpool.client.WorkerPool#WorkerPool(MessageHandler, String))}.
   */
  public void testWorkerPoolMessageHandlerString() {
    // TODO fill me in
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.workerpool.client.WorkerPool#WorkerPool(MessageHandler,java.lang.String, java.lang.String)}.
   */
  public void testWorkerPoolMessageHandlerStringString() {
    // TODO fill me in
  }
}
