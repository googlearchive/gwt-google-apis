# Google AjaxLoader API Library for GWT Release Notes #

## Release Notes for 1.1.0 ##
### Changes since 1.0.0 ###

  * This library requires GWT 2.0.3 or later and Java 1.5 or later.
  * Library updated for compatibility with GWT 2.0
  * Updates the AjaxLoader class to use the same protocol for loading jsapi as was used in the hosted page.
  * Added support for the google.loader.ClientLocation API
  * Added support for custom hostnames for enterprise users
  * Added Eclipse project files and ant script to HelloAjaxLoader


---

## Release Notes for 1.0.0 ##
### Initial Release ###

  * Allows specifying the API key dynamically through the AjaxLoader.init() function
  * Allows one or more APIs to be dynamically loaded through the AjaxLoader.loadAPI() function.
  * Includes 2 generally useful helper classes: ExceptionHelper and ArrayHelper