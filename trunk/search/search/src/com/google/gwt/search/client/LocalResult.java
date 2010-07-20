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
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayString;

/**
 * Local search results.
 */
public final class LocalResult extends Result {
  public static final ResultClass RESULT_CLASS = ResultClass.LOCAL_SEARCH_RESULT;

  public static LocalResult isLocalResult(Result result) {
    if (result.getResultClass().equals(RESULT_CLASS)) {
      return (LocalResult) result;
    }
    return null;
  }

  /**
   * A phone number.
   */
  public static final class PhoneNumber extends JavaScriptObject {
    public static final String DATA = "data";

    public static final String FAX = "fax";

    public static final String MAIN = "main";

    public static final String MOBILE = "mobile";

    protected PhoneNumber() {
      // Required for overlay types
    }

    /**
     * Returns the phone number as a string.
     * 
     * @return the phone number as a string.
     */
    public native String getNumber() /*-{
      return this.number;
    }-*/;

    /**
     * Returns the type of phone number. The value be one of "main", "fax",
     * "mobile", "data", or "".
     * 
     * @return the type of phone number.
     */
    public native String getType() /*-{
      return this.type;
    }-*/;
  }

  protected LocalResult() {
    // Required for overlay types
  }

  /**
   * Supplies an array consisting of the mailing address lines for this result,
   * for instance:
   * 
   * <pre>
   * ["1600 Amphitheatre Pky", "Mountain View, CA 94043"] 
   *  or
   * ["Via del Corso, 330", "00186 Roma (RM), Italy"]
   * </pre>
   * 
   * To correctly render an address associated with a result, either use the
   * {@link Result#getHtml()} method of the result directly or iterate through
   * this array and display each addressLine in turn.
   * 
   * @return an array consisting of the mailing address lines for the result.
   */
  public native JsArrayString getAddressLines() /*-{
    return this.addressLines;
  }-*/;

  /**
   * Returns the city name for the result. Note:, in some cases, this property
   * may be set to "".
   * 
   * @return the city name for the result. Note:, in some cases, this property
   *         may be set to "".
   */
  public native String getCity() /*-{
    return this.city;
  }-*/;

  /**
   * Returns a content snippet associated with the KML result, if this is a
   * "kml" result. For "local" results, this property is the empty string.
   * 
   * @return a content snippet associated with the KML result, if this is a
   *         "kml" result
   */
  public native String getContent() /*-{
    return this.content;
  }-*/;

  /**
   * Returns a country name for the result. Note:, in some cases, this property
   * may be set to "".
   * 
   * @return a country name for the result. Note:, in some cases, this property
   *         may be set to "".
   */
  public native String getCountry() /*-{
    return this.country;
  }-*/;

  /**
   * Returns a URL that can be used to provide driving directions from the
   * center of the set of search results to this search result. Note: This
   * method returns null when no URL is available.
   * 
   * @return a URL that can be used to provide driving directions from the
   *         center of the set of search results to this search result
   */
  public native String getDdUrl() /*-{
    return this.ddUrl;
  }-*/;

  /**
   * Returns a URL that can be used to provide driving directions from a user
   * specified location to this search result. Note, This method returns null
   * when no URL is available.
   * 
   * @return a URL that can be used to provide driving directions from a user
   *         specified location to this search result. Note, This method returns
   *         null when no URL is available.
   */
  public native String getDdUrlFromHere() /*-{
    return this.ddUrlFromHere;
  }-*/;

  /**
   * Returns a URL that can be used to provide driving directions from a user
   * specified location to this search result. Note, This method returns null
   * when no URL is available.
   * 
   * @return a URL that can be used to provide driving directions from a user
   *         specified location to this search result. Note, This method returns
   *         null when no URL is available.
   */
  public native String getDdUrlToHere() /*-{
    return this.ddUrlToHere;
  }-*/;

  /**
   * Returns the latitude value of the result.
   * 
   * @return the latitude value of the result.
   */
  public double getLat() {
    return Double.parseDouble(getLatAsString());
  }

  /**
   * Returns the type of this result which can either be "local" in the case of
   * a local business listing or "geocode" result or "kml" in the case of a KML
   * listing.
   * 
   * @return type of this result
   */
  public native String getListingType() /*-{
    return this.listingType;
  }-*/;

  /**
   * Returns the longitude value of the result.
   * 
   * @return the longitude value of the result.
   */
  public double getLng() {
    return Double.parseDouble(getLngAsString());
  }

  /**
   * Returns a list of {@link LocalResult.PhoneNumber}.
   * 
   * @return a list of {@link LocalResult.PhoneNumber}.
   */
  public native JsArray<PhoneNumber> getPhoneNumbers() /*-{
    return this.phoneNumbers == null ? [] : this.phoneNumbers;
  }-*/;

  /**
   * Returns a region name for the result (e.g., in the us, this is typically a
   * state abbreviation, in other regions it might be a province, etc.) Note:,
   * in some cases, this property may be set to "".
   * 
   * @return a region name for the result (e.g., in the us, this is typically a
   *         state abbreviation, in other regions it might be a province, etc.)
   */
  public native String getRegion() /*-{
    return this.region;
  }-*/;

  /**
   * Returns a url to a static map image representation of the current result.
   * The image is 150px by 100px tall with a single marker representing the
   * current location. Expected usage is to hyperlink this image using the url
   * property.
   * 
   * @return url to a static map image representation of the current result
   */
  public native String getStaticMapUrl() /*-{
    return this.staticMapUrl;
  }-*/;

  /**
   * Return the street address and number for the given result. Note:, in some
   * cases, this property may be set to "" if the result has no known street
   * address. address line.
   * 
   * @return the street address and number for the given result.
   */
  public native String getStreetAddress() /*-{
    return this.streetAddress;
  }-*/;

  /**
   * Returns the title for the result. In some cases, the title and the
   * streetAddress are the same. This typically occurs when the search term is a
   * street address such as <b>1231 Lisa Lane, Los Altos, CA</b>.
   * 
   * @return the title for the result.
   */
  public native String getTitle() /*-{
    return this.title;
  }-*/;

  /**
   * Returns the title, but unlike .title, this property is stripped of html
   * markup (e.g., &lt;b&gt;, &lt;i&gt;, etc.).
   * 
   * @return the title, but unlike .title, this property is stripped of html
   *         markup (e.g., &lt;b&gt;, &lt;i&gt;, etc.).
   */
  public native String getTitleNoFormatting() /*-{
    return this.titleNoFormatting;
  }-*/;

  /**
   * Returns a url to a Google Maps Details page associated with the search
   * result.
   * 
   * @return a url to a Google Maps Details page associated with the search
   *         result.
   */
  public native String getUrl() /*-{
    return this.url;
  }-*/;

  private native String getLatAsString() /*-{
    return this.lat;
  }-*/;

  private native String getLngAsString() /*-{
    return this.lng;
  }-*/;

}
