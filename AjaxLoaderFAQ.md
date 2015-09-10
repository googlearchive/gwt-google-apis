#summary GWT AjaxLoader API questions that are asked frequently.

# AjaxLoader API FAQs #



## Where can I go to get help with using the AjaxLoader API? ##

You may post questions to the  [GWT-Google-Apis](http://groups.google.com/group/gwt-google-apis) group, which is the forum for the developers of the GWT wrappers for all Google APIs.  You can also try the [Google-Web-Toolkit](http://groups.google.com/group/gwt-google-api) group which is the main public forum for GWT.

## Do I need a copy of this project to use AjaxLoader with the other Google APIs for GWT? ##

The projects that require the AjaxLoader classes are distributed with a .jar file that bundles
them in.  However, most projects also contain a gwt-XXX-noredist.jar distribution that does not
include the AjaxLoader or other classes.   The -noredist.jar bundles are useful when your
project already has a conflicting version of a dependency.   You may need a standalone version of gwt-ajaxloader.jar if you wish to use the -noredist.jar files.

## What should I do if I think I've found a bug? ##

First post your bug to the [GWT-Google-Apis](http://groups.google.com/group/gwt-google-api) group for discussion.  There may be a workaround or a patch already in place for the problem.  Bugs are logged in the [Issue Tracker](http://code.google.com/p/gwt-google-apis/issues/list).  Please reserve the issue tracker for bugs with the API itself, and not for diagnosing problems with a particular application.

## I have problems running my application from file ##

For modern browsers it's common to forbid cross-file references, which most likely is the cause of your problems. You can run `ant devmode` command to start development mode, and paste the starup URL without `?gwt.codesvr=127.0.0.1:9997` suffix into your browser (port may vary). It will make your embedded web server serve your files in a production mode.
