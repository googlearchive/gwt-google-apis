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

package com.google.gwt.visualization.client;

/**
 * Representation of time of day that may be used as a value in a data table (or
 * data view).
 */
public class TimeOfDay implements Comparable<TimeOfDay> {
  /**
   * Exception indicating an invalid value being set for a time of day field.
   */
  @SuppressWarnings("serial")
  public static class BadTimeException extends Exception {
    BadTimeException(int i, String field) {
      super(i + " is an invalid " + field + ".");
    }
  }

  private int hour;
  private int minute;
  private int second;
  private int millisecond;

  public TimeOfDay() {
  }

  public TimeOfDay(int hour, int minute, int second, int millisecond)
      throws BadTimeException {
    setHour(hour);
    setMinute(minute);
    setSecond(second);
    setMillisecond(millisecond);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    TimeOfDay right = (TimeOfDay) obj;
    return hour == right.hour && minute == right.minute
        && second == right.second && millisecond == right.millisecond;
  }

  public int getHour() {
    return hour;
  }

  public int getMillisecond() {
    return millisecond;
  }

  public int getMinute() {
    return minute;
  }

  public int getSecond() {
    return second;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + hour;
    result = prime * result + millisecond;
    result = prime * result + minute;
    result = prime * result + second;
    return result;
  }
  
  public int compareTo(TimeOfDay that) {
    // Safe because both are positive integers
    return millis() - that.millis();
  }
  
  final int millis() {
    // Safe because we are looking at number smaller than 100M, well within range of int
    int minutes = 60 * hour + minute;
    int seconds = 60 * minutes + second;
    int millis = 1000 * seconds + millisecond;
    return millis;
  }

  public void setHour(int hour) throws BadTimeException {
    if (hour < 0 || hour > 23) {
      throw new BadTimeException(hour, "hour");
    }
    this.hour = hour;
  }

  public void setMillisecond(int millisecond) throws BadTimeException {
    if (millisecond < 0 || millisecond > 999) {
      throw new BadTimeException(millisecond, "millisecond");
    }
    this.millisecond = millisecond;
  }

  public void setMinute(int minute) throws BadTimeException {
    if (minute < 0 || minute > 59) {
      throw new BadTimeException(minute, "minute");
    }
    this.minute = minute;
  }

  public void setSecond(int second) throws BadTimeException {
    if (second < 0 || second > 59) {
      throw new BadTimeException(second, "second");
    }
    this.second = second;
  }

  @Override
  public String toString() {
    return "{hour : " + hour + ", minute : " + minute + ", second : " + second
        + ", millisecond : " + millisecond + "}";
  }
}
