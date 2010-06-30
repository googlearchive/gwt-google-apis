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
package com.google.gwt.gadgets.client.osapi;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

import java.util.Iterator;

/**
 * A class representing OpenSocial collection.
 *
 * @param <T> Type of objects in the collection.
 */
public class OsapiCollection<T extends JavaScriptObject> extends
    JavaScriptObject {

  /**
   * Required by {@link JavaScriptObject} policy.
   */
  protected OsapiCollection() {
  };

  /**
   * Returns the number of results returned per page in this response. In
   * general, this will be equal to the value set in the
   * {@link CollectionRequestBuilder#setCount(int)} method, but MAY be less if
   * the Service Provider is unwilling to return as many results per page as
   * requested, or if there are less than the requested number of results left
   * to return when starting at the current start index. This field MUST be
   * present if and only if a value for count is specified in the request.
   */
  public final native int getItemsPerPage() /*-{
    return this.itemsPerPage;
  }-*/;

  /**
   * Returns a JavaScript array with collection's content.
   *
   * @return A JavaScript array with collection's content.
   */
  public final native JsArray<T> getList() /*-{
    return (this.list == null) ? null : this.list;
  }-*/;

  /**
   * Returns the index of the first result returned in this response, relative
   * to the starting index of all results that would be returned if no
   * startIndex had been requested. In general, this will be equal to the value
   * set in the {@link CollectionRequestBuilder#setStartIndex(int)} method, or 0
   * if no specific startIndex was requested.
   */
  public final native int getStartIndex() /*-{
    return this.startIndex;
  }-*/;

  /**
   * Returns the total number of results that would be returned if there were no
   * start index or count specified. This value tells the Consumer how many
   * total results to expect, regardless of the current pagination being used,
   * but taking into account the current filtering options in the request.
   */
  public final native int getTotalResults() /*-{
    return this.totalResults;
  }-*/;

  /**
   * Returns {@link Iterable} for use with for-each loops.
   *
   * @return {@link Iterable} for use with for-each loops.
   */
  public final Iterable<T> iterable() {
    return new Iterable<T>() {
      public Iterator<T> iterator() {
        return new Iterator<T>() {
          int i = 0;

          public boolean hasNext() {
            return i < getList().length();
          }

          public T next() {
            return getList().get(i++);
          }

          public void remove() {
            throw new UnsupportedOperationException();
          }
        };
      };
    };
  }
}
