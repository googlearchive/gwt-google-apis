#summary Using Gears in a GWT project

# Getting Started with Gears #

This guide describes the basics behind writing a small project that uses the Gears API with [Google Web Toolkit](http://code.google.com/p/google-web-toolkit) (GWT).  Before working through this guide, you should be somewhat familiar with the [Gears developer's site](http://code.google.com/apis/gears/).

## Assumptions ##

  * You are already familiar with [Google Web Toolkit](http://code.google.com/webtoolkit/overview.html)
  * You know how to [create a new GWT project](http://code.google.com/eclipse/docs/creating_new_webapp.html) using the Google Plugin for Eclipse.

## Getting the Gears API Library for GWT ##

You can download the latest production releases of the libraries from the [project download page](http://code.google.com/p/gwt-google-apis/downloads/). After you download the distribution, uncompress it.  Inside the folder you will find a .jar file named `gwt-gears.jar`.

You can either reference the `.jar` file from the folder that you uncompressed the project in, or copy it to another location, such as the location of your GWT distribution (containing `gwt-dev.jar` and `gwt-user.jar` files).  In this example, we've chosen to copy the file to the path `/usr/local/gwt-gears`.

## Gears Modules and Topics ##
Gears contains three main modules, [LocalServer](http://code.google.com/apis/gears/api_localserver.html), [Database](http://code.google.com/apis/gears/api_database.html), and [WorkerPool](http://code.google.com/apis/gears/api_workerpool.html).

Continue to [Database](GearsGettingStartedDatabase.md) to learn how to use the Gears Database Module.
Continue to [LocalServer](GearsGettingStartedOffline.md) to learn how to use the Gears LocalServer Module.
Continue to [WorkerPool](GearsGettingStartedWorkerPool.md) to learn how to use the Gears WorkerPool Module.