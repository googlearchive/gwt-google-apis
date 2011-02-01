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
import com.google.gwt.visualization.client.AbstractDrawOptions;

/**
 * Common axis options used by core charts. Axis options could be attached to an
 * Options object via setHAxisOptions or setVAxisOptions methods.
 *
 * @see <a href=
 *      "http://code.google.com/apis/visualization/documentation/gallery/areachart.html#Configuration_Options"
 *      > Configuration Options Reference</a>
 */
public class AxisOptions extends AbstractDrawOptions {
  public static AxisOptions create() {
    return JavaScriptObject.createObject().cast();
  }

  protected AxisOptions() {
  }

  public final native void setBaseline(double baseline) /*-{
    this.baseline = baseline;
  }-*/;

  public final native void setBaselineColor(String baselineColor) /*-{
    this.baselineColor = baselineColor;
  }-*/;

  public final native void setDirection(int direction) /*-{
    this.direction = direction;
  }-*/;

  public final native void setIsLogScale(boolean isLogScale) /*-{
    this.logScale = isLogScale;
  }-*/;

  public final native void setMaxValue(double max) /*-{
    this.maxValue = max;
  }-*/;

  public final native void setMinValue(double min) /*-{
    this.minValue = min;
  }-*/;

  public final native void setTextPosition(String position) /*-{
    this.textPosition = position;
  }-*/;

  public final native void setTextStyle(TextStyle style) /*-{
    this.textStyle = style;
  }-*/;

  public final native void setTitle(String title) /*-{
    this.title = title;
  }-*/;

  public final native void setTitleTextStyle(TextStyle style) /*-{
    this.titleTextStyle = style;
  }-*/;

}
