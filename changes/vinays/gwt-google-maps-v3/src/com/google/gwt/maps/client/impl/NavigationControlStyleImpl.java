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
package com.google.gwt.maps.client.impl;

import com.google.gwt.maps.client.NavigationControlStyle;

/**
 * 
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public class NavigationControlStyleImpl {
  
  public int getValue(NavigationControlStyle style) {
    return __getValue(style.toString());
  }

  private native int __getValue(String style) /*-{
    return $wnd.google.maps.NavigationControlStyle[style];
  }-*/;

}
