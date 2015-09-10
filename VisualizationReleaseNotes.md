# Google Visualization API Library for GWT Release Notes #


## Release Notes for 1.1.1 ##
### Changes since 1.1.0 ###
  * New Corecharts support.
  * Minor update for compatibility with GWT 2.1.
  * Enhancements to DataView and AbstractDataColumn classes.


---

## Release Notes for 1.1.0 ##
### Changes since 1.0.2 ###
  * This library requires GWT 2.0.3 or later and Java 1.5 or later
  * Library updated for compatibility with GWT 2.0
  * Added support for all image-charts: ImageAreaChart, ImageBarChart, ImageLineChart, ImagePieChart, ImageSparklineChart
  * Added wrapper for Toolbar
  * Updated Visualization API bindings to be up-to-date with published documentation
  * Added Eclipse project files and ant script to all samples


---

## Release Notes for 1.0.2 ##
### Changes since 1.0.1 ###
  * Adds new options and methods to OrgChart
  * Adds support for mouseover, mouseout and load events
  * Added support for some formatters
  * Moves AjaxLoader and some other common classes to a separate library.
    * The new class path for AjaxLoader is com.google.gwt.ajaxloader.client.AjaxLoader.
    * Use com.google.gwt.visualization.client.VisualizationUtils.loadVisualizationApi() to dynamically load the library.


---

## Release Notes for 1.0.1 ##
### Changes since 1.0.0 ###

  * Adds some missing javadoc annotations and packages to the build
  * Fixes a bug where JavaScript dates were not being correctly converted to Java dates in the Properties class
  * Fixed a bug in Selection methods that supported AbstractVisualization

### Known Issues ###

  * Linux hosted mode will not display visualizations that use svg or flash to render.  This will be addressed in upcoming releases of GWT with a revamped hosted mode.


---

## Release Notes for 1.0.0 (Release Candidate) ##
### Initial Release ###

  * Contains bindings for 14 popular visualizations authored by Google
  * Provides a convenience library to load visualizations using the AJAX Loader
  * Distributed with sample code for using existing visualizations
  * Libraries and sample code for creating your own visualization in GWT are included
  * Includes base classes that can be re-used to create bindings for other Visualizations
  * Javadoc is bundled with the distribution
  * Online documentation includes a tutorial, support tracker and javadoc