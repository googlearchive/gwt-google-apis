#summary Google API Library for Google Web Toolkit Release Notes

## Release 1.0.0 ##

Initial release including support for [Gears](http://gears.google.com/).

#### Known Issues ####

  * GWT's hosted browser only supports Gears on the Windows platform.  However, the compiled application will run on any Gears-enabled browser.
  * Refreshing a Gears application in GWT's hosted browser will ignore any URLs captured via the ResourceStore or ManagedResourceStore classes.
  * The offline capabilities of the !GWTNote sample application are not yet complete.  The sample does demonstrate persistence using the Gears Database class.
  * The Gears FileSubmitter class was excluded due to time constraints.



















