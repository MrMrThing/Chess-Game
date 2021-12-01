package company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Rules extends JPanel{
    public Rules(JFrame frame){
        JPanel panel1 = new JPanel();
        setBorder(new EmptyBorder(100, 10, 10, 10));
        this.setBackground(new Color(239,223,187));

        Font f1= new Font(Font.SERIF,  Font.BOLD, 35);
        Font f2= new Font(Font.SERIF,  Font.BOLD, 20);
        Font f3= new Font(Font.SERIF, Font.PLAIN,  15);

        Label a= new Label("You should have looked it up online you lazy player! ", JLabel.CENTER);
        a.setFont(f1);
        Label b= new Label("But since we are nice here are the basics:", JLabel.CENTER);
        b.setFont(f2);
        Label q= new Label("");
        Label c= new Label("");
        Label d= new Label("The Rook can move any number of squares in one direction (vertically or horizontally) if its path is not blocked.");
        d.setFont(f3);
        Label e= new Label("");
        Label f= new Label("The Queen can move in a straight line any number of squares in any one direction: horizontal, vertical, or diagonal as long as its path is not blocked by its own pieces. ");
        f.setFont(f3);
        Label g= new Label("");
        Label h= new Label("The Bishop can move any number of squares diagonally if its path is not blocked.");
        h.setFont(f3);
        Label i= new Label("");
        Label j= new Label("The Knight can jump over other pieces between its old and new squares. Think of the Knight’s move as an ”L.”");
        j.setFont(f3);
        Label k= new Label("");
        Label l= new Label("The Pawn moves straight ahead (never backward), but it captures diagonally");
        l.setFont(f3);
        Label m= new Label("");
        Label n= new Label("The King is the most important piece. When it is trapped, the whole team loses. The King can move one square in any direction");
        n.setFont(f3);
        Label o= new Label("");
        Label p= new Label("CHECK is when a king is attacked and threatened. Whereas CHECKMATE happens if f there is no way to get out of check: this ends the game");
        p.setFont(f3);
        Label r= new Label("");
        Label s= new Label("");

        panel1.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel1.setBackground(new Color(239,223,187));
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        panel1.add(a, gbc); //adds in all the labels to panels
        panel1.add(b, gbc);
        panel1.add(q, gbc);
        panel1.add(c, gbc);
        panel1.add(d,gbc);
        panel1.add(e,gbc);
        panel1.add(f,gbc);
        panel1.add(g,gbc);
        panel1.add(h,gbc);
        panel1.add(i,gbc);
        panel1.add(j,gbc);
        panel1.add(k,gbc);
        panel1.add(l,gbc);
        panel1.add(m,gbc);
        panel1.add(n,gbc);
        panel1.add(o,gbc);
        panel1.add(p,gbc);
        panel1.add(r,gbc);
        panel1.add(s,gbc);

        JButton close= new JButton("Back");
        close.setBackground(new Color(59,47,47));
        close.setForeground(new Color(239,223,187));
        close.setFont(f2);

        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu b= new Menu(frame);
                frame.getContentPane().add(b);
                setVisible(false);
                b.setVisible(true);
            }
        });

        panel1.add(close,gbc);

        this.add(panel1);

    }
}
