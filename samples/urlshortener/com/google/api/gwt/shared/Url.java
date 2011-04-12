/*
 * Copyright (c) 2011 Google Inc.
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

package com.google.api.gwt.samples.urlshortener.shared;

import com.google.web.bindery.requestfactory.shared.JsonRpcProxy;
import com.google.web.bindery.requestfactory.shared.ValueProxy;

/**
 * Definition of a "URL" object.
 *
 * @author jasonhall@google.com (Jason Hall)
 */
@JsonRpcProxy
public interface Url extends ValueProxy {

  // Since the method names match JSON field names with get/set prepended (e.g.,
  // "status" and "getStatus"), these will easily map to the fields in the JSON
  // payload.
  String getStatus();

  String getCreated();

  String getLongUrl();

  // Setters can return void, or the Url to support method chaining (e.g.,
  // url.setLongUrl().setFoo()). Note that this will return the same instance of
  // the Url, and not a mutated copy.
  Url setLongUrl(String longUrl);

  String getId();
}
