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

import com.google.gwt.core.client.JsArray;
import com.google.gwt.gadgets.client.osapi.Callback;
import com.google.gwt.gadgets.client.osapi.GadgetsOsapiTestCase;
import com.google.gwt.gadgets.client.osapi.OsapiCollection;
import com.google.gwt.gadgets.client.osapi.OsapiError;

/**
 * Tests {@link GetMediaItemsRequestBuilder} and {@link MediaItem}.
 */
public class GetMediaItemsTest extends GadgetsOsapiTestCase {

  public void testGetMediaItems_error() {
    setErrorMock();
    asAsync();
    MediaItemService.getInstance().newGetMediaItemsRequestBuilder().build().execute(
        new Callback<OsapiCollection<MediaItem>>() {
          public void onSuccess(OsapiCollection<MediaItem> object) {
            fail();
          }

          public void onFail(OsapiError error) {
            assertEquals(500, error.getCode());
            assertEquals("Internal error", error.getMessage());
            finishTest();
          }
        });
  }

  public void testGetMediaItems_simple() {
    setMock();
    asAsync();
    MediaItemService.getInstance().newGetMediaItemsRequestBuilder().build().execute(
        new Callback<OsapiCollection<MediaItem>>() {
          public void onFail(OsapiError error) {
            fail();
          }

          public void onSuccess(OsapiCollection<MediaItem> collection) {
            assertEquals(1, collection.getItemsPerPage());
            assertEquals(0, collection.getStartIndex());
            assertEquals(3, collection.getTotalResults());
            MediaItem mediaItem = collection.getList().get(0);
            assertEquals("1234", mediaItem.getId());
            assertEquals("vacation photo", mediaItem.getTitle());
            assertEquals("sailing photo", mediaItem.getDescription());
            assertEquals("http://example.com/photo.jpg",
                mediaItem.getThumbnailUrl());
            assertEquals("http://socialpage.com/tom/1234", mediaItem.getUrl());
            finishTest();
          }
        });
  }

  public void testGetMediaItems_parameters() {
    setParamsMock();
    asAsync();
    MediaItemService mediaItems = MediaItemService.getInstance();
    GetMediaItemsRequestBuilder builder = mediaItems.newGetMediaItemsRequestBuilder();

    builder.setAlbumId("album1234");
    builder.setIds("1", "2", "3");

    builder.build().execute(new Callback<OsapiCollection<MediaItem>>() {
      public void onFail(OsapiError error) {
        fail();
      }

      public void onSuccess(OsapiCollection<MediaItem> collection) {
        assertEquals(3, collection.getItemsPerPage());
        assertEquals(0, collection.getStartIndex());
        assertEquals(3, collection.getTotalResults());
        JsArray<MediaItem> list = collection.getList();
        assertEquals(3, list.length());
        assertEquals("1", list.get(0).getId());
        assertEquals("2", list.get(1).getId());
        assertEquals("3", list.get(2).getId());
        finishTest();
      }
    });
  }

  private native void setMock() /*-{
    $wnd.osapi.mediaitems.get = function (param) {
      return {
        "execute" : function (callback) {
          callback({
            "itemsPerPage": 1,
            "startIndex": 0,
            "totalResults": 3,
            "list": [{
              "id": "1234",
              "title": "vacation photo",
              "description": "sailing photo",
              "thumbnailUrl": "http://example.com/photo.jpg",
              "url" : "http://socialpage.com/tom/1234",
            }]
          });
        }
      };
    }
  }-*/;

  private native void setErrorMock() /*-{
    $wnd.osapi.mediaitems.get = function (param) {
      return {
        "execute" : function (callback) {
          callback({
            "error": {
              "code": 500,
              "message": "Internal error"
            }
          });
        }
      };
    }
  }-*/;

  private native void setParamsMock() /*-{
    $wnd.osapi.mediaitems.get = function (param) {
      return {
        "execute" : function (callback) {
          var ids = param.id;
          var albumId = param.albumId;
          if (albumId == null || albumId != "album1234" || ids == null
              || ids.sort().toString() != "1,2,3") {
            callback({
              "error": {}
            });
          } else {
            callback({
              "itemsPerPage": 3,
              "startIndex": 0,
              "totalResults": 3,
              "list": [{
                  "id": "1",
                },{
                  "id": "2",
                },{
                  "id": "3",
                }
              ]
            });
          }
        }
      };
    }
  }-*/;
}
