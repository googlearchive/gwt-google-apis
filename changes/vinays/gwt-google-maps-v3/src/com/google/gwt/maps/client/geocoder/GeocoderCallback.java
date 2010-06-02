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
package com.google.gwt.maps.client.geocoder;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.jsio.client.Exported;
import com.google.gwt.jsio.client.JSFunction;
import com.google.gwt.jsio.client.JSList;

/**
 * Callback interface for geocoder request.
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public abstract class GeocoderCallback extends JSFunction {
  
  public abstract void callback(List<HasGeocoderResult> responses, String status);
  
  @Exported
  public void callbackWrapper(JSList<JavaScriptObject> response, String status) {
    List<HasGeocoderResult> responses = new ArrayList<HasGeocoderResult>();
    for (JavaScriptObject jso : response) {
      responses.add(new GeocoderResult(jso));
    }
    callback(responses, status);
  }
  
}
