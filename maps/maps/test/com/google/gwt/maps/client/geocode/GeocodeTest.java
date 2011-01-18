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
package com.google.gwt.maps.client.geocode;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.maps.client.MapsTestCase;
import com.google.gwt.maps.client.geom.LatLng;

/**
 * Tests for the geocoding service and supporting classes.
 */
public class GeocodeTest extends MapsTestCase {
  private static class PlacemarkMock {
    final String addressQuery;
    final String city;
    final String countryCode;
    final String county;
    final String jsonString;
    final String postalCode;
    final String state;
    final String street;

    PlacemarkMock(String addressQuery, String jsonString, String street,
        String city, String postalCode, String county, String state,
        String countryCode) {
      this.addressQuery = addressQuery;
      this.jsonString = jsonString;
      this.street = street;
      this.city = city;
      this.postalCode = postalCode;
      this.county = county;
      this.state = state;
      this.countryCode = countryCode;
    }

    public String getAddressQuery() {
      return addressQuery;
    }

    void comparePlacemark(Placemark place) {
      // Now test the placemark methods on this object
      String cmpAddress = place.getAddress();
      assertNotNull("Address", cmpAddress);
      String cmpStreet = place.getStreet();
      assertNotNull("Street", cmpStreet);
      String cmpCity = place.getCity();
      assertNotNull("City", cmpCity);
      String cmpPostalCode = place.getPostalCode();
      assertNotNull("PostalCode", cmpPostalCode);
      String cmpCounty = place.getCounty();
      // The County field isn't populated consistently and this assertion will
      // fail on some results.
      // assertNotNull("County", place.getCounty());
      String cmpState = place.getState();
      assertNotNull("State", cmpState);
      String cmpCountryCode = place.getCountry();
      assertNotNull("Country Code", cmpCountryCode);

      assertTrue("Address length s/b > 0", cmpAddress.length() > 0);
      assertEquals("Street", street.toLowerCase(), cmpStreet.toLowerCase());
      assertEquals("City", city.toLowerCase(), cmpCity.toLowerCase());
      assertEquals("Postal Code", postalCode.toLowerCase(),
          cmpPostalCode.toLowerCase());
      if (cmpCounty != null) {
        assertEquals("County", county.toLowerCase(), cmpCounty.toLowerCase());
      }
      assertEquals("Administrative Area (state)", state.toLowerCase(),
          cmpState.toLowerCase());
      assertEquals("Country Code", countryCode.toLowerCase(),
          cmpCountryCode.toLowerCase());

      Placemark.ExtendedData extended = place.getExtendedData();
      assertNotNull("Extended Data", extended);
      assertTrue(extended.getBounds().getNorthEast().getLatitude() != 0);
      assertTrue(extended.getBounds().getNorthEast().getLongitude() != 0);
      assertTrue(extended.getBounds().getSouthWest().getLatitude() != 0);
      assertTrue(extended.getBounds().getSouthWest().getLongitude() != 0);
    }

    void testMockPlacemark() {
      JSONValue v = JSONParser.parse(jsonString);
      JSONObject obj = v.isObject();
      assertNotNull("Couldn't JSON parse an object from : " + addressQuery
          + " : \n" + jsonString, obj);
      Placemark place = (Placemark) obj.getJavaScriptObject();
      comparePlacemark(place);
    }
  }

  static final PlacemarkMock[] goodTestPlacemarks = {
      new PlacemarkMock(
          "10 10th St NW, Atlanta, GA 30309 USA",
          "{\"id\":\"p1\", \"address\":\"10 10th St NW, Atlanta, GA 30309, USA\", \"AddressDetails\":{\"Country\":{\"CountryNameCode\":\"US\", \"AdministrativeArea\":{\"AdministrativeAreaName\":\"GA\", \"SubAdministrativeArea\":{\"SubAdministrativeAreaName\":\"Fulton\", \"Locality\":{\"LocalityName\":\"Atlanta\", \"Thoroughfare\":{\"ThoroughfareName\":\"10 10th St NW\"}, \"PostalCode\":{\"PostalCodeNumber\":\"30309\"}}}}}, \"Accuracy\":8}, \"ExtendedData\": {\"LatLonBox\": {\"north\": 33.7846417,\"south\": 33.7783464,\"east\": -84.3849262,\"west\": -84.3912214}},\"Point\":{\"coordinates\":[-84.388042,33.781479,0]}}",
          "10 10th st nw", "atlanta", "30309", "fulton", "ga", "us"), //
      new PlacemarkMock(
          "Domkloster 3, 50667 Köln, Deutschland", //
          "{\"id\":\"p1\", \"address\":\"Domkloster, 50667 Altstadt-Nord, K�ln, Germany\", \"AddressDetails\":{\"Country\":{\"CountryNameCode\":\"DE\", \"AdministrativeArea\":{\"AdministrativeAreaName\":\"Nordrhein-Westfalen\", \"SubAdministrativeArea\":{\"SubAdministrativeAreaName\":\"K�ln\", \"Locality\":{\"LocalityName\":\"K�ln\", \"DependentLocality\":{\"DependentLocalityName\":\"Altstadt-Nord\", \"Thoroughfare\":{\"ThoroughfareName\":\"Domkloster\"}, \"PostalCode\":{\"PostalCodeNumber\":\"50667\"}}}}}}, \"Accuracy\":6}, \"ExtendedData\": {\"LatLonBox\": {\"north\": 50.9438241, \"south\": 50.9375288, \"east\": 6.9601959, \"west\": 6.9539007}},\"Point\":{\"coordinates\":[6.956613,50.940872,0]}}", // Cologne
          "Domkloster", "K�ln", "50667", "K�ln", "Nordrhein-Westfalen",
          "DE"),
      new PlacemarkMock(
          "4141 Avenue Pierre-De-Coubertin, Montréal, QC, Canada",
          "{\"id\":\"p1\", \"address\":\"4141 Rue Pierre-de-Coubertin, Montr�al, QC, Canada\", \"AddressDetails\":{\"Country\":{\"CountryNameCode\":\"CA\", \"AdministrativeArea\":{\"AdministrativeAreaName\":\"QC\", \"SubAdministrativeArea\":{\"SubAdministrativeAreaName\":\"Communaut�-Urbaine-de-Montr�al\", \"Locality\":{\"LocalityName\":\"Montr�al\", \"Thoroughfare\":{\"ThoroughfareName\":\"4141 Rue Pierre-de-Coubertin\"}, \"PostalCode\":{\"PostalCodeNumber\":\"H1V\"}}}}}, \"Accuracy\":8}, \"ExtendedData\": {\"LatLonBox\": {\"north\": 45.5691633, \"south\": 45.5628680, \"east\": -73.5408470, \"west\": -73.5471423}},\"Point\":{\"coordinates\":[-73.550814,45.555154,0]}}",
          "4141 Rue Pierre-de-Coubertin", "Montr�al", "H1V",
          "Communaut�-Urbaine-de-Montr�al", "QC", "CA"),
      new PlacemarkMock(
          "Champ de Mars 75007 Paris, France",
          "{\"id\":\"p1\", \"address\":\"Parc du Champ de Mars, 75007 7�me Arrondissement Paris, Paris, France\", \"AddressDetails\":{\"Country\":{\"CountryNameCode\":\"FR\", \"AdministrativeArea\":{\"AdministrativeAreaName\":\"Ile-de-France\", \"SubAdministrativeArea\":{\"SubAdministrativeAreaName\":\"Paris\", \"Locality\":{\"LocalityName\":\"Paris\", \"DependentLocality\":{\"DependentLocalityName\":\"7�me Arrondissement Paris\", \"Thoroughfare\":{\"ThoroughfareName\":\"Parc du Champ de Mars\"}, \"PostalCode\":{\"PostalCodeNumber\":\"75007\"}}}}}}, \"Accuracy\":6}, \"ExtendedData\": {\"LatLonBox\": {\"north\": 48.8634520, \"south\": 48.8487691, \"east\": 2.3143407,\"west\": 2.2823259}},\"Point\":{\"coordinates\":[2.298605,48.855948,0]}}",
          "Parc du Champ de Mars", "Paris", "75007", "Paris", "Ile-de-France",
          "FR")};

  @Override
  public String getModuleName() {
    return "com.google.gwt.maps.GoogleMapsTest";
  }

  /**
   * Create a Geocoder using a custom subclass of GeocderCache().
   */
  public void testGeocodeCustomGC() {
    // TODO(zundel): this case currently fails. issue 20
  }

  /**
   * Iterates through all the mock placemarks and tests them out.
   */
  public void testPlacemarks() {
    loadApi(new Runnable() {
      public void run() {
        for (PlacemarkMock m : goodTestPlacemarks) {
          m.testMockPlacemark();
        }
      }
    });
  }

  /**
   * Look up a good address.
   */
  public void testSimpleGeocode() {
    loadApi(new Runnable() {
      public void run() {

        final String testAddress = goodTestPlacemarks[0].getAddressQuery();
        Geocoder geocoder = new Geocoder();
        geocoder.getLatLng(testAddress, new LatLngCallback() {
          public void onFailure() {
            assertTrue("Geocode of " + testAddress + " failed.", false);
          }

          public void onSuccess(LatLng point) {
            // Test passed!
            finishTest();
          }
        });
      }
    });
  }

  /**
   * Look up a bad address.
   *
   * 18 Jan 2011 - this test started to fail with no changes to the Maps GWT bindings.
   * I changed the lookup slightly and it still fails.
   */
  public void disabledTestSimpleGeocodeFails() {
    loadApi(new Runnable() {
      public void run() {

        final String badTestAddress = "123 Main St, Googleville CX";
        Geocoder geocoder = new Geocoder();
        geocoder.getLatLng(badTestAddress, new LatLngCallback() {
          public void onFailure() {
            // Test passed!
            finishTest();
          }

          public void onSuccess(LatLng point) {
            // This was supposed to be a bad address.
            assertTrue("Geocode of " + badTestAddress + " suceeded, but was supposed to fail.", false);
          }
        });
      }
    });
  }

  /**
   * Create a Geocoder with a FactualGeocodeCache for the cache implementation.
   * s/b/ the same as using the default Geocoder() constructor.
   */
  public void testSimpleGeocodeFGC() {
    loadApi(new Runnable() {
      public void run() {

        final String testAddress = goodTestPlacemarks[0].getAddressQuery();
        Geocoder geocoder = new Geocoder(new FactualGeocodeCache());

        geocoder.getLatLng(testAddress, new LatLngCallback() {
          public void onFailure() {
            assertTrue("Geocode of " + testAddress + " failed.", false);
          }

          public void onSuccess(LatLng point) {
            // Test passed!
            finishTest();
          }
        });
      }
    });
  }

  /**
   * Create a Geocoder using the GeocderCache() cache implementation.
   */
  public void testSimpleGeocodeGC() {
    // TODO(zundel): this case currently fails. issue 20
  }

  /**
   * Test geocoding using a LocationCallback.
   */
  public void testSimpleLocationCallback() {
    loadApi(new Runnable() {
      public void run() {
        final String testAddress = goodTestPlacemarks[0].getAddressQuery();
        Geocoder geocoder = new Geocoder();

        geocoder.getLocations(testAddress, new LocationCallback() {
          public void onFailure(int statusCode) {
            assertTrue("Geocode of " + testAddress + " failed with status "
                + statusCode + " : " + StatusCodes.getName(statusCode), false);
          }

          public void onSuccess(JsArray<Placemark> locations) {
            assertTrue("length of locations expected to be > 0",
                locations.length() > 0);
            goodTestPlacemarks[0].comparePlacemark(locations.get(0));
            finishTest();
          }
        });
      }
    }, false);
  }

  /**
   * Test subclassing AbstractGeocoderCache.
   */
  public void testSubclassGeocoderCache() {
    loadApi(new Runnable() {
      public void run() {

        class MyGeocodeCache extends CustomGeocodeCache {
          int numTimesGetCalled = 0;
          int numTimesPutCalled = 0;
          int numTimesToCanonicalCalled = 0;

          @Override
          public JavaScriptObject get(String address) {
            JavaScriptObject result = super.get(address);
            numTimesGetCalled++;
            return result;
          }

          @Override
          public void put(String address, JavaScriptObject reply) {
            super.put(address, reply);
            numTimesPutCalled++;
          }

          @Override
          public String toCanonical(String address) {
            String result = super.toCanonical(address);
            numTimesToCanonicalCalled++;
            return result;
          }
        }

        /**
         * Create a Geocoder with the custom GeocodeCache for the cache
         * implementation.
         */
        final String testAddress = goodTestPlacemarks[0].getAddressQuery();
        final MyGeocodeCache myGeocodeCache = new MyGeocodeCache();
        Geocoder geocoder = new Geocoder(myGeocodeCache);

        geocoder.getLatLng(testAddress, new LatLngCallback() {
          public void onFailure() {
            assertTrue("Geocode of " + testAddress + " failed.", false);
          }

          public void onSuccess(LatLng point) {
            assertTrue("get() not called", myGeocodeCache.numTimesGetCalled > 0);
            assertTrue("put() not called", myGeocodeCache.numTimesPutCalled > 0);
            assertTrue("toCanonical() not called",
                myGeocodeCache.numTimesToCanonicalCalled > 0);
            finishTest();
          }
        });
      }
    });
  }

  public void testReverseGeocoder() {
    loadApi(new Runnable() {
      public void run() {

        // Somewhere in Berlin, Germany (DE)
        final LatLng testPoint = LatLng.newInstance(52.51622, 13.39782);
        Geocoder geocoder = new Geocoder();
        geocoder.getLocations(testPoint, new LocationCallback() {

          public void onFailure(int statusCode) {
            assertTrue("Reverse Geocode of " + testPoint
                + " failed with status " + statusCode + ": "
                + StatusCodes.getName(statusCode), false);
          }

          public void onSuccess(JsArray<Placemark> locations) {
            boolean resultFound = false;
            for (int i = 0; i < locations.length(); ++i) {
              Placemark location = locations.get(i);

              if ("Berlin".equals(location.getCity())) {
                finishTest();
              }

              resultFound = true;
            }
            if (resultFound) {
              // The location did not return 'Berlin' as the city, but this
              // could be
              // because the locale spells the city name a different way. At
              // least
              // we got a result - consider the test successful.
              finishTest();
            }
            assertTrue("Reverse Geocode of " + testPoint
                + " failed with no results.", false);
          }
        });
      }
    }, false);
  }
}
