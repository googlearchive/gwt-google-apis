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
package com.google.gwt.visualization.client;

import com.google.gwt.junit.client.GWTTestCase;

/**
 * Tests the Selection class.
 */
public class SelectionTest extends GWTTestCase {
  @Override
  public String getModuleName() {
    return "com.google.gwt.visualization.VisualizationTest";
  }
  
  public void testCell() {
    Selection selection;
    
    selection = Selection.createCellSelection(0, 0);
    assertTrue(selection.isCell());
    assertFalse(selection.isColumn());
    assertFalse(selection.isRow());
    assertEquals(0, selection.getColumn());
    assertEquals(0, selection.getRow());
    
    selection = Selection.createCellSelection(1, 1);
    assertTrue(selection.isCell());
    assertFalse(selection.isColumn());
    assertFalse(selection.isRow());
    assertEquals(1, selection.getColumn());
    assertEquals(1, selection.getRow());
  }

  public void testColumn() {
    Selection selection;
    
    selection = Selection.createColumnSelection(0);
    assertTrue(selection.isColumn());
    assertFalse(selection.isCell());
    assertFalse(selection.isRow());
    assertEquals(0, selection.getColumn());
    
    selection = Selection.createColumnSelection(1);
    assertTrue(selection.isColumn());
    assertFalse(selection.isCell());
    assertFalse(selection.isRow());
    assertEquals(1, selection.getColumn());
  }

  public void testRow() {
    Selection selection;
        
    selection = Selection.createRowSelection(0);
    assertTrue(selection.isRow());
    assertFalse(selection.isCell());
    assertFalse(selection.isColumn());
    assertEquals(0, selection.getRow());
    
    selection = Selection.createRowSelection(1);
    assertTrue(selection.isRow());
    assertFalse(selection.isCell());
    assertFalse(selection.isColumn());
    assertEquals(1, selection.getRow());
  }
}
