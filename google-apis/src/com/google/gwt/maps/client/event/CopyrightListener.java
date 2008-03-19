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

/**
 * CopyrightListener defines the interface for an object that listens for
 * additions to a {@link CopyrightCollection}.
 * 
 * @see CopyrightCollection#addCopyrightListener(CopyrightListener)
 */
public interface CopyrightListener {

  /**
   * Called on a "newcopyright" event. This event is fired when a new copyright
   * is added to the copyright collection of one of the tile layers contained in
   * this map type.
   * 
   * @param sender the originator of this event.
   * @param copyright the new copyright that was added.
   */
  
  void onNewCopyright(CopyrightCollection sender, Copyright copyright);

}
