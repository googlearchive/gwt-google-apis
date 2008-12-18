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
package com.google.gwt.mapsblogdec08.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapType;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.Control;
import com.google.gwt.maps.client.control.ControlAnchor;
import com.google.gwt.maps.client.control.ControlPosition;
import com.google.gwt.maps.client.control.SmallMapControl;
import com.google.gwt.maps.client.event.MarkerClickHandler;
import com.google.gwt.maps.client.geocode.Geocoder;
import com.google.gwt.maps.client.geocode.LatLngCallback;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.search.client.ImageResult;
import com.google.gwt.search.client.ImageSearch;
import com.google.gwt.search.client.ImageSizeValue;
import com.google.gwt.search.client.ResultSetSize;
import com.google.gwt.search.client.SafeSearchValue;
import com.google.gwt.search.client.SearchCompleteHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.SuggestionEvent;
import com.google.gwt.user.client.ui.SuggestionHandler;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A small app that uses the GWT SuggestBox and the Maps API Geocoding to
 * display world capitals.
 */
public class CapitalSearch extends Composite {
  private Geocoder geocoder = new Geocoder();
  private SuggestBox suggest = new SuggestBox(new CapitalSuggestOracle());

  /**
   * An object to cold contents of the database (and also happens to be an
   * object of the type expected to be returned from the SuggestBox).
   */
  private class CapitalSuggestion implements Suggestion, SearchCompleteHandler {
    private final String capital;
    private final String country;
    private LatLng point;
    private Marker marker;
    private InfoWindowContent content;

    public CapitalSuggestion(String country, String capital) {
      this.country = country;
      this.capital = capital;
    }

    public String getDisplayString() {
      return capital + ", " + country;
    }

    public String getReplacementString() {
      return getDisplayString();
    }

    private void addMarker() {
      geocoder.getLatLng(getDisplayString(), new LatLngCallback() {

        public void onFailure() {
          Window.alert("Couldn't geocode " + getDisplayString());

        }

        public void onSuccess(final LatLng point) {
          GWT.log("Geocoding suceeded. Point: " + point.toString(), null);

          CapitalSuggestion.this.point = point;

          // Kick off an image search for the capital
          ImageSearch search = new ImageSearch();
          search.addSearchCompleteHandler(CapitalSuggestion.this);
          search.setImageSize(ImageSizeValue.SMALL);
          search.setNoHtmlGeneration();
          search.setResultSetSize(ResultSetSize.SMALL);
          search.setSafeSearch(SafeSearchValue.STRICT);
          search.execute(getDisplayString());
        }

      });
    }

    public void show() {
      // Does a marker exist?
      if (marker == null) {
        // If not, create it and add it to the map.
        addMarker();
      } else {
        map.getInfoWindow().open(marker, content);
      }
    }

    String fmtDoubleLatLng(double val) {
      String result = Double.toString(val);
      return result.substring(0, result.indexOf('.') + 4);
    }

    public void onSearchComplete(SearchCompleteEvent event) {
      ImageResult result = (ImageResult) event.getResult();

      marker = new Marker(point);
      String lat = fmtDoubleLatLng(point.getLatitude());
      String lng = fmtDoubleLatLng(point.getLongitude());

      content = new InfoWindowContent(new HTML("Welcome to "
          + getDisplayString() + "<br>" + "<img width=\""
          + result.getThumbnailWidth() + "\" height=\""
          + result.getThumbnailHeight() + "\" src=\""
          + result.getThumbnailUrl() + "\"><br>" + "Lat: " + lat + " Lng: "
          + lng + ""));

      marker.addMarkerClickHandler(new MarkerClickHandler() {

        public void onClick(MarkerClickEvent event) {
          map.getInfoWindow().open(point, content);
          suggest.setText("");
        }
      });
      map.addOverlay(marker);
      map.getInfoWindow().open(marker, content);
      suggest.setText("");
    }
  }

  /**
   * Provides responses for queries made to the SuggestBox
   */
  private class CapitalSuggestOracle extends SuggestOracle {

    @Override
    public void requestSuggestions(Request request, Callback callback) {
      int limit = request.getLimit();
      String query = request.getQuery();
      Response response = new Response(find(limit, query));
      callback.onSuggestionsReady(request, response);
    }

  }

  /**
   * A Map control can be created in GWT
   */
  private class SearchControl extends Control.CustomControl {

    protected SearchControl() {
      super(new ControlPosition(ControlAnchor.TOP_RIGHT, 5, 5));
    }

    @Override
    public boolean isSelectable() {
      return false;
    }

    @Override
    protected Widget initialize(final MapWidget map) {
      suggest.setLimit(10);

      VerticalPanel panel = new VerticalPanel();
      panel.add(new Label("Search for a world capital:"));
      panel.getElement().getStyle().setProperty("backgroundColor", "#FFFFFF");
      panel.getElement().getStyle().setProperty("border", "solid #666666 1px");
      panel.setSpacing(5);
      panel.setWidth("110px");

      suggest.addEventHandler(new SuggestionHandler() {

        public void onSuggestionSelected(SuggestionEvent event) {
          ((CapitalSuggestion) event.getSelectedSuggestion()).show();;
        }
      });
      panel.add(suggest);

      // Work around a safari issue - wrap the panel in an AbsolutePanel.
      AbsolutePanel outerPanel = new AbsolutePanel();
      outerPanel.add(panel);
      return outerPanel;
    }
  }

  /**
   * This is an in-memory database of all the countries and capitals the
   * application knows about.
   */
  private final CapitalSuggestion[] capitals = new CapitalSuggestion[] {
      new CapitalSuggestion("Afghanistan", "Kabul"),
      new CapitalSuggestion("Albania", "Tirane"),
      new CapitalSuggestion("Algeria", "Algiers"),
      new CapitalSuggestion("Andorra", "Andorra la Vella"),
      new CapitalSuggestion("Angola", "Luanda"),
      new CapitalSuggestion("Antigua and Barbuda", "Saint John's"),
      new CapitalSuggestion("Argentina", "Buenos Aires"),
      new CapitalSuggestion("Armenia", "Yerevan"),
      new CapitalSuggestion("Australia", "Canberra"),
      new CapitalSuggestion("Austria", "Vienna"),
      new CapitalSuggestion("Azerbaijan", "Baku"),
      new CapitalSuggestion("The Bahamas", "Nassau"),
      new CapitalSuggestion("Bahrain", "Manama"),
      new CapitalSuggestion("Bangladesh", "Dhaka"),
      new CapitalSuggestion("Barbados", "Bridgetown"),
      new CapitalSuggestion("Belarus", "Minsk"),
      new CapitalSuggestion("Belgium", "Brussels"),
      new CapitalSuggestion("Belize", "Belmopan"),
      new CapitalSuggestion("Benin", "Porto-Novo"),
      new CapitalSuggestion("Bhutan", "Thimphu"),
      new CapitalSuggestion("Bolivia", "La Paz"),
      new CapitalSuggestion("Bolivia", "Sucre"),
      new CapitalSuggestion("Bosnia and Herzegovina", "Sarajevo"),
      new CapitalSuggestion("Botswana", "Gaborone"),
      new CapitalSuggestion("Brazil", "Brasilia"),
      new CapitalSuggestion("Brunei", "Bandar Seri Begawan"),
      new CapitalSuggestion("Bulgaria", "Sofia"),
      new CapitalSuggestion("Burkina Faso", "Ouagadougou"),
      new CapitalSuggestion("Burundi", "Bujumbura"),
      new CapitalSuggestion("Cambodia", "Phnom Penh"),
      new CapitalSuggestion("Cameroon", "Yaounde"),
      new CapitalSuggestion("Canada", "Ottawa"),
      new CapitalSuggestion("Cape Verde", "Praia"),
      new CapitalSuggestion("Central African Republic", "Bangui"),
      new CapitalSuggestion("Chad", "N'Djamena"),
      new CapitalSuggestion("Chile", "Santiago"),
      new CapitalSuggestion("China", "Beijing"),
      new CapitalSuggestion("Colombia", "Bogota"),
      new CapitalSuggestion("Comoros", "Moroni"),
      new CapitalSuggestion("Republic of the Congo", "Brazzaville"),
      new CapitalSuggestion("Democratic Republic of the Congo", "Kinshasa"),
      new CapitalSuggestion("Costa Rica", "San Jose"),
      new CapitalSuggestion("Cote d'Ivoire", "Yamoussoukro "),
      new CapitalSuggestion("Cote d'Iviore", "Abidjan (de facto)"),
      new CapitalSuggestion("Croatia", "Zagreb"),
      new CapitalSuggestion("Cuba", "Havana"),
      new CapitalSuggestion("Cyprus", "Nicosia"),
      new CapitalSuggestion("Czech Republic", "Prague"),
      new CapitalSuggestion("Denmark", "Copenhagen"),
      new CapitalSuggestion("Djibouti", "Djibouti"),
      new CapitalSuggestion("Dominica", "Roseau"),
      new CapitalSuggestion("Dominican Republic", "Santo Domingo"),
      new CapitalSuggestion("East Timor", "Dili"),
      new CapitalSuggestion("Ecuador", "Quito"),
      new CapitalSuggestion("Egypt", "Cairo"),
      new CapitalSuggestion("El Salvador", "San Salvador"),
      new CapitalSuggestion("Equatorial Guinea", "Malabo"),
      new CapitalSuggestion("Eritrea", "Asmara"),
      new CapitalSuggestion("Estonia", "Tallinn"),
      new CapitalSuggestion("Ethiopia", "Addis Ababa"),
      new CapitalSuggestion("Fiji", "Suva"),
      new CapitalSuggestion("Finland", "Helsinki"),
      new CapitalSuggestion("France", "Paris"),
      new CapitalSuggestion("Gabon", "Libreville"),
      new CapitalSuggestion("The Gambia", "Banjul"),
      new CapitalSuggestion("Georgia", "Tbilisi"),
      new CapitalSuggestion("Germany", "Berlin"),
      new CapitalSuggestion("Ghana", "Accra"),
      new CapitalSuggestion("Greece", "Athens"),
      new CapitalSuggestion("Grenada", "Saint George's"),
      new CapitalSuggestion("Guatemala", "Guatemala City"),
      new CapitalSuggestion("Guinea", "Conakry"),
      new CapitalSuggestion("Guinea-Bissau", "Bissau"),
      new CapitalSuggestion("Guyana", "Georgetown"),
      new CapitalSuggestion("Haiti", "Port-au-Prince"),
      new CapitalSuggestion("Honduras", "Tegucigalpa"),
      new CapitalSuggestion("Hungary", "Budapest"),
      new CapitalSuggestion("Iceland", "Reykjavik"),
      new CapitalSuggestion("India", "New Delhi"),
      new CapitalSuggestion("Indonesia", "Jakarta"),
      new CapitalSuggestion("Iran", "Tehran"),
      new CapitalSuggestion("Iraq", "Baghdad"),
      new CapitalSuggestion("Ireland", "Dublin"),
      new CapitalSuggestion("Israel", "Jerusalem"),
      new CapitalSuggestion("Italy", "Rome"),
      new CapitalSuggestion("Jamaica", "Kingston"),
      new CapitalSuggestion("Japan", "Tokyo"),
      new CapitalSuggestion("Jordan", "Amman"),
      new CapitalSuggestion("Kazakhstan", "Astana"),
      new CapitalSuggestion("Kenya", "Nairobi"),
      new CapitalSuggestion("Kiribati", "Tarawa Atoll"),
      new CapitalSuggestion("Korea, North", "Pyongyang"),
      new CapitalSuggestion("Korea, South", "Seoul"),
      new CapitalSuggestion("Kosovo", "Pristina"),
      new CapitalSuggestion("Kuwait", "Kuwait City"),
      new CapitalSuggestion("Kyrgyzstan", "Bishkek"),
      new CapitalSuggestion("Laos", "Vientiane"),
      new CapitalSuggestion("Latvia", "Riga"),
      new CapitalSuggestion("Lebanon", "Beirut"),
      new CapitalSuggestion("Lesotho", "Maseru"),
      new CapitalSuggestion("Liberia", "Monrovia"),
      new CapitalSuggestion("Libya", "Tripoli"),
      new CapitalSuggestion("Liechtenstein", "Vaduz"),
      new CapitalSuggestion("Lithuania", "Vilnius"),
      new CapitalSuggestion("Luxembourg", "Luxembourg"),
      new CapitalSuggestion("Macedonia", "Skopje"),
      new CapitalSuggestion("Madagascar", "Antananarivo"),
      new CapitalSuggestion("Malawi", "Lilongwe"),
      new CapitalSuggestion("Malaysia", "Kuala Lumpur"),
      new CapitalSuggestion("Maldives", "Male"),
      new CapitalSuggestion("Mali", "Bamako"),
      new CapitalSuggestion("Malta", "Valletta"),
      new CapitalSuggestion("Marshall Islands", "Majuro"),
      new CapitalSuggestion("Mauritania", "Nouakchott"),
      new CapitalSuggestion("Mauritius", "Port Louis"),
      new CapitalSuggestion("Mexico", "Mexico City"),
      new CapitalSuggestion("Micronesia", "Palikir"),
      new CapitalSuggestion("Moldova", "Chisinau"),
      new CapitalSuggestion("Monaco", "Monaco"),
      new CapitalSuggestion("Mongolia", "Ulaanbaatar"),
      new CapitalSuggestion("Montenegro", "Podgorica"),
      new CapitalSuggestion("Morocco", "Rabat"),
      new CapitalSuggestion("Mozambique", "Maputo"),
      new CapitalSuggestion("Myanmar", "Rangoon"),
      new CapitalSuggestion("Mynmar", "Naypyidaw"),
      new CapitalSuggestion("Namibia", "Windhoek"),
      new CapitalSuggestion("Nauru", "Yaren District"),
      new CapitalSuggestion("Nepal", "Kathmandu"),
      new CapitalSuggestion("Netherlands", "Amsterdam"),
      new CapitalSuggestion("Netherlands", "The Hague"),
      new CapitalSuggestion("New Zealand", "Wellington"),
      new CapitalSuggestion("Nicaragua", "Managua"),
      new CapitalSuggestion("Niger", "Niamey"),
      new CapitalSuggestion("Nigeria", "Abuja"),
      new CapitalSuggestion("Norway", "Oslo"),
      new CapitalSuggestion("Oman", "Muscat"),
      new CapitalSuggestion("Pakistan", "Islamabad"),
      new CapitalSuggestion("Palau", "Melekeok"),
      new CapitalSuggestion("Panama", "Panama City"),
      new CapitalSuggestion("Papua New Guinea", "Port Moresby"),
      new CapitalSuggestion("Paraguay", "Asuncion"),
      new CapitalSuggestion("Peru", "Lima"),
      new CapitalSuggestion("Philippines", "Manila"),
      new CapitalSuggestion("Poland", "Warsaw"),
      new CapitalSuggestion("Portugal", "Lisbon"),
      new CapitalSuggestion("Qatar", "Doha"),
      new CapitalSuggestion("Romania", "Bucharest"),
      new CapitalSuggestion("Russia", "Moscow"),
      new CapitalSuggestion("Rwanda", "Kigali"),
      new CapitalSuggestion("Saint Kitts and Nevis", "Basseterre"),
      new CapitalSuggestion("Saint Lucia", "Castries"),
      new CapitalSuggestion("Saint Vincent and the Grenadines", "Kingstown"),
      new CapitalSuggestion("Samoa", "Apia"),
      new CapitalSuggestion("San Marino", "San Marino"),
      new CapitalSuggestion("Sao Tome and Principe", "Sao Tome"),
      new CapitalSuggestion("Saudi Arabia", "Riyadh"),
      new CapitalSuggestion("Senegal", "Dakar"),
      new CapitalSuggestion("Serbia", "Belgrade"),
      new CapitalSuggestion("Seychelles", "Victoria"),
      new CapitalSuggestion("Sierra Leone", "Freetown"),
      new CapitalSuggestion("Singapore", "Singapore"),
      new CapitalSuggestion("Slovakia", "Bratislava"),
      new CapitalSuggestion("Slovenia", "Ljubljana"),
      new CapitalSuggestion("Solomon Islands", "Honiara"),
      new CapitalSuggestion("Somalia", "Mogadishu"),
      new CapitalSuggestion("South Africa", "Pretoria"),
      new CapitalSuggestion("South Africa", "CapeTown"),
      new CapitalSuggestion("South Africa", "Bloemfontein"),
      new CapitalSuggestion("Spain", "Madrid"),
      new CapitalSuggestion("Sri Lanka", "Colombo"),
      new CapitalSuggestion("Sri Lanka", "Sri Jayewardenepura Kotte"),
      new CapitalSuggestion("Sudan", "Khartoum"),
      new CapitalSuggestion("Suriname", "Paramaribo"),
      new CapitalSuggestion("Swaziland", "Mbabane"),
      new CapitalSuggestion("Sweden", "Stockholm"),
      new CapitalSuggestion("Switzerland", "Bern"),
      new CapitalSuggestion("Syria", "Damascus"),
      new CapitalSuggestion("Taiwan", "Taipei"),
      new CapitalSuggestion("Tajikistan", "Dushanbe"),
      new CapitalSuggestion("Tanzania", "Dar es Salaam"),
      new CapitalSuggestion("Tanzania", "Dodoma"),
      new CapitalSuggestion("Thailand", "Bangkok"),
      new CapitalSuggestion("Togo", "Lome"),
      new CapitalSuggestion("Tonga", "Nuku'alofa"),
      new CapitalSuggestion("Trinidad and Tobago", "Port-of-Spain"),
      new CapitalSuggestion("Tunisia", "Tunis"),
      new CapitalSuggestion("Turkey", "Ankara"),
      new CapitalSuggestion("Turkmenistan", "Ashgabat"),
      new CapitalSuggestion("Tuvalu", "Vaiaku village, Funafuti province"),
      new CapitalSuggestion("Uganda", "Kampala"),
      new CapitalSuggestion("Ukraine", "Kiev"),
      new CapitalSuggestion("United Arab Emirates", "Abu Dhabi"),
      new CapitalSuggestion("United Kingdom", "London"),
      new CapitalSuggestion("United States of America", "Washington D.C."),
      new CapitalSuggestion("Uruguay", "Montevideo"),
      new CapitalSuggestion("Uzbekistan", "Tashkent"),
      new CapitalSuggestion("Vanuatu", "Port-Vila"),
      new CapitalSuggestion("Vatican City", "Vatican City"),
      new CapitalSuggestion("Venezuela", "Caracas"),
      new CapitalSuggestion("Vietnam", "Hanoi"),
      new CapitalSuggestion("Yemen", "Sanaa"),
      new CapitalSuggestion("Zambia", "Lusaka"),
      new CapitalSuggestion("Zimbabwe", "Harare")};

  private MapWidget map;

  public CapitalSearch() {
    // Open a map centered on the equator/prime meridian
    map = new MapWidget(LatLng.newInstance(0, 0), 2);
    map.setSize("100%", "100%");

    map.removeMapType(MapType.getSatelliteMap());
    map.removeMapType(MapType.getHybridMap());

    // Add some controls for the zoom level
    map.addControl(new SmallMapControl());
    map.addControl(new SearchControl());

    initWidget(map);
  }

  private Collection<CapitalSuggestion> find(int limit, String query) {
    List<CapitalSuggestion> result = new ArrayList<CapitalSuggestion>();
    query = query.toLowerCase();
    for (CapitalSuggestion suggestion : capitals) {

      if (suggestion.getDisplayString().toLowerCase().contains(query)) {
        result.add(suggestion);
        limit--;
        if (limit == 0) {
          break;
        }
      }
    }
    return result;
  }
}
