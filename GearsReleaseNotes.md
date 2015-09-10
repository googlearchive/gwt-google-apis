#summary Gears API Library for GWT Release Notes.

# Gears API Library for GWT Release Notes #
## Release Notes for 1.3.0 ##
### Changes since 1.2.1 ###

  * This library requires GWT 2.0.3 or later and Java 1.5 or later.
  * Library updated for compatibility with GWT 2.0
  * Updated the linker to filter out the .gwt.rpc files from the gears manifest by default ([issue 280](https://code.google.com/p/gwt-google-apis/issues/detail?id=280))
  * Fixed method setOnProgressHandler() ([issue 288](https://code.google.com/p/gwt-google-apis/issues/detail?id=288))
  * Added support for the BlobBuilder class and Blob.getBytes() method
  * Added support for the Gears Canvas and Desktop's BlobMetaData object
  * Added Eclipse project files and ant script to all samples

### Known Issues in 1.3.0 ###

  * Creating a Worker from a GWT module is not supported out-of-the-box.
  * Filtering url with @filter in GearsManifest is not working with IE.


---

## Release Notes for 1.2.1 ##
### Changes since 1.2.0 ###

  * Desktop.openFiles no longer throws a CastClassException ([issue 239](https://code.google.com/p/gwt-google-apis/issues/detail?id=239))
  * Added the remove() method to the Database class
  * Fixed failures in RequestCallback on some browsers ([issue 242](https://code.google.com/p/gwt-google-apis/issues/detail?id=242))

### Known Issues in 1.2.0 ###

  * Gears applications can only be debugged using GWT's hosted mode on Windows; other hosted mode platforms do not support the Gears plugin. ([issue 11](https://code.google.com/p/gwt-google-apis/issues/detail?id=11))
  * Creating a Worker from a GWT module is not supported out-of-the-box.
  * GWT hosted mode does not respect URLs which are being redirected by the Gears plugin version 0.4.X. As a result, the ManagedResourceDemo will not demonstrate the expected refresh behavior.


---

## Release Notes for 1.2.0 ##
### Changes since 1.1.1 ###

  * Adds Gears 0.4 feature support:
    * Geolocation<
    * Desktop
    * Blob
    * HttpRequest
    * File Upload demo
  * Added support for Factory.getPermission() and Factory.hasPermission()
  * Updated all samples to allow more heap memory in launch scripts.


---

## Release Notes for 1.1.1 ##
### Changes since 1.1.0 ###

  * Updated gears\_init.js to add support for Gears under Webkit
  * Messages from workers can now be Objects, primitives, or arrays
  * Updated the samples to take care of a few cosmetic issues
  * Added WorkerPoolDemo sample


---

## Release Notes for 1.1.0 (release candidate) ##
### Changes since [r290](https://code.google.com/p/gwt-google-apis/source/detail?r=290) ###

  * This library requires GWT 1.5 or later and Java 1.5 or later.
  * Refactored API to use GWT 1.5's new JavaScript overlay feature; you must use the Factory class to get Gears class instances.
  * Renamed packages under a common client root.
  * All samples will now rebind their EntryPoints to handle cases where Gears is not installed.


---

## Release Notes for [r290](https://code.google.com/p/gwt-google-apis/source/detail?r=290) (milestone release) ##
### Changes since 1.0.0 ###

  * Added method to test for Gears availability. ([issue 1](https://code.google.com/p/gwt-google-apis/issues/detail?id=1))
  * Updated bundled gears\_init.js. ([issue 5](https://code.google.com/p/gwt-google-apis/issues/detail?id=5))
  * Added support for automatic Gears manifest creation. ([issue 6](https://code.google.com/p/gwt-google-apis/issues/detail?id=6))
  * Fixed ResourceStore exceptions to include the JS error messages. ([issue 7](https://code.google.com/p/gwt-google-apis/issues/detail?id=7))
  * ResourceStore URL capture does not work in GWT hosted mode. ([issue 10](https://code.google.com/p/gwt-google-apis/issues/detail?id=10))
  * ResultSet.getFieldAsString is documented to throw a DatabaseException but the exception is missing from the method's declaration. ([issue 68](https://code.google.com/p/gwt-google-apis/issues/detail?id=68))
  * JavaScriptExceptions can escape from the Database constructors. ([issue 69](https://code.google.com/p/gwt-google-apis/issues/detail?id=69))
  * Split these GWT bindings into their own distribution.


---

## Release Notes for 1.0.0 ##

### Initial release. ###