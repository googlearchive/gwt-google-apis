# Introduction #

Many charts can fire events, which can be caught and handled by code in the page hosting the chart.  If you are wrapping a chart that generates events, you need to expose those events in your GWT wrapper.  This document shows how to support an existing event in your Visualizaton subclass (such as `select`) or how to wrap a new event.

# The `Selectable` Interface #

Many charts fire the `select` event when the user clicks a component of the visualization.  The GWT Chart Tools API includes special support for the select event.  The wrapper for a chart that supports selection should implement the `Selectable` interface, and use the `Selection` class to implement these three special methods:

```
  public final void addSelectHandler(SelectHandler handler) {
    Selection.addSelectHandler(this, handler);
  }

  public final JsArray<Selection> getSelections() {
    return Selection.getSelections(this);
  }

  public final void setSelections(JsArray<Selection> sel) {
    Selection.setSelections(this, sel);
  }
```

# The Handler Class and its Subclasses #
To support another type of event, you need to write a class to wrap the event.  The event handler class should extend the base class `Handler`.

To illustrate the aspects of wrapping an event, we will look at some code samples from the [PageHandler](http://code.google.com/p/gwt-google-apis/source/browse/trunk/visualization/visualization/src/com/google/gwt/visualization/client/events/PageHandler.java) class.

## The Event Class ##

The event handler class should include an inner class with fields matching the properties of the event.  (The "event" class does not strictly need to be an inner class, but that is the convention in the GWT Chart Tools API.)  The event class should be a "bean" class, in other words, a simple data structure with methods for accessing the data.  Here is an example of an event with a single property, an integer value called "page":

```
  public class PageEvent {
    private int page;
    public PageEvent(int page) { this.page = page; }
    public int getPage() { return page; }
  }
```

An event with more properties would have more fields.

## The Event Callback ##

The event handler class should declare an abstract method for callers to override.  This "callback" method should take a single parameter of the inner event class discussed above.  For example:

```
  public abstract void onPage(PageEvent event);
```

## Implementing the onEvent() Method ##

The event handler class must override the method Handler.onEvent().  onEvent() is called when the event is fired.  The idea of the onEvent() method is to convert a Properties object, which is an "untyped" map of Strings to values, into the fully typed event data structure.  The implementation of onEvent() should extract the event properties from the Properties object, construct an event object with the event properties, and call the event callback method with the event object.  For example:

```
  @Override
  protected void onEvent(Properties properties) {
    // extract the event properties from the "properties" object by name
    int page = properties.getInt("page");

    // construct an event object with the data extracted from the "properties" object
    PageEvent event = new PageEvent(page);

    // call the public abstract "on event" method with the event object
    onPage(event);
  }
```

## The addHandler() Method ##

Strictly speaking, you're done: you don't actually need to touch the wrapper at all.  However, it is good practice to add an addHandler() method to the Visualization wrapper.  This method should simply delegate to Handler.addHandler():
=
```
  public final void addPageListener(PageHandler listener) {
    Handler.addHandler(this, "page", listener);
  }
```