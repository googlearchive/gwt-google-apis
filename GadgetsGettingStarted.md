#summary How to write and debug a Gadget using GWT

# Getting Started with Gadgets and GWT #

The gadgets library for GWT simplifies gadget development by automatically generating a [Gadget Specification](http://code.google.com/apis/gadgets/docs/reference.html#XML_Ref) from Java source, inserting a selection script in the specification much like a regular GWT project.  After compiling your gadget with GWT, all files are in place to [publish](http://code.google.com/apis/gadgets/docs/publish.html) your gadget.

Release 1.2 supports the `gadget.*` namespace ([Open Social](http://wiki.opensocial.org/) gadgets specification).  Releases prior to 1.2 only support the [legacy gadgets API](http://code.google.com/apis/gadgets/docs/legacy/dev_guide.html) based on the `_IG_*` namespace.

This guide will get you started writing your first gadget.  Before you start, familiarize yourself with [iGoogle](http://www.google.com/ig) and the [gadgets developer site](http://code.google.com/apis/gadgets/docs/dev_guide.html).


## Assumptions ##

  * You are already familiar with [Google Web Toolkit](http://code.google.com/webtoolkit/overview.html)
  * You know how to [create a new GWT project.](http://code.google.com/eclipse/docs/creating_new_webapp.html)
  * You are using GWT 2.0 or later.

## Downloading the gadget library for GWT ##

You can download the latest release of the library from the [project download page](http://code.google.com/p/gwt-google-apis/downloads/). After you download the distribution, uncompress it.  Inside the folder you will find a .jar file named `gwt-gadgets.jar`.

You can either reference the `.jar` file from the folder that you uncompressed the project in, or copy it to another location, such as the location of your GWT distribution (containing `gwt-dev.jar` and `gwt-user.jar` files).  In this example, we've chosen to copy the file to the path `/usr/local/gadgets`.

## Differences between a standard GWT project and a GWT gadget project ##

Look at the sample program _Hello Gadgets_ that ships with the `gwt-gadgets` distribution

> [HelloGadgets.java](http://code.google.com/p/gwt-google-apis/source/browse/releases/gadgets/1.2/samples/hellogadgets/src/com/google/gwt/gadgets/sample/hellogadgets/client/HelloGadgets.java)

There are some differences you will notice from a standard GWT project.

  1. There is no class that implements the [EntryPoint](http://google-web-toolkit.googlecode.com/svn/javadoc/2.0/com/google/gwt/core/client/EntryPoint.html) interface.
  1. The entry point class is a subclass of [Gadget](http://gwt-google-apis.googlecode.com/svn/javadoc/gadgets/1.2/com/google/gwt/gadgets/client/Gadget.html).
  1. There is a `@ModulePrefs` annotation on the class
  1. There is a relationship between the [Gadget](http://gwt-google-apis.googlecode.com/svn/javadoc/gadgets/1.2/com/google/gwt/gadgets/client/Gadget.html) subclass and the [UserPreferences](http://gwt-google-apis.googlecode.com/svn/javadoc/gadgets/1.2/com/google/gwt/gadgets/client/UserPreferences.html) subclass named `HelloPreferences`.


These differences derive from the fact that a gadget must adhere to a specific framework as described in the [gadget developer's documentation](http://code.google.com/apis/gadgets/docs/dev_guide.html).  To meet this requirement, gadget support is implemented using a custom [GWT Linker](http://google-web-toolkit.googlecode.com/svn/javadoc/2.0/com/google/gwt/core/ext/Linker.html).

## Creating a new GWT Project ##

Start by creating a new GWT project called SimpleGadget as described in the [Google Plugin for Eclipse user's guide.](http://code.google.com/eclipse/docs/creating_new_webapp.html)

Since we are working with an additional library, add `gwt-gadgets.jar` to the Java classpath.  Then, add the inherits line for `com.google.gwt.gadgets.Gadgets` to your module.

```
    <inherits name='com.google.gwt.gadgets.Gadgets' />
```

### Modify the Project ###

Start by opening up `SimpleGadget.java` and changing the class declaration to read:

```
import com.google.gwt.gadgets.client.Gadget;
import com.google.gwt.gadgets.client.UserPreferences;

   public class SimpleGadget extends Gadget<UserPreferences> 
```

Remove the `onModuleLoad()` method.  No constructor is necessary.

Next, you need to implement the `init(UserPreferences)` method from the `Gadget` class.  This sample implements a very simple body for the Gadget.

```
  @Override
  protected void init(UserPreferences preferences) {
    Button simpleButton = new Button("SimpleGadget");
    simpleButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        Window.alert("Hello World!");
      }
    });
    RootPanel.get().add(simpleButton);
  }
```


Finally, above the class statement, add an annotation to set the gadget title, author and email:

```
@ModulePrefs(title = "SimpleGadget", author = "yournamehere", author_email = "yournamehere@gmail.com")
```

[@ModulePrefs](http://gwt-google-apis.googlecode.com/svn/javadoc/gadgets/1.2/com/google/gwt/gadgets/client/Gadget.ModulePrefs.html) provides attributes to the [`&lt;ModulePrefs&gt;`](http://code.google.com/apis/gadgets/docs/reference.html#Moduleprefs_Ref) tag in the Gadget Specification.

These two annotations are currently mandatory and control what the output name of the gadget spec looks like and whether or not HTML quirks mode is turned on.

```
@UseLongManifestName(false)
@AllowHtmlQuirksMode(false)
```

## Compiling your Gadget ##

You now have the basics of a Gadget in place, but there are a few more steps required to run the Gadget, so let's compile and deploy what we have to a Gadget container like iGoogle.

Use the Google Plugin for Eclipse to build your project in Web mode. The output of the compiler will have:
  * Several `.cache.js` files, one per compiler permutation.  These contain the application logic for your gadget compiled for each browser/locale combination.
  * A `SampleGadgets.html` file.  This is just a holdover from a regular GWT module and is not used in publishing a gadget.
  * Other resources such as image files.
  * A `*.xml` file.  This is the gadget specification file that you will need in order to [publish your gadget](http://code.google.com/apis/gadgets/docs/publish.html). It contains:
    * Information about your gadget specified in `@ModulePrefs`
    * Which features your gadget needs
    * A user preference value specification
    * JavaScript code for initializing the Gadget API and loading one of the GWT compiler permutations.

## Uploading your Gadget to iGoogle ##

To test our your newly compiled code, take the output files above (located in the `SimpleGadget/war` directory unless you changed GWT's output location) and upload them all to the same directory on a public web server.

Next, go to [iGoogle](http://www.google.com/ig) and click the "Add Stuff" link in the upper right corner. Click "Add Feed or Gadget" and specify the full URL to the gadget spec file (`SimpleGadget.gadget.xml`) that you uploaded to your server in the previous step. Click "Add" and go back to your iGoogle page.

Congratulations. Your new (if slightly boring) Gadget should  appear on the page.

**Debugging Gadgets in Development Mode is not supported in the current release.**

### Caching effects from the Gadget container ###

One important aspect of the Gadget infrastructure you must be aware of is that Gadget containers, such as iGoogle, are free to cache the Gadget specifications.  In the case of iGoogle, the default caching is one hour.  See FAQ item [I updated my gadget, but I can't see my changes. What's going on?](http://code.google.com/apis/gadgets/faq.html#CantSeeChanges)

As a developer, you can use the [Developer Gadget](http://www.google.com/ig/directory?type=gadgets&url=www.google.com/ig/modules/developer.xml) on iGoogle to turn off caching for your view of the gadget by un-checking the `Cached` checkbox beside the URL of your Gadget Spec.

For deploying a gadget developed with GWT, the caching by Gadget Containers is a significant issue. If you deploy your GWT application and remove old versions of the compiled program, the cached version of the Gadget spec will still refer to the old (and now deleted) files and end users will experience an outage until the cache is refreshed.

## Adding container features ##

If the Gadget requires access to features of the container, it should implement the appropriate _NeedsFoo_ interfaces, which currently include:

  * [NeedsAnalytics](http://gwt-google-apis.googlecode.com/svn/javadoc/gadgets/1.2/com/google/gwt/gadgets/client/NeedsAnalytics.html)
  * [NeedsDynamicHeight](http://gwt-google-apis.googlecode.com/svn/javadoc/gadgets/1.2/com/google/gwt/gadgets/client/NeedsDynamicHeight.html)
  * [NeedsIntrinsics](http://gwt-google-apis.googlecode.com/svn/javadoc/gadgets/1.2/com/google/gwt/gadgets/client/NeedsIntrinsics.html)
  * [NeedsSetPrefs](http://gwt-google-apis.googlecode.com/svn/javadoc/gadgets/1.2/com/google/gwt/gadgets/client/NeedsSetPrefs.html)
  * [NeedsSetTitle](http://gwt-google-apis.googlecode.com/svn/javadoc/gadgets/1.2/com/google/gwt/gadgets/client/NeedsSetTitle.html)


At runtime, all feature setters will be called strictly before [init(UserPreferences)](http://gwt-google-apis.googlecode.com/svn/javadoc/gadgets/1.2/com/google/gwt/gadgets/client/Gadget.html#init(T)). The order in which the setters are called is undefined.


For example, if you wanted to use the optional feature to set the title dynamically, you would need to inherit the `NeedsSetTitle` interface. The following code sets the title of the gadget dynamically.

```
/**
 * Sample gadget from the Getting Started manual.
 */
@ModulePrefs(title = "Original Title", author = "yournamehere", author_email = "yournamehere@gmail.com")
@UseLongManifestName(false)
@AllowHtmlQuirksMode(false)
public class SimpleGadget extends Gadget<UserPreferences> implements
    NeedsSetTitle {
  int titleIndex = 0;
  String[] titles = {"NeedsTitle Example", "Hello World!", "Goodbye World!"};
  SetTitleFeature titleFeature;

  @Override
  protected void init(UserPreferences preferences) {
    Button changeTitleButton = new Button("Change Title");
    changeTitleButton.addClickHandler(new ClickHandler() {

      public void onClick(ClickEvent event) {
        titleFeature.setTitle(titles[titleIndex++ % titles.length]);
      }

    });
    RootLayoutPanel.get().add(changeTitleButton);
  }

  @Override
  public void initializeFeature(SetTitleFeature feature) {
    this.titleFeature = feature;
  }
}
```

Here's the resulting Gadget:

> ![http://gwt-google-apis.googlecode.com/svn/wiki/GettingStartedGadgets-ChangeTitle.png](http://gwt-google-apis.googlecode.com/svn/wiki/GettingStartedGadgets-ChangeTitle.png)


## Specifying Preferences ##

The gadget spec allows you to specify user preferences for your gadget.  User preferences allow the gadget container to provide a user interface and storage for parameters used to customize the gadget.

In GWT, access to user preferences is provided through a user-defined subtype of the [UserPreferences](http://gwt-google-apis.googlecode.com/svn/javadoc/gagets/1.2/com/google/gwt/gadgets/client/UserPreferences.html) interface. Each preference should be defined as a zero-argument method, returning the desired type of [UserPreferences.Preference Preference](http://gwt-google-apis.googlecode.com/svn/javadoc/gagets/1.2/com/google/gwt/gadgets/client/UserPreferences.Preference.html). The Gadget type should be parameterized with the specific [UserPreferences](http://gwt-google-apis.googlecode.com/svn/javadoc/gagets/1.2/com/google/gwt/gadgets/client/UserPreferences.html) subtype, which will be provided to the [init(UserPreferences)](http://gwt-google-apis.googlecode.com/svn/javadoc/gagets/1.2/com/google/gwt/gadgets/client/Gadget.html#init(T)) method.


The following sample code shows how to implement a simple checkbox type of preference.  In this sample, the gadget displays a different menu depending on whether the user checks the _Vegetarian_ checkbox.  First, the custom `UserPreference` subclass:

```
import com.google.gwt.gadgets.client.BooleanPreference;
import com.google.gwt.gadgets.client.UserPreferences;

public interface MealPreferences extends UserPreferences {

  @PreferenceAttributes(display_name = "Vegetarian", default_value = "false")
  BooleanPreference noMeat();

}
```

Next, a `Gadget` class that uses the `MealPreferences` class.  The meal choices are displayed in a table - when the user selects a dish, an alert window is opened.

```
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.gadgets.client.Gadget;
import com.google.gwt.gadgets.client.Gadget.AllowHtmlQuirksMode;
import com.google.gwt.gadgets.client.Gadget.ModulePrefs;
import com.google.gwt.gadgets.client.Gadget.UseLongManifestName;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Sample gadget from the Getting Started manual.
 */
@ModulePrefs(title = "MealPreferences Gadget", author = "yournamehere", author_email = "yournamehere@gmail.com")
@UseLongManifestName(false)
@AllowHtmlQuirksMode(false)
public class MealPreferencesGadget extends Gadget<MealPreferences> {
  private String[] meatEntrees = {
      "steak kabobs", "lamb fricassee", "chicken kiev"};
  private String[] vegEntrees = {"saag paneer", "baba ganoush", "boca burger"};
  private String[] dishes;

  @Override
  protected void init(MealPreferences preferences) {
    initDishes(preferences);

    // Create a table with a checklist of all available dishes based on the
    // user's dietary preferences.
    Panel p = new DockLayoutPanel(Unit.PX);
    p.setWidth("100%");

    FlexTable ft = new FlexTable();

    int index = 0;
    for (String dish : dishes) {
      CheckBox cb = new CheckBox();
      final String dishCopy = dish;
      cb.addClickHandler(new ClickHandler() {
        public void onClick(ClickEvent event) {
          Window.alert("Your order of " + dishCopy + " will be right up.");
        }
      });
      ft.setWidget(index, 0, cb);
      ft.setHTML(index, 1, dish);
      index++;
    }
    p.add(ft);
    RootLayoutPanel.get().add(p);
  }

  private void initDishes(MealPreferences preferences) {
    // Customize the list of dishes to the dining preferences of the user.
    if (preferences.noMeat().getValue().booleanValue()) {
      dishes = vegEntrees;
    } else {
      dishes = meatEntrees;
    }
  }
}
```


Here is a screen shot of the resulting gadget's preferences menu in iGoogle:

> ![http://gwt-google-apis.googlecode.com/svn/wiki/GettingStartedGadgets-MealPreferences.png](http://gwt-google-apis.googlecode.com/svn/wiki/GettingStartedGadgets-MealPreferences.png)


## Syndicating your Gadget ##


Once your testing is complete, you can list your gadget in the [content directory](http://code.google.com/apis/gadgets/docs/publish.html#Syndication) and syndicate it to any webpage, not just a Gadget container. For more information, see the [Publishing Using Syndication](http://code.google.com/apis/gadgets/docs/publish.html#Syndication) guidelines and documentation. Be sure to note the restrictions on syndicated gadgets, such as the storage of preferences.


## Next steps ##

For more information what you can do with Gadgets, read the [Gadget documentation](http://code.google.com/apis/gadgets/docs/dev_guide.html) and the [Gadget Library for GWT class reference](http://gwt-google-apis.googlecode.com/svn/javadoc/gagets/1.2/index.html).