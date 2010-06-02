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

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.maps.sample.client.bean.NavigationItem;
import com.google.gwt.maps.sample.client.event.ShowNavigationItemEvent;
import com.google.gwt.maps.sample.client.presenter.NavigationPresenter;
import com.google.gwt.maps.sample.client.view.ControllerView;
import com.google.gwt.maps.sample.client.view.NavigationView;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Sample usage code for GWT Maps API v3.
 * http://code.google.com/p/gwt-google-maps-v3/
 * 
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public class MapsSamples implements EntryPoint {
  
  final private HandlerManager eventBus = new HandlerManager(null);
  
  @Override
  public void onModuleLoad() {
    GWT.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
      
      @Override
      public void onUncaughtException(Throwable e) {
        e.printStackTrace();
      }
    });
    final ControllerView view = new ControllerView();
    final Controller controller = new Controller(view, eventBus);
    final NavigationItemsProvider navItemsProvider = new NavigationItemsProvider();
    final NavigationView navView = new NavigationView();
    final NavigationPresenter navPresenter = new NavigationPresenter(eventBus, navView);
    navPresenter.setNavigationItems(navItemsProvider.get());
    navPresenter.bind();
    controller.setNavigationPresenter(navPresenter);
    controller.bind();
    
    RootPanel.get("chrome").add(view.asWidget());
    
    final NavigationItem navItem = new NavigationItem("Simple", "Simple");
    eventBus.fireEvent(new ShowNavigationItemEvent(navItem));
  }
}
