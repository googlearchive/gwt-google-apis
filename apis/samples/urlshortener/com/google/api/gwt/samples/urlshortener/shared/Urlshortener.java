/*
 * Copyright (c) 2011 Google Inc.
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

package com.google.api.gwt.samples.urlshortener.shared;

import com.google.web.bindery.autobean.shared.AutoBean.PropertyName;
import com.google.web.bindery.requestfactory.shared.JsonRpcContent;
import com.google.web.bindery.requestfactory.shared.JsonRpcService;
import com.google.web.bindery.requestfactory.shared.JsonRpcWireName;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestFactory;

/**
 * Service definition for URL Shortener API (v1).
 *
 * @author jasonhall@google.com (Jason Hall)
 */
public interface Urlshortener extends RequestFactory {

  /** Returns a new {@link RequestContext}. */
  UrlContext url();

  /**
   * A specific {@link RequestContext}, which encapsulates creation of new
   * {@link Url} instances to be passed to the {@link #insert(Url)} method, and
   * which can be executed using the {@link #fire()} method. Once fired, the
   * RequestContext cannot be reused, and a new one must be created, which in
   * this case can be done by calling {@link Urlshortener#url()}.
   */
  @JsonRpcService
  public interface UrlContext extends RequestContext {

    /**
     * Creates a new short URL.
     *
     * @param content Request body content.
     */
    InsertRequest insert(@JsonRpcContent Url content);

    /** Request object to call the "urlshortener.url.insert" method. */
    @JsonRpcWireName(value = "urlshortener.url.insert", version = "v1")
    public interface InsertRequest extends Request<Url> {
    }

    /**
     * Expands a short URL.
     *
     * @param shortUrl The short URL, including the protocol.
     */
    GetRequest get(@PropertyName("shortUrl") String shortUrl);

    /** Request object to call the "urlshortener.url.get" method. */
    @JsonRpcWireName(value = "urlshortener.url.get", version = "v1")
    public interface GetRequest extends Request<Url> {
    }
  }
}
