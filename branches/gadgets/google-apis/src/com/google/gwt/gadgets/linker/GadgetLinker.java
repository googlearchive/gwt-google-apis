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
package com.google.gwt.gadgets.linker;

import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.dev.linker.GeneratedResource;
import com.google.gwt.dev.linker.LinkerContext;
import com.google.gwt.dev.linker.XSLinker;
import com.google.gwt.dev.util.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Finalizes the module manifest file with the selection script.
 */
public final class GadgetLinker extends XSLinker {

  @Override
  public String getDescription() {
    return "Google Gadget";
  }

  @Override
  protected void doEmitGeneratedResource(TreeLogger logger,
      LinkerContext context, GeneratedResource resource)
      throws UnableToCompleteException {
    // Mask the stub manifest created by the generator
    if (resource.getPartialPath().endsWith(".gadget.xml")) {
      return;
    }

    super.doEmitGeneratedResource(logger, context, resource);
  }

  @Override
  protected void emitSelectionScript(TreeLogger logger, LinkerContext context)
      throws UnableToCompleteException {
    logger = logger.branch(TreeLogger.DEBUG, "Building gadget manifest", null);

    String bootstrap = "<script>"
        + context.optimizeJavaScript(logger, generateSelectionScript(logger,
            context)) + "</script>";

    for (GeneratedResource manifestResource : context.getGeneratedResources()) {
      // Find the stub manifests
      if (!manifestResource.getPartialPath().endsWith((".gadget.xml"))) {
        continue;
      }

      // Read the contents
      StringBuffer manifest = new StringBuffer();
      try {
        BufferedReader in = new BufferedReader(new InputStreamReader(
            manifestResource.tryGetResourceAsStream(logger)));
        for (String line = in.readLine(); line != null; line = in.readLine()) {
          manifest.append(line).append("\n");
        }
        in.close();
      } catch (IOException e) {
        logger.log(TreeLogger.ERROR, "Unable to read manifest stub", e);
        throw new UnableToCompleteException();
      }

      replaceAll(manifest, "__BOOTSTRAP__", bootstrap);

      doEmit(logger, context, Util.getBytes(manifest.toString()),
          manifestResource.getPartialPath());
    }
  }

  @Override
  protected String generateScriptInjector(String scriptUrl) {
    if (isRelativeURL(scriptUrl)) {
      return "  if (!__gwt_scriptsLoaded['"
          + scriptUrl
          + "']) {\n"
          + "    __gwt_scriptsLoaded['"
          + scriptUrl
          + "'] = true;\n"
          + "    document.write('<script language=\\\"javascript\\\" src=\\\"'+_IG_GetCachedUrl(base+'"
          + scriptUrl + "') + '\\\"></script>');\n" + "  }\n";
    } else {
      return "  if (!__gwt_scriptsLoaded['"
          + scriptUrl
          + "']) {\n"
          + "    __gwt_scriptsLoaded['"
          + scriptUrl
          + "'] = true;\n"
          + "    document.write('<script language=\\\"javascript\\\" src=\\\"'+_IG_GetCachedUrl('"
          + scriptUrl + "') + '\\\"></script>');\n" + "  }\n";
    }
  }

  @Override
  protected String generateStylesheetInjector(String stylesheetUrl) {
    if (isRelativeURL(stylesheetUrl)) {
      return "  if (!__gwt_stylesLoaded['"
          + stylesheetUrl
          + "']) {\n"
          + "    __gwt_stylesLoaded['"
          + stylesheetUrl
          + "'] = true;\n"
          + "    document.write('<link rel=\\\"stylesheet\\\" href=\\\"'+_IG_GetCachedUrl(base+'"
          + stylesheetUrl + "') + '\\\">');\n" + "  }\n";
    } else {
      return "  if (!__gwt_stylesLoaded['"
          + stylesheetUrl
          + "']) {\n"
          + "    __gwt_stylesLoaded['"
          + stylesheetUrl
          + "'] = true;\n"
          + "    document.write('<link rel=\\\"stylesheet\\\" href=\\\"'+_IG_GetCachedUrl('"
          + stylesheetUrl + "') + '\\\">');\n" + "  }\n";
    }
  }

  @Override
  protected String getSelectionScriptTemplate(TreeLogger logger,
      LinkerContext context) {
    return "com/google/gwt/gadgets/linker/GadgetTemplate.js";
  }

}
