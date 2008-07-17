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

import com.google.gwt.ajaxsearch.client.impl.GResult;
import com.google.gwt.ajaxsearch.client.impl.GblogSearch;
import com.google.gwt.ajaxsearch.client.impl.GbookSearch;
import com.google.gwt.ajaxsearch.client.impl.GimageSearch;
import com.google.gwt.ajaxsearch.client.impl.GlocalSearch;
import com.google.gwt.ajaxsearch.client.impl.GnewsSearch;
import com.google.gwt.ajaxsearch.client.impl.GvideoSearch;
import com.google.gwt.ajaxsearch.client.impl.GwebSearch;
import com.google.gwt.ajaxsearch.jsio.client.impl.Extractor;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

import java.util.Date;

/**
 * The base class from which all search result types derive.
 */
public class Result {
  @SuppressWarnings("unused")
  private static final Extractor<Result> __extractor = new Extractor<Result>() {
    public Result fromJS(JavaScriptObject jso) {
      return createPeer(jso);
    }

    public JavaScriptObject toJS(Result o) {
      return o.jsoPeer;
    }
  };

  /**
   * Invoke cloneNode on an Element. This needs to be moved into DOM.
   */
  static native Element cloneNode(Element elt) /*-{
    return elt.cloneNode(true);
  }-*/;

  /**
   * Takes a JSON-style result object and creates a Result of the most specific
   * subtype to represent it.
   * 
   * @param obj the GResult object
   * @return a subtype of Result
   */
  static Result createPeer(JavaScriptObject obj) {
    String resultClass = GResult.IMPL.getGsearchResultClass(obj);

    if (GblogSearch.RESULT_CLASS.equals(resultClass)) {
      return new BlogResult(obj);
    } else if (GbookSearch.RESULT_CLASS.equals(resultClass)) {
      return new BookResult(obj);
    } else if (GimageSearch.RESULT_CLASS.equals(resultClass)) {
      return new ImageResult(obj);
    } else if (GlocalSearch.RESULT_CLASS.equals(resultClass)) {
      return new LocalResult(obj);
    } else if (GnewsSearch.RESULT_CLASS.equals(resultClass)) {
      return new NewsResult(obj);
    } else if (GvideoSearch.RESULT_CLASS.equals(resultClass)) {
      return new VideoResult(obj);
    } else if (GwebSearch.RESULT_CLASS.equals(resultClass)) {
      return new WebResult(obj);
    }

    return new Result(obj);
  }

  @SuppressWarnings("deprecation")
  static Date makeDate(String date) {
    return new Date(date);
  }

  static Widget makeWidget(Element elt) {
    Widget toReturn = new SimplePanel();
    DOM.appendChild(toReturn.getElement(), cloneNode(elt));
    return toReturn;
  }

  /**
   * Caches the result from getHtml().
   */
  private Widget htmlWidget;

  private final JavaScriptObject jsoPeer;

  public Result() {
    this(GResult.IMPL.construct());
  }

  Result(JavaScriptObject jsoPeer) {
    this.jsoPeer = jsoPeer;
    getImpl().bind(jsoPeer, this);
  }

  /**
   * A pre-rendered representation of the search result. The Result object will
   * produce only one Widget. Repeated calls will return the same Widget.
   * 
   * @return A Widget that will display the Result's data.
   */
  public Widget getHtml() {
    if (htmlWidget == null) {
      htmlWidget = makeWidget(getImpl().getHtml(this));
    }
    return htmlWidget;
  }
  
  String getHtmlRaw() {
    return getImpl().getHtml(this).toString();
  }
  
  GResult getImpl() {
    return GResult.IMPL;
  }

  /** 
   * Returns the underlying JavaScript Object (used for unit testing).
   * @return the underlying JavaScript Object (used for unit testing).
   */
  JavaScriptObject getJso() {
    return jsoPeer;
  }
}
