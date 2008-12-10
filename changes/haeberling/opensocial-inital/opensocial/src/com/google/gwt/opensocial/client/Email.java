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
 * Base interface for all email objects.
 *
 * @see "http://code.google.com/apis/opensocial/docs/0.8/reference/#opensocial.Email"
 */
public final class Email extends JavaScriptObject {
  protected Email() {
  }

  /**
   * All of the fields that an email has. These are the supported keys for the
   * Email.getField() method.
   */
  public enum Field {
    /**
     * The email type or label, specified as a String. Examples: work, my
     * favorite store, my house, etc.
     */
    TYPE("type"),

    /**
     * The email address, specified as a String.
     */
    ADDRESS("address");

    private String value = null;

    private Field(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return this.value;
    }
  }

  /**
   * Gets data for this body type that is associated with the specified key.
   *
   * @param key The key to get data for; keys are defined in Email.Field
   * @return The data
   */
  public String getField(Field key) {
    return nativeGetField(key.toString());
  }
  
  private native String nativeGetField(String value) /*-{
    return this.getField(value);
  }-*/;
}
