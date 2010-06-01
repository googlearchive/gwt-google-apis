/*
 * Copyright 2009 Google Inc.
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

import com.google.gwt.gadgets.client.GadgetFeature.FeatureName;

/**
 * The locked-domain library isolates your gadgets from other gadgets running on
 * the same page. We suggest that you add this feature requirement to your
 * gadget if your gadget stores sensitive private user data.
 * 
 * This feature is only supported on iGoogle. Other gadget containers may not
 * support this feature and will reject your gadget.
 */
@FeatureName("locked-domain")
public interface NeedsLockedDomain {

}
