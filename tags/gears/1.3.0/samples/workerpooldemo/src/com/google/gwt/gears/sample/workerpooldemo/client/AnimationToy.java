/*
 * Copyright 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.gwt.gears.sample.workerpooldemo.client;

import com.google.gwt.animation.client.Animation;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * A class that creates a little animation window.
 */
public class AnimationToy extends Composite {

  /**
   * A custom animation that moves a small image around a circle in an
   * {@link AbsolutePanel}.
   *
   * (Ripped from the GWT Showcase demo)
   */
  public class CustomAnimation extends Animation {
    /**
     * The x-coordinate of the center of the circle.
     */
    private int centerX = 60;

    /**
     * The y-coordinate of the center of the circle.
     */
    private int centerY = 60;

    /**
     * The radius of the circle.
     */
    private int radius = 50;

    protected CustomAnimation(int radius) {
      this.radius = radius;
      this.centerX = this.centerY = radius + 10;
    }

    @Override
    protected void onComplete() {
      super.onComplete();
      startButton.setEnabled(true);
      cancelButton.setEnabled(false);
    }

    @Override
    protected void onStart() {
      super.onStart();
      startButton.setEnabled(false);
      cancelButton.setEnabled(true);
    }

    @Override
    protected void onUpdate(double progress) {
      double radian = 2 * Math.PI * progress;
      updatePosition(animateeLeft, radian, 0);
      updatePosition(animateeBottom, radian, 0.5 * Math.PI);
      updatePosition(animateeRight, radian, Math.PI);
      updatePosition(animateeTop, radian, 1.5 * Math.PI);
    }

    /**
     * Update the position of the widget, adding a rotational offset.
     *
     * @param w the widget to move
     * @param radian the progress in radian
     * @param offset the offset in radian
     */
    private void updatePosition(Widget w, double radian, double offset) {
      radian += offset;
      double x = radius * Math.cos(radian) + centerX;
      double y = radius * Math.sin(radian) + centerY;
      absolutePanel.setWidgetPosition(w, (int) x, (int) y);
    }
  }

  /**
   * The absolute panel used in the example.
   */
  private AbsolutePanel absolutePanel = null;

  /**
   * The widget that is being animated.
   */
  private Widget animateeBottom = null;

  /**
   * The widget that is being animated.
   */
  private Widget animateeLeft = null;

  /**
   * The widget that is being animated.
   */
  private Widget animateeRight = null;

  /**
   * The widget that is being animated.
   */
  private Widget animateeTop = null;

  /**
   * The instance of an animation.
   */
  private CustomAnimation animation = null;

  /**
   * The {@link Button} used to cancel the {@link Animation}.
   */
  private Button cancelButton = null;

  /**
   * The {@link Button} used to start the {@link Animation}.
   */
  private Button startButton = null;

  /**
   * Constructor.
   */
  public AnimationToy() {
    initWidget(initialize());
  }

  /**
   * Initialize this example.
   */
  private Widget initialize() {

    // Create a new panel
    absolutePanel = new AbsolutePanel();

    // Add a widget that we will animate
    animateeTop = new Image("gwtLogoThumb.png");
    animateeBottom = new Image("gwtLogoThumb.png");
    animateeLeft = new Image("gwtLogoThumb.png");
    animateeRight = new Image("gwtLogoThumb.png");
    absolutePanel.add(animateeTop);
    absolutePanel.add(animateeBottom);
    absolutePanel.add(animateeLeft);
    absolutePanel.add(animateeRight);

    // Wrap the absolute panel in a DecoratorPanel
    VerticalPanel absolutePanelWrapper = new VerticalPanel();
    absolutePanelWrapper.add(absolutePanel);

    // Add the components to a panel and return it
    absolutePanelWrapper.add(createOptionsBar());

    // Create the custom animation
    animation = new CustomAnimation(70);

    // Set the start position of the widgets
    animation.onComplete();

    absolutePanel.setSize((animation.centerX * 2 + 10) + "px",
        (animation.centerY * 2 + 10) + "px");

    // Return the layout
    return absolutePanelWrapper;
  }

  /**
   * Create an options panel that allows users to select a widget and reposition
   * it.
   *
   * @return the new options panel
   */
  private Widget createOptionsBar() {
    // Create a panel to move components around
    HorizontalPanel optionsBar = new HorizontalPanel();
    optionsBar.setSpacing(5);
    optionsBar.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

    // Add a title
    optionsBar.add(new HTML("<b>Animate Logos</b>"));

    // Add start button
    startButton = new Button("Start");
    startButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        animation.run(2000);
      }
    });
    optionsBar.add(startButton);

    // Add cancel button
    cancelButton = new Button("Cancel");
    cancelButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        animation.cancel();
      }
    });
    optionsBar.add(cancelButton);

    // Return the options bar
    return optionsBar;
  }

}
