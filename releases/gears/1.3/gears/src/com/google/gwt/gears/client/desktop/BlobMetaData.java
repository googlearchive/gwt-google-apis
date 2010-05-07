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
package com.google.gwt.gears.client.desktop;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Represents metadata about a {@link com.google.gwt.gears.client.blob.Blob}
 * that has been extracted by
 * {@link Desktop#extractMetaData(com.google.gwt.gears.client.blob.Blob)}.
 */
public class BlobMetaData extends JavaScriptObject {

  protected BlobMetaData() {
    // required for overlay types
  }

  /**
   * For blobs that contain image data in a well-known encoding (such as PNG or
   * JPEG), returns the height of that image.
   * <p>
   * If the blob does not contain image data, a type-conversion error will occur
   * in hosted mode and unpredictable behavior may occur in web mode.
   * 
   * @return the image height.
   */
  public final native int getImageHeight() /*-{
    return this.imageHeight;
  }-*/;

  /**
   * For blobs that contain image data in a well-known encoding (such as PNG or
   * JPEG), returns the width of that image.
   * <p>
   * If the blob does not contain image data, a type-conversion error will occur
   * in hosted mode and unpredictable behavior may occur in web mode.
   * 
   * @return the image width.
   */
  public final native int getImageWidth() /*-{
    return this.imageWidth;
  }-*/;

  /**
   * Returns the MIME type of the blob, as calculated by
   * {@link Desktop#extractMetaData(com.google.gwt.gears.client.blob.Blob)}.
   * 
   * @return the MIME type, such as "image/jpeg", "image/png", or
   *         "application/octet-stream".
   */
  public final native String getMimeType() /*-{
    return this.mimeType;
  }-*/;
}
