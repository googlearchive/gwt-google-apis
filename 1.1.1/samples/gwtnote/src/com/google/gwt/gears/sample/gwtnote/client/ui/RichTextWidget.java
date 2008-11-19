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
package com.google.gwt.gears.sample.gwtnote.client.ui;

import com.google.gwt.gears.sample.gwtnote.client.GWTNote;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.KeyboardListenerAdapter;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A text-editing widget suitable for use in the main panel of a GWT app.
 */
public class RichTextWidget extends Composite {
  // Borrowed from GWT KitchenSink sample app.

  protected String oldName = "default";

  protected boolean nameEditable = false;

  private RichTextArea bodyWidget = null;

  private String curText = null;

  private Label status = null;

  private TextBox name = null;

  private ListBox options = null;

  private String version = "1";

  private PushButton nameEdit = null;

  private String[] lastOptions = null;

  private List<ChangeListener> nameListeners = new ArrayList<ChangeListener>();

  /**
   * Creates a new widget. This class needs access to certain fields and methods
   * on the application enclosing it.
   * 
   * @param parent the host application
   */
  public RichTextWidget(final GWTNote parent) {
    super();

    VerticalPanel top = new VerticalPanel();
    top.setWidth("100%");
    HorizontalPanel header = new HorizontalPanel();
    top.add(header);
    header.setWidth("100%");

    header.add(new Label("GWT GearsNote"));

    header.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
    status = new Label("Ready");
    header.add(status);

    this.bodyWidget = new RichTextArea();
    bodyWidget.addKeyboardListener(new KeyboardListenerAdapter() {
      @Override
      public void onKeyPress(Widget sender, char keyCode, int modifiers) {
        String newText = bodyWidget.getText();
        if (((newText == null) && (curText != null))
            || ((newText != null) && !newText.equals(curText))) {
          curText = newText;
        }
      }
    });

    HorizontalPanel controls = new HorizontalPanel();
    RichTextToolbar tb = new RichTextToolbar(this.bodyWidget);
    name = new TextBox();
    name.setText("default");
    name.setEnabled(false);
    nameEdit = new PushButton("Edit");
    nameEdit.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        String curName = name.getText();
        boolean doNotify = !oldName.equals(curName);
        if (!nameEditable) { // if becoming editable, store off current value
          oldName = curName;
        }
        if (nameEditable && (curName == null || "".equals(curName))) {
          // if becoming un-editable, check to make sure it's valid
          Window.alert("The note name cannot be blank. Please try again.");
          nameEdit.setText(oldName);
          return;
        }
        // if all else is good, just flip the state
        nameEditable = !nameEditable;
        name.setEnabled(nameEditable);
        nameEdit.getUpFace().setText((nameEditable ? "Confirm" : "Edit"));
        if (doNotify) {
          notifyNameListeners();
        }
      }
    });
    nameEdit.addStyleName("edit-button");

    options = new ListBox();
    controls.add(tb);
    options.addItem("default");
    options.setSelectedIndex(0);
    options.addChangeListener(new ChangeListener() {
      public void onChange(Widget sender) {
        String newName = options.getItemText(options.getSelectedIndex());
        name.setText(newName);
        notifyNameListeners();
      }
    });
    HorizontalPanel tmp = new HorizontalPanel();
    tmp.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
    tmp.add(new Label("Note name:"));
    tmp.add(name);
    tmp.add(nameEdit);
    tmp.add(options);
    controls.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
    controls.setWidth("100%");
    controls.add(tmp);
    top.add(controls);

    top.add(bodyWidget);
    this.bodyWidget.setWidth("100%");
    top.setCellHeight(bodyWidget, ((int) (Window.getClientHeight() * .75))
        + "px");
    this.bodyWidget.setHeight("100%");

    initWidget(top);
  }

  /**
   * Registers a ChangeListener to be notified whenever the name of the current
   * note is changed by the user.
   * 
   * @param cl the ChangeListener to register
   */
  public void addNameChangeListener(ChangeListener cl) {
    nameListeners.add(cl);
  }

  /**
   * Retrieves the current text stored in the UI widget.
   * 
   * @return the current text in the UI
   */
  public String getHTML() {
    return bodyWidget.getHTML();
  }

  /**
   * Returns the name of the current file.
   * 
   * @return contents of the <code>name</code> text box
   */
  public String getName() {
    if (nameEditable) {
      return oldName;
    }
    return name.getText();
  }

  /**
   * Returns the value of the version property.
   * 
   * @return the value of the version property
   */
  public String getVersion() {
    return version;
  }

  /**
   * Sets the content of the textbox to the specified value.
   * 
   * @param newText the new content of the textbox
   */
  public void setHTML(String newText) {
    bodyWidget.setHTML(newText);
  }

  /**
   * Sets the name of the current data.
   * 
   * @param newName the new name
   */
  public void setName(String newName) {
    name.setText(newName);
  }

  /**
   * Sets the contents of the name drop-down.
   * 
   * @param newOptions the options to include
   */
  public void setNameOptions(String[] newOptions) {
    Arrays.sort(newOptions);
    boolean good = true;
    if (lastOptions != null && newOptions.length == lastOptions.length) {
      for (int i = 0; i < newOptions.length; ++i) {
        if (!newOptions[i].equals(lastOptions[i])) {
          good = false;
          break;
        }
      }
    } else {
      good = false;
    }
    if (!good) {
      options.clear();
      for (int i = 0; i < newOptions.length; ++i) {
        options.addItem(newOptions[i]);
        if (newOptions[i].equals(name.getText())) {
          options.setSelectedIndex(i);
        }
      }
    }
    lastOptions = newOptions;
  }

  /**
   * Sets the status message.
   * 
   * @param status the new status
   */
  public void setStatus(String status) {
    this.status.setText(status);
  }

  /**
   * Sets the value of the version property.
   * 
   * @param newVersion the new version
   */
  public void setVersion(String newVersion) {
    version = newVersion != null ? newVersion : "1";
  }

  /**
   * Notifies all registered listeners that the name value has changed. This is
   * synthesizing an event, since TextBox doesn't normally fire this event when
   * we set it programmatically.
   */
  private void notifyNameListeners() {
    for (int i = 0; i < nameListeners.size(); ++i) {
      (nameListeners.get(i)).onChange(this);
    }
  }
}
