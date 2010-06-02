/* Copyright (c) 2010 Vinay Inc.
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
import com.google.gwt.maps.client.base.HasPoint;
import com.google.gwt.maps.client.base.HasSize;
import com.google.gwt.maps.client.overlay.impl.MarkerImageImpl;

/**
 * This class implements {@link HasMarkerImage}.
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public class MarkerImage implements HasMarkerImage {
  
  public static class Builder {
    final private String url;
    private JavaScriptObject size = null;
    private JavaScriptObject origin = null;
    private JavaScriptObject anchor = null;
    private JavaScriptObject scaledSize = null;
    
    public Builder(String url) {
      this.url = url;
    }
    
    public Builder setSize(HasSize size) {
      this.size = size.getJso();
      return this;
    }
    
    public Builder setOrigin(HasPoint origin) {
      this.origin = origin.getJso();
      return this;
    }
    
    public Builder setAnchor(HasPoint anchor) {
      this.anchor = anchor.getJso();
      return this;
    }
    
    public Builder setScaledSize(HasSize scaledSize) {
      this.scaledSize = scaledSize.getJso();
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
