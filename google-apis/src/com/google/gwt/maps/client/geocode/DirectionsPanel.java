package com.google.gwt.maps.client.geocode;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;

/**
 * A widget that holds the formatted directions retrieved from a
 * {@link Directions} query.
 * 
 * @see DirectionQueryOptions#DirectionQueryOptions(com.google.gwt.maps.client.Map,
 *      DirectionsPanel)
 *      
 * TODO: should this be final?  Need to see if subclassing can get us
 * into trouble.
 */
public class DirectionsPanel extends Widget {

  /**
   * Creates a new directions panel.
   */
  public DirectionsPanel() {
    setElement(DOM.createDiv());
  }
}
