/*
 * Copyright 2011 Google Inc.
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

package com.google.api.gwt.samples.buzz.shared;

import com.google.api.gwt.shared.AuthScope;

/**
 * Defines the available auth scopes for the Buzz service.
 *
 * @author jasonhall@google.com (Jason Hall)
 */
// TODO(jasonhall): This is a placeholder until it can be generated per service.
public enum BuzzAuthScope implements AuthScope {
  BUZZ("https://www.googleapis.com/auth/buzz"),
  BUZZ_READONLY("https://www.googleapis.com/auth/buzz.readonly"),
  PHOTOS("https://www.googleapis.com/auth/photos"),
  ;
  private final String url;

  private BuzzAuthScope(String url) {
    this.url = url;
  }

  @Override
  public String getScope() {
    return url;
  }
}
