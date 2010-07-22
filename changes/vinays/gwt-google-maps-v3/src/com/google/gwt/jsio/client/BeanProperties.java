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
package com.google.gwt.jsio.client;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import com.google.gwt.jsio.client.impl.MetaDataName;

/**
 * Indicates that methods that look like bean-style property setters should be
 * generated so as to read and write object properties rather than import
 * functions. This is most useful with JSON-style objects. The setting may by
 * applied on a per-method or per-type basis. When applied to a type, this
 * behavior may be overridden on individual methods via the use of a
 * {@link Imported} annotation.If the backing object does not contain data for a
 * property accessor, <code>null</code>, <code>0</code>, <code>' '</code>,
 * <code>false</code>, or an empty {@link JSList} will be returned.
 */
@Documented
@MetaDataName("gwt.beanProperties")
@Target(value = {ElementType.TYPE, ElementType.METHOD})
public @interface BeanProperties {
}
