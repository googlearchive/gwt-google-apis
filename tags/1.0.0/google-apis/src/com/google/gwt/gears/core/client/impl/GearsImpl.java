/*
 * Copyright 2007 Google Inc.
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

package com.google.gwt.gears.core.client.impl;

import com.google.gwt.core.client.JavaScriptException;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.gears.core.client.GearsException;
import com.google.gwt.gears.database.client.DatabaseException;

/**
 * 
 */
public class GearsImpl {
  /**
   * Utility method to convert a Java String[] to a JavaScript primitive string
   * array. The GWT compiler can transform scalar primitives between Java and
   * JavaScript; however, it cannot (currently) transform arrays of primitives.
   * As a result, Java arrays are opaque to JSNI/JavaScript code, so they have
   * to be explicitly reconstituted in order to be passed to JS functions.
   * 
   * A future version of the GWT compiler may automatically transform arrays of
   * primitive types; if that happens, this code can be removed.
   * 
   * @param ary the array of Java Strings to be reconstituted as a JavaScript
   *          array
   * @return an array of JavaScript strings
   */
  public static native JavaScriptObject convertToJavaScript(String[] ary) /*-{
   var newAry = [];
   var len = @com.google.gwt.gears.core.client.impl.GearsImpl::getLength([Ljava/lang/String;)(ary);
   for (var i = 0; i < len; ++i) {
   newAry[i] = @com.google.gwt.gears.core.client.impl.GearsImpl::getIthEntry([Ljava/lang/String;I)(ary, i); 
   }
   return newAry;
   }-*/;

  /**
   * Private common handler for all <code>createFoo</code> requests. Used to
   * map a <code>createFoo</code> call to a call to the underlying JavaScript
   * <code>GearsFactory.create</code>.
   * 
   * Essentially wraps a JSNI call with exception handling.
   * 
   * @see Gears#nativeCreate(String, String)
   * @param className the Gears-defined name of the object to create
   * @param version the desired Gears version
   * @return whatever object was returned by Gears
   * @throws GearsException if the object could not be created
   * 
   * TODO(mmendez): this should be private or package protected
   */
  public static JavaScriptObject create(String className, String version)
      throws GearsException {
    try {
      JavaScriptObject jso = nativeCreate(className, version);
      if (jso == null) {
        // TODO: What? This should never return null...
        throw new NullPointerException();
      }
      return jso;
    } catch (JavaScriptException e) {
      // TODO: Is this possible?
      throw new GearsException(e.getMessage(), e);
    } catch (NullPointerException e) {
      // TODO: Is this possible?
      throw new GearsException("Null DB handle", e);
    }
  }

  /**
   * Native wrapper around a call to <code>GearsFactory.create</code>.
   * 
   * Note that this method can (and will, according to the rules specified by
   * Gears) throw a runtime JavaScriptException.
   * 
   * @see GearsImpl#create(String, String)
   * @param className the Gears-defined name of the object to create
   * @param version the desired Gears version
   * @return whatever object was returned by Gears
   */
  public static native JavaScriptObject nativeCreate(String className,
      String version) /*-{
   return $wnd.google.gears.factory.create(className, version);
   }-*/;

  /**
   * Unpacks and returns the <code>i</code>-ith element of <code>arr</code>.
   * 
   * @param arr the array whose <code>i</code>-th element is to be fetched
   * @param i the index of the entry to fetch
   * @return <code>arr[i]</code>
   */
  private static String getIthEntry(String[] arr, int i) {
    return arr[i];
  }

  /**
   * Fetches the length of a Java array.
   * 
   * @param arr
   * @return
   */
  private static int getLength(String[] arr) {
    return arr.length;
  }
  
  /*
   * Called from JSNI
   */
  private static void throwDatabaseException(String message)
      throws DatabaseException {
    throw new DatabaseException(message);
  }

  /*
   * Called from JSNI
   */
  private static void throwGearsException(String message) throws GearsException {
    throw new GearsException(message);
  }
}
