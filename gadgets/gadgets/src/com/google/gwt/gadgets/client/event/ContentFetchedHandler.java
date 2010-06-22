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
package com.google.gwt.gadgets.client.event;

import com.google.gwt.gadgets.client.io.ResponseReceivedHandler;
import com.google.gwt.user.client.ui.RootPanel;

import java.util.EventObject;

/**
 * The primary interface a caller must implement to receive a response to a
 * {@link com.google.gwt.gadgets.client.IntrinsicFeature#fetchContent(String, ContentFetchedHandler)}
 * .
 * 
 * @deprecated use {@link ResponseReceivedHandler} instead.
 */
@Deprecated
public interface ContentFetchedHandler {

  /**
   * Encapsulates the arguments for the IntrinsicFeature.fetchContent()
   * response.
   */
  @SuppressWarnings("serial")
  class ContentFetchedEvent extends EventObject {
    private final String fetchedContent;
    private final String url;

    public ContentFetchedEvent(String fetchedContent, String url) {
      super(RootPanel.get());
      this.fetchedContent = fetchedContent;
      this.url = url;
    }

    /**
     * Returns the fetched content.
     * 
     * @return The fetched content
     */
    public String getFetchedContent() {
      return this.fetchedContent;
    }

    /**
     * Returns the fetched content's URL.
     * 
     * @return The fetched content's URL
     */
    public String getUrl() {
      return this.url;
    }
  }

  /**
   * Method to be invoked when a pending content fetch completes.
   * 
   * @param event event encapsulating the fetched data.
   */
  void onContentFetched(ContentFetchedEvent event);
}
