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
package com.google.gwt.maps.client.base;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.base.impl.InfoWindowOptionsImpl;

/**
 * This class implements {@link HasInfoWindowOptions}.
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public class InfoWindowOptions implements HasInfoWindowOptions {

  final private JavaScriptObject jso;
  
  public InfoWindowOptions(JavaScriptObject jso) {
    this.jso = jso;
  }
  
  public InfoWindowOptions() {
    this(InfoWindowOptionsImpl.impl.construct());
  }
  
  @Override
  public String getContent() {
    return InfoWindowOptionsImpl.impl.getContent(jso);
  }

  @Override
  public int getMaxWidth() {
    return InfoWindowOptionsImpl.impl.getMaxWidth(jso);
  }

  @Override
  public HasSize getPixelOffset() {
    return new Size(InfoWindowOptionsImpl.impl.getPixelOffset(jso));
  }

  @Override
  public HasLatLng getPosition() {
    return new LatLng(InfoWindowOptionsImpl.impl.getPosition(jso));
  }

  @Override
  public int getZIndex() {
    return InfoWindowOptionsImpl.impl.getZIndex(jso);
  }

  @Override
  public boolean isDisableAutoPan() {
    return InfoWindowOptionsImpl.impl.isDisableAutoPan(jso);
  }

  @Override
  public void setContent(String html) {
    InfoWindowOptionsImpl.impl.setContent(jso, html);
  }

  @Override
  public void setDisableAutoPan(boolean disableAutoPan) {
    InfoWindowOptionsImpl.impl.setDisableAutoPan(jso, disableAutoPan);
  }

  @Override
  public void setMaxWidth(int maxWidth) {
    InfoWindowOptionsImpl.impl.setMaxWidth(jso, maxWidth);
  }

  @Override
  public void setPixelOffset(HasSize size) {
    InfoWindowOptionsImpl.impl.setPixelOffset(jso, size.getJso());
  }

  @Override
  public void setPosition(HasLatLng position) {
    InfoWindowOptionsImpl.impl.setPosition(jso, position.getJso());
  }

  @Override
  public void setZIndex(int zIndex) {
    InfoWindowOptionsImpl.impl.setZIndex(jso, zIndex);
  }

  @Override
  public JavaScriptObject getJso() {
    return jso;
  }

}
