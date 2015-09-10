# Google API Libraries for Google Web Toolkit #

The Google API Libraries for [Google Web Toolkit](http://code.google.com/webtoolkit) is a collection of libraries that provide Java language bindings for popular Google JavaScript APIs. These libraries make it quick and easy for developers to use these Google JavaScript APIs with Google Web Toolkit. The libraries are supported by the Google Web Toolkit team.

<font color='red'><b>Status:</b> This library is currently <b>alpha</b>.</font> Don't use it in critical applications just yet!

## Available Libraries ##

Note that these versions rely on [GWT 2.3.0](http://code.google.com/webtoolkit/download.html) or higher.

#### [New Google APIs](GoogleAPIsGettingStarted.md) ####
> Libraries to communicate with new Google APIs, including:
  * Google+ API ([demo](http://gwt-google-apis.googlecode.com/svn/trunk/apis/samples/plus/demo/PlusSample.html) | [code](http://code.google.com/p/gwt-google-apis/source/browse/trunk/apis/samples/plus/com/google/api/gwt/samples/plus/client/PlusEntryPoint.java))
  * Google Books API
  * Google Calendar API ([demo](http://gwt-google-apis.googlecode.com/svn/trunk/apis/samples/calendar/demo/CalendarSample.html) | [code](http://code.google.com/p/gwt-google-apis/source/browse/trunk/apis/samples/calendar/com/google/api/gwt/samples/calendar/client/CalendarEntryPoint.java))
  * Google APIs Discovery Service
  * Google Tasks API
  * Google URL Shortener (goo.gl) API ([demo](http://gwt-google-apis.googlecode.com/svn/trunk/apis/samples/urlshortener/demo/UrlshortenerSample.html) | [code](http://code.google.com/p/gwt-google-apis/source/browse/trunk/apis/samples/urlshortener/com/google/api/gwt/samples/urlshortener/client/UrlshortenerEntryPoint.java))

**Google APIs libraries are no longer actively supported**

#### [Gadgets 1.2 Library](GadgetsGettingStarted.md) ####
> [Gadgets](http://code.google.com/apis/gadgets/) are simple HTML and JavaScript applications that can be embedded in webpages and other apps. This library simplifies gadget development with GWT by automatically generating a [Gadget Specification](http://code.google.com/apis/gadgets/docs/reference.html#XML_Ref) from Java source, inserting a selection script in the specification much like a regular GWT project. After compiling your gadget with GWT, all files are in place to [publish](http://code.google.com/apis/gadgets/docs/publish.html) your gadget.

#### [Google Chart Tools (aka Visualization) 1.1 Library](VisualizationGettingStarted.md) ####
> The [Google Chart Tools API](http://code.google.com/apis/charttools/index.html) provides GWT bindings to the Google Chart Tools.  This library enables you to expose your own data, stored on any data-store that is connected to the web, as a Chart Tools compliant datasource. This release updates existing bindings and adds support for all Image-Charts and Toolbar. For a full list of charts with GWT wrappers you can refer to the [Chart Tools/Interactive Chart Gallery](http://code.google.com/apis/visualization/documentation/gallery.html).

#### [AjaxLoader 1.1 Library](AjaxLoaderGettingStarted.md) ####
> Version 1.1 of the [AjaxLoader API](http://code.google.com/apis/ajax/documentation/) for GWT provides GWT applications to control when Google APIs are loaded and to vary API arguments at runtime. This release additionaly gives you control over [ClientLocation API](http://gwt-google-apis.googlecode.com/svn/javadoc/ajaxloader/1.1/com/google/gwt/ajaxloader/client/ClientLocation.html) and domain from which libraries are loaded. This library is incorporated into the Chart Tools, Maps, Search, and Language APIs.