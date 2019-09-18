package weather.search;

import com.google.gson.JsonObject;

import java.text.DecimalFormat;

public class WeatherData {
    private JsonObject weatherApiJsonObject;
    private int weatherId;
    private String weatherDescription;
    private String weatherIcon;
    private String weatherLocationName;
    private double weatherTempKelvin;
    private double weatherTempFahrenheit;
    private double weatherTempCelsius;

    public WeatherData(JsonObject weatherApiJsonObject) {
        this.weatherApiJsonObject = weatherApiJsonObject;
        this.weatherId = weatherApiJsonObject.get("weather").getAsJsonArray().get(0).getAsJsonObject().get("id").getAsInt();
        this.weatherDescription = weatherApiJsonObject.get("weather").getAsJsonArray().get(0).getAsJsonObject().get("description").getAsString();
        this.weatherIcon = weatherApiJsonObject.get("weather").getAsJsonArray().get(0).getAsJsonObject().get("icon").getAsString();
        this.weatherTempKelvin = weatherApiJsonObject.get("main").getAsJsonObject().get("temp").getAsDouble();
        this.weatherLocationName = weatherApiJsonObject.get("name").getAsString();
        this.weatherTempFahrenheit = kelvinToFarh(weatherTempKelvin);
        this.weatherTempCelsius = kelvinToCelsius(weatherTempKelvin);
    }



    // small print method to make sure everything is being recieved
    private void printWeatherData() {
        System.out.println(weatherId);
        System.out.println(weatherDescription);
        System.out.println(weatherIcon);
        System.out.println(weatherTempFahrenheit);
        System.out.println(weatherLocationName);
    }

    // Small conversion methods for weather temperature
    private double kelvinToFarh(double kelvin) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return Double.parseDouble(decimalFormat.format(((kelvin * 9) / 5) - 459.67));
    }


    private double kelvinToCelsius(double kelvin) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return Double.parseDouble(decimalFormat.format(kelvin - 273.15));
    }

                                    /* ------- Getters ------- */

    public int getWeatherId() {
        return weatherId;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public double getWeatherTempKelvin() {
        return weatherTempKelvin;
    }

    public double getWeatherTempFahrenheit() {
        return weatherTempFahrenheit;
    }

    public double getWeatherTempCelsius() {
        return weatherTempCelsius;
    }

    public JsonObject getWeatherApiJsonObject() {
        return weatherApiJsonObject;
    }

    public String getWeatherLocationName() {
        return weatherLocationName;
    }
                                    /* ------- Setters ------- */

    public void setWeatherApiJsonObject(JsonObject weatherApiJsonObject) {
        this.weatherApiJsonObject = weatherApiJsonObject;
    }

}
