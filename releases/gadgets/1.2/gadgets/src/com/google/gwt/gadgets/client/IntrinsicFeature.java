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
import com.google.gwt.gadgets.client.io.RequestOptions;
import com.google.gwt.gadgets.client.io.ResponseReceivedHandler;

/**
 * Provides access to intrinsic APIs provided by the container that are not part
 * of a feature-specific API.
 *
 * @deprecated use {@link com.google.gwt.gadgets.client.io.GadgetsIo} instead.
 */
@Deprecated
public class IntrinsicFeature implements GadgetFeature {
  private IntrinsicFeature() {
  }

  /**
   * Fetches the content of the provided URL and, when complete, calls the
   * {@link ContentFetchedHandler#onContentFetched(ContentFetchedEvent)} method
   * with the content of the fetched URL. The fetched content is cached on the
   * Gadget Container.
   *
   * @deprecated use {@link com.google.gwt.gadgets.client.io.GadgetsIo#makeRequest(String, ResponseReceivedHandler)} or
   *             {@link com.google.gwt.gadgets.client.io.GadgetsIo#makeRequestAsText(String, ResponseReceivedHandler)}
   *             instead.
   */
  @Deprecated
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
   *
   * @deprecated use
   *             {@link com.google.gwt.gadgets.client.io.GadgetsIo#makeRequest(String, ResponseReceivedHandler, RequestOptions)}
   *             or
   *             {@link com.google.gwt.gadgets.client.io.GadgetsIo#makeRequestAsText(String, ResponseReceivedHandler, RequestOptions)}
   *             instead.
   */
  @Deprecated
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
   * Fetches the content of the provided URL and when complete calls the
   * {@link XmlContentFetchedHandler#onXmlContentFetched(XmlContentFetchedEvent)}
   * method with the content of the fetched URL. The content will then be parsed
   * as XML content. The fetched content is cached on the Gadget Container.
   *
   * @deprecated use {@link com.google.gwt.gadgets.client.io.GadgetsIo#makeRequestAsDom(String, ResponseReceivedHandler)}
   *             instead.
   */
  @Deprecated
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
   * Fetches the content of the provided URL and when complete calls the
   * {@link XmlContentFetchedHandler#onXmlContentFetched(XmlContentFetchedEvent)}
   * method with the content of the fetched URL. The content will then be parsed
   * as XML content. The fetched content is cached on the Gadget Container with
   * a specified refresh interval specified in seconds.
   *
   * @deprecated use
   *             {@link com.google.gwt.gadgets.client.io.GadgetsIo#makeRequestAsDom(String, ResponseReceivedHandler, RequestOptions)}
   *             instead.
   */
  @Deprecated
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
   *
   * @deprecated use {@link com.google.gwt.gadgets.client.io.GadgetsIo#getProxyUrl(String)} instead.
   */
  @Deprecated
  public native String getCachedUrl(String url) /*-{
    return $wnd.gadgets.io.getProxyUrl(url);
  }-*/;

  /**
   * Returns a proxy URL that can be used to access a given URL with a specified
   * refresh interval specified in seconds.
   *
   * @deprecated use {@link com.google.gwt.gadgets.client.io.GadgetsIo#getProxyUrl(String, int)} instead.
   */
  @Deprecated
  public native String getCachedUrl(String url, int refreshIntervalSeconds) /*-{
    return $wnd.gadgets.io.getProxyUrl(url, refreshInterval);
  }-*/;

  /**
   * Returns a proxy URL that can be used to access the given image's URL.
   *
   * @deprecated use {@link com.google.gwt.gadgets.client.io.GadgetsIo#getProxyUrl(String)} instead.
   */
  @Deprecated
  public native String getImageUrl(String url) /*-{
    return $wnd.gadgets.io.getProxyUrl(url);
  }-*/;

  /**
   * Returns a proxy URL that can be used to access a given image's URL with a
   * specified refresh interval specified in seconds.
   *
   * @deprecated use {@link com.google.gwt.gadgets.client.io.GadgetsIo#getProxyUrl(String, int)} instead.
   */
  @Deprecated
  public native String getImageUrl(String url, int refreshIntervalSeconds) /*-{
    return $wnd.gadgets.io.getProxyUrl(url, refreshInterval);
  }-*/;

  /**
   * Native function that will trigger the content fetching.
   */
  private native void fetchContent(String url,
      Event.FetchContentCallback callback) /*-{
    return $wnd.gadgets.io.makeRequest(url, function(obj) {
          @com.google.gwt.gadgets.client.event.Event::callbackWrapper(Ljava/lang/String;Lcom/google/gwt/gadgets/client/event/Event$FetchContentCallback;)(obj.data, callback);
        }, {CONTENT_TYPE: $wnd.gadgets.io.ContentType.TEXT});
  }-*/;

  /**
   * Native function that will trigger the content fetching with a specified
   * refresh interval for caching.
   */
  private native void fetchContent(String url,
      Event.FetchContentCallback callback, int refreshIntervalSeconds) /*-{
    return $wnd.gadgets.io.makeRequest(url, function(obj) {
            @com.google.gwt.gadgets.client.event.Event::callbackWrapper(Ljava/lang/String;Lcom/google/gwt/gadgets/client/event/Event$FetchContentCallback;)(obj.data, callback);
          },{CONTENT_TYPE: $wnd.gadgets.io.ContentType.TEXT, refreshInterval: refreshIntervalSeconds}
        );
  }-*/;

  /**
   * Native function that will trigger the XML content fetching.
   */
  private native void fetchXmlContent(String url,
      Event.FetchXmlContentCallback callback) /*-{
    return $wnd.gadgets.io.makeRequest(url, function(obj) {
        @com.google.gwt.gadgets.client.event.Event::callbackWrapper(Lcom/google/gwt/dom/client/ObjectElement;Lcom/google/gwt/gadgets/client/event/Event$FetchXmlContentCallback;)(obj.data, callback);
      }, {CONTENT_TYPE: $wnd.gadgets.io.ContentType.DOM});
  }-*/;

  /**
   * Native function that will trigger the XML content fetching with a specified
   * refresh interval for caching.
   */
  private native void fetchXmlContent(String url,
      Event.FetchXmlContentCallback callback, int refreshIntervalSeconds) /*-{
    return $wnd.gadgets.io.makeRequest(url, function(obj) {
        @com.google.gwt.gadgets.client.event.Event::callbackWrapper(Lcom/google/gwt/dom/client/ObjectElement;Lcom/google/gwt/gadgets/client/event/Event$FetchXmlContentCallback;)(obj.data, callback);
      }, {CONTENT_TYPE: $wnd.gadgets.io.ContentType.DOM, refreshInterval: refreshIntervalSeconds });
  }-*/;
}