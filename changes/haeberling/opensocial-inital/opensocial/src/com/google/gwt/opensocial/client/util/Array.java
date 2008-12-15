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

package com.google.gwt.opensocial.client.util;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Overlay over a JavaScript array.
 *
 * @param <E> the type of the items in the list
 *
 * @TODO(haeberling): This should be renamed so it is less confusing.
 */
public class Array<E> extends JavaScriptObject {
  /**
   * Don't construct this object directly, instead use
   * {@link JavaScriptObject#createArray()}.
   */
  protected Array() {
  }

  /**
   * Removes all elements from the array.
   */
  public final native void clear() /*-{
    this.length = 0;
  }-*/;

  /**
   * Joins two arrays and returns the result.
   */
  public final native Array<E> concat(Array<E> arr1, Array<E> arr2) /*-{
    return this.concat(arr1, arr2);
  }-*/;

  /**
   * Returns the element at the given index.
   */
  public final native E get(int index) /*-{
    return this[index];
  }-*/;

  /**
   * Returns the first index of the given element.
   */
  public final native int indexOf(E search) /*-{
    if (!Array.prototype.indexOf) {
      Array.prototype.indexOf = function(elt) {
        for (var i = 0; i < this.length; i++) {
          if (i in this && this[i] === elt) {
            return from;
          }
        }
        return -1;
      };
    }
    return this.indexOf(search);
  }-*/;

  /**
   * Puts all the elements of an array into a string. The elements are separated
   * by a specified delimiter.
   */
  public final native String join(String delimiter) /*-{
    return this.join(delimiter);
  }-*/;

  /**
   * Returns the last index of the given element.
   */
  public final native int lastIndexOf(E search) /*-{
    if (!Array.prototype.ilastIdexOf) {
      Array.prototype.lastIndexOf = function(elt) {
        for (var i = this.length - 1; i >= 0; i--) {
          if (i in this && this[i] === elt) {
            return from;
          }
        }
        return -1;
      };
    }
    return this.lastIndexOf(search);
  }-*/;

  /**
   * Returns the length of the array.
   */
  public final native int length() /*-{
    return this.length;
  }-*/;

  /**
   * Removes and returns the last element of an array.
   */
  public final native E pop() /*-{
    return this.pop();
  }-*/;

  /**
   * Adds one or more elements to the end of an array and returns the new length.
   */
  public final native int push(E element) /*-{
    return this.push(element);
  }-*/;

  /**
   * Removes the given element from the array.
   */
  public final void remove(E element) {
    int index = indexOf(element);
    splice(index, 1);
  }

  /**
   * Reverses the order of the elements in an array.
   */
  public final native Array<E> reverse() /*-{
    return this.reverse();
  }-*/;

  /**
   * Removes and returns the first element of an array.
   */
  public final native E shift() /*-{
    return this.shift();
  }-*/;

  /**
   * Returns selected elements from an existing array.
   */
  public final native Array<E> slice(int start, int end) /*-{
    return this.slice(start, end);
  }-*/;

  /**
   * Sorts the elements of an array.
   */
  public final native void sort() /*-{
    this.sort();
  }-*/;

  /**
   * Removes and adds new elements to an array.
   */
  public final native Array<E> splice(int index, int howMany) /*-{
    return this.splice(index, howMany);
  }-*/;

  /**
   * Adds an elements to the beginning of an array and returns the new
   * length.
   */
  public final native int unshift(E element) /*-{
    return this.unshift(element);
  }-*/;
}
