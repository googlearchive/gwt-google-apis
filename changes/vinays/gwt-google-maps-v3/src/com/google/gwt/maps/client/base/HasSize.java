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

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Two-dimensonal size, where width is the distance on the x-axis, and height is
 * the distance on the y-axis.
 * 
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public interface HasSize {
  
  /**
   * Gets the jso.
   */
  public JavaScriptObject getJso();
  
  /**
   * Compares two Sizes.
   */
  public boolean equalsTo(HasSize other);
  
  /**
   * Returns a string representation of this Size.
   */
  public String toString();
  
  /**
   * The height along the y-axis, in pixels.
   */
  public int getHeight();
  
  /**
   * The width along the x-axis, in pixels.
   */
  public int getWidth();

}
