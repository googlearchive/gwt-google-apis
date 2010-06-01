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
package com.google.gwt.gears;

import com.google.gwt.gears.client.FactoryTest;
import com.google.gwt.gears.client.blobbuilder.BlobBuilderTest;
import com.google.gwt.gears.client.canvas.CanvasTest;
import com.google.gwt.gears.client.database.DatabaseTest;
import com.google.gwt.gears.client.database.ResultSetTest;
import com.google.gwt.gears.client.localserver.LocalServerTest;
import com.google.gwt.gears.client.localserver.ManagedResourceStoreTest;
import com.google.gwt.gears.client.workerpool.WorkerPoolTest;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * TestSuite for the Gears API.
 */
public class GearsTestSuite extends TestCase {
  public static Test suite() {
    TestSuite suite = new TestSuite("Test for the Gears API");

    suite.addTestSuite(DatabaseTest.class);
    // FactoryTest.hasPermission() fails without presenting a dialog.  This makes
    // it difficult to actually grant permission during unit tests!  Moved after 
    // another test to workaround.
    suite.addTestSuite(FactoryTest.class);
    suite.addTestSuite(BlobBuilderTest.class);
    suite.addTestSuite(CanvasTest.class);
    suite.addTestSuite(ResultSetTest.class);
    suite.addTestSuite(LocalServerTest.class);
    suite.addTestSuite(ResultSetTest.class);
    suite.addTestSuite(ManagedResourceStoreTest.class);
    suite.addTestSuite(WorkerPoolTest.class);
    return suite;
  }
}
