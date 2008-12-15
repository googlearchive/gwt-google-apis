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

package com.google.gwt.opensocial.client.util;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * This is a marker class to represent the objects returned by
 * <ul>
 *
 * <li>{@link com.google.gwt.opensocial.client.DataRequest#newFetchPeopleRequest(Array)}</li>
 * <li>{@link com.google.gwt.opensocial.client.DataRequest#newFetchPersonRequest(com.google.gwt.opensocial.client.DataRequest.Id)}</li>
 * <li>{@link com.google.gwt.opensocial.client.DataRequest#newFetchPersonAppDataRequest(com.google.gwt.opensocial.client.DataRequest.Id, String)}</li>
 * <li>{@link com.google.gwt.opensocial.client.DataRequest#newUpdatePersonAppDataRequest(com.google.gwt.opensocial.client.DataRequest.Id, String, String)}</li>
 * <li>{@link com.google.gwt.opensocial.client.DataRequest#newFetchActivitiesRequest(com.google.gwt.opensocial.client.DataRequest.Id)}</li>
 * </ul>
 * and object accepted by
 * <ul>
 * <li>{@link com.google.gwt.opensocial.client.DataRequest#add(DataRequestItem)}</li>
 * </ul>
 */
public class DataRequestItem extends JavaScriptObject {
  protected DataRequestItem() {
  }
}
