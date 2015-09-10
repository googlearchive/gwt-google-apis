This page will walk through a template project.  The template project is provided as a part of the Google API Library for GWT source tree.

## Directory structure conventions ##

The following tree structure shows the directory structure of a template gwt-google-apis project:

```
$PP_OFF
template
|-- build
|   |-- dist
|   |-- lib
|   `-- out
|       |-- distro-source
|       |-- doc
|       |   `-- javadoc
|       |-- template
|       |   |-- bin
|       |   |-- bin-test
|       `-- samples
|           `-- HelloTemplate
|               |-- bin
|               |-- launch-scripts
|               |   |-- linux
|               |   |-- mac
|               |   `-- windows
|               |-- src
|               `-- www
|-- build-tools
|-- distro-source
|   `-- src
|-- doc
|   `-- src
|-- eclipse
|   |-- template
|   |-- samples
|   |   `-- HelloTemplate
|   `-- settings
|       `-- code-style
|-- template
|   |-- javadoc
|   |-- src
|   `-- test
`-- samples
    `-- hellotemplate
        |-- launch-scripts
        |   |-- linux
        |   |-- mac
        |   `-- windows
        `-- src

```

| `template/` | Root of the project that should be named after the API |
|:------------|:-------------------------------------------------------|
| `template/build/` | A directory created to hold the results of the build   |
| `template/build-tools/` | An svn external reference to the GWT project's build-tools |
| `template/distro-source/` | Holds files that will be copied the root of the distribution.|
| `template/eclipse/` | Sample eclipse projects for the API and all samples.   |
| `template/eclipse/template` | API project.                                           |
| `template/eclipse/samples/HelloTemplate` | Example code that uses the API project file.           |
| `template/template/` | Top level dirctory for the API code.                   |
| `template/template/src/` | Contains the source for the API itself that will be packaged into a .jar. |
| `template/template/test/` |U nit test source for the API.                          |
| `template/samples/` | Example code that exercises the API.                   |
| `template/samples/hellotemplate/` | A directory containing one sample.                     |


## Package structure ##

The convention for the package for API code and test is

> `com.google.gwt.`_project name_`.client.`...

The convention for sample code is:

> `com.google.gwt.`_project name_`.sample.`_sample name_`.client`...

Inside your source code, if you have utility classes or classes needed to bind the JavaScript API to your GWT binding, the convention is to make a subdirectory under `.client` named `.impl`

> `com.google.gwt.`_project name_`.client.impl.`...


## Build requirements ##

  * (ant, GWT\_TOOLS)
  * Required ant targets
  * eclipse sample projects
  * Unit tests for wrapped functionality
  * samples