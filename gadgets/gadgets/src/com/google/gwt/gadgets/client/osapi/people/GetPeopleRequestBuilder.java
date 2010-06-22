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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.gadgets.client.osapi.CollectionRequestBuilder;
import com.google.gwt.gadgets.client.osapi.OsapiCollection;
import com.google.gwt.gadgets.client.osapi.OsapiRequest;

/**
 * Builder for requests returning {@link OsapiCollection} of {@link Person}.
 */
public class GetPeopleRequestBuilder extends
    CollectionRequestBuilder<GetPeopleRequestBuilder> {

  /**
   * Returns new instance of {@link GetPeopleRequestBuilder}.
   * 
   * @return New instance of {@link GetPeopleRequestBuilder}.
   */
  static GetPeopleRequestBuilder newInstance() {
    return JavaScriptObject.createObject().cast();
  }

  /**
   * Required by {@link JavaScriptObject} policy.
   */
  protected GetPeopleRequestBuilder() {
  }

  /**
   * Builds the request.
   * 
   * @return {@link OsapiRequest} instance.
   */
  public final native OsapiRequest<OsapiCollection<Person>> build() /*-{
    return $wnd.osapi.people.get(this);
  }-*/;

  /**
   * Defines a set of fields to be requested.
   * 
   * @see PeopleService#setDefaultFields(String...)
   * 
   * @param fields A set of fields to be requested.
   */
  public final native GetPeopleRequestBuilder setFields(JsArrayString fields) /*-{
    this.fields = fields;
    return this;
  }-*/;

  /**
   * Defines a set of fields to be requested.
   * 
   * @see PeopleService#setDefaultFields(String...)
   * 
   * @param fields A set of fields to be requested.
   */
  public final GetPeopleRequestBuilder setFields(String... fields) {
    JsArrayString array = JavaScriptObject.createArray().cast();
    for (String field : fields) {
      array.push(field);
    }
    return setFields(array);
  }
}
