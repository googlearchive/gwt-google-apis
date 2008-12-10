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
 * Base interface for all enum objects. This class allows containers to use
 * constants for fields that are usually have a common set of values.
 *
 * @see "http://code.google.com/apis/opensocial/docs/0.8/reference/#opensocial.Enum"
 */
public final class SocialEnum extends JavaScriptObject {
  /**
   * The enum keys used by the drinker field.
   *
   * @see Person.Field#DRINKER
   */
  public enum Drinker {
    NO, YES, SOCIALLY, OCCASIONALLY, REGULARLY, HEAVILY, QUITTING, QUIT;
  }

  /**
   * The enum keys used by the gender field.
   *
   * @see Person.Field#GENDER
   */
  public enum Gender {
    MALE, FEMALE;
  }

  /**
   * The enum keys used by the lookingFor field.
   *
   * @see Person.Field#LOOKING_FOR
   */
  public enum LookingFor {
    ACTIVITY_PARTNERS, DATING, FRIENDS, NETWORKING, RANDOM, RELATIONSHIP;
  }

  /**
   * The enum keys used by the networkPresence field.
   *
   * @see Person.Field#NETWORK_PRESENCE
   */
  public enum Presence {
    /**
     * The entity or resource is temporarily away.
     */
    AWAY,
    /**
     * The entity or resource is actively interested in chatting.
     */
    CHAT,
    /**
     * The entity or resource is busy (dnd = "Do Not Disturb").
     *
     * @see "http://code.google.com/apis/opensocial/docs/0.8/reference/#opensocial.Enum.Presence.DND"
     */
    DND,
    /**
     * The entity or resource is off line.
     */
    OFFLINE,
    /**
     * The entity or resource is on line.
     */
    ONLINE,
    /**
     * The entity or resource is away for an extended period (xa =
     * "eXtended Away").
     */
    XA;
  }

  /**
   * The enum keys used by the smoker field.
   *
   * @see Person.Field#SMOKER
   */
  public enum Smoker {
    NO, YES, SOCIALLY, OCCASIONALLY, REGULARLY, HEAVILY, QUITTING, QUIT;
  }

  protected SocialEnum() {
  }

  /**
   * The value of this enum. This will be a user displayable string. If the
   * container supports localization, the string will be localized.
   *
   * @return The enum's value.
   */
  public native String getDisplayValue() /*-{
     return this.getDisplayValue();
   }-*/;

  /**
   * Use this for logic within your gadget. If they key is null then the value
   * does not fit in the defined enums.
   *
   * @return The enum's key. This should be one of the defined enums below.
   */
  public native String getKey() /*-{
     this.getKey();
   }-*/;
}
