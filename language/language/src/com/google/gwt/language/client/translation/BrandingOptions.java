/*
 * Copyright 2009 Google Inc.
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
package com.google.gwt.language.client.translation;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Options for Branding. 
 */
public class BrandingOptions extends JavaScriptObject {
  
  /**
   * Specifies the type of branding. 
   */
  public static enum Type {
    HORIZONTAL("horizontal"),
    VERTICAL("vertical");
    
    private String value;
    
    private Type(String value) {
      this.value = value;
    }
    
    public String getValue() {
      return value;
    }
  }
  
  /**
   * Returns branding options object with specified properties.
   * 
   * @param type the type of branding (vertical or horizontal)
   * @return this object
   */
  public static final BrandingOptions newInstance(Type type) {
    return newInstance(type.getValue());
  }
  
  /**
   * Returns branding options object with specified properties.
   * 
   * @param type the type of branding (vertical or horizontal)
   * @return this object
   */
  private static native BrandingOptions newInstance(String type) /*-{
    var object = new Object();
    object.type = type;
    return object;
  }-*/;
  
  /**
   * Instantiate using newInstance() method.
   */
  protected BrandingOptions() { }
}
