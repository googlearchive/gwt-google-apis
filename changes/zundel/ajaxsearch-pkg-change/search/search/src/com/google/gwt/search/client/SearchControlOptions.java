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

import com.google.gwt.search.client.impl.GdrawOptions;
import com.google.gwt.search.client.impl.GsearcherOptions;
import com.google.gwt.core.client.GWT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Used to configure a SearchControl.
 */
public final class SearchControlOptions {

  final GdrawOptions drawOptions = GWT.create(GdrawOptions.class);

  /**
   * Maintain order in which the Search objects are added.
   */
  final List<Search> searchers = new ArrayList<Search>();

  /**
   * Associate Search objects with options objects.
   */
  final Map<Search, GsearcherOptions> searcherOptions = new HashMap<Search, GsearcherOptions>();

  /**
   * The last value set by a call to
   * {@link SearchControlOptions#setKeepLabel(java.lang.String)}. It is an
   * Object because it can be a String or a {@link KeepLabel}.
   */
  Object keepLabel;

  /**
   * The last value set by a calls to {@link #setLinkTarget}.
   */
  LinkTarget linkTarget;

  /**
   * The last value set by a call to {@link #setTimeoutInterval}.
   */
  TimeoutInterval timeoutInterval;

  /**
   * This constructor will create an empty options object.
   */
  public SearchControlOptions() {
  }

  /**
   * A convenience constructor to add several Searches in a default
   * configuration.
   * 
   * @param searches The Searches to add to the SearchControl
   */
  public SearchControlOptions(Search[] searches) {
    for (int i = 0; i < searches.length; i++) {
      add(searches[i]);
    }
  }

  /**
   * Add a Search to be executed by the SearchControl.
   * 
   * @param s The Search to execute
   */
  public void add(Search s) {
    add(s, ExpandMode.PARTIAL);
  }

  /**
   * Add a Search to be executed by the SearchControl.
   * 
   * @param s The Search to execute
   * @param expandMode The initial appearance of the Search's results in the
   *          SearchControl
   */
  public void add(Search s, ExpandMode expandMode) {
    GsearcherOptions options = GWT.create(GsearcherOptions.class);
    options.setExpandMode(expandMode.getValue());

    searchers.add(s);
    searcherOptions.put(s, options);
  }

  /**
   * Populates the SearchControl with a default set of Search objects. This will
   * add:
   * <ol>
   * <li>WebSearch</li>
   * <li>LocalSearch</li>
   * <li>VideoSearch</li>
   * <li>BlogSearch</li>
   * <li>NewsSearch</li>
   * <li>BookSearch</li>
   * <li>ImageSearch</li>
   * </ol>
   */
  public void addDefaultSearches() {
    add(new WebSearch());
    add(new LocalSearch());
    add(new VideoSearch());
    add(new BlogSearch());
    add(new NewsSearch());
    add(new BookSearch());
    add(new ImageSearch());
  }

  /**
   * Sets the DrawMode for the SearchControl to set its overall appearance.
   * 
   * @param mode The desired DrawMode for the SearchControl
   */
  public void setDrawMode(DrawMode mode) {
    drawOptions.setDrawMode(mode.getValue());
  }

  /**
   * Sets the label that will be used for the "keep" command when displayed.
   * 
   * @param label A predefined, localized label to use for the keep controls
   */
  public void setKeepLabel(KeepLabel label) {
    keepLabel = label;
  }

  /**
   * Sets the label that will be used for the "keep" command when displayed.
   * 
   * @param label The new label to use.
   */
  public void setKeepLabel(String label) {
    keepLabel = label;
  }

  /**
   * Sets the target window to use when the user clicks on a search result.
   * 
   * @param target The new LinkTarget for the SearchControl.
   */
  public void setLinkTarget(LinkTarget target) {
    this.linkTarget = target;
  }

  /**
   * Sets the timeout interval to use after the user stops typing into the
   * SearchControl before executing a query.
   * 
   * @param interval The new TimeoutInterval
   */
  public void setTimeoutInterval(TimeoutInterval interval) {
    this.timeoutInterval = interval;
  }

}
