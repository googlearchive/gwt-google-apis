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
 * Base interface for all person objects.
 * 
 * @see "http://code.google.com/apis/opensocial/docs/0.8/reference/#opensocial.Person"
 */
public class Person extends JavaScriptObject {
  /**
   * All of the fields that a person has.
   */
  public enum Field {
    /**
     * A general statement about the person, specified as a String. Not
     * supported by all containers.
     */
    ABOUT_ME("aboutMe"),

    /**
     * Person's favorite activities, specified as an Array of Strings. Not
     * supported by all containers.
     */
    ACTIVITIES("activities"),

    /**
     * Addresses associated with the person, specified as an Array of <a
     * href="opensocial.Address.html">Address</a>es. Not supported by all
     * containers.
     */
    ADDRESSES("addresses"),

    /**
     * Person's age, specified as a number. Not supported by all containers.
     *
     */
    AGE("age"),

    /**
     * Person's body characteristics, specified as an opensocial.BodyType. Not
     * supported by all containers.
     */
    BODY_TYPE("bodyType"),

    /**
     * Person's favorite books, specified as an Array of Strings. Not supported
     * by all containers.
     */
    BOOKS("books"),

    /**
     * Person's favorite cars, specified as an Array of Strings. Not supported
     * by all containers.
     */
    CARS("cars"),

    /**
     * Description of the person's children, specified as a String. Not
     * supported by all containers.
     */
    CHILDREN("children"),

    /**
     * Person's current location, specified as an <a
     * href="opensocial.Address.html">Address</a>. Not supported by all
     * containers.
     */
    CURRENT_LOCATION("currentLocation"),

    /**
     * Person's date of birth, specified as a Date object. Not supported by all
     * containers.
     */
    DATE_OF_BIRTH("dateOfBirth"),

    /**
     * Person's drinking status, specified as an opensocial.Enum with the enum's
     * key referencing opensocial.Enum.Drinker. Not supported by all containers.
     */
    DRINKER("drinker"),

    /**
     * Emails associated with the person, specified as an Array of <a
     * href="opensocial.Email.html">Email</a>s. Not supported by all containers.
     */
    EMAILS("emails"),

    /**
     * Person's ethnicity, specified as a String. Not supported by all
     * containers.
     *
     */
    ETHNICITY("ethnicity"),

    /**
     * Person's thoughts on fashion, specified as a String. Not supported by all
     * containers.
     */
    FASHION("fashion"),

    /**
     * Person's favorite food, specified as an Array of Strings. Not supported
     * by all containers.
     */
    FOOD("food"),

    /**
     * Person's gender, specified as an opensocial.Enum with the enum's key
     * referencing opensocial.Enum.Gender. Not supported by all containers.
     */
    GENDER("gender"),

    /**
     * Describes when the person is happiest, specified as a String. Not
     * supported by all containers.
     */
    HAPPIEST_WHEN("happiestWhen"),

    /**
     * A boolean indicating whether the person has used the current app.
     */
    HAS_APP("hasApp"),

    /**
     * Person's favorite heroes, specified as an Array of Strings. Not supported
     * by all containers.
     */
    HEROES("heroes"),

    /**
     * Person's thoughts on humor, specified as a String. Not supported by all
     * containers.
     */
    HUMOR("humor"),

    /**
     * A string ID that can be permanently associated with this person.
     */
    ID("id"),

    /**
     * Person's interests, hobbies or passions, specified as an Array of
     * Strings. Not supported by all containers.
     */
    INTERESTS("interest"),

    /**
     * Person's favorite jobs, or job interests and skills, specified as a
     * String. Not supported by all containers.
     */
    JOB_INTERESTS("jobInterests"),

    /**
     * Jobs the person has held, specified as an Array of <a
     * href="opensocial.Organization.html">Organization</a>s. Not supported by
     * all containers.
     *
     *
     */
    JOBS("jobs"),

    /**
     * List of the languages that the person speaks as ISO 639-1 codes,
     * specified as an Array of Strings. Not supported by all containers.
     */
    LANGUAGES_SPOKEN("languagesSpoken"),

    /**
     * Description of the person's living arrangement, specified as a String.
     * Not supported by all containers.
     */
    LIVING_ARRANGEMENT("livingArrangement"),

    /**
     * Person's statement about who or what they are looking for, or what they
     * are interested in meeting people for. Specified as a String. Not
     * supported by all containers.
     */
    LOOKING_FOR("lookingFor"),

    /**
     * Person's favorite movies, specified as an Array of Strings. Not supported
     * by all containers.
     */
    MOVIES("movies"),

    /**
     * Person's favorite music, specified as an Array of
     * Strings. Not supported by all containers.
     */
    MUSIC("music"),

    /**
     * A opensocial.Name object containing the person's name.
     */
    NAME("name"),

    /**
     * Person's current network status.
     */
    NETWORK_PRESENCE("networkPresence"),

    /**
     * A String representing the person's nickname.
     */
    NICKNAME("nickname"),

    /**
     * Description of the person's pets, specified as a String. Not supported by
     * all containers.
     */
    PETS("pets"),

    /**
     * Phone numbers associated with the person, specified as an Array of <a
     * href="opensocial.Phone.html">Phone</a>s. Not supported by all containers.
     */
    PHONE_NUMBERS("phoneNumbers"),

    /**
     * Person's political views, specified as a String. Not supported by all
     * containers.
     */
    POLITICAL_VIEWS("politicalViews"),

    /**
     * Person's profile song, specified as an opensocial.Url. Not supported by
     * all containers.
     */
    PROFILE_SONG("profileSong"),

    /**
     * Person's profile URL, specified as a String. This URL must be fully
     * qualified. Relative URLs will not work in gadgets. Not supported by all
     * containers.
     */
    PROFILE_URL("profileUrl"),

    /**
     * Person's profile video, specified as an opensocial.Url. Not supported by
     * all containers.
     */
    PROFILE_VIDEO("profileVideo"),

    /**
     * Person's favorite quotes, specified as an Array of Strings. Not supported
     * by all containers.
     */
    QUOTES("quotes"),

    /**
     * Person's relationship status, specified as a String.
     * Not supported by all containers.
     */
    RELATIONSHIP_STATUS("relationshipStatus"),

    /**
     * Person's relgion or religious views, specified as a String. Not supported
     * by all containers.
     */
    RELIGION("religion"),

    /**
     * Person's comments about romance, specified as a String. Not supported by
     * all containers.
     */
    ROMANCE("romance"),

    /**
     * What the person is scared of, specified as a String.
     * Not supported by all containers.
     */
    SCARED_OF("scaredOf"),

    /**
     * Schools the person has attended, specified as an
     * Array of <a href="opensocial.Organization.html">Organization</a>s. Not
     * supported by all containers.
     */
    SCHOOLS("schools"),

    /**
     * Person's sexual orientation, specified as a String.
     * Not supported by all containers.
     */
    SEXUAL_ORIENTATION("sexualOrientation"),

    /**
     * Person's smoking status, specified as an opensocial.Enum with the enum's
     * key referencing opensocial.Enum.Smoker. Not supported by all containers.
     */
    SMOKER("smoker"),

    /**
     * Person's favorite sports, specified as an Array of Strings. Not supported
     * by all containers.
     */
    SPORTS("sports"),

    /**
     * Person's status, headline or shoutout, specified as a String. Not
     * supported by all containers.
     */
    STATUS("status"),

    /**
     * Arbitrary tags about the person, specified as an Array of Strings. Not
     * supported by all containers.
     */
    TAGS("tags"),

    /**
     * Person's photo thumbnail URL, specified as a String. This URL must be
     * fully qualified. Relative URLs will not work in gadgets.
     */
    THUMBNAIL_URL("thumbnailUrl"),

    /**
     * Person's time zone, specified as the difference in minutes
     * between Greenwich Mean Time (GMT) and the user's local time. See
     * Date.getTimezoneOffset() in javascript for more details on this format.
     * Not supported by all containers.
     */
    TIME_ZONE("timeZone"),

    /**
     * Person's turn offs, specified as an Array of Strings. Not supported
     * by all containers.
     */
    TURN_OFFS("turnOffs"),

    /**
     * Person's turn ons, specified as an Array of Strings. Not
     * supported by all containers.
     */
    TURN_ONS("turnOns"),

    /**
     * Person's favorite TV shows, specified as an Array of Strings. Not
     * supported by all containers.
     */
    TV_SHOWS("tvShows"),

    /**
     * URLs related to the person, their webpages, or feeds. Specified
     * as an Array of opensocial.Url. Not supported by all containers.
     *
     *
     */
    URLS("urls");

    private String value = null;

    Field(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return value;
    }
  }

  protected Person() {
  }

  public final native String getDisplayName() /*-{
    return this.getDisplayName();
  }-*/;
  
  public final JavaScriptObject getField(Field key) {
    return nativeGetField(key.toString());
  }

  public final native String getId() /*-{
    return this.getId();
  }-*/;

  public final native boolean isOwner() /*-{
    return this.isOwner();
  }-*/;

  public final native boolean isViewer() /*-{
    return this.isViewer();
  }-*/;

  private final native JavaScriptObject nativeGetField(String key) /*-{
    var value = this.getField(key);
    return (value == undefined) ? null : value;
  }-*/;
}
