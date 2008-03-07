/*
 * Copyright 2007 Google Inc.
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
package com.google.gwt.ajaxsearch.client;

import com.google.gwt.jsio.client.JSOpaque;

/**
 * Used with {@link LocalSearch#setAddressLookupMode(AddressLookupMode)}.
 */
public final class AddressLookupMode extends JSOpaque {
  /**
   * Disables resolving addresses.
   */
  public static final AddressLookupMode DISABLED =
      new AddressLookupMode("DISABLED");
  
  /**
   * Enables resolving addresses.
   */
  public static final AddressLookupMode ENABLED =
      new AddressLookupMode("ENABLED");

  private AddressLookupMode(String mode) {
    super("$wnd.GlocalSearch.ADDRESS_LOOKUP_" + mode);
  }
}
