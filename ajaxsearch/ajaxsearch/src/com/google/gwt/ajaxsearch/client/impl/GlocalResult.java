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
package com.google.gwt.ajaxsearch.client.impl;

import com.google.gwt.ajaxsearch.client.LocalResult;
import com.google.gwt.ajaxsearch.jsio.client.BeanProperties;
import com.google.gwt.ajaxsearch.jsio.client.Constructor;
import com.google.gwt.ajaxsearch.jsio.client.JSFlyweightWrapper;
import com.google.gwt.ajaxsearch.jsio.client.JSList;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;

/**
 * @see http://code.google.com/apis/ajaxsearch/documentation/reference.html#_class_GlocalResult
 */
@BeanProperties
public interface GlocalResult extends GResult {
  GlocalResult IMPL = GWT.create(GlocalResult.class);

  /**
   * A phone number attached to the result.
   */
  @BeanProperties
  public interface PhoneNumber extends JSFlyweightWrapper {
    PhoneNumber IMPL = GWT.create(PhoneNumber.class);

    @Constructor("$wnd.Object")
    JavaScriptObject construct();

    String getNumber(LocalResult.PhoneNumber obj);

    String getType(LocalResult.PhoneNumber obj);
  }

  String getCity(LocalResult obj);

  String getCountry(LocalResult obj);

  String getDdUrl(LocalResult obj);

  String getDdUrlFromHere(LocalResult obj);

  String getDdUrlToHere(LocalResult obj);

  String getLat(LocalResult obj);

  String getLng(LocalResult obj);

  JSList<LocalResult.PhoneNumber> getPhoneNumbers(LocalResult obj);

  String getRegion(LocalResult obj);

  String getStreetAddress(LocalResult obj);

  String getTitle(LocalResult obj);

  String getTitleNoFormatting(LocalResult obj);

  String getUrl(LocalResult obj);
}
