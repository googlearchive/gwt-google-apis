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
package com.google.gwt.gears.offline.linker;

import com.google.gwt.core.ext.LinkerContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.linker.AbstractLinker;
import com.google.gwt.core.ext.linker.ArtifactSet;
import com.google.gwt.core.ext.linker.EmittedArtifact;
import com.google.gwt.core.ext.linker.LinkerOrder;
import com.google.gwt.core.ext.linker.LinkerOrder.Order;
import com.google.gwt.dev.util.Util;
import com.google.gwt.util.tools.Utility;
import com.google.gwt.util.tools.shared.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Generates a Google Gears manifest file for use with a ManagedResourceStore.
 * The generated manifest will have a partial path of
 * <code>moduleName.nocache.manifest</code>. If it exists, the
 * {@link EmittedArtifact} with a partial path of {@value #GEARS_MANIFEST} will
 * be used as a template for generating the final manifest.
 * 
 * @see com.google.gwt.gears.offline.client.Offline for a description of the
 *      manifest template.
 */
@LinkerOrder(Order.POST)
public final class GearsManifestLinker extends AbstractLinker {

  /**
   * The message digest; it's highly unlikely md5 is unsupported.
   */
  private static final String DIGEST_ALGORITHM = "MD5";

  /**
   * Used to extract the filter pragmas.
   */
  private static final Pattern FILTER_PATTERN = Pattern.compile(
      "@filter (.*)$", Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
  private static final String GEARS_MANIFEST = "GearsManifest.json";
  private static final String[] BUILTIN_FILTERS = { 
    ".*\\.gwt\\.rpc", // Causes problems with AppEngine, see issue 280 
  };

  private static void replaceAll(StringBuffer buf, String search, String replace) {
    int len = search.length();
    for (int pos = buf.indexOf(search); pos >= 0; pos = buf.indexOf(search,
        pos + 1)) {
      buf.replace(pos, pos + len, replace);
    }
  }

  /**
   * The user-provide manifest template is filtered before before resources are
   * written to disk.
   */
  EmittedArtifact userManifest = null;

  /**
   * Use all bytes written to the output to determine the manifest's version.
   * Gears don't really care about the value of the version field, just that it
   * may change from time to time.
   */
  private final MessageDigest digester;

  public GearsManifestLinker() throws NoSuchAlgorithmException {
    digester = MessageDigest.getInstance(DIGEST_ALGORITHM);
  }

  @Override
  public String getDescription() {
    return "Google Gears manifest linker";
  }

  @Override
  public ArtifactSet link(TreeLogger logger, LinkerContext context,
      ArtifactSet artifacts) throws UnableToCompleteException {
    ArtifactSet toReturn = new ArtifactSet(artifacts);

    SortedSet<EmittedArtifact> emitted = toReturn.find(EmittedArtifact.class);

    for (EmittedArtifact artifact : emitted) {
      if (artifact.getPartialPath().equals(GEARS_MANIFEST)) {
        userManifest = artifact;
        toReturn.remove(artifact);
        emitted.remove(artifact);
        break;
      }
    }

    toReturn.add(emitManifest(logger, context, userManifest, emitted));

    return toReturn;
  }

  private EmittedArtifact emitManifest(TreeLogger logger,
      LinkerContext context, EmittedArtifact userManifest,
      SortedSet<EmittedArtifact> artifacts) throws UnableToCompleteException {
    logger = logger.branch(TreeLogger.DEBUG, "Creating manifest artifact", null);

    // Try getting a user-defined manifest
    StringBuffer out = readManifestTemplate(logger, userManifest);

    // Use the template in the MD5 computation
    digester.update(Util.getBytes(out.toString()));

    // Look for @filter expressions in the manifest template
    Set<Pattern> filters = extractFilters(logger, out);

    // Append the builtin filters
    for (String pattern : BUILTIN_FILTERS) {
      filters.add(Pattern.compile(pattern));
    }

    // Generate the manifest entries
    String entries = generateEntries(logger, context, filters, artifacts);

    replaceAll(out, "__VERSION__", StringUtils.toHexString(digester.digest()));
    replaceAll(out, "__ENTRIES__", entries.toString());

    /*
     * NB: It's tempting to use LinkerContext.optimizeJavaScript here, but the
     * JSON standard requires that the keys in the object literal will be
     * enclosed in double-quotes. In our optimized JS form, the double-quotes
     * would normally be removed.
     */
    return emitBytes(logger, Util.getBytes(out.toString()),
        context.getModuleName() + ".nocache.manifest");
  }

  /**
   * Find all instances of the filter pragma in the manifest template and return
   * compiled regular expression Pattern objects.
   */
  private Set<Pattern> extractFilters(TreeLogger logger, CharSequence source)
      throws UnableToCompleteException {
    logger.branch(TreeLogger.DEBUG, "Finding @filter expressions", null);

    boolean filterError = false;
    Matcher filterMatcher = FILTER_PATTERN.matcher(source);
    Set<Pattern> filters = new HashSet<Pattern>();

    while (filterMatcher.find()) {
      String pattern = filterMatcher.group(1);
      try {
        filters.add(Pattern.compile(pattern));
      } catch (PatternSyntaxException e) {
        logger.log(TreeLogger.ERROR,
            "Could not compile filter pattern at character offset "
                + filterMatcher.start(), e);
        filterError = true;
      }
    }

    if (filterError) {
      throw new UnableToCompleteException();
    }

    return filters;
  }

  /**
   * Generate a string containing object literals for each manifest entry.
   */
  private String generateEntries(TreeLogger logger, LinkerContext context,
      Set<Pattern> filters, SortedSet<EmittedArtifact> artifacts)
      throws UnableToCompleteException {
    logger = logger.branch(TreeLogger.DEBUG, "Generating manifest contents",
        null);

    StringBuffer entries = new StringBuffer();
    boolean needsComma = false;
    paths : for (EmittedArtifact artifact : artifacts) {
      if (artifact.isPrivate()) {
        // These artifacts won't be in the module output directory
        continue;
      }

      String path = artifact.getPartialPath();
      for (Pattern p : filters) {
        if (p.matcher(path).matches()) {
          logger.log(TreeLogger.DEBUG, "Filtering resource " + path, null);
          continue paths;
        }
      }

      if (needsComma) {
        entries.append(",\n");
      } else {
        needsComma = true;
      }

      entries.append("{ \"url\" : \"");
      entries.append(path);
      entries.append("\" }");

      // Read the artifact into the digester
      InputStream in = artifact.getContents(logger);
      byte[] buffer = new byte[4096];
      int read;
      try {
        while ((read = in.read(buffer)) != -1) {
          digester.update(buffer, 0, read);
        }
      } catch (IOException e) {
        logger.log(TreeLogger.ERROR, "Unable to read artifact "
            + artifact.getPartialPath(), e);
        throw new UnableToCompleteException();
      }
    }

    // Add an alias for Module.nocache.js?compiled to support hosted-mode
    entries.append(",\n{ \"url\" : \"" + context.getModuleName()
        + ".nocache.js?compiled\" }");

    return entries.toString();
  }

  /**
   * Load the contents of the manifest template from a file named
   * {@value #GEARS_MANIFEST} in the root of the public path. Failing that, use
   * the built-in template.
   */
  private StringBuffer readManifestTemplate(TreeLogger logger,
      EmittedArtifact userManifest) throws UnableToCompleteException {
    logger = logger.branch(TreeLogger.DEBUG, "Reading manifest template", null);

    InputStream in;

    // See if we have a user-provided manifest to work with
    if (userManifest != null) {
      logger.log(TreeLogger.DEBUG, "Reading user-provided manifest", null);
      in = userManifest.getContents(logger);
      if (in == null) {
        logger.log(TreeLogger.ERROR,
            "Unable to read contents of user manifest", null);
        throw new UnableToCompleteException();
      }

    } else {
      // Fall back to the built-in manifest
      String packagePath = getClass().getPackage().getName().replace('.', '/');
      String resourceName = packagePath + "/" + GEARS_MANIFEST;
      in = getClass().getClassLoader().getResourceAsStream(resourceName);
      if (in == null) {
        logger.log(TreeLogger.ERROR, "Could not load built-in manifest from "
            + resourceName, null);
        throw new UnableToCompleteException();
      }
    }

    StringBuffer out = new StringBuffer();
    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    try {
      for (String line = reader.readLine(); line != null; line = reader.readLine()) {
        out.append(line).append("\n");
      }
    } catch (IOException e) {
      logger.log(TreeLogger.ERROR, "Unable to read manifest template", e);
      throw new UnableToCompleteException();
    }

    return out;
  }
}
