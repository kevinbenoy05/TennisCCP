import com.google.gson.*;
import java.io.*;
import java.net.*;
public class Location{
    private static double longitude, latitude;
    public static JsonObject weather;
    private static String address;
    public Location(String address) throws IOException{
        this.address = address;
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
   public String address(){
    return address;
   }
}