import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Countdown  {

    JFrame frame = new JFrame();
    JLabel counterLabel = new JLabel();
    JLabel counterLabel2 = new JLabel();


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

        counterLabel.setText(minuteString + ":" + secondString);
        counterLabel.setBounds(400, 100, 300, 200);
        counterLabel.setFont(new Font("Arial", Font.PLAIN, 40));
        counterLabel.setOpaque(true);
        counterLabel.setHorizontalAlignment(JLabel.RIGHT);

        counterLabel2.setText(minuteString + ":" + secondString);
        counterLabel2.setBounds(400,400,300,200);
        counterLabel2.setFont(new Font("Arial", Font.PLAIN,40));
        counterLabel2.setOpaque(true);
        counterLabel2.setHorizontalAlignment(JLabel.RIGHT);

        frame.add(counterLabel);
        frame.add(counterLabel2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}