Instructions for using Eclipse

These instructions require 
  GWT 2.0 or higher          http://code.google.com/webtoolkit
  Google Plugin for Eclipse  http://code.google.com/eclipse
  Java JDK 1.5 or higher     http://java.sun.com/

---------- Importing Projects ------------------------------------

To work with sample code in Eclipse, copy gwt-xxx.jar (xxx is the name of the
api. e.g. maps, search) to samples/name-of-sample/war/libs folder.

After copying the jar file, import the project in the samples/name-of-sample
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

