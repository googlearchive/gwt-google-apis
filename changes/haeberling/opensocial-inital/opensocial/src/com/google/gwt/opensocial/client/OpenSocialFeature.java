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

import com.google.gwt.gadgets.client.GadgetFeature;
import com.google.gwt.opensocial.client.DataRequest.Id;
import com.google.gwt.opensocial.client.util.ActivityParams;
import com.google.gwt.opensocial.client.util.OneArgumentFunction;

import java.util.List;
import java.util.Map;

/**
 * Provides access to the OpenSocial feature.
 *
 * @see "http://code.google.com/apis/opensocial/docs/0.8/reference/#opensocial"
 */
public class OpenSocialFeature implements GadgetFeature {
  private OpenSocialFeature() {
  }

  /**
   * Gets the current environment for this gadget.
   */
  public native Environment getEnvironment()/*-{
    return $wnd.opensocial.getEnvironment();
  }-*/;

  /**
   * Returns true if the current gadget has access to the specified permission.
   */
  // TODO(igoogle-zrh) - Incomplete - Need to test this first
  public native boolean hasPermission(Permission permission)/*-{
    return $wnd.opensocial.hasPermission(permission);
  }-*/;

  /**
   * Creates an activity object, which represents an activity on the server.
   *
   * It is only required to set one of {@link Activity.Field#TITLE_ID} or
   * {@link Activity.Field#TITLE}. In addition, if you are using any variables
   * in your title or title template, you must set
   * {@link Activity.Field#TEMPLATE_PARAMS}.
   *
   * @see Activity.Field
   * @see #requestCreateActivity(Activity, CreateActivityPriority,
   *      OneArgumentFunction)
   * @see "http://code.google.com/apis/opensocial/docs/0.8/reference/#opensocial.newActivity"
   *
   * @param params parameters defining the activity
   */
  public native Activity newActivity(ActivityParams params) /*-{
    var paramsObj = null;
    if (params != null) {
      paramsObj = params.@com.google.gwt.opensocial.client.ActivityParams::toJavaScript();
    }
    return $wnd.opensocial.newActivity(paramsObj);
  }-*/;

  /**
   * Creates a data request object to use for sending and fetching data from the
   * server.
   */
  public native DataRequest newDataRequest() /*-{
    return $wnd.opensocial.newDataRequest();
  }-*/;

  /**
   * Creates an IdSpec object.
   */
  public native IdSpec newIdSpec(Map<IdSpec.Field, Object> params) /*-{
    var paramsObj = params.@com.google.gwt.opensocial.client.util.Map::toJavaScript()();
    return $wnd.opensocial.newIdSpec(paramsObj);
  }-*/;

  /**
   * Creates a media item. Represents images, movies, and audio. Used when
   * creating activities on the server.
   *
   * @param mimeType MIME type of the media String
   * @param url where the media can be found
   * @param params Any other fields that should be set on the media item object;
   *        all of the defined Fields are supported
   */
  public native MediaItem newMediaItem(String mimeType, String url,
      Map<MediaItem.Field, Object> params) /*-{
    var paramsObj = @com.google.gwt.opensocial.client.JavaScriptObjectHelper::toJavaScript(Ljava/util/Map;)(params);
    return $wnd.opensocial.newActivityMediaItem(mimeType,url,paramsObj);
  }-*/;

  /**
   * Creates a media item associated with an activity. Represents images,
   * movies, and audio. Used when creating activities on the server.
   *
   * @param body the main text of the message
   * @param params any other fields that should be set on the message object;
   *        all of the defined Fields are supported
   */
  public native Message newMessage(String body, Map<Message.Field, Object> params) /*-{
    var paramsObj = @com.google.gwt.opensocial.client.JavaScriptObjectHelper::toJavaScript(Ljava/util/Map;)(params);
    return $wnd.opensocial.newMessage(body, paramsObj);
  }-*/;

  /**
   * Creates a NavigationParameters object.
   *
   * @see #requestShareApp(Id, String, OneArgumentFunction)
   */
  public native NavigationParameters newNavigationParameters(
      Map<NavigationParameters.Field, Object> params) /*-{
    var paramsObj = @com.google.gwt.opensocial.client.JavaScriptObjectHelper::toJavaScript(Ljava/util/Map;)(params);
    return $wnd.opensocial.newNavigationParameters(paramsObj);
  }-*/;

  /**
   * Takes an activity and tries to create it, without waiting for the operation
   * to complete. Optionally calls a function when the operation completes.
   *
   * @see #newActivity(ActivityParams)
   * @param activity the activity to create
   * @param priority the priority for this request
   * @param callback the function to call once the request has been processed.
   */
  public native void requestCreateActivity(Activity activity, CreateActivityPriority priority,
      OneArgumentFunction<DataResponse> callback) /*-{
    var priorityString = null
    if (priority != null) {
      priorityString = priority.@com.google.gwt.opensocial.client.CreateActivityPriority::toString()();
    }
    if (callback != null) {
      var nativeCallback = function(response) {
        callback.@com.google.gwt.opensocial.client.OneArgumentFunction<DataResponse>::run(Lcom/google/gwt/opensocial/client/DataResponse;)(response);
      }
      $wnd.opensocial.requestCreateActivity(activity,priority,nativeCallback);
    } else {
      $wnd.opensocial.requestCreateActivity(activity,priority,null);
    }
  }-*/;

  /**
   * Requests the user to grant access to the specified permissions.
   *
   * @param permissions the permissions to request from the viewer
   * @param reason displayed to the user as the reason why these permissions are
   *        needed
   * @param callback the function to call once the request has been processed
   */
  public native void requestPermission(List<Permission> permissions, String reason,
      OneArgumentFunction<DataResponse> callback) /*-{
    var permissionsObj = @com.google.gwt.opensocial.client.JavaScriptObjectHelper::toJavaScript(Ljava/util/List;)(permissions);
    var nativeCallback = function(response) {
      callback.@com.google.gwt.opensocial.client.OneArgumentFunction<DataResponse>::run(Lcom/google/gwt/opensocial/client/DataResponse;)(response);
    }
    $wnd.opensocial.requestPermission(permissionsObj, reason, nativeCallback);
  }-*/;

  /**
   * Requests the container to send a specific message to the specified users.
   *
   * @param id an ID or a group reference
   * @param message The message to send to the specified users
   */
  public native void requestSendMessage(Id id, String message) /*-{
    this.requestSendMessage(id.@com.google.gwt.opensocial.client.DataRequest.Id::toString()(), message);
  }-*/;

  /**
   * Requests the container to send a specific message to the specified users.
   *
   * @param id an ID or a group reference
   * @param message The message to send to the specified users
   * @param callback The function to call once the request has been processed
   */
  public native void requestSendMessage(Id id, String message,
      OneArgumentFunction<DataResponse> callback) /*-{
    var nativeCallback = function(response) {
      callback.@com.google.gwt.opensocial.client.OneArgumentFunction<DataResponse>::run(Lcom/google/gwt/opensocial/client/DataResponse;)(response);
    }
    this.requestSendMessage(id.@com.google.gwt.opensocial.client.DataRequest.Id::toString()(), message, nativeCallback);
  }-*/;

  /**
   * Requests the container to send a specific message to the specified users.
   *
   * @param id an ID or a group reference
   * @param message The message to send to the specified users
   * @param callback The function to call once the request has been processed
   * @param params - The optional parameters indicating where to send a user
   *        when a request is made, or when a request is accepted
   */
  public native void requestSendMessage(Id id, String message,
      OneArgumentFunction<DataResponse> callback, NavigationParameters params) /*-{
    var nativeCallback = function(response) {
      callback.@com.google.gwt.opensocial.client.OneArgumentFunction<DataResponse>::run(Lcom/google/gwt/opensocial/client/DataResponse;)(response);
    }
    this.requestSendMessage(id.@com.google.gwt.opensocial.client.DataRequest.Id::toString()(), message, nativeCallback, params);
  }-*/;

  /**
   * Requests the container to send a specific message to the specified users.
   *
   * @see "http://code.google.com/apis/opensocial/docs/0.8/reference/#opensocial.requestSendMessage"
   *
   * @param ids a list of IDs
   * @param message The message to send to the specified users
   */
  public native void requestSendMessage(List<Id> ids, String message) /*-{
    var idsObj = @com.google.gwt.opensocial.client.JavaScriptObjectHelper::toJavaScript(Ljava/util/List;)(ids);
    $wnd.requestSendMessage(idsObj, message);
  }-*/;

  /**
   * Requests the container to send a specific message to the specified users.
   *
   * @param ids a list of IDs
   * @param message The message to send to the specified users
   * @param callback The function to call once the request has been processed;
   *        either this callback will be called or the gadget will be reloaded
   *        from scratch
   */
  public native void requestSendMessage(List<Id> ids, String message,
      OneArgumentFunction<DataResponse> callback) /*-{
    var idsObj = @com.google.gwt.opensocial.client.JavaScriptObjectHelper::toJavaScript(Ljava/util/List;)(ids);
    var nativeCallback=function(response){
      callback.@com.google.gwt.opensocial.client.OneArgumentFunction<DataResponse>::run(Lcom/google/gwt/opensocial/client/DataResponse;)(response);
    }
    $wnd.requestSendMessage(idsObj, message, nativeCallback);
  }-*/;

  /**
   * Requests the container to send a specific message to the specified users.
   *
   * @param ids a list of IDs
   * @param message the message to send to the specified users
   * @param callback the function to call once the request has been processed;
   *        either this callback will be called or the gadget will be reloaded
   *        from scratch
   * @param params the optional parameters indicating where to send a user when
   *        a request is made, or when a request is accepted
   */
  public native void requestSendMessage(List<Id> ids, String message,
      OneArgumentFunction<DataResponse> callback, NavigationParameters params) /*-{
    var idsObj = @com.google.gwt.opensocial.client.JavaScriptObjectHelper::toJavaScript(Ljava/util/List;)(ids);
    var nativeCallback=function(response){
      callback.@com.google.gwt.opensocial.client.OneArgumentFunction<DataResponse>::run(Lcom/google/gwt/opensocial/client/DataResponse;)(response);
    }
    $wnd.requestSendMessage(idsObj, message, nativeCallback, params);
  }-*/;

  /**
   * Requests the container to share this gadget with the specified users.
   *
   * @param id an ID or a group reference
   * @param reason the reason the user wants the gadget to share itself
   */
  public native void requestShareApp(Id id, String reason) /*-{
    this.requestShareApp(id.@com.google.gwt.opensocial.client.DataRequest.Id::toString()(), reason);
  }-*/;

  /**
   * Requests the container to share this gadget with the specified users.
   *
   * @param id an ID or a group reference
   * @param reason the reason the user wants the gadget to share itself
   * @param callback the function to call once the request has been processed;
   *        either this callback will be called or the gadget will be reloaded
   *        from scratch
   */
  public native void requestShareApp(Id id, String reason,
      OneArgumentFunction<DataResponse> callback) /*-{
    var nativeCallback=function(response){
      callback.@com.google.gwt.opensocial.client.OneArgumentFunction<DataResponse>::run(Lcom/google/gwt/opensocial/client/DataResponse;)(response);
    }
    this.requestShareApp(id.@com.google.gwt.opensocial.client.DataRequest.Id::toString()(), reason, nativeCallback);
  }-*/;

  /**
   * Requests the container to share this gadget with the specified users.
   *
   * @param id an ID or a group reference
   * @param reason the reason the user wants the gadget to share itself
   * @param callback the function to call once the request has been processed;
   *        either this callback will be called or the gadget will be reloaded
   *        from scratch
   * @param params the optional parameters indicating where to send a user when
   *        a request is made, or when a request is accepted
   */
  public native void requestShareApp(Id id, String reason,
      OneArgumentFunction<DataResponse> callback, NavigationParameters params) /*-{
    var nativeCallback=function(response){
      callback.@com.google.gwt.opensocial.client.OneArgumentFunction<DataResponse>::run(Lcom/google/gwt/opensocial/client/DataResponse;)(response);
    }
    this.requestShareApp(id.@com.google.gwt.opensocial.client.DataRequest.Id::toString()(), reason, nativeCallback, params);
  }-*/;

  /**
   * Requests the container to share this gadget with the specified users.
   *
   * @param ids a list of IDs
   * @param reason the reason the user wants the gadget to share itself
   */
  public native void requestShareApp(List<String> ids, String reason) /*-{
    var idsObj = @com.google.gwt.opensocial.client.JavaScriptObjectHelper::toJavaScript(Ljava/util/List;)(ids);
    $wnd.requestShareApp(idsObj, reason);
  }-*/;

  /**
   * Requests the container to share this gadget with the specified users.
   *
   * @param ids a list of IDs
   * @param reason the reason the user wants the gadget to share itself
   * @param callback the function to call once the request has been processed;
   *        either this callback will be called or the gadget will be reloaded
   *        from scratch
   */
  public native void requestShareApp(List<String> ids, String reason,
      OneArgumentFunction<DataResponse> callback) /*-{
    var idsObj = @com.google.gwt.opensocial.client.JavaScriptObjectHelper::toJavaScript(Ljava/util/List;)(ids);
    var nativeCallback = function(response) {
      callback.@com.google.gwt.opensocial.client.util.OneArgumentFunction<DataResponse>::run(Lcom/google/gwt/opensocial/client/DataResponse;)(response);
    }
    $wnd.requestShareApp(idsObj, reason, nativeCallback);
  }-*/;

  /**
   * Requests the container to share this gadget with the specified users.
   *
   * @param ids a list of IDs
   * @param reason the reason the user wants the gadget to share itself
   * @param callback the function to call once the request has been processed;
   *        either this callback will be called or the gadget will be reloaded
   *        from scratch
   * @param params the optional parameters indicating where to send a user when
   *        a request is made, or when a request is accepted
   */
  public native void requestShareApp(List<String> ids, String reason,
      OneArgumentFunction<DataResponse> callback, NavigationParameters params) /*-{
    var idsObj = @com.google.gwt.opensocial.client.JavaScriptObjectHelper::toJavaScript(Ljava/util/List;)(ids);
    var nativeCallback = function(response) {
      callback.@com.google.gwt.opensocial.client.util.OneArgumentFunction<DataResponse>::run(Lcom/google/gwt/opensocial/client/DataResponse;)(response);
    }
    $wnd.requestShareApp(idsObj, reason, nativeCallback, params);
  }-*/;

}
