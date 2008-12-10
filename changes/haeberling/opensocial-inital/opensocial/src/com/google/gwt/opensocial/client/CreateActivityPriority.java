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
package com.google.gwt.opensocial.client;

/**
 * The priorities a create activity request can have. See also:
 * opensocial.requestCreateActivity()
 * 
 * @see
 *   "http://code.google.com/apis/opensocial/docs/0.8/reference/#opensocial.CreateActivityPriority"
 */
public enum CreateActivityPriority {
  /**
   * If the activity is of high importance, it will be created even if this
   * requires asking the user for permission. This may cause the container to
   * open a user flow which may navigate away from your gagdet.
   */
  HIGH,
  /**
   * If the activity is of low importance, it will not be created if the user
   * has not given permission for the current app to create activities. With
   * this priority, the requestCreateActivity call will never open a user flow.
   */
  LOW;
}
