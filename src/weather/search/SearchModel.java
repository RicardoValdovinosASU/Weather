package weather.search;

import com.google.gson.JsonObject;
import openweathermap.api.CurrentWeatherData;

public class SearchModel {
    private String cityName;
    private String countryCode; // use ISO 3166 country codes
    private String cityID;
    private String latitude;
    private String longitude;
    private String zipCode;

    public SearchModel() {
        nullModelData();
    }

    public void nullModelData() {
        this.cityName = null;
        this.countryCode = null;
        this.cityID = null;
        this.latitude = null;
        this.longitude = null;
        this.zipCode = null;
    }

    public JsonObject initWeatherData() {
        CurrentWeatherData currentWeatherData = new CurrentWeatherData(cityName, countryCode, cityID, latitude, longitude, zipCode);
        return currentWeatherData.weatherByGiven();
    }

    /* ------- Setters ------- */

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
