/*
 * Copyright 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.api.gwt.client;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.core.client.ScriptInjector;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Loads the Google API Javascript Client asynchronously.
 *
 * @author jasonhall@google.com (Jason Hall)
 */
public abstract class GoogleApiLoader {

  private static Impl impl;

  public static GoogleApiLoader get() {
    if (impl == null) {
      impl = new Impl();
    }
    return impl;
  }

  public abstract void load(Callback<Void, Exception> callback);

  private static final class Impl extends GoogleApiLoader {
    private static final String ONLOAD_CALLBACK = "googleapi_gwt_onload";
    private static final String JS_CLIENT_URL =
        "https://apis.google.com/js/client.js?onload=" + ONLOAD_CALLBACK;

    private static final Queue<Callback<Void, Exception>> outstandingCallbacks =
        new LinkedList<Callback<Void, Exception>>();
    private static boolean loading = false;

    @Override
    public void load(final Callback<Void, Exception> callback) {
      // Only load the script once, if the necessary API isn't available
      if (!isLoaded()) {
        outstandingCallbacks.offer(callback);
        if (!loading) {
          // Start loading the library.
          loading = true;
          nativeSetupScriptConfig();
          ScriptInjector.fromUrl(JS_CLIENT_URL)
          .setWindow(ScriptInjector.TOP_WINDOW)
          .setCallback(new Callback<Void, Exception>() {
            @Override
            public void onSuccess(Void v) {
            }

            @Override
            public void onFailure(Exception e) {
              broadcastError();
            }
          }).inject();
        }
      } else {
        Scheduler.get().scheduleDeferred(new ScheduledCommand() {
          @Override
          public void execute() {
            callback.onSuccess(null);
          }
        });
      }
    }

    private static final native void nativeSetupScriptConfig() /*-{
    $wnd[@com.google.api.gwt.client.GoogleApiLoader.Impl::ONLOAD_CALLBACK] = $entry(function() {
      @com.google.api.gwt.client.GoogleApiLoader.Impl::scriptLoaded()();
    });
  }-*/;

    private static void scriptLoaded() {
      loading = false;

      Scheduler.get().scheduleDeferred(new ScheduledCommand() {
        @Override
        public void execute() {
          while (!outstandingCallbacks.isEmpty()) {
            outstandingCallbacks.poll().onSuccess(null);
          }
        }
      });
    }

    private static void broadcastError() {
      loading = false;
      final IllegalStateException e =
          new IllegalStateException("Failed to load Google APIs library.");

      Scheduler.get().scheduleDeferred(new ScheduledCommand() {
        @Override
        public void execute() {
          while (!outstandingCallbacks.isEmpty()) {
            outstandingCallbacks.poll().onFailure(e);
          }
        }
      });
    }

    public static native boolean isLoaded() /*-{
      return !!$wnd.gapi && !!$wnd.gapi.client;
    }-*/;
  }
}
