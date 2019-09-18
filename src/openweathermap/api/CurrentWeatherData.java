package openweathermap.api;

import com.google.gson.JsonObject;

public class CurrentWeatherData {
    private String cityName;
    private String countryCode; // use ISO 3166 country codes
    private String cityID;
    private String latitude;
    private String longitude;
    private String zipCode;

    // this object establishes a connection to the api
    private OpenWeatherMapApiConnection apiConnection;

    // Use null for unused parameters
    public CurrentWeatherData(String cityName, String countryCode, String cityID, String latitude, String longitude, String zipCode) {
        this.cityName = cityName;
        this.countryCode = countryCode;
        this.cityID = cityID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.zipCode = zipCode;
        this.apiConnection = new OpenWeatherMapApiConnection();
    }

                                    /* ------- API Based Methods ------- */

    // gets whether depending on what values are give
    public JsonObject weatherByGiven() {
        if (cityName != null && countryCode != null) {
            return weatherByCityName();
        } else if (cityName != null) {
            return weatherByCityNameAndCountryCode();
        }
        if (cityID != null) {
            return weatherByCityID();
        }
        if (latitude != null && longitude != null) {
            return weatherByGeographicalCoordinates();
        }
        if (zipCode != null && countryCode != null) {
            return weatherByZipCodeAndCountryCode();
        } else if (zipCode != null) {
            return weatherByZipCode();
        }
        return new JsonObject();
    }

    // You can call current weather data for one location by city name, city ID, geographical coordinates, and zip code.
    public JsonObject weatherByCityName() {
        return apiConnection.establishConnection(cityName, null, null, null, null, null);
    }

    public JsonObject weatherByCityNameAndCountryCode() {
        return apiConnection.establishConnection(cityName, countryCode, null, null, null, null);
    }

    public JsonObject weatherByCityID() {
        return apiConnection.establishConnection(null, null, cityID, null, null, null);
    }

    public JsonObject weatherByGeographicalCoordinates() {
        return apiConnection.establishConnection(null, null, null, latitude, longitude, null);
    }

    public JsonObject weatherByZipCodeAndCountryCode() {
        return apiConnection.establishConnection(null, countryCode, null, null, null, zipCode);
    }

    // "Please note if country is not specified then the display works for USA as a default." -http://openweathermap.org/current
    public JsonObject weatherByZipCode() {
        return apiConnection.establishConnection(null, null, null, null, null, zipCode);
    }

                                    /* ------- Getters & Setters ------- */

    // Getters
    public String getCityName() {
        return cityName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCityID() {
        return cityID;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getZipCode() {
        return zipCode;
    }

    // Setters
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
