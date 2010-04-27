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
package com.google.gwt.maps.client;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Tests the "GDraggableObject" wrapper classes.
 * 
 */
public class DraggableObjectTest extends MapsTestCase {

  private static native boolean nativeTestContainer(
      DraggableObjectOptions options, Element elem) /*-{
    return options.container === elem;
  }-*/;

  private static native boolean nativeTestDraggable(
      DraggableObjectOptions options, String value) /*-{
    return options.draggableCursor == value;
  }-*/;

  private static native boolean nativeTestDragging(
      DraggableObjectOptions options, String value) /*-{
    return options.draggingCursor == value;
  }-*/;

  private static native boolean nativeTestLeft(DraggableObjectOptions options,
      int value) /*-{
    return options.left == value;
  }-*/;

  private static native boolean nativeTestTop(DraggableObjectOptions options,
      int value) /*-{
    return options.top == value;
  }-*/;

  // TODO(zundel): These tests intermittently fail and I'm not sure why
  public void disableTestDraggableObjectStatic() {
    loadApi(new Runnable() {
      public void run() {
        String result;
        result = DraggableObject.getDraggingCursorDefault();
        assertNotNull("getDraggingCursorDefault is null", result);
        result = DraggableObject.getDraggableCursorDefault();
        assertNotNull("getDraggableCursorDefault is null", result);

        DraggableObject.setDraggableCursorDefault("text");
        assertEquals("setDraggableCursorDefault", "text",
            DraggableObject.getDraggableCursorDefault());
        DraggableObject.setDraggingCursorDefault("move");
        assertEquals("setDraggingCursorDefault", "move",
            DraggableObject.getDraggingCursorDefault());
        Element testElement = Document.get().createDivElement();
        DraggableObject dragObject = DraggableObject.newInstance(testElement);
        assertNotNull("newInstance(element)", dragObject);

        testElement = Document.get().createDivElement();
        DraggableObjectOptions options = DraggableObjectOptions.newInstance();
        dragObject = DraggableObject.newInstance(testElement, options);
        assertNotNull("newInstance(element)", dragObject);
      }
    });
  }

  @Override
  public String getModuleName() {
    return "com.google.gwt.maps.GoogleMapsTest";
  }

  /**
   * Runs before every test method.
   */
  @Override
  public void gwtSetUp() {
    TestUtilities.cleanDom();
  }

  public void testDraggableObject() {
    loadApi(new Runnable() {

      public void run() {
        MapWidget map = new MapWidget();
        map.setSize("300px", "300px");
        RootPanel.get().add(map);

        DraggableObject obj = map.getDragObject();
        assertNotNull("getDragObject", obj);
        obj.setDraggableCursor("text");
        obj.setDraggingCursor("crosshair");
      }
    });
  }

  public void testDraggableObjectOptions() {
    loadApi(new Runnable() {

      public void run() {
        DraggableObjectOptions options = DraggableObjectOptions.newInstance();
        Element testElement = Document.get().createDivElement();
        options.setContainer(testElement);
        assertTrue("container", nativeTestContainer(options, testElement));
        String val = "testDraggable";
        options.setDraggableCursor(val);
        assertTrue("draggable", nativeTestDraggable(options, "testDraggable"));
        val = "testDragging";
        options.setDraggingCursor(val);
        assertTrue("dragging", nativeTestDragging(options, "testDragging"));
        options.setLeft(1);
        assertTrue("left", nativeTestLeft(options, 1));
        options.setTop(2);
        assertTrue("right", nativeTestTop(options, 2));
      }
    });
  }
}