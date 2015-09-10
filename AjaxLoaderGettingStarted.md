#summary Getting Started Using the Google AjaxLoader API for GWT

# Getting Started with the AjaxLoader API #

The AjaxLoader project is intendend for loading Google AJAX Apis and isn't of much use by itself.   The AjaxLoader class allows a GWT application to dynamically configure load options such as the locale and API key at run time, instead of delegating these functions to the host HTML page.

## Downloading the AjaxLoader API Library for GWT ##

You can download the latest production releases of the libraries from the [project download page](http://code.google.com/p/gwt-google-apis/downloads/). After you download the distribution, uncompress it.  Inside the folder you will find a .jar file named `gwt-ajaxloader.jar`. This .jar file is the only thing you'll need from the distribution.

You can reference the `.jar` file either from the folder that you uncompressed the project in, or copy it and reference it from another location.  In this example, we've chosen to copy the file to the path `/usr/local/gwt-ajaxloader`.

## Setting up a GWT Project to use the AjaxLoader API ##

There are three steps needed to use the AjaxLoader API in a GWT project:

  1. Include the library JAR file in the classpath
  1. Inherit com.google.gwt.ajaxloader.AjaxLoader in the module XML definition file.
  1. Wrap the code that relies on the dynamically loaded api with AjaxLoader.loadApi() calls.

## For more information ##

See the Javadoc and sample application included in the distribution package.
