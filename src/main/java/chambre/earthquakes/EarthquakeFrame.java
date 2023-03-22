package chambre.earthquakes;

import com.google.gson.Gson;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.TimeUnit;
import java.util.jar.JarEntry;

public class EarthquakeFrame extends JFrame {
    public EarthquakeFrame(){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2,1));
        mainPanel.setPreferredSize(new Dimension(100,300));

        JLabel textPanel = new JLabel("Most Recent Earthquake: ");
        textPanel.setPreferredSize(new Dimension(100,300));
        textPanel.setFont(new Font("Arial",Font.BOLD,30));
        mainPanel.add(textPanel);

        JLabel locationPanel = new JLabel();
        locationPanel.setPreferredSize(new Dimension(100,100));
        locationPanel.setFont(new Font("Arial",Font.BOLD,30));
        mainPanel.add(locationPanel);


        setTitle("Most recent earthquake");
        setSize(500, 700);
        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://earthquake.usgs.gov/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        EarthquakeService service = retrofit.create(EarthquakeService.class);

        Disposable disposable = Observable.interval(0,30, TimeUnit.SECONDS)
                .flatMap((Long) ->service.getLatestEarthquakes())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(
                        featureCollection -> {
                            String location =featureCollection.features[0].properties.place;
                            locationPanel.setText(location);
                        },
                        Throwable::printStackTrace
                );
    }
}
