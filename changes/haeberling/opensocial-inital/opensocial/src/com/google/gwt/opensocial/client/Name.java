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
 * Base interface for all name objects.
 * 
 * @see "http://code.google.com/apis/opensocial/docs/0.8/reference/#opensocial.Name"
 */
public final class Name extends JavaScriptObject {
  protected Name() {
  }

  /**
   * All of the fields that an name has. These are the supported keys for the
   * Name.getField() method.
   */
  public enum Field {
    /**
     * The family name. Specified as a String.
     */
    FAMILY_NAME("familyName"),

    /**
     * The given name. Specified as a String.
     */
    GIVEN_NAME("givenName"),

    /**
     * The additional name. Specified as a String.
     */
    ADDITIONAL_NAME("additionalName"),

    /**
     * The honorific prefix. Specified as a String.
     */
    HONORIFIC_PREFIX("honorificPrefix"),

    /**
     * The honorific suffix. Specified as a String.
     */
    HONORIFIC_SUFFIX("honorificSuffix"),

    /**
     * The unstructured name. Specified as a String.
     */
    UNSTRUCTURED("unstructured");

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
   * @param key The key to get data for; keys are defined in Name.Field
   * @return The data
   */
  public String getField(Field key) {
    return nativeGetField(key.toString());
  }
  
  private native String nativeGetField(String value) /*-{
    return this.getField(value);
  }-*/;
}
