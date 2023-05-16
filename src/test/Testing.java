package test;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
public class Testing{
    public static void main(String[] args) throws IOException{
        URL url = new URL("https://api.weatherapi.com/v1/forecast.json?key=7f983c36356d4b3299d214232232204&q=38.838581704559964,%20-77.27655886669672&days=1&aqi=no&alerts=no%22");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        int code = connection.getResponseCode();
        System.out.println(code);
        JsonParser parser = new JsonParser();
        JsonElement root = parser.parse(new InputStreamReader((InputStream) connection.getContent()));
        JsonObject object = root.getAsJsonObject();
        JsonElement a = object.get("location");
        JsonObject o = a.getAsJsonObject();
        System.out.println(o.get("name")); 
        getLat();
    }
    public static void getLat() throws IOException{
        URL url = new URL("http://api.positionstack.com/v1/forward?access_key=0c6906776d03be9e384efe5d39ff3da5&query=1600%20Pennsylvania%20Ave%20NW,%20Washington%20DC");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        JsonParser parser = new JsonParser();
        JsonElement root = parser.parse(new InputStreamReader((InputStream) connection.getContent()));
        JsonObject object = root.getAsJsonObject();
        JsonArray a1 = object.getAsJsonArray("data");
        JsonObject e = a1.get(0).getAsJsonObject();
        System.out.println(e.get("latitude"));

        
    }
}