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
package com.google.gwt.ajaxsearch.client;

import com.google.gwt.ajaxsearch.client.impl.GSearch;
import com.google.gwt.ajaxsearch.client.impl.GSearchCompleteCallback;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.ui.Widget;

import java.util.List;

/**
 * A base class for various types of Google searches. This class is intended to
 * be used only through its concrete subtypes.
 */
public abstract class Search {
  /**
   * Unimplemented. Refer to {@link SearchControl#createPeer(JavaScriptObject)}
   * for why this is unimplemented.
   */
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
   * A collection of listeners to notify when the Search receives results.
   */
  private SearchListenerCollection listeners;

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
   * Add a SearchListener to receive notifications when Results are created
   * after executing the Search. This search will fire when searches created by
   * {@link Search#execute(String)} complete.
   * 
   * @param l The listener to add
   */
  public void addSearchListener(SearchListener l) {
    if (listeners == null) {
      listeners = new SearchListenerCollection();
      impl.setSearchCompleteCallback(this, null, new GSearchCompleteCallback() {
        @Override
        public void onSearchResult() {
          for (Result result : getResults()) {
            listeners.fireResult(Search.this, result);
          }
        }
      });
    }
    listeners.add(l);
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
   */
  public Widget getAttribution() {
    return Result.makeWidget(impl.getAttribution(this));
  }

  /**
   * Retrieve the results from the previously-execute query. The exact types of
   * the Results will vary based upon the subtype of Search that is in use.
   * 
   * @return A List of {@link Result} objects.
   */
  public List<? extends Result> getResults() {
    return impl.getResults(this);
  }

  /**
   * Remove a previously-added SearchListener.
   * 
   * @param l The SearchListener to remove
   */
  public void removeSearchListener(SearchListener l) {
    if (listeners != null) {
      listeners.remove(l);
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
   * @see <a href="http://www.google.com/help/refinesearch.html">Refine Search</a>
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
