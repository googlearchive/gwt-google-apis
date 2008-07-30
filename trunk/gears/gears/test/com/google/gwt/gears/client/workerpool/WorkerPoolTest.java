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

import com.google.gwt.gears.client.Factory;
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

  /**
   * Test method for {@link WorkerPool#createWorker(String)}.
   */
  public void disabledTestCreateWorkerFromStringNull() {
    WorkerPool wp = Factory.getInstance().createWorkerPool();
    wp.setMessageHandler(new WorkerPoolMessageHandler() {
      public void onMessageReceived(MessageEvent event) {
        // purposely empty
      }
    });

    try {
      int wID = wp.createWorker(null);
      wp.sendMessage("stop", wID);
      fail("Expected a NullPointerException");
    } catch (NullPointerException e) {
      // Expected to get here
    }
  }

  /**
   * Test method for {@link WorkerPool#sendMessage(String, int)}.
   */
  public void disabledTestSendMessageNegativeWorkerID() {
    WorkerPool wp = Factory.getInstance().createWorkerPool();
    wp.createWorker(WORKER_JS_SRC);
    wp.sendMessage("", -2);
  }

  /**
   * Test method for {@link WorkerPool#sendMessage(String, int)}.
   */
  public void disabledTestSendMessageNull() {
    WorkerPool wp = Factory.getInstance().createWorkerPool();
    int workerID = wp.createWorker(WORKER_JS_SRC);

    wp.sendMessage(null, workerID);
    wp.sendMessage("", -2);
  }

  @Override
  public String getModuleName() {
    return "com.google.gwt.gears.Gears";
  }

  /**
   * Test method for {@link WorkerPool#createWorker(String)} and
   * {@link WorkerPool#sendMessage(String, int)}.
   */
  public void testCreateWorkerFromString() {
    WorkerPool wp = Factory.getInstance().createWorkerPool();
    wp.setMessageHandler(new WorkerPoolMessageHandler() {
      public void onMessageReceived(MessageEvent event) {
        finishTest();
      }
    });

    int wID = wp.createWorker(WORKER_JS_SRC);
    delayTestFinish(5000);
    wp.sendMessage("stop", wID);
  }

  /**
   * Test method for {@link WorkerPool#createWorker(String)}.
   */
  public void testCreateWorkerFromStringEmpty() {
    WorkerPool wp = Factory.getInstance().createWorkerPool();
    wp.setMessageHandler(new WorkerPoolMessageHandler() {
      public void onMessageReceived(MessageEvent event) {
        // purposely empty
      }
    });

    // You can create a worker from an empty string, you just can't sent it a
    // message.
    wp.createWorker("");
  }

  /**
   * Test method for {@link WorkerPool#sendMessage(String, int)}.
   */
  public void testSendMessage() {
    WorkerPool wp = Factory.getInstance().createWorkerPool();
    int workerID = wp.createWorker(WORKER_JS_SRC);
    delayTestFinish(5000);
    wp.setMessageHandler(new WorkerPoolMessageHandler() {
      public void onMessageReceived(MessageEvent event) {
        assertEquals("done", event.getBody());
        finishTest();
      }
    });
    wp.sendMessage("foo", workerID);
  }

  /**
   * Test method for {@link WorkerPool#sendMessage(String, int)}.
   */
  public void testSendMessageEmpty() {
    WorkerPool wp = Factory.getInstance().createWorkerPool();
    int workerID = wp.createWorker(WORKER_JS_SRC);
    wp.sendMessage("", workerID);
  }
}
