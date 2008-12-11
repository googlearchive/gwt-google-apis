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

/**
 * Base interface for all message objects.
 *
 * @see OpenSocialFeature#newMessage(String, java.util.Map)
 * @see OpenSocialFeature#requestSendMessage(com.google.gwt.opensocial.client.DataRequest.Id,
 *      String, com.google.gwt.opensocial.client.util.OneArgumentFunction)
 * @see "http://code.google.com/apis/opensocial/docs/0.8/reference/#opensocial.Message"
 */
public final class Message extends JavaScriptObject {
  /**
   * All of the fields that messages can have.
   *
   * @see Message#getField
   * @see Message#setField
   */
  public enum Field {
    /**
     * The main text of the message. HTML attributes are allowed and are
     * sanitized by the container.
     */
    BODY("body"),

    /**
     * The main text of the message as a message template. Specifies the message
     * ID to use in the gadget xml.
     */
    BODY_ID("bodyId"),

    /**
     * The title of the message. HTML attributes are allowed and are sanitized
     * by the container.
     */
    TITLE("title"),

    /**
     * The title of the message as a message template. Specifies the message ID
     * to use in the gadget xml.
     */
    TITLE_ID("titleId"),

    /**
     * The title of the message, specified as an opensocial.Message.Type.
     */
    TYPE("type");

    private String value = null;

    private Field(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return this.value;
    }
  }


  /**
   * The types of messages that can be sent.
   */
  public enum Type {
    /**
     * An email.
     */
    EMAIL("email"),

    /**
     * A short private message.
     */
    NOTIFICATION("notification"),

    /**
     * A message to a specific user that can be seen only by that user.
     */
    PRIVATE_MESSAGE("privateMessage"),

    /**
     * A message to a specific user that can be seen by more than that user.
     */
    PUBLIC_MESSAGE("publicMessage");

    private String value = null;

    private Type(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return this.value;
    }
  }

  protected Message() {
  }

  /**
   * Gets the message data that's associated with the specified key.
   */
  public String getField(Field key) {
    return nativeGetField(key.toString());
  }
  
  /**
   * Sets data for this message associated with the given key.
   */
  public void setField(Field key, String data) {
    nativeSetField(key.toString(), data);
  }
  
  private native String nativeGetField(String value) /*-{
    return this.getField(value);
  }-*/;
  
  private native void nativeSetField(String key, String data) /*-{
  	this.setField(key, data);
  }-*/;
}
