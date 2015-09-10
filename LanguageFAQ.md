#summary GWT Language API questions that are asked frequently.

# Language API FAQs #



## I saw the Language API is deprecated. What is the replacement? ##

Check out the [Translate Element](http://www.google.com/webelements/translate) instead.

## What Languages are supported by the Language API? ##

Check the underlying [Ajax Language JavaScript API documentation](http://code.google.com/apis/ajaxlanguage) for a list of the currently supported languages.  If a new language is added to the JavaScript API but not yet supported by the Language enumeration, please file an [issue](http://code.google.com/p/gwt-google-apis/issues/list).  In the interim, you can pass in the text representation of the language code.

## Where can I go to get help with using the Language APIs? ##

You may post questions to the  [GWT-Google-Apis](http://groups.google.com/group/gwt-google-apis) group, which is the forum for the developers of the GWT wrappers for all Google APIs.  You can also try the [Google-Web-Toolkit](http://groups.google.com/group/gwt-google-api) group which is the main public forum for GWT.

## What should I do if I think I've found a bug? ##

First post your bug to the [GWT-Google-Apis](http://groups.google.com/group/gwt-google-api) group for discussion.  There may be a workaround or a patch already in place for the problem.  Bugs are logged in the [Issue Tracker](http://code.google.com/p/gwt-google-apis/issues/list).  Please reserve the issue tracker for bugs with the API itself, and not for diagnosing problems with a particular application.

## I have problems running my application from file ##

For modern browsers it's common to forbid cross-file references, which most likely is the cause of your problems. You can run `ant devmode` command to start development mode, and paste the starup URL without `?gwt.codesvr=127.0.0.1:9997` suffix into your browser (port may vary). It will make your embedded web server serve your files in a production mode.