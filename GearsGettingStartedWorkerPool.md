# Using the Gears WorkerPool Module in a GWT project #

## Creating a new GWT project ##

Start by creating a new GWT project called GearsWorkerPoolTutorial as described in the[GWT Plugin Guide](http://code.google.com/eclipse/docs/creating_new_webapp.html).

Since we are working with an additional library, add the library `gwt-gears.jar` to the Java classpath.  Then, add the module `com.google.gwt.gears.Gears` to your module.

```
    <inherits name='com.google.gwt.gears.Gears' />
```

## Update the HTML host file ##

Replace the body of the HTML host file `src/com/example/google/gwt/gearsworkerpooltutorial/public/GearsWorkerPoolTutorial.html` with a `<div>` tag that we can use for the GWT application.

```
<body>

    <h1>GearsWorkerPoolTutorial</h1>

    <div id="gearsTutorial">

</body>
```

## Use a WorkerPool object in the  .java source ##

Replace the body of the of the sample .java file `src/com/example/google/gwt/gearsworkerpooltutorial/client/GearsWorkerPoolTutorial.java` with the following:

```
package com.example.google.gwt.gearsworkerpooltutorial.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.gears.client.Factory;
import com.google.gwt.gears.client.workerpool.WorkerPool;
import com.google.gwt.gears.client.workerpool.WorkerPoolMessageHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Demonstrates how to use a WorkerPool worker and the impact
 * of using an asynchronous vs. synchronous call to do long running
 * work on the user interface.
 */
public class GearsWorkerPoolTutorial implements EntryPoint,
    WorkerPoolMessageHandler {
  private Button syncButton = new Button("Synchronous");
  private Button workerPoolButton = new Button("Asynchronous");
  private Button interactButton = new Button("Interact");
  private int nextRow = 0;
  private WorkerPool wp;
  private int workerId;

  public void onModuleLoad() {
    RootPanel.get().getElement().getStyle().setPropertyPx("margin", 15);
    Grid grid = new Grid(3, 3);
    addRow(grid, "Interact with UI:", interactButton, "");
    addRow(
        grid,
        "Run Computation:",
        syncButton,
        "<i>Note the synchronous computation blocks UI interaction and may even "
            + "cause an 'unresponsive script' warning on some browsers.</i>");
    addRow(grid, "", workerPoolButton,
        "<i>But you can still interact while the asynchronous "
            + "computation runs in a JavaScript worker</i>");
    RootPanel.get().add(grid);
    RootPanel.get().add(new HTML("<h2>Results</h2>"));

    // The background color toggles when this button is placed.
    interactButton.addClickHandler(new ClickHandler() {
      private boolean darkBackground = false;

      public void onClick(ClickEvent event) {
        String color = "white";
        if (!darkBackground) {
          color = "grey";
        }
        RootPanel.get().getElement().getStyle().setProperty("backgroundColor",
            color);
        darkBackground = !darkBackground;
      }
    });

    // Perform some simulated work synchronously
    syncButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        logMessage("Starting Synchronous...");
        syncWorker("Synchronous");
      }
    });

    /*
     * Perform some simulated work asynchronously.
     * This will load the code for the worker from a file named 'worker.js'
     * in the ../public directory.
     */
    workerPoolButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        if (wp == null) {
          wp = Factory.getInstance().createWorkerPool();
          wp.setMessageHandler(GearsWorkerPoolTutorial.this);
          workerId = wp.createWorkerFromUrl("worker.js");
        }
        logMessage("Starting Asynchronous...");
        // The worker will start calculating when it receives this message.
        wp.sendMessage("Asynchronous", workerId);
      }
    });
  }

  private void addRow(Grid grid, String labelString, Button button,
      String descriptionString) {
    grid.setWidget(nextRow, 0, new HTML(labelString));
    grid.setWidget(nextRow, 1, button);
    grid.setWidget(nextRow, 2, new HTML(descriptionString));
    nextRow++;
  }

  /**
   * Invoked when a message is recieved from a WorkerPool worker.
   */
  public void onMessageReceived(MessageEvent event) {
    logMessage(event.getBody());
  }

  /**
   * Append a new message onto the browser.
   * @param message String to write into the browser.
   */
  private void logMessage(String message) {
    RootPanel.get().add(new Label(message));
  }

  /**
   * Simulate work in the main browser.
   * @param message input message for the worker.
   */
  public void syncWorker(String message) {
    // Busy wait for 5 seconds
    long end = System.currentTimeMillis() + 5000;
    while (true) {
      int busyCounter = 1000;
      String busyVar;
      while (busyCounter-- > 0) {
        busyVar = "garbage" + busyCounter;
      }
      long now = System.currentTimeMillis();
      if (now > end) {
        break;
      }
    }
    logMessage("Approved: " + message);
  }
}
```

## A simple JavaScript worker ##

Save the following file as `src/com/example/google/gwt/gearsworkerpooltutorial/public/worker.js`.  This worker just echoes the message back to the sender with a message... after waiting for a few seconds.

```
// worker.js A simple Gears WorkerPool demo
google.gears.workerPool.onmessage = function(a, b, message) {
  google.gears.workerPool.sendMessage(doWork(message.body), message.sender);
};

function doWork(message) {
  // Busy wait for 5 seconds
  var start = new Date();
  while (true) {
    var busyCounter = 10000;
    var busyVar;
    while (busyCounter-- > 0) {
       busyVar = "garbage" + busyCounter;
    }
    var now = new Date();
    if (now.getTime() - start.getTime() > 5000) {
	break;
    }
  }

  return "Approved: " + message;
};

```

## How to deal with browsers that do not support Gears ##

The Gears plugin is a component that must be installed by users and is not supported on all browser configurations.  Thus, you will want to let users know when your application detects that the Gears plugin is not installed or not supported.  You can create a rebind rule in your GWT module that will invoke a different EntryPoint in this instance.

### Add a new EntryPoint class ###

The following is an example of an alternative EntryPoint class is used when the Gears plugin is not detected in the browser.  Save this class as `NoGears.java` in the same package with the `GearsWorkerPoolTutorial` class.

```
package com.example.google.gwt.gearsworkerpooltutorial.client;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * This entry point will be used when no Gears plugin is detected
 */
public class NoGears {
  public void onModuleLoad() {
    RootPanel rootPanel = RootPanel.get();
    rootPanel.add(new HTML(
        "<font color=\"red\">ERROR: This browser does not support Gears. "
        + " Please <a href=\"http://gears.google.com/\">install Gears</a> " 
        + "and reload the application.  Note that GWT Gears applications can "
        + "only be debugged in development mode on Windows.</font>"));
  }
}
```

### Modify GearsWorkerPoolTutorial.gwt.xml ###

In order to tell the compiler to add logic to the selection script to pull up your rebound entry point, you need to create a [replace-class-with](http://code.google.com/webtoolkit/doc/latest/DevGuideCodingBasicsDeferred.html#replacement) rule in your `gwt.xml` module file.

```
  <!-- Rebind the entry point if Gears is not installed -->
  <replace-with class="com.example.google.gwt.gearsworkerpooltutorial.client.NoGears">
    <when-type-is class="com.example.google.gwt.gearsworkerpooltutorial.client.GearsWorkerPoolTutorial"/>
    <when-property-is name="gears.installed" value="false"/>
  </replace-with>
```

## Run the GearsWorkerPoolTutorial sample project ##

Now, you should be able to execute your sample project in development mode by either running _GearsWorkerPoolTutorial-shell_ from the command line or using the _Run_ configuration from Eclipse.

> _Note: Using devmode mode with the Gears project on Windows will not work with Chrome, please use other browser_

And here is what the tutorial application looks like when run:

> ![http://gwt-google-apis.googlecode.com/svn/wiki/GearsWorkerPoolGettingStarted1.png](http://gwt-google-apis.googlecode.com/svn/wiki/GearsWorkerPoolGettingStarted1.png)
