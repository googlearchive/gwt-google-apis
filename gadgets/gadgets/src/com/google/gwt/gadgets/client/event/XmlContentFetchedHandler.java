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

import com.google.gwt.dom.client.ObjectElement;
import com.google.gwt.gadgets.client.io.ResponseReceivedHandler;
import com.google.gwt.user.client.ui.RootPanel;

import java.util.EventObject;

/**
 * The primary interface a caller must implement to receive a response to an
 * {@link com.google.gwt.gadgets.client.IntrinsicFeature#fetchXmlContent(String,
 * XmlContentFetchedHandler)}. Creates a DOM Tree from of the fetched content.
 *
 * @deprecated use {@link ResponseReceivedHandler} instead.
 */
@Deprecated
public interface XmlContentFetchedHandler {

  /**
   * Encapsulates the arguments for the IntrinsicFeature.fetchXmlContent()
   * response.
   */
  @SuppressWarnings("serial")
  class XmlContentFetchedEvent extends EventObject {
    private final ObjectElement fetchedXmlContent;
    private final String url;

    public XmlContentFetchedEvent(ObjectElement fetchedXmlContent, String url) {
      super(RootPanel.get());
      this.fetchedXmlContent = fetchedXmlContent;
      this.url = url;
    }

    /**
     * Returns the fetched content. If the content could not be parsed, the
     * ObjectElement has no child Element.
     *
     * @return The Xml fetched content
     */
    public ObjectElement getFetchedXmlContent() {
      return this.fetchedXmlContent;
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
   * Method to be invoked when a pending XML content fetch completes.
   *
   * @param event event encapsulating the fetched data.
   */
  void onXmlContentFetched(XmlContentFetchedEvent event);

}
