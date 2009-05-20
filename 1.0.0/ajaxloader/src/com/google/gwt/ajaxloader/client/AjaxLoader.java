/*
 * Copyright 2008 Google Inc.
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
package com.google.gwt.ajaxloader.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ScriptElement;

import java.util.Vector;

/**
 * A wrapper for the google Ajax API loader.
 * 
 * http://code.google.com/apis/ajax/documentation/
 */
public class AjaxLoader {
  /**
   * An object to pass as the third parameter to load() call.
   */
  public static class AjaxLoaderOptions extends JavaScriptObject {
    public static AjaxLoaderOptions newInstance() {
      return JavaScriptObject.createObject().cast();
    }

    protected AjaxLoaderOptions() {
      // A protected constructor is required for JavaScriptObject overlays.
    }

    public final native void setBaseDomain(String baseDomain) /*-{
      this.base_domain = baseDomain;
    }-*/;

    public final native void setLanguage(String language) /*-{
      // TODO(zundel): try to incorporate w/ GWT locale?
       this.language = language;
    }-*/;

    // TODO(zundel): the docs are a little confusing on this one.
    public final native void setNoCss(boolean value) /*-{
      this.nocss = value;
    }-*/;

    public final native void setOtherParms(String otherParams) /*-{
      this.other_params = otherParams;
    }-*/;

    public final native void setPackages(JsArrayString packages) /*-{
      this.packages = packages;
    }-*/;

    public final void setPackages(String... packages) {
      setPackages(ArrayHelper.createJsArray(packages));
    }

    private native void setCallback(Runnable onLoad) /*-{
      this.callback = function() {
        @com.google.gwt.ajaxloader.client.ExceptionHelper::runProtected(Ljava/lang/Runnable;)(onLoad);
      }
    }-*/;
  }

  // NativeCreateCallback already ran, or someone injected the API outside of
  // this program.
  static boolean alreadyInjected = false;

  // Set to true if the init(key) method has been called.
  static boolean initialized = false;

  // True if the JavaScript __gwt_AjaxLoader_onLoad callback has already run.
  // This function is registered on the window in nativeCreateCallback()
  static boolean loaded = false;

  static Vector<Runnable> queuedApiLoads = new Vector<Runnable>();

  /**
   * Initialize the API without a key.
   */
  public static void init() {
    init(null);
  }

  /**
   * Initialize the API with a supplied key value. See
   * http://code.google.com/apis/ajaxsearch/signup.html
   * 
   * @param apiKey API key value.
   */
  public static void init(String apiKey) {
    if (initialized == true) {
      return;
    }

    loaded = injectJsapi(apiKey);
    initialized = true;
  }

  /**
   * Launches an API load request.
   * 
   * @param api The name of the API to load
   * @param version The API version to load
   * @param onLoad A callback that will be invoked when the API is finished
   *          loaded. Do not make any calls into the API being loaded until this
   *          call returns.
   * @param settings An object containing additional settings.
   */
  public static void loadApi(final String api, final String version,
      Runnable onLoad, AjaxLoaderOptions settings) {

    // Initialize the API if it hasn't already been initialized.
    init();

    // Set the onLoad callback into the
    assert (onLoad != null);
    if (settings == null) {
      settings = AjaxLoaderOptions.newInstance();
    }
    settings.setCallback(onLoad);
    final AjaxLoaderOptions copyOfSettings = settings;

    // Define a Runnable that will run the actual load.
    Runnable apiLoad = new Runnable() {

      public void run() {
        nativeLoadApi(api, version, copyOfSettings);
      }
    };

    if (loaded) {
      // jsapi is finished loading, start the individual API load now.
      apiLoad.run();
    } else {
      // Defer the load until jsapi is finished.
      queuedApiLoads.add(apiLoad);
    }
  }

  /**
   * Adds a script element to the DOM that loads the Ajax API Loader main script
   * "jsapi".
   * 
   * @param apiKey Optional API key value (pass null to omit the key). See
   *          http://code.google.com/apis/ajaxsearch/signup.html
   * @returns <code>true</code> if the API has already been loaded. Otherwise,
   *          returns <code>false</code>, meaning that the application should
   *          wait for a callback.
   */
  private static boolean injectJsapi(String apiKey) {
    if (alreadyInjected) {
      return true;
    }
    boolean alreadyLoaded = nativeCreateCallback();
    alreadyInjected = true;
    if (alreadyLoaded) {
      return true;
    }
    Document doc = Document.get();
    String key = (apiKey == null) ? "" : ("key=" + apiKey + "&");
    String src = "http://www.google.com/jsapi?" + key
        + "callback=__gwt_AjaxLoader_onLoad";
    ScriptElement script = doc.createScriptElement();
    script.setSrc(src);
    script.setType("text/javascript");
    doc.getBody().appendChild(script);
    return false;
  }

  /**
   * Creates a function to be registered for a callback after jsapi loads.
   */
  private static native boolean nativeCreateCallback() /*-{
    if ($wnd['google'] && $wnd.google['load']) {
      // The API has already been loaded.
      return true;
    }
    $wnd.__gwt_AjaxLoader_onLoad = function() {
      @com.google.gwt.ajaxloader.client.AjaxLoader::onLoadCallback()();
    }
     // The application must wait for a callback.
     return false;
  }-*/;

  /**
   * Wrapper for AjaxLoader google.load() native method.
   */
  private static native void nativeLoadApi(String api, String version,
      JavaScriptObject settings) /*-{
    $wnd.google.load(api, version, settings);
  }-*/;

  /**
   * Called back when the jsapi is finished loaded. It must kick of any API
   * loads that have been queued while waiting on jsapi to finish loading.
   */
  @SuppressWarnings("unused")
  private static void onLoadCallback() {
    loaded = true;

    for (Runnable r : queuedApiLoads) {
      r.run();
    }
    queuedApiLoads.clear();
  }

  private AjaxLoader() {
    // This class only contains static methods and should not be instantiated.
  }
}
