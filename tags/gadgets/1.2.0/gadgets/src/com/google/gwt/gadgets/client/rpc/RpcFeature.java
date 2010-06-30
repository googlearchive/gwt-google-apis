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
package com.google.gwt.gadgets.client.rpc;

/**
 * Provides access to the gadgets.rpc feature.
 * 
 * This is just a skeleton implementation that will add the RPC feature to the
 * gadget spec and allow a developer to write JSNI methods to access RPC from
 * JavaScript. For example:
 * 
 * <pre>
 *   public static native void callMyRpc(String arg1, int arg2, JavaScriptObject arg3)
 *    ...
 *    $wnd.gadgets.rpc.call("targetId", "serviceName",  arg1, arg2, arg3, function(callbackArg) {
 *      ...
 *    });
 * </pre>
 * 
 * @see "http://code.google.com/p/gwt-google-apis/issues/detail?id=390"
 */
public interface RpcFeature {
  /*
   * TODO(zundel): There real RPC methods are not implemented because they rely
   * on JavaScript's dynamic typing. A full, flexible solution in GWT would
   * require special generator support where users could declare RPC methods
   * return values as they do for declaring GWT-RPC interfaces.
   */

  /**
   * Unregisters the default service handler.
   */
  void unregisterDefault();
}