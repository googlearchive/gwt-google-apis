/*
 * Copyright 2010 Google Inc.
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
package com.google.gwt.visualization.sample.visualizationshowcase.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.AbstractDrawOptions;
import com.google.gwt.visualization.client.CommonChartOptions;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.visualizations.Visualization;

/**
 * Demonstrates ad hoc visualization wrapping.
 *
 * @see <a
 *      href="http://visapi-gadgets.googlecode.com/svn/trunk/pilesofmoney/doc.html"
 *      > Piles of Money Reference. </a>
 */
public class MoneyDemo implements LeftTabPanel.WidgetProvider {
  private Widget widget;

  public MoneyDemo() {
    CommonChartOptions options = CommonChartOptions.create();

    options.setWidth(120);
    options.setHeight(40);
    options.setTitle("Reveneues By Country");

    DataTable data = DataTable.create();

    data.addColumn(ColumnType.STRING, "Label");
    data.addColumn(ColumnType.NUMBER, "Value");
    data.addRows(4);
    data.setValue(0, 0, "France");
    data.setValue(1, 0, "Germany");
    data.setValue(2, 0, "USA");
    data.setValue(3, 0, "Poland");
    data.setCell(0, 1, 10, "$10,000", null);
    data.setCell(1, 1, 30, "$30,000", null);
    data.setCell(2, 1, 20, "$20,000", null);
    data.setCell(3, 1, 7.5, "$7,500", null);

    widget = new Visualization<AbstractDrawOptions>(data, options) {
      @Override
      protected native JavaScriptObject createJso(Element div) /*-{
        return new $wnd.PilesOfMoney(div);
      }-*/;
    };
  }

  public Widget getWidget() {
    return widget;
  }
}
