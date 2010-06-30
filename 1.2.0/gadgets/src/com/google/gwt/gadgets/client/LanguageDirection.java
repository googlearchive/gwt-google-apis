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
package com.google.gwt.gadgets.client;

/**
 * Enum used in the {@link Gadget.GadgetLocale} annotation to specify the language
 * direction to use for a locale.
 * 
 * For more informatoin, see <a
 * href="http://code.google.com/apis/gadgets/docs/i18n.html#BIDI">Creating
 * Bi-Directional Gadgets</a>
 */
public enum LanguageDirection {

  /**
   * Left to Right language support.
   */
  LTR("ltr"),

  /**
   * Right to Left language support.
   */
  RTL("rtl"),

  /**
   * Not specified (use the default.)
   */
  UNSPECIFIED(null);

  /**
   * Returns the value of the substitution variable __BIDI_DIR__
   * 
   * @return LTR when the gadget is in LTR-mode and RTL when the gadget is in
   *         RTL-mode.
   */
  public static LanguageDirection getDir() {
    String nativeDir = getDirNative();
    for (LanguageDirection dir : LanguageDirection.values()) {
      if (dir.getValue().equals(nativeDir)) {
        return dir;
      }
    }
    return LanguageDirection.UNSPECIFIED;
  }

  /**
   * Returns the value of the substitution variable __BIDI_END_EDGE__
   * 
   * @return "right" when the gadget is in LTR-mode and "left" when the gadget
   *         is in RTL-mode.
   */
  public static native String getEndEdge() /*-{
    return $wnd.__BIDI_END_EDGE__;
  }-*/;

  /**
   * Returns the value of the substitution variable __BIDI_REVERSE_DIR__.
   * 
   * @return RTL when the gadget is in LTR-mode and LTR when the gadget is
   *         in RTL-mode.
   */
  public static LanguageDirection getReverseDir() {
    String nativeReverseDir = getReverseDirNative();
    for (LanguageDirection reverseDir : LanguageDirection.values()) {
      if (reverseDir.getValue().equals(nativeReverseDir)) {
        return reverseDir;
      }
    }
    return LanguageDirection.UNSPECIFIED;    
  }

  /**
   * Returns the value of the substitution variable __BIDI_START_EDGE__
   * 
   * @return "left" when the gadget is in LTR-mode and "right" when the gadget
   *         is in RTL-mode.
   */
  public static native String getStartEdge() /*-{
    return $wnd.__BIDI_START_EDGE__;
  }-*/;

  private static native String getDirNative() /*-{
    return $wnd.__BIDI_DIR__;
  }-*/;

  private static native String getReverseDirNative() /*-{
    return $wnd.__BIDI_REVERSE_DIR__;
  }-*/;

  private final String value;

  private LanguageDirection(String value) {
    this.value = value;
  }
  
  public String getValue() {
    return value;
  }
}
