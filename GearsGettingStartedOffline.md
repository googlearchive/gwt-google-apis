# Using the Gears Offline Module in a GWT project #

## Creating a new GWT project ##

Start by creating a new GWT project called GearsOfflineTutorial as described in the [GWT Plugin Guide](http://code.google.com/eclipse/docs/creating_new_webapp.html).

Since we are working with an additional library, add the library `gwt-gears.jar` to the Java classpath.  Then, add the module `com.google.gwt.gears.Offline` to your module.

```
    <inherits name='com.google.gwt.gears.Offline' />
```

## Update the HTML host file ##

Replace the body of the HTML host file `com/example/google/gwt/gearsofflinetutorial/public/Gearsofflinetutorial.html` with a `<div>` tag that we can use for the GWT application.

```
<body>

    <h1>Gears Offline Tutorial</h1>

    <div id="gearsTutorial">

</body>
```

## Use a Offline object in the  .java source ##

The [Offline](http://gwt-google-apis.googlecode.com/svn/javadoc/gears/1.3/com/google/gwt/gears/offline/client/Offline.html) class provides a convenient way to generate a Gears manifest file.  It works by using a custom GWT linker to track all the generated JavaScript files as well as resources on the public path.

To complete the `com/example/google/gwt/gearsofflinetutorial/client/GearsOfflineTutorial.java`, you can replace the `onModuleLoad()` with the following methods that create a [ManagedResourceStore](http://gwt-google-apis.googlecode.com/svn/javadoc/gears/1.3/com/google/gwt/gears/client/localserver/ManagedResourceStore.html) using the Offline object.

```
package com.example.google.gwt.gearsdatabasetutorial.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.gears.client.Factory;
import com.google.gwt.gears.client.GearsException;
import com.google.gwt.gears.client.localserver.LocalServer;
import com.google.gwt.gears.client.localserver.ManagedResourceStore;
import com.google.gwt.gears.offline.client.Offline;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Tutorial for using Google Gears Offline support
 */
public class GearsOfflineTutorial implements EntryPoint {

  private final Button createManagedResourceStoreButton = new Button(
      "Go Offline");

  private final Button removeManagedResourceStoreButton = new Button(
      "Remove Offline Store");

  private final Label statusLabel = new Label();

  public void onModuleLoad() {
    
    VerticalPanel vpanel = new VerticalPanel();
    vpanel.setSpacing(5);
    vpanel.getElement().getStyle().setPropertyPx("margin", 10);
    RootPanel.get().add(vpanel);
    HorizontalPanel hpanel = new HorizontalPanel();
    vpanel.add(hpanel);
    vpanel.add(statusLabel);

    // Draw a different interface if the application can be served offline.
    try {
      LocalServer server = Factory.getInstance().createLocalServer();
      hpanel.add(createManagedResourceStoreButton);
      // This check to see if the host page can be served locally
      if (server.canServeLocally(Window.Location.getPath())) {
        createManagedResourceStoreButton.setText("Refresh Manifest");
        // Include a resource the needs to be added to the manifest
        HTML iframe = new HTML("<iframe src=\"iframe.html\"></iframe>");
        vpanel.add(iframe);
        // Give the user an opportunity to delete the managed resource store
        hpanel.add(removeManagedResourceStoreButton);
      }
    } catch (GearsException ex) {
      Window.alert("Exception: " + ex);
      return;
    }

    createManagedResourceStoreButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        statusLabel.setText("Starting update");
        createManagedResourceStore();
      }
    });

    removeManagedResourceStoreButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        try {
          LocalServer server = Factory.getInstance().createLocalServer();
          ManagedResourceStore store = Offline.getManagedResourceStore();
          server.removeManagedStore(store.getName());
          statusLabel.setText("Removed ManagedResourceStore. Please refresh the page to see the changes.");
        } catch (GearsException e) {
          statusLabel.setText(e.getMessage());
        }
      }
    });
  }

  private void createManagedResourceStore() {
    try {
      final ManagedResourceStore managedResourceStore = Offline.getManagedResourceStore();

      new Timer() {
        final String oldVersion = managedResourceStore.getCurrentVersion();
        String transferringData = "Transferring data";

        @Override
        public void run() {
          switch (managedResourceStore.getUpdateStatus()) {
            case ManagedResourceStore.UPDATE_OK:
              if (managedResourceStore.getCurrentVersion().equals(oldVersion)) {
                statusLabel.setText("No update was available.");
              } else {
                statusLabel.setText("Update to "
                    + managedResourceStore.getCurrentVersion()
                    + " was completed.  Please refresh the page to see the changes.");
              }
              break;
            case ManagedResourceStore.UPDATE_CHECKING:
            case ManagedResourceStore.UPDATE_DOWNLOADING:
              transferringData += ".";
              statusLabel.setText(transferringData);
              schedule(500);
              break;
            case ManagedResourceStore.UPDATE_FAILED:
              statusLabel.setText(managedResourceStore.getLastErrorMessage());
              break;
          }
        }
      }.schedule(500);

    } catch (GearsException e) {
      statusLabel.setText("");
      Window.alert(e.getMessage());
    }
  }
}
```

The other part of this demo is a separate `HTML` file that is stored in `com/example/google/gwt/gearsofflinetutorial/public/iframe.html`

```
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 </head>
 <body>
  <h1>Offline Data</h1>
  This file is being served from the offline store.
 </body>
</html>
```

This demo first displays a button to create a ManagedResourceStore using the Offline class.  Once the button is presses, the update process begins.  When all files are downloaded and available in offline mode, you'll be prompted to refresh the application (using the browser's refresh button.)  You can also disconnect the network to show that the operations are occurring locally.

> ![http://gwt-google-apis.googlecode.com/svn/wiki/GearsOfflineGettingStarted1.png](http://gwt-google-apis.googlecode.com/svn/wiki/GearsOfflineGettingStarted1.png)

After the resources are loaded into the ManagedResourceStore, an HTML iframe is loaded from the public path and added to the page.   Also, the buttons change to allow you to attempt to update or to remove the store.

> ![http://gwt-google-apis.googlecode.com/svn/wiki/GearsOfflineGettingStarted2.png](http://gwt-google-apis.googlecode.com/svn/wiki/GearsOfflineGettingStarted2.png)

## How to deal with browsers that do not support Gears ##

The Gears plugin is a component that must be installed by users and is not supported on all browser configurations.  Thus, you will want to let users know when your application detects that the Gears plugin is not installed or not supported.  You can create a rebind rule in your GWT module that will invoke a different EntryPoint in this instance.

### Add a new EntryPoint class ###

The following is an example of an alternative EntryPoint class is used when the Gears plugin is not detected in the browser.  Save this class as `NoGears.java` in the same package with the `GearsOfflineTutorial` class.

```
package com.example.google.gwt.gearsdatabasetutorial.client;

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

### Modify GearsOfflineTutorial.gwt.xml ###

In order to tell the compiler to add logic to the selection script to pull up your rebound entry point, you need to create a [replace-class-with](http://code.google.com/webtoolkit/doc/latest/DevGuideCodingBasicsDeferred.html#replacement) rule in your `gwt.xml` module file.

```
  <!-- Rebind the entry point if Gears is not installed -->
  <replace-with class="com.example.google.gwt.gearsdatabasetutorial.client.NoGears">
    <when-type-is class="com.example.google.gwt.gearsdatabasetutorial.client.GearsOfflineTutorial"/>
    <when-property-is name="gears.installed" value="false"/>
  </replace-with>
```

## Run the GearsOfflineTutorial sample project ##

Now, you should be able to execute your sample project in development mode by either running _ant devmode_ from the command line or using the _Run_ configuration from Eclipse.

> _Note: Using GWT devmode to debug a Gears project on Windows will not work with Chrome, please use another browser_

> _Note: The Offline support from Gears will not work with a URL that begins with `file:///`.  Upload your project to a web server to test offline support._