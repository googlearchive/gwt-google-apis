Instructions for using Eclipse

These instructions require 
  GWT 2.0 or higher          http://code.google.com/webtoolkit
  Google Plugin for Eclipse  http://code.google.com/eclipse
  Java JDK 1.5 or higher     http://java.sun.com/
  Ant 1.7.1 or higher        http://ant.apache.org/

---------- Importing Projects ------------------------------------

To work with sample code in Eclipse, the precompiled library needs to be copied
into the war directory. 

If you are working with HelloGadgets, GadgetRPC or BasicGadgetAds sample,
please follow the instructions below:
*) Copy gwt-gadgets.jar to the samples/name-of-sample/war/WEB-INF/lib folder.
OR
*) Execute command 'ant libs' in the directory of the sample project. Please be
   sure to pass to ant information about location of your GWT SDK. You can do
   it by either setting gwt.sdk property on the command line or by creating
   environment variable GWT_HOME.

The Traveler sample requires more preparation:
1) Download http://gwt-google-apis.googlecode.com/files/gwt-maps-1.1.0.zip or
   higher and place it in the war/WEB-INF/lib directory.
2) Point the environment variable GWT_HOME at the location of your GWT SDK.
3) Checkout http://google-web-toolkit.googlecode.com/svn/tools and point
   GWT_TOOLS environment variable at the directory created by the checkout.
4) Download the AppEngine SDK for Java
   (http://code.google.com/appengine/downloads.html) and point the
   APPENGINE_HOME environment variable at the extracted archive.
5) Execute the 'ant libs' command in the directory of the sample.
If you forget any of above steps, the 'ant libs' command will show a suitable
warning.

After copying the libraries, import the project in the samples/name-of-sample
folder.

Select Project -> Properties from the menu bar and afterwards select:

  Google -> Web Toolkit Settings... -> Use Google Web Toolkit

Make sure your SDK settings are valid.

----------- Launching in Development Mode --------------------------

In the Package Explorer, right click on the project that has an entry point 
you want to run.  Select

  Run As -> Web Application

The development mode window will start, prompting you to enter a URL 
into a supported browser.  For more information on Development mode, see:

  http://code.google.com/webtoolkit/gettingstarted.html#running

This procedure will work with most of the samples distributed with the project,
but a more complex launch configuration will require you to set up a run
configuration using the Run... Dialog.

