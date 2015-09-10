#summary Google AJAX Search API Library for GWT Release Notes.

# Google AJAX Search API Library for GWT Release Notes #


## Release Notes for 1.1.0 ##
### Changes since 1.0.2 ###

  * This library requires GWT 2.0.3 or later and Java 1.5 or later.
  * Library updated for compatibility with GWT 2.0
  * Added Eclipse project files and ant script to HelloSearch


---

## Release Notes for 1.0.2 ##
### Changes since 1.0.1 ###

  * Added AjaxLoader support


---

## Release Notes for 1.0.1 ##
### Changes since 1.0.0 ###

  * Added an overload to LocalSearch.setCenterPoint() to accept a LatLng object from the Maps API.
  * Corrected a problem where the widget returned from getHtml() was always null.
  * Updated the VideoResult class to add hasRating() and hasViewCount() methods.


---

## Release Notes for 1.0.0 (release candidate) ##
### Changes since [r290](https://code.google.com/p/gwt-google-apis/source/detail?r=290) ###

  * This library requires GWT 1.5 or later and Java 1.5 or later.
  * Added ImageSearch support.
  * For event handling, removed Listener classes and replaced with Handler classes
  * Added support for the SearchStarting callback.
  * Added support for the cursor property, allowing developers to fetch subsequent pages of results.
  * Renamed package prefix from com.google.gwt.ajaxsearch to com.google.gwt.search
  * Renamed sample from AJAXSearch to HelloSearch
  * Fixed other issues noted in the Issue Tracker
  * Added unit tests
  * All of the Result classes are now more efficiently implemented in terms of GWT overlay. As a result, some of the method return types have changed from returning JSList's to JsArrays. Please update your code accordingly.


---

## Release Notes for [r290](https://code.google.com/p/gwt-google-apis/source/detail?r=290) (milestone release) ##
### Initial release. ###