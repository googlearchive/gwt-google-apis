/* Copyright (c) 2010 Vinay Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.gwt.maps.client;

/**
 * Identifiers for common MapTypes.
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public interface HasMapTypeId {

  /**
   * This map type displays a transparent layer of major streets on satellite images.
   */
  public String getHybrid();
  
  /**
   * This map type displays a normal street map.
   */
  public String getRoadmap();
  
  /**
   * This map type displays satellite images.
   */
  public String getSatellite();
  
  /**
   * This map type displays maps with physical features such as terrain and vegetation.
   */
  public String getTerrain();
  
}
