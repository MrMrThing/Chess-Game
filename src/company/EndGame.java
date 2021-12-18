package company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Objects;

public class EndGame extends JPanel  {
    public EndGame(Piece k, Game b_game) {

        JPanel panel1 = new JPanel();
        Label a, b, c, d, e, f;
        setBorder(new EmptyBorder(100, 10, 10, 10));
        this.setBackground(new Color(239,223,187));

        //Font
        Font f1= new Font(Font.SERIF,  Font.BOLD, 35);
        Font f2= new Font(Font.SERIF,  Font.BOLD, 20);

        panel1.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel1.setBackground(new Color(239,223,187));
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        if(k.color==b_game.player.m_color) {
            d= new Label("");
            e = new Label("");
            f= new Label("");
            a= new Label(b_game.player.m_name + " you lost!", JLabel.CENTER);
            a.setFont(f1);
            c= new Label("");
            b= new Label("Congratulation "+ b_game.player2.m_name, JLabel.CENTER);
            b.setFont(f1);
            //add all the labels to panels
            panel1.add(d,gbc);
            panel1.add(e,gbc);
            panel1.add(f,gbc);
            panel1.add(a, gbc);
            panel1.add(b, gbc);
            panel1.add(c,gbc);
            if(!Objects.equals(b_game.player2.m_name, "Guest")) {
                b_game.player2.m_win = true;
                b_game.player2.WriteEachFileWin();
            }
        }
        else if(k.color==b_game.player2.m_color) {
            d= new Label("");
            e = new Label("");
            f= new Label("");
            a= new Label(b_game.player2.m_name + " you lost!", JLabel.CENTER);
            a.setFont(f1);
            c= new Label("");
            b= new Label("Congratulation "+ b_game.player.m_name, JLabel.CENTER);
            b.setFont(f1);
            //add all the labels to panels
            panel1.add(d,gbc);
            panel1.add(e,gbc);
            panel1.add(f,gbc);
            panel1.add(a, gbc);
            panel1.add(b, gbc);
            panel1.add(c,gbc);
            if(!Objects.equals(b_game.player.m_name, "Guest")) {
                b_game.player.m_win = true;
                b_game.player.WriteEachFileWin();
            }
        }

        //button to go back to menu
        JButton close= new JButton("END");
        close.setBackground(new Color(59,47,47));
        close.setForeground(new Color(239,223,187));
        close.setFont(f2);

        //Add actionListener for button
        close.addActionListener(e1 -> {
            System.exit(0);
        });

        //Add button to JPanel
        panel1.add(close,gbc);

        this.add(panel1);
    }
}
