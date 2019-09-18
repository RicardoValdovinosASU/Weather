package openweathermap.api;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class OpenWeatherMapApiConnection {
    // API Constants
    private static final String API_KEY = readApiFromJson();

    private static final String API_CALL_ENDPOINT = "api.openweathermap.org";
    private static final String API_CALL_INFO = "/data/2.5/weather?";

                                    /* ------- Generate Necessary Files ------- */

    private static String readApiFromJson() {
        try {
            FileReader fileReader = new FileReader(new File("WeatherConfig.json"));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            JsonReader jsonReader = new JsonReader(bufferedReader);
            return new JsonParser().parse(jsonReader).getAsJsonObject().get("API_KEY").getAsString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } return "";
    }


                                    /* ------- Establishing A Connection to The Open Weather Api ------- */

    // establishes the connection to the API using a URL. It then returns a json object depending on the specified parameters
    public JsonObject establishConnection(String cityName, String countryCode, String cityID, String latitude, String longitude, String zipCode) {
        JsonObject data;
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) (new URL("http://" + API_CALL_ENDPOINT + API_CALL_INFO +
                    specifyParameters(cityName, countryCode, cityID, latitude, longitude, zipCode) + "&APPID="
                    + API_KEY)).openConnection();
            data = getDataFromConnection(new InputStreamReader(urlConnection.getInputStream()));
            urlConnection.disconnect();
            return data;
        } catch (MalformedURLException m) {
            m.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JsonObject();
    }

    // Specifies parameters to be used when establishing a connection the the api
    private String specifyParameters(String cityName, String countryCode, String cityID, String latitude, String longitude, String zipCode) {
        if (cityName != null && countryCode != null) {
            return "q=" + cityName + "," + countryCode;
        } else if (cityName != null) {
            return "q=" + cityName;
        }
        if (cityID != null) {
            return "id=" + cityID;
        }
        if (latitude != null && longitude != null) {
            return "lat=" + latitude + "&lon=" + longitude;
        }
        if (zipCode != null && countryCode != null) {
            return "zip=" + zipCode + "," + countryCode;
        } else if (zipCode != null) {
            return "zip=" + zipCode;
        }
        return "";
    }

    // Uses Gson library to get the retrieved data from the api in JSON format
    private JsonObject getDataFromConnection(InputStreamReader inputStreamReader) {
        JsonElement element = new JsonParser().parse(new Gson().newJsonReader(inputStreamReader));
        return element.getAsJsonObject();
    }
}
