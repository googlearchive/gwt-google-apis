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

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.maps.sample.client.Constant;
import com.google.gwt.maps.sample.client.view.SampleView;

/**
 * Simple maps sample.
 * 
 * Embed maps in to page with basic controls.
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public class SimplePresenter implements Presenter<SimplePresenter.Display> {

  final private String presenterLink;
  final private String viewLink;
  
  public static interface Display extends SampleView {
  }
  
  final private SimplePresenter.Display display;
  final private HandlerManager eventBus;
  
  /**
   * @param display
   * @param eventBus
   */
  public SimplePresenter(SimplePresenter.Display display, HandlerManager eventBus) {
    super();
    this.display = display;
    this.eventBus = eventBus;
    presenterLink = Constant.SOURCE_URL_PREFIX + this.getClass().getName().replace('.', '/')
        + ".java";
    viewLink = Constant.SOURCE_URL_PREFIX + display.getClass().getName().replace('.', '/')
        + ".java";
  }

  @Override
  public void bind() {
    display.setPresenterLink(presenterLink);
    display.setViewLink(viewLink);
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
}
