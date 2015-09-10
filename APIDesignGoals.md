## Design Goals of API bindings ##

The AJAX Search, Gadgets, Gears, and Maps APIs were written with the following design goals in mind.


  * **Take JavaScript constructs and make them easy to use for Java developers to use.**

Primarily, this means using Java's type system to help prevent the user from making coding mistakes.  One example of this can be found in the Event system used in the Maps API.  A decision was made to create a separate Handler class and Event class for each type of event, so that creating a new event with an IDE would allow the IDE to easily create skeleton code.

  * **Javadoc should stand on its own.**

We copied the documentation of the JavaScript API into the javadoc comments of the methods so that the users of the API could find the information easily, and not have to refer to both documents and guess which JavaScript method or methods mapped to a Java method.

  * **The API should be small. Optimizable in terms of resulting code size.**

In general we did not create lots of value added functionality on top of the API.  This is to provide a light-weight binding when compiled which should result in smaller downloads of the applications that use the API.

In general, a well written API that meets this design goal should be able to optimize out most of the code in it if only a small subset of the API is referenced by the developer.

  * **Wrappers should not incur a large runtime cost.**

The bindings should not try to keep large tables or perform excessive calculations, but instead provide as direct a route as possible to the functionality provided by the JavaScript API.

  * **Future proof API components that are likely to change.**

Many APIs provided by Google have new functionality released on a regular basis.  We want the bindings to be able to add in this new functionality in a backward compatible way.  One of the biggest differences between JavaScript and Java is that it is quite easy for a JavaScript developer to simply tack on new method arguments and not break functionality.  In Java, this can lead to trouble, especially when that method is declared as a part of an `interface`, which is common for  callback methods.  See [Binding design guidelines](DesigningAPIWrappers.md) for a discussion of the Event Handler pattern.

  * **Not relying on external libraries.**

_TBD: Mention the decision to copy JSIO library into API bindings._