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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.maps.sample.client.bean.NavigationItem;
import com.google.gwt.maps.sample.client.event.ShowNavigationItemEvent;
import com.google.gwt.maps.sample.client.event.ShowNavigationItemHandler;
import com.google.gwt.maps.sample.client.view.View;

/**
 * Navigation bar item presenter.
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public class NavigationItemPresenter implements Presenter<NavigationItemPresenter.Display> {

  public static interface Display extends View {
    
    void setHtml(String html);
    
    HasClickHandlers getLink();
    
    void setSelected(boolean selected);
  }
  
  /**
   * Build presenter in right order of method call.
   */
  public static class Builder {
    
    final private NavigationItemPresenter.Display display;
    final private HandlerManager eventBus;
    private NavigationItem navigationItem = null;
    
    public Builder(final Display display, final HandlerManager eventBus) {
      this.display = display;
      this.eventBus = eventBus;
    }
    
    public Builder setNavigationItem(NavigationItem navItem) {
      navigationItem = navItem;
      return this;
    }
    
    public NavigationItemPresenter build() {
      if (navigationItem == null) {
        throw new NullPointerException("Required value not set : navigationItem");
      }
      final NavigationItemPresenter presenter = new NavigationItemPresenter(display, eventBus);
      presenter.setNavigationItem(navigationItem);
      presenter.bind();
      return presenter;
    }
  }
  
  final private NavigationItemPresenter.Display display;
  final private HandlerManager eventBus;
  final private ArrayList<HandlerRegistration> handlers;
  private NavigationItem navigationItem;

  /**
   * @param display
   * @param eventBus
   */
  private NavigationItemPresenter(final Display display, final HandlerManager eventBus) {
    super();
    this.display = display;
    this.eventBus = eventBus;
    handlers = new ArrayList<HandlerRegistration>();
  }

  @Override
  public void bind() {
    display.getLink().addClickHandler(new ClickHandler() {
      
      @Override
      public void onClick(ClickEvent event) {
        eventBus.fireEvent(new ShowNavigationItemEvent(navigationItem));
      }
    });
    
    handlers.add(eventBus.addHandler(ShowNavigationItemEvent.TYPE, new ShowNavigationItemHandler() {
      
      @Override
      public void onShowNavigationItem(ShowNavigationItemEvent event) {
        display.setSelected(navigationItem.equals(event.getNavigationItem()));
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
    // No-op.
  }
  
  public void setNavigationItem(final NavigationItem navItem){
    navigationItem = navItem;
    display.setHtml(navigationItem.getHtml());
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof NavigationItem) {
      return this.navigationItem.equals((NavigationItem) obj);
    }
    if (! (obj instanceof NavigationItemPresenter)) {
      return false;
    }
    return this.navigationItem.equals(((NavigationItemPresenter) obj).navigationItem);
  }

  @Override
  public int hashCode() {
    return this.navigationItem.hashCode();
  }
}
