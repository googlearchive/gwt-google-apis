/* Copyright (c) 2010 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.gwt.maps.client.overlay;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.base.Point;
import com.google.gwt.maps.client.base.Size;
import com.google.gwt.maps.client.overlay.impl.MarkerImageImpl;

/**
 * This class implements {@link HasMarkerImage}.
 */
public class MarkerImage implements HasMarkerImage {
  
  public static class Builder {
    final private String url;
    private Size size = null;
    private Point origin = null;
    private Point anchor = null;
    private Size scaledSize = null;
    
    public Builder(String url) {
      this.url = url;
    }
    
    public Builder setSize(Size size) {
      this.size = size;
      return this;
    }
    
    public Builder setOrigin(Point origin) {
      this.origin = origin;
      return this;
    }
    
    public Builder setAnchor(Point anchor) {
      this.anchor = anchor;
      return this;
    }
    
    public Builder setScaledSize(Size scaledSize) {
      this.scaledSize = scaledSize;
      return this;
    }
    
    public MarkerImage build() {
      return new MarkerImage(url, size, origin, anchor, scaledSize);
    }
  }
  
  final private JavaScriptObject jso;
  
  public MarkerImage(final JavaScriptObject jso) {
    this.jso = jso;
  }
  
  private MarkerImage(String url, JavaScriptObject size,
      JavaScriptObject origin, JavaScriptObject anchor,
      JavaScriptObject scaledSize) {
    this(MarkerImageImpl.impl.construct(url, size, origin, anchor, scaledSize));
  }

  @Override
  public JavaScriptObject getJso() {
    return jso;
  }

}
