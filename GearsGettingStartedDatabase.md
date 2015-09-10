# Using the Gears Database Module in a GWT project #

## Creating a new GWT project ##

Start by creating a new GWT project called GearsDatabaseTutorial as described in the [GWT Plugin Guide](http://code.google.com/eclipse/docs/creating_new_webapp.html).

Since we are working with an additional library, add the library `gwt-gears.jar` to the Java classpath.  Then, add the module `com.google.gwt.gears.Gears` to your module.

```
    <inherits name='com.google.gwt.gears.Gears' />
```

## Update the HTML host file ##

Replace the body of the HTML host file `com/example/google/gwt/gearsdatabasetutorial/public/GearsDatabaseTutorial.html` with a `<div>` tag that we can use for the GWT application.

```
<body>

    <h1>GearsDatabaseTutorial</h1>

    <div id="gearsTutorial">

</body>
```

## Use a Database object in the  .java source ##

To complete the `GearsDatabaseTutorial.java`, you can replace the `onModuleLoad()` with the following methods that create a database, insert data, and read from a simple database.

```
package com.example.google.gwt.gearsdatabasetutorial.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.gears.client.Factory;
import com.google.gwt.gears.client.GearsException;
import com.google.gwt.gears.client.database.Database;
import com.google.gwt.gears.client.database.DatabaseException;
import com.google.gwt.gears.client.database.ResultSet;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.RootPanel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A demonstration of how to use Gears' Database feature.
 */
public class GearsDatabaseTutorial implements EntryPoint {
  private Database db;
  
  /**
   * Each time the application is reloaded, a new entry will be added to the database.
   */
  public void onModuleLoad() {

    // Create the database if it doesn't exist.
    try {
      db = Factory.getInstance().createDatabase();
      db.open("gears-database-tutorial");
      // The 'int' type will store up to 8 byte ints depending on the magnitude of the 
      // value added.
      db.execute("create table if not exists tutorial (Timestamp int)");
    } catch (GearsException e) {
      Window.alert(e.toString());
    }
    
    // Add an entry to the database
    try {
      db.execute("insert into tutorial values (?)", new String[] {
          Long.toString(System.currentTimeMillis())});
    }
    catch (DatabaseException e) {
      Window.alert(e.toString());
    }
  
    // Fetch previous results from the database.
    List<String> timestamps= new ArrayList<String>();
    try {
      ResultSet rs = db.execute("select * from tutorial order by Timestamp");
      for (int i = 0; rs.isValidRow(); ++i, rs.next()) {
        timestamps.add(rs.getFieldAsString(0));
      }
      rs.close();
    } catch (DatabaseException e) {
      Window.alert(e.toString());
    }
    
    // Display the list of results in a table
    Grid grid = new Grid(timestamps.size() + 1, 1);
    grid.setText(0, 0, "Accesses to this Page:");
    for (int row = 0; row < timestamps.size(); ++row) {
      Date stamp = new Date(Long.valueOf(timestamps.get(row)));
      grid.setText(row + 1, 0, stamp.toString()); 
    }

    RootPanel.get("gearsTutorial").add(grid);
  }
}
```


## How to deal with browsers that do not support Gears ##

The Gears plugin is a component that must be installed by users and is not supported on all browser configurations.  Thus, you will want to let users know when your application detects that the Gears plugin is not installed or not supported.  You can create a rebind rule in your GWT module that will invoke a different EntryPoint in this instance.

### Add a new EntryPoint class ###

The following is an example of an alternative EntryPoint class is used when the Gears plugin is not detected in the browser.  Save this class as `NoGears.java` in the same package with the `GearsDatabaseTutorial` class.

```
package com.example.google.gwt.gearsdatabasetutorial.client;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * This entry point will be used when no Gears plugin is detected
 */
public class NoGears {
  public void onModuleLoad() {
    RootPanel rootPanel = RootPanel.get();
    rootPanel.add(new HTML(
        "<font color=\"red\">ERROR: This browser does not support Gears. "
        + " Please <a href=\"http://gears.google.com/\">install Gears</a> " 
        + "and reload the application.  Note that GWT Gears applications can "
        + "only be debugged in development mode on Windows.</font>"));
  }
}
```

### Modify GearsDatabaseTutorial.gwt.xml ###

In order to tell the compiler to add logic to the selection script to pull up your rebound entry point, you need to create a [replace-class-with](http://code.google.com/webtoolkit/doc/latest/DevGuideCodingBasicsDeferred.html#replacement) rule in your `gwt.xml` module file.

```
  <!-- Rebind the entry point if Gears is not installed -->
  <replace-with class="com.example.google.gwt.gearsdatabasetutorial.client.NoGears">
    <when-type-is class="com.example.google.gwt.gearsdatabasetutorial.client.GearsDatabaseTutorial"/>
    <when-property-is name="gears.installed" value="false"/>
  </replace-with>
```

## Run the GearsDatabaseTutorial sample project ##

Now, you should be able to execute your sample project in development mode by either running _ant devmode_ from the command line or using the _Run_ configuration from Eclipse.

> _Note: Using devmode mode with the Gears project on Windows will not work with Chrome, please use another browser_