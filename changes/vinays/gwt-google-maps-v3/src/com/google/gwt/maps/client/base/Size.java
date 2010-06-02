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
package com.google.gwt.maps.client.base;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.base.impl.SizeImpl;

/**
 * This class implements {@link HasSize}.
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public class Size implements HasSize {

  final private SizeImpl impl;
  final private JavaScriptObject jso;
  
  public Size(final SizeImpl impl, final JavaScriptObject jso) {
    this.impl = impl;
    this.jso = jso;
  }
  
  public Size(final JavaScriptObject jso) {
    this.impl = GWT.create(SizeImpl.class);
    this.jso = jso;
  }

  public Size(int width, int height) {
    this.impl = GWT.create(SizeImpl.class);
    this.jso = impl.construct(width, height);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.base.HasSize#equalsTo(com.google.gwt.maps.client.base.HasSize)
   */
  @Override
  public boolean equalsTo(HasSize other) {
    return impl.equals(jso, other.getJso());
  }
  
  /* (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object other) {
    if (other == null || !(other instanceof Size)) {
      return false;
    }
    return equalsTo((HasSize) other);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.base.HasSize#getHeight()
   */
  @Override
  public int getHeight() {
    return impl.getHeight(jso);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.base.HasSize#getJso()
   */
  @Override
  public JavaScriptObject getJso() {
    return jso;
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.base.HasSize#getWidth()
   */
  @Override
  public int getWidth() {
    return impl.getWidth(jso);
  }
  
  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return impl.toString(jso);
  }

}
