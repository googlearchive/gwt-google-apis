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
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

import java.util.Date;

/**
 * The base class from which all search result types derive.
 */
public abstract class Result extends JavaScriptObject {
  /**
   * Invoke cloneNode on an Element. This needs to be moved into DOM.
   */
  static native Element cloneNode(Element elt) /*-{
    return elt.cloneNode(true);
  }-*/;

  @SuppressWarnings("deprecation")
  static Date makeDate(String date) {
    return new Date(date);
  }

  static Widget makeWidget(Element elt) {
    Widget toReturn = new SimplePanel();
    DOM.appendChild(toReturn.getElement(), cloneNode(elt));
    return toReturn;
  }

  protected Result() {
    // Required for overlay types
  }

  /**
   * A pre-rendered representation of the search result. The Result object will
   * produce only one Widget. Repeated calls will return the same Widget.
   * 
   * @return A Widget that will display the Result's data.
   */
  public final Widget getHtml() {
    Widget widget = getWidget();
    if (widget == null) {
      Element element = getElement();
      widget = makeWidget(element);
      setWidget(widget);
    }
    return widget;
  }

  public final ResultClass getResultClass() {
    String nativeResultClass = getResultClassString();
    if (nativeResultClass == null) {
      throw new NullPointerException(
          "Couldn't find result class field in Gresult object");
    }
    return ResultClass.fromString(nativeResultClass);
  }

  public final native String getResultClassString() /*-{
    return this["GsearchResultClass"];
  }-*/;

  public final native String getString() /*-{
    return this.titleNoFormatting;
  }-*/;

  private native Element getElement() /*-{
    return this.html;
  }-*/;

  private native Widget getWidget() /*-{
    return this.widget;
  }-*/;
  
  private native void setWidget(Widget widget) /*-{
    this.widget = widget;
  }-*/;
}
