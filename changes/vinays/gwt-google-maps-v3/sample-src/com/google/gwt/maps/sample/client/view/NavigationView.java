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
import com.google.gwt.maps.sample.client.presenter.NavigationPresenter.Display;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Navigation bar ui.
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public class NavigationView extends Composite implements Display {

  private static NavigationViewUiBinder uiBinder = GWT
      .create(NavigationViewUiBinder.class);

  interface NavigationViewUiBinder extends UiBinder<Widget, NavigationView> {
  }

  @UiField
  FlowPanel navigationList;
  
  public NavigationView() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  @Override
  public void addNavigationItem(
      com.google.gwt.maps.sample.client.presenter.NavigationItemPresenter.Display navItem,
      int index) {
    navigationList.insert(navItem.asWidget(), index);
  }

  @Override
  public void clearNavigationItems() {
    navigationList.clear();
  }

  @Override
  public com.google.gwt.maps.sample.client.presenter.NavigationItemPresenter.Display getNavItemDisplay() {
    return new NavigationItemView();
  }

  @Override
  public void removeNavigationItem(int index) {
    navigationList.remove(index);
  }

  @Override
  public Widget asWidget() {
    return this;
  }

}
