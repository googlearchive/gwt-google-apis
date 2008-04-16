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
package com.google.gwt.maps.client.event;

import com.google.gwt.maps.client.Copyright;
import com.google.gwt.maps.client.CopyrightCollection;

import java.util.EventObject;

/**
 * Provides an interface to implement in order to receive MapEvent.NEWCOPYRIGHT
 * events from the {@link CopyrightCollection}.
 * 
 */
public interface NewCopyrightHandler {

  /*
   * For consistency, this class should have been named
   * CopyrightCollectionNewCopyrightHandler, but that is a real mouthful, so I left it short.
   */
  /**
   * Encapsulates the arguments for event.
   */
  @SuppressWarnings("serial")
  class NewCopyrightEvent extends EventObject {
    private final Copyright copyright;

    public NewCopyrightEvent(CopyrightCollection source, Copyright copyright) {
      super(source);
      this.copyright = copyright;
    }

    /**
     * Returns the copyright associated with this event.
     * 
     * @return the copyright associated with this event.
     */
    public Copyright getCopyright() {
      return copyright;
    }

    /**
     * Returns the instance of the {@link CopyrightCollection} that generated
     * this event.
     * 
     * @return the instance of the {@link CopyrightCollection} that generated
     *         this event.
     */
    public CopyrightCollection getSender() {
      return (CopyrightCollection) getSource();
    }
  }

  /**
   * Method to be invoked when a "newcopyright" event fires on a
   * {@link CopyrightCollection}.
   * 
   * @param event contains the properties of the event.
   */
  void onNewCopyright(NewCopyrightEvent event);
}
