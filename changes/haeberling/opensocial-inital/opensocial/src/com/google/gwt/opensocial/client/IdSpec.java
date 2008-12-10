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
 * Base interface for all id spec objects.
 *
 * @see "http://code.google.com/apis/opensocial/docs/0.8/reference/#opensocial.IdSpec"
 */
public class IdSpec extends JavaScriptObject {
  protected IdSpec() {
  }

  /**
   * All of the fields that an IdSpec has.
   */
  public enum Field {
    /**
     * A string representing the group id.
     */
    GROUP_ID("GROUP_ID"),
    /**
     * An optional numeric parameter, used to specify how many "hops" are
     * allowed between two people still considered part of the same group.
     */
    NETWORK_DISTANCE("NETWORK_DISTANCE"),
    /**
     * A string or an array of strings representing the user id.
     */
    USER_ID("USER_ID");

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
   * Constant person IDs available when fetching person information.
   */
  public enum PersonId {
    OWNER("OWNER"), VIEWER("VIEWER");

    private String value = null;

    private PersonId(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return this.value;
    }
  }

  /**
   * Gets the id spec's data that's associated with the specified key.
   */
  public String getField(Field key) {
    return nativeGetField(key.toString());
  }
  
  /**
   * Sets data for this id spec associated with the given key.
   */
  public void setField(Field key, String data) {
    nativeSetField(key.toString(), data);
  }
  
  private native String nativeGetField(String value) /*-{
    return this.getField(value);
  }-*/;

  private native void nativeSetField(String key, String data) /*-{
    this.setField(key, data);
  }-*/;
}
