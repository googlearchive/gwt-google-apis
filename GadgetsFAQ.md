#summary Frequently Asked Questions about the GWT bindings for gadgets

# Gadgets  FAQs #

These FAQs refer to the Google API Library for GWT bindings for the gadgets API.  See also [Project Overview](Overview.md) for general information on the Google API Libraries for GWT.



## Why doesn't the sample work in Development mode? ##

The Gadgets library currently does not support Development Mode.

## How can I get GWT RPC to work in a Gadget? ##

The gwt-gadgets library creates what is called an 'inline' gadget, which puts the implementation of your gadget into the gadget spec file.  While this is a boon to performance, the gadget container now becomes the origin of your application, meaning that you must use a proxy to access resources on other hosts.

On the client side, all you need to do is invoke [`GadgetsRpc.redirectThroughProxy`](http://gwt-google-apis.googlecode.com/svn/javadoc/gadgets/1.2/com/google/gwt/gadgets/client/gwtrpc/GadgetsGwtRpc.html#redirectThroughProxy(com.google.gwt.user.client.rpc.ServiceDefTarget)) method on your service. This replaces the RequestBuilder used by a GWT RPC client with calls to methods in the [`GadgetIo`](http://gwt-google-apis.googlecode.com/svn/javadoc/gadgets/1.2/com/google/gwt/gadgets/client/io/GadgetsIo.html) class.

```
  YourServiceAsync yourService = GWT.create(YourService.class);
  GadgetsGwtRpc.redirectThroughProxy((ServiceDefTarget) yourService);
```

Then, on the server side of the connection, there is a second problem to address.  By default in GWT 1.5, the servlet checks for a content type of `text/x-gwt-rpc`.  Unfortunately, the iGoogle proxy re-writes this header.  To workaround this problem, GWT 1.5.2 and later provides a method that gives the developer some control over this checking:

```
  // Default implemention of RemoteServiceServlet.readContent(HttpServletRequest)
  protected String readContent(HttpServletRequest request)
      throws ServletException, IOException {
    return RPCServletUtils.readContentAsUtf8(request, true);
  }
```


For a gadget, override this method in your implementation of `RemoteServiceServlet` and implement it as:

```
  @Override
  protected String readContent(HttpServletRequest request)
      throws ServletException, IOException {
    return RPCServletUtils.readContentAsUtf8(request, false);
  }
```

See also [issue 443](http://code.google.com/p/gwt-google-apis/issues/detail?id=443).

The [Gadget RPC example](http://code.google.com/p/gwt-google-apis/source/browse/#svn/trunk/gadgets/samples/gadgetrpc/src/com/google/gwt/gadgets/sample/gadgetrpc) demonstrates how to follow described steps to use GWT RPC in the Gadgets container.

## Why can't I use GWT Theme support with a Gadget? ##

The GWT themes include references to resources such as images through URLs.  Since those resources' URLs are not properly re-written to go through the gadget container's proxy, the gadget ends up trying to fetch them from the gadget container instead of the server where your gadget is hosted.

One solution, as shown in the HelloGadgets sample, is to manually copy the stylesheet rules that you need for your gadget into a single .css file.  Translate any relative URLs into fully qualified URLs.  You will need to insure that the server hosting these resources is capable of handling the full load of the gadget, as this bypasses the gadget container's proxy and you lose the benefits of its cache.

## I am using GWT 1.6 and having trouble with problems parsing XML or the Xerces library ##

New features in 1.6 have included multiple versions of the Xerces library.  Try using the `gwt-gadgets-noredist.jar` jar file instead of `gwt-gears.jar` (new in the gwt-gadgets-1.0.2 release).  The `-noredist.jar` version does not include any extra library classes outside of the `com.google.gwt.gears` package.

## I'm using the GWT 2.1 milestone and see errors using GWT RPC ##

```
SEVERE: Exception while dispatching incoming RPC call
java.lang.SecurityException: Blocked request without GWT permutation header (XSRF attack?)
       at
com.google.gwt.user.server.rpc.RemoteServiceServlet.checkPermutationStrongName(RemoteServiceServlet.java:250)
```

As of [r7703](https://code.google.com/p/gwt-google-apis/source/detail?r=7703), (GWT 2.1 milestones), the X-GWT-Permutation header is being checked.  You can bypass this check by  overriding the method `checkPermutationStrongName()` in your subclass of `RemoteServiceServlet`.