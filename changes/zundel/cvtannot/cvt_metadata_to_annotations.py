#!/usr/bin/python

#
# cvt_metadata_to_annotations.py - Convert JSIO src to use Java 1.5 annotations
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

import optparse
import re;

options=[];
javadoc_start_re=re.compile('^\s*\/\*\*');
javadoc_end_re=re.compile('^.*\*\/');

def main ():
  # read command line args
  p = optparse.OptionParser()
  p.add_option('--test', '-t', action='store_true')
  options, files_to_convert = p.parse_args()


  #print "Options.test = ", options.test
  #print len(files_to_convert), " files to convert = ", files_to_convert


  # For each file to process:
  for curr_filename in files_to_convert:
    convert_file(curr_filename)

# Convert a single file
#
def convert_file (curr_filename):

    
    # open the file for reading
    curr_file = open(curr_filename, mode='r')
    file_contents=[]
  
    # scan the file one line at a time.
    while True:
      curr_line = curr_file.readline()
      if len(curr_line) == 0:
        break;
      
      # if a javadoc start is encountered,
      if javadoc_start_re.match(curr_line):
        print " ++++ got javadoc start: ", curr_line
        comment = slurp_javadoc_comment(curr_file, curr_line)
        comment = convert_metadata(comment)
        file_contents.append(comment)
      else:
         # append the current line to copy in memory
         file_contents.append(curr_line)
         
    # If the file was modfied
    # back up the old copy of the file
    # write out the in-memory copy that has been modified to the old filename.


# Slurp out a comment from the file
def slurp_javadoc_comment (curr_file, comment_start):
  comment=[comment_start];
  while True:
      curr_line = curr_file.readline()
      if len(curr_line) == 0:
        break;
      comment.append(curr_line);
      if javadoc_end_re.match(curr_line):
        break;
  print "Comment = ", comment
  return comment




#
def convert_metadata(orig_comment):
  final_comment = []
  for curr_line in orig_comment:
     # If the line contains JSIO metadata:
             # pull JSIO metatdata out of the comment
             # copy what remains of the comment to memory
             # add the new annotations
             # mark that the document has been modified
    #      } else {
             # Copy the unmodified javadoc comment to memory
             final_comment.append(curr_line)
  return final_comment

main();
