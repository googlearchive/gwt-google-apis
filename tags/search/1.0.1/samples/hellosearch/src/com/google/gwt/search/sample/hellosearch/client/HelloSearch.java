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
package com.google.gwt.search.sample.hellosearch.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.search.client.ExpandMode;
import com.google.gwt.search.client.ImageSearch;
import com.google.gwt.search.client.KeepHandler;
import com.google.gwt.search.client.KeepLabel;
import com.google.gwt.search.client.LinkTarget;
import com.google.gwt.search.client.NewsResult;
import com.google.gwt.search.client.NewsSearch;
import com.google.gwt.search.client.Result;
import com.google.gwt.search.client.ResultSetSize;
import com.google.gwt.search.client.SearchCompleteHandler;
import com.google.gwt.search.client.SearchControl;
import com.google.gwt.search.client.SearchControlOptions;
import com.google.gwt.search.client.SearchStartingHandler;
import com.google.gwt.search.client.VideoResult;
import com.google.gwt.search.client.VideoSearch;
import com.google.gwt.search.client.WebResult;
import com.google.gwt.search.client.WebSearch;
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
 * HelloSearch application.
 */
public class HelloSearch implements EntryPoint, KeepHandler, SearchCompleteHandler,
    SearchStartingHandler {

  private class GoogleCodeWebSearch extends WebSearch {
    public GoogleCodeWebSearch() {
      setSiteRestriction("code.google.com");
      setUserDefinedLabel("Google Code");
      setResultSetSize(ResultSetSize.LARGE);
    }
  }

  private VerticalPanel clips = new VerticalPanel();
  private HorizontalPanel hp = new HorizontalPanel();
  private static final int CLIP_WIDTH = 200;
  
  public void onKeep(KeepEvent event) {
    final Result result = event.getResult();
    
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
    options.add(new ImageSearch());
    options.setKeepLabel(KeepLabel.SAVE);
    options.setLinkTarget(LinkTarget.BLANK);

    SearchControl searchControl = new SearchControl(options);

    searchControl.addKeepHandler(this);
    searchControl.addSearchCompleteHandler(this);
    searchControl.addSearchStartingHandler(this);
    searchControl.execute("Google Web Toolkit");
    
    clips.setWidth(CLIP_WIDTH + "px");
    hp.add(clips);
    hp.add(searchControl);
    hp.setWidth("100%");
    hp.getElement().getStyle().setPropertyPx("margin", 15);
    
    RootPanel.get().add(hp);
  }

  /**
   * This is just to show that the concrete returned types are those defined in
   * the API.
   */
  public void onSearchComplete(SearchCompleteEvent event) {
    Result result = event.getResult();
    System.out.println("The result is a " + result.getClass().getName());
  }

  public void onSearchStarting(SearchStartingEvent event) {
    System.out.println("Searching for query: " + event.getQuery() + " : "
        + event.getSearch().toString());
  }

}
