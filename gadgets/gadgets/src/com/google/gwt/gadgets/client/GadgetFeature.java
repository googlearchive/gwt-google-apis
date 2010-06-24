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
package com.google.gwt.gadgets.client;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * This is a tag interface for API objects that provide access to optional
 * features within the Gadget container. A GadgetFeature must define a zero-arg
 * constructor with an arbitrary access modifier.
 */
public interface GadgetFeature {

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
    String INTRINSIC = "__@INTRINSIC@__";

    /**
     * Additional <code>MayRequire</code> tags can be added to the Gadget
     * manifest here.
     *
     * @deprecated
     */
    @Deprecated
    MayRequire[] requirements() default {};

    /**
     * The names of the features required from the container. If the feature is
     * intrinsic to the container, use the {@link #INTRINSIC} value.
     */
    String[] value();
  }

  /**
   * This annotation allows additional <code>MayRequire</code> tags to be added
   * to the module specification. It can be added via
   * {@link Gadget.ModulePrefs#requirements()} or
   * {@link FeatureName#requirements()}.
   *
   * @deprecated
   */
  @Deprecated
  @Target(value = {})
  public @interface MayRequire {
    /*
     * NB: The Target annotation above means this annotation can't be applied to
     * any Java element, but is only usable as a value in an annotation
     * property.
     */

    String TYPE_BROWSER = "browser";
    String TYPE_PLATFORM = "platform";
    String TYPE_PLUGIN = "plugin";

    /**
     * Provides optional explanatory text for the requirement.
     */
    String info() default "";

    /**
     * Specifies the minimum version of the requirement.
     */
    String min_version() default "";

    /**
     * Specifies the type of requirement. One of the predefined
     * <code>TYPE_</code> fields can be used, or a container-specific type may
     * be specified.
     */
    String type();

    /**
     * The value for the requirement.
     */
    String value();
  }
}
