# Introduction #

This document covers the conversion of the Google API Library for GWT from a single monolithic JAR, into a set of API specific jars that can be developed and released independently.

# Goals #
  * Allow GWT bindings to be developed and released independently.

# Directory Layout for the Project as Whole #
Every API will live in its own directory under the project root.  The root directory should have the following layout:
  * common.ant.xml - Common build rules that APIs can use.
  * build - APIs will emit their build artifacts into directories under this location
  * build-tools - This directory contains GWT's custom checkstyle checks, and gwt-specific ant extensions.  These rarely change, but an APIs can always provide their own version if necessary.
  * ajaxsearch
  * gadgets
  * gears
  * maps
  * ... other APIs

## Directory Layout for Each API ##
Each API will live in its own directory under the project directory.  It is assumed that the invoking ant in that directory will produce an image suitable for distribution.  The layout of this directory is assumed to be as follows:
  * build.xml - This file contains all of the rules needed to produce an image suitable for distribution.
  * distro-source - Resource to include in the distribution
  * doc
  * eclipse - eclipse project files
  * (gwt bindings source code)
  * samples - sample code for the API

# Directory Layout for an API Distribution #
This is the basic layout for each of the distributable images for an API.
  * COPYING
  * COPYING.html
  * about.txt
  * about.html
  * doc
    * javadoc
  * api\_specific.jar
  * release\_notes.html
  * samples

# Build ant script targets for the Project as a Whole #
A build script will be included at the project root.  Invoking it with any of the standard ant targets will cause that target to be invoked for all APIs.
  * build (default)
  * checkstyle
  * clean
  * doc
  * samples
  * test
    * test.hosted
    * test.remoteweb
    * test.web

## Build ant script targets for each API ##
These are inherited from the top level build script.  These ant script should be able to run independently of the top level ant build script.

# Issue Tracking #
  * Issues should be labeled with an API specific label - maps for JS maps, gears for gears, etc.

# Open Issues #
  * Provide a devdocreader documentation template?
  * Provide template build scripts for new APIs
  * What should the COPYING files be for each top distro?
  * Need to provide instructions/script for setting up a template directory for a new project.

# Resolved Issues #
  * Make sure that APIs that depend on different versions of JSIO can play nicely with each other.
    * We chose to rebase (changed the package names) of the JSIO library statically and include the source in the distro.  We will consider build time rebasing or simply using JSIO as a JAR post the milestone.