# Gadgets API Library for GWT Release Notes #

## Release Notes for 1.2.0 ##
### Changes since 1.1.0 ###

  * Updates use of the gadgets JavaScript API to use gadget.**namespace.
  * Deprecates legacy gadgets related methods and interfaces.
  * Adds OpenSocial Lite API (1.0) core support.
  * New Traveler sample shows use of gadgets + OpenSocial + AppEngine using OAuth authentication and the Maps library.
  * New Utility class for easy use of GWT RPC in the gadgets environment + updated GadgetRPC example.
  * Adds support for Locale settings in gadget manifest.
  * HTML strict or quirks mode can be selected with the @UseHtmlQuirksMode annotation.
  * Gadget manifest names can be generated with short or long format, controlled by the @UseLongManifestName annotation.
  * Changes Feature classes to interfaces to allow mocking for unit testing.**

### Known Issues in 1.2.0 ###

  * Gadgets debugging is currently not supported.

## Release Notes for 1.1.0 ##
### Changes since 1.0.3 ###

  * This library requires GWT 2.0.3 or later and Java 1.5 or later
  * Library updated for compatibility with GWT 2.0
  * Added support for multiple views and the Views API mapping
  * Added Eclipse project files and ant script to all samples


---

## Release Notes for 1.0.3 ##
### Changes since 1.0.2 ###

  * EnumValue tags in the gadget manifest are now omitted for hidden user prefs
  * Added the LockedDomain feature
  * Caching of generated JavaScript code is now set to 1 year. Previuosly it was just an hour. This should improve the loading time of GWT gadgets.


---

## Release Notes for 1.0.2 ##
### Changes since 1.0.1 ###

  * Adds support for GadgetAds and sample code.
  * Adds the getMsg() method to PreferencesUtil for <a href='http://code.google.com/apis/gadgets/docs/i18n.html#Displaying'>internationalization</a> support.
  * Adds an Image subclass that uses the gadget caching mechanism.
  * Adds support for the fetchContent and fetchContentXML features.
  * Fixed the GadgetGenerator to work on classes derived from a subclass of Gadget (e.g. GadgetAds).
  * Fixed problem debugging gadgets under Windows (<a href='http://code.google.com/p/gwt-google-apis/issues/detail?id=213'><a href='https://code.google.com/p/gwt-google-apis/issues/detail?id=213'>issue 213</a></a>.)
  * Dynamic Resize fixed (<a href='http://code.google.com/p/gwt-google-apis/issues/detail?id=218'><a href='https://code.google.com/p/gwt-google-apis/issues/detail?id=218'>issue 218</a></a>.)
  * Corrects some NPE's in the GadgetLinker and GadgetGenerator classes.
  * Updates GadgetTemplate and heap size in samples launch scripts for compatibility with GWT 1.6


---

## Release Notes for 1.0.1 ##
### Changes since 1.0.0 ###

  * Fix to a Null Pointer Exception in the Gadget generator triggered when a Gadget subclass implements a non-feature interface.
  * Added an annotation @InjectContent for a Gadget which enables developers to inject handwritten content (stored in files) into the gadget spec.
  * Added Gadget RPC Sample.


---

## Release Notes for 1.0.0 (release candidate) ##
### Changes since [r290](https://code.google.com/p/gwt-google-apis/source/detail?r=290) ###

  * This library requires GWT 1.5 or later and Java 1.5 or later.
  * This release supports the [Legacy gadget API](http://code.google.com/apis/gadgets/docs/legacy/dev_guide.html).
  * Fixed script and stylesheet injection in gadgets bootstrap.
  * Added xerces library reference for debugging a gadget in hosted mode.

### Known Issues ###

  * Gadgets must be debugged using -noserver and the gadget must be hosted in a gadget container like [iGoogle](http://www.google.com/ig) or [Shindig](http://incubator.apache.org/shindig/).
  * GWT RPC does not work through iGoogle ([issue 154](https://code.google.com/p/gwt-google-apis/issues/detail?id=154))


---

## Release Notes for [r290](https://code.google.com/p/gwt-google-apis/source/detail?r=290) (milestone release) ##

### Initial release. ###