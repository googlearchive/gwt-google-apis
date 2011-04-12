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

import com.google.api.gwt.server.impl.DesktopOAuth2Login;
import com.google.api.gwt.server.impl.SimpleRequestTransport;

/**
 * Contains helper methods to be overridden in GWT super-source.
 */
class TransportHelper {
  static OAuth2Login createLogin() {
    return new DesktopOAuth2Login();
  }

  static GoogleApiRequestTransport createTransport() {
    return new SimpleRequestTransport();
  }
}
