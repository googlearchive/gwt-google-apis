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
package com.google.gwt.gears.client.localserver;

/**
 * A callback class used to receive notification of {@link ResourceStore}
 * capture operations.
 * 
 * TODO(mmendez): how about just onFailure or onSuccess since we know we are in
 * the URL capture callback.
 */
public abstract class URLCaptureCallback {
  /**
   * Callback method invoked when a capture operation fails.
   * 
   * @param url the URL that was requested for capture
   * @param captureId the ID of the capture operation
   */
  public abstract void onCaptureFailure(String url, int captureId);

  /**
   * Callback method invoked when a capture operation succeeds.
   * 
   * @param url the URL that was requested for capture
   * @param captureId the ID of the capture operation
   */
  public abstract void onCaptureSuccess(String url, int captureId);
}
