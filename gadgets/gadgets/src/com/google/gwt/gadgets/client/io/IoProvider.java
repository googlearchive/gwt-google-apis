/*
 * Copyright 2010 Google Inc.
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
package com.google.gwt.gadgets.client.io;

/**
 * Class providing implementation of {@link GadgetsIo}.
 */
public class IoProvider {

  private static GadgetsIo io = new GadgetsIoImpl();

  /**
   * Method for setting mock implementation of {@link GadgetsIo} for tests.
   *
   * @param mockIo Mock implementation of {@link GadgetsIo} for tests.
   */
  static void setMock(GadgetsIo mockIo) {
    IoProvider.io = mockIo;
  }

  /**
   * Method providing implementation of {@link GadgetsIo}.
   *
   * @return Implementation of {@link GadgetsIo}.
   */
  public static GadgetsIo get() {
    return io;
  }

  /**
   * This class is not meant to be instantiated.
   */
  private IoProvider(){
  }
}
