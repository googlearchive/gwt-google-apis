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
package com.google.gwt.gadgets.client.osapi;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.gadgets.client.osapi.people.GetPeopleRequestBuilder;
import com.google.gwt.gadgets.client.osapi.people.PeopleService;
import com.google.gwt.gadgets.client.osapi.people.Person;

/**
 * Tests {@link OsapiCollection} and {@link CollectionRequestBuilder}.
 */
public class OsapiCollectionTest extends GadgetsOsapiTestCase {

  public void testOsapiCollection_checkContainsFilter() {
    setFilterTestMock("contains", "value");
    asAsync();
    PeopleService people = PeopleService.getInstance();

    GetPeopleRequestBuilder builder = people.newGetOwnersFriendsRequestBuilder();

    builder.setContainsFilter("field", "value");

    builder.build().execute(new Callback<OsapiCollection<Person>>() {
      public void onFail(OsapiError error) {
        fail();
      }

      public void onSuccess(OsapiCollection<Person> collection) {
        assertEquals(0, collection.getTotalResults());
        finishTest();
      }
    });
  }

  public void testOsapiCollection_checkEqualsFilter() {
    setFilterTestMock("equals", "value");
    asAsync();
    PeopleService people = PeopleService.getInstance();

    GetPeopleRequestBuilder builder = people.newGetOwnersFriendsRequestBuilder();

    builder.setEqualsFilter("field", "value");

    builder.build().execute(new Callback<OsapiCollection<Person>>() {
      public void onFail(OsapiError error) {
        fail();
      }

      public void onSuccess(OsapiCollection<Person> collection) {
        assertEquals(0, collection.getTotalResults());
        finishTest();
      }
    });
  }

  public void testOsapiCollection_checkNumbers() {
    setNumbersTestMock();
    asAsync();
    PeopleService people = PeopleService.getInstance();

    GetPeopleRequestBuilder builder = people.newGetOwnersFriendsRequestBuilder();

    builder.setCount(6).setStartIndex(3);

    builder.build().execute(new Callback<OsapiCollection<Person>>() {
      public void onFail(OsapiError error) {
        fail();
      }

      public void onSuccess(OsapiCollection<Person> collection) {
        assertEquals(3, collection.getStartIndex());
        assertEquals(6, collection.getItemsPerPage());
        assertEquals(12, collection.getTotalResults());
        JsArray<Person> list = collection.getList();

        int i;
        for (i = 0; i < list.length(); i++) {
          Person p = list.get(i);
          assertEquals(Integer.toString(i), p.getId());
        }
        assertEquals(6, i);

        i = 0;
        for (Person p : collection.iterable()) {
          assertEquals(Integer.toString(i++), p.getId());
        }
        assertEquals(6, i);
        finishTest();
      }
    });
  }

  public void testOsapiCollection_checkPresentFilter() {
    setFilterTestMock("present", null);
    asAsync();
    PeopleService people = PeopleService.getInstance();

    GetPeopleRequestBuilder builder = people.newGetOwnersFriendsRequestBuilder();

    builder.setPresentFilter("field");

    builder.build().execute(new Callback<OsapiCollection<Person>>() {
      public void onFail(OsapiError error) {
        fail();
      }

      public void onSuccess(OsapiCollection<Person> collection) {
        assertEquals(0, collection.getTotalResults());
        finishTest();
      }
    });
  }

  public void testOsapiCollection_checkSorting() {
    setSortingTestMock();
    asAsync();
    PeopleService people = PeopleService.getInstance();

    GetPeopleRequestBuilder builder = people.newGetOwnersFriendsRequestBuilder();

    builder.setSorting(Person.ID, SortOrder.ASCENDING);

    builder.build().execute(new Callback<OsapiCollection<Person>>() {
      public void onFail(OsapiError error) {
        fail();
      }

      public void onSuccess(OsapiCollection<Person> collection) {
        assertEquals(0, collection.getTotalResults());
        finishTest();
      }
    });
  }

  public void testOsapiCollection_checkStartsWithFilter() {
    setFilterTestMock("startsWith", "value");
    asAsync();
    PeopleService people = PeopleService.getInstance();

    GetPeopleRequestBuilder builder = people.newGetOwnersFriendsRequestBuilder();

    builder.setStartsWithFilter("field", "value");

    builder.build().execute(new Callback<OsapiCollection<Person>>() {
      public void onFail(OsapiError error) {
        fail();
      }

      public void onSuccess(OsapiCollection<Person> collection) {
        assertEquals(0, collection.getTotalResults());
        finishTest();
      }
    });
  }


  private native void setFilterTestMock(String operation, String value) /*-{
  $wnd.osapi.people.get = function (param) {
    return {
      "execute" : function (callback) {
        if (param.filterBy == "field" && param.filterValue == value &&
            param.filterOp == operation) {
          callback({
            "totalResults": 0,
            "list": []
          });
        } else {
          callback({
            "error": {}
          });
        }
      }
    };
  }
}-*/;

  private native void setNumbersTestMock() /*-{
    $wnd.osapi.people.get = function (param) {
      return {
        "execute" : function (callback) {
          if (param.startIndex == 3 && param.count == 6) {
            callback({
              "itemsPerPage": 6,
              "startIndex": 3,
              "totalResults": 12,
              "list": [{"id": "0"}, {"id": "1"}, {"id": "2"},
                {"id": "3"}, {"id": "4"}, {"id": "5"}]
            });
          } else {
            callback({
              "error": {}
            });
          }
        }
      };
    }
  }-*/;

  private native void setSortingTestMock() /*-{
    $wnd.osapi.people.get = function (param) {
      return {
        "execute" : function (callback) {
          if (param.sortBy == "id" && param.sortOrder == "ascending") {
            callback({
              "totalResults": 0,
              "list": []
            });
          } else {
            callback({
              "error": {}
            });
          }
        }
      };
    }
  }-*/;
}
