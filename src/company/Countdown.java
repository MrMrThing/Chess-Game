package company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Countdown  {

    static JLabel counterLabel = new JLabel();
    static JLabel counterLabel2 = new JLabel();
    static JLabel scoreCounter = new JLabel();
    static JLabel scoreCounter2 = new JLabel();
    static JLabel eatScore = new JLabel();

    int elapsedTime = 900000-10000;
    int second_down = 1000;
    int points = 0;
    int points2 = 0;
    int second = 0;
    int minute = 0;
    // Format the string with 2
    String secondString = String.format("%02d", second);
    String minuteString = String.format("%02d", minute);

    // creates an object that initialise every 1000 miliseconds
    Timer timer = new Timer(1000, new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            //elapsed time, count down with 1000 miliseconds
            elapsedTime = elapsedTime - second_down;

            //format the time to militarytime. 
            minute = (elapsedTime / 60000) % 60;
            second = (elapsedTime / 1000) % 60;
            //format the string to have 2 decimals
            secondString = String.format("%02d", second);
            minuteString = String.format("%02d", minute);
            // Insert the variables in to the label.
            counterLabel.setText(minuteString + ":" + secondString);
            scoreCounter.setText("" + points);


            // stop the timer, if player/ai has run of time.
            if(minute==0 && second ==0){
                timer.stop();
                System.out.println("You run out of time!");
            }

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
            counterLabel.setText(minuteString + ":" + secondString);
            scoreCounter2.setText("" + points2);

            if(minute==0 && second ==0){
                timer.stop();
                System.out.println("You run out of time!");
            }
        }

    });

    Countdown() {

        counterLabel.setLayout(null);
        //Insert the text into counterLabel
        counterLabel.setText(minuteString + ":" + secondString);
        //Manipulate the cordinates and size for counterlabel
        counterLabel.setBounds(800, 400, 100, 50);
        //counterlabel use selected font
        counterLabel.setFont(new Font("Arial", Font.PLAIN, 35));
        //paint the counterlabel, allowing the underlying pixels to show through
        counterLabel.setOpaque(true);
        
        counterLabel2.setLayout(null);
        counterLabel2.setText(minuteString + ":" + secondString);
        counterLabel2.setBounds(900, 400, 100, 50);
        counterLabel2.setFont(new Font("Arial", Font.PLAIN,35));
        counterLabel2.setOpaque(true);

        scoreCounter.setLayout(null);
        scoreCounter.setText("" + points);
        scoreCounter.setBounds(800, 500, 100, 50);
        scoreCounter.setFont(new Font("Arial", Font.PLAIN,35));
        scoreCounter.setOpaque(true);

        scoreCounter2.setLayout(null);
        scoreCounter2.setText("" + points2);
        scoreCounter2.setBounds(900, 500, 100, 50);
        scoreCounter2.setFont(new Font("Arial", Font.PLAIN,35));
        scoreCounter2.setOpaque(true);

        eatScore.setLayout(null);
        eatScore.setText("Scoreboard");
        eatScore.setBounds(800, 450, 200, 50);
        eatScore.setFont(new Font("Arial", Font.PLAIN,35));
        eatScore.setOpaque(true);


    }
}