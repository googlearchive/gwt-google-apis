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
package com.google.gwt.maps.client.geocode;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * A base class used to represent a client side cache for Geocoder requests.
 * 
 * This class is a base class but is not intended to be extended directly. For a
 * functional class that you can also extend, see {@link CustomGeocodeCache}.
 */
public abstract class GeocodeCache {

  /**
   * Used by JSIO library to instantiate this class from an existing JavaScript
   * object.
   */
  @SuppressWarnings("unused")
  private static GeocodeCache createPeer(JavaScriptObject jsoPeer) {
    return new CustomGeocodeCache(jsoPeer);
  }

  protected final JavaScriptObject jsoPeer;

  /**
   * @deprecated use {@link GeocodeCache#GeocodeCache(JavaScriptObject)} instead
   */
  @Deprecated
  protected GeocodeCache() {
    jsoPeer = null;
  }

  /**
   * Instantiate a new wrapper object from an existing JavaScript instance.
   */
  protected GeocodeCache(JavaScriptObject jsoPeer) {
    this.jsoPeer = jsoPeer;
  }

  /**
   * Returns the reply which was stored under the given address. If no reply was
   * ever stored for the given address, this method returns <code>null</code>
   * 
   * @param address the address used as a key to lookup.
   * @return the previously cached result.
   */
  public abstract JavaScriptObject get(String address);

  /**
   * Returns whether or not the given reply should be cached. By default very
   * rudimentary checks are performed on the reply object. In particular, this
   * class makes sure that the object is not <code>null</code> and that it has
   * the name field . This method may be overridden by extending classes to
   * provide more precise conditions on the reply object.
   * 
   * @param reply the reply to test.
   * @return true if the value can be cached.
   */
  public abstract boolean isCacheable(JavaScriptObject reply);

  /**
   * Stores the given reply under the given address. This method calls the
   * {@link #isCacheable} method to verify that the reply may be cached. If it
   * gets a go-ahead, it caches the reply under the address normalized with the
   * help of the {@link #toCanonical} method.
   * 
   * @param address the address used in the query.
   * @param reply the reply value to cache.
   */
  public abstract void put(String address, JavaScriptObject reply);

  /**
   * Purges all replies from the cache. After this method returns, the cache is
   * empty.
   */
  public abstract void reset();

  /**
   * Returns what is considered a canonical version of the address. It converts
   * the address parameter to lower case, replaces commas with spaces and
   * replaces multiple spaces with one space.
   * 
   * @param address the address to convert to canonical form.
   * @return the address in canonical form.
   */
  public abstract String toCanonical(String address);
}
