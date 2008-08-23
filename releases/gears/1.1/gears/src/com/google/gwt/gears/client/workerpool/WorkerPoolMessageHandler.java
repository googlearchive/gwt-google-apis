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
 * Provides an interface to implement in order to receive message events.
 */
public interface WorkerPoolMessageHandler {
  /**
   * Encapsulates the arguments of the message event.
   */
  final class MessageEvent extends JavaScriptObject {
    protected MessageEvent() {
      // Required for overlay types
    }

    /**
     * Returns the body text of the message.
     * 
     * @return the body text of the message
     */
    public native String getBody() /*-{
      return this.body;
    }-*/;

    /**
     * Returns the sender's origin, represented as a string of the form:
     * SCHEME://DOMAIN[:PORT].
     * 
     * Note that the port is omitted for standard ports (http port 80, https
     * port 443).
     * 
     * @return the sender's origin, represented as a string of the form:
     *         SCHEME://DOMAIN[:PORT]
     */
    public native String getOrigin() /*-{
      return this.origin;
    }-*/;

    /**
     * Returns the id of the worker that sent this message.
     * 
     * @return the id of the worker that sent this message
     */
    public native int getSender() /*-{
      return this.sender;
    }-*/;
  }

  /**
   * Method to be invoked when a worker receives a message event.
   * 
   * @param event contains the properties of the event
   */
  void onMessageReceived(MessageEvent event);
}