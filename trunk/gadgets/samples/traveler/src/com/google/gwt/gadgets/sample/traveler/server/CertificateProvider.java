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
package com.google.gwt.gadgets.sample.traveler.server;

import com.google.appengine.repackaged.com.google.common.collect.ImmutableMapBuilder;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Provides public certificates for opensocial containers.
 *
 * For list of public certificates visit <a
 * href="https://opensocialresources.appspot.com/certificates">this site</a>.
 */
public class CertificateProvider {

  private static final Logger log = Logger.getLogger(CertificateProvider.class.getName());

  private static final String PATH_PREFIX = "WEB-INF/certificates/";
  private static final Map<String, String> CERTIFICATES;

  static {
    Map<String, String> filenames = new HashMap<String, String>();
    filenames.put("www.google.com", "igoogle.cert");
    filenames.put("orkut.com", "pub.1199819524.-1556113204990931254.cer");
    // put next certificates here:
    // filenames.put("consumer - key", "filename from WEB-INF/certificates/");

    ImmutableMapBuilder<String, String> certificates = new ImmutableMapBuilder<String, String>();
    for (Map.Entry<String, String> entry : filenames.entrySet()) {
      String path = PATH_PREFIX + entry.getValue();
      String certificate = null;
      try {
        certificate = getFileAsString(new File(path));
      } catch (FileNotFoundException e) {
        log.warning("missing file for: " + entry.getKey());
      } catch (IOException e) {
        log.warning(e.getMessage());
      }
      if (certificate != null) {
        certificates.put(entry.getKey(), certificate);
      }
    }
    CERTIFICATES = certificates.getMap();
  }

  private static CertificateProvider certificateProvider;

  public static CertificateProvider get() {
    if (certificateProvider == null) {
      certificateProvider = new CertificateProvider();
    }
    return certificateProvider;
  }

  private static String getFileAsString(File file) throws IOException {
    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
    byte[] bytes = new byte[(int) file.length()];
    bis.read(bytes);
    bis.close();
    return new String(bytes);
  }

  private CertificateProvider() {
  }

  public String getCertificate(String consumerKey) {
    return CERTIFICATES.get(consumerKey);
  }
}
