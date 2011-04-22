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

/**
 * Provides access to the Ads APIs provided by the container.
 */
public class PubSubFeature implements GadgetFeature {

  public interface PubSubCallback {
    void onMessage(String channelName, String sender, String message);
  }

  private PubSubFeature() {
  }

  /**
   * Publishes a string-type message to a channel.
   * 
   * @param channelName the name of the channel
   * @param message the message to publish
   */
  public native void publish(String channelName, String message) /*-{
    $wnd.gadgets.pubsub.publish(channelName, message);
  }-*/;

  /**
   * Subscribes a gadget to a message channel.
   * 
   * @param channelName the name of the channel
   * @param callback A function that will be called with the channel messages
   */
  public native void subscribe(String channelName, PubSubCallback callback) /*-{
    $wnd.gadgets.pubsub.subscribe(channelName, function(sender, message) {
      callback.@com.google.gwt.gadgets.client.PubSubFeature$PubSubCallback::onMessage(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)(channelName, sender, message);
    });
  }-*/;

  /**
   * Unsubscribes the gadget from a message channel.
   * 
   * @param channelName the name of the channel
   */
  public native void unsubscribe(String channelName) /*-{
    $wnd.gadgets.pubsub.unsubscribe(channelName);
  }-*/;
  
  /**
   * Used just for testing a mock version of the JS API.
   */
  static PubSubFeature getInstance() {
    return new PubSubFeature();
  }
   
}
