#summary GWT Chart Tools questions that are asked frequently.  Very frequently.

# Chart Tools (aka Visualization API) FAQs #



## Where can I go to get help with using the Chart Tools APIs? ##

For general questions about the Chart Tools API for GWT, first try the
[Google Visualization API Google group](http://groups.google.com/group/google-visualization-api).  Make sure to mention that you are using the GWT wrappers for the project.

Another place you can to for help is the [GWT-Google-Apis](http://groups.google.com/group/gwt-google-apis) group, which is the forum for the developers of the GWT wrappers for all Google APIs.  You can also try the [Google-Web-Toolkit](http://groups.google.com/group/gwt-google-api) group which is the main public forum for GWT.

## What should I do if I think I've found a bug? ##

First post your bug to the [GWT-Google-Apis](http://groups.google.com/group/gwt-google-api) group for discussion.  There may be a workaround or a patch already in place for the problem.  Bugs are logged in the [Issue Tracker](http://code.google.com/p/gwt-google-apis/issues/list).  Please reserve the issue tracker for bugs with the API itself, and not for diagnosing problems with a particular application.

## Can I use the GWT Chart Tools API with both Interactive and Image Charts? ##

Most of the code in the gwt-visualization library is targeted toward interactive charts, but you can use the [Image Chart](http://code.google.com/apis/visualization/documentation/gallery/genericimagechart.html) to build an image chart with the library.

## Why do I see the dialog "Password needed" when extracting the .zip distribution in Windows? ##

There are some files created by the GWT compiler that are very long and cause issues with the built-in Windows compressed folder support.  You can try using a different program to unzip the folder (such as Cygwin or 7-zip).

## Why can't I run the samples from the distribution? ##

If you are on Windows, you may be hitting a path length problem.  Try unpacking the distribution with a third-party unzip program.   Also, try putting it in a folder with a short name off of the root of a drive.

If you are having trouble running the Apache Ant againt provided samples,  make sure you have the GWT\_HOME environment variable set.  It should contain a pathname that contains a uncompressed GWT distribution.

## Why do I get an exception saying that $wnd.google.visualization "is null" or "has no properties"? ##

The following stack trace indicates that the JavaScript API is not getting loaded:

```
[ERROR] Unable to load module entry point class
com.example.myviz.client.MyViz (see associated exception for
details)
com.google.gwt.core.client.JavaScriptException: (TypeError):
'$wnd.google.visualization' is null or not an object
 number: -2146823281
 description: '$wnd.google.visualization' is null or not an object
        at com.google.gwt.visualization.client.visualizations.Table.createJso
(Native Method) 
...
```

Make sure your hosted HTML page includes a `<script>` tag to load the Ajax Loader.

```
    <script type="text/javascript" src="http://www.google.com/jsapi"></script>
```

You can then add JavaScript code to load the API yourself, or you can use the AjaxLoader class provided with the gwt-visualization API.  See the [Getting Started](VisualizationGettingStarted.md) page for an example.

If you have this tag in place, try opening up your web browser (IE on Windows, Safari on Mac) and navigate to the url `http://www.google.com/jsapi` manually.  If you do not see a page of JavaScript, chances are that there is some proxy or firewall setting blocking access to download the API.

## How can I get the Visualization Widgets working in GWT 1.5 Hosted Mode under Linux? ##

This issue should be resolved under GWT 2.0 and later using the Development Mode browser plugin.

Under GWT 1.5 and prior, the hosted mode browser did not support flash out of the box.

Answer from a [Google Visualization API Group](http://groups.google.com/group/google-visualization-api/browse_thread/thread/c23870b13c1b0c62) thread:

In Ubuntu (up to 9.04) I could get the timeline working with the following:

  * GWT uses Mozilla 1.7 as development mode browser with its own plugins.  Flash player 10 does not work reliable with this old browser therefore grab flash 9 from http://www.adobe.com/go/tn_14266.

  * Copy the libflashplugin.so from the archive to /opt/gwt/mozilla-1.7.12/plugins (or whereever you have gwt installed) and restart the development mode browser


## I removed a VisualizationWidget from a Panel.  When I added it back, it was gone!  What happened? ##

GWT developers are used to working with widgets while they are detached from the DOM.  Attaching and detaching the widgets affects how and where they appear, but not the widgets themselves.  However, many of the charts provided by the Google Chart Tools API for Interactive Charts do not support this pattern.  (Currently, this applies to the AreaChart, BarChart, ColumnChart, LineChart, PieChart, and ScatterChart.)  Thus, these charts should be added to a panel once and not removed until they are no longer needed.

## I called `setSelection()` on a chart before adding it to a Panel.  Why didn't it work? ##

Some methods of some charts only work properly when the chart is attached to the DOM.  See the answer to the previous question.

## What happened to com.google.gwt.visualization.client.AjaxLoader?  After upgrading it is gone! ##

In release gwt-visualization-1.0.2, The AjaxLoader class was moved to a common package to be shared with other APIs.  You can access the same functionality by using the com.google.gwt.visualization.client.VisualizationUtils class.  In the future, we will deprecate classes before removing them.

## Can I combine the Chart Tools API and Gears to create an offline application? ##

No.  See [the Visualization API FAQ](http://code.google.com/apis/visualization/faq.html#offline).

## Can I use the Chart Tools API for GWT in a plain Java application without using GWT? ##

No.  The gwt-visualization library merely wraps a set of JavaScript APIs and is dependent on the underlying browser, flash plugin, and uses the network to communicate with Google's servers.

## I'm using GWT 2.0 and I have a problem running visualization on IE8 ##

Please make sure you have a <!DOCTYPE html> declaration at the top of your html files.

## I have problems running my application from file ##

For modern browsers it's common to forbid cross-file references, which most likely is the cause of your problems. You can run `ant devmode` command to start development mode, and paste the starup URL without `?gwt.codesvr=127.0.0.1:9997` suffix into your browser (port may vary). It will make your embedded web server serve your files in a production mode.

## How do I use the updated Chart look and feel? ##

The Chart Tools API has
[released changes](http://code.google.com/apis/visualization/documentation/release_notes.html#05182010) that update the look and feel of some of the charts.
The steps to migrate to the new look and feel are outlined in the [Migration Guide](http://code.google.com/apis/visualization/documentation/release_notes.html#migratingtocore) for JavaScript.
The GWT Chart Tools library version 1.1.1 provides new classes under com.google.gwt.visualization.client.visualizations.corechart that provide native access to this updated look and feel.

For GWT Chart Tools library version 1.1.0 and prior, you can follow these steps:

The ChartTools API In order to do this with GWT, when loading the API, pass the string `"corechart"` instead of `LineChart.PACKAGE` as the last argument to `VisualizationUtils.loadVisualizationApi()`.  Do not use any of the individual chart `PACKAGE` constants or you may see inconsistent behavior.

Not all of the methods may work appropriately as some have been renamed.  If you need to use these methods, then create a subclass of the appropriate Chart [Options](http://gwt-google-apis.googlecode.com/svn/javadoc/visualization/1.1/com/google/gwt/visualization/client/CommonOptions.html) class and add new JSNI methods to invoke the JavaScript API.

## Why do I see the error "Invalid version number" when running Developemnt Mode? ##

```
Connection received from localhost:12345
   [ERROR] Invalid version number "2.1" passed to external.gwtOnLoad(), expected "2.0"; your hosted mode bootstrap file may be out of date; if you are using -noserver try recompiling and redeploying your app
```

This happens when the gwt-visualization.jar file was compiled with a different version of the GWT toolkit than the one you are using to launch Dev Mode.  Either switch the runtime GWT version you are using to launch  Dev Mode or compile the  gwt-visualization.jar file from source with the same version you are currently using.