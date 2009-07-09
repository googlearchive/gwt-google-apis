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
package com.google.gwt.gears.client;

import com.google.gwt.gears.client.blobbuilder.BlobBuilder;
import com.google.gwt.gears.client.canvas.Canvas;
import com.google.gwt.gears.client.database.Database;
import com.google.gwt.gears.client.localserver.LocalServer;
import com.google.gwt.gears.client.workerpool.WorkerPool;
import com.google.gwt.junit.client.GWTTestCase;

/**
 * Test cases for the {@link Factory} class.
 */
public class FactoryTest extends GWTTestCase {

  @Override
  public String getModuleName() {
    return "com.google.gwt.gears.Gears";
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.client.Factory#createBlobBuilder()}.
   */
  public void testCreateBlobBuilder() {
    Factory factory = Factory.getInstance();
    BlobBuilder builder = factory.createBlobBuilder();
    assertNotNull("Factory.createBlobBuilder() returned null", builder);
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.client.Factory#createCanvas()}.
   */
  public void testCreateCanvas() {
    Factory factory = Factory.getInstance();
    Canvas canvas = factory.createCanvas();
    assertNotNull("Factory.createCanvas() returned null", canvas);
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.client.Factory#createDatabase()}.
   */
  public void testCreateDatabase() {
    Factory factory = Factory.getInstance();
    Database database = factory.createDatabase();
    assertNotNull("Factory.createDatabase() returned null", database);
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.client.Factory#createLocalServer()}.
   */
  public void testCreateLocalServer() {
    Factory factory = Factory.getInstance();
    LocalServer localServer = factory.createLocalServer();
    assertNotNull("Factory.createLocalServer() returned null", localServer);
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.client.Factory#createWorkerPool()}.
   */
  public void testCreateWorkerPool() {
    Factory factory = Factory.getInstance();
    WorkerPool workerPool = factory.createWorkerPool();
    assertNotNull("Factory.createWorkerPool() returned null", workerPool);
  }

  /**
   * Test method for {@link com.google.gwt.gears.client.Factory#getBuildInfo()}.
   */
  public void testGetBuildInfo() {
    Factory factory = Factory.getInstance();
    assertNotNull("Factory.getBuildInfo() was null", factory.getBuildInfo());
  }

  /**
   * Test method for {@link com.google.gwt.gears.client.Factory#getInstance()}.
   */
  public void testGetInstance() {
    assertNotNull("Factory.getInstance() was null", Factory.getInstance());
  }

  /**
   * Test method for {@link com.google.gwt.gears.client.Factory#getVersion()}.
   */
  public void testGetVersion() {
    Factory factory = Factory.getInstance();
    assertNotNull("Factory.getVersion() was null", factory.getVersion());
  }

  /**
   * Test Method for {@link com.google.gwt.gears.client.Factory#hasPermission()} .
   */
  public void testHasPermission() {
    Factory factory = Factory.getInstance();
    assertTrue("Factory.hasPermission() was false", factory.hasPermission());
  }
}
