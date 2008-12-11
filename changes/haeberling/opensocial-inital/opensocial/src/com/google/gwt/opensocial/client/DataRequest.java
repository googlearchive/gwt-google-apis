/*
 * Copyright 2008 Google Inc.
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
package com.google.gwt.opensocial.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.opensocial.client.util.Array;
import com.google.gwt.opensocial.client.util.DataRequestItem;
import com.google.gwt.opensocial.client.util.Map;
import com.google.gwt.opensocial.client.util.OneArgumentFunction;

/**
 * Creates an item to request social information from the container. This
 * includes data for friends, profiles, app data, and activities. All apps that
 * require access to people information should send a DataRequest.
 *
 * @see "http://code.google.com/apis/opensocial/docs/0.8/reference/#opensocial.DataRequest"
 */
public class DataRequest extends JavaScriptObject {
  /*
   * Do not create a DataRequest directly, instead use
   * OpenSocial.newDataRequest();
   */
  protected DataRequest() {
  }

  /**
   * A person ID, which can be one of the special values VIEWER, OWNER,
   * VIEWER_FRIENDS, OWNER_FRIENDS, or any arbitrary string.
   */
  public enum Id {
    VIEWER("VIEWER"),
    OWNER("OWNER"),
    VIEWER_FRIENDS("VIEWER_FRIENDS"),
    OWNER_FRIENDS("OWNER_FRIENDS");

    private String value = null;

    Id(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return value;
    }
  }

  /**
   * Enum for params passed to DataRequest.newFetchPeopleRequest method.
   */
  public enum PeopleRequestFields {
    /**
     * An array of {@link Person.Field} specifying what profile data to fetch
     * for each of the person objects. The server will always include ID, NAME,
     * and THUMBNAIL_URL.
     */
    PROFILE_DETAILS("profileDetail"),

    /**
     * A sort order for the people objects; defaults to TOP_FRIENDS. Possible
     * values are defined by {@link DataRequest.SortOrder}
     */
    SORT_ORDER("sortOrder"),

    /**
     * How to filter the people objects; defaults to ALL. Possible values are
     * defined by {@link DataRequest.FilterType}
     */
    FILTER("filter"),

    /**
     * When paginating, the index of the first item to fetch. Specified as a
     * int.
     */
    FIRST("first"),

    /**
     * The maximum number of items to fetch; defaults to 20. If set to a larger
     * number, a container may honor the request, or may limit the number to a
     * container-specified limit of at least 20. Specified as a int.
     */
    MAX("max");

    private String value;

    PeopleRequestFields(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return this.value;
    }
  }

  /**
   * The sort orders available for ordering person objects.
   */
  public enum SortOrder {
    /**
     * When used will sort people by the container's definition of top friends.
     */
    TOP_FRIENDS("topFriends"),

    /**
     * When used will sort people alphabetically by the name field.
     */
    NAME("name");
    private String value = null;

    SortOrder(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return value;
    }
  }

  /**
   * The filters available for limiting person requests.
   */
  public enum FilterType {
    /**
     * Retrieves all friends.
     */
    ALL("all"),

    /**
     * Retrieves all friends with any data for this application.
     */
    HAS_APP("hasApp");
    private String value = null;

    FilterType(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return value;
    }
  }

  /**
   * The escape types available for escaping app data requests.
   */
  public enum EscapeType {
    /**
     * When used will HTML-escape the data.
     */
    HTML_ESCAPE("htmlEscape"),

    /**
     * When used will not escape the data.
     */
    NONE("none");
    private String value = null;

    EscapeType(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return value;
    }
  }

  /**
   * The fields available as extra parameters to an activity request.
   */
  public enum ActivityRequestFields {
    /**
     * If provided will filter all activities by this app Id. at the moment you
     * can only request activities for your own app
     */
    APP_ID("appId");
    private String value;

    private ActivityRequestFields(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return this.value;
    }
  }

  /**
   * The fields available as extra parameters to an app data request.
   */
  public enum DataRequestFields {
    /**
     * How to escape person data returned from the server; defaults to
     * HTML_ESCAPE, Possible values are defined by {@link EscapeType}.
     */
    ESCAPE_TYPE("escapeType");
    private String value;

    private DataRequestFields(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return this.value;
    }
  }

  /**
   * Adds an item to fetch (get) or update (set) data from the server. A single
   * DataRequest object can have multiple items.
   *
   * @param object specifies which data to fetch or update
   */
  public final native void add(DataRequestItem object) /*-{
    this.add(object);
  }-*/;

  /**
   * Adds an item to fetch (get) or update (set) data from the server. A single
   * DataRequest object can have multiple items.
   *
   * @param object specifies which data to fetch or update
   * @param key a key to map the generated response data to
   */
  public final native void add(JavaScriptObject object, String key) /*-{
    this.add(object, key);
  }-*/;

  /**
   * Creates an item to request an activity stream from the server. When
   * processed, returns a {@link DataRequestItem} object.
   *
   * @param idSpec a {@link Id} to specify which people to fetch
   */
  public final native DataRequestItem newFetchActivitiesRequest(Id idSpec) /*-{
    return this.newFetchActivitiesRequest(idSpec.@com.google.gwt.opensocial.client.DataRequest.Id::toString()());
  }-*/;

  /**
   * Creates an item to request an activity stream from the server. When
   * processed, returns a {@link DataRequestItem} object.
   *
   * @param idSpec a {@link Id} to specify which people to fetch
   * @param params a
   *        <code>Map.<opensocial.DataRequest.ActivityRequestFields, Object></code>
   *        containing additional parameters to pass to the request; not
   *        currently used
   */
  public final native DataRequestItem newFetchActivitiesRequest(Id idSpec,
      Map<ActivityRequestFields, Object> params) /*-{
    var paramsObj = params.@com.google.gwt.opensocial.client.util.Map::toJavaScript()();
    return this.newFetchActivitiesRequest(idSpec.@com.google.gwt.opensocial.client.DataRequest.Id::toString()(), paramsObj);
  }-*/;


  /**
   * Creates an item to request friends from the server. When processed,
   * returns a {@link DataRequestItem} object.
   *
   * @param idSpec a {@link Id} to specify which people to fetch
   */
  public final native DataRequestItem newFetchPeopleRequest(Id idSpec) /*-{
    return this.newFetchPeopleRequest(idSpec);
  }-*/;

  /**
   * Creates an item to request friends from the server. When processed,
   * returns a {@link DataRequestItem} object.
   *
   * @param idSpec a {@link Id} to specify which people to fetch
   * @param params additional parameters to pass to the request
   */
  public final native DataRequestItem newFetchPeopleRequest(Id idSpec,
      Map<PeopleRequestFields, Object> params) /*-{
    var paramsObj = params.@com.google.gwt.opensocial.client.util.Map::toJavaScript()();
    return this.newFetchPeopleRequest(idSpec.@com.google.gwt.opensocial.client.DataRequest.Id::toString()(), paramsObj);
  }-*/;

  /**
   * Creates an item to request friends from the server. When processed,
   * returns a {@link DataRequestItem} object.
   *
   * @param idSpec a {@link Array}&lt;{@link Id}&gt; to specify which people to
   *        fetch
   */
  public final native DataRequestItem newFetchPeopleRequest(Array<Id> idSpec) /*-{
    return this.newFetchPeopleRequest(id);
  }-*/;

  /**
   * Creates an item to request friends from the server. When processed,
   * returns a {@link DataRequestItem} object.
   *
   * @param idSpec a {@link Array}&lt;{@link Id}&gt; to specify which people to
   *        fetch
   * @param params additional parameters to pass to the request
   */
  public final native DataRequestItem newFetchPeopleRequest(Array<Id> idSpec,
      Map<PeopleRequestFields, Object> params) /*-{
    var paramsObj = params.@com.google.gwt.opensocial.client.util.Map::toJavaScript()();
    return this.newFetchPeopleRequest(id, paramsObj);
  }-*/;

  /**
   * Creates an item to request app data for the given people.
   *
   * @param idSpec a {@link Id} enum to specify which people to fetch
   * @param key a {@link String} to specify which data to fetch
   */
  public final native DataRequestItem newFetchPersonAppDataRequest(Id idSpec, String key) /*-{
    return this.newFetchPersonAppDataRequest(idSpec.@com.google.gwt.opensocial.client.DataRequest.Id::toString()(), key);
  }-*/;

  /**
   * Creates an item to request app data for the given people.
   *
   * @param idSpec a {@link Array}&lt;{@link Id}&gt; to specify which people to
   *        fetch
   * @param key a {@link String} to specify which data to fetch
   */
  public final native DataRequestItem newFetchPersonAppDataRequest(Array<Id> idSpec, String key) /*-{
    return this.newFetchPersonAppDataRequest(id, key);
  }-*/;

  /**
   * Creates an item to request app data for the given people.
   *
   * @param idSpec a {@link Id} enum to specify which people to fetch
   * @param keys a {@link Array}&lt;{@link String}&gt; to specify which data to
   *        fetch
   */
  public final native DataRequestItem newFetchPersonAppDataRequest(Id idSpec, Array<String> keys) /*-{
    return this.newFetchPersonAppDataRequest(idSpec.@com.google.gwt.opensocial.client.DataRequest.Id::toString()(), keys);
  }-*/;

  /**
   * Creates an item to request app data for the given people.
   *
   * @param idSpec a {@link Array}&lt;{@link Id}&gt; to specify which people to
   *        fetch
   * @param keys a {@link Array}&lt;{@link String}&gt; to specify which data to
   *        fetch
   */
  public final native DataRequestItem newFetchPersonAppDataRequest(Array<Id> idSpec,
      Array<String> keys) /*-{
    return this.newFetchPersonAppDataRequest(id, keys);
  }-*/;

  /**
   * Creates an item to request app data for the given people.
   *
   * @param idSpec a {@link Id} enum to specify which people to fetch
   * @param key a {@link String} to specify which data to fetch
   * @param params additional parameters to pass to the request
   */
  public final native DataRequestItem newFetchPersonAppDataRequest(Id idSpec, String key,
      Map<DataRequestFields, Object> params) /*-{
    var paramsObj = params.@com.google.gwt.opensocial.client.util.Map::toJavaScript()();
    return this.newFetchPersonAppDataRequest(idSpec.@com.google.gwt.opensocial.client.DataRequest.Id::toString()(), key, paramsObj);
  }-*/;

  /**
   * Creates an item to request app data for the given people.
   *
   * @param idSpec a {@link Array}&lt;{@link Id}&gt; to specify which people to
   *        fetch
   * @param key a {@link String} to specify which data to fetch
   * @param params additional parameters to pass to the request
   */
  public final native DataRequestItem newFetchPersonAppDataRequest(Array<Id> idSpec, String key,
      Map<DataRequestFields, Object> params) /*-{
    var paramsObj = params.@com.google.gwt.opensocial.client.util.Map::toJavaScript()();
    return this.newFetchPersonAppDataRequest(idSpec, key, paramsObj);
  }-*/;

  /**
   * Creates an item to request app data for the given people.
   *
   * @param idSpec a {@link Id} enum to specify which people to fetch
   * @param keys a {@link Array}&lt;{@link String}&gt; to specify which data to
   *        fetch
   * @param params additional parameters to pass to the request
   */
  public final native DataRequestItem newFetchPersonAppDataRequest(Id idSpec, Array<String> keys,
      Map<DataRequestFields, Object> params) /*-{
    var paramsObj = params.@com.google.gwt.opensocial.client.util.Map::toJavaScript()();
    return this.newFetchPersonAppDataRequest(idSpec.@com.google.gwt.opensocial.client.DataRequest.Id::toString()(), keys, paramsObj);
  }-*/;

  /**
   * Creates an item to request app data for the given people.
   *
   * @param idSpec a {@link Array}&lt;{@link Id}&gt; to specify which people to
   *        fetch
   * @param keys a {@link Array}&lt;{@link String}&gt; to specify which data to
   *        fetch
   * @param params additional parameters to pass to the request
   */
  public final native DataRequestItem newFetchPersonAppDataRequest(Array<Id> idSpec,
      Array<String> keys, Map<DataRequestFields, Object> params) /*-{
    var paramsObj = params.@com.google.gwt.opensocial.client.util.Map::toJavaScript()();
    return this.newFetchPersonAppDataRequest(idSpec, keys, paramsObj);
  }-*/;

  /**
   * Creates an item to request a profile for the specified person ID. When
   * processed, returns a {@link DataRequestItem} object.
   *
   * @param idSpec an {@link Id} used to specify which people to fetch
   */
  public final native DataRequestItem newFetchPersonRequest(Id idSpec) /*-{
    return this.newFetchPersonRequest(idSpec.@com.google.gwt.opensocial.client.DataRequest.Id::toString()());
  }-*/;

  /**
   * Creates an item to request a profile for the specified person ID. When
   * processed, returns a {@link DataRequestItem} object.
   *
   * @see "http://code.google.com/apis/opensocial/docs/0.8/reference/#opensocial.DataRequest.newFetchPersonRequest"
   *
   * @param idSpec an {@link Id} used to specify which people to fetch
   * @param params additional parameters to pass to the request
   */
  public final native DataRequestItem newFetchPersonRequest(Id idSpec,
      Map<PeopleRequestFields, Object> params) /*-{
    var paramsObj = params.@com.google.gwt.opensocial.client.util.Map::toJavaScript()();
    return this.newFetchPersonRequest(idSpec, paramsObj);
  }-*/;


  /**
   * Deletes the given keys from the datastore for the given person. When
   * processed, does not return any data.
   *
   * @param idSpec The ID of the person to update; only the special VIEWER ID is
   *        currently allowed
   * @param key The key you want to delete from the datastore; this can be a
   *        single key name, or "*" to mean "all keys"
   */
  public final native DataRequestItem newRemovePersonAppDataRequest(Id idSpec, String key) /*-{
    return this.newRemovePersonAppDataRequest(idSpec.@com.google.gwt.opensocial.client.DataRequest.Id::toString()(), key);
  }-*/;

  /**
   * Deletes the given keys from the datastore for the given person. When
   * processed, does not return any data.
   *
   * @param idSpec the ID of the person to update; only the special VIEWER ID is
   *        currently allowed
   * @param keys the keys you want to delete from the datastore
   */
  public final native DataRequestItem newRemovePersonAppDataRequest(Id idSpec, Array<String> keys) /*-{
    return this.newRemovePersonAppDataRequest(idSpec, keys);
  }-*/;

  /**
   * Creates an item to request an update of an app field for the given person.
   * When processed, does not return any data.
   *
   * @param idSpec the ID of the person to update; only the special VIEWER ID is
   *        currently allowed
   * @param key the name of the key; this may only contain alphanumeric
   *        (A-Za-z0-9) characters, underscore(_), dot(.) or dash(-)
   * @param value the value, must be valid json
   */
  public final native DataRequestItem newUpdatePersonAppDataRequest(Id idSpec, String key,
      String value) /*-{
    return this.newUpdatePersonAppDataRequest(idSpec.@com.google.gwt.opensocial.client.DataRequest.Id::toString()(), key, value);
  }-*/;

  /**
   * Sends a data request to the server in order to get a data response.
   * Although the server may optimize these requests, they will always be
   * executed as though they were serial.
   *
   * @param callback the function to call with the data response generated by
   *        the server
   */
  public final native void send(OneArgumentFunction<DataResponse> callback)/*-{
    var nativeCallback=function(response) {
      callback.@com.google.gwt.opensocial.client.OneArgumentFunction<DataResponse>::run(Lcom/google/gwt/opensocial/client/DataResponse;)(response);
    }
    this.send(nativeCallback);
  }-*/;
}
