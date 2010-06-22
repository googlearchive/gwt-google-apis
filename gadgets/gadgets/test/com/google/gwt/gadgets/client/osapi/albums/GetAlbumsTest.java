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

import com.google.gwt.core.client.JsArray;
import com.google.gwt.gadgets.client.osapi.Callback;
import com.google.gwt.gadgets.client.osapi.GadgetsOsapiTestCase;
import com.google.gwt.gadgets.client.osapi.OsapiCollection;
import com.google.gwt.gadgets.client.osapi.OsapiError;

/**
 * Tests {@link GetAlbumsRequestBuilder} and {@link Album}.
 */
public class GetAlbumsTest extends GadgetsOsapiTestCase {

  public void testGetAlbums_error() {
    setErrorMock();
    asAsync();
    AlbumsService.getInstance().newGetAlbumsRequestBuilder().build().execute(
        new Callback<OsapiCollection<Album>>() {
          public void onSuccess(OsapiCollection<Album> object) {
            fail();

          }

          public void onFail(OsapiError error) {
            assertEquals(500, error.getCode());
            assertEquals("Internal error", error.getMessage());
            finishTest();
          }
        });
  }

  public void testGetAlbums_simple() {
    setMock();
    asAsync();
    AlbumsService.getInstance().newGetAlbumsRequestBuilder().build().execute(
        new Callback<OsapiCollection<Album>>() {
          public void onFail(OsapiError error) {
            fail();
          }

          public void onSuccess(OsapiCollection<Album> collection) {
            assertEquals(1, collection.getItemsPerPage());
            assertEquals(0, collection.getStartIndex());
            assertEquals(3, collection.getTotalResults());
            Album album = collection.getList().get(0);
            assertEquals("1234", album.getId());
            assertEquals("vacation album", album.getTitle());
            assertEquals("sailing vacations", album.getDescription());
            assertEquals("http://example.com/album_photo.jpg", album.getThumbnailUrl());
            assertEquals("tom", album.getOwnerId());
            assertEquals("http://socialpage.com/tom/1234", album.getUrl());
            assertEquals(42, album.getMediaItemCount());
            finishTest();
          }
        });
  }

  public void testGetAlbums_parameters() {
    setParamsMock();
    asAsync();
    AlbumsService albums = AlbumsService.getInstance();
    GetAlbumsRequestBuilder builder = albums.newGetAlbumsRequestBuilder();
    builder.setIds("1", "2", "3");
    builder.build().execute(
        new Callback<OsapiCollection<Album>>() {
          public void onFail(OsapiError error) {
            fail();
          }

          public void onSuccess(OsapiCollection<Album> collection) {
            assertEquals(3, collection.getItemsPerPage());
            assertEquals(0, collection.getStartIndex());
            assertEquals(3, collection.getTotalResults());
            JsArray<Album> list = collection.getList();
            assertEquals(3, list.length());
            assertEquals("1", list.get(0).getId());
            assertEquals("2", list.get(1).getId());
            assertEquals("3", list.get(2).getId());
            finishTest();
          }
        });
  }

  private native void setMock() /*-{
    $wnd.osapi.albums.get = function (param) {
      return {
        "execute" : function (callback) {
          callback({
            "itemsPerPage": 1,
            "startIndex": 0,
            "totalResults": 3,
            "list": [{
              "id": "1234",
              "title": "vacation album",
              "description": "sailing vacations",
              "thumbnailUrl": "http://example.com/album_photo.jpg",
              "ownerId" : "tom",
              "url" : "http://socialpage.com/tom/1234",
              "mediaItemCount": 42
            }]
          });
        }
      };
    }
  }-*/;

  private native void setParamsMock() /*-{
    $wnd.osapi.albums.get = function (param) {
      return {
        "execute" : function (callback) {
          var ids = param.id;
          if (ids == null || ids.sort().toString() != "1,2,3") {
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

  private native void setErrorMock() /*-{
    $wnd.osapi.albums.get = function (param) {
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
}
