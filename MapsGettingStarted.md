# Getting Started with Google Maps #

The Google Maps API provides a convenient JavaScript API which allows you to add mapping functionality to your application. The [Google Maps library for GWT](http://code.google.com/p/gwt-google-apis) allows you to access this JavaScript API from Java code compiled with the GWT compiler.  At the present time, the gwt-maps API only supports Google Maps API version 2.

This guide describes the basics of getting the Google Maps Library working in a GWT project. For more information on the functionality provided by the API itself, see the [Google Maps API](http://code.google.com/apis/maps/) developer's guide.

## Assumptions ##

  * You are using GWT 2.0.3 or higher.
  * You are already familiar with [Google Web Toolkit](http://code.google.com/webtoolkit/overview.html)
  * You know how to [create a new GWT project.](http://code.google.com/eclipse/docs/creating_new_webapp.html)

## Downloading the Google Maps Library for GWT ##

You can download the latest release of the library from the [project download page](http://code.google.com/p/gwt-google-apis/downloads/). After you download the distribution, uncompress it.  Inside the folder you will find a .jar file named `gwt-maps.jar`.

You can either reference the `.jar` file from the folder that you uncompressed the project in, or copy it to another location, such as the location of your GWT distribution (containing `gwt-dev.jar` and `gwt-user.jar` files).  In this example, we've chosen to copy the file to the path `/usr/local/gwt-maps`.  If you are using Windows, you might choose to copy these files to a path like `C:\gwt\gwt-maps`

## Creating a new GWT Project ##

Start by creating a new GWT project called SimpleMaps as described in the [Google Plugin for Eclipse user's guide.](http://code.google.com/eclipse/docs/creating_new_webapp.html)

Since we are working with an additional library, add `gwt-maps.jar` to the Java classpath.  Then, add the inherits line for `com.google.gwt.maps.GoogleMaps` to your module.

```
$PP_OFF
    <inherits name='com.google.gwt.maps.GoogleMaps' />
```

Note that in order to use the Maps API, you need to apply for a Google Maps API key.  Running with no key specified will work with `localhost` for development purposes, but you will need to apply for your own key to deploy to a website.


## Update the HTML host file ##

Remove all contents from the body of the HTML host file `src/com/example/google/gwt/mapstutorial/public/SimpleMaps.html`. This demo will use the RootLayoutPanel to create a dynamic application.

```
<body>
</body>
```

Make sure the HTML DOCTYPE declaration is set at the top of the file to `<!doctype html>` to put the browser rendering engine in standards mode.  This is required for the GWT LayoutPanel to work correctly on all browsers.

## Maps API key ##

Note that in order to use the Maps API version 2, you need to apply for a <a href='http://www.google.com/apis/maps/signup.html'>Google Maps API key</a>. Running with no key specified will work with localhost for development purposes, but you will need to apply for your own key to deploy to a website.  The key can be specified either as a script tag query string, or as a parameter to the Maps.loadMapsApi() method as shown below.

## Add a map object to .java source ##

To complete the `src/com/example/google/gwt/mapstutorial/client/SimpleMaps.java` file, remove the contents of the SimpleMaps class, add some imports, and replace the body of the class as follows:

```
package com.example.google.gwt.mapstutorial.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.Maps;
import com.google.gwt.maps.client.control.LargeMapControl;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/*
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SimpleMaps implements EntryPoint {

  // GWT module entry point method.
  public void onModuleLoad() {
   /*
    * Asynchronously loads the Maps API.
    *
    * The first parameter should be a valid Maps API Key to deploy this
    * application on a public server, but a blank key will work for an
    * application served from localhost.
   */
   Maps.loadMapsApi("", "2", false, new Runnable() {
      public void run() {
        buildUi();
      }
    });
  }

  private void buildUi() {
    // Open a map centered on Cawker City, KS USA
    LatLng cawkerCity = LatLng.newInstance(39.509, -98.434);

    final MapWidget map = new MapWidget(cawkerCity, 2);
    map.setSize("100%", "100%");
    // Add some controls for the zoom level
    map.addControl(new LargeMapControl());

    // Add a marker
    map.addOverlay(new Marker(cawkerCity));

    // Add an info window to highlight a point of interest
    map.getInfoWindow().open(map.getCenter(),
        new InfoWindowContent("World's Largest Ball of Sisal Twine"));

    final DockLayoutPanel dock = new DockLayoutPanel(Unit.PX);
    dock.addNorth(map, 500);

    // Add the map to the HTML host page
    RootLayoutPanel.get().add(dock);
  }
}
```


## Run the SimpleMaps sample project ##

Now save your project and run it in development mode.

Congratulations! You should now have Google Maps API working within a GWT application.

> ![http://gwt-google-apis.googlecode.com/svn/wiki/GettingStartedBasic5.png](http://gwt-google-apis.googlecode.com/svn/wiki/GettingStartedBasic5.png)