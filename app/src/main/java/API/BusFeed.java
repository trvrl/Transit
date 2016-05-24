package API;

import android.location.Location;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Tak on 24/05/2016.
 */
public class BusFeed {
    private static String API_KEY = "3u5DrRTEp1P6bMUlApvA";
    private Location location;
    private static String responseString;

    public static String execute(int stopNumber) {

        String feedLineString = "";
        try {
            URL url = new URL("http://api.translink.ca/rttiapi/v1/buses?apikey=" + API_KEY + "&stopNo=" + Integer.toString(stopNumber));
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("accept", "application/json");
            feedLineString = retrieveFeed(urlConnection);
            urlConnection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return feedLineString;
    }



    private static String retrieveFeed(HttpURLConnection url) {
        String bufferResponse = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            bufferedReader.close();
            bufferResponse = stringBuilder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return bufferResponse;
    }



}

