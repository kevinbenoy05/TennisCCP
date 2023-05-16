package test;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
public class Location{
    private static double longitude, latitude;
    public static JsonObject weather;
    public Location(String address) throws IOException{
        setCoordinates(address);
        weather = setWeather(latitude, longitude);
    }
    public Location(double latitude, double longitude) throws IOException{
        this.latitude = latitude;
        this.longitude = longitude;
        weather = setWeather(latitude, longitude);
    }
    public JsonObject setWeather(double latitude, double longitude) throws IOException{
        URL url = new URL("https://api.weatherapi.com/v1/forecast.json?key=7f983c36356d4b3299d214232232204&q="+ latitude + ",%20" + longitude + "&days=1&aqi=no&alerts=no%22");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        int code = connection.getResponseCode();
        System.out.println(code);
        JsonParser parser = new JsonParser();
        JsonElement root = parser.parse(new InputStreamReader((InputStream) connection.getContent()));
        JsonObject object = root.getAsJsonObject();
        return object;
    }
    public static void setCoordinates(String address) throws IOException{
        address.replace(" ", "%20");
        URL url = new URL("http://api.positionstack.com/v1/forward?access_key=0c6906776d03be9e384efe5d39ff3da5&query=" + address);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        JsonParser parser = new JsonParser();
        JsonElement root = parser.parse(new InputStreamReader((InputStream) connection.getContent()));
        JsonObject object = root.getAsJsonObject().getAsJsonArray("data").get(0).getAsJsonObject();
        //Set Coordinates
        latitude = object.get("latitude").getAsDouble();
        longitude = object.get("longitude").getAsDouble();
    }
    public static void main(String[] args) throws IOException { // To test methods
        Location l = new Location("1600 Pennsylvania Ave NW, Washington DC");
        System.out.println(weather);
    }
}