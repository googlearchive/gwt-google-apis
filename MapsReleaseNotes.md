# Google Maps API Library for GWT Release Notes #

## Release Notes for 1.1.0 ##
### Changes since 1.0.4 ###

  * This library requires GWT 2.0.3 or later and Java 1.5 or later.
  * Library updated for compatibility with GWT 2.0
  * Added support for Street View
  * Improved support for AdsManager
  * Added support for Aerial and Mapmaker map types
  * Improved support for TrafficOverlay through addition of GTrafficOverlayOptions equivalent
  * Added support for ZIndexProcess for Markers
  * Added support for LatLonBox bounds for Placemark
  * Other issues noted in the [issue tracker](http://code.google.com/p/gwt-google-apis/issues/list)
  * Added Eclipse project files and ant script to HelloMaps

### Known Issues ###

  * This API only supports the Google Maps v2 API.


---

## Release Notes for 1.0.4 ##
### Changes since 1.0.3 ###

  * The minimum required Maps API version is 2.147
  * Adds new options and methods to OrgChart
  * Adds support for mouseover, mouseout and load events
  * Added support for some formatters
  * Moves AjaxLoader and some other common classes to a separate library

### Known Issues ###

  * Not all features in the Maps API are supported. The Maps API is constantly evolving.


---

## Release Notes for 1.0.3 ##
### Changes since 1.0.2 ###

  * Adds support for reverse Geocoding in the Geocoder class
  * Adds the TileLayerOverlay.reset() method
  * Adds support for the GAdsManager


---

## Release Notes for 1.0.2 ##
### Changes since 1.0.1 (Release Candiate 2) ###

  * Fixed a bug inside of LatLngBounds.newInstance()
  * Allow Marker and other overlay classes to be subclassed ([issue 206](https://code.google.com/p/gwt-google-apis/issues/detail?id=206))
  * Fixed a problem caused by the GDirections.getStatus() is returning an empty object
  * Removed setCenter() from checkResize(), because in some rare cases, the setCenter() is fatal to the Maps API.  Added checkResizeAndCenter() if you want the old behavior
  * Added support for alternate methods to convert LatLng to pixels


---

## Release Notes for 1.0.1 (Release Candidate 2) ##
### Changes since 1.0.0 (Release Candidate 1) ###

  * Corrected Projection subclassing issue in Hosted mode. ([issue 180](https://code.google.com/p/gwt-google-apis/issues/detail?id=180))
  * Allow MarkerOptions and TileLayer to be subclassed or extended.
  * Corrected the Overlay createPeer() method to construct the proper type (used when accessing an Overlay instance from an event.)
  * Additional patches needed to correct problems with putting Widgets in infowindows (issues 156,181)
  * Maps API samples set the Maps version to v=2 instead of v=2.x.  The experimental version showed compatibility problems.
  * Other issues as noted in the [issue tracker](http://code.google.com/p/gwt-google-apis/issues/list)


---

## Release Notes for 1.0.0 (Release Candidate 1) ##
### Changes since [r290](https://code.google.com/p/gwt-google-apis/source/detail?r=290) ###

  * This library requires GWT 1.5 or later and Java 1.5 or later.
  * This library requires the use of the Google Maps API 2.118 or later.
  * Removed Listener classes for events that were previously deprecated. Each removed class has an equivalent Handler class.
  * Changed many classes to be subclasses of JavaScriptObject (JavaScript overlay types). This means that you can no longer use new to construct many types - you must use the factory method Type.newInstance() instead.
  * Fixed problems initializing constants on IE6/7 in Hosted Mode.
  * Removed MarkerManager, as Maps team is discontinuing support for it in deference to an open source version with more features.
  * Added checks to see that the JS API was properly loaded.
  * Added support for Polyline and Polygon drawing/editing features.
  * Updates to JSIO to support pre-release changes to GWT 1.5 hosted mode.
  * Added support for the Google Earth map type and retrieving the plugin instance
  * Fixed GeocodeCache subclassing issues.
  * Added many unit tests.
  * Fixed many other issues noted in the [Issue Tracker](http://code.google.com/p/gwt-google-apis/issues/list).


---

## Release Notes for [r290](https://code.google.com/p/gwt-google-apis/source/detail?r=290) (milestone release) ##
### Changes since [r41](https://code.google.com/p/gwt-google-apis/source/detail?r=41) ###

  * Added many Maps API features added since 2.83 up to 2.98.
  * Updated features now require Java 1.5 and GWT 1.5 or higher.
  * Updated to use Java 1.5 generics, enum types, and annotations.
  * Maps project placed in a separate tree so it can be built and distributed independently.
  * New event handling system that models each Maps API with a single Java Event and Handler class. Existing Listener classes are now deprecated.
  * Added unit tests for event handing.
  * Added javadoc comments to all classes.
  * Updated to use JSIO [r83](https://code.google.com/p/gwt-google-apis/source/detail?r=83).
  * Removed use of long values passed via JSNI and JSIO methods.
  * Added styling and documentation to the HelloMaps sample project.
  * Added additional features to the HelloMaps sample project, partially as a way to manually test the newly added API features.
  * Changed API to extract constant values from the Maps API instead of hard coding constants in Java.
  * Many issues from the issue tracker have been addressed, all marked as version 1.1\_RC.

## Release Notes for [r41](https://code.google.com/p/gwt-google-apis/source/detail?r=41) (milestone release) ##
### Initial release. ###