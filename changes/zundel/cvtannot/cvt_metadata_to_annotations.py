#!/usr/bin/python

#
# cvt_metadata_to_annotations.py - Convert JSIO src to use Java 1.5 annotations
#
# usage: cvt_metadata_to_annotations.py [--test] [--verbose] filename1 [filename 2 [...]]
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

options=[];
javadoc_start_re=re.compile('^\s*\/\*\*');
javadoc_end_re=re.compile('\*\/');

def main ():
  # read command line args
  p = optparse.OptionParser()
  p.add_option('--test', '-t', action='store_true')
  p.add_option('--verbose', '-v', action='store_true')
  options, files_to_convert = p.parse_args()


  #print "Options.test = ", options.test
  #print len(files_to_convert), " files to convert = ", files_to_convert


  # For each file to process:
  for curr_filename in files_to_convert:
    convert_file(curr_filename)

# Convert a single file
def convert_file (curr_filename):
    doc_changed = False
    
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
         
    # Update the file if it was modfied
    if len(file_contents) > 0 and doc_changed:
      # back up the old copy of the file
      os.rename(curr_filename, curr_filename + ".save")

      output_file = open(curr_filename, "w")
      for line in file_contents:
        output_file.writelines(line)
      output_file.close()

      # write out the in-memory copy that has been modified to the old filename.
      print curr_filename + ": doc modified"

# Slurp out a comment from the file
def slurp_javadoc_comment (curr_file, comment_start):
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
gwt_global_re = re.compile('\@gwt.global')
gwt_imported_re = re.compile('\@gwt.imported')
gwt_name_policy_re = re.compile('\@gwt.namePolicy\s+([^\s]+)')
gwt_no_identity_re = re.compile('\@gwt.noIdentity')
gwt_read_only_re = re.compile('\@gwt.readOnly')
# RE to identify a javadoc comment that is blank.
blank_comment_re = re.compile('\s*\/\*\*[\*\s]*\*\/')
comment_start_whitespace_re = re.compile('^(\s*)\/')

# Convert a comment containing JSIO metadata into a comment and annotations 
def convert_metadata(orig_comment):
  final_comment = []
  final_annotations=[]
  m = comment_start_whitespace_re.search(orig_comment[0])
  tabover=m.group(1)
  for curr_line in orig_comment:

    # If the line contains JSIO metadata, replace with an annotation
    #   add the new annotations

    if (gwt_binding_re.search(curr_line)):
      final_annotations.append(tabover + '@Binding'+"\n")
    elif gwt_exported_re.search(curr_line):
      final_annotations.append(tabover + '@Exported'+"\n")
    elif gwt_bean_properties_re.search(curr_line):
      final_annotations.append(tabover + '@BeanProperties'+"\n")
    elif gwt_constructor_re.search(curr_line):
      # I think there is a way to capture the result computed above, but
      # I don't know how to do it in Python, so recompute it. - EZA
      m = gwt_constructor_re.search(curr_line)
      final_annotations.append(tabover + '@Constructor("'+m.group(1)+'")'+"\n")
    elif gwt_field_name_re.search(curr_line):
      m = gwt_field_name_re.search(curr_line)
      final_annotations.append(tabover + '@FieldName("'+m.group(1)+'")'+"\n")
    elif gwt_global_re.search(curr_line):
      final_annotations.append(tabover + '@Global'+"\n")
    elif gwt_imported_re.search(curr_line):
      final_annotations.append(tabover + '@Imported'+"\n")
    elif gwt_name_policy_re.search(curr_line):
      m = gwt_name_policy_re.search(curr_line)
      final_annotations.append(tabover + '@NamePolicy("'+m.group(1)+'")'+"\n")
    elif gwt_no_identity_re.search(curr_line):
      final_annotations.append(tabover + '@NoIdentity'+"\n")
    elif gwt_read_only_re.search(curr_line):
      final_annotations.append(tabover + '@ReadOnly'+"\n")
    else:
        # Copy the unmodified javadoc comment to memory
        final_comment.append(curr_line)

  # If we stripped metadata out of a comment and the comment that
  # is left is completely blank, nuke the comment.
  if (len(final_annotations) > 0):
    comment_changed = True
    final_comment_string = reduce(lambda x,y: x+y, final_comment)
    if (blank_comment_re.search(final_comment_string)):
      #print ("EMPTY COMMENT FOUND: ", final_comment)
      final_comment = [];
  else:
    comment_changed = False

  # Nuke any comments that are now empty
  return final_comment + final_annotations, comment_changed

# Invoke the main entry point
main();
