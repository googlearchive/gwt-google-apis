#summary Frequently Asked Questions about the GWT bindings for AJAX Search

# AJAX Search FAQs #

These FAQs refer to the Google AJAX Search API Library for GWT.  See also [Project Overview](Overview.md) for general information on the Google API Libraries for GWT.



## Using the AJAX Search RESTful interface ##

There is no explicit API support at this time for the AJAXSearch RESTful interface, but you can implement it with a combination of the GWT's `RequestBuilder` class and  and [JavaScript overlay types](http://code.google.com/webtoolkit/doc/latest/DevGuideCodingBasicsOverlay.html).

## Why is there a `gwt` argument in the query line on the 

&lt;script&gt;

 tag to load the AJAX Search API? ##

This optional parameter is used by Google to collect data on what percentage of API traffic is generated from apps built with GWT.

## I have problems running my application from file ##

For modern browsers it's common to forbid cross-file references, which most likely is the cause of your problems. You can run `ant devmode` command to start development mode, and paste the starup URL without `?gwt.codesvr=127.0.0.1:9997` suffix into your browser (port may vary). It will make your embedded web server serve your files in a production mode.
