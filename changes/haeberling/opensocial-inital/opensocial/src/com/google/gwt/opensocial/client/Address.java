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
 * Base interface for all address objects.
 * 
 * @see "http://code.google.com/apis/opensocial/docs/0.8/reference/#opensocial.Address"
 */
public final class Address extends JavaScriptObject {
  protected Address() {
  }

  /**
   * All of the fields that an Address has. These are the supported keys for the
   * Address.getField() method.
   */
  public enum Field {
    /**
     * The address type or label. Examples: work, my favorite store, my house,
     * etc Specified as a String.
     *
     *
     */
    TYPE("type"),

    /**
     * If the container does not have structured addresses in its data store,
     * this field will return the unstructured address that the user entered.
     * Use opensocial.getEnvironment().supportsField to see which fields are
     * supported. Specified as a String.
     *
     *
     */
    UNSTRUCTURED_ADDRESS("unstructuredAddress"),

    /**
     * The po box of the address if there is one. Specified as a String.
     *
     *
     */
    PO_BOX("poBox"),

    /**
     * The street address. Specified as a String.
     *
     *
     */
    STREET_ADDRESS("streetAddress"),

    /**
     * The extended street address. Specified as a String.
     *
     *
     */
    EXTENDED_ADDRESS("extendedAddress"),

    /**
     * The region. Specified as a String.
     *
     *
     */
    REGION("region"),

    /**
     * The locality. Specified as a String.
     *
     *
     */
    LOCALITY("locality"),

    /**
     * The postal code. Specified as a String.
     *
     *
     */
    POSTAL_CODE("postalCode"),

    /**
     * The country. Specified as a String.
     *
     *
     */
    COUNTRY("country"),

    /**
     * The latitude. Specified as a Number.
     *
     *
     */
    LATITUDE("latitude"),

    /**
     * The longitude. Specified as a Number.
     *
     *
     */
    LONGITUDE("longitude");

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
   */
  public String getField(Field key) {
    return nativeGetField(key.toString());
  }
  
  private native String nativeGetField(String value) /*-{
    return this.getField(value);
  }-*/;
}
