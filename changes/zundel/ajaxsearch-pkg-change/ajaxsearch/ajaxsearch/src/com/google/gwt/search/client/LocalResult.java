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

import com.google.gwt.search.client.impl.GResult;
import com.google.gwt.search.client.impl.GlocalResult;
import com.google.gwt.search.jsio.client.impl.Extractor;
import com.google.gwt.core.client.JavaScriptObject;

import java.util.List;

/**
 * Local search results.
 */
public class LocalResult extends Result {
  /**
   * A phone number.
   */
  public static class PhoneNumber {
    @SuppressWarnings("unused")
    private static final Extractor<PhoneNumber> __extractor = new Extractor<PhoneNumber>() {
      public PhoneNumber fromJS(JavaScriptObject jso) {
        return new PhoneNumber(jso);
      }

      public JavaScriptObject toJS(PhoneNumber o) {
        return o.jsoPeer;
      }
    };

    public static final String MAIN = "main";

    public static final String FAX = "fax";

    public static final String MOBILE = "mobile";

    public static final String DATA = "data";

    private final JavaScriptObject jsoPeer;

    // TODO: Do we even need this constructor? If so, can it be private?
    public PhoneNumber() {
      this(GlocalResult.PhoneNumber.IMPL.construct());
    }

    private PhoneNumber(JavaScriptObject jsoPeer) {
      this.jsoPeer = jsoPeer;
    }

    public String getNumber() {
      return GlocalResult.PhoneNumber.IMPL.getNumber(this);
    }

    public String getType() {
      return GlocalResult.PhoneNumber.IMPL.getType(this);
    }
  }

  LocalResult(JavaScriptObject obj) {
    super(obj);
  }

  public String getCity() {
    return GlocalResult.IMPL.getCity(this);
  }

  public String getCountry() {
    return GlocalResult.IMPL.getCountry(this);
  }

  public String getDdUrl() {
    return GlocalResult.IMPL.getDdUrl(this);
  }

  public String getDdUrlFromHere() {
    return GlocalResult.IMPL.getDdUrlFromHere(this);
  }

  public String getDdUrlToHere() {
    return GlocalResult.IMPL.getDdUrlToHere(this);
  }

  public double getLat() {
    return Double.parseDouble(GlocalResult.IMPL.getLat(this));
  }

  public double getLng() {
    return Double.parseDouble(GlocalResult.IMPL.getLng(this));
  }

  /**
   * Returns a list of {@link LocalResult.PhoneNumber}.
   */
  public List<PhoneNumber> getPhoneNumbers() {
    return GlocalResult.IMPL.getPhoneNumbers(this);
  }

  public String getRegion() {
    return GlocalResult.IMPL.getRegion(this);
  }

  public String getStreetAddress() {
    return GlocalResult.IMPL.getStreetAddress(this);
  }

  public String getTitle() {
    return GlocalResult.IMPL.getTitle(this);
  }

  public String getTitleNoFormatting() {
    return GlocalResult.IMPL.getTitleNoFormatting(this);
  }

  public String getUrl() {
    return GlocalResult.IMPL.getUrl(this);
  }

  @Override
  GResult getImpl() {
    return GlocalResult.IMPL;
  }
}
