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
 * All languages. In sync with google.language.Languaes enum. 
 */
public enum Language {
  AFRIKAANS("af"),
  ALBANIAN("sq"),
  AMHARIC("am"),
  ARABIC("ar"),
  ARMENIAN("hy"),
  AZERBAIJANI("az"),
  BASQUE("eu"),
  BELARUSIAN("be"),
  BENGALI("bn"),
  BIHARI("bh"),
  BULGARIAN("bg"),
  BURMESE("my"),
  CATALAN("ca"),
  CHEROKEE("chr"),
  CHINESE("zh"),
  CHINESE_SIMPLIFIED("zh-CN"),
  CHINESE_TRADITIONAL("zh-TW"),
  CROATIAN("hr"),
  CZECH("cs"),
  DANISH("da"),
  DHIVEHI("dv"),
  DUTCH("nl"),
  ENGLISH("en"),
  ESPERANTO("eo"),
  ESTONIAN("et"),
  FILIPINO("tl"),
  FINNISH("fi"),
  FRENCH("fr"),
  GALICIAN("gl"),
  GEORGIAN("ka"),
  GERMAN("de"),
  GREEK("el"),
  GUARANI("gn"),
  GUJARATI("gu"),
  HEBREW("iw"),
  HINDI("hi"),
  HUNGARIAN("hu"),
  ICELANDIC("is"),
  INDONESIAN("id"),
  INUKTITUT("iu"),
  ITALIAN("it"),
  JAPANESE("ja"),
  KANNADA("kn"),
  KAZAKH("kk"),
  KHMER("km"),
  KOREAN("ko"),
  KURDISH("ku"),
  KYRGYZ("ky"),
  LAOTHIAN("lo"),
  LATVIAN("lv"),
  LITHUANIAN("lt"),
  MACEDONIAN("mk"),
  MALAY("ms"),
  MALAYALAM("ml"),
  MALTESE("mt"),
  MARATHI("mr"),
  MONGOLIAN("mn"),
  NEPALI("ne"),
  NORWEGIAN("no"),
  ORIYA("or"),
  PASHTO("ps"),
  PERSIAN("fa"),
  POLISH("pl"),
  PORTUGUESE("pt-PT"),
  PUNJABI("pa"),
  ROMANIAN("ro"),
  RUSSIAN("ru"),
  SANSKRIT("sa"),
  SERBIAN("sr"),
  SINDHI("sd"),
  SINHALESE("si"),
  SLOVAK("sk"),
  SLOVENIAN("sl"),
  SPANISH("es"),
  SWAHILI("sw"),
  SWEDISH("sv"),
  TAJIK("tg"),
  TAMIL("ta"),
  TAGALOG("tl"),
  TELUGU("te"),
  THAI("th"),
  TIBETAN("bo"),
  TURKISH("tr"),
  UKRAINIAN("uk"),
  URDU("ur"),
  UZBEK("uz"),
  UIGHUR("ug"),
  VIETNAMESE("vi"),
  UNKNOWN("");

  private String langCode;
  private Language(String langCode) {
    this.langCode = langCode;
  }
  
  public String getLangCode() {
    return langCode;
  }
}
