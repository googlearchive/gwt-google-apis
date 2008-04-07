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

/**
 * Handler interface for messages sent between JavaScript threads created using
 * the Gears WorkerPool system.
 */
public interface MessageHandler {
  /**
   * Called when a message has been sent to this thread by another thread.
   * 
   * @param message the contents of the message sent by the sender
   * @param srcWorker the WorkerPool thread ID of the sender
   */
  void onMessageReceived(String message, int srcWorker);
}
