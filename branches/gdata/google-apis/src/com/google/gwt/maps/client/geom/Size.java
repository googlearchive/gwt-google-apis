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
package com.google.gwt.maps.client.geom;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.impl.SizeImpl;

/**
 * 
 */
public final class Size {

  private static final SizeImpl impl = (SizeImpl) GWT.create(SizeImpl.class);

  static Size createPeer(JavaScriptObject jso) {
    return new Size(jso);
  }

  private final JavaScriptObject jsoPeer;

  public Size(int width, int height) {
    jsoPeer = impl.construct(width, height);
  }

  private Size(JavaScriptObject jso) {
    this.jsoPeer = jso;
  }

  public boolean equals(Object obj) {
    if (obj instanceof Size) {
      return impl.equals((Size) obj);
    }
    return false;
  }

  public int getHeight() {
    return impl.getHeight(jsoPeer);
  }

  public int getWidth() {
    return impl.getWidth(jsoPeer);
  }

  public String toString() {
    return impl.toString(jsoPeer);
  }

}
