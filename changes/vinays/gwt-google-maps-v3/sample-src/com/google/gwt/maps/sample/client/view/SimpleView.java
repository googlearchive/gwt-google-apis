/* Copyright (c) 2010 Google Inc.
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
package com.google.gwt.maps.sample.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.maps.client.MapOptions;
import com.google.gwt.maps.client.MapTypeId;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.sample.client.presenter.SimplePresenter.Display;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Simple sample ui.
 *
 */
public class SimpleView extends Composite implements Display {
  
  final private static int ZOOM = 8;
  final private static LatLng CENTER = LatLng.construct(-34.397, 150.644);
  final private static String MAP_TYPE = new MapTypeId().getRoadmap();

  final private MapWidget mapWidget;
  
  private static SimpleViewUiBinder uiBinder = GWT
      .create(SimpleViewUiBinder.class);

  interface SimpleViewUiBinder extends UiBinder<Widget, SimpleView> {
  }
  
  @UiField
  Anchor presenterLink;
  @UiField
  Anchor viewLink;
  @UiField
  SimplePanel mapWrapper;

  public SimpleView() {
    initWidget(uiBinder.createAndBindUi(this));
    final MapOptions options = new MapOptions();
    options.setZoom(ZOOM);
    options.setCenter(CENTER);
    options.setMapTypeId(MAP_TYPE);
    options.setDraggable(true);
    options.setNavigationControl(true);
    options.setMapTypeControl(true);
    mapWidget = new MapWidget(options);
    mapWrapper.setWidget(mapWidget);
    mapWidget.setSize("800px", "600px");
  }

  @Override
  public Widget asWidget() {
    return this;
  }

  @Override
  public void setPresenterLink(String url) {
    presenterLink.setHref(url);
    presenterLink.setText(url);
  }

  @Override
  public void setViewLink(String url) {
    viewLink.setHref(url);
    viewLink.setText(url);
  }

}
