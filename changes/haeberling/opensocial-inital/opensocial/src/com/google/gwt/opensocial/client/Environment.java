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

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Represents the current environment for a gadget.
 *
 * @see "http://code.google.com/apis/opensocial/docs/0.8/reference/#opensocial.Environment"
 * @see OpenSocialFeature#getEnvironment()
 */
public final class Environment extends JavaScriptObject {
  protected Environment() {
  }

  /**
   * The types of objects in this container.
   */
  public enum ObjectType {
    PERSON("person"),
    ADDRESS("address"),
    BODY_TYPE("bodyType"),
    EMAIL("email"),
    NAME("name"),
    ORGANIZATION("organization"),
    PHONE("phone"),
    URL("url"),
    ACTIVITY("activity"),
    MEDIA_ITEM("mediaItem"),
    MESSAGE("message"),
    MESSAGE_TYPE("messageType"),
    SORT_ORDER("sortOrder"),
    FILTER_TYPE("filterType");

    private String value = null;

    private ObjectType(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return value;
    }
  }

  /**
   * Returns the current domain for example, "orkut.com" or "myspace.com".
   *
   * @return The Domain
   */
  public native String getDomain() /*-{
     return this.getDomain();
   }-*/;

  /**
   * Returns true if the specified field is supported in this container on the
   * given object type.
   *
   * @param objectType The object type to check for the field
   * @param fieldName The name of the field to check for
   * @return True if the field is supported on the specified object type
   */
  public boolean supportsField(ObjectType objectType, String fieldName) {
    String objectTypeStr = "";
    if (objectType != null) {
      objectTypeStr = objectType.toString();
    }
    return nativeSupportsField(objectTypeStr, fieldName);
  }

  private native boolean nativeSupportsField(String objectTypeStr, String fieldName) /*-{
    return this.supportsField(objectTypeStr, fieldName);
  }-*/;
}
