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
package com.google.api.gwt.shared;

import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.RequestTransport;

/**
 * Provides access to Google API endpoints for RequestFactory.
 *
 * @deprecated Use {@link com.google.api.gwt.client.GoogleApiRequestTransport}.
 *
 * @author jasonhall@google.com (Jason Hall)
 */
@Deprecated
public interface GoogleApiRequestTransport<T extends GoogleApiRequestTransport<T>>
    extends RequestTransport {
  void create(@SuppressWarnings("rawtypes") Receiver<GoogleApiRequestTransport> receiver);

  T setBaseUrl(String baseUrl);

  T setAccessToken(String accessToken);

  T setApiAccessKey(String apiAccessKey);

  T setApplicationName(String applicationName);
}
