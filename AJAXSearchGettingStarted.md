#summary Using Google AJAX Search in a project

# Getting Started with Google AJAX Search #

The AJAX Search API provides a convenient way to add a pre-packaged search box and display results to a webpage, or to manipulate search results in a programmatic way. The [Google AJAX Search library for GWT](http://code.google.com/p/gwt-google-apis) allows you to access this JavaScript API from Java code compiled with the GWT compiler.

This guide describes the basics of getting the Google AJAX Search Library working in a GWT project. For more information on the functionality provided by the AJAX Search API, see the [Google AJAX Search API](http://code.google.com/apis/ajaxsearch/) developer's guide.

## Assumptions ##

  * You are already familiar with [Google Web Toolkit](http://code.google.com/webtoolkit/overview.html)
  * You know how to [create a new GWT project](http://code.google.com/eclipse/docs/creating_new_webapp.html) using the Google Plugin for Eclipse.
  * You are using GWT 2.0 or later.

## Downloading the Google AJAX Search Library for GWT ##

You can download the latest release of the library from the [project download page](http://code.google.com/p/gwt-google-apis/downloads/). After you download the distribution, uncompress it.  Inside the folder you will find a .jar file named `gwt-search.jar`.

You can either reference the `.jar` file from the folder that you uncompressed the project in, or copy it to another location, such as the location of your GWT distribution (containing `gwt-dev.jar` and `gwt-user.jar` files).  In this example, we've chosen to copy the file to the path `/usr/local/gwt-search`.


## Setting up a GWT Project to use the AJAX Search API ##

There are three steps required for a GWT project to use the AJAX Search Library.

  1. Include the library JAR file in the classpath
  1. Inherit com.google.gwt.search.Search in the module XML definition file.
  1. Include a `<script>` tag in your module in order to load the api and provide the AJAX Search key.

We'll explain each as we set up a new project.

## Creating a new GWT Project ##

Start by creating a new GWT project called SimpleSearch as described in the [GWT Plugin Guide](http://code.google.com/eclipse/docs/creating_new_webapp.html).

Since we are working with an additional library, add the library `gwt-search.jar` to the Java classpath.  Then, add the module `com.google.gwt.search.Search` to your module.

```
    <inherits name='com.google.gwt.search.Search' />
```

For better effect please remove the .css reference from the SimpleSearch.html page and remove all HTML tags in the body.

## Adding the AJAX Search script tag to your module XML file ##

Your GWT application will need access to the AJAX Search API, as well as the API key. In order to do this, you must include a `<script>` tag in your module's `SimpleSearch.gwt.xml` file. Include the script tag shown in your module.xml file above the automatically generated stylesheet reference.

```
    <!--
      If you want to deploy this application outside of localhost,
      you must obtain a Google AJAX Search API key at:
        http://code.google.com/apis/search/signup.html
      append &amp;key=ABC to the string below, replacing ABC with the key
      obtained from the site above.
     -->
    <script src="http://www.google.com/uds/api?file=uds.js&amp;v=1.0&amp;gwt=1"/>

    <!-- Specify the application specific style sheet. -->
    <stylesheet src='SimpleSearch.css' />   
```

You can obtain a Google AJAX Search API key at http://code.google.com/apis/ajaxsearch/signup.html. Append &amp;key=ABC to the src attribute, replacing ABC with the key obtained from the site above.

## Basic steps to use the API ##

Now that your project is set up, the basic steps to use the API are as follows:

  1. Create one or more search objects
    * [BlogSearch](http://gwt-google-apis.googlecode.com/svn/javadoc/search/1.1/com/google/gwt/search/client/BlogSearch.html)
    * [BookSearch](http://gwt-google-apis.googlecode.com/svn/javadoc/search/1.1/com/google/gwt/search/client/BookSearch.html)
    * [LocalSearch](http://gwt-google-apis.googlecode.com/svn/javadoc/search/1.1/com/google/gwt/search/client/LocalSearch.html)
    * [ImageSearch](http://gwt-google-apis.googlecode.com/svn/javadoc/search/1.1/com/google/gwt/search/client/ImageSearch.html)
    * [NewsSearch](http://gwt-google-apis.googlecode.com/svn/javadoc/search/1.1/com/google/gwt/search/client/NewsSearch.html)
    * [VideoSearch](http://gwt-google-apis.googlecode.com/svn/javadoc/search/1.1/com/google/gwt/search/client/VideoSearch.html)
    * [WebSearch](http://gwt-google-apis.googlecode.com/svn/javadoc/search/1.1/com/google/gwt/search/client/WebSearch.html)
  1. Add the search object(s) to a [SearchControlOptions](http://gwt-google-apis.googlecode.com/svn/javadoc/search/1.1/com/google/gwt/search/client/SearchControlOptions.html) object
  1. Optionally register a [SearchListener](http://gwt-google-apis.googlecode.com/svn/javadoc/search/1.1/com/google/gwt/search/client/SearchListener.html) on the SearchControlOptions object
  1. Create a [SearchControl](http://gwt-google-apis.googlecode.com/svn/javadoc/search/1.1/com/google/gwt/search/client/SearchControl.html) object from the `SearchControlOptions`
  1. Call the [SearchControl.execute()](http://gwt-google-apis.googlecode.com/svn/javadoc/search/1.1/com/google/gwt/search/client/SearchControl.html#execute(/java/lang/String)execute) method to specify a search term and start the search .

We'll do just that in the next step.

## Adding a Google AJAX SearchControl to the page ##

Let's create a basic but nicely formatted search box and the corresponding results using web and image results. Here we use the API's built in HTML rendering.

Replace the contents of your `onModuleLoad` method with the following:

```
public class SimpleSearch implements EntryPoint {

  public void onModuleLoad() {

    SearchControlOptions options = new SearchControlOptions();
    WebSearch webSearch = new WebSearch();
    webSearch.setResultSetSize(ResultSetSize.LARGE);
    options.add(webSearch);
    ImageSearch imageSearch = new ImageSearch();
    options.add(imageSearch, ExpandMode.OPEN);
    final SearchControl control = new SearchControl(options);
    control.execute("treehouse");
    RootPanel.get().add(control);
  }
}
```

Now save your project and run it in development mode.

Congratulations! You should now have Google AJAX Search working within a GWT application.

> ![http://gwt-google-apis.googlecode.com/svn/wiki/AJAXSearch2.png](http://gwt-google-apis.googlecode.com/svn/wiki/AJAXSearch2.png)


## Using a SearchControl with a SearchResultsHandler ##

The Search API is not limited to showing results in just the format above. By registering a  [SearchResultsHandler.html](http://gwt-google-apis.googlecode.com/svn/javadoc/search/1.1/com/google/gwt/search/client/SearchResultsHandler), you can access all the details of the results returned from the search and handle them in a programmatic way.

Replace the contents of the SimpleSearch class with the code below.

```
public class SimpleSearch implements EntryPoint {
  int currentRow;
  FlexTable resultsTable;

  public void onModuleLoad() {

    resultsTable = new FlexTable();
    SearchControlOptions options = new SearchControlOptions();

    WebSearch webSearch = new WebSearch();
    // Choose no HTML generation for quicker results.
    webSearch.setNoHtmlGeneration();
    options.add(webSearch);
    final SearchControl control = new SearchControl(options);
    control.addSearchResultsHandler(new SearchResultsHandler() {

      public void onSearchResults(SearchResultsEvent event) {
        JsArray<? extends Result> results = event.getResults();

        for (int i = 0; i < results.length(); i++) {
          if (results.get(i).getResultClass().equals(
              ResultClass.WEB_SEARCH_RESULT)) {
            currentRow++;

            WebResult result = (WebResult) results.get(i);
            resultsTable.setText(currentRow, 0, "" + currentRow);
            resultsTable.setHTML(currentRow, 1, "<a href=\"" + result.getUrl()
                + "\">" + result.getTitle() + "</a>");
          }
        }
      }

    });
    final TextBox textBox = new TextBox();
    textBox.setText("treehouse");
    Button button = new Button("Run Search");
    button.addClickHandler(new ClickHandler() {

      public void onClick(ClickEvent event) {
        for (int i = 0; resultsTable.getRowCount() > 0; ++i) {
          resultsTable.removeRow(0);
        }
        currentRow = 0;
        control.execute(textBox.getText());
      }

    });

    VerticalPanel vp = new VerticalPanel();
    vp.add(textBox);
    vp.add(button);
    vp.add(resultsTable);

    RootPanel.get().add(vp);

  }
}
```


Here is a screen capture of the resulting application in development mode.

> ![http://gwt-google-apis.googlecode.com/svn/wiki/AJAXSearch-nohtml.png](http://gwt-google-apis.googlecode.com/svn/wiki/AJAXSearch-nohtml.png)

## Next Steps ##
You should now have the basics of a working GWT application using the Google AJAX Search Library.

To find out more about what you can do with the API, see the [library class reference](http://gwt-google-apis.googlecode.com/svn/javadoc/search/1.1/index.html) and the [Google AJAX Search API Developer's Guide](http://code.google.com/apis/ajaxsearch/)