#summary Wrapping an Interactive Chart in GWT

# Interactive Charts and Wrappers #

In order to use an interactive chart in a GWT project, the chart needs a GWT "wrapper" class.  The following chart classes come bundled in the GWT Chart Tools API:

  * [AnnotatedTimeLine](http://code.google.com/apis/visualization/documentation/gallery/annotatedtimeline.html)
  * [ImageAreaChart](http://code.google.com/apis/visualization/documentation/gallery/imageareachart.html)
  * [AreaChart](http://code.google.com/apis/visualization/documentation/gallery/areachart.html)
  * [ImageBarChart](http://code.google.com/apis/visualization/documentation/gallery/imagebarchart.html)
  * [BarChart](http://code.google.com/apis/visualization/documentation/gallery/barchart.html), [ColumnChart](http://code.google.com/apis/visualization/documentation/gallery/columnchart.html)
  * [Gauge](http://code.google.com/apis/visualization/documentation/gallery/gauge.html)
  * [GeoMap](http://code.google.com/apis/visualization/documentation/gallery/geomap.html)
  * [ImageChart](http://code.google.com/apis/visualization/documentation/gallery/genericimagechart.html)
  * [IntensityMap](http://code.google.com/apis/visualization/documentation/gallery/intensitymap.html)
  * [ImageLineChart](http://code.google.com/apis/visualization/documentation/gallery/imagelinechart.html)
  * [LineChart](http://code.google.com/apis/visualization/documentation/gallery/linechart.html)
  * [MapVisualization](http://code.google.com/apis/visualization/documentation/gallery/map.html)
  * [MotionChart](http://code.google.com/apis/visualization/documentation/gallery/motionchart.html)
  * [OrgChart](http://code.google.com/apis/visualization/documentation/gallery/orgchart.html)
  * [ImagePieChart](http://code.google.com/apis/visualization/documentation/gallery/imagepiechart.html)
  * [PieChart](http://code.google.com/apis/visualization/documentation/gallery/piechart.html)
  * [ScatterChart](http://code.google.com/apis/visualization/documentation/gallery/scatterchart.html)
  * [ImageSparklineChart](http://code.google.com/apis/visualization/documentation/gallery/imagesparkline.html)
  * [Table](http://code.google.com/apis/visualization/documentation/gallery/table.html).

However, if you want to use a chart that does not yet have a GWT wrapper, it's easy to write your own. This document describes how.

This document does not deal with supporting events; that subject is treated in [a separate document](VisualizationEventModel.md).

Most of the code samples in this document are lifted from [MotionChart](http://code.google.com/p/gwt-google-apis/source/browse/releases/visualization/1.1/visualization/src/com/google/gwt/visualization/client/visualizations/MotionChart.java) from the GWT Chart Tools API.  This is a simple example of a wrapper class.  You might want to have a quick look at the code before continuing.

## The createJso() Method ##

Every wrapper class must implement a createJso() method.  This method is a [JSNI](http://code.google.com/p/jslibs/wiki/jsffi) call to the constructor of the underlying JavaScript implementation of the chart.  For example:

```
  public JavaScriptObject creatJso(Element parent) /*-{
    return new $wnd.google.visualization.MotionChart(parent);
  }-*/;
```

This JavaScriptObject can be accessed (although this is usually unnecessary) through the `getJso()` method.

## The Options Class ##

Wrappers must extend the class `Visualization<E extends AbstractDrawOptions>`.  E is a class that defines the draw options that can be used with this visualization.  The wrappers provided by the GWT Chart Tools API follow the convention of defining an inner class called Options, and extending Visualization<XXX.Options>.

The options class does not need to be an inner class, but it does need to extend AbstractDrawOptions, which is a subclass of of [JavaScriptObject](http://code.google.com/p/google-web-toolkit-doc-1-5/wiki/DevGuideOverlayTypes) (the same class that is returned from `createJso()`).  JavaScriptObject is a special GWT class that allows GWT Java code to hold references to objects defined in JavaScript.

There are several notable restrictions to keep in mind when working with JavaScriptObject or one its subclasses:

  * **An instance of a JavaScriptObject must be instantiated in JavaScript code, and not in GWT Java.**  To enforce this, GWT forbids public constructors on subclasses of JavaScriptObject.  Therefore, the class must define a protected or private constructor, so that Java does not implicitly define a public constructor.  Instead of a public constructor, concrete subclasses of JavaScriptObject define a JSNI factory method that returns an instance of the class.  For example:

```
  public static Options create(Element parent) {
    return JavaScriptObject.create().cast();
  }
  protected Options() {
  }
```

  * **Polymorphic dispatch is not allowed on methods in JavaScriptObject.**  Thus, either the class or all methods of subclasses of JavaScriptObject must be marked as `final`.  Also, JavaScriptObjects cannot implement Java interfaces for the same reason.

  * **A JavaScript String object maps to a Java String when returned from JSNI, not a JavaScriptObject.**  Running such code may work in web mode, but a project in hosted mode will throw a cast class exception.

  * **JavaScriptObject subclasses cannot contain instance variables.**

## Options Class Setters ##

The Options class should have a setter method for each draw option that the visualization supports.  It's a good idea to write these setters in JSNI. For example, if your visualization supports an integer option named "width", its JSNI method would look like this:

```
    public final native void setWidth(int width) /*-{
      this.width = width;
    }-*/;
```

This is very straightforward for options that expect integers, strings, doubles, and booleans. It's a bit trickier when options expect arrays, dates, or complex JavaScript objects; we will describe that later. A full treatment of sharing objects between Java source and JavaScript can be found [here](http://code.google.com/p/google-web-toolkit-doc-1-5/wiki/DevGuideMarshaling).

In the common case of options that expect simple types, it is also possible to write the setter in pure Java, by calling `AbstractDrawOptions.setOption()`.  However, creating JSNI methods for each property is more efficient.

# Options with Exotic Types #

Creating a setter method for an Options class that takes a array, date, or complex JavaScriptObject requires some extra steps.

## Complex JavaScript Option Types ##

If the option expects a complex JavaScript object, the parameter type should be a subclass of JavaScriptObject.  The best way is to write your own subclass of JavaScriptObject with setters (implemented in JSNI) that reflect the expected structure of the JavaScript object.  A good example is the [Color](http://code.google.com/p/gwt-google-apis/source/browse/releases/visualization/1.1/visualization/src/com/google/gwt/visualization/client/Color.java) class from the GWT Chart Tools API.

Compare the Color code to the specification of the [backgroundColor option](http://code.google.com/apis/visualization/documentation/gallery/areachart.html#Configuration_Options), one of the options that expects a complex object that can be any of the following types:

  * _A string with color supported by HTML, for example 'red' or '#00cc00'_
  * _An object with properties stroke, fill, and strokeSize. The properties stroke and fill should be a string with a color. The property strokeSize is a number. For example: `{backgroundColor: {stroke: 'black', fill: '#eee', strokeSize: 1}}`. To use just fill, without stroke, use stroke: null, strokeSize: 0._

Like all instantiable subclasses of JavaScriptObject, Color has a protected constructor and a static `create()` method.  Color also has setters very similar to the setters of the Options class.

## Array Option Types ##

When a visualization option expects an array, the parameter type should be one of [the GWT JsArray classes](http://google-web-toolkit.googlecode.com/svn/javadoc/2.0/com/google/gwt/core/client/package-summary.html): JsArray, JsArrayBoolean, JsArrayInteger, JsArrayNumber, and JsArrayString.  For example:

```
  public final native void setColors(JsArrayString colors) /*-{
    this.colors = colors;
  }-*/;
```

It's easy to offer your users a more Java-like varargs interface as well, using the `ArrayHelper.toJsArray()` methods, for example:

```
  public final void setColors(String... colors) {
    setColors(ArrayHelper.toJsArray(colors));
  }
```

## Date Option Types ##

The best way to pass a Date into JSNI is to call `getTime()`, cast the returned `long` into a `double` (Java will actually do that "promotion" automatically), and construct a new Date object from the `number` variable in your JSNI.  It's best to call `new $wnd.Date(n)` instead of `new Date(n)`, because the visualization may perform type checking when it implements the options, and a `new Date()` created in JSNI will actually fail a traditional type check.  (This is because the GWT is running in an iframe.)

Here is an example of an option of type Date:

```
    public native void setZoomStartTime(double startTime) /*-{
      this.zoomStartTime = new $wnd.Date(startTime);
    }-*/;
```

You may not want to put a method like that in the public interface of your Options, because what you really want from the caller is a Date, not a double.  So it might be a good idea to make the JSNI method private, and call it from a GWT Java method that accepts a Date.  For example:

```
    public final void setZoomStartTime(Date startTime) {
      nativeSetZoomStartTime(startTime.getTime());
    }

    private native void nativeSetZoomStartTime(double startTime) /*-{
      this.zoomStartTime = new $wnd.Date(startTime);
    }-*/;
```

# Wrapping Methods #

As mentioned before, the underlying JavaScript visualization object can be accessed by calling `getJso()`.  One case where you probably need to call this is when you are wrapping a method.  Here is a code sample that wraps a method called `collapse`, that takes an integer argument and does not return a value:

```

  public void collapse(int row) {
    nativeCollapse(getJso(), row);
  }

  private final native void nativeCollapse(JavaScriptObject jso, int row) /*-{
    jso.collapse(row);
  }-*/;
```

Passing objects between GWT Java and native JavaScript can be tricky; for guidance, see the section on exotic options above.