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
package com.google.gwt.gears.sample.uploaddemo.server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Accepts a POST request containing a file and writes the data to a temporary
 * file. A size limit of 20MB is enforced.
 */
public class UploadServlet extends HttpServlet {
  private static final long serialVersionUID = -5911011533094624685L;

  private static final int MB = 1048576;
  private static final int MAX_FILE_SIZE = 20 * MB;

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    if (req.getContentLength() > MAX_FILE_SIZE) {
      resp.sendError(HttpServletResponse.SC_REQUEST_ENTITY_TOO_LARGE,
          "File is too large");
      return;
    }

    String fileName = req.getHeader("X-Filename");
    if (fileName != null) {
      System.out.println("File name: " + fileName);
    }

    InputStream in = req.getInputStream();
    if (in != null) {
      File tmp = File.createTempFile("upload", ".tmp");
      FileOutputStream out = new FileOutputStream(tmp);
      byte[] buffer = new byte[65536];

      int l;
      while ((l = in.read(buffer)) != -1) {
        out.write(buffer, 0, l);
      }
      out.close();
      in.close();

      System.out.println("Saved " + req.getContentLength() + " bytes to "
          + tmp.getAbsolutePath());

      resp.setStatus(HttpServletResponse.SC_OK);
      resp.setContentType("text/plain");
      resp.getWriter().println("OK");
      if (fileName != null) {
        resp.getWriter().println("file name: " + fileName);
      }
      resp.getWriter().println(
          "saved " + req.getContentLength() + " bytes to "
              + tmp.getAbsolutePath());

    } else {
      resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }
  }
}
