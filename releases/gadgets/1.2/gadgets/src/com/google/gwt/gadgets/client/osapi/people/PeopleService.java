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
import com.google.gwt.gadgets.client.osapi.OsapiFeature;

/**
 * A class representing a service object, which maps to opensocial People
 * Service.
 *
 * @see "http://opensocial-resources.googlecode.com/svn/spec/1.0/Social-API-Server.xml#People-Service"
 */
public class PeopleService {

  private static PeopleService service;

  /**
   * Returns an instance of {@link PeopleService}. Note that this class is a
   * singleton and all instances returned by this method are identical.
   *
   * @return An instance of {@link PeopleService}.
   */
  public static PeopleService getInstance() {
    if (service == null) {
      service = new PeopleService();
    }
    return service;
  }

  private JsArrayString defaultFields;

  /**
   * Private constructor to enforce singleton pattern.
   */
  private PeopleService() {
  }

  /**
   * Returns an instance of {@link GetPeopleRequestBuilder} requesting specific
   * group of the specific user.
   *
   * @return An instance of {@link GetPeopleRequestBuilder} requesting specific
   *         group of the specific user.
   */
  public GetPeopleRequestBuilder newGetGroupByIdRequestBuilder(String userId,
      String groupId) {
    GetPeopleRequestBuilder builder = GetPeopleRequestBuilder.newInstance();
    builder.setUserId(userId);
    builder.setGroupId(groupId);
    builder.setFields(defaultFields);
    return builder;
  }

  /**
   * Returns an instance of {@link GetPersonRequestBuilder} requesting an owner
   * of the gadget.
   *
   * @return An instance of {@link GetPersonRequestBuilder} requesting an owner
   *         of the gadget.
   */
  public GetPersonRequestBuilder newGetOwnerRequestBuilder() {
    GetPersonRequestBuilder builder = GetPersonRequestBuilder.newInstance();
    builder.setUserId(OsapiFeature.USER_OWNER);
    builder.setGroupId(OsapiFeature.GROUP_SELF);
    builder.setFields(defaultFields);
    return builder;
  }

  /**
   * Returns an instance of {@link GetPeopleRequestBuilder} requesting all
   * contacts of the owner of the gadget.
   *
   * @return An instance of {@link GetPersonRequestBuilder} requesting all
   *         contacts of the owner of the gadget.
   */
  public GetPeopleRequestBuilder newGetOwnersAllRequestBuilder() {
    GetPeopleRequestBuilder builder = GetPeopleRequestBuilder.newInstance();
    builder.setUserId(OsapiFeature.USER_OWNER);
    builder.setGroupId(OsapiFeature.GROUP_ALL);
    builder.setFields(defaultFields);
    return builder;
  }

  /**
   * Returns an instance of {@link GetPeopleRequestBuilder} requesting friends
   * of the owner of the gadget.
   *
   * @return An instance of {@link GetPersonRequestBuilder} requesting friends
   *         of the owner of the gadget.
   */
  public GetPeopleRequestBuilder newGetOwnersFriendsRequestBuilder() {
    GetPeopleRequestBuilder builder = GetPeopleRequestBuilder.newInstance();
    builder.setUserId(OsapiFeature.USER_OWNER);
    builder.setGroupId(OsapiFeature.GROUP_FRIENDS);
    builder.setFields(defaultFields);
    return builder;
  }

  /**
   * Returns an instance of {@link GetPersonRequestBuilder} requesting a person
   * with given id.
   *
   * @param userId
   * @return An instance of {@link GetPersonRequestBuilder} requesting a person
   *         with given id.
   */
  public GetPersonRequestBuilder newGetPersonByIdRequestBuilder(String userId) {
    GetPersonRequestBuilder builder = GetPersonRequestBuilder.newInstance();
    builder.setUserId(userId);
    builder.setGroupId(OsapiFeature.GROUP_SELF);
    builder.setFields(defaultFields);
    return builder;
  }

  /**
   * Returns an instance of {@link GetPersonRequestBuilder} requesting a viewer
   * of the gadget.
   *
   * @return An instance of {@link GetPersonRequestBuilder} requesting a viewer
   *         of the gadget.
   */
  public GetPersonRequestBuilder newGetViewerRequestBuilder() {
    GetPersonRequestBuilder builder = GetPersonRequestBuilder.newInstance();
    builder.setUserId(OsapiFeature.USER_VIEWER);
    builder.setGroupId(OsapiFeature.GROUP_SELF);
    builder.setFields(defaultFields);
    return builder;
  }

  /**
   * Returns an instance of {@link GetPeopleRequestBuilder} requesting all
   * contacts of the viewer of the gadget.
   *
   * @return An instance of {@link GetPersonRequestBuilder} requesting all
   *         contacts of the viewer of the gadget.
   */
  public GetPeopleRequestBuilder newGetViewersAllRequestBuilder() {
    GetPeopleRequestBuilder builder = GetPeopleRequestBuilder.newInstance();
    builder.setUserId(OsapiFeature.USER_VIEWER);
    builder.setGroupId(OsapiFeature.GROUP_ALL);
    builder.setFields(defaultFields);
    return builder;
  }

  /**
   * Returns an instance of {@link GetPeopleRequestBuilder} requesting friends
   * of the viewer of the gadget.
   *
   * @return An instance of {@link GetPersonRequestBuilder} requesting friends
   *         of the viewer of the gadget.
   */
  public GetPeopleRequestBuilder newGetViewersFriendsRequestBuilder() {
    GetPeopleRequestBuilder builder = GetPeopleRequestBuilder.newInstance();
    builder.setUserId(OsapiFeature.USER_VIEWER);
    builder.setGroupId(OsapiFeature.GROUP_FRIENDS);
    builder.setFields(defaultFields);
    return builder;
  }

  /**
   * Convenience method to define a default set of fields to request.
   * Invocations of this method does not affect requests created with parameters
   * which explicitly define fields to request.
   *
   * @param fields Default set of fields to request.
   */
  public void setDefaultFields(String... fields) {
    defaultFields = JavaScriptObject.createArray().cast();
    for (String field : fields) {
      defaultFields.push(field);
    }
  }
}
