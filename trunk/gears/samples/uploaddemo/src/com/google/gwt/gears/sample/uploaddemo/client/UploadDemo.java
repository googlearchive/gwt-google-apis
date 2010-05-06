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
package com.google.gwt.gears.sample.uploaddemo.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.gears.client.Factory;
import com.google.gwt.gears.client.blob.Blob;
import com.google.gwt.gears.client.desktop.Desktop;
import com.google.gwt.gears.client.desktop.File;
import com.google.gwt.gears.client.desktop.OpenFilesHandler;
import com.google.gwt.gears.client.httprequest.HttpRequest;
import com.google.gwt.gears.client.httprequest.ProgressEvent;
import com.google.gwt.gears.client.httprequest.ProgressHandler;
import com.google.gwt.gears.client.httprequest.RequestCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Roughly replicates a FORM upload using Gears.
 * <p>
 * When the user clicks the Browse button, the Desktop.openFiles method is
 * invoked allowing the user to select a file. The selection is saved as a Blob
 * reference and upload as the body of the POST request when the Upload button
 * is clicked.
 */
public class UploadDemo implements EntryPoint {
  final Factory factory = Factory.getInstance();
  RootPanel root;
  Blob selectedFile;

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    root = RootPanel.get();

    final Button browse = new Button("Browse");

    final Button upload = new Button("Upload");
    upload.setEnabled(false);

    final TextBox selected = new TextBox();
    selected.setEnabled(false);

    final SimplePanel progressInner = new SimplePanel();
    progressInner.addStyleName("progressInner");

    final SimplePanel progressGauge = new SimplePanel();
    progressGauge.addStyleName("progressGauge");
    progressGauge.add(progressInner);

    final HTML result = new HTML();

    browse.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        Desktop desktop = factory.createDesktop();
        desktop.openFiles(new OpenFilesHandler() {

          public void onOpenFiles(OpenFilesEvent event) {
            File[] files = event.getFiles();
            selected.setText(files[0].getName());
            selectedFile = files[0].getBlob();
            upload.setEnabled(true);
          }
        }, true);
      }
    });

    upload.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        HttpRequest request = factory.createHttpRequest();
        request.open("POST", GWT.getModuleBaseURL() + "upload");
        request.setRequestHeader("X-Filename", selected.getText());

        request.setCallback(new RequestCallback() {
          public void onResponseReceived(HttpRequest request) {
            String msg = request.getStatus() + " " + request.getResponseText();
            if (request.getStatus() != 200) {
              result.setHTML("<p style=\"color:red\">" + msg + "</p>");
            } else {
              result.setHTML("<p style=\"color:green\">" + msg + "</p>");
            }
          }
        });

        request.getUpload().setProgressHandler(new ProgressHandler() {
          public void onProgress(ProgressEvent event) {
            double pcnt = ((double) event.getLoaded() / event.getTotal());
            progressInner.setWidth((int) Math.floor(pcnt * 100) + "%");
          }
        });
        request.send(selectedFile);
      }
    });

    HorizontalPanel inputPanel = new HorizontalPanel();
    inputPanel.add(selected);
    inputPanel.add(browse);
    inputPanel.add(upload);

    root.add(inputPanel);
    root.add(new HTML("Progress:"));
    root.add(progressGauge);
    root.add(result);
  }
}
