#summary Getting Started Using the Google Chart Tools with GWT

# Getting Started with Chart Tools #

## Assumptions ##

  * You are familiar with [Google Web Toolkit](http://code.google.com/webtoolkit/overview.html).
  * You know how to [create a new GWT project](http://code.google.com/webtoolkit/gettingstarted.html).
  * You are familiar with [Google Chart Tools API](http://code.google.com/apis/charttools/).
  * You are using the Chart Tools (Visualization) API Library for GWT version 1.1.0 or later.
  * You are using GWT 2.0 or later.
  * You have installed Eclipse and/or Apache Ant.

## Downloading the Chart Tools API Library for GWT ##

You can download the latest production releases of the libraries from the [project download page](http://code.google.com/p/gwt-google-apis/downloads/). After you download the distribution, uncompress it.  Inside the folder you will find a .jar file named `gwt-visualization.jar`. This .jar file is the only thing you'll need from the distribution.

You can reference the `.jar` file either from the folder that you uncompressed the project in, or copy it and reference it from another location.  In this example, we've chosen to copy the file to the path `/usr/local/gwt-visualization`.

## Setting up a GWT Project to use the Chart Tools API ##

There are three steps needed to use the gwt-visualization API in a GWT project:

  1. Include the library JAR file in the classpath
  1. Inherit com.google.gwt.visualization.Visualization in the module XML definition file.

We'll explain each step as we set up a new project.

## Creating a New GWT Project ##

Start by creating a new GWT project called SimpleViz as described in the
[GWT Plugin Guide](http://code.google.com/eclipse/docs/creating_new_webapp.html).

Since we are working with an additional library, add the library `gwt-visualization.jar` to the Java classpath.  Then, add the module `com.google.gwt.visualization.Visualization` to your module.

```
  <inherits name='com.google.gwt.visualization.Visualization'/>
```

For better effect please remove the .css reference from the SimpleViz.html page and remove all HTML tags in the body.

## A Simple Interactive Chart ##

Replace the contents of SimpleViz.java with the following:

```
package com.example.simpleviz.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.Selection;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.events.SelectHandler;
import com.google.gwt.visualization.client.visualizations.PieChart;
import com.google.gwt.visualization.client.visualizations.PieChart.Options;

public class SimpleViz implements EntryPoint {
  public void onModuleLoad() {
    // Create a callback to be called when the visualization API
    // has been loaded.
    Runnable onLoadCallback = new Runnable() {
      public void run() {
        Panel panel = RootPanel.get();
 
        // Create a pie chart visualization.
        PieChart pie = new PieChart(createTable(), createOptions());

        pie.addSelectHandler(createSelectHandler(pie));
        panel.add(pie);
      }
    };

    // Load the visualization api, passing the onLoadCallback to be called
    // when loading is done.
    VisualizationUtils.loadVisualizationApi(onLoadCallback, PieChart.PACKAGE);
  }

  private Options createOptions() {
    Options options = Options.create();
    options.setWidth(400);
    options.setHeight(240);
    options.set3D(true);
    options.setTitle("My Daily Activities");
    return options;
  }

  private SelectHandler createSelectHandler(final PieChart chart) {
    return new SelectHandler() {
      @Override
      public void onSelect(SelectEvent event) {
        String message = "";
        
        // May be multiple selections.
        JsArray<Selection> selections = chart.getSelections();

        for (int i = 0; i < selections.length(); i++) {
          // add a new line for each selection
          message += i == 0 ? "" : "\n";
          
          Selection selection = selections.get(i);

          if (selection.isCell()) {
            // isCell() returns true if a cell has been selected.
            
            // getRow() returns the row number of the selected cell.
            int row = selection.getRow();
            // getColumn() returns the column number of the selected cell.
            int column = selection.getColumn();
            message += "cell " + row + ":" + column + " selected";
          } else if (selection.isRow()) {
            // isRow() returns true if an entire row has been selected.
            
            // getRow() returns the row number of the selected row.
            int row = selection.getRow();
            message += "row " + row + " selected";
          } else {
            // unreachable
            message += "Pie chart selections should be either row selections or cell selections.";
            message += "  Other visualizations support column selections as well.";
          }
        }
        
        Window.alert(message);
      }
    };
  }

  private AbstractDataTable createTable() {
    DataTable data = DataTable.create();
    data.addColumn(ColumnType.STRING, "Task");
    data.addColumn(ColumnType.NUMBER, "Hours per Day");
    data.addRows(2);
    data.setValue(0, 0, "Work");
    data.setValue(0, 1, 14);
    data.setValue(1, 0, "Sleep");
    data.setValue(1, 1, 10);
    return data;
  }
}
```

Now you can build your project again, and open `war/SimpleViz.html` in your favorite browser.

> ![http://gwt-google-apis.googlecode.com/svn/wiki/SimpleViz-1.png](http://gwt-google-apis.googlecode.com/svn/wiki/SimpleViz-1.png)

Congratulations! You should now have an interactive chart working within a GWT application.

## What Just Happened? ##

Let's take a closer look at SimpleViz.

### Calling the Ajax Loader ###

In order to load the Google Chart Tools API into your GWT module, you need to invoke the Google Ajax Loader.  This is done with the static methods of the AjaxLoader class.  To load the API, you need to provide a Java Runnable object to be executed when the API is loaded, along with a list of charts to be loaded.  Visualizations provided by Google come with a constant named `PACKAGE` that should be used in order to load them.  For example:

```
  public void onModuleLoad() {
    // Create a callback to be called when the visualization API
    // has been loaded.
    Runnable onLoadCallback = new Runnable() {
      public void run() {
        Panel panel = RootPanel.get();
 
        // Create a pie chart visualization.
        PieChart pie = new PieChart(createTable(), createOptions());

        pie.addSelectHandler(createSelectHandler(pie));
        panel.add(pie);
      }
    };

    // Load the visualization api, passing the onLoadCallback to be called
    // when loading is done.
    VisualizationUtils.loadVisualizationApi(onLoadCallback, PieChart.PACKAGE);
  }
```

### Creating Draw Options ###

The properties of the Options class for each chart correspond to the supported names and value types defined in the [documentation](http://code.google.com/apis/visualization/documentation/gallery.html) of each specific chart.  In the GWT Chart Tools API, each chart has its own Options class.  These classes are subclasses of JavaScriptObject, so they cannot be instantiated with the _new_ operator.  Instead, they are created with the static method Options.create().

```
  private Options createOptions() {
    Options options = Options.create();
    options.setWidth(400);
    options.setHeight(240);
    options.set3D(true);
    options.setTitle("My Daily Activities");
    return options;
  }
```

For the purpose of forward compatibility, the class AbstractDrawOptions (which is the base class of all Options classes) also includes some general-purpose `set()` methods.  This allows the caller to set a new option that has not yet been incorporated into their version of the Options class.  For example:

```
options.set("nlives", 10);
```

### Creating a DataTable ###

For all charts, the data is created using the [DataTable](http://code.google.com/apis/visualization/documentation/reference.html#DataTable) class or the [DataView](http://code.google.com/apis/visualization/documentation/reference.html#DataView) class. A DataTable is a two dimensional table with rows and columns and cells. As with the Options classes, the `DataTable` class is a subclass of `JavaScriptObject`, so new instances are created by calling the static method `DataTable.create()`.  Each column has a data type defined by the `DataTable.ColumnType` enum.

```
  private AbstractDataTable createTable() {
    DataTable data = DataTable.create();
    data.addColumn(ColumnType.STRING, "Task");
    data.addColumn(ColumnType.NUMBER, "Hours per Day");
    data.addRows(2);
    data.setValue(0, 0, "Work");
    data.setValue(0, 1, 14);
    data.setValue(1, 0, "Sleep");
    data.setValue(1, 1, 10);
    return data;
  }
```

### Creating a DataView ###

To provide a read-only view of a DataTable, you can bind a [DataView](http://code.google.com/apis/visualization/documentation/reference.html#DataView) object to your DataTable. `DataView` enables you to expose a subset of columns and show columns in a different order.  For example, instead of returning a DataTable from createTable(), we can return a DataView:

```
  private AbstractDataTable createTable() {
    // Underlying data
    DataTable data = DataTable.create();
    data.addColumn(ColumnType.STRING, "Task");
    data.addColumn(ColumnType.STRING, "Location");
    data.addColumn(ColumnType.NUMBER, "Hours per Day");
    data.addRows(3);
    data.setValue(0, 0, "Work");
    data.setValue(0, 1, "Mountain View");
    data.setValue(0, 2, 10);
    data.setValue(1, 0, "Commute");
    data.setValue(1, 1, "Route 17");
    data.setValue(1, 2, 4);
    data.setValue(2, 0, "Sleep");
    data.setValue(2, 1, "Santa Cruz");
    data.setValue(2, 2, 10);

    // Data view -- read only, and no location column
    DataView result = DataView.create(data);
    result.setColumns(new int[]{0, 2});
    return result;
  }
```

### Issuing a Query ###

A data table can also be obtained from a data source that has been configured to operate with Google Chart Tools (for example, Google Spreadsheets), using the [Query](http://code.google.com/p/gwt-google-apis/source/browse/releases/visualization/1.1/visualization/src/com/google/gwt/visualization/client/Query.java) class.  The `Query.send()` method takes a `Query.Callback` object that specifies what to do with the QueryResponse.  If the QueryResponse is not an error, you can call `QueryResponse.getDataTable()` and use the resulting DataTable to draw the chart. For example:

```
    String dataUrl = "http://spreadsheets.google.com/tq?"
      + "key=pWiorx-0l9mwIuwX5CbEALA&range=A1:B12&gid=0&headers=-1";

    // Create a query to go to the above URL.
    Query query = Query.create(dataUrl);

    // Send the query.
    query.send(new Callback() {
      public void onResponse(QueryResponse response) {
        if (response.isError()) {
          Window.alert(response.getMessage());
        } else {
          // Get the data from the QueryResponse.
          DataTable data = response.getDataTable();

          // Create the Options object.
          Options options = createOptions();

          // Create a PieChart and add it to a panel.
          panel.add(new PieChart(data, options));
        }
      }
    });
```

> ![http://gwt-google-apis.googlecode.com/svn/wiki/SimpleVizQuery-1.png](http://gwt-google-apis.googlecode.com/svn/wiki/SimpleVizQuery-1.png)

### Creating a SelectHandler ###

Handlers are created for Visualizations very similarly to how listeners are created for widgets.  Visualization subclasses define "addXXXHandler" methods that take a handler for some event.  Many visualizations support SelectHandler and SelectEvent. Here is an example of a SelectHandler implementation:

```
  private SelectHandler createSelectHandler(final PieChart chart) {
    return new SelectHandler() {
      @Override
      public void onSelect(SelectEvent event) {
        String message = "";
        
        // May be multiple selections.
        JsArray<Selection> selections = chart.getSelections();

        for (int i = 0; i < selections.length(); i++) {
          // add a new line for each selection
          message += i == 0 ? "" : "\n";
          
          Selection selection = selections.get(i);

          if (selection.isCell()) {
            // isCell() returns true if a cell has been selected.
            
            // getRow() returns the row number of the selected cell.
            int row = selection.getRow();
            // getColumn() returns the column number of the selected cell.
            int column = selection.getColumn();
            message += "cell " + row + ":" + column + " selected";
          } else if (selection.isRow()) {
            // isRow() returns true if an entire row has been selected.
            
            // getRow() returns the row number of the selected row.
            int row = selection.getRow();
            message += "row " + row + " selected";
          } else {
            // unreachable
            message += "Pie chart selections should be either row selections or cell selections.";
            message += "  Other visualizations support column selections as well.";
          }
        }
        
        Window.alert(message);
      }
    };
  }
```

## Further Reading ##

Learn all about wrapping [new visualizations](VisualizationNewWrapper.md) and [new events](VisualizationEventModel.md) for use in GWT code.