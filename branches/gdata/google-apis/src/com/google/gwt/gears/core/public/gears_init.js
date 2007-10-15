/*
 * Copyright 2007 Google Inc.
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
// Sets up google.gears.factory to point to an instance of GearsFactory.
//
// google.gears.factory is *the only* supported way to access GearsFactory.
// We reserve the right to change the way Scour is exposed in the browser at
// in the future, but we will always support the google.gears.factory object.
//
// Circumvent this file at your own risk!

if (typeof google == 'undefined') {
  var google = {};
}

if (typeof google.gears == 'undefined') {
  google.gears = {};
}

if (typeof google.gears.factory == 'undefined') {
  google.gears.factory = (function() {
    // Firefox
    if (typeof GearsFactory != 'undefined') {
      return new GearsFactory();
    }

    // IE
    try {
      return new ActiveXObject('Gears.Factory');
    } catch (e) {}

    // Safari
    if (navigator.mimeTypes["application/x-googlegears"]) {
      var factory = document.createElement("object");
      factory.style.display = "none";
      factory.width = "0";
      factory.height = "0";
      factory.type = "application/x-googlegears";
      document.documentElement.appendChild(factory);
      return factory;
    }

    return null;
  })();
}
