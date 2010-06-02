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
 * 
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public class ShowNavigationItemEvent extends GwtEvent<ShowNavigationItemHandler> {

  public static final Type<ShowNavigationItemHandler> TYPE
      = new Type<ShowNavigationItemHandler>();
  
  final private NavigationItem navItem;
  
  /**
   * @param navItem
   */
  public ShowNavigationItemEvent(final NavigationItem navItem) {
    super();
    this.navItem = navItem;
  }

  @Override
  protected void dispatch(ShowNavigationItemHandler handler) {
    handler.onShowNavigationItem(this);
  }

  @Override
  public Type<ShowNavigationItemHandler> getAssociatedType() {
    return TYPE;
  }
  
  public NavigationItem getNavigationItem() {
    return navItem;
  }

}
