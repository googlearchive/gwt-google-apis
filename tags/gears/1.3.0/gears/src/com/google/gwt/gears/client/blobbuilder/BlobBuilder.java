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
package com.google.gwt.gears.client.blobbuilder;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.gears.client.blob.Blob;
import com.google.gwt.gears.client.impl.Utils;

/**
 * A Blob is an immutable, read-only object, the same as a JavaScript string.
 * BlobBuilders are used to generate a Blob with new content. They are to Java's
 * StringBuilder as Gears Blobs are to Java's Strings.
 * <p>
 * <b>Permission</b>
 * <p>
 * The BlobBuilder API does not require user permission.
 */
public class BlobBuilder extends JavaScriptObject {

  protected BlobBuilder() {
    // required for overlay types
  }

  /**
   * Appends bytes to the Blob-in-progress.
   * 
   * @param c a <code>byte</code>.
   */
  public final native void append(byte c) /*-{
    this.append(c);
  }-*/;

  /**
   * Appends bytes to the Blob-in-progress.
   * 
   * @param s a string (which will be encoded as UTF-8).
   */
  public final native void append(String s) /*-{
    this.append(s);
  }-*/;

  /**
   * Appends bytes to the Blob-in-progress.
   * 
   * @param b a blob.
   */
  public final native void append(Blob b) /*-{
    this.append(b);
  }-*/;

  /**
   * Appends bytes to the Blob-in-progress.
   * 
   * @param bytes an array of bytes.
   */
  public final void append(byte[] bytes) {
    append(Utils.toJavaScriptArray(bytes));
  }

  /**
   * Appends bytes to the Blob-in-progress.
   * 
   * @param strings an array of strings.
   */
  public final void append(String[] strings) {
    append(Utils.toJavaScriptArray(strings));
  }

  /**
   * Appends bytes to the Blob-in-progress.
   * 
   * @param blobs an array of blobs.
   */
  public final void append(Blob[] blobs) {
    append(Utils.toJavaScriptArray(blobs));
  }

  /**
   * Bakes the previously appended data into a new, immutable Blob object.
   * 
   * @return A new Blob containing the previously appended data.
   */
  public final native Blob getAsBlob() /*-{
    return this.getAsBlob();
  }-*/;

  private native void append(JavaScriptObject arr) /*-{
    this.append(arr);
  }-*/;
}
