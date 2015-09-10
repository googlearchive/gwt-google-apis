# Introduction #

This tutorial explains how you can develop a new visualization in GWT and make it available to JavaScript applications.   The source code for this example is included in the visualization distribution in the `samples` folder.

# Writing the GWT visualization #

A GWT based visualization extends the [AbstractVisualization](http://gwt-google-apis.googlecode.com/svn/javadoc/visualization/1.0/com/google/gwt/visualization/client/AbstractVisualization.html) Class. In this example the visualization is called [CustomVisualization](http://code.google.com/p/gwt-google-apis/source/browse/trunk/visualization/samples/customvisualization/src/com/google/gwt/visualization/sample/customvisualization/client/CustomVisualization.java):

```
class CustomVisualization extends AbstractVisualization<CustomVisualization.CustomVisualizationDrawOptions> 
```

The type parameter for [AbstractVisualization](http://gwt-google-apis.googlecode.com/svn/javadoc/visualization/1.0/com/google/gwt/visualization/client/AbstractVisualization.html) is a subclass of [AbstractDrawOptions](http://gwt-google-apis.googlecode.com/svn/javadoc/visualization/1.0/com/google/gwt/visualization/client/AbstractDrawOptions.html) and specifies the class which stores the drawing options.  For example a configurable background color:

```
  public static class CustomVisualizationDrawOptions extends AbstractDrawOptions {
    protected CustomVisualizationDrawOptions() {
    }
    
    public final native String getBackgroundColor() /*-{
      return this.backgroundColor || "white";
    }-*/;
    
    public final native void setBackgroundColor(String color) /*-{
      this.backgroundColor = color;
    }-*/;
  }
```

> _Note: There are a few AbstractDrawOptions sublcasses that you can choose to implement already pre-defined in the Visualization API, such as [CommonOptions](http://gwt-google-apis.googlecode.com/svn/javadoc/visualization/1.0/com/google/gwt/visualization/client/CommonOptions.html) and [CommonChartOptions](http://gwt-google-apis.googlecode.com/svn/javadoc/visualization/1.0/com/google/gwt/visualization/client/CommonChartOptions.html)._

A class which extends AbstractVisualization must implement the draw() method.  It is the draw() method's responsibity to take the provided data and draw the visualization in the browser window.  An [AbstractDataTable](http://gwt-google-apis.googlecode.com/svn/javadoc/visualization/1.0/com/google/gwt/visualization/client/AbstractDataTable.html) and the drawing options are provided as inputs. In the example a [FlexTable](http://google-web-toolkit.googlecode.com/svn/javadoc/1.5/com/google/gwt/user/client/ui/FlexTable.html) is used to display the data values in a grid.

```
  private final FlexTable grid = new FlexTable();
  private int selectedColumn = 0;
  private int selectedRow = 0;
  
  public CustomVisualization() {
    initWidget(grid);
  }

@Override
  public void draw(AbstractDataTable dataTable, CustomVisualizationDrawOptions options) {
 grid.clear();
    // Apply the drawing options
    if (options != null) {
      grid.getElement().getStyle().setProperty("backgroundColor", options.getBackgroundColor());
    }
         
    for (int c = 0; c < dataTable.getNumberOfColumns(); c++) {
      grid.setHTML(0, c,"<b>" + dataTable.getColumnLabel(c) + "</b>");
    }
    
    for (int r = 0; r < dataTable.getNumberOfRows(); r++) {
      for (int c = 0; c < dataTable.getNumberOfColumns(); c++) {
        grid.setText(r + 1, c, dataTable.getFormattedValue(r, c));
      } 
    }
  }
```

Now that we have a visualization implemented in GWT, the next step is to make it available to JavaScript. This can be done with the [VisualizationFactory](http://gwt-google-apis.googlecode.com/svn/javadoc/visualization/1.0/com/google/gwt/visualization/client/AbstractVisualization.VisualizationFactory.html) class.

```
  private final VisualizationFactory factory = new VisualizationFactory() {
    
    public AbstractVisualization<?> create() {
     return new CustomVisualization();
    }
   };
   
  public void onModuleLoad() {
    // Register the visualization
    AbstractVisualization.registerVisualization("CustomVisualization", factory);
   }
```

This code will register a JavaScript visualization named "CustomVisualization". If such a visualization is created in JavaScript, the create() method is called to instantiate the GWT visualization.

# Selection Handling #

Selection handling is an optional API that some visualizations support. If the GWT visualization should support selection, the [Selectable](http://gwt-google-apis.googlecode.com/svn/javadoc/visualization/1.0/com/google/gwt/visualization/client/Selectable.html) interface needs to be implemented:

```
 class CustomVisualization extends AbstractVisualization<CustomVisualization.CustomVisualizationDrawOptions> 
    implements SelectionMethods {

  public Selection getSelection() {
    return SelectionHelper.createSelection(selectedRow, selectedColumn);
  }

  public void setSelection(Selection sel) {
    Window.alert("selection changed");
  }

```

# Using a Visualization in JavaScript #
The GWT visualization is now usable from JavaScript like any other visualization:

```
 <div id="mydiv"></div>
 
 <script type="text/javascript">
   function drawVisualization() {
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Name');
        data.addColumn('number', 'Salary');
        data.addColumn('boolean', 'Full Time');
        data.addRows(5);
        data.setCell(0, 0, 'John');
        data.setCell(0, 1, 10000, '$10,000');
        data.setCell(0, 2, true);
        data.setCell(1, 0, 'Mary');
        data.setCell(1, 1, 25000, '$25,000');
        data.setCell(1, 2, true);
        data.setCell(2, 0, 'Steve');
        data.setCell(2, 1, 8000, '$8,000');
        data.setCell(2, 2, false);
        data.setCell(3, 0, 'Ellen');
        data.setCell(3, 1, 20000, '$20,000');
        data.setCell(3, 2, true);
        data.setCell(4, 0, 'Mike');
        data.setCell(4, 1, 12000, '$12,000');
        data.setCell(4, 2, false);

        var vis = new CustomVisualization(document.getElementById('mydiv'));
        vis.draw(data, {backgroundColor: "#eeeeee"});
      }
 </script>
```

# Loading the Visualization #

<a href='Hidden comment: 
TODO (zundel): This might change based on our AJAXLoader class.
'></a>
The Google Visualization API and the GWT code are both loaded asynchronously.  The loading of the modules needs to be finished before they can be used. An easy way to ensure this is to use a counter and a callback function to keep track of the module loading.

```
    <script type="text/javascript">
      // Two modules to load (Google Visualization API and the GWT CustomVisualization)
      var num_modules = 2;
      
      // This function will be called after each module was loaded.
      function onLoadCallback() {
    	  // Render the visualization if this was the last module.
        if (--num_modules == 0) { 
        	drawVisualization();   
        }
      }
 </script>
```

The modules need to call this callback after they finished loading. For the Google Visualization API this is done with setOnLoadCallback:

```
 <script language="javascript" src="http://www.google.com/jsapi"></script>
 <script type="text/javascript">
 google.load("visualization", "1");
 google.setOnLoadCallback(onLoadCallback);
 </script>
 <script language="javascript" src="com.google.gwt.visualization.sample.customvisualization.CustomVisualization.nocache.js"></script>
```

In GWT we can just use a [JSNI](http://code.google.com/webtoolkit/doc/latest/DevGuideCodingBasicsJSNI.html) function that is executed after the visualization was registered:

```
 public void onModuleLoad() {
    // Register the visualization
    AbstractVisualization.registerVisualization("CustomVisualization", factory);
    callOnLoadCallback(GWT.getModuleName());
  }

  private static native void callOnLoadCallback(String name) /*-{
    if ($wnd.onLoadCallback != undefined) {
    	$wnd.onLoadCallback(name);
    } 
  }-*/;
```

If you plan to make your visualization available to applications which run on other domains than the domain that serves your visualization you need to compile using the cross site linker ( [XSLinker](http://code.google.com/webtoolkit/doc/latest/DevGuideOrganizingProjects.html#DevGuideModuleXml)).

This can be done by adding this line the [CustomVisualization.gwt.xml](http://code.google.com/p/gwt-google-apis/source/browse/trunk/visualization/samples/customvisualization/src/com/google/gwt/visualization/sample/customvisualization/CustomVisualization.gwt.xml).

```
  <add-linker name="xs" />
```

# Links #
  * The [source code of this example](http://code.google.com/p/gwt-google-apis/source/browse/trunk/visualization/samples/customvisualization/src/com/google/gwt/visualization/customvisualization/)