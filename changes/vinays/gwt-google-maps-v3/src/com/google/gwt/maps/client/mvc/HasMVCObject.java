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
package com.google.gwt.maps.client.mvc;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.HasJso;

/**
 * Base class implementing KVO.
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public interface HasMVCObject extends HasJso {

  /**
   * Binds a View to a Model.
   */
  public void bindTo(String key, HasMVCObject target);
  
  /**
   * Generic handler for state changes. Override this in derived classes to
   * handle arbitrary state changes.
   */
  public void changed(String key);
  
  /**
   * Gets a value.
   */
  JavaScriptObject get(String key);
  
  /**
   * Notify all observers of a change on this property. This notifies both
   * objects that are bound to the object's property as well as the object that
   * it is bound to.
   */
  void notify(String key);
  
  /**
   * Sets a value.
   */
  void set(String key, JavaScriptObject value);
  
  /**
   * Sets a collection of key-value pairs.
   */
  void setValues(JavaScriptObject values);
  
  /**
   * Removes a binding. Unbinding will set the unbound property to the current
   * value. The object will not be notified, as the value has not changed.
   */
  void unbind(String key);
  
  /**
   * Removes all bindings.
   */
  void unbindAll();
  
}
