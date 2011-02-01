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

import java.util.Date;

/**
 * Date Range with start and end date objects.
 */
public class DateRange {
  private Date start;
  
  private Date end;
  
  public DateRange(Date start, Date end) {
    this.start = start;
    this.end = end;
  }

  public final Date getEnd() {
    return this.end;
  }

  public final Date getStart() {
    return this.start;
  }
}