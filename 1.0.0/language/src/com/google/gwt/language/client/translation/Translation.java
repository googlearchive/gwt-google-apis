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

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

/**
 * Wrappers for all translation API.
 */
public class Translation {
  
  /**
   * Creates a widget with Google branding.
   * 
   * @return branding widget
   */
  public static Widget createBrandingWidget(BrandingOptions options) {
    return new HTML(Translation.getBranding(options).getInnerHTML());
  }

  /**
   * A global method that will return the language code that describes the
   * language of the given text. The result is supplied asynchronously to the
   * given callback function as the result object.
   * 
   * @param text text whose language has to be detected
   * @param callback callback for asynchronous feedback
   */
  public static native void detect(String text, LangDetCallback callback) /*-{
    $wnd.google.language.detect(text, function(result) {
      callback.@com.google.gwt.language.client.translation.LangDetCallback::onCallbackWrapper(Lcom/google/gwt/language/client/translation/LangDetResult;)(result);
    });
  }-*/;
  
  /**
   * Gets Google branding DOM and attaches it to the div specified by {@code
   * id}. Note that for this to work, the div given by {@code id} should already
   * be attached to RootPanel before this method is invoked.
   * 
   * @param id the id to which branding DOM should be attached
   * @param options options for branding
   */
  public static native void getBranding(String id, BrandingOptions options) /*-{
    $wnd.google.language.getBranding(id, options);
  }-*/;
  
  /**
   * Returns Google brand images as a HTML Element object.
   * 
   * @return the element containing branding DOM
   */
  public static native Element getBranding(BrandingOptions options) /*-{
    return $wnd.google.language.getBranding(options);
  }-*/;

  /**
   * Method that will detect whether Unicode font rendering for the indicated
   * language is supported on the user's browser or not. The result is a
   * constant from the Font Rendering Status Enum.
   * 
   * @param lang the language whose font rendering support status is expected
   * @return font rendering status
   */
  public static FontRenderingStatus isFontRenderingSupported(Language lang) {
    int status = isFontRenderingSupported(lang.getLangCode());
    switch (status) {
      case 0:
        return FontRenderingStatus.UNSUPPORTED;
      case 1:
        return FontRenderingStatus.SUPPORTED;
    }
    return FontRenderingStatus.UNKNOWN;
  }

  /**
   * A method that will return the translatable status for a given language
   * code.
   * 
   * @param lang the language from Language enum.
   * @return true if language is translatable.
   */
  public static boolean isTranslatable(Language lang) {
    return isTranslatable(lang.getLangCode());
  }

  /**
   * Method that will return translated text for the given text supplied,
   * matching the destination language. The result is supplied asynchronously to
   * the given callback as the result object.
   * 
   * @param option the {@code Option} object.
   * @param src source language
   * @param dest destination language
   * @param callback callback for asynchronous feedback
   */
  public static void translate(Option option, Language src, Language dest,
      TranslationCallback callback) {
    translateWithOption(option, src.getLangCode(), dest.getLangCode(),
        callback);
  }

  /**
   * Method that will return translated text for the given text supplied,
   * matching the destination language. The result is supplied asynchronously to
   * the given callback as the result object.
   * 
   * @param text text to be translated
   * @param src source language
   * @param dest destination language
   * @param callback callback for asynchronous feedback
   */
  public static void translate(String text, Language src, Language dest,
      TranslationCallback callback) {
    translate(text, src.getLangCode(), dest.getLangCode(), callback);
  }

  /**
   * Translates text from source to destination language.
   * 
   * @param text text to be translated
   * @param src source language
   * @param dest destination language
   * @param callback callback for asynchronous feedback
   */
  public static native void translate(String text, String src, String dest,
      TranslationCallback callback) /*-{
    $wnd.google.language.translate(text, src, dest, function(result) {
      callback.@com.google.gwt.language.client.translation.TranslationCallback::onCallbackWrapper(Lcom/google/gwt/language/client/translation/TranslationResult;)(result);
    });
  }-*/;

  /**
   * Returns font rendering support status.
   * 
   * @param langCode the language code.
   * @return an integer that represents the font rendering status.
   */
  private static native int isFontRenderingSupported(String langCode) /*-{
    return $wnd.google.language.isFontRenderingSupported(langCode);
  }-*/;

  /**
   * Private helper method that actually invokes the underlying javascript to
   * get translatable status of language.
   * 
   * @param langCode 2 letter language code.
   * @return true if translatable, false otherwise.
   */
  private static native boolean isTranslatable(String langCode) /*-{
    return $wnd.google.language.isTranslatable(langCode);
  }-*/;

  /**
   * Translates text from source to destination language given the {@code
   * Option} object.
   * 
   * @param option the option object containing details of translation text.
   * @param src source language
   * @param dest destination language
   * @param callback callback for asynchronous feedback
   */
  private static native void translateWithOption(Option option, String src,
      String dest, TranslationCallback callback) /*-{
    $wnd.google.language.translate(option, src, dest, function(result) {
      callback.@com.google.gwt.language.client.translation.TranslationCallback::onCallbackWrapper(Lcom/google/gwt/language/client/translation/TranslationResult;)(result);
    });
  }-*/;

  /**
   * This class contains only static members and not meant to be instantiated.
   */
  private Translation() {
  }
}
