/*
 * Copyright 2009 Google Inc.
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
package com.google.gwt.visualization.client;

import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.formatters.DateFormat;
import com.google.gwt.visualization.client.formatters.DateFormat.FormatType;
import com.google.gwt.visualization.client.formatters.DateFormat.Options;
import com.google.gwt.visualization.client.visualizations.PieChart;

import java.util.Date;

/**
 * Tests for the DateFormat class.
 */
public class DateFormatTest extends VisualizationTest {
  @Override
  public String getModuleName() {
    return "com.google.gwt.visualization.VisualizationTest";
  }
  
  public void testDateFormat() {
    loadApi(new Runnable() {
      public void run() {
        DataTable dataTable = DataTable.create();
        dataTable.addColumn(ColumnType.STRING, "Name", "name");
        dataTable.addColumn(ColumnType.DATE, "Start Date", "sd");
        dataTable.addColumn(ColumnType.DATETIME, "Next Quarterly Meeting", 
            "meeting");
        dataTable.addRows(5);
        int r = 0;
        dataTable.setValue(r, 0, "hillel");
        dataTable.setValue(r, 1, createDateFromUTC("March 11, 2005"));
        dataTable.setValue(r, 2, createDateFromUTC("February 16, 2009, 11:00"));
        r++;
        dataTable.setValue(r, 0, "yoav");
        dataTable.setValue(r, 1, createDateFromUTC("August 6, 1975"));
        dataTable.setValue(r, 2, createDateFromUTC("April 1, 2009, 14:00"));
        r++;
        dataTable.setValue(r, 0, "niv");
        dataTable.setValue(r, 1, createDateFromUTC("April 21, 2004"));
        dataTable.setValue(r, 2, createDateFromUTC("July 4, 2009, 22:00"));
        r++;
        dataTable.setValue(r, 0, "roi");
        dataTable.setValue(r, 1, createDateFromUTC("September 11, 2001"));
        dataTable.setValue(r, 2, createDateFromUTC("January 30, 2009, 8:00"));
        dataTable.setFormattedValue(r, 2, "30/01/2009, 8:00");
        r++;
        dataTable.setValue(r, 0, "yael");
        dataTable.setValue(r, 1, createDateFromUTC("January 15, 2005"));
        dataTable.setValue(r, 2, createDateFromUTC("May 18, 2009, 15:00"));
        r++;

        // Verify that explicitly setting the formatted value overrides the default
        // date.
        assertEquals("30/01/2009, 8:00", dataTable.getFormattedValue(3, 2));

        // Test date formatting using formatType and a timezone.
        Options options;
        
        options = Options.create();
        options.setPattern(FormatType.LONG);
        options.setTimeZone(2);
        DateFormat formatter = DateFormat.create(options);
        formatter.format(dataTable, 1);
        formatter.format(dataTable, 2);
        assertEquals("March 11, 2005", dataTable.getFormattedValue(0, 1));
        assertEquals("February 16, 2009 1:00:00 PM UTC+2",
                     dataTable.getFormattedValue(0, 2));
        // Verify that using a formatter on a column overrides all previously set
        // formatted-values.
        assertEquals("January 30, 2009 10:00:00 AM UTC+2",
                     dataTable.getFormattedValue(3, 2));

        // Test formatting date using pattern and timezone.
        
        options = Options.create();
        options.setPattern("dd/MM/yyyy");
        options.setTimeZone(2);
        formatter = DateFormat.create(options);
        formatter.format(dataTable, 1);
        assertEquals("11/03/2005", dataTable.getFormattedValue(0, 1));
        options = Options.create();
        options.setPattern("dd/MM/yyyy, HH:mm");
        options.setTimeZone(2);
        formatter = DateFormat.create(options);
        formatter.format(dataTable, 2);
        assertEquals("16/02/2009, 13:00", dataTable.getFormattedValue(0, 2));
      }
    });
  }

  @Override
  protected String getVisualizationPackage() {
    return PieChart.PACKAGE;
  }

  private Date createDateFromUTC(String utcDate) {
    return new Date((long) createDoubleFromUTC(utcDate));
  }

  private native double createDoubleFromUTC(String utcDate) /*-{
    var utc = new Date(utcDate);
    var result = new Date(utc.getTime() - utc.getTimezoneOffset() * 60000);
    return result.getTime();
  }-*/;
}
