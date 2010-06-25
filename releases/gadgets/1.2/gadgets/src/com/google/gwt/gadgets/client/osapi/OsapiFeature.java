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
package com.google.gwt.gadgets.client.osapi;

import com.google.gwt.gadgets.client.GadgetFeature;
import com.google.gwt.gadgets.client.osapi.people.PeopleService;

/**
 * Provides access to social features of the container.
 *
 * @see "http://code.google.com/intl/pl/apis/opensocial/"
 */
public class OsapiFeature implements GadgetFeature {

  // Special values for UserId
  public static final String USER_OWNER = "@owner";
  public static final String USER_VIEWER = "@viewer";

  // Special values for GroupId
  public static final String GROUP_ALL = "@all";
  public static final String GROUP_SELF = "@self";
  public static final String GROUP_FRIENDS = "@friends";

  private OsapiFeature() {
  }

  /**
   * Returns an instance of {@link PeopleService}.
   *
   * @return An instance of {@link PeopleService}.
   */
  public PeopleService getPeopleService() {
    return PeopleService.getInstance();
  }
}
