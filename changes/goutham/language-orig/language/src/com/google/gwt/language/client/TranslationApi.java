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
package com.google.gwt.language.client;

/**
 * Wrappers for all translation API.
 */
public class TranslationApi {
  
  public static void translate(String text, Language src, Language dest, TranslationCallback callback) {
    translate(text, src.getLangCode(), dest.getLangCode(), callback);
  }
  
  private static native void translate(String text, String src, String dest, TranslationCallback callback) /*-{
    $wnd.google.language.translate(text, src, dest, function(result) {
      callback.@com.google.gwt.language.client.TranslationCallback::callback(Lcom/google/gwt/language/client/TranslationResult;)(result);
    });
  }-*/;
  
  public static void translate(Option option, Language src, Language dest, TranslationCallback callback) {
    translateWithOption(option.getType().getValue(), option.getText(), src.getLangCode(), dest.getLangCode(),
        callback);
  }
  
  private static native void translateWithOption(String type, String text, String src, String dest, TranslationCallback callback) /*-{
    $wnd.google.language.translate({"type":type, "text":text}, src, dest, function(result) {
      callback.@com.google.gwt.language.client.TranslationCallback::callback(Lcom/google/gwt/language/client/TranslationResult;)(result);
    });
  }-*/;
  
  public static native void detect(String text, LangDetCallback callback) /*-{
    $wnd.google.language.detect(text, function(result) {
      callback.@com.google.gwt.language.client.LangDetCallback::callback(Lcom/google/gwt/language/client/LangDetResult;)(result);
    });
  }-*/;
  
  public static boolean isTranslatable(Language lang) {
    return isTranslatable(lang.getLangCode());
  }
  
  private static native boolean isTranslatable(String langCode) /*-{
    return $wnd.google.language.isTranslatable(langCode);
  }-*/;
  
  public static FontRenderingStatus isFontRenderingSupported(Language lang) {
    int status = isFontRenderingSupported(lang.getLangCode());
    switch (status) {
      case 0: return FontRenderingStatus.UNSUPPORTED;
      case 1: return FontRenderingStatus.SUPPORTED;
    }
    return FontRenderingStatus.UNKNOWN;
  }
  
  private static native int isFontRenderingSupported(String langCode) /*-{
    return  $wnd.google.language.isFontRenderingSupported(langCode);
  }-*/;
}
