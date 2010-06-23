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
package com.google.gwt.gadgets.sample.traveler.client;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.gadgets.client.io.AuthorizationType;
import com.google.gwt.gadgets.client.io.GadgetsIo;
import com.google.gwt.gadgets.client.io.IoProvider;
import com.google.gwt.gadgets.client.io.MethodType;
import com.google.gwt.gadgets.client.io.RequestOptions;
import com.google.gwt.gadgets.client.io.ResponseReceivedHandler;

/**
 * Component responsible for communication with
 * {@link com.google.gwt.gadgets.sample.traveler.server.TravelerServlet}.
 *
 * All requests made by this component use authorization provided by OpenSocial
 * container. Consult http://oauth.net/core/1.0/#signing_process for details.
 */
public class TravelerServletClient {

  private static final String SERVLET_NAME = "travelerservlet";
  private GadgetsIo io;
  private String servletUrl;

  public TravelerServletClient(String serverUrl) {
    this.servletUrl = serverUrl + SERVLET_NAME;
    this.io = IoProvider.get();
  }

  public void getLocations(ResponseReceivedHandler<JsArray<Location>> callback) {
    getUsersLocations(null, callback);
  }

  public void getUsersLocations(String userId,
      ResponseReceivedHandler<JsArray<Location>> callback) {
    String url = servletUrl;
    url += "?nocache=" + Math.random();
    if (userId != null) {
      url += "&userId=" + userId;
    }

    RequestOptions opts = RequestOptions.newInstance().setAuthorizationType(
        AuthorizationType.SIGNED);
    io.makeRequestAsJso(url, callback, opts);
  }

  public void saveLocation(Location location,
      ResponseReceivedHandler<Object> callback) {
    RequestOptions params = RequestOptions.newInstance();
    params.setMethodType(MethodType.POST).setPostData(io.encodeValues(location));
    params.setAuthorizationType(AuthorizationType.SIGNED);
    io.makeRequest(servletUrl, callback, params);
  }

  public void deleteLocation(Location location,
      ResponseReceivedHandler<Object> callback) {
    String url = servletUrl + "?key=" + location.getKey();
    RequestOptions params = RequestOptions.newInstance();
    params.setMethodType(MethodType.DELETE).setAuthorizationType(
        AuthorizationType.SIGNED);
    io.makeRequest(url, callback, params);
  }
}
