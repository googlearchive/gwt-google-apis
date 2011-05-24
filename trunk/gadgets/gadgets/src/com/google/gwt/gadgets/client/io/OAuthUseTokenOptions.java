/*
 * Copyright 2011 Google Inc.
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
package com.google.gwt.gadgets.client.io;

/**
 * An enum representing the gadgets.io.RequestParameters.OAUTH_USE_TOKEN
 * options.
 */
public enum OAuthUseTokenOptions {
  ALWAYS("always"), IF_AVAILABLE("if_available"), NEVER("never");

  private final String useTokenOption;

  private OAuthUseTokenOptions(String useTokenOption) {
    this.useTokenOption = useTokenOption;
  }

  /**
   * Returns the {@link String} representing when to use OAuth tokens.
   * 
   * @return the {@link String} representing when to use OAuth tokens.
   */
  public String getUseTokenOption() {
    return useTokenOption;
  }
}
