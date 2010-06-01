/*
 * Copyright 2009 Google Inc.
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
package com.google.gwt.gears.client.canvas;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptException;
import com.google.gwt.gears.client.Factory;
import com.google.gwt.gears.client.blob.Blob;
import com.google.gwt.gears.client.desktop.BlobMetaData;
import com.google.gwt.gears.client.desktop.Desktop;
import com.google.gwt.gears.client.httprequest.HttpRequest;
import com.google.gwt.gears.client.httprequest.RequestCallback;
import com.google.gwt.junit.client.GWTTestCase;

import java.util.Arrays;

/**
 * Some of these tests were inspired by:
 * http://code.google.com/p/gears/source/browse/trunk/gears/test/testcases/canvas_tests.js.
 */
public class CanvasTest extends GWTTestCase {

  private interface LoadBlobCallback {
    void onBlobLoaded(Blob blob, String filename);
  }

  private static void loadBlob(Factory factory, final String filename,
      final LoadBlobCallback callback) {
    HttpRequest request = factory.createHttpRequest();
    request.open("GET", GWT.getModuleBaseURL() + filename);
    request.setCallback(new RequestCallback() {
      public void onResponseReceived(HttpRequest request) {
        callback.onBlobLoaded(request.getResponseBlob(), filename);
      }
    });
    request.send();
  }

  @Override
  public String getModuleName() {
    return "com.google.gwt.gears.Gears";
  }

  public void testCrop() {
    final Factory factory = Factory.getInstance();

    Canvas canvas = factory.createCanvas();
    canvas.crop(40, 40, 100, 100);
    assertEquals(100, canvas.getWidth());
    assertEquals(100, canvas.getHeight());
  }

  public void testCropNoop() {
    final Factory factory = Factory.getInstance();

    delayTestFinish(5000);
    loadBlob(factory, "sample-original.jpeg", new LoadBlobCallback() {
      public void onBlobLoaded(Blob blob, String filename) {
        Canvas canvas = factory.createCanvas();
        canvas.decode(blob);

        Blob originalBlob = canvas.encode();
        int originalWidth = canvas.getWidth();
        int originalHeight = canvas.getHeight();

        canvas.crop(0, 0, originalWidth, originalHeight);
        assertEquals(originalBlob, canvas.encode());
        assertEquals(originalWidth, canvas.getWidth());
        assertEquals(originalHeight, canvas.getHeight());

        finishTest();
      }
    });
  }
  
  // XXX: Gears itself (0.5.23.0) fails this test
  /*
   * public void testCropToZeroSize() { final Factory factory =
   * Factory.getInstance();
   * 
   * Canvas canvas = factory.createCanvas(); canvas.crop(40, 40, 0, 0);
   * assertEquals(0, canvas.getWidth()); assertEquals(0, canvas.getHeight()); }
   */

  public void testDecode() {
    final Factory factory = Factory.getInstance();
    final Canvas canvas = factory.createCanvas();

    this.delayTestFinish(5000);
    loadBlob(factory, "sample-original.jpeg", new LoadBlobCallback() {
      public void onBlobLoaded(Blob blob, String filename) {
        canvas.decode(blob);
        assertEquals(313, canvas.getWidth());
        assertEquals(234, canvas.getHeight());

        finishTest();
      }
    });
  }

  public void testEncode() {
    Factory factory = Factory.getInstance();
    Canvas canvas = factory.createCanvas();
    Desktop desktop = factory.createDesktop();

    Blob blob = canvas.encode();
    BlobMetaData metaData = desktop.extractMetaData(blob);
    assertEquals("image/png", metaData.getMimeType());
    assertEquals(canvas.getWidth(), metaData.getImageWidth());
    assertEquals(canvas.getHeight(), metaData.getImageHeight());

    blob = canvas.encode("image/png");
    metaData = desktop.extractMetaData(blob);
    assertEquals("image/png", metaData.getMimeType());
    assertEquals(canvas.getWidth(), metaData.getImageWidth());
    assertEquals(canvas.getHeight(), metaData.getImageHeight());

    blob = canvas.encode("image/jpeg");
    metaData = desktop.extractMetaData(blob);
    assertEquals("image/jpeg", metaData.getMimeType());
    assertEquals(canvas.getWidth(), metaData.getImageWidth());
    assertEquals(canvas.getHeight(), metaData.getImageHeight());

    try {
      canvas.encode("image/gif");
      fail();
    } catch (JavaScriptException jse) {
    }
  }

  public void testPropertiesCanBeWrittenAndReadBack() {
    Factory factory = Factory.getInstance();

    Canvas canvas = factory.createCanvas();
    assertEquals(150, canvas.getHeight());
    assertEquals(300, canvas.getWidth());

    canvas.setHeight(40);
    canvas.setWidth(50);

    assertEquals(40, canvas.getHeight());
    assertEquals(50, canvas.getWidth());
  }

  public void testResize() {
    final Factory factory = Factory.getInstance();

    Canvas canvas = factory.createCanvas();
    int newWidth = 400;
    int newHeight = 40;
    canvas.resize(newWidth, newHeight);
    assertEquals(newWidth, canvas.getWidth());
    assertEquals(newHeight, canvas.getHeight());
  }

  public void testResizeWeirdCases() {
    Factory factory = Factory.getInstance();
    Canvas canvas = factory.createCanvas();
    try {
      canvas.resize(-4, 9);
      fail();
    } catch (JavaScriptException jse) {
    }
    // XXX: Gears itself (0.5.23.0) fails this test
    /*
     * canvas.resize(0, 0); assertEquals(0, canvas.getWidth()); assertEquals(0,
     * canvas.getHeight());
     */
  }

  private void assertEquals(Blob blob1, Blob blob2) {
    assertEquals(blob1.getLength(), blob2.getLength());
    for (int i = 0, l = blob1.getLength(); i < l; i += 1024) {
      int length = Math.min(l - i, 1024);
      assertTrue(Arrays.equals(blob1.getBytes(i, length), blob2.getBytes(i,
          length)));
    }
  }
}
