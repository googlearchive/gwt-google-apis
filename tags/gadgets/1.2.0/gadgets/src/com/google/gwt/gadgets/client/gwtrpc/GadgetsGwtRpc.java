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
package com.google.gwt.gadgets.client.gwtrpc;

import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.user.client.rpc.RpcRequestBuilder;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

/**
 * Class providing a way to redirect GWT RPC calls through Gadgets container.
 */
public class GadgetsGwtRpc {

  /**
   * Redirects passed service through gadgets container which makes GWT RPC
   * possible in Gadgets environment.
   *
   * See <a href="http://code.google.com/p/gwt-google-apis/wiki/GadgetsFAQ#How_can_I_get_GWT_RPC_to_work_in_a_Gadget?"
   * >description of how to use GWT RPC in the Gadgets environment</a>.
   *
   * @param serviceDef definition of the service.
   */
  public static void redirectThroughProxy(ServiceDefTarget serviceDef) {
    serviceDef.setRpcRequestBuilder(new RpcRequestBuilder() {
      @Override
      protected RequestBuilder doCreate(String serviceEntryPoint) {
        return new GadgetsRequestBuilder(RequestBuilder.POST, serviceEntryPoint);
      }
    });
  }

  /**
   * This class is not meant to be instantiated.
   */
  private GadgetsGwtRpc() {
  }
}
