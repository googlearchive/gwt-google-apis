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
package com.google.gwt.maps.client.geom;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.impl.SizeImpl;

/**
 * A Size represents the size in pixels of a rectangular area of the map. The
 * size object has two parameters, width and height. Width is a difference in
 * the x-coordinate; height is a difference in the y-coordinate, of points.
 */
public final class Size {

  private static final SizeImpl impl = GWT.create(SizeImpl.class);

  static Size createPeer(JavaScriptObject jso) {
    return new Size(jso);
  }

  private final JavaScriptObject jsoPeer;

  /**
   * Construct a new Size object.
   * 
   * @param width Width is a difference in the x-coordinate.
   * @param height height is a difference in the y-coordinate, of points.
   */
  public Size(int width, int height) {
    jsoPeer = impl.construct(width, height);
  }

  /**
   * Wrap an existing GSize object.
   * 
   * @param jso the JavaScriptObject to wrap.
   */
  private Size(JavaScriptObject jso) {
    this.jsoPeer = jso;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Size) {
      Size cmp = (Size) obj;
      return (cmp.getHeight() == getHeight() 
          && cmp.getWidth() == getWidth());
    }
    return false;
  }

  /**
   * Returns the height parameter.
   * 
   * @return the height parameter.
   */
  public int getHeight() {
    return impl.getHeight(jsoPeer);
  }

  /**
   * Returns the width parameter.
   * 
   * @return the width parameter.
   */
  public int getWidth() {
    return impl.getWidth(jsoPeer);
  }

  @Override
  public int hashCode() {
    return getWidth() + (101 * getHeight());
  }

  @Override
  public String toString() {
    return impl.toString(jsoPeer);
  }

}
