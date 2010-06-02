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
package com.google.gwt.maps.sample.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.maps.sample.client.Controller.Display;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Aplication page layout.
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public class ControllerView extends Composite implements Display {

  private static ControllerViewUiBinder uiBinder = GWT
      .create(ControllerViewUiBinder.class);

  interface ControllerViewUiBinder extends UiBinder<Widget, ControllerView> {
  }
  
  @UiField
  SimplePanel navBar;
  @UiField
  SimplePanel content;

  public ControllerView() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  @Override
  public void setContentDisplay(View display) {
    content.setWidget(display.asWidget());
  }

  @Override
  public void setNavigationDisplay(
      com.google.gwt.maps.sample.client.presenter.NavigationPresenter.Display display) {
    navBar.setWidget(display.asWidget());
  }

  @Override
  public Widget asWidget() {
    return this;
  }

}
