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
package com.google.gwt.gears.sample.workerpooldemo.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.gears.client.Factory;
import com.google.gwt.gears.client.workerpool.WorkerPool;
import com.google.gwt.gears.client.workerpool.WorkerPoolMessageHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Sample application demonstrating how to use the {@link WorkerPool} class.
 */
public class WorkerPoolDemo implements EntryPoint, WorkerPoolMessageHandler,
    PiSpigot.Callback {
  private static final String INTERACTION_DESC = "<p>Notice how the browser "
      + "freezes during the synchronous calculation and some browsers alert with "
      + "a slow script warning.  During the WorkerPool calculation you can "
      + "interact with the animation and see the results updated interactively.</p>"
      + "<p><i>Note: In hosted mode, this demo works only on Windows</i></p>";

  Button startStopButton = new Button("Start");
  private RadioButton asyncCalc = new RadioButton("calcGroup",
      "Asynchronous (WorkerPool) code");
  private boolean calculationInProgress = false;
  private int currentRow = 0;
  private SimplePanel htmlGoesHere = new SimplePanel();
  private HTML htmlResults = new HTML();
  private ListBox numDigitsListBox = new ListBox();
  private int primeWorkerId;
  private long startTime;
  private Label statusLabel = new Label();
  private RadioButton syncCalc = new RadioButton("calcGroup",
      "Synchronous code");
  private VerticalPanel vp = new VerticalPanel();
  private WorkerPool workerPool = null;

  /**
   * Callback from PiSpigot class.
   */
  public void onDigits(String digits) {
    htmlResults.setHTML(htmlResults.getHTML() + "<code>" + digits
        + "</code><br>");
  }

  /**
   * Callback from WorkerPool class.
   */
  public void onMessageReceived(MessageEvent event) {
    assert (event.getSender() == primeWorkerId);
    if (event.getBody().startsWith("DIGITS")) {
      htmlResults.setHTML(htmlResults.getHTML() + "<code>"
          + event.getBody().substring(6) + "</code><br>");
    } else if (event.getBody().startsWith("MSG")) {
      htmlResults.setHTML(htmlResults.getHTML() + event.getBody().substring(4)
          + "<br>");
    } else if (event.getBody().equals("END")) {
      this.stopCalculation();
    } else {
      htmlResults.setHTML(htmlResults.getHTML()
          + "<font color=\"red\"><i>Unknown message " + event.getBody()
          + "</i></font>");
    }
  }

  public void onModuleLoad() {

    vp.setSpacing(10);
    vp.add(buildControlPanel());
    vp.add(statusLabel);

    HorizontalPanel digitPanel = new HorizontalPanel();
    digitPanel.add(new HTML("<h1>3.</h1>"));
    digitPanel.add(htmlGoesHere);
    htmlResults.setHTML("<h1><i>???</i></h1>");
    htmlGoesHere.add(htmlResults);
    vp.add(digitPanel);
    RootPanel.get().add(vp);
  }

  private Widget buildControlPanel() {
    VerticalPanel outerPanel = new VerticalPanel();
    DecoratorPanel tableWrapper = new DecoratorPanel();
    FlexTable resultTable = new FlexTable();

    numDigitsListBox.addItem("1,000", "1000");
    numDigitsListBox.addItem("5,000", "5000");
    numDigitsListBox.addItem("10,000", "10000");
    numDigitsListBox.addItem("20,000", "20000");
    numDigitsListBox.addItem("100,000", "100000");
    buildControlPanelRow(resultTable, "Number of Digits to compute: ",
        numDigitsListBox);

    buildControlPanelRow(resultTable, "Execute calculation using:", syncCalc);
    syncCalc.setValue(true);
    buildControlPanelRow(resultTable, "", asyncCalc);

    startStopButton.addClickHandler(new ClickHandler() {

      public void onClick(ClickEvent event) {
        if (calculationInProgress) {
          statusLabel.setText(statusLabel.getText() + "...Cancelled");
          stopCalculation();
          return;
        }
        htmlResults.setText("");
        statusLabel.setText("Starting calculation");
        calculationInProgress = true;
        startStopButton.setEnabled(false);
        startStopButton.setText("Working...");

        startTime = System.currentTimeMillis();
        final int numDigits = Integer.valueOf(numDigitsListBox.getValue(numDigitsListBox.getSelectedIndex()));
        if (syncCalc.getValue()) {
          /*
           * A DeferredCommand is used here so that the previous updates to the
           * user interface will appear. The synchronous computation will block
           * until the calculation is complete, freezing the user interface.
           */
          DeferredCommand.addCommand(new Command() {
            public void execute() {
              doSyncCalculation(numDigits);
            }
          });
        } else {
          doAsyncCalculation(numDigits);
        }
      }
    });

    buildControlPanelRow(resultTable, "", startStopButton);
    tableWrapper.setWidget(resultTable);

    // Position the Animation so that it looks centered.
    Widget toy = new AnimationToy();
    AbsolutePanel toyWrapper = new AbsolutePanel();
    toyWrapper.setSize("450px", "210px");
    toyWrapper.add(toy, 70, 0);
    outerPanel.add(toyWrapper);

    HTML desc = new HTML(INTERACTION_DESC);
    desc.setWidth("450px");
    outerPanel.add(desc);
    outerPanel.add(tableWrapper);
    return outerPanel;
  }

  /**
   * Utility routine to construct one row of the user interface control panel.
   *
   * @param table the table to add to.
   * @param text HTML text to add in the first column
   * @param w a widget in the control panel to add in the second column.
   */
  private void buildControlPanelRow(FlexTable table, String text, Widget w) {
    table.insertRow(currentRow);
    table.insertCell(currentRow, 0);
    table.setWidget(currentRow, 0, new HTML(text));
    table.insertCell(currentRow, 1);
    table.setWidget(currentRow, 1, w);
    currentRow++;
  }

  /**
   * Creates a worker if necessary, and sends it a message to start computing Pi
   * to the number of digits requested. The method returns immediately after
   * sending the message. Meanwhile, the computation starts to run in the worker
   * when it receives the message.
   *
   * @param numDigits the number of digits of Pi to compute.
   */
  private void doAsyncCalculation(int numDigits) {

    if (workerPool == null) {
      workerPool = Factory.getInstance().createWorkerPool();
      primeWorkerId = workerPool.createWorkerFromUrl("pi_spigot_worker.js");
      workerPool.setMessageHandler(this);
    }
    workerPool.sendMessage("START " + numDigits, primeWorkerId);
  }

  /**
   * Computes the digits of Pi synchronously. The method will return after the
   * computation is complete.
   *
   * @param numDigits the number of digits of Pi to compute.
   */
  private void doSyncCalculation(int numDigits) {
    PiSpigot s = new PiSpigot(numDigits, 100, this);
    s.compute();
    stopCalculation();
  }

  /**
   * Cleanup the state of the app after a computation finishes to report status
   * and prepare for the next computation.
   */
  private void stopCalculation() {
    startStopButton.setEnabled(true);
    startStopButton.setText("Start");
    calculationInProgress = false;
    long elapsed = (System.currentTimeMillis() - startTime) / 1000;
    String secondString = "seconds";
    if (elapsed == 1) {
      secondString = "second";
    }
    statusLabel.setText(elapsed + " " + secondString + " elapsed");
  }
}
