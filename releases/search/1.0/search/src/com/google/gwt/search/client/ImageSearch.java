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

import com.google.gwt.search.client.impl.GimageSearch;
import com.google.gwt.search.client.impl.ImageSearchRestrict;

/**
 * A Google Image search.
 */
public class ImageSearch extends Search {
  private static final GimageSearch IMPL = GimageSearch.IMPL;

  public ImageSearch() {
    super(IMPL);
  }

  /**
   * Clears the colorization restriction.
   */
  public void clearColorization() {
    IMPL.setRestriction(this, ImageSearchRestrict.COLORIZATION.getValue());
  }

  /**
   * Clears the file type restriction.
   */
  public void clearFileType() {
    IMPL.setRestriction(this, ImageSearchRestrict.FILETYPE.getValue());
  }

  /**
   * Clears the image size restriction.
   */
  public void clearImageSize() {
    IMPL.setRestriction(this, ImageSearchRestrict.IMAGESIZE.getValue());
  }

  /**
   * Restricts search results to images with certain colorization.
   * 
   * @param value the colorization to return.
   */
  public void setColorization(ColorizationValue value) {
    IMPL.setRestriction(this, ImageSearchRestrict.COLORIZATION.getValue(),
        value.getValue());
  }

  /**
   * Restricts search results to images of a certain file type.
   * 
   * @param value the file type to return.
   */
  public void setFileType(FileTypeValue value) {
    IMPL.setRestriction(this, ImageSearchRestrict.FILETYPE.getValue(),
        value.getValue());
  }

  /**
   * Restricts the search by image size.
   * 
   * @param value the image size to research the search to.
   */
  public void setImageSize(ImageSizeValue value) {
    IMPL.setRestriction(this, ImageSearchRestrict.IMAGESIZE.getValue(),
        value.getValue());
  }

  /**
   * Restricts the search to a specific type of image.
   * 
   * @param value the type of image to restrict the search to
   */
  public void setImageType(ImageTypeValue value) {
    IMPL.setRestriction(this, ImageSearchRestrict.IMAGETYPE.getValue(),
        value.getValue());
  }

  /**
   * Enables or disables Safe Search filtering.
   * 
   * @param value value to set for filtering.
   */
  public void setSafeSearch(SafeSearchValue value) {
    IMPL.setRestriction(this, ImageSearchRestrict.SAFESEARCH.getValue(),
        value.getValue());
  }

  /**
   * This method is used to restrict the set of image search results returned by
   * this searcher. To restrict to www.photobucket.com, simply call this method
   * passing in a value of "www.photobucket.com". To clear site restrictions,
   * pass in a value of null.
   * 
   * @param site
   */
  public void setSiteRestriction(String site) {
    IMPL.setSiteRestriction(this, site);
  }
}
