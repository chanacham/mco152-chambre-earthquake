package chambre.earthquakes;
import com.google.gson.Gson;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Main {
    public static void main(String[] args) throws IOException {
        EarthquakeFrame gui = new EarthquakeFrame();
        gui.setVisible(true);
    }


}