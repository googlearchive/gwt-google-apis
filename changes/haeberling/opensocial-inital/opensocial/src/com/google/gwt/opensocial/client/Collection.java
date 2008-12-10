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
import com.google.gwt.opensocial.client.util.Array;
import com.google.gwt.opensocial.client.util.Data;

/**
 * Collection of multiple objects with useful accessors. May also represent
 * subset of a larger collection (for example, page 1 of 10) and contain
 * information about the larger collection.
 * 
 * @see "http://code.google.com/apis/opensocial/docs/0.8/reference/#opensocial.Collection"
 * @param <T> the type of the elements of the collection
 */
public class Collection<T> extends Data {
  protected Collection() {
  }

  /**
   * Returns an array of all the objects in this collection.
   */
  public final native Array<T> asArray() /*-{
    return this.asArray();
  }-*/;

  /**
   * Executes the provided function once per member of the collection, with each
   * member in turn as the parameter to the function.
   *
   * @param function A JavaScript function with one argument, to call on each
   *        member of the collection
   */
  public final native void each(JavaScriptObject function) /*-{
     this.each(function);
   }-*/;

  /**
   * Finds the entry with the given ID value, or returns null if none is found.
   */
  // TODO(rohitghatol) : Check if this really works
  public final native T getById(String id) /*-{
    return this.getById(id);
  }-*/;

  /**
   * Gets the offset of this collection within a larger result set.
   */
  public final native int getOffset() /*-{
    return this.getOffset();
  }-*/;

  /**
   * Gets the total size of the larger result set that this collection belongs
   * to.
   */
  public final native int getTotalSize() /*-{
    return this.getTotalSize();
  }-*/;

  /**
   * Gets the size of this collection, which is equal to or less than the total
   * size of the result.
   */
  public final native int size() /*-{
    return this.size();
  }-*/;
}
