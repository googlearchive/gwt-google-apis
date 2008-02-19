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
package com.google.gwt.gadgets.client;

import com.google.gwt.core.client.EntryPoint;

/**
 * A base class for writing a Google Gadget.
 * 
 * <p>
 * Writing a Gadget:
 * <ol>
 * <li>Declare a subtype of Gadget (e.g.
 * <code>public class MyGadget extends Gadget</code>) that is default
 * instantiable.</li>
 * <li>If the Gadget requires access to features of the container, it should
 * implement any of the <code>NeedsFoo</code> interfaces.</li>
 * <li>At runtime, all feature setters will be called strictly before
 * {@link #init(UserPreferences)}. The order in which the setters are called is
 * undefined.</li>
 * </ol>
 * 
 * <p>
 * Access to user preferences is provided through a user-defined subtype of the
 * {@link UserPreferences} interface. Each preference should be defined as a
 * zero-argument method, returning the desired type of {@link Preference}. The
 * Gadget type should be parameterized with the specific UserPrefrences subtype,
 * which will be provided to the {@link #init(UserPreferences)} method.
 * 
 * @param <T> the type of UserPreferences the Gadget expects.
 */
public abstract class Gadget<T extends UserPreferences> implements EntryPoint {
  protected Gadget() {
  }

  /**
   * This is used by the Gadget framework for low-level bootstrap functions.
   */
  public final void onModuleLoad() {
  }

  /**
   * This method will be called after all of the feature initialization hooks
   * have been called.
   */
  protected abstract void init(T preferences);
}
