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
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.jsio.client.JSFlyweightWrapper;
import com.google.gwt.jsio.client.BeanProperties;
import com.google.gwt.jsio.client.Constructor;
import com.google.gwt.jsio.client.JSList;

/**
 * @see http://code.google.com/apis/ajaxsearch/documentation/reference.html#_class_GlocalResult
 */
@BeanProperties
public interface GlocalResult extends GResult {
  public static final GlocalResult IMPL =
    (GlocalResult)GWT.create(GlocalResult.class);

  /**
   * A phone number attached to the result.
   */
  @BeanProperties
  public interface PhoneNumber extends JSFlyweightWrapper {
    public static final PhoneNumber IMPL =
      (PhoneNumber)GWT.create(PhoneNumber.class);
    
    @Constructor("Object")
    public JavaScriptObject construct();

    public String getNumber(LocalResult.PhoneNumber obj);

    public String getType(LocalResult.PhoneNumber obj);
  }

  public String getCity(LocalResult obj);
  public String getCountry(LocalResult obj);
  public String getDdUrl(LocalResult obj);
  public String getDdUrlFromHere(LocalResult obj);
  public String getDdUrlToHere(LocalResult obj);
  public String getLat(LocalResult obj);
  public String getLng(LocalResult obj);
  public JSList<LocalResult.PhoneNumber> getPhoneNumbers(LocalResult obj);
  public String getRegion(LocalResult obj);
  public String getStreetAddress(LocalResult obj);
  public String getTitle(LocalResult obj);
  public String getTitleNoFormatting(LocalResult obj);
  public String getUrl(LocalResult obj);
}
