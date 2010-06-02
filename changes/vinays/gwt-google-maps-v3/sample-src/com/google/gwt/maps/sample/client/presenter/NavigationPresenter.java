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
package com.google.gwt.maps.sample.client.presenter;

import java.util.ArrayList;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.maps.sample.client.bean.NavigationItem;
import com.google.gwt.maps.sample.client.event.NavigationItemAddedEvent;
import com.google.gwt.maps.sample.client.event.NavigationItemAddedHandler;
import com.google.gwt.maps.sample.client.event.NavigationItemDeletedEvent;
import com.google.gwt.maps.sample.client.event.NavigationItemDeletedHandler;
import com.google.gwt.maps.sample.client.view.View;

/**
 * Left navigation bar presenter.
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public class NavigationPresenter implements Presenter<NavigationPresenter.Display> {

  public static interface Display extends View{
    
    void addNavigationItem(NavigationItemPresenter.Display navItem, int index);
    
    void removeNavigationItem(int index);
    
    void clearNavigationItems();
    
    NavigationItemPresenter.Display getNavItemDisplay();
    
  }
  
  public static class Builder {
    
    final private HandlerManager eventBus;
    final private NavigationPresenter.Display display;
    private ArrayList<NavigationItem> navItems = null;

    public Builder(HandlerManager eventBus, Display display) {
      super();
      this.eventBus = eventBus;
      this.display = display;
    }
    
    public Builder setNavigationItems(final ArrayList<NavigationItem> items) {
      navItems = items;
      return this;
    }
    
    public NavigationPresenter build() {
      final NavigationPresenter presenter = new NavigationPresenter(eventBus, display);
      if (navItems != null) {
        presenter.setNavigationItems(navItems);
      }
      presenter.bind();
      return presenter;
    }
    
  }
  
  final private HandlerManager eventBus;
  final private NavigationPresenter.Display display;
  final private ArrayList<HandlerRegistration> handlers;
  final private ArrayList<NavigationItemPresenter> navItemPresenters;

  /**
   * @param eventBus
   * @param display
   */
  public NavigationPresenter(HandlerManager eventBus, Display display) {
    super();
    this.eventBus = eventBus;
    this.display = display;
    handlers = new ArrayList<HandlerRegistration>();
    navItemPresenters = new ArrayList<NavigationItemPresenter>();
  }

  @Override
  public void bind() {
    handlers.add(eventBus.addHandler(NavigationItemAddedEvent.TYPE, new NavigationItemAddedHandler() {
      
      @Override
      public void onNavigationItemAdded(NavigationItemAddedEvent event) {
        addNavigationItem(event.getNavigationItem());
      }
    }));
    handlers.add(eventBus.addHandler(NavigationItemDeletedEvent.TYPE, new NavigationItemDeletedHandler() {
      
      @Override
      public void onNavigationItemDeleted(NavigationItemDeletedEvent event) {
        removeNavigationItem(event.getNavigationItem());
      }
    }));
  }

  @Override
  public Display getDisplay() {
    return display;
  }

  @Override
  public HandlerManager getEventBus() {
    return eventBus;
  }

  @Override
  public void release() {
    for(HandlerRegistration handler : handlers) {
      handler.removeHandler();
    }
  }
  
  public void setNavigationItems(ArrayList<NavigationItem> items) {
    display.clearNavigationItems();
    for (final NavigationItem item : items) {
      addNavigationItem(item);
    }
  }
  
  protected void addNavigationItem(final NavigationItem item) {
    final NavigationItemPresenter.Builder navItemBuilder = new NavigationItemPresenter.Builder(
        display.getNavItemDisplay(), eventBus);
    navItemBuilder.setNavigationItem(item);
    final NavigationItemPresenter presenter = navItemBuilder.build();
    display.addNavigationItem(presenter.getDisplay(), navItemPresenters.size());
    navItemPresenters.add(presenter);
  }
  
  protected void removeNavigationItem(final NavigationItem item) {
    final int navItemIndex = navItemPresenters.indexOf(item);
    if (navItemIndex >= 0) {
      display.removeNavigationItem(navItemIndex);
      navItemPresenters.remove(navItemIndex);
    }
  }
}
