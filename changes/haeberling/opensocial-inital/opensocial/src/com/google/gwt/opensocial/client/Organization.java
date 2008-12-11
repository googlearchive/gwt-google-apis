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
 * Base interface for all Organization objects.
 * 
 * @see "http://code.google.com/apis/opensocial/docs/0.8/reference/#opensocial.Organization"
 */
public final class Organization extends JavaScriptObject {
  /**
   * All of the fields that an name has. These are the supported keys for
   * {@link Organization#getField(Field)}.
   */
  public enum Field {
    /**
     * The name of the organization. For example, could be a school name or a
     * job company. Specified as a string. Not supported by all containers.
     */
    NAME("name"),

    /**
     * The title or role the person has in the organization, specified as a
     * string. This could be graduate student, or software engineer. Not
     * supported by all containers.
     */
    TITLE("title"),

    /**
     * A description or notes about the person's work in the organization,
     * specified as a string. This could be the courses taken by a student, or a
     * more detailed description about a Organization role. Not supported by all
     * containers.
     */
    DESCRIPTION("description"),

    /**
     * The field the organization is in, specified as a string. This could be
     * the degree pursued if the organization is a school. Not supported by all
     * containers.
     */
    FIELD("field"),

    /**
     * The subfield the Organization is in, specified as a string. Not supported
     * by all containers.
     */
    SUB_FIELD("subField"),

    /**
     * The date the person started at the organization, specified as a Date. Not
     * supported by all containers.
     */
    START_DATE("startDate"),

    /**
     * The date the person stopped at the organization, specified as a Date. A
     * null date indicates that the person is still involved with the
     * organization. Not supported by all containers.
     */
    END_DATE("endDate"),

    /**
     * The salary the person receieves from the organization, specified as a
     * string. Not supported by all containers.
     */
    SALARY("salary"),

    /**
     * The address of the organization, specified as an opensocial.Address. Not
     * supported by all containers.
     */
    ADDRESS("address"),

    /**
     * A webpage related to the organization, specified as a string. Not
     * supported by all containers.
     */
    WEBPAGE("webpage");
    private String value = null;

    private Field(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return this.value;
    }
  }

  protected Organization() {
  }

  /**
   * Gets data for this body type that is associated with the specified key.
   *
   * @param key The key to get data for; keys are defined in Organization.Field
   * @return The data
   */
  public String getField(Field key) {
    return nativeGetField(key.toString());
  }
  
  private native String nativeGetField(String value) /*-{
    return this.getField(value);
  }-*/;
}
