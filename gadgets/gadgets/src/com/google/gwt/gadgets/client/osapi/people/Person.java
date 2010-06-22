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
package com.google.gwt.gadgets.client.osapi.people;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * A class that represents a person and defines a minimal set of fields that
 * opensocial container is required to implement. This class is meant to be
 * extended to be used with specific containers.
 */
public class Person extends JavaScriptObject {

  /**
   * Field identifier for person's name suitable for display.
   */
  public static final String DISPLAY_NAME = "displayName";
  /**
   * Field identifier for person's string ID.
   */
  public static final String ID = "id";
  /**
   * Field identifier for person's photo thumbnail URL.
   */
  public static final String THUMBNAIL_URL = "thumbnailUrl";

  /**
   * Required by {@link JavaScriptObject} policy.
   */
  protected Person() {
  }

  /**
   * Returns the name of this Person, suitable for display to end-users.
   * 
   * @return The name of this Person, suitable for display to end-users.
   */
  public final native String getDisplayName() /*-{
    return (this[@com.google.gwt.gadgets.client.osapi.people.Person::DISPLAY_NAME] == null) ? null : this[@com.google.gwt.gadgets.client.osapi.people.Person::DISPLAY_NAME];
  }-*/;

  /**
   * Returns a string Id that can be permanently associated with this person.
   * For the anonymous viewer, this value is -1.
   * 
   * @return A string Id that can be permanently associated with this person.
   */
  public final native String getId() /*-{
    return (this[@com.google.gwt.gadgets.client.osapi.people.Person::ID] == null) ? null : this[@com.google.gwt.gadgets.client.osapi.people.Person::ID];
  }-*/;

  /**
   * Returns person's photo thumbnail URL, specified as a string. Returned URL
   * is fully qualified.
   * 
   * @return Person's photo thumbnail URL, specified as a string.
   */
  public final native String getThumbnailUrl() /*-{
    return (this[@com.google.gwt.gadgets.client.osapi.people.Person::THUMBNAIL_URL] == null) ? null : this[@com.google.gwt.gadgets.client.osapi.people.Person::THUMBNAIL_URL];
  }-*/;
}