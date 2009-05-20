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
package com.google.gwt.gears.client.workerpool;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayBoolean;
import com.google.gwt.core.client.JsArrayInteger;
import com.google.gwt.core.client.JsArrayNumber;
import com.google.gwt.core.client.JsArrayString;

/**
 * Provides an interface to implement in order to receive message events.
 */
public interface WorkerPoolMessageHandler {
  /**
   * Encapsulates the arguments of the message event.
   */
  final class MessageEvent extends JavaScriptObject {
    protected MessageEvent() {
      // Required for overlay types
    }

    /**
     * Returns the body text of the message as a String. Boxed strings
     * (String()) are not supported by Gears.
     * 
     * @return the body of the message
     */
    public native String getBody() /*-{
      return String(this.body);
    }-*/;

    /**
     * Returns the body text of the message as a JsArray.
     * 
     * No type conversions are performed. If the message is not an Array type,
     * the method may throw a JavaScriptException in hosted mode.
     * 
     * @return the body of the message
     */
    public native JsArray<?> getBodyArray() /*-{
      return this.body;
    }-*/;

    /**
     * Returns the body text of the message as a JsArrayBoolean.
     * 
     * No type conversions are performed. If the message is not an Array type,
     * the method may throw a JavaScriptException in hosted mode.
     * 
     * @return the body of the message
     */
    public native JsArrayBoolean getBodyArrayBoolean() /*-{
      return this.body;
    }-*/;

    /**
     * Returns the body text of the message as a JsArrayInteger.
     * 
     * No type conversions are performed. If the message is not an Array type,
     * the method may throw a JavaScriptException in hosted mode.
     * 
     * @return the body of the message
     */
    public native JsArrayInteger getBodyArrayInteger() /*-{
      return this.body;
    }-*/;

    /**
     * Returns the body text of the message as a JsArrayNumber.
     * 
     * No type conversions are performed. If the message is not an Array type,
     * the method may throw a JavaScriptException in hosted mode.
     * 
     * @return the body of the message
     */
    public native JsArrayNumber getBodyArrayNumber() /*-{
      return this.body;
    }-*/;

    /**
     * Returns the body text of the message as a JsArrayString.
     * 
     * No type conversions are performed. If the message is not an Array type,
     * the method may throw a JavaScriptException in hosted mode.
     * 
     * @return the body of the message
     */
    public native JsArrayString getBodyArrayString() /*-{
      return this.body;
    }-*/;

    /**
     * Returns the body text of the message as a boolean.
     * 
     * @return the body of the message as a boolean.
     */
    public native boolean getBodyBoolean() /*-{
      return Boolean(this.body);
    }-*/;

    /**
     * Returns the body text of the message as a double. Boxed numbers
     * (Number()) are not supported by Gears.
     * 
     * @return the body of the message as a double.
     */
    public native double getBodyNumber() /*-{
      return Number(this.body);
    }-*/;

    /**
     * Returns the body text of the message as a JavaScriptObject
     * 
     * No type conversions are performed and Gears does not support Boxed types.
     * If the message is not really an Object, the code will fail with a
     * JavaScriptException in hosted mode.
     * 
     * @return the body of the message
     */
    public native JavaScriptObject getBodyObject() /*-{
      return this.body;
    }-*/;

    /**
     * Returns the sender's origin, represented as a string of the form:
     * SCHEME://DOMAIN[:PORT].
     * 
     * Note that the port is omitted for standard ports (http port 80, https
     * port 443).
     * 
     * @return the sender's origin, represented as a string of the form:
     *         SCHEME://DOMAIN[:PORT]
     */
    public native String getOrigin() /*-{
      return this.origin;
    }-*/;

    /**
     * Returns the id of the worker that sent this message.
     * 
     * @return the id of the worker that sent this message
     */
    public native int getSender() /*-{
      return this.sender;
    }-*/;

    /**
     * Returns <code>true</code> if the message body is a Boolean value.
     * 
     * @return <code>true</code> if the message body is a Boolean value.
     */
    public native boolean isMessageBoolean() /*-{
      return (typeof this.body) == "boolean";
    }-*/;

    /**
     * Returns <code>true</code> if the message body is a Number value.
     * 
     * @return <code>true</code> if the message body is a Number value.
     */
    public native boolean isMessageNumber() /*-{
      return (typeof this.body) == "number";
    }-*/;

    /**
     * Returns <code>true</code> if the message body is a Object value.
     * 
     * @return <code>true</code> if the message body is a Object value.
     */
    public native boolean isMessageObject() /*-{
      return (typeof this.body) == "object";
    }-*/;

    /**
     * Returns <code>true</code> if the message body is a String value.
     * 
     * @return <code>true</code> if the message body is a String value.
     */
    public native boolean isMessageString() /*-{
      return (typeof this.body) == "string";
    }-*/;
  }

  /**
   * Method to be invoked when a worker receives a message event.
   * 
   * @param event contains the properties of the event
   */
  void onMessageReceived(MessageEvent event);
}