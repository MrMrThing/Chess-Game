package company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Countdown extends JPanel{

    //add the JLabel
    static JLabel counter1 = new JLabel();
    static JLabel counter2 = new JLabel();
    static JLabel Player1 = new JLabel();
    static JLabel Player2 = new JLabel();
    static JLabel scoreCounter1 = new JLabel();
    static JLabel scoreCounter2 = new JLabel();
    static JLabel eatScore = new JLabel();


    // add the variables
    int elapsedTime1 = 600000;
    int elapsedTime2 = 600000;
    int second_down = 1000;
    int points1 = 0;
    int points2 = 0;
    int second = 0;
    int minute = 0;
    int turn1;
    int turn2;

    // Format the string with 2 decimals.
    String secondString = String.format("%02d", second);
    String minuteString = String.format("%02d", minute);

    // creates an object that initialise every 1000 miliseconds
    Timer timer1 = new Timer(1000, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            //elapsed time, count down with 1000 miliseconds
            elapsedTime1 = elapsedTime1 - second_down;
            //format the time to militarytime. 
            minute = (elapsedTime1 / 60000) % 60;
            second = (elapsedTime1 / 1000) % 60;
            //format the string to have 2 decimals
            secondString = String.format("%02d", second);
            minuteString = String.format("%02d", minute);
            // Insert the variables in to the label.
            counter1.setText(minuteString + ":" + secondString);
            scoreCounter1.setText("" + points1);

            // stop the timer, if player 1 or player 2 has run of time.
            if (minute == 0 && second == 0) {
                timer1.stop();
                System.out.println("You run out of time and lost!");
            }
        }
    });
    // The same as time above
    Timer timer2 = new Timer(1000, new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            elapsedTime2 = elapsedTime2 - second_down;
            minute = (elapsedTime2 / 60000) % 60;
            second = (elapsedTime2 / 1000) % 60;
            secondString = String.format("%02d", second);
            minuteString = String.format("%02d", minute);
            counter2.setText(minuteString + ":" + secondString);
            scoreCounter2.setText("" + points2);

            if (minute == 0 && second == 0) {
                timer2.stop();
                System.out.println("You run out of time and lost!");
            }
        }
    });

    public Countdown(Game b_game) {
        counter1.setLayout(null);
        //Insert the text into counter1
        counter1.setText(minuteString + ":" + secondString);
        //Manipulate the cordinates and size for counter1
        counter1.setBounds(800, 400, 100, 50);
        //counter1 use selected font
        counter1.setFont(new Font("Arial", Font.PLAIN, 35));
        //paint the counter1, allowing the underlying pixels to show through
        counter1.setOpaque(true);

        counter2.setLayout(null);
        counter2.setText(minuteString + ":" + secondString);
        counter2.setBounds(900, 400, 100, 50);
        counter2.setFont(new Font("Arial", Font.PLAIN, 35));
        counter2.setOpaque(true);

        Player1.setLayout(null);
        Player1.setText(b_game.player.m_name);
        Player1.setBounds(800, 350, 100, 50);
        Player1.setFont(new Font("Arial", Font.PLAIN, 24));
        Player1.setOpaque(true);

        Player2.setLayout(null);
        Player2.setText(b_game.player2.m_name);
        Player2.setBounds(900, 350, 100, 50);
        Player2.setFont(new Font("Arial", Font.PLAIN, 24));
        Player2.setOpaque(true);

        scoreCounter1.setLayout(null);
        scoreCounter1.setText("" + points1);
        scoreCounter1.setBounds(800, 500, 100, 50);
        scoreCounter1.setFont(new Font("Arial", Font.PLAIN, 35));
        scoreCounter1.setOpaque(true);

        scoreCounter2.setLayout(null);
        scoreCounter2.setText("" + points2);
        scoreCounter2.setBounds(900, 500, 100, 50);
        scoreCounter2.setFont(new Font("Arial", Font.PLAIN, 35));
        scoreCounter2.setOpaque(true);

        eatScore.setLayout(null);
        eatScore.setText("Scoreboard");
        eatScore.setBounds(800, 450, 200, 50);
        eatScore.setFont(new Font("Arial", Font.PLAIN, 35));
        eatScore.setOpaque(true);
    }
}