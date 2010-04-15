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
package com.google.gwt.visualization.client.visualizations;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.AbstractDrawOptions;

/**
 * Visualizations toolbar. A tool for visualizations, for exporting purposes.
 * 
 * @see <a href=
 *      "http://code.google.com/apis/visualization/documentation/gallery/toolbar.html"
 *      > Visualization Toolbar Reference</a>
 */
public class Toolbar extends Widget {

  public static final String PACKAGE = "default";

  private JsArray<Component> components;
  private DivElement div;

  /**
   * Enum for component type.
   */
  public enum Type {
    HTMLCODE("htmlcode"), CSV("csv"), HTML("html"), IGOOGLE("igoogle");

    private String typeCode;

    Type(String typeCode) {
      this.typeCode = typeCode;
    }

    public String getTypeCode() {
      return typeCode;
    }
  }

  /**
   * Component for the toolbar.
   */
  public static class Component extends AbstractDrawOptions {
    public static Component create() {
      return JavaScriptObject.createObject().cast();
    }

    protected Component() {
    }

    public final native void setDataSource(String dataSource) /*-{
      this.datasource = dataSource;
    }-*/;

    public final native void setGadget(String gadget) /*-{
      this.gadget = gadget;
    }-*/;

    public final void setType(Type type) {
      setType(type.typeCode);
    }

    public final native void setUserprefs(String userprefs) /*-{
      this.userprefs = userprefs;
    }-*/;

    private native void setType(String type) /*-{
      this.type = type;
    }-*/;
  }

  public Toolbar() {
    super();
    div = Document.get().createDivElement();
    setElement(div);
    setStyleName("gwt-viz-container");
    components = createComponents();
  }

  public void addComponent(Component value) {
    components.set(components.length(), value);
  }

  @Override
  protected void onLoad() {
    nativeDraw(div, components);
  }

  private native JsArray<Component> createComponents() /*-{
    return [];
  }-*/;

  private native void nativeDraw(Element div, JsArray<Component> components) /*-{
    $wnd.google.visualization.drawToolbar(div, components);
  }-*/;
}
