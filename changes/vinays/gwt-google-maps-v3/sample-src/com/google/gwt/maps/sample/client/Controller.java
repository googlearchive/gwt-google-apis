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

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.maps.sample.client.event.ShowNavigationItemEvent;
import com.google.gwt.maps.sample.client.event.ShowNavigationItemHandler;
import com.google.gwt.maps.sample.client.presenter.DirectionsPanelPresenter;
import com.google.gwt.maps.sample.client.presenter.EventClosurePresenter;
import com.google.gwt.maps.sample.client.presenter.EventSimplePresenter;
import com.google.gwt.maps.sample.client.presenter.GeocoderSimplePresenter;
import com.google.gwt.maps.sample.client.presenter.NavigationPresenter;
import com.google.gwt.maps.sample.client.presenter.PolygonSimplePresenter;
import com.google.gwt.maps.sample.client.presenter.Presenter;
import com.google.gwt.maps.sample.client.presenter.SimplePresenter;
import com.google.gwt.maps.sample.client.view.DirectionsPanelView;
import com.google.gwt.maps.sample.client.view.EventClosureView;
import com.google.gwt.maps.sample.client.view.EventSimpleView;
import com.google.gwt.maps.sample.client.view.GeocoderSimpleView;
import com.google.gwt.maps.sample.client.view.PolygonSimpleView;
import com.google.gwt.maps.sample.client.view.SimpleView;
import com.google.gwt.maps.sample.client.view.View;

/**
 * Controller to listen to events and take relvant actions.
 * 
 * It is responsible for screen switches.
 * TODO(vinays): Use history management for screen switching.
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public class Controller implements Presenter<Controller.Display>{

  public static interface Display extends View {
    void setNavigationDisplay(NavigationPresenter.Display display);
    
    void setContentDisplay(View display);
  }
  
  final private static String SAMPLE_SIMPLE = "simple";
  final private static String SAMPLE_SIMPLE_EVENT = "event-simple";
  final private static String SAMPLE_EVENT_CLOSURE = "event-closure";
  final private static String SAMPLE_POLYGON_SIMPLE = "polygon-simple";
  final private static String SAMPLE_GEOCODER_SIMPLE = "geocoder-simple";
  final private static String SAMPLE_DIRECTIONS_PANEL = "directions-panel";
  
  final private Controller.Display display;
  final private HandlerManager eventBus;
  final private ArrayList<HandlerRegistration> handlers;
  
  private Presenter<?> lastPresenter = null;

  /**
   * @param display
   * @param eventBus
   */
  public Controller(Display display, HandlerManager eventBus) {
    super();
    this.display = display;
    this.eventBus = eventBus;
    handlers = new ArrayList<HandlerRegistration>();
  }
  
  public void setNavigationPresenter(final NavigationPresenter navPresenter) {
    display.setNavigationDisplay(navPresenter.getDisplay());
  }

  @Override
  public void bind() {
    handlers.add(eventBus.addHandler(ShowNavigationItemEvent.TYPE, new ShowNavigationItemHandler() {
      
      @Override
      public void onShowNavigationItem(ShowNavigationItemEvent event) {
        final String title = event.getNavigationItem().getName().toLowerCase();
        Presenter<?> presenter = null;
        if (SAMPLE_SIMPLE.equals(title)) {
          presenter = showSimple();
        } else if (SAMPLE_SIMPLE_EVENT.equals(title)) {
          presenter = showSimpleEvent();
        } else if (SAMPLE_EVENT_CLOSURE.equals(title)) {
          presenter = showEventClosure();
        } else if (SAMPLE_POLYGON_SIMPLE.equals(title)) {
          presenter = showPolygonSimple();
        } else if (SAMPLE_GEOCODER_SIMPLE.equals(title)) {
          presenter = showGeocoderSimple();
        } else if (SAMPLE_DIRECTIONS_PANEL.equals(title)) {
          presenter = showDirectionsPanel();
        }
        
        if (presenter != null) {
          if (lastPresenter != null) {
            lastPresenter.release();
          }
          lastPresenter = presenter;
        }
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
    for (HandlerRegistration handler : handlers) {
      handler.removeHandler();
    }
  }
  
  private SimplePresenter showSimple() {
    final SimplePresenter.Display view = new SimpleView();
    display.setContentDisplay(view);
    final SimplePresenter presenter = new SimplePresenter(view, eventBus);
    presenter.bind();
    return presenter;
  }
  
  private EventSimplePresenter showSimpleEvent() {
    final EventSimplePresenter.Display view = new EventSimpleView();
    display.setContentDisplay(view);
    final EventSimplePresenter presenter = new EventSimplePresenter(view, eventBus);
    presenter.bind();
    return presenter;
  }
  
  private EventClosurePresenter showEventClosure() {
    final EventClosurePresenter.Display view = new EventClosureView();
    display.setContentDisplay(view);
    final EventClosurePresenter presenter = new EventClosurePresenter(view, eventBus);
    presenter.bind();
    return presenter;
  }
  
  private PolygonSimplePresenter showPolygonSimple() {
    final PolygonSimplePresenter.Display view = new PolygonSimpleView();
    display.setContentDisplay(view);
    final PolygonSimplePresenter presenter = new PolygonSimplePresenter(view);
    presenter.bind();
    return presenter;
  }
  
  private GeocoderSimplePresenter showGeocoderSimple() {
    final GeocoderSimplePresenter.Display view = new GeocoderSimpleView();
    display.setContentDisplay(view);
    final GeocoderSimplePresenter presenter = new GeocoderSimplePresenter(view);
    presenter.bind();
    return presenter;
  }
  
  private DirectionsPanelPresenter showDirectionsPanel() {
    final DirectionsPanelPresenter.Display view = new DirectionsPanelView();
    display.setContentDisplay(view);
    final DirectionsPanelPresenter presenter = new DirectionsPanelPresenter(view);
    presenter.bind();
    return presenter;
  }
}
