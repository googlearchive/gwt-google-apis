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
package com.google.gwt.gadgets.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Allows the Gadget manifest generator to automatically generate
 * <code>requires</code> elements. The generator will examine all types to
 * which the Gadget is assignable and collect the feature names that are
 * required.
 */
@Documented
@Target(ElementType.TYPE)
public @interface FeatureName {
  /**
   * A value used to indicate that the feature is implicitly available in the
   * container and does not need a <code>requires</code> tag in the manifest.
   */
  public static final String INTRINSIC = "__@GWT@__";

  /**
   * The names of the features required from the container. If the feature is
   * intrinsic to the container, use the {@link #INTRINSIC} value.
   */
  public String[] value();
}
