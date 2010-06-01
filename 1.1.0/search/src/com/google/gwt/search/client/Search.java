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
package com.google.gwt.search.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.search.client.SearchCompleteHandler.SearchCompleteEvent;
import com.google.gwt.search.client.impl.GSearch;
import com.google.gwt.search.client.impl.GSearchCompleteCallback;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;

/**
 * A base class for various types of Google searches. This class is intended to
 * be used only through its concrete subtypes.
 */
public abstract class Search {

  /**
   * Tracks the results returned by the search.
   */
  public static final class Cursor extends JavaScriptObject {

    protected Cursor() {
    }

    /**
     * Returns the index into the pages array of the current set of results.
     * 
     * @return the index into the pages array of the current set of results.
     */
    public native int getCurrentPageIndex() /*-{
      return this.currentPageIndex;
    }-*/;

    /**
     * Returns the estimated number of results.
     * 
     * @return the estimated number of results.
     */
    public long getEstimatedResultCount() throws NumberFormatException {
      return Long.valueOf(nativeGetEstimatedResultCount());
    }

    /**
     * Returns a URL that can be used to fetch more results rendered in HTML.
     * 
     * @return a URL that can be used to fetch more results rendered in HTML.
     */
    public native String getMoreResultsUrl() /*-{
      return this.moreResultsUrl;
    }-*/;

    /**
     * Returns an array of pages of results.
     * 
     * @return an array of pages of results.
     */
    public native JsArray<Page> getPages() /*-{
      return this.pages;
    }-*/;

    private native String nativeGetEstimatedResultCount() /*-{
      return this.estimatedResultCount;
    }-*/;
  }

  /**
   * Represents one page in a search.
   */
  public static final class Page extends JavaScriptObject {

    protected Page() {
    }

    /**
     * Returns a text label associated with the entry e.g., "1", "2", "3", or
     * "4".
     * 
     * @return a text label associated with the entry e.g., "1", "2", "3", or
     *         "4".
     */
    public native String getLabel() /*-{
      return String(this.label);
    }-*/;

    /**
     * Returns the value that will be used in the <code>&start</code> URL
     * argument to request a bundle of results.
     * 
     * @return the value that will be used in the <code>&start</code> URL
     *         argument to request a bundle of results
     */
    public native String getStart() /*-{
      return this.start;
    }-*/;
  }

  /**
   * Unimplemented. Refer to {@link SearchControl#createPeer(JavaScriptObject)}
   * for why this is unimplemented.
   */
  @SuppressWarnings("unused")
  static Search createPeer(JavaScriptObject obj) {
    throw new RuntimeException(
        "Received a Search that was created outside of Java API.");
  }

  /**
   * The JSIO API binding to use.
   */
  private final GSearch impl;

  /**
   * The backing JSO, named per JSIO spec.
   */
  private final JavaScriptObject jsoPeer;

  /**
   * A collection of handlers to notify when the Search receives results.
   */
  private SearchResultsHandlerCollection handlers;

  /**
   * Package-protected constructor to restrict the use of the class outside of
   * the AjaxSearch package.
   * 
   * @param impl The JSIO interface to use.
   */
  Search(GSearch impl) {
    this.impl = impl;
    this.jsoPeer = impl.construct();
    impl.bind(jsoPeer, this);
  }

  /**
   * Add a SearchCompleteHandler to receive notifications when Results are
   * created after executing the Search. This search will fire when searches
   * created by {@link Search#execute(String)} complete.
   * 
   * @param handler The handler to add
   * @deprecated use {@link #addSearchResultsHandler(SearchResultsHandler )}
   */
  @Deprecated
  public void addSearchCompleteHandler(final SearchCompleteHandler handler) {
    // Delegate to addSearchResultsHandler()
    addSearchResultsHandler(new SearchResultsHandler() {
      public void onSearchResults(SearchResultsEvent event) {
        JsArray<? extends Result> results = event.getResults();
        for (int i = 0, length = results.length(); i < length; ++i) {
          handler.onSearchComplete(new SearchCompleteEvent(event.getSearch(),
              results.get(i)));
        }
      }
    });
  }

  /**
   * Add a {@link SearchResultsHandler} to receive notifications when Results
   * are created after executing the Search. This search will fire when searches
   * created by {@link Search#execute(String)} complete.
   * 
   * @param handler The handler to add
   */
  public void addSearchResultsHandler(SearchResultsHandler handler) {
    if (handlers == null) {
      handlers = new SearchResultsHandlerCollection();
      impl.setSearchCompleteCallback(this, null, new GSearchCompleteCallback() {
        @Override
        public void onSearchResult() {
          JsArray<? extends Result> results = getResults();
          handlers.fireResult(Search.this, results);
        }
      });
    }
    handlers.add(handler);
  }

  /**
   * Remove the results obtained by the last call to {@link #execute(String)}.
   */
  public void clearResults() {
    impl.clearResults(this);
  }

  /**
   * Regenerate the HTML associated with a Result object. This can be used to
   * regenerate the pre-rendered HTML associated with a Result object if
   * {@link #setNoHtmlGeneration()} had been previously called on the Search.
   * 
   * @param result The Result to re-render.
   */
  // XXX probably don't need to expose this if we never offer the ability to
  // get results without HTML
  public void createResultHtml(Result result) {
    impl.createResultHtml(this, result);
  }

  /**
   * Programmatically execute a query.
   * 
   * @param query The search query to execute.
   */
  public void execute(String query) {
    impl.execute(this, query);
  }

  /**
   * According to the terms of service for the Google AJAX Search API, it is
   * necessary to display the attribution Element near search result if you are
   * using a raw Searcher.
   * 
   * Be prepared for this method to return <code>null</code> as an attribution
   * is not always available.
   */
  public Widget getAttribution() {
    Element attribution = impl.getAttribution(this);
    if (attribution == null) {
      return null;
    }
    return Result.makeWidget(attribution);
  }

  /**
   * After a successful search, this object will be populated. You can retrieve
   * results from additional pages.
   * 
   * @return on success, a valid cursor object. otherwise, returns
   *         <code>null</code>
   */
  public Cursor getCursor() {
    return (Cursor) impl.getCursor(this);
  }

  /**
   * Retrieve the results from the previously-execute query. The exact types of
   * the Results will vary based upon the subtype of Search that is in use.
   * 
   * @return A List of {@link Result} objects.
   */
  public JsArray<? extends Result> getResults() {
    return impl.getResults(this);
  }

  /**
   * After a search completes, this method allows you to fetch another bundle of
   * search results. Applications specify which page by using the page argument.
   * This value is then used to index the array returned by
   * {@link Cursor#getPages()} which ultimately is used to compute the value of
   * the <code>&start</code> url argument.
   * 
   * @param pageNumber supplies the page number of the results that the
   *          application wants. This value is range checked relative to the
   *          <code>.cursor.pages</code> property and no operation is performed
   *          if the page is out of range or if the underlying searcher does not
   *          have a cursor property.
   */
  public void gotoPage(int pageNumber) {
    impl.gotoPage(this, pageNumber);
  }

  /**
   * Remove a previously-added SearchCompleteHandler.
   * 
   * @param l The SearchCompleteHandlerer to remove
   * @deprecated
   */
  @Deprecated
  public void removeSearchCompleteHandler(SearchCompleteHandler l) {
    // now a no-op... Too much trouble to implement and I think its seldomly
    // used.
  }

  /**
   * Remove a previously-added SearchCompleteHandler.
   * 
   * @param l The SearchCompleteHandlerer to remove
   */
  public void removeSearchResultsHandler(SearchResultsHandler l) {
    if (l != null) {
      handlers.remove(l);
    }
  }

  /**
   * Set the target window to use if the user clicks on a displayed result's
   * link.
   * 
   * @param target The new LinkTarget for new Results created by the Search
   *          object.
   */
  public void setLinkTarget(LinkTarget target) {
    impl.setLinkTarget(this, target.getValue());
  }

  /**
   * Invoking this method will disable {@link Result#getHtml()} method in any
   * results created by the Search. Invoking this method will decrease query
   * execution time if you do not intend to use pre-rendered results.
   */
  // XXX do we really want this?
  public void setNoHtmlGeneration() {
    impl.setNoHtmlGeneration(this);
  }

  /**
   * Add additional terms to any query executed by the Search. This can be used
   * to create more specific queries.
   * 
   * @param addition Additional parameters to add to any queries executed by the
   *          Search.
   * @see <a href="http://www.google.com/help/refinesearch.html">Refine
   *      Search</a>
   */
  public void setQueryAddition(String addition) {
    impl.setQueryAddition(this, addition);
  }

  /**
   * Request more or fewer results when executing a search. This is taken only
   * as a hint and may not be honored when the search is executed.
   * 
   * @param size The desired number of results.
   */
  public void setResultSetSize(ResultSetSize size) {
    impl.setResultSetSize(this, size.getValue());
  }

  /**
   * Adds a user-specified CSS class suffix to the pre-rendered HTML provided by
   * Results that are created by the Search.
   * 
   * @param label A CSS class suffix.
   */
  public void setUserDefinedClassSuffix(String label) {
    impl.setUserDefinedClassSuffix(this, label);
  }

  /**
   * Used by SearchControl when it is used to displayed search results. This
   * will set the group heading for the results associated with a particular
   * search.
   * 
   * @param label The label to use when displayed Results associated with a
   *          Search in a SearchControl
   */
  public void setUserDefinedLabel(String label) {
    impl.setUserDefinedLabel(this, label);
  }

}
