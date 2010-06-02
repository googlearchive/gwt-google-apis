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
package com.google.gwt.maps.sample.client;

import java.util.ArrayList;

import com.google.gwt.maps.sample.client.bean.NavigationItem;

/**
 * Provider for NavigationItem beans.
 * 
 * List here all the (name, html) tuples of navigation list items.
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public class NavigationItemsProvider {

  final private static String[][] NAV_ITEM_NAMES = new String[][] {
    {"Simple", "Simple"},
    {"Event-Simple", "Event-Simple"},
    {"Event-Closure", "Event-Closure"},
    {"Polygon-Simple", "Polygon-Simple"},
    {"Geocoder-Simple", "Geocoder-Simple"},
    {"Directions-Panel", "Directions-Panel"}
  };
  
  public ArrayList<NavigationItem> get() {
    final ArrayList<NavigationItem> navItems = new ArrayList<NavigationItem>();
    for (int i = 0; i < NAV_ITEM_NAMES.length; ++i) {
      navItems.add(new NavigationItem(NAV_ITEM_NAMES[i][0], NAV_ITEM_NAMES[i][1]));
    }
    return navItems;
  }
}
