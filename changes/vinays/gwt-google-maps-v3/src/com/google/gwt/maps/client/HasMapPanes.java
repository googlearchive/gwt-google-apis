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
package com.google.gwt.maps.client;

import com.google.gwt.user.client.Element;

/**
 * 
 * 
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public interface HasMapPanes extends HasJso {

  /**
   * @return the floatPane
   */
  public Element getFloatPane();

  /**
   * @return the floatShadow
   */
  public Element getFloatShadow();

  /**
   * @return the mapPane
   */
  public Element getMapPane();

  /**
   * @return the overlayImage
   */
  public Element getOverlayImage();

  /**
   * @return the overlayLayer
   */
  public Element getOverlayLayer();

  /**
   * @return the overlayMouseTarget
   */
  public Element getOverlayMouseTarget();

  /**
   * @return the overlayShadow
   */
  public Element getOverlayShadow();

//  /**
//   * @param floatPane
//   *          the floatPane to set
//   */
//  public void setFloatPane(Element floatPane);
//
//  /**
//   * @param floatShadow
//   *          the floatShadow to set
//   */
//  public void setFloatShadow(Element floatShadow);
//
//  /**
//   * @param mapPane
//   *          the mapPane to set
//   */
//  public void setMapPane(Element mapPane);
//
//  /**
//   * @param overlayImage
//   *          the overlayImage to set
//   */
//  public void setOverlayImage(Element overlayImage);
//
//  /**
//   * @param overlayLayer
//   *          the overlayLayer to set
//   */
//  public void setOverlayLayer(Element overlayLayer);
//
//  /**
//   * @param overlayMouseTarget
//   *          the overlayMouseTarget to set
//   */
//  public void setOverlayMouseTarget(Element overlayMouseTarget);
//
//  /**
//   * @param overlayShadow
//   *          the overlayShadow to set
//   */
//  public void setOverlayShadow(Element overlayShadow);

}
