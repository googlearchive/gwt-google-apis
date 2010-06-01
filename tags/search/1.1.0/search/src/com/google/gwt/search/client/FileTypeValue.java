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
package com.google.gwt.search.client;

import com.google.gwt.search.jsio.client.JSOpaque;

/**
 * Used with {@link ImageSearch#setFileType(FileTypeValue)} to control the types
 * of results displayed.
 */
public enum FileTypeValue {
  /**
   * Restrict to only JPEG images.
   */
  JPG("JPG"),
  
  /**
   * Restrict to only PNG images.
   */
  PNG("PNG"),
  
  /**
   * Restrict to only GIF images.
   */
  GIF("GIF"),
  
  /**
   * Restrict to only BMP images.
   */
  BMP("BMP");

  private final JSOpaque value;

  private FileTypeValue(String type) {
    value = new JSOpaque("$wnd.GSearch.FILETYPE_" + type);
  }

  JSOpaque getValue() {
    return value;
  }
}
