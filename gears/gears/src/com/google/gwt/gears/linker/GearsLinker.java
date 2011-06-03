/*
 * Copyright 2011 Google Inc.
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

package com.google.gwt.gears.linker;

import com.google.gwt.core.ext.LinkerContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.linker.AbstractLinker;
import com.google.gwt.core.ext.linker.ArtifactSet;
import com.google.gwt.core.ext.linker.CompilationResult;
import com.google.gwt.core.ext.linker.DelegatingCompilationResult;
import com.google.gwt.core.ext.linker.LinkerOrder;
import com.google.gwt.core.ext.linker.LinkerOrder.Order;
import com.google.gwt.core.ext.linker.Shardable;
import com.google.gwt.core.ext.linker.StatementRanges;
import com.google.gwt.core.ext.linker.impl.StandardStatementRanges;
import com.google.gwt.dev.util.Util;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Prepends the contents of gears_init.js into the {@link CompilationResult}
 * artifacts.
 */
@LinkerOrder(Order.PRE)
@Shardable
public class GearsLinker extends AbstractLinker {

  private static class EnhancedCompilationResult extends DelegatingCompilationResult {
    private String[] javaScript = null;
    private final String jsToInject;
    private StatementRanges[] updatedStatementRanges = null;

    EnhancedCompilationResult(CompilationResult delegate, String jsToInject) {
      super(GearsLinker.class, delegate);
      this.jsToInject = jsToInject;
    }

    @Override
    public String[] getJavaScript() {
      if (javaScript == null) {
        String[] delegateJavaScript = super.getJavaScript().clone();
        assert delegateJavaScript.length > 0;
        javaScript = new String[delegateJavaScript.length];
        javaScript[0] = jsToInject + delegateJavaScript[0];
        for (int i = 1; i < delegateJavaScript.length; i++) {
          javaScript[i] = delegateJavaScript[i];
        }
      }
      return javaScript;
    }

    /**
     * Shuffle the statement range by the length of the added javascript.
     */
    @Override
    public StatementRanges[] getStatementRanges() {
      int offset = jsToInject.length();
      if (updatedStatementRanges == null && super.getStatementRanges() != null) {
        updatedStatementRanges = super.getStatementRanges().clone();
        // Stuff a new element into range[0] for gears_init.js
        ArrayList<Integer> starts = new ArrayList<Integer>();
        ArrayList<Integer> ends = new ArrayList<Integer>();
        starts.add(0);
        ends.add(offset);
        for (int i = 0; i < updatedStatementRanges[0].numStatements(); i++) {
          starts.add(updatedStatementRanges[0].start(i) + offset);
          ends.add(updatedStatementRanges[0].end(i) + offset);
        }
        updatedStatementRanges[0] = new StandardStatementRanges(starts, ends);
      }
      return updatedStatementRanges;
    }
  }

  @Override
  public String getDescription() {
    return "GearsLinker";
  }

  @Override
  public ArtifactSet link(TreeLogger logger, LinkerContext context, ArtifactSet artifacts,
      boolean onShard) throws UnableToCompleteException {

    if (!onShard) {
      return artifacts;
    }

    ArtifactSet updatedArtifacts = new ArtifactSet(artifacts);

    // find gears_init.js
    String initPath = "com/google/gwt/gears/linker/gears_init.js";
    InputStream gearsInitJs = getClass().getClassLoader().getResourceAsStream(initPath);

    if (gearsInitJs == null) {
      logger.log(TreeLogger.ERROR, "Internal Error: Unable to find " + initPath + " on classpath.");
      throw new UnableToCompleteException();
    }

    String jsToInject = Util.readStreamAsString(gearsInitJs);
    jsToInject = context.optimizeJavaScript(logger, jsToInject);

    // Prepend jsToInject to the JavaScript output.
    for (CompilationResult compilationResult : artifacts.find(CompilationResult.class)) {
      EnhancedCompilationResult enhancedResult =
          new EnhancedCompilationResult(compilationResult, jsToInject);
      updatedArtifacts.remove(compilationResult);
      updatedArtifacts.add(enhancedResult);
    }

    return updatedArtifacts;
  }
}
