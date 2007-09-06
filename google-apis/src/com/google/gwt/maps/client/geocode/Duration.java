/*
 * Copyright 2007 Google Inc.
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

public final class Duration {

  static Duration createPeer(JavaScriptObject jsoPeer) {
    return new Duration(jsoPeer);
  }

  private final JavaScriptObject jsoPeer;

  protected Duration(JavaScriptObject jsoPeer) {
    this.jsoPeer = jsoPeer;
  }

  public int inSeconds() {
    return DurationImpl.impl.getSeconds(jsoPeer);
  }

  public String inLocalizedUnits() {
    return DurationImpl.impl.getHtml(jsoPeer);
  }

}
