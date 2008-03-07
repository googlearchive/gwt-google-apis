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
package com.google.gwt.gadgets.client.impl;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Target;

/**
 * Specifies the name of the type of PreferenceGenerator should be used when
 * generating the implementation a Preference. This annotation is only required
 * when defining a new type of Preference; it should not be necessary for Gadget
 * developers to use this annotation. This is a String as opposed to a class
 * literal so that the PreferenceGenerator type does not have to be within the
 * source path of the client code.
 */
@Inherited
@Target(ElementType.TYPE)
public @interface PreferenceGeneratorName {
  String value();
}
