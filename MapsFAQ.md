#summary Frequently Asked Questions about the GWT bindings for Google Maps

# Maps FAQs #

These FAQs refer to the Google Maps Library for GWT.  See the [Project Overview](Overview.md) for general information on the Google Maps Library for GWT.



## Where can I go for more help using the Maps API for GWT? ##

Besides this User Guide, there are three Google groups you can search and post to:

  * [Google-Web-Toolkit](http://groups.google.com/group/Google-Web-Toolkit): the forum for all users of the Google Web Toolkit.
  * [GWT-Google-Apis](http://groups.google.com/group/gwt-google-apis): a group used by contributors to the GWT bindings for the Maps API.  Use this list for bug reports or discussion about the development of new features.
  * [Google Maps API](http://groups.google.com/group/Google-Maps-API): a group used for users of the Maps JavaScript API.  Search this group to find known issues and solutions with the Maps API in general.  Be sure to read their posting guidelines before writing to the list.  There is lots of traffic to this list and in general they will point GWT users to one of the first 2 lists unless the problem can be reproduced in plain hand-written JavaScript.

## I don't see Google Maps method XXX in the API. What should I do? ##

New features are added to the Google Maps API often. The newest features of the JavaScript Maps API may not yet be present in the latest stable release of the GWT library bindings.

First check the [gwt-google-apis issue tracker](http://code.google.com/p/gwt-google-apis/) to see if the method might have been added to the trunk, but not the stable release yet.  If it has, you can download the subversion source, build it with [Apache Ant](http://ant.apache.org), and get an updated `gwt-maps.jar`.   Submitting a patch along with your issue is encouraged and appreciated!  See [Making GWT Better](http://code.google.com/webtoolkit/makinggwtbetter.html).

## What about the Mapitz and other Maps bindings? ##

The Google API Libraries for Google Web Toolkit is the official set of bindings for Google APIs maintained by Google.  Other developers have made available APIs that give an interface to the Google Maps API or other similar functionality through GWT.  Although Google does not maintain or support these alternate bindings, you are likely to find other users of those libraries on the GWT forum [Google-Web-Toolkit](http://groups.google.com/group/Google-Web-Toolkit).

## How do I get the latest version of the API from Subversion? ##

Building your own gwt-maps.jar from the latest source isn't too hard.  You will
need the following software:

  * The 1.5 or 1.6 version of the [JDK](http://java.sun.com)
  * The [Apache Ant](http://ant.apache.org) utility (version 1.7.1 or greater)
  * A [Subversion](http://subversion.tigris.org) client.

Once you have the above software installed, follow these steps:

  1. Download the code from the subversion repository. If you are using the command line tool, use the command: `svn co http://gwt-google-apis.googlecode.com/svn/trunk trunk`
  1. Download the tools directory from the Google Web Toolkit repository.  If you are using the command line tool, use the command: `svn co http://google-web-toolkit.googlecode.com/svn/tools tools`
  1. Set the environment variable `GWT_TOOLS` to point to the directory where you checked out the tools distribution.
  1. Set the environment variable `GWT_HOME` to point to a binary distribution of GWT downloaded from the [GWT project site](http://code.google.com/webtoolkit).
  1. Run `ant` on the top level file.  The `gwt-maps` library will build along with the other libraries.

The `gwt-maps.jar` file should end up under `maps/build/lib/gwt-maps.jar`.

## Why is my map showing up very small or not at all? ##

By default, the map does not have a CSS width and height set.  You must use `MapWidget.setSize()`, `MapWidget.setWidth()`, `MapWidget.setHeight()` or style with `width` and `height` CSS properties of the map widget.

If you wish to scale to the size of the container, make sure you adjust the size of your outer containing panel or widget and use `setSize()` with a percentage.  The following example will create a map that fills the width of the browser and is 500 pixels tall:

```
   AbsolutePanel panel = new AbsolutePanel();
   panel.setSize("100%","500px");
   MapWidget myMap = new MapWidget();
   map.setSize("100%", "100%");
   panel.add(map);
   RootPanel.add(panel);
```

## Why do I see the map off center surrounded with grey background on one side? ##

As of gwt-maps-1.1, the MapWidget implements the RequiresResize interface.  When added to a panel that supports this interface (such as DockLayoutPanel), the widget automatically calls map.checkResizeAndCenter() whenever the panel containing the widget is resized.  This new feature makes it possible to set your map size to 100% in both directions and fill up the available space on the screen.  To use LayoutPanel subclasses properly, make sure your html page is set to use standards mode by adding `<!doctype html>` as the first line in the document.

In gwt-maps 1.0, it may be necessary to force the map to resize its layout after the map is initially added.  you can use the `MapWidget.checkResizeAndCenter()` method to clean this up.   You may need to delay calling this function using a `DeferredCommand` or other mechanism to wait until after the DOM layout pass that resizes the map is called.

## I'm using localhost, why am I seeing a dialog asking for a key? ##

Passing no key to the Maps API usually works for developing on the local machine, but you may need to create a key for localhost:`<port>` at the [Maps developer web site.](http://code.google.com/apis/maps/signup.html)

If you are not sure which hostname/port combination to use in order to register for a Maps API Key, try printing out the value of `Window.Location.getHref()` in your app.


## How do I use the Google Maps API for GWT with a Maps  API for Business client ID? ##

Maps API Business users can use `AjaxLoaderOptions` to set the client ID.  This will also bypass the need for an API key. For example:

```
  AjaxLoaderOptions options = AjaxLoaderOptions.newInstance();
  options.setOtherParms("client=gme-yourclientid&sensor=false");
  Maps.loadMapsApi("notsupplied", "2", false, options, new Runnable() {
    public void run() {
      buildUi();
    }
  });
```

Please remember to adjust the sensor parameter accordingly.

## Why can't I use Maps API data types in a servlet? ##

The gwt-maps API is a set of wrappers for a JavaScript API that runs in a web browser.  Unfortunately, you cannot use these APIs in server side code.

## How can I use GWT with Maps API 3.0? ##

As of the gwt-maps-1.1 release, the current bindings are only compatible with the Maps v2 APIs.  You can use JSNI methods to interface with the Maps v3 API, or there are  [other projects hosted on Google Code](http://code.google.com/query/#q=gwt%20maps%20v3) that have GWT bindings for the Maps v3 API.

## How can I run JUnit unit tests using the Maps API? ##

Running unit tests for the Maps API sometimes fails because some of the API methods require a proper Maps API key to be setup.

  * One alternative is to create a maps key for the IP address/port of your machine the test is running on and edit GoogleMapsTest.gwt.xml.  But if you are running from eclipse, that port number changes with each run, so this idea kind of stinks.

  * Another workaround is to use 'localhost' for running your tests. Here's how to do that:

> Create a new GWT JUnit Test launch configuration in Eclipse (using the Google Eclipse Plugin) for the class com.google.gwt.maps.MapsTestSuite.

  1. Edit the command line arguments and add the following to the VM args textarea:
```
    -Dgwt.args="-runStyle Manual:1"
```
  1. Next, run the test.  After a few moments, a URL will popup in the console asking you to point your browser to it.
  1. There are 2 IP addresses in the URL (they refer to your local machine's network card).  Substitute '127.0.0.1' for the IP addresses and launch a browser similar to the following:
```
    http://127.0.0.1:55947/com.google.gwt.maps.GoogleMapsTest.JUnit/junit.html?gwt.junit.sessionId=0&gwt.codesvr=127.0.0.1:55945&gwt.junit.blockindex=134
```

## I have problems running my application from file ##

For modern browsers it's common to forbid cross-file references, which most likely is the cause of your problems. You can run `ant devmode` command to start development mode, and paste the starup URL into your browser without `?gwt.codesvr=127.0.0.1:9997` suffix (port may vary). It will make your embedded web server serve your files in a production mode.

## Why does my app throw an exception in Map.addOverlay() when adding a Marker to the map with a custom Icon? ##

The JavaScript API isn't very clear about this, but when using a custom icon you need to be careful to set the icon size and anchor point.  Also, when using a custom icon that isn't shaped like the default marker, the shadow may look funny, so its best to plan on replacing that as well.

```
    Icon icon = Icon.newInstance("http://www.example.com/foo.png");
    icon.setShadowURL("http://www.example.com/foo-shadow.png");
    icon.setIconSize(Size.newInstance(12, 20));
    icon.setShadowSize(Size.newInstance(22, 20));
    icon.setIconAnchor(Point.newInstance(6, 20));
    icon.setInfoWindowAnchor(Point.newInstance(5, 1));
    map.addOverlay(new Marker(LatLng.newInstance(lat,lng), options));
```

Also see the [source code](http://code.google.com/p/gwt-google-apis/source/browse/trunk/maps/samples/hellomaps/src/com/google/gwt/maps/sample/hellomaps/client/IconDemo.java) from the HelloMaps demo.

## Can I load the Map over `https` using `AjaxLoader` with `base_domain` parameter set? ##
It is not possible to load the Maps over SSL using the `AjaxLoader` with `base_domain` parameter set. This will, however, not affect the application performance because API requests are always handled by the optimal Maps server.

The only purpose of `base_domain` parameter is to bias geocoding results. If you need that functionality, you can force the same behaviour in your application by using `com.google.gwt.maps.client.geocode.Geocoder` class with
`baseCountryCode` set.