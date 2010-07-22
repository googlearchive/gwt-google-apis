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

/**
 * Classes for importing existing JavaScript APIs and exporting Java functions
 * to be used JavaScript code.
 * 
 * <p>
 * The GWT compiler can automatically generate:
 * <ul>
 * <li>Linkage to functions defined within a {@link
 * com.google.gwt.core.client.JavaScriptObject}</li>
 * <li>Exports of Java functions to be made available to JavaScript callers</li>
 * <li>Accessors for bean-style properties</li>
 * </ul>
 * </p>
 * <p>
 * Any method defined in an interface that extends {@link
 * com.google.gwt.jsio.client.JSWrapper} or an abstract method in a class that
 * implements {@link com.google.gwt.jsio.client.JSWrapper} will be acted upon by
 * the compiler. The default behavior is to invoke an identically-named method
 * on the underlying {@link com.google.gwt.core.client.JavaScriptObject}. This
 * behavior may be altered by the presence of various annotations on the class
 * and its methods.
 * </p>
 * 
 * <p>
 * The parameter and return types supported by JSWrapper are:
 * <ul>
 * <li>primitive</li>
 * <li>boxed primitive</li>
 * <li>{@link java.lang.String}</li>
 * <li>{@link com.google.gwt.jsio.client.JSFunction}</li>
 * <li>{@link com.google.gwt.jsio.client.JSWrapper}</li>
 * <li>{@link com.google.gwt.jsio.client.JSList} having a compatible generic
 * type</li>
 * <li>{@link com.google.gwt.core.client.JavaScriptObject} and subtypes</li>
 * <li>{@link com.google.gwt.jsio.client.JSOpaque} as a method parameter only.</li>
 * </ul>
 * </p>
 * 
 * <p>
 * In the general case, there may be at most one
 * {@link com.google.gwt.jsio.client.JSWrapper} instance associated with a
 * {@link com.google.gwt.core.client.JavaScriptObject}. This allows the
 * JSWrapper to maintain an object identity equivalent to that of the underlying
 * JavaScriptObject. This is done by adding an additional property,
 * <code>__gwtObject</code>, to the JavaScriptObject.
 * </p>
 * 
 * 
 * <h1>Examples</h1>
 * 
 * <h2>HelloJSIO</h2>
 * 
 * <pre>
 * class HelloJSIO {
 *   &#064;BeanProperties
 *   interface HelloWrapper extends JSWrapper {
 *     public String getHello();
 *   }
 * 
 *   public void hello() {
 *     HelloWrapper hello = GWT.create(HelloWrapper.class);
 *     hello.setJSONData(&quot;{hello:'Hello world'}&quot;);
 *     Window.alert(hello.getHello());
 *   }
 * }
 * </pre>
 * 
 * 
 * 
 * <h2>Finishing an abstract class</h2>
 * 
 * <pre>
 * &#064;BeanProperties
 * abstract class MixedWrapper implements JSWrapper {
 *   // Property accessor
 *   public abstract int getA();
 * 
 *   // Property accessor
 *   public abstract int getB();
 * 
 *   public int multiply() {
 *     return getA() * getB();
 *   }
 * 
 *   // This method would be imported
 *   public abstract int importedFunction(String s);
 * }
 * 
 * MixedWrapper wrapper = (MixedWrapper) GWT.create(MixedWrapper.class);
 * wrapper.setJSONData(&quot;{a:2, b:5}&quot;);
 * Window.alert(wrapper.multiply());
 * </pre>
 * 
 * would show you the value <code>10</code> .
 */
package com.google.gwt.jsio.client;

