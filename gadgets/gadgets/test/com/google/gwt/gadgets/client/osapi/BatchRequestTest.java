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

import com.google.gwt.gadgets.client.osapi.people.PeopleService;
import com.google.gwt.gadgets.client.osapi.people.Person;

/**
 * Tests {@link BatchRequest}.
 */
public class BatchRequestTest extends GadgetsOsapiTestCase {

  /**
   * This is a workaround for inability to count callback returns with an int,
   * which cannot be assignable and final at the same time.
   */
  private static class Counter {
    int counter = 0;

    public int getValue() {
      return counter;
    }

    public void increase() {
      counter++;
    }
  }

  /**
   * This test adds a number of requests and checks if the code of wrapper of
   * {@link BatchRequest} called right callbacks.
   */
  public void testBatchRequest() {
    setMock();
    PeopleService people = PeopleService.getInstance();
    BatchRequest batch = BatchRequest.newInstance();

    int numberOfRequests = 0;
    final Counter numberOfReturns = new Counter();

    numberOfRequests++;
    batch.add(people.newGetPersonByIdRequestBuilder("1").build(),
        new Callback<Person>() {
          public void onFail(OsapiError error) {
            fail();
          }

          public void onSuccess(Person person) {
            numberOfReturns.increase();
            assertEquals("1", person.getId());
          }
        });

    numberOfRequests++;
    batch.add(people.newGetPersonByIdRequestBuilder("fail").build(),
        new Callback<Person>() {
          public void onFail(OsapiError error) {
            numberOfReturns.increase();
            assertEquals("fail", error.getMessage());
          }

          public void onSuccess(Person person) {
            fail();
          }
        });

    numberOfRequests++;
    batch.add(people.newGetPersonByIdRequestBuilder(null).build(),
        new Callback<Person>() {
          public void onFail(OsapiError error) {
            numberOfReturns.increase();
            assertEquals("top fail", error.getMessage());
          }

          public void onSuccess(Person person) {
            fail();
          }
        });

    numberOfRequests++;
    batch.add(people.newGetPersonByIdRequestBuilder("2").build(),
        new Callback<Person>() {
          public void onFail(OsapiError error) {
            fail();
          }

          public void onSuccess(Person person) {
            numberOfReturns.increase();
            assertEquals("2", person.getId());
          }
        });

    numberOfRequests++;
    batch.add(people.newGetPersonByIdRequestBuilder("fail").build(),
        new Callback<Person>() {
          public void onFail(OsapiError error) {
            numberOfReturns.increase();
            assertEquals("fail", error.getMessage());
          }

          public void onSuccess(Person person) {
            fail();
          }
        });

    numberOfRequests++;
    batch.add(people.newGetPersonByIdRequestBuilder("3").build(),
        new Callback<Person>() {
          public void onFail(OsapiError error) {
            fail();
          }

          public void onSuccess(Person person) {
            numberOfReturns.increase();
            assertEquals("3", person.getId());
          }
        });

    numberOfRequests++;
    batch.add(people.newGetPersonByIdRequestBuilder(null).build(),
        new Callback<Person>() {
          public void onFail(OsapiError error) {
            numberOfReturns.increase();
            assertEquals("top fail", error.getMessage());
          }

          public void onSuccess(Person person) {
            fail();
          }
        });

    batch.execute();
    assertEquals(numberOfRequests, numberOfReturns.getValue());
  }

  private native void setMock() /*-{
    // This mock stores added requests, and execute them sequentially when
    // execute() method is called on it. Results are stored under key passed as
    // the key of the request - as described by specification. If request will
    // result in a null value, it's result is not added to results store.
    $wnd.osapi.newBatch = function () {
      var requests_store = {};
      return {
        "add": function (key, request) {
          requests_store[key] = request;
        },
        "execute": function (callback) {
          var requests_results = {};
          for (var key in requests_store) {
            var request = requests_store[key];
            request.execute(function (result) {
              if (result != null) {
                requests_results[key] = result;
              }
            });
          }
          requests_results.error = {
            "message": "top fail"
          }
          callback(requests_results);
        }
      }
    };
    // if userId is null, request returns null
    // if userId is "fail", request returns error
    // otherwise, request returns person object with id equal to passed userId
    $wnd.osapi.people.get = function (param) {
      return {
        "execute" : function (callback) {
          if (param.userId == null) {
            callback(null);
          } else if (param.userId == "fail") {
            callback({
              "error": {
                "message": "fail"
              }
            });
          } else {
            callback({
              "id": param.userId
            });
          }
        }
      };
    }
  }-*/;
}
