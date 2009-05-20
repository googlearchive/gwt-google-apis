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
package com.google.gwt.gears.client.localserver;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;

/**
 * The FileSubmitter object facilitates the submission of local files, that were
 * previously captured using {@link ResourceStore#captureFile(Element, String)},
 * in multipart/form-data encoded form submissions.
 * 
 * To create a {@link FileSubmitter}, call
 * {@link ResourceStore#createFileSubmitter()}. The returned object allows only
 * URLs contained in the originating resource store to be submitted.
 */
public final class FileSubmitter extends JavaScriptObject {
  protected FileSubmitter() {
    // Required for overlay types
  }

  /**
   * Prepares the htmlElement to submit the file indicated by <code>url</code>.
   * The <code>htmlElement</code> must be contained in an html form. When the
   * form is submitted the file will be included as part of the resulting POST.
   * The <code>url</code> is the captured resource's key in the originating
   * Store. Relative URLs are interpreted according to the current page's
   * location. The name attribute of <code>htmlElement</code> determines the
   * parameter name associated with the uploaded file.
   * 
   * Due to differences between Firefox and Internet Explorer, this method's
   * behavior is browser specific. Firefox: The <code>htmlElement</code> must
   * be a reference to an &lt;input type="file"&gt; element. Internet Explorer:
   * The <code>htmlElement</code> must NOT be a reference to an &lt;input&gt;
   * of any type.
   */
  public native void setFileInputElement(Element htmlElement, String url) /*-{
    this.setFileInputElement(htmlElement, url);
  }-*/;
}