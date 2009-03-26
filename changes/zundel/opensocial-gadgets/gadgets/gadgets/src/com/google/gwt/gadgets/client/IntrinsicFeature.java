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
package com.google.gwt.gadgets.client;

import com.google.gwt.dom.client.ObjectElement;
import com.google.gwt.gadgets.client.event.ContentFetchedHandler;
import com.google.gwt.gadgets.client.event.Event;
import com.google.gwt.gadgets.client.event.XmlContentFetchedHandler;
import com.google.gwt.gadgets.client.event.ContentFetchedHandler.ContentFetchedEvent;
import com.google.gwt.gadgets.client.event.XmlContentFetchedHandler.XmlContentFetchedEvent;

/**
 * Provides access to intrinsic APIs provided by the container that are not part
 * of a feature-specific API.
 */
public class IntrinsicFeature implements GadgetFeature {
  private IntrinsicFeature() {
  }

  /**
   * Fetches the content of the provided URL and, when complete, calls the
   * {@link ContentFetchedHandler#onContentFetched(ContentFetchedEvent)} method
   * with the content of the fetched URL. The fetched content is cached on the
   * Gadget Container.
   */
  public void fetchContent(final String url,
      final ContentFetchedHandler contentFetchedHandler) {
    fetchContent(url, new Event.FetchContentCallback() {
      public void callback(String content) {
        ContentFetchedEvent event = new ContentFetchedEvent(content, url);
        contentFetchedHandler.onContentFetched(event);
      }
    });
  }

  /**
   * Fetches the content of the provided URL and, when complete, calls the
   * {@link ContentFetchedHandler#onContentFetched(ContentFetchedEvent)} method
   * with the content of the fetched URL. The fetched content is cached on the
   * Gadget Container with a specified refresh interval specified in seconds.
   */
  public void fetchContent(final String url,
      final ContentFetchedHandler contentFetchedHandler, int millis) {
    fetchContent(url, new Event.FetchContentCallback() {
      public void callback(String content) {
        ContentFetchedEvent event = new ContentFetchedEvent(content, url);
        contentFetchedHandler.onContentFetched(event);
      }
    }, millis);
  }

  /**
   * Fetches the content of the provided URL and when complete calls the {@link 
   * XmlContentFetchedHandler#onXmlContentFetched(XmlContentFetchedEvent)}
   * method with the content of the fetched URL. The content will then be parsed
   * as XML content. The fetched content is cached on the Gadget Container.
   */
  public void fetchXmlContent(final String url,
      final XmlContentFetchedHandler contentFetchedHandler) {
    fetchXmlContent(url, new Event.FetchXmlContentCallback() {
      public void callback(ObjectElement content) {
        XmlContentFetchedEvent event = new XmlContentFetchedEvent(content, url);
        contentFetchedHandler.onXmlContentFetched(event);
      }
    });
  }

  /**
   * Fetches the content of the provided URL and when complete calls the {@link 
   * XmlContentFetchedHandler#onXmlContentFetched(XmlContentFetchedEvent)}
   * method with the content of the fetched URL. The content will then be parsed
   * as XML content. The fetched content is cached on the Gadget Container with
   * a specified refresh interval specified in seconds.
   */
  public void fetchXmlContent(final String url,
      final XmlContentFetchedHandler contentFetchedHandler, int millis) {
    fetchXmlContent(url, new Event.FetchXmlContentCallback() {
      public void callback(ObjectElement content) {
        XmlContentFetchedEvent event = new XmlContentFetchedEvent(content, url);
        contentFetchedHandler.onXmlContentFetched(event);
      }
    }, millis);
  }

  /**
   * Returns a proxy URL that can be used to access a given URL.
   */
  public native String getCachedUrl(String url) /*-{
     return $wnd.gadgets.io.getProxyUrl(url);
   }-*/;

  /**
   * Returns a proxy URL that can be used to access a given URL with a specified
   * refresh interval specified in seconds.
   */
  public native String getCachedUrl(String url, int refreshIntervalSeconds) /*-{
     return $wnd.gadgets.io.getProxyUrl(url, { "REFRESH_INTERVAL":refreshInterval });
   }-*/;

  /**
   * Returns a proxy URL that can be used to access the given image's URL.
   * @deprecated use getCachedUrl()
   */
  @Deprecated
  public String getImageUrl(String url) {
     return getCachedUrl(url);
   }

  /**
   * Returns a proxy URL that can be used to access a given image's URL with a
   * specified refresh interval specified in seconds.
   * @deprecated use getCachedUrl()
   */
   @Deprecated
  public String getImageUrl(String url, int refreshIntervalSeconds) {
     return getCachedUrl(url, refreshIntervalSeconds);
   }

  /**
   * Native function that will trigger the content fetching.
   */
  private native void fetchContent(String url,
      Event.FetchContentCallback callback) /*-{
     return $wnd._IG_FetchContent(url, function(content) {
           @com.google.gwt.gadgets.client.event.Event::callbackWrapper(Ljava/lang/String;Lcom/google/gwt/gadgets/client/event/Event$FetchContentCallback;)(content, callback);
         });
   }-*/;

  /**
   * Native function that will trigger the content fetching with a specified
   * refresh interval for caching.
   */
  private native void fetchContent(String url,
      Event.FetchContentCallback callback, int refreshIntervalSeconds) /*-{
     return $wnd._IG_FetchContent(url, function(content) { 
             @com.google.gwt.gadgets.client.event.Event::callbackWrapper(Ljava/lang/String;Lcom/google/gwt/gadgets/client/event/Event$FetchContentCallback;)(content, callback);
           },{refreshInterval: refreshIntervalSeconds}
         );
   }-*/;

  /**
   * Native function that will trigger the XML content fetching.
   */
  private native void fetchXmlContent(String url,
      Event.FetchXmlContentCallback callback) /*-{
     return $wnd._IG_FetchXmlContent(url, function(content) { 
         @com.google.gwt.gadgets.client.event.Event::callbackWrapper(Lcom/google/gwt/dom/client/ObjectElement;Lcom/google/gwt/gadgets/client/event/Event$FetchXmlContentCallback;)(content, callback);});
   }-*/;

  /**
   * Native function that will trigger the XML content fetching with a specified
   * refresh interval for caching.
   */
  private native void fetchXmlContent(String url,
      Event.FetchXmlContentCallback callback, int refreshIntervalSeconds) /*-{
     return $wnd._IG_FetchXmlContent(url, function(content) { 
         @com.google.gwt.gadgets.client.event.Event::callbackWrapper(Lcom/google/gwt/dom/client/ObjectElement;Lcom/google/gwt/gadgets/client/event/Event$FetchXmlContentCallback;)(content, callback);},
         {refreshInterval: refreshIntervalSeconds });
   }-*/;
}