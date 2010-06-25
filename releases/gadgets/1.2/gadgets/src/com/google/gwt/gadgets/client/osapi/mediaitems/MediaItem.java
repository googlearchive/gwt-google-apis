/*
 * Copyright 2010 Google Inc.
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
package com.google.gwt.gadgets.client.osapi.mediaitems;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * A class representing images, movies, and audio.
 */
public class MediaItem extends JavaScriptObject {
  /**
   * @TODO: This class is based on Orkut's support for MediaItem. Adding support
   *        for additional properties might be considered when support for more
   *        containers will be added.
   */

  /**
   * Required by {@link JavaScriptObject} policy.
   */
  protected MediaItem() {
  }

  /**
   * Returns description of the media item.
   *
   * @return Description of the media item.
   */
  public final native String getDescription() /*-{
    return (this.description == null) ? null : this.description;
  }-*/;

  /**
   * Returns id Associated with the media item.
   *
   * @return Id Associated with the media item.
   */
  public final native String getId() /*-{
    return (this.id == null) ? null : this.id;
  }-*/;

  /**
   * Returns URL to a thumbnail image of the media item.
   *
   * @return URL to a thumbnail image of the media item.
   */
  public final native String getThumbnailUrl() /*-{
    return (this.thumbnailUrl == null) ? null : this.thumbnailUrl;
  }-*/;

  /**
   * Returns title describing the media item.
   *
   * @return Title describing the media item.
   */
  public final native String getTitle() /*-{
    return (this.title == null) ? null : this.title;
  }-*/;

  /**
   * Returns the URL where the media can be found.
   *
   * @return The URL where the media can be found.
   */
  public final native String getUrl() /*-{
    return (this.url == null) ? null : this.url;
  }-*/;
}
