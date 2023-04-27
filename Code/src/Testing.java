import java.io.*;
import java.net.*;
import com.google.gson.*;
public class Testing{
    public static void main(String[] args) {
        String request = "http://api.weatherapi.com/v1/current.json?key=7f983c36356d4b3299d214232232204&q=38.838541501348914,-77.27628127546669&aqi=yes";
        URL url = new URL(request);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        JsonObject o;
    }
}