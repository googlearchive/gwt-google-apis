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
package com.google.gwt.gadgets.client.io;

/**
 * An enum representing HTTP request methods. Values of this enum are passed to
 * the {@link RequestOptions} instance. Note that the only request method
 * required by container to implement is {@link MethodType#GET} method.
 * Implementation of remaining methods is optional.
 *
 *
 * See <a href="http://code.google.com/intl/pl/apis/gadgets/docs/reference/#gadgets.io.MethodType"
 * >gadgets specification</a> for reference.
 */
public enum MethodType {
  DELETE("DELETE"), GET("GET"), HEAD("HEAD"), POST("POST"), PUT("PUT");

  private String methodType;

  private MethodType(String methodType) {
    this.methodType = methodType;
  }

  /**
   * Returns the {@link String} representing HTTP request method.
   *
   * @return the {@link String} representing HTTP request method.
   */
  public String getMethodType() {
    return methodType;
  }
}
