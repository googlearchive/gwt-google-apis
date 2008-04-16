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
package com.google.gwt.gadgets.sample.hellogadgets.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.gadgets.client.Gadget;
import com.google.gwt.gadgets.client.IntrinsicFeature;
import com.google.gwt.gadgets.client.NeedsIntrinsics;
import com.google.gwt.gadgets.client.NeedsSetPrefs;
import com.google.gwt.gadgets.client.NeedsSetTitle;
import com.google.gwt.gadgets.client.SetPrefsFeature;
import com.google.gwt.gadgets.client.SetTitleFeature;
import com.google.gwt.gadgets.client.Gadget.ModulePrefs;
import com.google.gwt.gadgets.sample.hellogadgets.client.HelloPreferences.ButtonPosition;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * HelloWorld Gadget.
 */
// NB: The __UP_title__ will cause the value of the title preference to be
// substituted automatically
@ModulePrefs(title = "__UP_title__", author = "BobV", author_email = "gwt.team.bobv+gadgets@gmail.com")
public class HelloGadgets extends Gadget<HelloPreferences> implements NeedsIntrinsics,
    NeedsSetPrefs, NeedsSetTitle {
  IntrinsicFeature intrinsics;
  SetPrefsFeature setPrefs;
  SetTitleFeature setTitle;

  public void initializeFeature(IntrinsicFeature feature) {
    intrinsics = feature;
  }

  public void initializeFeature(SetPrefsFeature feature) {
    this.setPrefs = feature;
  }

  public void initializeFeature(SetTitleFeature feature) {
    this.setTitle = feature;
  }

  public void tryXHR(RequestBuilder.Method method, boolean useCached) {
    String url = GWT.getModuleBaseURL() + "hello.css";
    if (useCached) {
      url = intrinsics.getCachedUrl(url);
    }
    RequestBuilder rb = new RequestBuilder(method, url);
    try {
      rb.sendRequest("This is content", new RequestCallback() {
        public void onError(Request request, Throwable exception) {
          exception.printStackTrace();
          Window.alert("onError: " + exception.getMessage());
        }

        public void onResponseReceived(Request request, Response response) {
          Window.alert("Success! " + response.getText());
        }
      });
    } catch (RequestException e) {
      e.printStackTrace();
      Window.alert("new RequestBuilder failed: " + e.getMessage());
    }
  }

  protected void init(final HelloPreferences prefs) {
    Button b = new Button("Set my title", new ClickListener() {
      public void onClick(Widget sender) {
        String title = Window.prompt("What is the new title?",
            prefs.promptSomethingElse().getValue());
        if (title != null) {
          setTitle.setTitle(title);

          /*
           * Save the title for next time. Because the module's title is defined
           * in terms of the user preference, the value will be displayed
           * whenever the module starts.
           */
          setPrefs.set(prefs.title(), title);
        }
      }
    });
    b.setWidth("100%");

    ButtonPosition buttonPosition = prefs.buttonPosition().getValue();
    b.addStyleName(buttonPosition.getAlign());

    RootPanel.get().add(b);
    if (prefs.showMessage().getValue()) {
      RootPanel.get().add(new Label("Try changing the module's settings"));
    }

    Button tryXHRGet = new Button("Try GET", new ClickListener() {
      public void onClick(Widget sender) {
        tryXHR(RequestBuilder.GET, false);
      }
    });
    RootPanel.get().add(tryXHRGet);

    Button tryXHRPost = new Button("Try POST", new ClickListener() {
      public void onClick(Widget sender) {
        tryXHR(RequestBuilder.POST, false);
      }
    });
    RootPanel.get().add(tryXHRPost);

    Button tryXHRGetCached = new Button("Try cached GET", new ClickListener() {
      public void onClick(Widget sender) {
        tryXHR(RequestBuilder.GET, true);
      }
    });
    RootPanel.get().add(tryXHRGetCached);

    Button tryXHRPostCached = new Button("Try cached POST",
        new ClickListener() {
          public void onClick(Widget sender) {
            tryXHR(RequestBuilder.POST, true);
          }
        });
    RootPanel.get().add(tryXHRPostCached);
  }
}
