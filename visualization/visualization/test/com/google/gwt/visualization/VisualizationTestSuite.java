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
package com.google.gwt.visualization;

import com.google.gwt.junit.tools.GWTTestSuite;
import com.google.gwt.visualization.client.AjaxLoaderTest;
import com.google.gwt.visualization.client.AnnotatedTimeLineTest;
import com.google.gwt.visualization.client.AreaChartTest;
import com.google.gwt.visualization.client.ArrowFormatTest;
import com.google.gwt.visualization.client.BarChartTest;
import com.google.gwt.visualization.client.BarFormatTest;
import com.google.gwt.visualization.client.ChartAreaTest;
import com.google.gwt.visualization.client.ColorFormatTest;
import com.google.gwt.visualization.client.ColorTest;
import com.google.gwt.visualization.client.ColumnChartTest;
import com.google.gwt.visualization.client.CommonChartOptionsTest;
import com.google.gwt.visualization.client.CommonOptionsTest;
import com.google.gwt.visualization.client.CoreAreaChartTest;
import com.google.gwt.visualization.client.CoreBarChartTest;
import com.google.gwt.visualization.client.CoreColumnChartTest;
import com.google.gwt.visualization.client.CoreComboChartTest;
import com.google.gwt.visualization.client.CoreLineChartTest;
import com.google.gwt.visualization.client.CorePieChartTest;
import com.google.gwt.visualization.client.CoreScatterChartTest;
import com.google.gwt.visualization.client.DataColumnTest;
import com.google.gwt.visualization.client.DataTableTest;
import com.google.gwt.visualization.client.DataViewTest;
import com.google.gwt.visualization.client.DateFormatTest;
import com.google.gwt.visualization.client.GaugeTest;
import com.google.gwt.visualization.client.GeoMapTest;
import com.google.gwt.visualization.client.ImageAreaChartTest;
import com.google.gwt.visualization.client.ImageBarChartTest;
import com.google.gwt.visualization.client.ImageChartTest;
import com.google.gwt.visualization.client.ImageLineChartTest;
import com.google.gwt.visualization.client.ImagePieChartTest;
import com.google.gwt.visualization.client.ImageSparklineChartTest;
import com.google.gwt.visualization.client.IntensityMapTest;
import com.google.gwt.visualization.client.LineChartTest;
import com.google.gwt.visualization.client.MapVisualizationTest;
import com.google.gwt.visualization.client.MotionChartTest;
import com.google.gwt.visualization.client.NumberFormatTest;
import com.google.gwt.visualization.client.OrgChartTest;
import com.google.gwt.visualization.client.PatternFormatTest;
import com.google.gwt.visualization.client.PieChartTest;
import com.google.gwt.visualization.client.PropertiesTest;
import com.google.gwt.visualization.client.QueryTest;
import com.google.gwt.visualization.client.ScatterChartTest;
import com.google.gwt.visualization.client.SelectionTest;
import com.google.gwt.visualization.client.TableTest;
import com.google.gwt.visualization.client.TimeOfDayTest;
import com.google.gwt.visualization.client.ToolbarTest;
import com.google.gwt.visualization.client.visualizations.corechart.OptionsTest;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * TestSuite for the Visualization API.
 *
 * Running GWTTestCases as a suite speeds up the execution when you have many
 * classes.
 */
public class VisualizationTestSuite extends GWTTestSuite {
  public static Test suite() {
    TestSuite suite = new TestSuite("Test for the Visualization API");

    // To run a single test, you can update the path expression in the property:
    //    gwt.junit.testcase.includes
    suite.addTestSuite(AjaxLoaderTest.class);
    suite.addTestSuite(AnnotatedTimeLineTest.class);
    suite.addTestSuite(AreaChartTest.class);
    suite.addTestSuite(ArrowFormatTest.class);
    suite.addTestSuite(BarChartTest.class);
    suite.addTestSuite(BarFormatTest.class);
    suite.addTestSuite(ChartAreaTest.class);
    suite.addTestSuite(ColorFormatTest.class);
    suite.addTestSuite(ColorTest.class);
    suite.addTestSuite(ColumnChartTest.class);
    suite.addTestSuite(CommonChartOptionsTest.class);
    suite.addTestSuite(CommonOptionsTest.class);
    suite.addTestSuite(CoreAreaChartTest.class);
    suite.addTestSuite(CoreBarChartTest.class);
    suite.addTestSuite(CoreColumnChartTest.class);
    suite.addTestSuite(CoreComboChartTest.class);
    suite.addTestSuite(CoreLineChartTest.class);
    suite.addTestSuite(CorePieChartTest.class);
    suite.addTestSuite(CoreScatterChartTest.class);
    suite.addTestSuite(DataColumnTest.class);
    suite.addTestSuite(DataTableTest.class);
    suite.addTestSuite(DataViewTest.class);
    suite.addTestSuite(DateFormatTest.class);
    suite.addTestSuite(GaugeTest.class);
    suite.addTestSuite(GeoMapTest.class);
    suite.addTestSuite(ImageAreaChartTest.class);
    suite.addTestSuite(ImageBarChartTest.class);
    suite.addTestSuite(ImageChartTest.class);
    suite.addTestSuite(ImageLineChartTest.class);
    suite.addTestSuite(ImagePieChartTest.class);
    suite.addTestSuite(ImageSparklineChartTest.class);
    suite.addTestSuite(IntensityMapTest.class);
    suite.addTestSuite(LineChartTest.class);
    suite.addTestSuite(MapVisualizationTest.class);
    suite.addTestSuite(MotionChartTest.class);
    suite.addTestSuite(NumberFormatTest.class);
    suite.addTestSuite(OrgChartTest.class);
    suite.addTestSuite(PatternFormatTest.class);
    suite.addTestSuite(PieChartTest.class);
    suite.addTestSuite(PropertiesTest.class);
    suite.addTestSuite(QueryTest.class);
    suite.addTestSuite(SelectionTest.class);
    suite.addTestSuite(ScatterChartTest.class);
    suite.addTestSuite(TableTest.class);
    suite.addTestSuite(TimeOfDayTest.class);
    suite.addTestSuite(ToolbarTest.class);

    // Corechart tests.
    suite.addTestSuite(OptionsTest.class);

    return suite;
  }
}
