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
import com.google.gwt.jsio.client.impl.Extractor;
import com.google.gwt.maps.client.impl.PointImpl;

public final class Point {

  private static final PointImpl impl = (PointImpl) GWT.create(PointImpl.class);

  // TODO: DELETE ME! (needs to function w/o)
  private static final Extractor __extractor = new Extractor() {
    public Object fromJS(JavaScriptObject jso) {
      return createPeer(jso);
    }

    public JavaScriptObject toJS(Object o) {
      return ((Point) o).jsoPeer;
    }
  };

  static Point createPeer(JavaScriptObject jso) {
    return new Point(jso);
  }

  private final JavaScriptObject jsoPeer;

  public Point(int x, int y) {
    jsoPeer = impl.construct(x, y);
  }

  private Point(JavaScriptObject jso) {
    this.jsoPeer = jso;
  }

  public boolean equals(Object obj) {
    if (obj instanceof Point) {
      return impl.equals(jsoPeer, (Point) obj);
    }
    return false;
  }

  public int getX() {
    return impl.getX(jsoPeer);
  }

  public int getY() {
    return impl.getY(jsoPeer);
  }

  public String toString() {
    return impl.toString(jsoPeer);
  }

}
