# Designing JavaScript API Wrappers for GWT #

When creating a wrapper for an existing JavaScript API using the Google Web Toolkit, there are a number of approaches available.  This page will point out some common approaches and some lessons learned over the course of creating wrappers for several APIs.

## Tools for integrating JavaScript with GWT ##

  * JavaScript Native Interface (JSNI) (_TBD_ link to GWT Dev Guide)

> JSNI is the most basic way to interact with JavaScript.  It is covered in the Developer's Guide. It is commonly used by many developers.

  * JavaScriptObject overlays (_TBD_ link to GWT Dev Guide)

> When a JavaScript object is returned from JavaScript into Java, it is declared to be of type JavaScriptObject.  In GWT 1.5, the compiler now allows you to subclass JavaScriptObject (with some restrictions) and access the object contents using JSNI.  The `this` target is set to be the JavaScriptObject.

  * [JavaScript InterOp library (JSIO)](http://code.google.com/p/gwt-api-interop)

> The JSIO library is an optional library that provides facilities for different kinds of JavaScript integration. It provides an easy way to construct new JavaScript objects (@Constructor and @Global), create property accessors(@BeanProperties), set callbacks (JSFunction), export Java functions onto a JavaScript object (@Exported) and passing arrays back and forth between Java and JavaScript(JSList).

  * GWT Linker extensions (_TBD_ link to GWT Dev Guide)

> Another feature new to GWT 1.5 is the Linker.  This gives application writers control over the way files are created in the linking stage of the GWT output.  In the GWT Gadgets API, for example, the linker is used to create a Gadgets specification file.

  * GWT Generators (_TBD_ link to GWT Dev Guide)

> Generators are used to simplify repetitive tasks.  JSIO uses generators to automatically create temporary files that implement JavaScript wrapper functions in JSNI.

Each tool has its own tradeoffs:

  * JSNI is the most basic method and gives you the most flexibility.  Using JSNI frequently can be tedious and error prone, as you are including more and more JavaScript in the code that will not be subjected to compile time checks.
  * JavaScript overlays are very useful when you want to present an API to the user that closely models the JavaScript API.  The compiler can also be very efficient with the code generated from an overlay.
  * The JSIO library has a number of powerful tools for integrating with JavaScript libraries.  It is used in the [GWT Google APIs](http://code.google.com/p/gwt-google-apis/) binding for [Google Maps](http://code.google.com/apis/mpas) and [AJAX Search](http://code.google.com/apis/ajax).  One downside is that using JSIO involves importing an extra library into your build environment.  Also keep in mind that the JSIO library is somewhat experimental, and not commonly used by GWT developers.   Try to use JavaScript overlays where possible.
  * Writing Generators and Linkers are advanced usage of the GWT library.  Prepare to get dirty reading through the GWT core code learning how to use GWT internals such as the TypeOracle and Artifacts.

## Common Coding Issues ##


### Accessing JavaScript properties ###

  * Use JSNI
  * Use JavaScriptOverlays
  * Use JSIO Library @BeanProperties - From a performance perspective, is almost always preferable to use an overlay type or JSNI methods instead.

_TBD_


### Passing Arrays back and forth between JavaScript library ###

  * JsArray class in GWT
The JsArray class is a set of wrappers around the JavaScript Array class.  There are separate class names for different primitive JavaScript types.  JsArray types will compile into native JavaScript arrays.  Note that in hosted mode, your code will have to cross the JSNI boundary every time it needs access the array, which may cause performance issues when debugging.

  * JSArray in JSIO Library - If you are already using JSIO this class can help you manage migrating arrays between Java and JavaScript.

_TBD_

### Exposing Constant values ###

  * Creating a getter using JavaScriptOverlays
  * Creating a getter using @Global in JSIO Library
  * Static Initializer of a Java constant

_TBD_

### Creating a class intended for end developers to subclass and override implementation ###

  * Problem: Overriding a method can lose the method def in the object
  * JSIO tricks using prototype object.

_TBD_

### Creating a Java class hierarchy out of a JavaScript class hierarchy ###

  * Problem: Overriding a method can lose the method def in the object. Declare methods final if you don't want them stomped on!

_TBD_

### Passing a callback function to a JavaScript method ###

There are several considerations for implementing JavaScript callbacks in GWT:
  1. Handling exceptions w/in callbacks in Hosted Mode requires that you wrap the invocation of the callback with a try/catch block and delegate exceptions to an UncaughtExceptionHandler.
  1. Callback parameter signatures in JavaScript may change and be backward compatible with JavaScript client code, but not with the Java wrappers.

  * Using a JSNI Method containing a closure for the callback, then delegating to a Java static method.



  * JSIO JSFunction - Very convenient for defining callbacks.




_TBD_

## Loading Patterns ##

  * Put it in API provided module.gwt.xml
  * Put it in user's .gwt.xml
  * (need ajaxloader sol'n)

_TBD_

## Modelling JS functions with optional arguments ##

For optional arguments, the usual approach is to create overloads for each
additional parameter.  If a JavaScript method had a signature where
all three arguments were optional, as follows:

```
  getPermission(siteName?, imageUrl?, extraMessage?)
```

The Java accessors to this method could be modeled as four overloaded methods:

```
 public native boolean getPermission()
 public native boolean getPermission(String siteName)
 public native boolean getPermission(String siteName, String
imageUrl)
 public native boolean getPermission(String siteName, String
imageUrl, String extraMessage)
```


## Common Design Problems ##

  * Deciding when to omit a feature.
  * Deciding when to create extra types.
  * Planning your wrappers for future JS API growth.
  * Providing access to JavaScriptObject instances for end developer flexibility.
  * How to write Unit Tests
  * Hiding the Java/JavaScript integration mechanism.