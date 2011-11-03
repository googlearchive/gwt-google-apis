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
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayBoolean;
import com.google.gwt.core.client.JsArrayInteger;
import com.google.gwt.core.client.JsArrayNumber;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.gears.client.Factory;
import com.google.gwt.junit.client.GWTTestCase;

/**
 * Test the Gears WorkerPool API.
 */
public class WorkerPoolTest extends GWTTestCase {
  /**
   * Used internally to swap messages between worker and main thread.
   */
  private static class FooMessage extends JavaScriptObject {
    protected FooMessage() {
    }

    public final native int getFoo() /*-{
      return this.foo;
    }-*/;

    public final native void setFoo(int value) /*-{
      this.foo = value;
    }-*/;
  }

  private static final String WORKER_JS_SRC = "" + "function workerInit() {"
      + "  google.gears.workerPool.onmessage = function(a, b, message){"
      + "     google.gears.workerPool.sendMessage('done', message.sender);"
      + "  };" + "}" + "workerInit();";

  private static final String WORKER_JS_SRC_BOOL = ""
      + "function workerInit() {"
      + "  google.gears.workerPool.onmessage = function(a, b, message){"
      + "     var returnMessage = 'error';"
      + "     if (message.body === true) { returnMessage = 'done'; }"
      + "     google.gears.workerPool.sendMessage(returnMessage, message.sender);"
      + "  };" + "}" + "workerInit();";

  private static final String WORKER_JS_SRC_DOUBLE = ""
      + "function workerInit() {"
      + "  google.gears.workerPool.onmessage = function(a, b, message){"
      + "     var returnMessage = 'error';"
      + "     if (message.body == 1.0) { returnMessage = 'done'; }"
      + "     google.gears.workerPool.sendMessage(returnMessage, message.sender);"
      + "  };" + "}" + "workerInit();";

  private static final String WORKER_JS_SRC_MAKE_TYPE = ""
      + "function workerInit() {"
      + "  google.gears.workerPool.onmessage = function(a, b, message) {"
      + "     var returnMessage = 'error';"
      + "     if (message.body == 'boolean') { "
      + "       returnMessage = true; "
      + "     } else if (message.body == 'Boolean') { "
      + "       returnMessage = new Boolean(true); "
      + "     } else if (message.body == 'string') { "
      + "       returnMessage = 'stringValue'; "
      + "     } else if (message.body == 'String') { "
      + "       returnMessage = new String('stringValue'); "
      + "     } else if (message.body == 'number') { "
      + "       returnMessage = 1.0; "
      + "     } else if (message.body == 'Number') { "
      + "       returnMessage = new Number(1.0); "
      + "     } else if (message.body == 'object') { "
      + "       returnMessage = new Object(); returnMessage.foo = 1; "
      + "     } else if (message.body == 'arrayBoolean') { "
      + "       returnMessage = [ true, false, true, false, true ];"
      + "     } else if (message.body == 'arrayInteger') { "
      + "       returnMessage = [ 1, 2, 3 ];"
      + "     } else if (message.body == 'arrayNumber') { "
      + "       returnMessage = [ 1.1, 2.2, 3.3, 4.4 ];"
      + "     } else if (message.body == 'arrayObject') { "
      + "       returnMessage = [ {foo:1}, {foo:2} ];"
      + "     } else if (message.body == 'arrayString') { "
      + "       returnMessage = [ 'foo', 'bar' ];"
      + "     }"
      + "     google.gears.workerPool.sendMessage(returnMessage, message.sender);"
      + "  };" + "}" + "workerInit();";

  private static final String WORKER_JS_SRC_OBJ = ""
      + "function workerInit() {"
      + "  google.gears.workerPool.onmessage = function(a, b, message){"
      + "     var returnMessage = 'error';"
      + "     if (message.body.foo == 1) { returnMessage = 'done'; }"
      + "     google.gears.workerPool.sendMessage(returnMessage, message.sender);"
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

  public void disabledTestReceiveMessageNumberBoxed() {
    WorkerPool wp = Factory.getInstance().createWorkerPool();
    int workerID = wp.createWorker(WORKER_JS_SRC_MAKE_TYPE);
    delayTestFinish(5000);
    wp.setMessageHandler(new WorkerPoolMessageHandler() {
      public void onMessageReceived(MessageEvent event) {
        // Window.alert("Got message as string: " + event.getBody());
        assertFalse("!isMessageString()", event.isMessageString());
        assertTrue("isMessageObject()", event.isMessageObject());
        assertFalse("!isMessageBoolean()", event.isMessageBoolean());
        assertFalse("isMessageNumber()", event.isMessageNumber());
        assertEquals(1.0, event.getBodyNumber());
        finishTest();
      }
    });
    wp.sendMessage("Number", workerID);
  }

  public void disabledTestReceiveMessageStringBoxed() {
    WorkerPool wp = Factory.getInstance().createWorkerPool();
    int workerID = wp.createWorker(WORKER_JS_SRC_MAKE_TYPE);
    delayTestFinish(5000);
    wp.setMessageHandler(new WorkerPoolMessageHandler() {
      public void onMessageReceived(MessageEvent event) {
        // Window.alert("Got message as string: " + event.getBody());
        assertFalse("!isMessageNumber()", event.isMessageNumber());
        assertTrue("isMessageObject()", event.isMessageObject());
        assertFalse("!isMessageBoolean", event.isMessageBoolean());
        assertFalse("isMessageString()", event.isMessageString());
        assertEquals("stringValue", event.getBody());
        finishTest();
      }
    });
    wp.sendMessage("String", workerID);
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

    wp.sendMessage((String) null, workerID);
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
        // intentionally empty
      }
    });

    // You can create a worker from an empty string, you just can't sent it a
    // message.
    wp.createWorker("");
  }

  public void testReceiveMessageArrayBoolean() {
    WorkerPool wp = Factory.getInstance().createWorkerPool();
    int workerID = wp.createWorker(WORKER_JS_SRC_MAKE_TYPE);
    delayTestFinish(5000);
    wp.setMessageHandler(new WorkerPoolMessageHandler() {
      public void onMessageReceived(MessageEvent event) {
        // Window.alert("Got message as string: " + event.getBody());
        assertFalse("!isMessageNumber()", event.isMessageNumber());
        assertFalse("!isMessageString()", event.isMessageString());
        assertTrue("isMessageObject()", event.isMessageObject());
        assertFalse("!isMessageBoolean()", event.isMessageBoolean());
        JsArrayBoolean result = event.getBodyArrayBoolean();
        assertEquals("length of array", 5, result.length());
        assertEquals("array[0]", true, result.get(0));
        assertEquals("array[1]", false, result.get(1));
        assertEquals("array[2]", true, result.get(2));
        assertEquals("array[3]", false, result.get(3));
        assertEquals("array[4]", true, result.get(4));
        finishTest();
      }
    });
    wp.sendMessage("arrayBoolean", workerID);
  }

  public void testReceiveMessageArrayInteger() {
    WorkerPool wp = Factory.getInstance().createWorkerPool();
    int workerID = wp.createWorker(WORKER_JS_SRC_MAKE_TYPE);
    delayTestFinish(5000);
    wp.setMessageHandler(new WorkerPoolMessageHandler() {
      public void onMessageReceived(MessageEvent event) {
        // Window.alert("Got message as string: " + event.getBody());
        assertFalse("!isMessageNumber()", event.isMessageNumber());
        assertFalse("!isMessageString()", event.isMessageString());
        assertTrue("isMessageObject()", event.isMessageObject());
        assertFalse("!isMessageBoolean()", event.isMessageBoolean());
        JsArrayInteger result = event.getBodyArrayInteger();
        assertEquals("length of array", 3, result.length());
        assertEquals("array[0]", 1, result.get(0));
        assertEquals("array[1]", 2, result.get(1));
        assertEquals("array[2]", 3, result.get(2));
        finishTest();
      }
    });
    wp.sendMessage("arrayInteger", workerID);
  }

  public void testReceiveMessageArrayNumber() {
    WorkerPool wp = Factory.getInstance().createWorkerPool();
    int workerID = wp.createWorker(WORKER_JS_SRC_MAKE_TYPE);
    delayTestFinish(5000);
    wp.setMessageHandler(new WorkerPoolMessageHandler() {
      public void onMessageReceived(MessageEvent event) {
        // Window.alert("Got message as string: " + event.getBody());
        assertFalse("!isMessageNumber()", event.isMessageNumber());
        assertFalse("!isMessageString()", event.isMessageString());
        assertTrue("isMessageObject()", event.isMessageObject());
        assertFalse("!isMessageBoolean()", event.isMessageBoolean());
        JsArrayNumber result = event.getBodyArrayNumber();
        assertEquals("length of array", 4, result.length());
        assertEquals("array[0]", 1.1, result.get(0));
        assertEquals("array[1]", 2.2, result.get(1));
        assertEquals("array[2]", 3.3, result.get(2));
        assertEquals("array[3]", 4.4, result.get(3));
        finishTest();
      }
    });
    wp.sendMessage("arrayNumber", workerID);
  }

  public void testReceiveMessageArrayObject() {
    WorkerPool wp = Factory.getInstance().createWorkerPool();
    int workerID = wp.createWorker(WORKER_JS_SRC_MAKE_TYPE);
    delayTestFinish(5000);
    wp.setMessageHandler(new WorkerPoolMessageHandler() {
      @SuppressWarnings("unchecked")
      public void onMessageReceived(MessageEvent event) {
        // Window.alert("Got message as string: " + event.getBody());
        assertFalse("!isMessageNumber()", event.isMessageNumber());
        assertFalse("!isMessageString()", event.isMessageString());
        assertTrue("isMessageObject()", event.isMessageObject());
        assertFalse("!isMessageBoolean()", event.isMessageBoolean());
        JsArray<FooMessage> result = (JsArray<FooMessage>) event.getBodyArray();
        assertEquals("length of array", 2, result.length());
        FooMessage m = result.get(0);
        assertEquals("array[0]", 1, m.getFoo());
        m = result.get(1);
        assertEquals("array[1]", 2, m.getFoo());
        finishTest();
      }
    });
    wp.sendMessage("arrayObject", workerID);
  }

  public void testReceiveMessageArrayString() {
    WorkerPool wp = Factory.getInstance().createWorkerPool();
    int workerID = wp.createWorker(WORKER_JS_SRC_MAKE_TYPE);
    delayTestFinish(5000);
    wp.setMessageHandler(new WorkerPoolMessageHandler() {
      public void onMessageReceived(MessageEvent event) {
        // Window.alert("Got message as string: " + event.getBody());
        assertFalse("!isMessageNumber()", event.isMessageNumber());
        assertFalse("!isMessageString()", event.isMessageString());
        assertTrue("isMessageObject()", event.isMessageObject());
        assertFalse("!isMessageBoolean()", event.isMessageBoolean());
        JsArrayString result = event.getBodyArrayString();
        assertEquals("length of array", 2, result.length());
        assertEquals("array[0]", "foo", result.get(0));
        assertEquals("array[1]", "bar", result.get(1));
        finishTest();
      }
    });
    wp.sendMessage("arrayString", workerID);
  }

  public void testReceiveMessageBoolean() {
    WorkerPool wp = Factory.getInstance().createWorkerPool();
    int workerID = wp.createWorker(WORKER_JS_SRC_MAKE_TYPE);
    delayTestFinish(5000);
    wp.setMessageHandler(new WorkerPoolMessageHandler() {
      public void onMessageReceived(MessageEvent event) {
        // Window.alert("Got message as string: " + event.getBody());
        assertFalse("!isMessageNumber()", event.isMessageNumber());
        assertFalse("!isMessageString()", event.isMessageString());
        assertFalse("!isMessageObject()", event.isMessageObject());
        assertTrue("isMessageBoolean()", event.isMessageBoolean());
        assertEquals(true, event.getBodyBoolean());
        finishTest();
      }
    });
    wp.sendMessage("boolean", workerID);
  }

  public void testReceiveMessageBooleanBoxed() {
    WorkerPool wp = Factory.getInstance().createWorkerPool();
    int workerID = wp.createWorker(WORKER_JS_SRC_MAKE_TYPE);
    delayTestFinish(5000);
    wp.setMessageHandler(new WorkerPoolMessageHandler() {
      public void onMessageReceived(MessageEvent event) {
        // Window.alert("Got message as string: " + event.getBody());
        assertFalse("!isMessageNumber()", event.isMessageNumber());
        assertFalse("!isMessageString()", event.isMessageString());
        assertTrue("isMessageObject()", event.isMessageObject());
        assertFalse("isMessageBoolean()", event.isMessageBoolean());
        assertEquals(true, event.getBodyBoolean());
        finishTest();
      }
    });
    wp.sendMessage("Boolean", workerID);
  }

  public void testReceiveMessageNumber() {
    WorkerPool wp = Factory.getInstance().createWorkerPool();
    int workerID = wp.createWorker(WORKER_JS_SRC_MAKE_TYPE);
    delayTestFinish(5000);
    wp.setMessageHandler(new WorkerPoolMessageHandler() {
      public void onMessageReceived(MessageEvent event) {
        // Window.alert("Got message as string: " + event.getBody());
        assertFalse("!isMessageString()", event.isMessageString());
        assertFalse("!isMessageObject()", event.isMessageObject());
        assertFalse("!isMessageBoolean()", event.isMessageBoolean());
        assertTrue("isMessageNumber()", event.isMessageNumber());
        assertEquals(1.0, event.getBodyNumber());
        finishTest();
      }
    });
    wp.sendMessage("number", workerID);
  }

  public void testReceiveMessageObject() {
    WorkerPool wp = Factory.getInstance().createWorkerPool();
    int workerID = wp.createWorker(WORKER_JS_SRC_MAKE_TYPE);
    delayTestFinish(5000);
    wp.setMessageHandler(new WorkerPoolMessageHandler() {
      public void onMessageReceived(MessageEvent event) {
        // Window.alert("Got message as string: " + event.getBody());
        assertFalse("!isMessageNumber()", event.isMessageNumber());
        assertFalse("!isMessageString()", event.isMessageString());
        assertFalse("!isMessageBoolean()", event.isMessageBoolean());
        assertTrue("isMessageObject()", event.isMessageObject());
        FooMessage fooObj = event.getBodyObject().cast();
        assertEquals(1, fooObj.getFoo());
        finishTest();
      }
    });
    wp.sendMessage("object", workerID);
  }

  public void testReceiveMessageString() {
    WorkerPool wp = Factory.getInstance().createWorkerPool();
    int workerID = wp.createWorker(WORKER_JS_SRC_MAKE_TYPE);
    delayTestFinish(5000);
    wp.setMessageHandler(new WorkerPoolMessageHandler() {
      public void onMessageReceived(MessageEvent event) {
        // Window.alert("Got message as string: " + event.getBody());
        assertFalse("!isMessageNumber()", event.isMessageNumber());
        assertFalse("!isMessageObject()", event.isMessageObject());
        assertFalse("!isMessageBoolean", event.isMessageBoolean());
        assertTrue("isMessageString()", event.isMessageString());
        assertEquals("stringValue", event.getBody());
        finishTest();
      }
    });
    wp.sendMessage("string", workerID);
  }

  /**
   * Test method for {@link WorkerPool#sendMessage(boolean, int)}.
   */
  public void testSendMessageBoolean() {

    WorkerPool wp = Factory.getInstance().createWorkerPool();
    int workerID = wp.createWorker(WORKER_JS_SRC_BOOL);
    delayTestFinish(5000);
    wp.setMessageHandler(new WorkerPoolMessageHandler() {
      public void onMessageReceived(MessageEvent event) {
        assertEquals("done", event.getBody());
        finishTest();
      }
    });
    wp.sendMessage(true, workerID);
  }

  /**
   * Test method for {@link WorkerPool#sendMessage(String, int)}.
   */
  public void testSendMessageEmpty() {
    WorkerPool wp = Factory.getInstance().createWorkerPool();
    int workerID = wp.createWorker(WORKER_JS_SRC);
    wp.sendMessage("", workerID);
  }

  /**
   * Test method for {@link WorkerPool#sendMessage(double, int)}.
   */
  public void testSendMessageNumber() {
    WorkerPool wp = Factory.getInstance().createWorkerPool();
    int workerID = wp.createWorker(WORKER_JS_SRC_DOUBLE);
    delayTestFinish(5000);
    wp.setMessageHandler(new WorkerPoolMessageHandler() {
      public void onMessageReceived(MessageEvent event) {
        assertEquals("done", event.getBody());
        finishTest();
      }
    });
    wp.sendMessage(1.0, workerID);
  }

  /**
   * Test method for {@link WorkerPool#sendMessage(JavaScriptObject, int)}.
   */
  public void testSendMessageObject() {
    WorkerPool wp = Factory.getInstance().createWorkerPool();
    int workerID = wp.createWorker(WORKER_JS_SRC_OBJ);
    delayTestFinish(5000);
    wp.setMessageHandler(new WorkerPoolMessageHandler() {
      public void onMessageReceived(MessageEvent event) {
        assertEquals("done", event.getBody());
        finishTest();
      }
    });
    FooMessage foo = FooMessage.createObject().cast();
    foo.setFoo(1);
    wp.sendMessage(foo, workerID);
  }

  /**
   * Test method for {@link WorkerPool#sendMessage(String, int)}.
   */
  public void testSendMessageString() {
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
}
