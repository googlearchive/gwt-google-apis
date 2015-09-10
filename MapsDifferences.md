# Differences between the JavaScript Maps API and the Maps API Library for Google Web Toolkit #

## Naming ##

The Maps API prepends all methods with the letter `G`.  The GWT library drop this prefix.

## Constant Values ##

In many cases, where the Maps API provides a constant value, the GWT library will provide a class with an accessor method to retrieve the value.  This is primarily for run-time efficiency and compact code generation in GWT.  Creating a separate Java constant value would require the constant to be copied at run time.

## Event Handling ##

The Maps event handling in JavaScript relies on JavaScript's dynamic type system.  For Java, we modeled each event as a separate class with accessor methods to retreive the event arguments, and a separate handler class to implement.  This gives a type safe, yet extensible way to handle events.  It also creates a large number of classes and methods on some of the objects - your IDE should help you make quick work of selecting and implementing the appropriate handler classes.

## Subclassing ##

Subclassing in Java and JavaScript has different semantics.  When using GWT to wrap JavaScript, the API author must be careful to preseve the semantics of subclassing (such as the super() method).  In some cases, you will see that a special class has been provided to allow authors to create a subclass.  See [CustomGeocodeCache](http://gwt-google-apis.googlecode.com/svn/javadoc/maps/1.0/com/google/gwt/maps/client/geocode/CustomGeocodeCache.html).

You may notice that there are other classes that you would like to subclass that have been marked final.  Often it is because the implications of creating a subclass have not yet been considered.  Consider using composition instead.  If that will not work, please post on the [Google-web-toolkit-contributors group](http://groups.google.com) if you find a class marked final that you would like to remove the restriction.

## Overlay Types ##

The GWT wrappers use a concept known as JavaScript overlay types to represent a Google Maps API object with as lightweight a representation as possible.  This means that some objects are subclasses of JavaScriptObject, a special type in GWT that represents a raw JavaScript object returned from an API.  Due to some constraints in GWT, the constructor of an overlay type must be proteted, and thus, you must use a factory method to instantiate these types.  By convention, the name of the factory method is `newInstance()`.  For example, to create a new `LatLng` object you would use `LatLng.newInstance(double, double)`:

```
  LatLng myLatLng = LatLng.newInstance(45.1, -105.7);
```

## Features ##

The Maps team releases new versions of the Google Maps api every few weeks.  The `gwt-maps` wrapper trails the public release of the Maps APIs and probably won't contain all the features you see on the [Google Maps developer site](http://code.google.com/apis/maps).
