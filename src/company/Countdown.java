package company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Countdown  {

    static JLabel counterLabel = new JLabel();
    static JLabel counterLabel2 = new JLabel();
    static JLabel scoreCounter = new JLabel();

    int elapsedTime = 900000;
    int incrementTime;
    int second = 0;
    int minute = 0;
    String secondString = String.format("%02d", second);
    String minuteString = String.format("%02d", minute);

    // creates an object that initialise every 1000 miliseconds
    Timer timer = new Timer(1000, new ActionListener() {

        public void actionPerformed(ActionEvent e) {

            //elapsed time, count down with 1000 miliseconds
            elapsedTime = elapsedTime - 1000;

            //format the time to militarytime. 
            minute = (elapsedTime / 60000) % 60;
            second = (elapsedTime / 1000) % 60;
            //format the string to have 2 decimals
            secondString = String.format("%02d", second);
            minuteString = String.format("%02d", minute);
        }

    });
    // The same as time above
    Timer timer1 = new Timer(1000, new ActionListener() {

        public void actionPerformed(ActionEvent e) {

            elapsedTime = elapsedTime - 1000;
            minute = (elapsedTime / 60000) % 60;
            second = (elapsedTime / 1000) % 60;
            secondString = String.format("%02d", second);
            minuteString = String.format("%02d", minute);
        }

    });

    Countdown() {

        counterLabel.setLayout(null);
        //Insert the text into counterLabel
        counterLabel.setText(minuteString + ":" + secondString);
        //Manipulate the cordinates and size for counterlabel
        counterLabel.setBounds(900, 400, 100, 50);
        //counterlabel use selected font
        counterLabel.setFont(new Font("Arial", Font.PLAIN, 35));
        //paint the counterlabel, allowing the underlying pixels to show through
        counterLabel.setOpaque(true);
        
        counterLabel2.setLayout(null);
        counterLabel2.setText(minuteString + ":" + secondString);
        counterLabel2.setBounds(800, 400, 100, 50);
        counterLabel2.setFont(new Font("Arial", Font.PLAIN,35));
        counterLabel2.setOpaque(true);

        scoreCounter.setLayout(null);
        scoreCounter.setText("test");
        scoreCounter.setBounds(800, 500, 100, 50);
        scoreCounter.setFont(new Font("Arial", Font.PLAIN,35));
        scoreCounter.setOpaque(true);


    }
}