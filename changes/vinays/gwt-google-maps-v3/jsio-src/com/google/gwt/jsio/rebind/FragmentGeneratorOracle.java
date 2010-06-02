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
package com.google.gwt.jsio.rebind;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;

/**
 * Returns FragmentGenerators.
 */
class FragmentGeneratorOracle {
  /**
   * All the types of FragmentGenerators that we know about.
   */
  private static final Class<?>[] classes = {
      LongFragmentGenerator.class, BoxedTypeFragmentGenerator.class,
      JavaScriptObjectFragmentGenerator.class,
      JSFunctionFragmentGenerator.class, JSListFragmentGenerator.class,
      PrimitiveFragmentGenerator.class, StringFragmentGenerator.class,
      JSOpaqueFragmentGenerator.class, JSWrapperFragmentGenerator.class,
      PeeringFragmentGenerator.class,

      /*
       * We don't actually support some types, but we can at least provide
       * useful error messages.
       */
      ArrayFragmentGenerator.class, JSFlyweightFragmentGenerator.class};

  /**
   * The List will always be checked in-order.
   */
  private List<FragmentGenerator> fragmentGenerators = new ArrayList<FragmentGenerator>();

  /**
   * Constructor.
   */
  public FragmentGeneratorOracle() {
    try {
      for (Class<?> clazz : classes) {
        fragmentGenerators.add(clazz.asSubclass(FragmentGenerator.class).newInstance());
      }
    } catch (InstantiationException e) {
      throw new RuntimeException(e);
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Finds a FragmentGenerator that can operate on a given type.
   * 
   * @param logger the context's TreeLogger
   * @param oracle the type system in use
   * @param type the type to generate fragments for
   * @return <code>null</code> if there is no FragmentGenerator for the
   *         specified type
   * @throws UnableToCompleteException if there is no registered generator
   */
  public FragmentGenerator findFragmentGenerator(TreeLogger logger,
      TypeOracle oracle, JType type) throws UnableToCompleteException {

    for (FragmentGenerator g : fragmentGenerators) {
      if (g.accepts(oracle, type)) {
        return g;
      }
    }

    logger.branch(TreeLogger.ERROR, "The type " + type.getQualifiedSourceName()
        + " cannot be processed by JSIO.", null);
    throw new UnableToCompleteException();
  }
}
