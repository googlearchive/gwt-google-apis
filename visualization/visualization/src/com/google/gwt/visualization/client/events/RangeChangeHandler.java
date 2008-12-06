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
package com.google.gwt.visualization.client.events;

import com.google.gwt.visualization.client.Properties;

import java.util.Date;

/**
 * This class handles range events for visualizations such as AnnotatedTimeLine.
 */
public abstract class RangeChangeHandler extends Handler {
  /**
   * This event is fired when the user changes the date range of the
   * visualization.
   */
  public class RangeChangeEvent {
    private Date from;
    private Date to;

    public RangeChangeEvent(Date from, Date to) {
      this.from = from;
      this.to = to;
    }

    public Date getFrom() {
      return from;
    }

    public Date getTo() {
      return to;
    }
  }

  public abstract void onRangeChange(RangeChangeEvent event);

  @Override
  protected void onEvent(Properties properties) {
    Date from = properties.getDate("from");
    Date to = properties.getDate("to");
    onRangeChange(new RangeChangeEvent(from, to));
  }
}