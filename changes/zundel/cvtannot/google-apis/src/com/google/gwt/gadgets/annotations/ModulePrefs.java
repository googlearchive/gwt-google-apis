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

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Defines the preferences associated with the gadget.
 */
@Target(ElementType.TYPE)
public @interface ModulePrefs {
  public String author() default "";

  public String author_aboutme() default "";

  public String author_affiliation() default "";

  public String author_email() default "";

  public String author_link() default "";

  public String author_location() default "";

  public String author_photo() default "";

  public String author_quote() default "";

  public String description() default "";

  public String directory_title() default "";

  public int height() default 200;

  public boolean scaling() default true;

  public String screenshot() default "";

  public boolean scrolling() default false;

  public boolean singleton() default true;

  public String thumbnail() default "";

  public String title() default "";

  public String title_url() default "";

  public int width() default 320;
}
