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
package com.google.gwt.opensocial.client;

/**
 * Media item associated with an activity. Represents images, movies, and
 * audio.
 *
 * @see "http://code.google.com/apis/opensocial/docs/0.8/reference/#opensocial.MediaItem"
 */
public final class MediaItem {
  public enum Field {
    /**
     * The type of media, specified as a {@link Type}.
     */
    TYPE("type"),

    /**
     * The MIME type of media, specified as a {@link java.lang.String}.
     */
    MIME_TYPE("mimeType"),

    /**
     * A {@link java.lang.String} specifying the URL where the media can be found.
     */
    URL("url");

    private String value = null;

    private Field(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return this.value;
    }
  }

  public enum Type {
    IMAGE("image"),

    VIDEO("video"),

    AUDIO("audio");

    private String value = null;

    private Type(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return this.value;
    }
  }

  /**
   * Gets the media item data that's associated with the specified key.
   *
   * @param key The key to get data for; see the Field class for possible
   *        values
   * @return The data
   */
  public native String getField(String key) /*-{
    return this.getField(key);
  }-*/;

  /**
   * Sets data for this media item associated with the given key.
   *
   * @param key The key to set data for
   * @param data The data to set
   */
  public native void setField(String key, String data) /*-{
    this.setField(key, data);
  }-*/;
}
