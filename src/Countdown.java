import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Countdown  {

    static JLabel counterLabel = new JLabel();
    static JLabel counterLabel2 = new JLabel();


    int elapsedTime = 900000;
    int second = 0;
    int minute = 0;
    String secondString = String.format("%02d", second);
    String minuteString = String.format("%02d", minute);

    Timer timer = new Timer(1000, new ActionListener() {

        public void actionPerformed(ActionEvent e) {

            elapsedTime = elapsedTime - 1000;
            minute = (elapsedTime / 60000) % 60;
            second = (elapsedTime / 1000) % 60;
            secondString = String.format("%02d", second);
            minuteString = String.format("%02d", minute);
            counterLabel.setText(minuteString + ":" + secondString);

        }

    });

    Timer timer1 = new Timer(1000, new ActionListener() {

        public void actionPerformed(ActionEvent e) {

            elapsedTime = elapsedTime - 1000;
            minute = (elapsedTime / 60000) % 60;
            second = (elapsedTime / 1000) % 60;
            secondString = String.format("%02d", second);
            minuteString = String.format("%02d", minute);
            counterLabel2.setText(minuteString + ":" + secondString);

        }

    });
    Countdown() {

        counterLabel.setLayout(null);
        counterLabel2.setLayout(null);

        counterLabel.setText(minuteString + ":" + secondString);
        counterLabel.setBounds(700, 412, 200, 100);
        counterLabel.setFont(new Font("Arial", Font.PLAIN, 40));
        counterLabel.setOpaque(true);
        counterLabel.setHorizontalAlignment(JLabel.RIGHT);

        counterLabel2.setText(minuteString + ":" + secondString);
        counterLabel2.setBounds(625, 400, 200, 100);
        counterLabel2.setFont(new Font("Arial", Font.PLAIN,40));
        counterLabel2.setOpaque(true);
        counterLabel2.setHorizontalAlignment(JLabel.RIGHT);
    }
}