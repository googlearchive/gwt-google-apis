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
package com.google.gwt.gadgets.sample.gadgetrpc.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.gadgets.client.Gadget;
import com.google.gwt.gadgets.client.IntrinsicFeature;
import com.google.gwt.gadgets.client.NeedsIntrinsics;
import com.google.gwt.gadgets.client.Gadget.ModulePrefs;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * A demonstration of how to use GWT RPC with the gwt-gadgets library.
 */
@ModulePrefs(title = "Gadget RPC Demo", author = "Eric Z", author_email = "zundel+gadgets@google.com")
public class GadgetRPC extends Gadget<GadgetRPCPreferences> implements NeedsIntrinsics {

  private IntrinsicFeature intrinsicMethods = null;
  private GadgetRPCPreferences prefs = null;
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
  
  public void initializeFeature(IntrinsicFeature feature) {
    this.intrinsicMethods = feature;
  }

  @Override
  protected void init(GadgetRPCPreferences preferences) {
    this.prefs = preferences;
    
    gadgetService = GWT.create(GadgetService.class);
    // If the gadget has caching turned on, change the service entry point.  This works
    // around the Single Origin Policy(SOP) when the gadget is hosted inside the gadget spec.
    ServiceDefTarget serviceDef = (ServiceDefTarget) gadgetService;
    String rpcUrl = serviceDef.getServiceEntryPoint();
    if (prefs.useCachedXHR().getValue()) {
      rpcUrl = intrinsicMethods.getCachedUrl(rpcUrl);
      serviceDef.setServiceEntryPoint(rpcUrl);
    }
    
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
    doRPCButton.addClickListener(new ClickListener() {

      public void onClick(Widget sender) {
        gadgetService.getServerInfo(rpcCallback);
      }    
    });  
  }
}
