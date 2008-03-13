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
package com.google.gwt.ajaxsearch.sample.ajaxsearch.client;

import com.google.gwt.ajaxsearch.client.ExpandMode;
import com.google.gwt.ajaxsearch.client.KeepLabel;
import com.google.gwt.ajaxsearch.client.KeepListener;
import com.google.gwt.ajaxsearch.client.LinkTarget;
import com.google.gwt.ajaxsearch.client.NewsResult;
import com.google.gwt.ajaxsearch.client.NewsSearch;
import com.google.gwt.ajaxsearch.client.Result;
import com.google.gwt.ajaxsearch.client.ResultSetSize;
import com.google.gwt.ajaxsearch.client.Search;
import com.google.gwt.ajaxsearch.client.SearchControl;
import com.google.gwt.ajaxsearch.client.SearchControlOptions;
import com.google.gwt.ajaxsearch.client.SearchListener;
import com.google.gwt.ajaxsearch.client.VideoResult;
import com.google.gwt.ajaxsearch.client.VideoSearch;
import com.google.gwt.ajaxsearch.client.WebResult;
import com.google.gwt.ajaxsearch.client.WebSearch;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.WindowResizeListener;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * AJAXSearch application.
 */
public class AJAXSearch implements EntryPoint, KeepListener, SearchListener,
    WindowResizeListener {

  private class GoogleCodeWebSearch extends WebSearch {
    public GoogleCodeWebSearch() {
      setSiteRestriction("code.google.com");
      setUserDefinedLabel("Google Code");
      setResultSetSize(ResultSetSize.LARGE);
    }
  }
  private VerticalPanel clips = new VerticalPanel();

  private HorizontalPanel hp = new HorizontalPanel();

  public void onKeep(SearchControl control, final Result result) {
    String title;
    if (result instanceof WebResult) {
      WebResult web = (WebResult) result;
      title = web.getTitle();
    } else if (result instanceof NewsResult) {
      NewsResult web = (NewsResult) result;
      title = web.getTitle();
    } else if (result instanceof VideoResult) {
      VideoResult video = (VideoResult) result;
      title = video.getTitle();
      // Metadata is also available
      System.out.println(video.getTbHeight() + "x" + video.getTbWidth() + " "
          + video.getDuration() + "seconds");
    } else {
      // Ads don't have an official interface
      title = "Advertisement";
    }

    HTML h = new HTML(title);
    h.addStyleName("clipLink");
    h.addClickListener(new ClickListener() {
      public void onClick(Widget w) {
        PopupPanel p = new PopupPanel(true);
        p.addStyleName("keepPopup");
        p.setWidget(result.getHtml());
        p.setPopupPosition(w.getAbsoluteLeft() + 5, w.getAbsoluteTop()
            + w.getOffsetHeight() + 5);
        p.show();
      }
    });

    clips.add(h);
  }

  public void onModuleLoad() {
    Window.addWindowResizeListener(this);

    clips.setWidth("100%");
    clips.addStyleName("clips");
    
    clips.add(new Label("Saved Clippings:"));

    SearchControlOptions options = new SearchControlOptions();

    // We can use custom subclasses
    options.add(new GoogleCodeWebSearch(), ExpandMode.OPEN);

    // Or configure inline
    WebSearch ws = new WebSearch();
    ws.setSiteRestriction("ajaxian.com");
    ws.setUserDefinedLabel("Ajaxian");
    ws.setResultSetSize(ResultSetSize.SMALL);
    options.add(ws);
    options.add(new NewsSearch());
    options.add(new VideoSearch(), ExpandMode.CLOSED);
    options.setKeepLabel(KeepLabel.SAVE);
    options.setLinkTarget(LinkTarget.BLANK);

    SearchControl searchControl = new SearchControl(options);
    searchControl.addKeepListener(this);
    searchControl.addSearchListener(this);
    searchControl.execute("Google Web Toolkit");
    
    clips.setWidth("200px");
    hp.add(clips);
    hp.add(searchControl);
    RootPanel.get().add(hp, 5, 5);
    onWindowResized(Window.getClientWidth(), Window.getClientHeight());
  }

  /**
   * This is just to show that the concrete returned types are those defined
   * in the API.
   */
  public void onSearchResult(Search search, Result result) {
    System.out.println("The result is a " + GWT.getTypeName(result));
  }

  public void onWindowResized(int width, int height) {
    hp.setPixelSize(width - 30, height - 30);
  }
}
