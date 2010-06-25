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

/**
 * A superclass for all request builders building requests returning collections
 * of objects.
 *
 * @param <T> Specific request builder implementation.
 */
public abstract class CollectionRequestBuilder<T extends CollectionRequestBuilder<?>>
    extends OsapiRequestBuilder<T> {

  /**
   * Required by {@link com.google.gwt.core.client.JavaScriptObject} policy.
   */
  protected CollectionRequestBuilder() {
  };

  /**
   * Indicates to return elements where <code>value</code> appears somewhere in
   * the element's <code>field</code> value. Note that only one filter can be
   * set on request and previously set filters (of any type) are canceled with
   * invocation of this method.
   *
   * @param field Identifier of the field to use for filtering.
   * @param value Value to use for filtering.
   */
  public final native T setContainsFilter(String field, String value) /*-{
    this.filterBy = field;
    this.filterOp = "contains";
    this.filterValue = value;
    return this;
  }-*/;

  /**
   * Sets the page size for a paged collection. If this parameter is not
   * specified the container can choose how many items in the collection should
   * be returned.
   *
   * @param count The page size for a paged collection.
   */
  public final native T setCount(int count) /*-{
    this.count = count;
    return this;
  }-*/;

  /**
   * Indicates to return elements where <code>value</code> exactly matches the
   * element's <code>field</code> value. Note that only one filter can be set on
   * request and previously set filters (of any type) are canceled with
   * invocation of this method.
   *
   * @param field Identifier of the field to use for filtering.
   * @param value Value to use for filtering.
   */
  public final native T setEqualsFilter(String field, String value) /*-{
    this.filterBy = field;
    this.filterOp = "equals";
    this.filterValue = value;
    return this;
  }-*/;

  /**
   * Indicates to return elements where the element's <code>field</code> value
   * is not null or empty. Note that only one filter can be set on request and
   * previously set filters (of any type) are canceled with invocation of this
   * method.
   *
   * @param field Identifier of the field to use for filtering.
   */
  public final native T setPresentFilter(String field) /*-{
    this.filterBy = field;
    this.filterOp = "present";
    this.filterValue = undefined;
    return this;
  }-*/;

  /**
   * Indicates to sort return elements by the element's <code>field</code> value
   * and given order.
   *
   * @param field Identifier of the field to use for sorting.
   * @param order The order to use for sorting.
   */
  public final native T setSorting(String field, SortOrder order) /*-{
    this.sortBy = field;
    this.sortOrder = order.@com.google.gwt.gadgets.client.osapi.SortOrder::getOrder()();
    return this;
  }-*/;

  /**
   * Sets start index for a paged collection. This value is relative to the
   * starting index of all results that would be returned if no startIndex had
   * been specified, but with specified filtering.
   *
   * @param startIndex Start index for a paged collection.
   */
  public final native T setStartIndex(int startIndex) /*-{
    this.startIndex = startIndex;
    return this;
  }-*/;

  /**
   * Indicates to return elements where <code>value</code> is an exact prefix of
   * the element's <code>field</code> value. Note that only one filter can be
   * set on request and previously set filters (of any type) are canceled with
   * invocation of this method.
   *
   * @param field Identifier of the field to use for filtering.
   * @param value Value to use for filtering.
   */
  public final native T setStartsWithFilter(String field, String value) /*-{
    this.filterBy = field;
    this.filterOp = "startsWith";
    this.filterValue = value;
    return this;
  }-*/;
}
