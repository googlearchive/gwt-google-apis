This project uses the http://code.google.com/p/gwt-api-interop/ project at r83.  
The packages for its classes have been rebased to live under the ajaxsearch.jsio
subpackage.  This will prevent versioning conflicts between this API and any other
uses of gwt-api-interop.

We should explore either performing the rebasing during the build or just
using the gwt-api-interop library (and make sure that we are clear on the 
implications).