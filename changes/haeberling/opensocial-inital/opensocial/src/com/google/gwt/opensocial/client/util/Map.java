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
 * Implements a map in a less JavaScript-code-size-inflating way than java.util.Map.
 *
 * @param <K> the type of the keys of the map
 * @param <V> the type of the values in the map
 * 
 * @TODO(haeberling): This should be renamed so it is less confusing.
 * @TODO(haeberlong): V should at some point extend JavaScriptObject
 */
public class Map<K, V> extends JavaScriptObject {
  protected Map() {
  }
  
  public final V get(K key) {
    return nativeGet(key.toString());
  }

  public final void put(K key, V value) {
    nativePut(key.toString(), value);
  }

  private final native V nativeGet(String key) /*-{
    return this[key];
  }-*/;

  private final native void nativePut(String key, V value) /*-{
    this[key] = value;
  }-*/;
}
