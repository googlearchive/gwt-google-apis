# Using a library or source code #

When you develop code with the GWT Google APIs you have two choices.  You can either use the library from a JAR file, or you can use the library by pointing to a source tree, usually a current version of the library checked out from the Subversion repository.  Which method you choose depends on your development needs.

The production version of the GWT Google APIs is distributed as a
`.jar` file that is easy to integrate with an existing GWT project.
Choose the `.jar` file library if you need a stable release of the
library and do not have a need for a specific pre-release feature.

The latest version of the API source code is also publicly available
from a [Subversion](http://subversion.tigris.org/) repository hosted by
[Google Code](http://code.google.com/).  Choose the source code if you
need to use the latest features and bug fixes by the library, or if
you are interested in contributing patches to the library.


## Adding Google APIs to a GWT Project from source ##

This section describes how to integrate your project with the GWT Google APIs project using a checkout of the subversion repository.  If this is your first time setting up a project, you should work through the basic API specific Getting Started instructions.  These additional instructions are provided for those that intend to be a contributor to one of the `gwt-google-apis` projects, or need to use the very latest release of the API before a public release.

If you find yourself struggling with these instructions, you might want to go back and use one of the API specific Getting Started pages, which is sufficient for almost all developers.

### Getting the GWT Google APIs source ###

Downloading the latest source code requires the use of a
[Subversion](http://subversion.tigris.org/) client.  The Subversion
website contains command line clients for most operating systems.
Also, some IDEs can access a subversion repository natively or with a
plugin.  You can also find open source and commercial Subversion
clients on the web.

To get started, point your client at the GWT Google APIs subversion repository  [subversion repository](http://code.google.com/p/gwt-google-apis/source) and check out a copy of the project into a directory.

The following example shows a checkout using the command line client on Linux:

```
 $ mkdir /usr/local/gwt-google-apis
 $ cd /usr/local/gwt-google-apis/
 $ svn checkout http://gwt-google-apis.googlecode.com/svn/trunk/ .
 A    ajaxsearch/distro-source
 A    ajaxsearch-apis/distro-source/common.ant.xml
 A    ajaxsearch/distro-source/core
 A    ajaxsearch/distro-source/core/src
 ...
Checked out revision 330.
```

Remember the path where you performed the checkout.  This documentation will refer to this path as _GWT\_GOOGLE\_APIS\_DIR_.

You will also need a copy of the GWT tools.  Check that out from the google-web-toolkit project:

```
$ mkdir -p /usr/local/gwt
$ cd /usr/local/gwt
$ svn co http://google-web-toolkit.googlecode.com/svn/tools ./tools
```

and then set an environment variable named GWT\_TOOLS to point there.

```
$ GWT_TOOLS=/usr/local/gwt/tools
$ export GWT_TOOLS
```

You will also need GWT\_HOME setup to point to a binary build of the [GWT distribution](http://code.google.com/webtoolkit/download.html).

After you have checked out the code from Subversion, you need to build the distribution.  You will need [Apache Ant](http://ant.apache.org) and a Java JDK, such as the one available from [Sun Microsystems](http://java.sun.com/).

```
$ cd /usr/local/gwt-google-apis/
$ ant
...

BUILD SUCCESSFUL
Total time: 10 minutes 47 seconds
```


### Creating a skeleton project ###

You can use either the Google Plugin for Eclipse or the `webAppCreator` script to create a skeleton project to get started.

```
$PP_OFF
$ webAppCreator -out MapsTutorial com.example.google.gwt.mapstutorial.client.MapsTutorial
```


Then update the module to include the Maps project module file and the Google Maps API script.  Edit `MapsTutorial/src/com/example/google/gwt/mapstutorial/MapsTutorial.gwt.xml` and add the following lines:

```
 <!-- Load the Google Maps GWT bindings from the gwt-google-apis project -->
 <inherits name="com.google.gwt.maps.GoogleMaps" />

  <!--
    If you want to deploy this application outside of localhost,
    you must obtain a Google Maps API key at:
    http://www.google.com/apis/maps/signup.html
    Replace the src attribute below with a URL that contains your key.
  -->
 <!-- script src="http://maps.google.com/maps?gwt=1&amp;file=api&amp;v=2.x&amp;key=???" /-->

 <!-- You can run under localhost without a key -->
 <script src="http://maps.google.com/maps?gwt=1&amp;file=api&amp;v=2.x" />
```

### Import the Eclipse project from the GWT Google APIs source code ###

Next, you can import the project.

  1. Choose _File->Import..._ from the menu bar.
  1. Select _General->Existing Projects into Workspace_
  1. Browse from _Select root directory_ to the path you checked out from subversion (_GWT\_GOOGLE\_APIS\_DIR_`/maps/maps` or `/usr/local/gwt/gwt-google-apis/maps/maps` in our example.)
  1. You should see a project named `gwt-maps` on the list.  Make sure it is selected.
  1. Choose _Finish_ to import the project.

You may need to fixup the project's build path.

  1. Make sure the `gwt-maps` project is open and selected in the tree on the left.
  1. Navigate to  _Project->Properties...->Java Build Path->Libraries_
  1. If you are using the Google Plugin for Eclipse, make sure the project has _Use Google Web Toolkit_ enabled in the project properties.
  1. If you aren't using the Google Plugin for Eclipse, add the `gwt-dev.jar` and `gwt-user.jar` libraries to the _Libraries_ tab.  Alternatively, if you have imported the GWT source projects, add the gwt-dev and gwt-user projects to the _Projects_ tab.

### Updating Eclipse Build and Launch Configuration ###

If you are using the Eclipse IDE and created your project with `applicationCreator -eclipse`, then you will have a `.project` file and a `MapsTutorial.launch` file in the root of your project.

Import the MapsTutorial project into eclipse using the _File->Import..._ command.

After you successfully import the project, you first need to update the MapsTutorial build configuration.

  1. Make sure your new project is open and select your project in the tree on the left.
  1. Navigate to  _Project->Properties...
  1. Select_Java Build Path_in the tree on the left
  1. Open the_Projects_tab.
  1. Add the project `gwt-maps`._

Next, update the launch configuration for DevelopmentMode.   Using the Google Plugin for Eclipse, you simply create a new Web Application launch configuration.   The following steps demonstrate how to create a launch configuration manually without the plugin to  launch DevelopmentMode from Eclipse's `Run Dialog...` menu option.

  1. Make sure your new project is open.
  1. Navigate to  _Run->Open Run Dialog..._
  1. In the Run Dialog, open the _Java Application_ branch in the tree on the left
  1. Select your project name (`MapsTutorial` in our example.)
  1. Open the Classpath tab.  Select the _User Entries_ item in the tree.
  1. Click the _Advanced..._ button.
  1. Select _Add Folders_ and _OK_.
  1. When the file browser appears, select the directory `src` from the `gwt-maps` project you created previously.
  1. Use the _Up_ and _Down_ button to move the _src_ folders to the top of the _User Entries_ list.  Make sure that the _MapsTutorial (default classpath)_ is at the bottom of the list.

> ![http://gwt-google-apis.googlecode.com/svn/wiki/GettingStartedBasic4.png](http://gwt-google-apis.googlecode.com/svn/wiki/GettingStartedBasic4.png)


Now you are ready to build a project!