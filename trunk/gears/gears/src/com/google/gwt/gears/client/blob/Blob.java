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
package com.google.gwt.gears.client.blob;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayInteger;
import com.google.gwt.gears.client.impl.Utils;

/**
 * A Blob is a reference to an opaque block of binary data.
 * <p>
 * Blobs are a general-purpose interchange format. They can be passed into, and
 * returned by, a variety of Gears methods. Blobs are, in many ways, parallel to
 * Strings. Most APIs that accept a 'String' could be updated to accept
 * 'String-or-Blob'.
 * <p>
 * Blobs are immutable. The binary data referenced by a Blob cannot be modified
 * directly. (This guarantees Blobs will have predictable behavior when passed
 * to asynchronous APIs.) In practice, this is not a limitation; APIs can accept
 * Blobs and return new Blobs. Note that JavaScript strings are also immutable;
 * they behave the same way.
 * <p>
 * <b>Throwing exceptions</b>
 * <p>
 * 
 * Any operation involving a Blob may throw an exception. This includes:
 * <ul>
 * <li>calling all APIs that accept Blob arguments.
 * <li>accessing any attribute or method on a Blob object.
 * </ul>
 * Callers should be prepared to handle exceptions in all these cases.
 * <p>
 * Exceptions can occur because Blobs may be backed by user files. If such a
 * file changes, the Blob referencing it is no longer valid.
 * <p>
 * It is somewhat uncommon for files to change while they are being referenced.
 * But web applications that want to be completely robust should handle this
 * scenario.
 * <p>
 * <b>Permission</b>
 * <p>
 * The Blob API does not require user permission.
 */
public final class Blob extends JavaScriptObject {

  protected Blob() {
    // required for overlay types
  }

  /**
   * Returns the bytes of the Blob.
   * 
   * @return A byte array containing the Blob's bytes.
   */
  public byte[] getBytes() {
    return Utils.toJavaArray(nativeGetBytes());
  }

  /**
   * Returns the bytes of a slice of the Blob from <code>offset</code> to the
   * end of the Blob.
   * 
   * @param offset The position of the first byte to return.
   * @return A byte array containing the Blob's bytes.
   */
  public byte[] getBytes(int offset) {
    return Utils.toJavaArray(nativeGetBytes(offset));
  }

  /**
   * Returns the bytes of a slice of the Blob.
   * 
   * @param offset The position of the first byte to return.
   * @param length The number of bytes to return.
   * @return A byte array containing the Blob's bytes.
   */
  public byte[] getBytes(int offset, int length) {
    return Utils.toJavaArray(nativeGetBytes(offset, length));
  }

  /**
   * Gets the length of the Blob, in bytes.
   * 
   * @return The length of the Blob, in bytes.
   */
  public native int getLength() /*-{
    return this.length;
  }-*/;

  /**
   * Extracts a subset of the current Blob and returns it as a new Blob.
   * 
   * @param offset The position of the first byte to extract.
   * @return A new Blob containing the specified subset.
   */
  public native Blob slice(int offset) /*-{
    return this.slice(offset);
  }-*/;

  /**
   * Extracts a subset of the current Blob and returns it as a new Blob.
   * 
   * @param offset The position of the first byte to extract.
   * @param length The number of bytes to extract. The default value means to
   *          the end of the Blob.
   * @return A new Blob containing the specified subset.
   */
  public native Blob slice(int offset, int length)/*-{
    return this.slice(offset, length);
  }-*/;

  private native JsArrayInteger nativeGetBytes() /*-{
    return this.getBytes();
  }-*/;

  private native JsArrayInteger nativeGetBytes(int offset) /*-{
    return this.getBytes(offset);
  }-*/;

  private native JsArrayInteger nativeGetBytes(int offset, int length) /*-{
    return this.getBytes(offset, length);
  }-*/;
}
