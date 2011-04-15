/*
 * Copyright 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */


/** 
 * Based on com/google/gwt/core/ext/linker/impl/processMetas.js
 * 
 * Originally intended to process all <meta> tags:
 * gwt:property, gwt:onPropertyErrorFn, gwt:onLoadErrorFn
 * For Gadgets, we substitute gadget Prefs for HTML <meta> tags.
 * 
 * This is included into the selection scripts
 * wherever PROCESS_METAS appears with underlines
 * on each side.
 */
function processMetas() {
  
  var metaProps = {}
  var propertyErrorFunc;
  var onLoadErrorFunc;

  var meta;
  var prefs = new $wnd.gadgets.Prefs();

  if (meta = prefs.getString('gwt:onLoadErrorFn')) {
    try {
      onLoadErrorFunc = eval(meta);
    } catch (e) {
      alert('Bad handler \"' + content + '\" for \"gwt:onLoadErrorFn\"');
    }
  }

  if (meta = prefs.getString('gwt:onPropertyErrorFn')) {
    try {
      propertyErrorFunc = eval(meta);
    } catch (e) {
      alert('Bad handler \"' + content + '\" for \"gwt:onPropertyErrorFn\"');
    }
  }

  if (meta = prefs.getArray('gwt:property')) {
    for ( var i = 0; i < meta.length; i++) {
      var content = meta[i];
      if (content) {
        var value, eq = content.indexOf('=');
        if (eq >= 0) {
          name = content.substring(0, eq);
          value = content.substring(eq + 1);
        } else {
          name = content;
          value = '';
        }
        metaProps[name] = value;
      }
    }
  }
  
  /**
   * Gadget iframe URLs are generated with the locale in the URL as a
   * lang/country parameter pair (e.g. lang=en&country=US) in lieu of the
   * single locale parameter.  To propagate this to the I18N.gwt.xml
   * property provider, we'll construct a locale string and add it as a 
   * meta property.
   */
  if (metaProps['locale'] == null) {
    var lang = $wnd.gadgets.util.getUrlParameters()['lang'];
    if (lang != null) {
      var country = $wnd.gadgets.util.getUrlParameters()['country'];
      if (country != null) {
        metaProps['locale'] = lang + "_" + country;
      } else {
        metaProps['locale'] = lang;
      }
    }
  }
    
  // Set some of the variables in the main script
  __gwt_getMetaProperty = function(name) {
    var value = metaProps[name];
    return (value == null) ? null : value;
  }
  __propertyErrorFunction = propertyErrorFunc;
  __MODULE_FUNC__.__errFn = onLoadErrorFunc;
}
