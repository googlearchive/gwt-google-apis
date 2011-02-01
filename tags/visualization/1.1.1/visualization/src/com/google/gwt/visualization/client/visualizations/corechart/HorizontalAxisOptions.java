/*
 * Copyright 2010 Google Inc.
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

package com.google.gwt.visualization.client.visualizations.corechart;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Specific horizontal axis options used by core charts. Horizontal axis options
 * could be attached to an Options object via setHAxisOptions method.
 *
 * @see <a href=
 *      "http://code.google.com/apis/visualization/documentation/gallery/areachart.html#Configuration_Options"
 *      > Configuration Options Reference</a>
 */
public class HorizontalAxisOptions extends AxisOptions {
  public static HorizontalAxisOptions create() {
    return JavaScriptObject.createObject().cast();
  }

  protected HorizontalAxisOptions() {
  }

  public final native void setMaxAlternation(int maxAlternation) /*-{
    this.maxAlternation = maxAlternation;
  }-*/;

  public final native void setShowTextEvery(int showTextEvery) /*-{
    this.showTextEvery = showTextEvery;
  }-*/;

  public final native void setSlantedText(boolean isSlanted) /*-{
    this.slantedText = isSlanted;
  }-*/;

  public final native void setSlantedTextAngle(double slantedTextAngle) /*-{
    this.slantedTextAngle = slantedTextAngle;
  }-*/;
}
