package chambre.earthquakes;
import com.google.gson.Gson;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Main {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_hour.geojson");
        URLConnection connection = url.openConnection();
        connection.getInputStream();
        InputStream inputStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        Gson gson = new Gson();
        FeatureCollection featureCollection = gson.fromJson(reader, FeatureCollection.class);
        System.out.println(featureCollection.features[0].properties.place);

        EarthquakeFrame gui = new EarthquakeFrame(featureCollection.features[0].properties.place);
        gui.setVisible(true);
    }


}