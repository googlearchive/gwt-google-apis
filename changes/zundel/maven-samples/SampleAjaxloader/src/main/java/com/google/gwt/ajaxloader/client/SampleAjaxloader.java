// Copyright 2010 Google Inc. All Rights Reserved.

package com.google.gwt.ajaxloader.client;

import com.google.gwt.ajaxloader.client.AjaxKeyRepository;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;

/**
 * @author zundel@google.com (Your Name Here)
 * 
 */
public class SampleAjaxloader implements EntryPoint {

  /*
   * (non-Javadoc)
   * 
   * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
   */
  public void onModuleLoad() {
    Window.alert("Hello World!");
    AjaxKeyRepository.putKey("1.2.3.4", "0xdeadbeef");
    AjaxKeyRepository.putKey("5.6.7.8", "Hello World");
    Window.alert(AjaxKeyRepository.getKey("5.6.7.8"));
  }

}
