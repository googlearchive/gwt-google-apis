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
package com.google.gwt.gadgets.sample.traveler.server;

import com.google.appengine.api.datastore.Text;

import java.util.Date;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * Location class used by server-side part Represent a single geographical
 * location with it's description.
 */

@PersistenceCapable
public class Location {
  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  @Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
  private String key;

  @Persistent
  private String ownerId;

  @Persistent
  private String container;

  @Persistent
  private String title;

  @Persistent
  private Text description;

  @Persistent
  private Date date;

  @Persistent
  private double latitude;

  @Persistent
  private double longitude;

  public Location(String ownerId, String container, String title,
      String description, Date date, double latitude, double longitude) {
    this.ownerId = ownerId;
    this.container = container;
    this.title = title;
    this.description = new Text(description);
    this.date = date;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public String getContainer() {
    return container;
  }

  public Date getDate() {
    return date;
  }

  public String getDescription() {
    return description.getValue();
  }

  public String getKey() {
    return key;
  }

  public double getLatitude() {
    return latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public String getOwnerId() {
    return ownerId;
  }

  public String getTitle() {
    return title;
  }

  public void setContainer(String container) {
    this.container = container;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public void setDescription(String description) {
    this.description = new Text(description);
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public void setOwnerId(String ownerId) {
    this.ownerId = ownerId;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
