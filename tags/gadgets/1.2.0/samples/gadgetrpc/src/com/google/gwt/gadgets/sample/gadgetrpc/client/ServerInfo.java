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
package com.google.gwt.gadgets.sample.gadgetrpc.client;

import java.io.Serializable;
import java.util.Date;

/**
 * Payload returned from the server.
 */
@SuppressWarnings("serial")
public class ServerInfo implements Serializable {
  private Date servletStartTime;
  private Date currentTime;

  public Date getCurrentTime() {
    return currentTime;
  }

  public Date getServletStartTime() {
    return servletStartTime;
  }

  public void setCurrentTime(Date currentTime) {
    this.currentTime = currentTime;
  }

  public void setServletStartTime(Date servletStartTime) {
    this.servletStartTime = servletStartTime;
  }
}
