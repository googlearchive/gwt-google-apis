/*
 * Copyright 2009 Google Inc.
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
 * If a gadget wants to support multiple views, it has to contain one
 * ContentSection for each. The annotation
 * {@link com.google.gwt.gadgets.client.Gadget.InjectContent} can be used to
 * inject content into the section and
 * {@link com.google.gwt.gadgets.client.Gadget.ContentType} is used to define
 * which view this section is used for.
 * 
 * @param <T> A Gadget subclass
 */
public abstract class ContentSection<T extends Gadget<?>> {

  /**
   * This is the entry method for the content section. It should be overridden
   * by a user-defined implementation.
   * 
   * @param gadget The gadget object that contains this content section.
   */
  public abstract void init(T gadget);
}
