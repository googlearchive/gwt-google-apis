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
package com.google.gwt.gadgets.client.osapi.people;

import com.google.gwt.gadgets.client.osapi.Callback;
import com.google.gwt.gadgets.client.osapi.GadgetsOsapiTestCase;
import com.google.gwt.gadgets.client.osapi.OsapiCollection;
import com.google.gwt.gadgets.client.osapi.OsapiError;

/**
 * Tests {@link GetPeopleRequestBuilder} and {@link PeopleService}.
 */
public class GetPeopleTest extends GadgetsOsapiTestCase {

  public void testGetPeople_error() {
    setMock("error", "error");
    asAsync();
    PeopleService.getInstance().newGetOwnersFriendsRequestBuilder().build().execute(
        new Callback<OsapiCollection<Person>>() {
          public void onFail(OsapiError error) {
            assertEquals(500, error.getCode());
            assertEquals("Internal error", error.getMessage());
            finishTest();
          }

          public void onSuccess(OsapiCollection<Person> collection) {
            fail();
          }
        });
  }

  public void testGetPeople_byId() {
    setMock("user", "group");
    asAsync();
    PeopleService.getInstance().newGetGroupByIdRequestBuilder("user", "group").build().execute(
        new Callback<OsapiCollection<Person>>() {
          public void onFail(OsapiError error) {
            fail();
          }

          public void onSuccess(OsapiCollection<Person> collection) {
            checkReturnedCollection(collection);
            finishTest();
          }
        });
  }

  public void testGetPeople_ownersAll() {
    setMock("@owner", "@all");
    asAsync();
    PeopleService.getInstance().newGetOwnersAllRequestBuilder().build().execute(
        new Callback<OsapiCollection<Person>>() {
          public void onFail(OsapiError error) {
            fail();
          }

          public void onSuccess(OsapiCollection<Person> collection) {
            checkReturnedCollection(collection);
            finishTest();
          }
        });
  }

  public void testGetPeople_ownersFriends() {
    setMock("@owner", "@friends");
    asAsync();
    PeopleService.getInstance().newGetOwnersFriendsRequestBuilder().build().execute(
        new Callback<OsapiCollection<Person>>() {
          public void onFail(OsapiError error) {
            fail();
          }

          public void onSuccess(OsapiCollection<Person> collection) {
            checkReturnedCollection(collection);
            finishTest();
          }
        });
  }

  public void testGetPeople_viewersAll() {
    setMock("@viewer", "@all");
    asAsync();
    PeopleService.getInstance().newGetViewersAllRequestBuilder().build().execute(
        new Callback<OsapiCollection<Person>>() {
          public void onFail(OsapiError error) {
            fail();
          }

          public void onSuccess(OsapiCollection<Person> collection) {
            checkReturnedCollection(collection);
            finishTest();
          }
        });
  }

  public void testGetPeople_viewersFriends() {
    setMock("@viewer", "@friends");
    asAsync();
    PeopleService.getInstance().newGetViewersFriendsRequestBuilder().build().execute(
        new Callback<OsapiCollection<Person>>() {
          public void onFail(OsapiError error) {
            fail();
          }

          public void onSuccess(OsapiCollection<Person> collection) {
            checkReturnedCollection(collection);
            finishTest();
          }
        });
  }

  public void testSetFields() {
    setMockFields();
    asAsync();
    PeopleService people = PeopleService.getInstance();

    GetPeopleRequestBuilder builder = people.newGetOwnersAllRequestBuilder();
    builder.setFields(Person.DISPLAY_NAME, Person.ID, Person.THUMBNAIL_URL);

    builder.build().execute(new Callback<OsapiCollection<Person>>() {
      public void onFail(OsapiError error) {
        fail();
      }

      public void onSuccess(OsapiCollection<Person> collection) {
        checkReturnedCollection(collection);
        finishTest();
      }
    });
  }

  public void testSetFields_defaultFields() {
    setMockFields();
    asAsync();
    PeopleService people = PeopleService.getInstance();

    people.setDefaultFields(Person.DISPLAY_NAME, Person.ID,
        Person.THUMBNAIL_URL);

    people.newGetOwnersAllRequestBuilder().build().execute(
        new Callback<OsapiCollection<Person>>() {
          public void onFail(OsapiError error) {
            fail();
          }

          public void onSuccess(OsapiCollection<Person> collection) {
            checkReturnedCollection(collection);
            finishTest();
          }
        });
  }

  private void checkReturnedCollection(OsapiCollection<Person> collection) {
    assertEquals(2, collection.getItemsPerPage());
    assertEquals(1, collection.getStartIndex());
    assertEquals(3, collection.getTotalResults());
    // No need to test the returned list, we just need to be sure that the
    // right object was returned. OsapiCollection is tested in it's own test.
  }

  private native void setMock(String userId, String groupId) /*-{
    $wnd.osapi.people.get = function (param) {
      return {
        "execute" : function (callback) {
          if (param.userId != userId || param.groupId != groupId) {
            callback({
              "error": {
                "code": 500,
                "message": "Internal error"
              }
            });
          } else {
            callback({
              "itemsPerPage": 2,
              "startIndex": 1,
              "totalResults": 3,
              "list": [{
                  "displayName": "John Doe",
                  "id": "1234",
                  "thumbnailUrl": "http://example.com/vacation_photo.jpg"
                }, {
                  "displayName": "Jane Doe",
                  "id": "4321",
                  "thumbnailUrl": "http://example.com/lovely_photo.jpg"
                }
              ]
            });
          }
        }
      };
    }
  }-*/;

  private native void setMockFields() /*-{
    $wnd.osapi.people.get = function (param) {
      return {
        "execute" : function (callback) {
          var fields = param.fields;
          if (fields == null || fields.sort().toString() != "displayName,id,thumbnailUrl") {
            callback({
              "error": {
                "code": 500,
                "message": "Internal error"
              }
            });
          } else {
            callback({
              "itemsPerPage": 2,
              "startIndex": 1,
              "totalResults": 3,
              "list": [{
                  "displayName": "John Doe",
                  "id": "1234",
                  "thumbnailUrl": "http://example.com/vacation_photo.jpg"
                }, {
                  "displayName": "Jane Doe",
                  "id": "4321",
                  "thumbnailUrl": "http://example.com/lovely_photo.jpg"
                }
              ]
            });
          }
        }
      };
    }
  }-*/;
}
