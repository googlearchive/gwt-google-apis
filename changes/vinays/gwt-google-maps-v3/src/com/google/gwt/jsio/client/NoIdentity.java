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
 * This object disables maintaining a 1:1 identity mapping between a JSWrapper
 * and the backing JSO. The
 * {@value com.google.gwt.jsio.rebind.JSWrapperGenerator#BACKREF} field will not
 * be added to the JSO. Additionally, {@link JSWrapper#setJavaScriptObject} will
 * no longer throw {@link MultipleWrapperException}.
 */
@Documented
@MetaDataName("gwt.noIdentity")
@Target(ElementType.TYPE)
public @interface NoIdentity {
}
