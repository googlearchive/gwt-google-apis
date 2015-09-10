#summary Getting Started Using the Google Language API for GWT

# Getting Started with the Language API #

## Assumptions ##

  * You are familiar with [Google Web Toolkit](http://code.google.com/webtoolkit/overview.html).
  * You know how to [create a new GWT project](http://code.google.com/eclipse/docs/creating_new_webapp.html) using the Google Plugin for Eclipse.
  * You are familiar with [Google Language API](http://code.google.com/apis/ajaxlanguage/).
  * You are using GWT 2.0 or later.

## Downloading the Language API Library for GWT ##

You can download the latest production releases of the libraries from the [project download page](http://code.google.com/p/gwt-google-apis/downloads/). After you download the distribution, uncompress it.  Inside the folder you will find a .jar file named `gwt-language.jar`. This .jar file is the only thing you'll need from the distribution.

You can reference the `.jar` file either from the folder that you uncompressed the project in, or copy it and reference it from another location.  In this example, we've chosen to copy the file to the path `/usr/local/gwt-language`.

## Setting up a GWT Project to use the Language API ##

There are three steps needed to use the Language API in a GWT project:

  1. Include the library JAR file in the classpath
  1. Inherit com.google.gwt.language.Language in the module XML definition file.
  1. Add a call to your Module entry point to `LanguageUtil.loadTranslation()` and/or `LanguageUtil.loadTransliteration()` to download the API from Google's severs.

We'll explain each step as we set up a new project.

## Creating a New GWT Project ##

Start by creating a new GWT project called SimpleLanguage as described in the
[GWT Plugin Guide](http://code.google.com/eclipse/docs/creating_new_webapp.html).

Since we are working with an additional library, add the library `gwt-language.jar` to the Java classpath.  Then, add the module `com.google.gwt.language.Language` to your module.

```
    <inherits name='com.google.gwt.language.Language' />
```

For better effect please remove the .css reference from the SimpleLanguage.html page and remove all HTML tags in the body.

## Writing your first application ##

Replace the content of `SimpleLanguage` class with the code below to get your application running!

```
package com.example.simplelang.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.language.client.LanguageUtils;
import com.google.gwt.language.client.translation.Language;
import com.google.gwt.language.client.translation.Translation;
import com.google.gwt.language.client.translation.TranslationCallback;
import com.google.gwt.language.client.translation.TranslationResult;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Main class for GWT language API demo.
 */
public class SimpleLanguage implements EntryPoint {

  public void onModuleLoad() {

    // Loads the translation API
    //
    // It is not safe to make calls into the translation API until the run()
    // method is invoked. Use LanguageUtils.loadTransliteration() for the
    // transliteration API.
    LanguageUtils.loadTranslation(new Runnable() {
      public void run() {
        RootPanel.get().add(new HTML("<h1>SimpleLanguage</h1><p>"));
        RootPanel.get().add(createTranslationPanel());
      }
    });
  }

  // Creates translation panel.
  private Panel createTranslationPanel() {
    final TextArea transArea = new TextArea();
    transArea.setPixelSize(200, 100);

    // This is where translation results are put.
    final Label outputLabel = new Label();

    Button translateButton = new Button("Translate");
    translateButton.addClickHandler(new ClickHandler() {

      public void onClick(ClickEvent event) {
        Translation.translate(transArea.getText(), Language.ENGLISH,
            Language.SPANISH, new TranslationCallback() {

              @Override
              protected void onCallback(TranslationResult result) {
                if (result.getError() != null) {
                  outputLabel.setText(result.getError().getMessage());
                } else {
                  outputLabel.setText(result.getTranslatedText());
                }
              }
            });
      }
    });

    // Add all widgets to translation panel.
    VerticalPanel transPanel = new VerticalPanel();
    transPanel.add(transArea);
    transPanel.add(translateButton);
    transPanel.add(new Label("Translation result: "));
    transPanel.add(outputLabel);
    return transPanel;
  }
}
```

The Language API provides support for loading API using AjaxLoader library. You may use the `LanguageUtils` class to load translation API in this case. When the API javascript is loaded, the `run()` method is executed which loads a simple Language demo. Enter some text in the textarea and click on "Translate" to translate input text from English to Spanish. Check out the screenshot of the running application below:

> http://gwt-google-apis.googlecode.com/svn/wiki/SimpleLanguage.PNG