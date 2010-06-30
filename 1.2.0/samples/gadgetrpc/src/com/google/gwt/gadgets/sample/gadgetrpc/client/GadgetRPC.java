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
package com.google.gwt.gadgets.sample.gadgetrpc.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.gadgets.client.Gadget;
import com.google.gwt.gadgets.client.UserPreferences;
import com.google.gwt.gadgets.client.Gadget.AllowHtmlQuirksMode;
import com.google.gwt.gadgets.client.Gadget.ModulePrefs;
import com.google.gwt.gadgets.client.Gadget.UseLongManifestName;
import com.google.gwt.gadgets.client.gwtrpc.GadgetsGwtRpc;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * A demonstration of how to use GWT RPC with the gwt-gadgets library.
 */
@ModulePrefs(title = "Gadget RPC Demo", author = "Eric Z", author_email = "zundel+gadgets@google.com")
// Create a short manifest name (instead of prepending the package prefix)
@UseLongManifestName(false)
@AllowHtmlQuirksMode(false)
public class GadgetRPC extends Gadget<GadgetRPC.Preferences> {

  public static interface Preferences extends UserPreferences {
  }

  private Label serverStartedText = new Label("<not retrieved>");
  private Label serverCurrentText = new Label("<not retrieved>");
  private Label exceptionInfo = new Label();
  private GadgetServiceAsync gadgetService;
  private AsyncCallback<ServerInfo> rpcCallback = new AsyncCallback<ServerInfo>() {

    public void onFailure(Throwable caught) {
      Window.alert("RPC Failed: " + caught);
      exceptionInfo.setText(caught.toString());
    }

    public void onSuccess(ServerInfo result) {
      serverStartedText.setText(result.getServletStartTime().toString());
      serverCurrentText.setText(result.getCurrentTime().toString());
    }
  };

  @Override
  protected void init(GadgetRPC.Preferences preferences) {

    gadgetService = GWT.create(GadgetService.class);
    ServiceDefTarget serviceDef = (ServiceDefTarget) gadgetService;
    String rpcUrl = serviceDef.getServiceEntryPoint();

    // Uses Gadgets container as proxy for GWT RPC requests
    GadgetsGwtRpc.redirectThroughProxy(serviceDef);

    // Build the user interface.
    VerticalPanel vp = new VerticalPanel();
    vp.setWidth("100%");

    vp.add(new Label("RPC to: " + rpcUrl));

    HorizontalPanel startedHp = new HorizontalPanel();
    startedHp.add(new Label("Server Start Time: "));
    startedHp.add(serverStartedText);
    vp.add(startedHp);

    HorizontalPanel currentHp = new HorizontalPanel();
    currentHp.add(new Label("Server Current Time: "));
    currentHp.add(serverCurrentText);
    vp.add(currentHp);

    Button doRPCButton = new Button("Call RPC Method");
    vp.add(doRPCButton);

    vp.add(exceptionInfo);

    RootPanel.get().add(vp);

    // Setup a button listener to invoke the RPC.
    doRPCButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        gadgetService.getServerInfo(rpcCallback);
      }
    });
  }
}
