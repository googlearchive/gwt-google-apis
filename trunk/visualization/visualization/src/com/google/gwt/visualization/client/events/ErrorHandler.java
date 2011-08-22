/*
 * Copyright 2011 Google Inc.
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
package com.google.gwt.visualization.client.events;

import com.google.gwt.ajaxloader.client.Properties;

/**
 * A handler for error events. In principle, all visualizations should throw
 * an error event in the event of an error.
 */
public abstract class ErrorHandler extends Handler {
  public static class ErrorException extends RuntimeException {
    public ErrorException(Throwable cause) {
      super(cause);
    }
  }

  /**
   * The ready event is fired when visualization has finished rendering. The
   * ReadyEvent class is a placeholder.
   */
  public static class ErrorEvent {
    private String id;
    private String message;
    private String detailedMessage;
    private Properties options;

    private ErrorEvent(String id, String message, String detailedMessage,
        Properties options) {
      this.id = id;
      this.message = message;
      this.detailedMessage = detailedMessage;
      this.options = options;
    }

    /**
     * @return The ID of the DOM element containing the chart that threw the
     * error.
     */
    public String getId() {
      return id;
    }

    public String getMessage() {
      return message;
    }

    public String getDetailedMessage() {
      return detailedMessage;
    }

    public Properties getOptions() {
      return options;
    }
  }

  public abstract void onError(ErrorEvent event);

  @Override
  protected void onEvent(Properties properties) {
    try {
      onError(new ErrorEvent(properties.getString("id"),
          properties.getString("message"),
          properties.getString("detailedMessage"),
          (Properties) properties.getObject("options")));
   } catch (Properties.TypeException x) {
     throw new ErrorException(x);
   }
  }
}
