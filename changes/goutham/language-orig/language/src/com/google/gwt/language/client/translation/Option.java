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
 * Option object containing text to be translated and the content type.
 */
public final class Option extends JavaScriptObject {

  /**
   * Creates a new instance of option object with given text and content type.
   * 
   * @param text the text to be translated.
   * @param contentType the content type of text. See {@code ContentType}
   * @return Option object
   */
  public static Option newInstance(String text, ContentType contentType) {
    return newInstance(text, contentType.getValue());
  }

  /**
   * Native method for constructing this object.
   * 
   * @param text text to be translated.
   * @param type type of text. See {@code ContentType}.
   * @return Option object
   */
  private static native Option newInstance(String text, String type) /*-{
    var option = new Object();
    option.text = text;
    option.type = type;
    return option;
  }-*/;

  /**
   * Subclasses of JavaScriptObject should have protected constructors. To
   * create a new instance, use method {@code newInstance}.
   */
  protected Option() {
  }

  /**
   * Returns the text-to-be-translated.
   * 
   * @return string containing text-to-be-translated.
   */
  public native String getText() /*-{
    return this.text;
  }-*/;

  /**
   * Returns the content type - an instance of enum {@code ContentType}.
   * 
   * @return content type as ContentType enum.
   */
  public ContentType getType() {
    String type = getTypeString();
    if (type.equalsIgnoreCase(ContentType.TEXT.getValue())) {
      return ContentType.TEXT;
    } else if (type.equalsIgnoreCase(ContentType.HTML.getValue())) {
      return ContentType.HTML;
    }
    return null;
  }

  /**
   * Type of content - html or text.
   * 
   * @return return content type of
   */
  private native String getTypeString() /*-{
    return this.type;
  }-*/;
}
