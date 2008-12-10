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
 * Base interface for all BodyType objects.
 * 
 * @see "http://code.google.com/apis/opensocial/docs/0.8/reference/#opensocial.BodyType"
 */
public final class BodyType extends JavaScriptObject {
  protected BodyType() {
  }

  /**
   * All of the fields that an BodyType has. These are the supported keys for
   * the BodyType.getField() method.
   */
  public enum Field {

    /**
     * The build of the person's body, specified as a string. Not supported by
     * all containers.
     */
    BUILD("build"),

    /**
     * The height of the person in meters, specified as a number. Not supported
     * by all containers.
     */
    HEIGHT("height"),

    /**
     * The weight of the person in kilograms, specified as a number. Not
     * supported by all containers.
     */
    WEIGHT("weight"),

    /**
     * The eye color of the person, specified as a string. Not supported by all
     * containers.
     */
    EYE_COLOR("eyeColor"),

    /**
     * The hair color of the person, specified as a string. Not supported by all
     * containers.
     */
    HAIR_COLOR("hairColor");
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
   * @param key The key to get data for; keys are defined in BodyType.Field
   * @return The data
   */
  public String getField(Field key) {
    return nativeGetField(key.toString());
  }
  
  private native String nativeGetField(String value) /*-{
    return this.getField(value);
  }-*/;
}
