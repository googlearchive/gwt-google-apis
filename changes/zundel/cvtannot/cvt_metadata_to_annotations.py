#!/usr/bin/python2.4
# Requires the builtin 'set' type new to Python 2.4

# Copyright 2007 Google Inc.
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

#
# cvt_metadata_to_annotations.py - Convert GWT JSIO code to use Java annotations
#
# Finds any metadata comments that are used by JSIO and replaces them
# with the equivalent Annotation syntax.  For example.
#
# /**
#  * @gwt.constructor $wnd.MyObject
#  */
#
# is converted to
#
# @Constructor("$wnd.MyObject")
#
# (and the appropriate 'import' added to the file)
#
# If the 2nd line of the file looks like a Google copyright, the 
# copyright is updated to the current year.

# WARNING: Make sure you have a backup copy of your code before running this.
#          script.  It doesn't handle all cases approprately
#              - encoding a javadoc comment in a string
#              - a commented out javadoc comment.
# 
# When a file is found that needs converting, the old file is backed up to
# <filename>.save and the new file is re-written to <filename>
#

import optparse
import re
import os
import pdb # interactive debugger
import datetime
import sys

global options
options=[]
javadoc_start_re=re.compile('^\s*\/\*\*')
javadoc_end_re=re.compile('\*\/')
full_description="""
Example JSIO metadata:

   /**                                
    * @gwt.constructor $wnd.MyObject  
    */                                

 is converted to:

   @Constructor("$wnd.MyObject")

 - When a file is found that needs converting, the old file is backed up to
   <filename>.save and the new file is re-written to <filename>
 - The appropriate 'import' statements are added to the file.
 - If a javadoc comment is empty after the conversion, the comment is removed.
 - If the 2nd line of the file looks like a Google copyright, the
    copyright is updated to he current year.

 WARNING: Make sure you have a backup copy of your code before running this.
          script.  It doesn't handle all cases approprately:
              - encoding a javadoc comment in a string
              - a commented out javadoc comment.
"""


def main ():
  """The main entry point"""
  global options

  # read command line args
  p = optparse.OptionParser(description="""Converts GWT JSIO metadata 
specifications to Java 1.5 annotations.  Old files are backed up as 
<filename>.save if any data is changed. """)

  p.add_option('--usage', '-?', action='store_true', 
               help="Extended usage information")
  p.add_option('--test', '-t', action='store_true', 
               help='Test run only - do not change any files')
  p.add_option('--verbose', '-v', action='store_true', 
               help='Print messages while running')
  (options, files_to_convert) = p.parse_args()

  if options.usage:
    p.print_help()
    print full_description
    sys.exit()

  if options.verbose:
      print len(files_to_convert), " files to examine."

  # For each file to process:
  for curr_filename in files_to_convert:
    convert_file(curr_filename)


def convert_file (curr_filename):
    """ Convert a single file from metatdata to annotations"""
    global java_imports
    global options

    if options.verbose:
      print "Examining file ", curr_filename

    doc_changed = False
    java_imports = set([])
    
    # open the file for reading
    curr_file = open(curr_filename, mode='r')
    file_contents=[]
  
    # scan the file one line at a time.
    while True:
      curr_line = curr_file.readline()
      if len(curr_line) == 0:
        break;
      
      # if a javadoc start is encountered,
      if javadoc_start_re.search(curr_line):
        #print " ++++ got javadoc start: ", curr_line
        comment = slurp_javadoc_comment(curr_file, curr_line)
        comment, comment_changed = convert_metadata(comment)
        # print "Comment = ", comment
        result = file_contents.append(comment)
        if doc_changed == False:
           doc_changed = comment_changed
      else:
        # append the current line to copy in memory
        file_contents.append(curr_line)
         
    if doc_changed == False:
        return False

    # Update the file if it was modfied
    # merge in the new java imports
    file_contents = merge_imports(file_contents) 
      
    if options.test:
      print "Would have modified: ",curr_filename
      return False
    
    # back up the old copy of the file
    os.rename(curr_filename, curr_filename + ".save")
    
    output_file = open(curr_filename, "w")
    for line in file_contents:
      output_file.writelines(line)
    output_file.close()
      
    # write out the in-memory copy that has been modified to the old filename.
    if options.verbose:
      print "  Modified: ", curr_filename
      print "  Old file backed up to: ", curr_filename + ".save"

    return True

def slurp_javadoc_comment (curr_file, comment_start):
  """Slurp out a comment from the file"""
  comment=[comment_start];
  while True:
      curr_line = curr_file.readline()
      if len(curr_line) == 0:
        break;
      comment.append(curr_line);
      if javadoc_end_re.search(curr_line):
        break;
  return comment

# Regular expressions to identify JSIO metadata comments
gwt_binding_re = re.compile('\@gwt\.binding')
gwt_exported_re = re.compile('\@gwt\.exported')
gwt_bean_properties_re = re.compile('\@gwt.beanProperties')
gwt_constructor_re = re.compile('\@gwt.constructor\s+([^\s]+)')
gwt_field_name_re = re.compile('\@gwt.fieldName\s+([^\s]+)')
gwt_global_re = re.compile('\@gwt.global\s+([^\s]+)')
gwt_imported_re = re.compile('\@gwt.imported')
gwt_name_policy_re = re.compile('\@gwt.namePolicy\s+([^\s]+)')
gwt_no_identity_re = re.compile('\@gwt.noIdentity')
gwt_read_only_re = re.compile('\@gwt.readOnly')
# RE to identify a javadoc comment that is blank.
blank_comment_re = re.compile('\s*\/\*\*[\*\s]*\*\/')
comment_start_whitespace_re = re.compile('^(\s*)\/')


def convert_metadata(orig_comment):
  """Convert a comment containing JSIO metadata into a comment and annotations """
  global java_imports
  final_comment = []
  final_annotations=[]

  m = comment_start_whitespace_re.search(orig_comment[0])
  tabover=m.group(1)
  for curr_line in orig_comment:

    # If the line contains JSIO metadata, replace with an annotation
    #   add the new annotations
    if (gwt_binding_re.search(curr_line)):
      final_annotations.append(tabover + '@Binding'+"\n")
      java_imports.add("import com.google.gwt.jsio.client.Binding;\n")
    elif gwt_exported_re.search(curr_line):
      final_annotations.append(tabover + '@Exported'+"\n")
      java_imports.add("import com.google.gwt.jsio.client.Exported;\n")
    elif gwt_bean_properties_re.search(curr_line):
      final_annotations.append(tabover + '@BeanProperties'+"\n")
      java_imports.add("import com.google.gwt.jsio.client.BeanProperties;\n")
    elif gwt_constructor_re.search(curr_line):
      # I think there is a way to capture the result computed above, but
      # I don't know how to do it in Python, so recompute it. - EZA
      m = gwt_constructor_re.search(curr_line)
      final_annotations.append(tabover + '@Constructor("'+m.group(1)+'")'+"\n")
      java_imports.add("import com.google.gwt.jsio.client.Constructor;\n")
    elif gwt_field_name_re.search(curr_line):
      m = gwt_field_name_re.search(curr_line)
      final_annotations.append(tabover + '@FieldName("'+m.group(1)+'")'+"\n")
      java_imports.add("import com.google.gwt.jsio.client.FieldName;\n")
    elif gwt_global_re.search(curr_line):
      m = gwt_global_re.search(curr_line)
      final_annotations.append(tabover + '@Global("'+m.group(1)+'")'+"\n")
      java_imports.add("import com.google.gwt.jsio.client.Global;\n")
    elif gwt_imported_re.search(curr_line):
      final_annotations.append(tabover + '@Imported'+"\n")
      java_imports.add("import com.google.gwt.jsio.client.Imported;\n")
    elif gwt_name_policy_re.search(curr_line):
      m = gwt_name_policy_re.search(curr_line)
      final_annotations.append(tabover + '@NamePolicy("'+m.group(1)+'")'+"\n")
      java_imports.add("import com.google.gwt.jsio.client.NamePolicy;\n")
    elif gwt_no_identity_re.search(curr_line):
      final_annotations.append(tabover + '@NoIdentity'+"\n")
      java_imports.add("import com.google.gwt.jsio.client.NoIdentity;\n")
    elif gwt_read_only_re.search(curr_line):
      final_annotations.append(tabover + '@ReadOnly'+"\n")
      java_imports.add("import com.google.gwt.jsio.client.ReadOnly;\n")
    else:
        # Copy the unmodified javadoc comment to memory
        final_comment.append(curr_line)

  # If we stripped metadata out of a comment and the comment that
  # is left is completely blank, nuke the comment.
  if (len(final_annotations) > 0):
    comment_changed = True
    final_comment_string = reduce(lambda x,y: x+y, final_comment)
    if (blank_comment_re.search(final_comment_string)):
      final_comment = [];
  else:
    comment_changed = False

  # Nuke any comments that are now empty
  return final_comment + final_annotations, comment_changed

copyright_re = re.compile('^ \* Copyright 20\d\d Google Inc')

def merge_imports(contents):
  """Merge in the imports in java_imports into the file  below the last
  imports already declared."""

  global java_imports
  global options

  # Sort the imports and put them in a list
  #   (there's got to be a simpler way to convert a set to a list)
  sorted_imports = []
  [ sorted_imports.append(i) for i in java_imports ]
  sorted_imports.sort()

  # Scan the file_contents from the end of the file until the last 'import'
  # line is found.
  curr_lineno = filelen = len(contents)

  copyright_line = contents[1];
  if copyright_re.match(str(copyright_line)):
    now = datetime.date.today();
    contents[1] = " * Copyright " + str(now.year) + " Google Inc.\n"

  while curr_lineno > 0:
    curr_lineno = curr_lineno - 1
    curr_line = contents[curr_lineno] # should be a string
    if str(curr_line).startswith("import "):
       break;

  # This the place to merge together the imports
  top = contents[:curr_lineno + 1];
  bottom = contents[curr_lineno + 1:]      
  return top + sorted_imports + bottom

# Invoke the main entry point
main();
