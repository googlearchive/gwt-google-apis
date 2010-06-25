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
package com.google.gwt.gadgets.client.osapi.albums;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.gadgets.client.osapi.mediaitems.MediaItem;

/**
 * Albums represents collections of {@link MediaItem}.
 */
public class Album extends JavaScriptObject {
  /**
   * @TODO: This class is based on Orkut's support for Album. Adding support
   *        for additional properties might be considered when support for more
   *        containers will be added.
   */

  /**
   * Required by {@link JavaScriptObject} policy.
   */
  protected Album() {
  }

  /**
   * Returns description of the album.
   *
   * @return Description of the album.
   */
  public final native String getDescription() /*-{
    return (this.description == null) ? null : this.description;
  }-*/;

  /**
   * Returns unique identifier for the album.
   *
   * @return Unique identifier for the album.
   */
  public final native String getId() /*-{
    return (this.id == null) ? null : this.id;
  }-*/;

  /**
   * Returns id of the owner of the album.
   *
   * @return Id of the owner of the album.
   */
  public final native String getOwnerId() /*-{
    return (this.ownerId == null) ? null : this.ownerId;
  }-*/;

  /**
   * Returns URL to a thumbnail cover of the album.
   *
   * @return URL to a thumbnail cover of the album.
   */
  public final native String getThumbnailUrl() /*-{
    return (this.thumbnailUrl == null) ? null : this.thumbnailUrl;
  }-*/;

  /**
   * Returns the title of the album.
   *
   * @return The title of the album.
   */
  public final native String getTitle() /*-{
    return (this.title == null) ? null : this.title;
  }-*/;

  /**
   * Returns the URL of the album.
   *
   * @return The URL of the album.
   */
  public final native String getUrl() /*-{
    return (this.url == null) ? null : this.url;
  }-*/;

  /**
   * Returns number of items in the album.
   *
   * @return Number of items in the album.
   */
  public final native int getMediaItemCount() /*-{
    return (this.mediaItemCount == null) ? null : this.mediaItemCount;
  }-*/;
}
