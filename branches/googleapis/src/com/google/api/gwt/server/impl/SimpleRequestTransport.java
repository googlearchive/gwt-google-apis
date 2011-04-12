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
package com.google.api.gwt.server.impl;

import com.google.api.gwt.shared.GoogleApiRequestTransport;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Intended to be constructed via {@link GoogleApiRequestTransport.Builder}.
 */
public class SimpleRequestTransport extends GoogleApiRequestTransport {

  @Override
  protected void configure(RequestTransportReceiver receiver) {
    receiver.onSuccess(this);
  }


  static String readResponse(HttpURLConnection conn) throws IOException {
    StringBuilder sb = new StringBuilder();
    InputStreamReader in = new InputStreamReader(conn.getInputStream());
    char[] buf = new char[1024];
    for (;;) {
      int length = in.read(buf);
      if (length == -1) {
        break;
      }
      sb.append(buf, 0, length);
    }
    in.close();
    conn.disconnect();
    String contents = sb.toString();
    if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
      throw new IOException("Bad response code: " + conn.getResponseCode() + " "
          + conn.getResponseMessage());
    }
    return contents;
  }


  @Override
  public void send(String payload, TransportReceiver receiver) {
    HttpURLConnection conn = null;
    try {
      URL url = new URL(getRpcUrl());
      conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("POST");
      for (Map.Entry<String, String> entry : getHeaders().entrySet()) {
        conn.setRequestProperty(entry.getKey(), entry.getValue());
      }
      conn.setUseCaches(false);
      conn.setDoInput(true);
      conn.setDoOutput(true);

      conn.connect();

      PrintStream out = new PrintStream(conn.getOutputStream());
      out.print(payload);
      out.flush();
      out.close();

      String contents = readResponse(conn);
      receiver.onTransportSuccess(contents);
    } catch (IOException e) {
      String httpCode = null;
      String httpMessage = e.getMessage();
      if (conn != null) {
        try {
          httpCode = String.valueOf(conn.getResponseCode());
          httpMessage = conn.getResponseMessage();
        } catch (IOException ignored) {
          // Can't do much about this
        }
      }
      receiver.onTransportFailure(new ServerFailure(httpMessage, httpCode, null, true));
    }
  }
}
