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
package com.google.gwt.gadgets.client.osapi.albums;

/**
 * A class representing a service object, which maps to opensocial Albums
 * Service.
 *
 * @see "http://opensocial-resources.googlecode.com/svn/spec/1.0/Social-API-Server.xml#Albums-Service"
 */
public class AlbumsService {

  private static AlbumsService service;

  /**
   * Returns an instance of {@link AlbumsService}. Note that this class is a
   * singleton and all instances returned by this method are identical.
   *
   * @return An instance of {@link AlbumsService}.
   */
  public static AlbumsService getInstance() {
    if (service == null) {
      service = new AlbumsService();
    }
    return service;
  }

  /**
   * Private constructor to enforce singleton pattern.
   */
  private AlbumsService() {
  }

  /**
   * Returns an instance of {@link GetAlbumsRequestBuilder}.
   *
   * @return An instance of {@link GetAlbumsRequestBuilder}.
   */
  public GetAlbumsRequestBuilder newGetAlbumsRequestBuilder() {
    return GetAlbumsRequestBuilder.newInstance();
  }
}
