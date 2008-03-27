#!/usr/bin/python
#
# quick and dirty to write map widget event code

sourcename = "MapWidget"
prefix1 = "MapMoveEnd"
prefix2 = "mapMoveEnd"
mapeventname = "MOVEEND"
callbackmethod = "onMoveEnd"

imports_template = "\
import com.google.gwt.maps.client.event." + prefix1 + "Handler;\n\
import com.google.gwt.maps.client.event." + prefix1 + "Handler." + prefix1 + "Event;\n"

add_template = "\n\
  private HandlerCollection<" + prefix1 + "Handler> "  + prefix2 + "Handlers;\n\
\n\
  public void add" + prefix1 + "Handler(final " + prefix1 + "Handler handler) {\n\
    if (" + prefix2 + "Handlers == null) {\n\
      "+ prefix2 + "Handlers = new HandlerCollection<" + prefix1 + "Handler>(\n\
          jsoPeer, MapEvent." + mapeventname + ");\n\
    }\n\
\n\
    " + prefix2 + "Handlers.addHandler(handler, new VoidCallback() {\n\
      @Override\n\
      public void callback() {\n\
        " + prefix1 + "Event e = new " + prefix1 + "Event(" + sourcename + ".this);\n\
        handler." + callbackmethod + "(e);\n\
      }\n\
    }); \n\
  }\n\
\n"

clear_template="\
  /**\n\
   * Removes all handlers of this map added with\n\
   * {@link " + sourcename + "#add" + prefix1 + "Handler(" + prefix1 + "Handler)}.\n\
   */\n\
  public void clear" + prefix1 + "Handlers() {\n\
    if (" + prefix2 + "Handlers != null) {\n\
      " + prefix2 + "Handlers.clearHandlers();\n\
    }\n\
  }\n\
\n"

remove_template = "\
  /**\n\
   * Removes a single handler of this map previously added with\n\
   * {@link " + sourcename + "#add" + prefix1 + "Handler(" + prefix1 + "Handler)}.\n\
   * \n\
   * @param handler the handler to remove\n\
   */\n\
  public void remove" + prefix1 + "Handler(" + prefix1 + "Handler handler) {\n\
    if (" + prefix2 + "Handlers != null) {\n\
      " + prefix2 + "Handlers.removeHandler(handler);\n\
    }\n\
  }\n"

handler_template = "\
/*\n\
 * Copyright 2008 Google Inc.\n\
 * \n\
 * Licensed under the Apache License, Version 2.0 (the \"License\"); you may not\n\
 * use this file except in compliance with the License. You may obtain a copy of\n\
 * the License at\n\
 * \n\
 * http://www.apache.org/licenses/LICENSE-2.0\n\
 * \n\
 * Unless required by applicable law or agreed to in writing, software\n\
 * distributed under the License is distributed on an \"AS IS\" BASIS, WITHOUT\n\
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the\n\
 * License for the specific language governing permissions and limitations under\n\
 * the License.\n\
 */\n\
package com.google.gwt.maps.client.event;\n\
\n\
import com.google.gwt.maps.client." + sourcename + ";\n\
\n\
import java.util.EventObject;\n\
\n\
/**\n\
 * Provides an interface to implement in order to receive MapEvent."+ mapeventname + "events from the\n\
  * {@link " + sourcename + "}.\n\
 */\n\
public interface " + prefix1 + "Handler {\n\
\n\
  /**\n\
   * Encapsulates the arguments for the MapEvent." + mapeventname+ " event on a {@link " + sourcename + "}.\n\
   */\n\
  class " + prefix1 + "Event extends EventObject {\n\
\n\
    public " + prefix1 + "Event(" + sourcename + " source) {\n\
      super(source);\n\
    }\n\
\n\
    /**\n\
     * Returns the instance of the map that generated this event.\n\
     * \n\
     * @return the instance of the map that generated this event.\n\
     */\n\
    public " + sourcename + " getSender() {\n\
      return (" + sourcename + ") getSource();\n\
    }\n\
  }\n\
\n\
  /**\n\
   * Method to be invoked when a MapEvent." + mapeventname + " event fires on a {@link " + sourcename + "}.\n\
   * \n\
   * @param event contains the properties of the event.\n\
   */\n\
  void "+callbackmethod+"(" + prefix1 + "Event event);\n\
}\n"

print imports_template
print add_template
print clear_template
print remove_template
print
print handler_template
