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
package com.google.gwt.gears.client.blobbuilder;

import com.google.gwt.core.client.JavaScriptException;
import com.google.gwt.gears.client.Factory;
import com.google.gwt.gears.client.blob.Blob;
import com.google.gwt.junit.client.GWTTestCase;

/**
 * These tests are a port of:
 * http://code.google.com/p/gears/source/browse/trunk/gears/test/testcases/blob_tests.js.
 */
public class BlobBuilderTest extends GWTTestCase {

  @Override
  public String getModuleName() {
    return "com.google.gwt.gears.Gears";
  }

  public void testBlobBuilder() {
    Factory factory = Factory.getInstance();
    
    BlobBuilder builder1 = factory.createBlobBuilder();
    // This is actually already tested in FactoryTest
    assertNotNull("Could not create a blob builder", builder1);
    builder1.append("Hello");
    builder1.append(" ");
    builder1.append("World");
    Blob blob1 = builder1.getAsBlob();
    assertEquals(11, blob1.getLength());
    
    BlobBuilder builder2 = factory.createBlobBuilder();
    builder2.append(".");
    Blob blob2 = builder2.getAsBlob();
    assertEquals(1, blob2.getLength());

    BlobBuilder builder3 = factory.createBlobBuilder();
    Blob blob3 = builder3.getAsBlob();
    assertEquals(0, blob3.getLength());

    BlobBuilder builder4 = factory.createBlobBuilder();
    builder4.append(blob1);
    builder4.append(blob2);
    builder4.append(blob3);
    Blob blob4 = builder4.getAsBlob();
    assertEquals(blob1.getLength() + blob2.getLength() + blob3.getLength(), blob4.getLength());

    BlobBuilder builder6 = factory.createBlobBuilder();
    builder6.append("\uCF8F");  // 3 bytes in utf8
    builder6.append("\u00A2");  // 2 bytes in utf8
    Blob blob6 = builder6.getAsBlob();
    assertEquals(3 + 2, blob6.getLength());

    // \uCF8F is 1100 1111 1000 1111 in UTF-16, which in UTF-8 is
    // iiio 1100 io11 1110 io00 1111, where 'i' and 'o' are ones and zeroes
    // introduced by the UTF-8 encoding, and '1' and '0' are the content.
    // Converting these three bytes to hex gives EC BE 8F, or 236 190 143.
    // Similarly, \u00A2 becomes iio0 0010 io10 0010, or C2 A2, or 194 162.
    byte[] bytes6 = blob6.getBytes(0, 5);
    assertEquals(5, bytes6.length);
    assertEquals((byte) 236, bytes6[0]);
    assertEquals((byte) 190, bytes6[1]);
    assertEquals((byte) 143, bytes6[2]);
    assertEquals((byte) 194, bytes6[3]);
    assertEquals((byte) 162, bytes6[4]);

    try {
      blob6.getBytes(-1, 5);
      fail("Offset must be a non-negative integer.");
    } catch (JavaScriptException jse) { }
    
    try {
      blob6.getBytes(1, 999999);
      fail("Length must be at most 1024.");
    } catch (JavaScriptException jse) { }
    
    try {
      blob6.getBytes(1, 999);
      fail("Read error during getBytes.");
    } catch (JavaScriptException jse) { }
    
    try {
      blob6.getBytes(999, 1);
      fail("Read error during getBytes.");
    } catch (JavaScriptException jse) { }

    byte[] bytes6a = blob6.slice(2).getBytes();
    byte[] bytes6b = blob6.getBytes(2);
    assertEquals(3, bytes6a.length);
    assertEquals(3, bytes6b.length);
    for (int i = 0; i < bytes6a.length; i++) {
      assertEquals(bytes6a[i], bytes6b[i]);
    }
  }
  
  public void testBlobBuilderAppendIntStringBlobArray() {
    // The integer values below map to ASCII characters like so:
    // A=65, B=66, C=67, D=68, E=69, F=70, G=71, H=72, I=73, J=74, ...,
    // W=87, X=88, Y=89, Z=90. Note that the second blob ends with 250,
    // which is outside the range of ASCII (i.e. it is not a valid UTF-8 string
    // and therefore not equivalent to any builder.append(string) call).
    Factory factory = Factory.getInstance();
    
    BlobBuilder builderAbcd = factory.createBlobBuilder();
    builderAbcd.append("AB");
    builderAbcd.append((byte) 67);
    builderAbcd.append((byte) (68 + 2560));
    Blob blobAbcd = builderAbcd.getAsBlob();
    byte[] bytesAbcd = blobAbcd.getBytes();
    assertEquals(4, blobAbcd.getLength());
    assertEquals((byte) 65, bytesAbcd[0]);
    assertEquals((byte) 66, bytesAbcd[1]);
    assertEquals((byte) 67, bytesAbcd[2]);
    assertEquals((byte) 68, bytesAbcd[3]);

    BlobBuilder builder = factory.createBlobBuilder();
    builder.append("W");
    builder.append("X");
    builder.append((byte) 89);
    builder.append(new String[] { "Z" });
    builder.append(new Blob[] { blobAbcd });
    builder.append(bytesAbcd);
    builder.append((byte) 69);
    builder.append("FG");
    builder.append((byte) 72);
    builder.append("I");
    builder.append((byte) 250);
    Blob blob = builder.getAsBlob();
    byte[] bytes = blob.getBytes();
    assertEquals(18, blob.getLength());

    assertEquals((byte) 87, bytes[0]);
    assertEquals((byte) 88, bytes[1]);
    assertEquals((byte) 89, bytes[2]);
    assertEquals((byte) 90, bytes[3]);
    assertEquals((byte) 65, bytes[4]);
    assertEquals((byte) 66, bytes[5]);
    assertEquals((byte) 67, bytes[6]);
    assertEquals((byte) 68, bytes[7]);
    assertEquals((byte) 65, bytes[8]);
    assertEquals((byte) 66, bytes[9]);
    assertEquals((byte) 67, bytes[10]);
    assertEquals((byte) 68, bytes[11]);
    assertEquals((byte) 69, bytes[12]);
    assertEquals((byte) 70, bytes[13]);
    assertEquals((byte) 71, bytes[14]);
    assertEquals((byte) 72, bytes[15]);
    assertEquals((byte) 73, bytes[16]);
    assertEquals((byte) 250, bytes[17]);
  }
}
