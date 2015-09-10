#summary Frequently Asked Questions about the GWT bindings for Gears

# Gears FAQs #

These FAQs refer to the Google API Library for GWT bindings for the Gears API.
See also [Project Overview](Overview.md) for general information on the Google API Libraries for GWT.



## Why do I see the dialog "Password needed" when extracting the .zip distribution in Windows? ##

There are some files created by the GWT compiler that are very long and cause issues with the built-in Windows compressed folder support.  You can try using a different program to unzip the folder (such as Cygwin or 7-zip), or you can use the `.tar.gz` distribution.

## How do I write worker thread code in GWT? ##

The bindings do not support such a way right now, but if you create a GWT module and specify the Single Script linker, you can load the generated script as a public resource using the [WorkerPool.createWorkerFromUrl(String)](http://gwt-google-apis.googlecode.com/svn/javadoc/gears/1.3/com/google/gwt/gears/client/workerpool/WorkerPool.html#createWorkerFromUrl(java.lang.String)) method.

## Why do the samples that use LocalServer fail when I run them using a file:// URL? ##

This is a constraint of the Gears plugin.  Run them using the Compile/Browse from hosted mode or by copying the files to be served from a web server to run the samples locally.

## I am using GWT 1.6 (or above) and having trouble with problems parsing XML or the Xerces library ##

New features in 1.6 have included multiple versions of the Xerces library.  Try using the `gwt-gears-noredist.jar` jar file instead of `gwt-gears.jar` (new in the gwt-gears-1.2.0 release).  The `-noredist.jar` version does not include any extra library classes outside of the `com.google.gwt.gears` package.

## I have problems running my application from file ##

Modern browsers commonly forbid cross-file references, which result in permissions errors. From the command line, you can run `ant devmode` command to start development mode, make sure your project has been compiled, then paste the starup URL without the `?gwt.codesvr=127.0.0.1:9997` suffix into your browser (port may vary). It will make GWT devmode's embedded web server serve your compiled project in production mode.