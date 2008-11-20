/*
 * Copyright 2008 Google Inc.
 * 
 * Licensed uimport com.google.gwt.core.client.JavaScriptObject;
; you may not
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
package com.google.gwt.gears.client.httprequest;

import com.google.gwt.core.client.JavaScriptObject;

public final class ProgressEvent extends JavaScriptObject {

  protected ProgressEvent() {
    // Required for overlay types
  }

  public native int getTotal()/*-{
    return this.total;
  }-*/;

  public native int getLoaded()/*-{
    return this.loaded;
  }-*/;

  public native int isLengthComputable()/*-{
    return this.lengthComputable;
  }-*/;
}