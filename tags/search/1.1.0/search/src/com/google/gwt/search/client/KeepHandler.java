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
package com.google.gwt.search.client;

/**
 * Receives notifications that the user wished to keep a search result.
 */
public interface KeepHandler {

  /**
   * A container for arguments of the Keep callback.
   */
  static class KeepEvent {
    private SearchControl control;
    private Result result;

    KeepEvent(SearchControl control, Result result) {
      this.control = control;
      this.result = result;
    }

    /**
     * Returns the Result selected by the user.
     * 
     * @return the Result selected by the user.
     */
    public Result getResult() {
      return result;
    }

    /**
     * Returns the {@link SearchControl} that the user interacted with.
     * 
     * @return the {@link SearchControl} that the user interacted with.
     */
    public SearchControl getSearchControl() {
      return control;
    }
  }

  /**
   * Invoked when the user selects a result to keep.
   * 
   * @param event arguments to the callback.
   */
  void onKeep(KeepEvent event);
}
