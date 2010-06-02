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
import com.google.gwt.maps.client.mvc.impl.MVCObjectImpl;

/**
 * This class implements {@link HasMVCObject}.
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public class MVCObject implements HasMVCObject {

  final private JavaScriptObject jso;
  
  public MVCObject(final JavaScriptObject jso) {
    this.jso = jso;
  }

  public MVCObject() {
    this(MVCObjectImpl.impl.construct());
  }
  
  @Override
  public void bindTo(String key, HasMVCObject target) {
    MVCObjectImpl.impl.bindTo(jso, key, target.getJso());
  }

  @Override
  public void changed(String key) {
    MVCObjectImpl.impl.changed(jso, key);
  }

  @Override
  public JavaScriptObject get(String key) {
    return MVCObjectImpl.impl.get(jso, key);
  }

  @Override
  public void notify(String key) {
    MVCObjectImpl.impl.notify(jso, key);
  }

  @Override
  public void set(String key, JavaScriptObject value) {
    MVCObjectImpl.impl.set(value, key, value);
  }

  @Override
  public void setValues(JavaScriptObject values) {
    MVCObjectImpl.impl.setValues(values, values);
  }

  @Override
  public void unbind(String key) {
    MVCObjectImpl.impl.unbind(jso, key);
  }

  @Override
  public void unbindAll() {
    MVCObjectImpl.impl.unbindAll(jso);
  }

  @Override
  public JavaScriptObject getJso() {
    return jso;
  }

}
