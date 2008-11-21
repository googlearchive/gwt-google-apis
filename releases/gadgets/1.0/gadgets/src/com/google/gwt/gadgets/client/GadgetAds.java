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

/**
 * A base class for writing a Google Gadget Ads.
 * 
 * <p>Adds the {@code clickurl} and {@code aiturl} UserPrefs and the {@code ads}
 * feature that are requirements for Gadget Ads.
 * @param <T> a subclass of AdsUserPreferences.
 * 
 */
public abstract class GadgetAds<T extends AdsUserPreferences> extends Gadget<T>
  implements NeedsAds {
}
