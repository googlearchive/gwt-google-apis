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
import com.google.gwt.core.client.JsArrayString;

/**
 * All languages. In sync with google.language.Languages enum.
 */
public enum Language {
  AFRIKAANS("af"), // Comments keep Eclipse formatter from putting on one line
  ALBANIAN("sq"), //
  AMHARIC("am"), //
  ARABIC("ar"), //
  ARMENIAN("hy"), //
  AZERBAIJANI("az"), //
  BASQUE("eu"), //
  BELARUSIAN("be"), //
  BENGALI("bn"), //
  BIHARI("bh"), //
  BRETON("br"), //
  BULGARIAN("bg"), //
  BURMESE("my"), //
  CATALAN("ca"), //
  CHEROKEE("chr"), //
  CHINESE("zh"), //
  CHINESE_SIMPLIFIED("zh-CN"), //
  CHINESE_TRADITIONAL("zh-TW"), //
  CORSICAN("co"), //
  CROATIAN("hr"), //
  CZECH("cs"), //
  DANISH("da"), //
  DHIVEHI("dv"), //
  DUTCH("nl"), //
  ENGLISH("en"), //
  ESPERANTO("eo"), //
  ESTONIAN("et"), //
  FAROESE("fo"), //
  FILIPINO("tl"), //
  FINNISH("fi"), //
  FRENCH("fr"), //
  FRISIAN("fy"), //
  GALICIAN("gl"), //
  GEORGIAN("ka"), //
  GERMAN("de"), //
  GREEK("el"), //
  // GUARANI("gn"),
  GUJARATI("gu"), //
  HATIAN_CREOLE("ht"), //
  HEBREW("iw"), //
  HINDI("hi"), //
  HUNGARIAN("hu"), //
  ICELANDIC("is"), //
  INDONESIAN("id"), //
  INUKTITUT("iu"), //
  IRISH("ga"), //
  ITALIAN("it"), //
  JAPANESE("ja"), //
  JAVANESE("jw"), //
  KANNADA("kn"), //
  KAZAKH("kk"), //
  KHMER("km"), //
  KOREAN("ko"), //
  KURDISH("ku"), //
  KYRGYZ("ky"), //
  LAOTHIAN("lo"), //
  LATIN("la"), //
  LATVIAN("lv"), //
  LITHUANIAN("lt"), //
  LUXEMBOURGISH("lb"), //
  MACEDONIAN("mk"), //
  MALAY("ms"), //
  MALAYALAM("ml"), //
  MALTESE("mt"), //
  MAORI("mi"), //
  MARATHI("mr"), //
  MONGOLIAN("mn"), //
  NEPALI("ne"), //
  NORWEGIAN("no"), //
  OCCITAN("oc"), //
  ORIYA("or"), //
  PASHTO("ps"), //
  PERSIAN("fa"), //
  POLISH("pl"), //
  PORTUGUESE("pt"), //
  PORTUGUESE_PORTUGAL("pt-PT"), //
  PUNJABI("pa"), //
  QUECHUA("qu"), //
  ROMANIAN("ro"), //
  RUSSIAN("ru"), //
  SANSKRIT("sa"), //
  SCOTS_GAELIC("gd"), //
  SERBIAN("sr"), //
  SINDHI("sd"), //
  SINHALESE("si"), //
  SLOVAK("sk"), //
  SLOVENIAN("sl"), //
  SPANISH("es"), //
  SUDANESE("su"), //
  SWAHILI("sw"), //
  SWEDISH("sv"), //
  SYRIAC("syr"), //
  TAJIK("tg"), //
  TAMIL("ta"), //
  TAGALOG("tl"), //
  TATAR("tt"), //
  TELUGU("te"), //
  THAI("th"), //
  TIBETAN("bo"), //
  TONGA("to"), //
  TURKISH("tr"), //
  UKRAINIAN("uk"), //
  URDU("ur"), //
  UZBEK("uz"), //
  UIGHUR("ug"), //
  VIETNAMESE("vi"), //
  UNKNOWN(""), //
  WELSH("cy"), //
  YIDDISH("yi"), //
  YORUBA("yo"); //

  private String langCode;

  /**
   * Private constructor.
   * 
   * @param langCode the unique short string that identifies a language.
   */
  private Language(String langCode) {
    this.langCode = langCode;
  }

  /**
   * Returns language code.
   * 
   * @return a string containing the language code.
   */
  public String getLangCode() {
    return langCode;
  }

  /**
   * Bindings for the google.languages.language.Languages object.
   */
  public static class SupportedLanguages extends JavaScriptObject {
    protected SupportedLanguages() {
    }

    /**
     * Lookup a language code value by language name.
     */
    public final native String getLanguageCode(String name) /*-{
      return this[name];
    }-*/;

    /**
     * Look up a language name value by language code.
     */
    public final native String getLanguageName(String languageCode) /*-{
      if (!this.__lookupByCode) {
        // Create a map from language code back to name 
        this.__lookupByCode = {};
        for (var prop in this) {
          if (prop.match(/^[A-Z_]+$/)) {
            this.__lookupByCode[this[prop]] = prop;
          }
        }
      }
      return this.__lookupByCode[languageCode];
    }-*/;

    /**
     * Returns a list of language keys in the object.
     */
    public final native JsArrayString getLanguages() /*-{
      var result = [];
      for (var prop in this) {
        if (prop.match(/^[A-Z_]+$/)) {
          result.push(prop);
        }
      }
      return result;
    }-*/;
  }

  /**
   * Returns a JavaScriptObject that contains the list of language names as
   * properties, with the 2 character ISO language code as the property values.
   */
  public static native SupportedLanguages nativeSupportedLangauges() /*-{
    return $wnd.google.language.Languages;
  }-*/;
}
