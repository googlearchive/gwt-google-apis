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
package com.google.gwt.visualization.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayBoolean;
import com.google.gwt.core.client.JsArrayInteger;
import com.google.gwt.core.client.JsArrayNumber;
import com.google.gwt.core.client.JsArrayString;

/**
 * DrawOptions stores the options for drawing the visualization.
 * 
 */
public abstract class AbstractDrawOptions extends JavaScriptObject {
  public static JsArrayBoolean createJsArray(boolean[] bits) {
    JsArrayBoolean result = JsArrayBoolean.createArray().cast();
    for (int i = 0; i < bits.length; i++) {
      result.set(i, bits[i]);
    }
    return result;
  }
  
  public static JsArrayInteger createJsArray(int[] integers) {
    JsArrayInteger result = JsArrayInteger.createArray().cast();
    for (int i = 0; i < integers.length; i++) {
      result.set(i, integers[i]);
    }
    return result;
  }
  
  public static JsArrayNumber createJsArray(double[] numbers) {
    JsArrayNumber result = JsArrayNumber.createArray().cast();
    for (int i = 0; i < numbers.length; i++) {
      result.set(i, numbers[i]);
    }
    return result;
  }
  
  public static JsArrayString createJsArray(String[] strings) {
    JsArrayString result = JsArrayString.createArray().cast();
    for (int i = 0; i < strings.length; i++) {
      result.set(i, strings[i]);
    }
    return result;
  }

  protected AbstractDrawOptions() {
  }

  public final native void setOption(String option, boolean value) /*-{
    this[option] = value;
  }-*/;

  public final native void setOption(String option, double value) /*-{
    this[option] = value;
  }-*/;
  
  public final native void setOption(String option, int value) /*-{
    this[option] = value;
  }-*/;
  
  public final native void setOption(String option, String value) /*-{
    this[option] = value;
  }-*/;
}