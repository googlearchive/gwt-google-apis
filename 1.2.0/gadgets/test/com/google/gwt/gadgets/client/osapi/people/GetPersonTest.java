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
import com.google.gwt.gadgets.client.osapi.OsapiError;

/**
 * Tests {@link GetPersonRequestBuilder} and {@link PeopleService}.
 */
public class GetPersonTest extends GadgetsOsapiTestCase {

  public void testGetPerson_error() {
    setMock("error", "error");
    asAsync();
    PeopleService.getInstance().newGetOwnerRequestBuilder().build().execute(
        new Callback<Person>() {
          public void onFail(OsapiError error) {
            assertEquals(500, error.getCode());
            assertEquals("Internal error", error.getMessage());
            finishTest();
          }

          public void onSuccess(Person person) {
            fail();
          }
        });
  }

  public void testGetPerson_getById() {
    setMock("some_id", "@self");
    asAsync();
    PeopleService.getInstance().newGetPersonByIdRequestBuilder("some_id").build().execute(
        new Callback<Person>() {
          public void onFail(OsapiError error) {
            fail();
          }

          public void onSuccess(Person person) {
            checkReturnedPerson(person);
            finishTest();
          }
        });
  }

  public void testGetPerson_getOwner() {
    setMock("@owner", "@self");
    asAsync();
    PeopleService.getInstance().newGetOwnerRequestBuilder().build().execute(
        new Callback<Person>() {
          public void onFail(OsapiError error) {
            fail();
          }

          public void onSuccess(Person person) {
            checkReturnedPerson(person);
            finishTest();
          }
        });
  }

  public void testGetPerson_getViewer() {
    setMock("@viewer", "@self");
    asAsync();
    PeopleService.getInstance().newGetViewerRequestBuilder().build().execute(
        new Callback<Person>() {
          public void onFail(OsapiError error) {
            fail();
          }

          public void onSuccess(Person person) {
            checkReturnedPerson(person);
            finishTest();
          }
        });
  }

  public void testSetFields() {
    setMockFields();
    asAsync();
    PeopleService people = PeopleService.getInstance();

    GetPersonRequestBuilder builder = people.newGetOwnerRequestBuilder();
    builder.setFields(Person.DISPLAY_NAME, Person.ID, Person.THUMBNAIL_URL);

    builder.build().execute(
        new Callback<Person>() {
          public void onFail(OsapiError error) {
            fail();
          }

          public void onSuccess(Person person) {
            checkReturnedPerson(person);
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

    people.newGetOwnerRequestBuilder().build().execute(
        new Callback<Person>() {
          public void onFail(OsapiError error) {
            fail();
          }

          public void onSuccess(Person person) {
            checkReturnedPerson(person);
            finishTest();
          }
        });
  }


  private void checkReturnedPerson(Person person) {
    assertEquals("John Doe", person.getDisplayName());
    assertEquals("1234", person.getId());
    assertEquals("http://example.com/lovely_photo.jpg",
        person.getThumbnailUrl());
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
              "displayName": "John Doe",
              "id": "1234",
              "thumbnailUrl": "http://example.com/lovely_photo.jpg"
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
              "displayName": "John Doe",
              "id": "1234",
              "thumbnailUrl": "http://example.com/lovely_photo.jpg"
            });
          }
        }
      };
    }
  }-*/;
}
