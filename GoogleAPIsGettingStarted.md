# Getting Started with Using Google APIs #

**Note: This document is specific to the URL Shortener API. However, the concepts it introduces are universal to other services. Please consult the service's API documentation and JavaDoc reference for information specific to the service you intend to use.**

## Assumptions ##

  * You are already familiar with [Google Web Toolkit](http://code.google.com/webtoolkit/overview.html).
  * You know how to [create a new GWT project](http://code.google.com/eclipse/docs/creating_new_webapp.html) using the Google Plugin for Eclipse.
  * You are using GWT 2.4 or later.

## Download a service-specific Google API library ##

You can download the latest release of the library from the [download page](http://code.google.com/p/gwt-google-apis/wiki/Downloads).

For the purposes of this sample, we will download the [URL Shortener library](http://code.google.com/p/gwt-google-apis/downloads/detail?name=gwt-urlshortener-v1-0.3-alpha.jar).

You can either reference the `.jar` file from the folder where you uncompressed the project, or you can copy it to another location, such as the location of your GWT distribution (containing `gwt-dev.jar` and `gwt-user.jar` files).

## Create a new GWT Project ##

Start by creating a new GWT project as described in the [GWT Plugin Guide](http://code.google.com/eclipse/docs/creating_new_webapp.html).

Add the jar you just downloaded to the Java classpath.  Then, add the GWT module to your project's module by adding a line like this to your project's gwt.xml file:

```
<inherits name='com.google.api.gwt.services.Urlshortener' />
```

If you want to communicate with the Google+ API, add this line to your gwt.xml file:

```
<inherits name='com.google.api.gwt.services.Plus' />
```

To use the Tasks service instead, the module is `Tasks`, for the Calendar API, use `Calendar`, and so on.

## Communicating with the service ##

Now that your project has the code it needs to make requests to the service, there are a couple of things you need to do to enable requests.

### Initialize the GoogleApiRequestTransport ###

Before making a request, you must initialize the transport. To do this, write code like this:

```
// e.g., Plus.class, Tasks.class, Calendar.class, etc...
private Urlshortener urlshortener = GWT.create(Urlshortener.class);

private void initialize() {
  urlshortener.initialize(new SimpleEventBus(),
      new GoogleApiRequestTransport(YOUR_APPLICATION_NAME, YOUR_API_KEY);
}
```

This code snippet sets up the service so that it can make requests. It sets your API key (which is available at the [Google APIs Console](http://code.google.com/apis/console)) and your application's name. Once you've done this you're ready to make requests.

### Make Requests to the Service ###

The specifics of making a request are different depending on which API you are using, but using the URL Shortener API to expand a short link looks like this:

```
private void makeRequest() {
  String shortUrl = "http://goo.gl/h8cEU";
  urlshortener.url().get(shortUrl)
      // If the service had any optional parameters, they would go here, e.g.:
      // .setOptionalParameter("optionalParam")
      // .setAnotherOptionalParameter("anotherOptionalParameter")
      .to(new Receiver<Url>() {
        @Override
        public void onSuccess(Url url) {
          String longUrl = url.getLongUrl();
        }
      })
      .fire();
}
```

This code snippet uses the same `urlshortener` instance described earlier, which was instantiated by a call to `GWT.create(Urlshortener.class)`.

Note that required parameters are passed to the `get()` method. When a method requires parameters to be included, they will be present in the method's signature. If the method accepts optional parameters, they will be available in a style that allows them to be appended to the call before `fire()` is called.

## Adding Authentication ##

Some APIs provide methods that access or update a user's private data. Before you make any requests to these APIs, you need to ask the user of your application to authenticate and grant your application access to their data. If you tried the example above, you found out that this was needed when your request failed!

In fact, you need to ask the user for permission before initializing the transport.  You can request access by using an    [OAuth 2.0](http://code.google.com/apis/accounts/docs/OAuth2.html) flow.

```
private void authorize() {
  OAuth2Login.get().authorize(YOUR_CLIENT_ID,
      Urlshortener.UrlshortenerAuthScope.URLSHORTENER, 
      new Callback<Void, Exception>() {
        @Override
        public void onSuccess(Void unused) {
          // Your app has been granted access. Make a request as described above.
        }

        @Override
        public void onFailure(Exception e) {
          // The request failed!
        }
      });
}
```

If the service provides multiple scopes that are needed by the app, a list of scopes can be passed to `authorize()` method.

The scopes you provide to the `authorize()` method grant your application different access levels to your user's data. Some scopes provide read-only access while others also provide write access to their data. Read the API's documentation and decide which scopes your application requires.

Calling the `authorize()` method may display a pop-up to ask your user to grant access to their data. If they grant access, the callback's `onSuccess()` method is invoked. If the user declines, or if an error occurs, the `onFailure()` method will be called.

## Next Steps ##
You should now have the basics of using the URL Shortener API in your GWT application. The concepts learned in this guide apply to all new Google API libraries.

For a more complete sample of working with the URL Shortener API, with running code, see the [URL Shortener API Sample](http://code.google.com/p/gwt-google-apis/source/browse/trunk/apis/samples/urlshortener/com/google/api/gwt/samples/urlshortener/client/UrlshortenerEntryPoint.java) ([demo](http://gwt-google-apis.googlecode.com/svn/trunk/apis/samples/urlshortener/demo/UrlshortenerSample.html)).

To find out more about what you can do with a specific service, see the the library's specific API reference, or check out some [sample applications](GoogleAPIsSamples.md).