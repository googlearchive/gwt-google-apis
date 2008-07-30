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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.search.client.impl.GResult;
import com.google.gwt.search.client.impl.GlocalResult;
import com.google.gwt.search.jsio.client.impl.Extractor;

import java.util.List;

/**
 * Local search results.
 */
public class LocalResult extends Result {
  /**
   * A phone number.
   */
  public static class PhoneNumber {
    public static final String DATA = "data";

    public static final String FAX = "fax";

    // TODO(zundel): should these be expressed to enums?
    public static final String MAIN = "main";

    public static final String MOBILE = "mobile";

    @SuppressWarnings("unused")
    private static final Extractor<PhoneNumber> __extractor = new Extractor<PhoneNumber>() {
      public PhoneNumber fromJS(JavaScriptObject jso) {
        return new PhoneNumber(jso);
      }

      public JavaScriptObject toJS(PhoneNumber o) {
        return o.jsoPeer;
      }
    };

    private final JavaScriptObject jsoPeer;

    private PhoneNumber(JavaScriptObject jsoPeer) {
      this.jsoPeer = jsoPeer;
    }

    /**
     * Returns the phone number as a string.
     * 
     * @return the phone number as a string.
     */
    public String getNumber() {
      return GlocalResult.PhoneNumber.IMPL.getNumber(this);
    }

    /**
     * Returns the type of phone number. The value be one of "main", "fax",
     * "mobile", "data", or "".
     * 
     * @return the type of phone number.
     */
    public String getType() {
      return GlocalResult.PhoneNumber.IMPL.getType(this);
    }
  }

  LocalResult(JavaScriptObject obj) {
    super(obj);
  }

  /**
   * Returns the city name for the result. Note:, in some cases, this property
   * may be set to "".
   * 
   * @return the city name for the result. Note:, in some cases, this property
   *         may be set to "".
   */
  public String getCity() {
    return GlocalResult.IMPL.getCity(this);
  }

  /**
   * Returns a country name for the result. Note:, in some cases, this property
   * may be set to "".
   * 
   * @return a country name for the result. Note:, in some cases, this property
   *         may be set to "".
   */
  public String getCountry() {
    return GlocalResult.IMPL.getCountry(this);
  }

  /**
   * Returns a URL that can be used to provide driving directions from the
   * center of the set of search results to this search result. Note: This
   * method returns null when no URL is available.
   * 
   * @return a URL that can be used to provide driving directions from the
   *         center of the set of search results to this search result
   */
  public String getDdUrl() {
    return GlocalResult.IMPL.getDdUrl(this);
  }

  /**
   * Returns a URL that can be used to provide driving directions from a user
   * specified location to this search result. Note, This
   * method returns null when no URL is available.
   * 
   * @return a URL that can be used to provide driving directions from a user
   *         specified location to this search result. Note, This
   * method returns null when no URL is available.
   */
  public String getDdUrlFromHere() {
    return GlocalResult.IMPL.getDdUrlFromHere(this);
  }

  /**
   * Returns a URL that can be used to provide driving directions from a user
   * specified location to this search result. Note, This
   * method returns null when no URL is available.
   * 
   * @return a URL that can be used to provide driving directions from a user
   *         specified location to this search result. Note, This
   * method returns null when no URL is available.
   */
  public String getDdUrlToHere() {
    return GlocalResult.IMPL.getDdUrlToHere(this);
  }

  /**
   * Returns the latitude value of the result.
   * 
   * @return the latitude value of the result.
   */
  public double getLat() {
    return Double.parseDouble(GlocalResult.IMPL.getLat(this));
  }

  /**
   * Returns the longitude value of the result.
   * 
   * @return the longitude value of the result.
   */
  public double getLng() {
    return Double.parseDouble(GlocalResult.IMPL.getLng(this));
  }

  /**
   * Returns a list of {@link LocalResult.PhoneNumber}.
   * 
   * @return a list of {@link LocalResult.PhoneNumber}.
   */
  public List<PhoneNumber> getPhoneNumbers() {
    return GlocalResult.IMPL.getPhoneNumbers(this);
  }

  /**
   * Returns a region name for the result (e.g., in the us, this is typically a
   * state abbreviation, in other regions it might be a province, etc.) Note:,
   * in some cases, this property may be set to "".
   * 
   * @return a region name for the result (e.g., in the us, this is typically a
   *         state abbreviation, in other regions it might be a province, etc.)
   */
  public String getRegion() {
    return GlocalResult.IMPL.getRegion(this);
  }

  /**
   * Return the street address and number for the given result. Note:, in some
   * cases, this property may be set to "" if the result has no known street
   * address. address line.
   * 
   * @return the street address and number for the given result.
   */
  public String getStreetAddress() {
    return GlocalResult.IMPL.getStreetAddress(this);
  }

  /**
   * Returns the title for the result. In some cases, the title and the
   * streetAddress are the same. This typically occurs when the search term is a
   * street address such as <b>1231 Lisa Lane, Los Altos, CA</b>.
   * 
   * @return the title for the result.
   */
  public String getTitle() {
    return GlocalResult.IMPL.getTitle(this);
  }

  /**
   * Returns the title, but unlike .title, this property is stripped of html
   * markup (e.g., &lt;b&gt;, &lt;i&gt;, etc.).
   * 
   * @return the title, but unlike .title, this property is stripped of html
   *         markup (e.g., &lt;b&gt;, &lt;i&gt;, etc.).
   */
  public String getTitleNoFormatting() {
    return GlocalResult.IMPL.getTitleNoFormatting(this);
  }

  /**
   * Returns a url to a Google Maps Details page associated with the search
   * result.
   * 
   * @return a url to a Google Maps Details page associated with the search
   *         result.
   */
  public String getUrl() {
    return GlocalResult.IMPL.getUrl(this);
  }

  @Override
  GResult getImpl() {
    return GlocalResult.IMPL;
  }
  
}
