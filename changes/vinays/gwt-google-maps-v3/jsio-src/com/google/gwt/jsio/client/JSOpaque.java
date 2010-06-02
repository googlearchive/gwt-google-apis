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
package com.google.gwt.jsio.client;

/**
 * Allows by-name references to JavaScript values. This is intended for use with
 * opaque values such as those used in enumeration-like types.
 */
public class JSOpaque {
  /**
   * Stores the named reference. This value is never intended to be read or
   * dereferenced by GWT developers.
   */
  private final String reference;

  /**
   * Constructor.
   * 
   * @param reference A named reference to a globally-defined value.
   */
  public JSOpaque(String reference) {
    this.reference = reference;
  }

  /**
   * Object identity between JSOpaque instances is based on their reference.
   * 
   * @return <code>true</code> if the other JSOpaque has the same reference.
   */
  public final boolean equals(JSOpaque o) {
    return reference.equals(o.reference);
  }

  /**
   * Allows comparisons of the JSOpaque to JavaScriptObjects.
   */
  @Override
  public native boolean equals(Object o) /*-{
    // We use eval so that JSOpaques don't have to be generated classes
    var result = eval(this.@com.google.gwt.jsio.client.JSOpaque::reference);
    
    // Object versus everything else
    if (typeof(result) == 'object' && typeof(o) == 'object') {
    return result.equals(o);
    } else {
    return result == o;
    }
    }-*/;

  @Override
  public int hashCode() {
    return reference.hashCode();
  }

  /**
   * Convenience method for comparing object identity.
   * 
   * @return <code>true</code> if the value represented by the JSOpaque shares
   *         identity with the value represented by <code>o</code>.
   */
  public final native boolean identityEquals(JSOpaque o) /*-{
    return eval(this.@com.google.gwt.jsio.client.JSOpaque::reference) ===
    eval(o.@com.google.gwt.jsio.client.JSOpaque::reference);
    }-*/;

  /**
   * Convenience method for comparing object identity.
   * 
   * @return <code>true</code> if the value represented by the JSOpaque shares
   *         identity with <code>o</code>
   */
  public final native boolean identityEquals(Object o) /*-{
    return eval(this.@com.google.gwt.jsio.client.JSOpaque::reference) === o;
    }-*/;

  @Override
  public native String toString() /*-{
    return String(eval(this.@com.google.gwt.jsio.client.JSOpaque::reference));
    }-*/;
}
