package chambre.earthquakes;

import javax.swing.*;
import java.awt.*;
import java.util.jar.JarEntry;

public class EarthquakeFrame extends JFrame {
    public EarthquakeFrame(String mostRecent){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2,1));
        mainPanel.setPreferredSize(new Dimension(100,300));

        JLabel textPanel = new JLabel("Most Recent Earthquake: ");
        textPanel.setPreferredSize(new Dimension(100,300));
        textPanel.setFont(new Font("Arial",Font.BOLD,30));
        mainPanel.add(textPanel);

        JLabel locationPanel = new JLabel(mostRecent);
        locationPanel.setPreferredSize(new Dimension(100,100));
        locationPanel.setFont(new Font("Arial",Font.BOLD,30));
        mainPanel.add(locationPanel);


        setTitle("Most recent earthquake");
        setSize(500, 700);
        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
