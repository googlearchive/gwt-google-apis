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
package com.google.gwt.gadgets.sample.gadgetrpc.server;

import com.google.gwt.gadgets.sample.gadgetrpc.client.GadgetService;
import com.google.gwt.gadgets.sample.gadgetrpc.client.ServerInfo;
import com.google.gwt.user.server.rpc.RPCServletUtils;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * A sample servlet that returns information about the server.
 */
public class GadgetRPCServlet extends RemoteServiceServlet implements
    GadgetService {
  private static Date servletStartTime = new Date();

  public ServerInfo getServerInfo() {
    ServerInfo info = new ServerInfo();
    info.setServletStartTime(servletStartTime);
    info.setCurrentTime(new Date());
    return info;
  }

  /**
   * Do not validate HTTP headers - iGoogle munges them.
   *  application/x-www-form-urlencoded
   */
  @Override
  public String readContent(HttpServletRequest request) throws ServletException, IOException {
    return RPCServletUtils.readContent(request, "application/x-www-form-urlencoded", null);
  }
  
  @Override
  protected void checkPermutationStrongName() throws SecurityException {
   // do nothing - skip this for now - XSFR block
  }
}
