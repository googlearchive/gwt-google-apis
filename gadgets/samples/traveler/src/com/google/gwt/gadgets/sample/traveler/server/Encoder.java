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
package com.google.gwt.gadgets.sample.traveler.server;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

/**
 * Utility class encoding {@link Location} JSON.
 */
public class Encoder {
  private static final DateFormat format = DateFormat.getDateTimeInstance(
      DateFormat.MEDIUM, DateFormat.MEDIUM);

  private Encoder() {
  }

  private static String escape(String str) {
    return str.replaceAll("\"", "\\\\\"");
  }

  private static synchronized String format(Date date) {
    return format.format(date);
  }

  private static StringBuilder appendAsJson(Location loc, StringBuilder sb,
      boolean withKey) {
    sb.append("{");
    if (withKey) {
      sb.append("\"key\":\"" + escape(loc.getKey()) + "\", ");
    }
    sb.append("\"title\":\"" + escape(loc.getTitle()) + "\", ");
    sb.append("\"description\":\"" + escape(loc.getDescription()) + "\", ");
    sb.append("\"date\":\"" + escape(format(loc.getDate())) + "\", ");
    sb.append("\"latitude\":" + loc.getLatitude() + ", ");
    sb.append("\"longitude\":" + loc.getLongitude());
    sb.append("}");
    return sb;
  }

  public static String locationsAsJson(List<Location> locs, boolean withKeys) {
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    sb.append("[");
    for (Location loc : locs) {
      if (first) {
        first = false;
      } else {
        sb.append(",");
      }
      appendAsJson(loc, sb, withKeys);
    }
    sb.append("]");
    return sb.toString();
  }
}
