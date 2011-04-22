/*
 * Copyright 2010 Google Inc.
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
package com.google.gwt.gadgets.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.gadgets.client.PubSubFeature.PubSubCallback;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.Timer;

/**
 * Tests the {@link PubSubFeature} class.
 */
public class PubSubTest extends GWTTestCase {
  static final String TEST_CHANNEL = "testChannel";
  static final String TEST_MESSAGE = "testMessage";
  static final int ASYNC_DELAY = 10 * 1000;

  @Override
  public String getModuleName() {
    return "com.google.gwt.gadgets.GadgetsTest";
  }

  public void testSubscribe() {
    setSubscribeMock();
    assertEquals("channelName", null, getResultsChannelName());
    assertEquals("callback", null, getResultsCallback());
    PubSubFeature feature = PubSubFeature.getInstance();
    feature.subscribe(TEST_CHANNEL, new PubSubCallback() {
      public void onMessage(String channelName, String sender, String message) {
        fail("I wasn't expecting a callback.");
      }
    });
    assertEquals("channelName", TEST_CHANNEL, getResultsChannelName());
    assertNotNull("callback", getResultsCallback());
  }

  public void testUnsubscribe() {
    setSubscribeMock();
    setUnsubscribeMock();
    assertEquals("channelName", null, getResultsChannelName());
    assertEquals("callback", null, getResultsCallback());
    PubSubFeature feature = PubSubFeature.getInstance();
    feature.subscribe(TEST_CHANNEL, new PubSubCallback() {
      public void onMessage(String channelName, String sender, String message) {
        fail("I wasn't expecting a callback.");
      }
    });
    assertEquals("channelName", TEST_CHANNEL, getResultsChannelName());
    assertNotNull("callback", getResultsCallback());
    feature.unsubscribe(TEST_CHANNEL);
    assertEquals("channelName", null, getResultsChannelName());
    assertEquals("callback", null, getResultsCallback());
  }

  public void testPublish() {
    setSubscribeMock();
    setPublishMock();
    assertEquals("channelName", null, getResultsChannelName());
    assertEquals("callback", null, getResultsCallback());
    final PubSubFeature feature = PubSubFeature.getInstance();
    feature.subscribe(TEST_CHANNEL, new PubSubCallback() {
      public void onMessage(String channelName, String sender, String message) {
        assertEquals("channelName in PubSubCallback", channelName, TEST_CHANNEL);
        assertEquals("message in PubSubCallback", message, TEST_MESSAGE);
        finishTest();
      }
    });
    assertEquals("channelName", TEST_CHANNEL, getResultsChannelName());
    assertNotNull("callback", getResultsCallback());
    delayTestFinish(ASYNC_DELAY);

    new Timer() {
      @Override
      public void run() {
        feature.publish(TEST_CHANNEL, TEST_MESSAGE);
      }
    }.schedule(100);
  }

  /**
   * Runs before every test method.
   */
  @Override
  protected void gwtSetUp() throws Exception {
    super.gwtSetUp();
    setMockBase();
  }

  /**
   * Cleaning after test.
   */
  @Override
  protected void gwtTearDown() throws Exception {
    clearMock();
    super.gwtTearDown();
  }

  private native void clearMock() /*-{
    $wnd.gadgets = undefined;
  }-*/;

  private native void setMockBase() /*-{
    $wnd.gadgets = {};
    $wnd.gadgets.pubsub = {};
  }-*/;

  private native void setPublishMock() /*-{
    $wnd.gadgets.pubsub.publish = function(channelName, message) {
      if (channelName == $wnd.gadgets.pubsub.results_channel_name) {
        $wnd.gadgets.pubsub.results_callback("mock_sender", message);
      } 
    }
  }-*/;

  private native String getResultsChannelName() /*-{
    return $wnd.gadgets.pubsub.results_channel_name == null ? null : $wnd.gadgets.pubsub.results_channel_name;
  }-*/;

  private native JavaScriptObject getResultsCallback() /*-{
    return $wnd.gadgets.pubsub.results_callback == null ? null :  
         $wnd.gadgets.pubsub.results_callback;
  }-*/;

  private native void setSubscribeMock() /*-{
    $wnd.gadgets.pubsub.subscribe = function(channelName, callback) {
      $wnd.gadgets.pubsub.results_channel_name = channelName;
      $wnd.gadgets.pubsub.results_callback = callback;
    }
  }-*/;

  private native void setUnsubscribeMock() /*-{
    $wnd.gadgets.pubsub.unsubscribe = function(channelName) {
      if (channelName == $wnd.gadgets.pubsub.results_channel_name) {
        $wnd.gadgets.pubsub.results_channel_name = undefined;
        $wnd.gadgets.pubsub.results_callback = undefined;
      }
    }
  }-*/;
}
