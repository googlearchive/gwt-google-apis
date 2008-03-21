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
package com.google.gwt.maps.client.geocode;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.impl.DurationImpl;

/**
 * An object returned by
 * {@link com.google.gwt.maps.client.geocode.DirectionResults#getDuration()}
 * This object contains two fields: a number indicating the numeric value of the
 * time (in seconds), and a string containing a localized string representation
 * of the time.
 */
public final class Duration {

  static Duration createPeer(JavaScriptObject jsoPeer) {
    return new Duration(jsoPeer);
  }

  private final JavaScriptObject jsoPeer;

  protected Duration(JavaScriptObject jsoPeer) {
    this.jsoPeer = jsoPeer;
  }

  /**
   * Returns a string containing a localized string representation of the time.
   * @return a string containing a localized string representation of the time.
   */
  public String inLocalizedUnits() {
    return DurationImpl.impl.getHtml(jsoPeer);
  }

  /**
   * Returns a number indicating the numeric value of the time (in seconds).
   * @return a number indicating the numeric value of the time (in seconds).
   */
  public int inSeconds() {
    return DurationImpl.impl.getSeconds(jsoPeer);
  }
}
