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
 * Individual Java functions may be exported to JavaScript callers by applying
 * this annotation on a concrete Java method within a JSWrapper. The Java method
 * will be bound to a property on the backing object per the class's NamePolicy
 * or a {@link FieldName} annotation on the method. This annotation can also be
 * used in conjunction with a {@link JSFunction} type when the type declares
 * more than one method.
 */
@Documented
@MetaDataName("gwt.exported")
@Target(ElementType.METHOD)
public @interface Exported {
}
