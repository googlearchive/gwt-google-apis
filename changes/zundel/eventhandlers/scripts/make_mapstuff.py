#!/usr/bin/python
#
# quick and dirty to write map widget event code

# Copyright 2008 Google Inc.
# 
# Licensed under the Apache License, Version 2.0 (the "License"); you may not
# use this file except in compliance with the License. You may obtain a copy of
# the License at
# 
# http://www.apache.org/licenses/LICENSE-2.0
# 
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
# WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
# License for the specific language governing permissions and limitations under
# the License.


import optparse
import re
import os
import pdb # interactive debugger
import datetime
import sys


global options
options=[]
global sourcename
sourcename = "MapWidget"
#sourcename = "Marker"
#sourcename = "InfoWindow"

global stripprefix
stripprefix = "Map"
#stripprefix = "Marker"
#stripprefix = "InfoWindow"

package="com.google.gwt.maps.client"

def main ():
  """The main entry point"""
  global options

  # read command line args
  p = optparse.OptionParser(description="""Create map events implementation""");

  p.add_option('--verbose', '-v', action='store_true', 
               help='Print messages while running')
  (options, event_input_filename) = p.parse_args()

  if len(event_input_filename) !=  1:
    print "Expected filenaming containing events handlers to create as last argument";
    p.print_help()
    sys.exit()

  events=[]

  eventfile=open(event_input_filename[0]);
  while True:
      curr_line = eventfile.readline()
      if len(curr_line) == 0:
             break;
      events.append(curr_line.rstrip())
  eventfile.close()

  process_events(events)
  return
 
def process_events (events):
  """Process the array of events read in from the file"""
  global sourcename
  global options
  global stripprefix

  imports = ""
  add_handlers = ""
  clear_handlers = ""
  remove_handlers = ""
  trigger_handlers = ""
 
  for event in events:
    # The name of the event looks like: "MapMoveStart"  Create variations of
    # capitalization and suffixes using this name
    prefix1 = event
    if options.verbose:
      print "prefix1 is ", prefix1
    prefix2 = event
    prefix2 = event[:1].lower() + event[1:]
    if options.verbose:
      print "prefix2 is ", prefix2

    # strip off the prefix used to differentiate the source of the event.
    if event.startswith(stripprefix):
      tmpsuffix = event[len(stripprefix):]
    else:
      tmpsuffix = event

    mapeventname = tmpsuffix.upper()
    if options.verbose:
      print "mapeventname is ", mapeventname
    callbackmethod = "on" + tmpsuffix
    if options.verbose:
      print "callbackmethod is ", callbackmethod
          
    imports += "\
import com.google.gwt.maps.client.event." + prefix1 + "Handler;\n\
import com.google.gwt.maps.client.event." + prefix1 + "Handler." + prefix1 + "Event;\n"

    add_handlers += "\n\
  private HandlerCollection<" + prefix1 + "Handler> "  + prefix2 + "Handlers;\n\
\n\
 /**\n\
  *\n\
  *\n\
  * @param handler the handler to call when this event fires.\n\
  */\n\
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

    clear_handlers += "\
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

    remove_handlers += "\
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

    trigger_handlers += "\
  /**\n\
   * Manually trigger the specified event on this object.\n\
   * \n\
   * @param event an event to deliver to the handler.\n\
   */\n\
  public void trigger(" + prefix1 + "Event event) {\n\
    " + prefix2 + "Handlers.trigger();\n\
  }\n\
\n"


    handler = "\
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
import " + package + "." + sourcename + ";\n\
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
    handler_file = open (prefix1+"Handler.java","w");
    handler_file.write(handler);
    handler_file.close();
    
  sourcefile = open (sourcename + ".java.imports", "w");
  sourcefile.write(imports);
  sourcefile.close();

  sourcefile = open (sourcename + ".java.methods", "w");
  sourcefile.write(add_handlers);
  sourcefile.write(clear_handlers);
  sourcefile.write(remove_handlers);
  sourcefile.write(trigger_handlers);
  sourcefile.close();
  
# Invoke the main entry point
main()
sys.exit()
