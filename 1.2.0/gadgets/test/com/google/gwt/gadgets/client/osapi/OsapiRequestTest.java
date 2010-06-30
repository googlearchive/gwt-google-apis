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

import com.google.gwt.gadgets.client.osapi.people.GetPersonRequestBuilder;
import com.google.gwt.gadgets.client.osapi.people.PeopleService;
import com.google.gwt.gadgets.client.osapi.people.Person;
import com.google.gwt.i18n.client.DateTimeFormat;

/**
 * Tests {@link OsapiRequestBuilder}.
 */
public class OsapiRequestTest extends GadgetsOsapiTestCase {

  public void testOsapiRequest_updatedSince() {
    DateTimeFormat dtf = DateTimeFormat.getFormat("yyyy-MM-dd'T'hh:mm:ss.SSSS");
    String date = "2010-06-21T12:30:00.0000";

    setUpdatedSinceMock(date);
    asAsync();

    PeopleService people = PeopleService.getInstance();
    GetPersonRequestBuilder builder = people.newGetOwnerRequestBuilder();
    builder.setUpdatedSince(dtf.parse(date));
    builder.build().execute(new Callback<Person>() {
      public void onFail(OsapiError error) {
        fail();
      }

      public void onSuccess(Person person) {
        assertEquals("1234", person.getId());
        finishTest();
      }
    });
  }

  private native void setUpdatedSinceMock(String updatedSince) /*-{
    $wnd.osapi.people.get = function (param) {
      return {
        "execute" : function (callback) {
          if (param.updatedSince == updatedSince) {
            callback({
              "id": "1234"
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
