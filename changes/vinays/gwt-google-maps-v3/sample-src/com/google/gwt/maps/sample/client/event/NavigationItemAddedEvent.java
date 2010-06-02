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
package com.google.gwt.maps.sample.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.maps.sample.client.bean.NavigationItem;

/**
 * Navigation item added to the db.
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public class NavigationItemAddedEvent extends GwtEvent<NavigationItemAddedHandler> {

  public static final Type<NavigationItemAddedHandler> TYPE
      = new Type<NavigationItemAddedHandler>();
  
  final private NavigationItem navItem;
  
  /**
   * @param navItem
   */
  public NavigationItemAddedEvent(NavigationItem navItem) {
    super();
    this.navItem = navItem;
  }

  @Override
  protected void dispatch(NavigationItemAddedHandler handler) {
    handler.onNavigationItemAdded(this);
  }

  @Override
  public com.google.gwt.event.shared.GwtEvent.Type<NavigationItemAddedHandler> getAssociatedType() {
    return TYPE;
  }
  
  public NavigationItem getNavigationItem() {
    return navItem;
  }

}
